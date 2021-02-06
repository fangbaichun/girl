package com.fbc.girl.server.boot;

import cn.hutool.core.lang.Singleton;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.fbc.girl.common.utils.IdHelper;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionCustomizer;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

@NoArgsConstructor
public class SnowflakeIdWorkerInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
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
//        beanDefinitionBuilder.addPropertyReference("snowflake", "snowflake");

        // 注册bean
        defaultListableBeanFactory.registerBeanDefinition("snowflake", beanDefinitionBuilder.getRawBeanDefinition());

    }

}
