package com.liuhx.nacos.netty.service.impl;

import com.liuhx.nacos.common.dubbo.OperateCarService;

import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class OperateCarImpl implements OperateCarService {

    @Override
    public boolean operate(String method, String carId) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }
    
}
