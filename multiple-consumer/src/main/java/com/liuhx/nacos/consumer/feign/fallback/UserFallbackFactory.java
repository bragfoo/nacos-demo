package com.liuhx.nacos.consumer.feign.fallback;

import com.liuhx.nacos.common.config.response.CommonResponse;

import com.liuhx.nacos.common.entity.po.User;
import com.liuhx.nacos.consumer.feign.UserService;
import org.springframework.stereotype.Component;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Component
public class UserFallbackFactory implements UserService {


    @Override
    public CommonResponse<List<User>> findAllUser() {

        return new CommonResponse<>("1","error",null);
    }

    @Override
    public CommonResponse<User> getToken(String token) {
        return new CommonResponse<>("1","error",null);
    }

    @Override
    public Integer testException() {
        return 0;
    }
}
