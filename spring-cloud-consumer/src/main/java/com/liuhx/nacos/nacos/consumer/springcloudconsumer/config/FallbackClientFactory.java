package com.liuhx.nacos.nacos.consumer.springcloudconsumer.config;

import com.liuhx.nacos.nacos.consumer.springcloudconsumer.config.response.CommonResponse;

import org.springframework.stereotype.Component;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FallbackClientFactory implements FallbackFactory<CommonResponse<String>>{

    @Override
    public CommonResponse<String> create(Throwable arg0) {
        // TODO Auto-generated method stub
        log.error("hystrix ={} ", arg0.getMessage());
        CommonResponse<String> response = new CommonResponse<String>();
        response.setErrorCode("500");
        response.setErrorMessage("服务异常");
        return response;
    }
    
}
