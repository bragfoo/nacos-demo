package com.liuhx.nacos.common.config.response;

import org.springframework.core.MethodParameter;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.io.IOException;
import java.util.List;

/**
 * @program iot-service
 * @description: 返回值统一为responseEntity
 * @author: liuhx
 * @create: 2020/05/09 17:10
 */
public class UnifiedRequestResponseBodyMethodProcessor extends RequestResponseBodyMethodProcessor {
    public UnifiedRequestResponseBodyMethodProcessor(List<HttpMessageConverter<?>> converters) {
        super(converters);
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest)
            throws IOException, HttpMediaTypeNotAcceptableException, HttpMessageNotWritableException {
        if (returnValue instanceof CommonResponse || returnValue instanceof ResponseEntity) { // 如果是统一返回类型，不做处理
            super.handleReturnValue(returnValue, returnType, mavContainer, webRequest);
        } else {
            // 如果不是统一返回类型，转成统一返回类型
            super.handleReturnValue(new CommonResponse<>(returnValue), returnType, mavContainer, webRequest);
        }
    }
}
