package com.liuhx.nacos.consumer.controller;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import com.liuhx.nacos.common.config.response.CommonResponse;
import com.liuhx.nacos.common.entity.po.User;
import com.liuhx.nacos.consumer.feign.UserService;

import com.liuhx.nacos.consumer.service.CarOperateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("car")
@Slf4j
public class CarController {
    @Resource
    UserService userService;
    @Resource
    CarOperateService carOperateService;

    @GetMapping("find")
    public String find() {
        return JSON.toJSONString(userService.findAllUser());
    }

    @GetMapping("getToken")
    public User getToken() {
        CommonResponse<User> userResponse = userService.getToken("asdasd");
        log.info(JSON.toJSONString(userResponse));
        return userResponse.getData();
    }

    @PostMapping("openDoor")
    public Boolean openDoor(String carId) {
        return carOperateService.operate("openDoor", carId);
    }

    @GetMapping("testException")
    public int testException() {
        log.info("输出{}", userService.testException());
        return 1;
    }
}
