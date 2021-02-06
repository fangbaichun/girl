package com.fbc.ihrm.company;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.fbc.girl.common.utils.IdHelper;
import com.fbc.girl.server.annotation.GirlServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

@GirlServer
@EntityScan(basePackages = "com.fbc.ihrm.entity")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

}
