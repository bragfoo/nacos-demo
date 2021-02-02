package com.liuhx.nacos.nacos.consumer.springcloudconsumer.config.exception;

import com.alibaba.fastjson.JSONObject;
import com.liuhx.nacos.nacos.consumer.springcloudconsumer.config.response.CommonResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;
import sun.security.validator.ValidatorException;

/**
 * @author: wuqiupeng <qpwu@vw-mobvoi.com>
 * @date: 2020/4/30 14:47
 * @description: 自定义异常处理类
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@Slf4j
public class CommonExceptionHandler {

    private static final JSONObject DEFAULT_JSON = new JSONObject();

    @ExceptionHandler(CommonException.class)
    public ResponseEntity<CommonResponse> handleIOTCommonException(CommonException e) {
        CommonResponse response = new CommonResponse();
        response.setErrorCode(e.getErrorCode());
        response.setErrorMessage(e.getErrorMessage());
        response.setData(DEFAULT_JSON);
        return new ResponseEntity<>(response, e.getHttpStatus());
    }

    /**
     * 验证异常
     *
     * @return
     */
    @ExceptionHandler(ValidatorException.class)
    public ResponseEntity<CommonResponse> handleMethodArgumentNotValidException(ValidatorException ex) {
        log.info("MethodArgumentNotValidException", ex);
        CommonResponse response = new CommonResponse();
        response.setErrorCode("20000");
        response.setErrorMessage(ex.getMessage());
        response.setData(DEFAULT_JSON);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * 全局异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CommonResponse> handleException(BindException e) {
        log.error(e.getMessage(), e);
        CommonResponse response = new CommonResponse();
        response.setErrorCode("100000");
        response.setErrorMessage(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        response.setData(DEFAULT_JSON);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
