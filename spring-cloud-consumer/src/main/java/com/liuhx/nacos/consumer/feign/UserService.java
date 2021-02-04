package com.liuhx.nacos.consumer.feign;

import java.util.List;

import com.liuhx.nacos.common.config.response.CommonResponse;
import com.liuhx.nacos.common.entity.po.User;
import com.liuhx.nacos.consumer.config.FallbackClientFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "spring-cloud-user", path = "/user/", fallbackFactory = FallbackClientFactory.class)
public interface UserService {
    @GetMapping("findAllUser")
    CommonResponse<List<User>> findAllUser();
}
