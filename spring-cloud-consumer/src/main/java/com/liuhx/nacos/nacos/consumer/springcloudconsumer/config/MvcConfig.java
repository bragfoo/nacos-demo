package com.liuhx.nacos.nacos.consumer.springcloudconsumer.config;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import com.liuhx.nacos.nacos.consumer.springcloudconsumer.config.response.UnifiedRequestResponseBodyMethodProcessor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Resource
    RequestMappingHandlerAdapter adapter;

    /**
     * 统一所有rest api接口的返回值结构
     */
    @PostConstruct
    public void init() {
        // 统一返回值结构
        List<HandlerMethodReturnValueHandler> handlers = adapter.getReturnValueHandlers();
        if (handlers != null) {
            List<HandlerMethodReturnValueHandler> newHandlers = handlers.stream().map(handler -> {
                if (handler instanceof RequestResponseBodyMethodProcessor) {
                    return new UnifiedRequestResponseBodyMethodProcessor(adapter.getMessageConverters());
                } else {
                    return handler;
                }
            }).collect(Collectors.toList());
            // 替换returnValueHandlers
            adapter.setReturnValueHandlers(newHandlers);
        }
    }
     /**
     * 日志记录过滤器
     *
     * @return 日志记录过滤器
     */
    @Bean
    public CommonsRequestLoggingFilter commonsRequestLoggingFilter() {
        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
        filter.setIncludeQueryString(true);
        filter.setIncludeHeaders(true);
        filter.setIncludePayload(true);
        filter.setIncludeClientInfo(true);
        filter.setMaxPayloadLength(1024);
        return filter;
    }

}
