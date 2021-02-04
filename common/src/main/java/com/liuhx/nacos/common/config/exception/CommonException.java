package com.liuhx.nacos.common.config.exception;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author: hxliu
 * @date: 2020/4/30 14:40
 * @description: 自定义异常
 */
public class CommonException extends RuntimeException{
  /**
   *
   */
  private static final long serialVersionUID = 1L;

  @Getter
  private String errorCode;

  @Getter
  private String errorMessage;

  @Getter
  private HttpStatus httpStatus;

  public CommonException(HttpStatus httpStatus, String errorCode, String errorMessage) {
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
    this.httpStatus = httpStatus;
  }

  @Override
  public String toString() {
    return JSON.toJSONString(this);
  }

}
