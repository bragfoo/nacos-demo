package com.liuhx.nacos.common.config.response;

import lombok.Data;

/**
 * @author: wuqiupeng <qpwu@vw-mobvoi.com>
 * @date: 2020/4/30 14:50
 * @description: 通用返回类
 */
@Data
public class CommonResponse<T> {
    private long timestamp;
    private String message;
    private String code;
    private T data;

    public CommonResponse() {
        timestamp = System.currentTimeMillis();
        code = "0";
        message = "SUCCESS";
    }

    public CommonResponse(T d) {
        timestamp = System.currentTimeMillis();
        code = "0";
        message = "SUCCESS";
        data = d;
    }
}
