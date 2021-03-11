package com.liuhx.nacos.consumer.feign;

import java.util.List;

import com.liuhx.nacos.common.config.response.CommonResponse;
import com.liuhx.nacos.common.entity.po.User;
import com.liuhx.nacos.consumer.feign.fallback.UserFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "spring-cloud-user", path = "/user/", fallback = UserFallbackFactory.class)
public interface UserService {
    @GetMapping("findAllUser")
    CommonResponse<List<User>> findAllUser();
    @RequestMapping(value = "getToken",method = RequestMethod.GET)
    CommonResponse<User> getToken(@RequestParam("token") String token);
    @GetMapping("testException")
    Integer testException();
}
