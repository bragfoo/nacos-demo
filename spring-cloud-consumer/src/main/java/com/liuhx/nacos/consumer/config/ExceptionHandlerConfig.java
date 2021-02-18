package com.liuhx.nacos.consumer.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ControllerAdvice;
import com.liuhx.nacos.common.config.exception.CommonExceptionHandler;

/**
 * @program iot-service
 * @description: 异常处理配置
 * @author: liuhx
 * @create: 2020/05/09 18:54
 */
@Configuration
@ConditionalOnMissingBean(annotation = ControllerAdvice.class)
public class ExceptionHandlerConfig {
    @Bean
    public CommonExceptionHandler CommonExceptionHandler() {
        return new CommonExceptionHandler();
    }
}
