package com.example.provider.controller;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @ClassName MyController
 * @Author fangbc
 * @Date 2020/6/6 13:33
 * @Version 1.0
 */
@RestController
@RequestMapping("/provider")
public class MyController {

    private String datasource ="bbb";

    @GetMapping("/bbb")
    public String bbb() {
//        Config config = ConfigService.getConfig("application");
//        this.datasource = config.getProperty("datasource",null);
        return datasource;
    }
}
