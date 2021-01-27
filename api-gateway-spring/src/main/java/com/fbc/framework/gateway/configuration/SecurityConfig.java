package com.fbc.framework.gateway.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * @Description
 * @ClassName SecurityConfig
 * @Author fangbc
 * @Date 2020/6/13 17:20
 * @Version 1.0
 */
@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    /**
     * 用户的接口类
     * @return
     */
    @Bean
    public MapReactiveUserDetailsService userDetailsService() {
        //自定义一个用户
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("123456")
                .roles("ADMIN","aaa")
                .build();
        return new MapReactiveUserDetailsService(user);
    }

    /**
     * 主要过滤配置类
     * @param http
     * @return
     */
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
            .authorizeExchange()
            .pathMatchers("/order/**").hasAuthority("aaa")
            .pathMatchers("/user/**").hasRole("ADMIN")
            .anyExchange().authenticated()
            .and()
            .httpBasic().and()
            .formLogin();
        return http.build();
    }
}
