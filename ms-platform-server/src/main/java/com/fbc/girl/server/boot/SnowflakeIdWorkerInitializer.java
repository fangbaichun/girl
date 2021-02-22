package com.fbc.girl.server.boot;

import cn.hutool.core.lang.Singleton;
import cn.hutool.core.lang.Snowflake;
import com.fbc.girl.common.utils.IdHelper;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 初始化全局的雪花ID生成器
 */
@NoArgsConstructor
public class SnowflakeIdWorkerInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        String name = applicationContext.getClass().getName();
        //当web容器初始化时，再进行初始化
        if (name.endsWith("AnnotationConfigServletWebServerApplicationContext")) {
            long datacenterId = IdHelper.getDatacenterId(IdHelper.maxDatacenterId);
            long maxWorkerId = IdHelper.getMaxWorkerId(datacenterId, IdHelper.maxWorkerId);

            // 获取bean工厂并转换为DefaultListableBeanFactory
            DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) applicationContext.getBeanFactory();

            // 通过BeanDefinitionBuilder创建bean定义
            BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(Snowflake.class,() -> {
                Snowflake snowflake = Singleton.get(Snowflake.class, datacenterId, maxWorkerId);
                return snowflake;
            });

            // 设置属性userService,此属性引用已经定义的bean:userService,这里userService已经被spring容器管理了.
            // beanDefinitionBuilder.addPropertyReference("snowflake", "snowflake");

            // 注册bean
            defaultListableBeanFactory.registerBeanDefinition("snowflake", beanDefinitionBuilder.getRawBeanDefinition());
        }
    }

}
