package com.fbc.monitor.admin;


import com.fbc.girl.server.annotation.GirlServer;
import org.springframework.boot.SpringApplication;

@GirlServer
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
