package com.liuhx.nacos.demo.springclouddemo.config.response;

import lombok.Data;

/**
 * @author: wuqiupeng <qpwu@vw-mobvoi.com>
 * @date: 2020/4/30 14:50
 * @description: 通用返回类
 */
@Data
public class CommonResponse<T> {
    private long timestamp;
    private String errorMessage;
    private String errorCode;
    private T data;

    public CommonResponse() {
        timestamp = System.currentTimeMillis();
        errorCode = "0";
        errorMessage = "SUCCESS";
    }

    public CommonResponse(T d) {
        timestamp = System.currentTimeMillis();
        errorCode = "0";
        errorMessage = "SUCCESS";
        data = d;
    }
}
