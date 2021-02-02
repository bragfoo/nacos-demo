package com.liuhx.nacos.nacos.consumer.springcloudconsumer.feign;

import java.util.List;

import com.liuhx.nacos.nacos.consumer.springcloudconsumer.config.FallbackClientFactory;
import com.liuhx.nacos.nacos.consumer.springcloudconsumer.config.response.CommonResponse;
import com.liuhx.nacos.nacos.consumer.springcloudconsumer.entity.po.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "spring-cloud-demo", path = "/user/", fallbackFactory = FallbackClientFactory.class)
public interface UserService {
    @GetMapping("findAllUser")
    CommonResponse<List<User>> findAllUser();
}
