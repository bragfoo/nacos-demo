package com.liuhx.nacos.consumer.controller;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import com.liuhx.nacos.consumer.feign.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("car")
public class CarController {
    @Resource
    UserService userService;
    @GetMapping("find")
    public String find(){
        return JSON.toJSONString(userService.findAllUser());
    }
}
