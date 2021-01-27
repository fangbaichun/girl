package com.example.customer.controller;

//import com.ctrip.framework.apollo.Config;
//import com.ctrip.framework.apollo.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Description
 * @ClassName MyController
 * @Author fangbc
 * @Date 2020/6/6 13:32
 * @Version 1.0
 */
@RestController
@RequestMapping("customer")
public class MyController {

    @Autowired
    private RestTemplate restTemplate;
    @Value("${datasource:aaa}")
    private String datasource;

    @GetMapping("/aaa")
    public String aaa() {
//        String bbb = restTemplate.getForObject("http://PROVIDER/provider/bbb", String.class);
        return datasource;
    }
}
