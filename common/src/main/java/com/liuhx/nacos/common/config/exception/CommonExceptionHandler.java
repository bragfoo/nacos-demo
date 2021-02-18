package com.liuhx.nacos.common.config.exception;

import com.liuhx.nacos.common.config.response.CommonResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: 
 * @date: 2020/4/30 14:47
 * @description: 自定义异常处理类
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@Slf4j
public class CommonExceptionHandler {


    @ExceptionHandler(CommonException.class)
    public ResponseEntity<CommonResponse<String>> handleIOTCommonException(CommonException e) {
        CommonResponse<String> response = new CommonResponse<String>();
        response.setCode(e.getErrorCode());
        response.setMessage(e.getErrorMessage());
        response.setData("");
        return new ResponseEntity<>(response, e.getHttpStatus());
    }

    /**
     * 验证异常
     *
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CommonResponse<String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.info("MethodArgumentNotValidException", ex);
        CommonResponse<String> response = new CommonResponse<String>();
        response.setCode("20000");
        response.setMessage(ex.getMessage());
        response.setData("");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * RuntimeException异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<CommonResponse<String>> handleException(RuntimeException e) {
        log.error(e.getMessage(), e);
        CommonResponse<String> response = new CommonResponse<String>();
        response.setCode("10000");
        response.setMessage(e.getMessage());
        response.setData("");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
