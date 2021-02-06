package com.fbc.girl.server.annotation;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

import java.lang.annotation.*;

/**
 * @Description
 * @ClassName GirlServer
 * @Author fangbc
 * @Date 2020/6/6 20:03
 * @Version 1.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@SpringBootApplication
@EnableDiscoveryClient
@ServletComponentScan
@ComponentScan //扫描本项目包
@ComponentScan("com.fbc.girl.server.exception") //扫描全局异常处理包
public @interface GirlServer {
}
