package com.example.provider;

import com.fbc.girl.server.annotation.GirlServer;
import org.springframework.boot.SpringApplication;

@GirlServer
public class ProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class, args);
    }

}
