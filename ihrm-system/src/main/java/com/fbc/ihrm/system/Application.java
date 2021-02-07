package com.fbc.ihrm.system;

import com.fbc.girl.server.annotation.GirlServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@GirlServer
@EntityScan(basePackages = "com.fbc.ihrm.entity")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
