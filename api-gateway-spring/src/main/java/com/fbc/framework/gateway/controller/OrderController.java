package com.fbc.framework.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @ClassName OrderController
 * @Author fangbc
 * @Date 2020/6/13 17:32
 * @Version 1.0
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @GetMapping("/getOrder")
    public String getOrder() {
        return "order";
    }
}
