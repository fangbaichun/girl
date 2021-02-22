package com.fbc.frame.gateway.config;

import com.fbc.frame.gateway.handler.JsonServerAuthenticationFailureHandler;
import com.fbc.frame.gateway.handler.JsonServerAuthenticationSuccessHandler;
import com.fbc.frame.gateway.handler.ReactiveUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.DelegatingReactiveAuthenticationManager;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.ServerAuthenticationFailureHandler;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.security.web.server.context.WebSessionServerSecurityContextRepository;
import reactor.core.publisher.Mono;

import java.util.LinkedList;

/**
 * @author qinyong
 * @Title:
 * @Package com.sisheng.authority.security.config
 * @Description: created by IntelliJ IDEA
 * @date 2019-06-03 09:05
 */
@Configuration
/**
 * 启用webflux登陆权限校验
 */
@EnableWebFluxSecurity
/**
 * 启用@PreAuthorize注解配置，如果不加这个注解的话，即使方法中加了@PreAuthorize也不会生效
 */
@EnableReactiveMethodSecurity
public class SecurityConfig {

    private static final String[] AUTH_WHITELIST = new String[]{"/login", "/actuator/**"};

    @Value("${login.page:/login}")
    private String loginPage;

    @Bean
    ReactiveAuthenticationManager reactiveAuthenticationManager() {
        final ReactiveUserDetailsService detailsService = userDetailsService();
        LinkedList<ReactiveAuthenticationManager> managers = new LinkedList<>();
        managers.add(authentication -> {
            // 其他登陆方式 (比如手机号验证码登陆) 可在此设置不得抛出异常或者 Mono.error
            return Mono.empty();
        });
        // 必须放最后不然会优先使用用户名密码校验但是用户名密码不对时此 AuthenticationManager 会调用 Mono.error 造成后面的 AuthenticationManager 不生效
        managers.add(new UserDetailsRepositoryReactiveAuthenticationManager(detailsService));
        return new DelegatingReactiveAuthenticationManager(managers);
    }

    /**
     * 将登陆后的用户及权限信息存入session中
     * @return
     */
    @Bean
    ServerSecurityContextRepository serverSecurityContextRepository() {
        return new WebSessionServerSecurityContextRepository();
    }

    /**
     * 密码加密工具
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 自定义获取用户信息，此处使用mysql基于RBAC模式
     * @return
     */
    @Bean
    public ReactiveUserDetailsService userDetailsService() {
        return new ReactiveUserDetailsServiceImpl();
    }

    /**
     * 此处的代码会放在SecurityConfig类中，此处只是摘要下
     * @param http
     * @return
     */
    @Bean
    SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) {
        ServerHttpSecurity.FormLoginSpec formLoginSpec = http.formLogin();
        return formLoginSpec
//                .loginPage(loginPage)
                .authenticationSuccessHandler(createAuthenticationSuccessHandler())
                .authenticationFailureHandler(createAuthenticationFailureHandler())
                .and().csrf().disable()
                .httpBasic().disable()
                .authorizeExchange()
                .pathMatchers(AUTH_WHITELIST).permitAll()
                .anyExchange().authenticated()
                .and().build();
    }

    /**
     * 登陆成功后执行的处理器
     * @return
     */
    private ServerAuthenticationSuccessHandler createAuthenticationSuccessHandler() {
        return new JsonServerAuthenticationSuccessHandler();
    }

    /**
     * 登陆失败后执行的处理器
     * @return
     */
    private ServerAuthenticationFailureHandler createAuthenticationFailureHandler() {
        return new JsonServerAuthenticationFailureHandler();
    }
}