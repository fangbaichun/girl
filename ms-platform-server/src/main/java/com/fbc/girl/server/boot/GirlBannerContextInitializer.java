package com.fbc.girl.server.boot;

import cn.hutool.system.SystemUtil;
import lombok.NoArgsConstructor;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @Description
 * @ClassName GirlBannerContextInitializer
 * @Author fangbc
 * @Date 2020/6/8 20:02
 * @Version 1.0
 */
@NoArgsConstructor
public class GirlBannerContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        String name = applicationContext.getClass().getName();
        if (name.endsWith("AnnotationConfigServletWebServerApplication") || name.endsWith("AnnotationConfigReactiveWebServerApplicationContext")) {

        }
    }

    static {
        System.setProperty("clientIP",SystemUtil.getHostInfo().getAddress());
    }
}
