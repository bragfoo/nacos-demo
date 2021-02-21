package com.liuhx.nacos.consumer.service.impl;

import com.liuhx.nacos.consumer.service.CarOperateService;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.locks.Lock;

@Service
public class CarOperateServiceImpl implements CarOperateService {
    @Resource
    RedisLockRegistry redisLockRegistry;
    @Override
    public boolean operate(String method, String carId) {
        Lock lock = redisLockRegistry.obtain(carId);
        if (lock.tryLock()){
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
            return true;
        }
        return false;
    }
}
