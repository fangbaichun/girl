package com.fbc.framework.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @ClassName UserController
 * @Author fangbc
 * @Date 2020/6/13 17:31
 * @Version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/getUser")
    public String getUser() {
        return "user";
    }
}
