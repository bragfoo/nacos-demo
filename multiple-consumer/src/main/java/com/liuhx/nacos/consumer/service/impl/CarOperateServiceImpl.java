package com.liuhx.nacos.consumer.service.impl;

import java.util.concurrent.locks.Lock;

import javax.annotation.Resource;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.liuhx.nacos.common.config.exception.CommonException;
import com.liuhx.nacos.common.dubbo.OperateCarService;
import com.liuhx.nacos.consumer.service.CarOperateService;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.http.HttpStatus;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.integration.zookeeper.config.LeaderInitiatorFactoryBean;
import org.springframework.integration.zookeeper.lock.ZookeeperLockRegistry;
import org.springframework.stereotype.Service;

@Service
public class CarOperateServiceImpl implements CarOperateService {
    @Resource
    RedisLockRegistry redisLockRegistry;
    @DubboReference
    private OperateCarService operateService;
    @Resource
    ZookeeperLockRegistry zookeeperLockRegistry;
    @Override
    public boolean operate(String method, String carId) {

        Lock lock = redisLockRegistry.obtain(carId);
        Lock zooLock = zookeeperLockRegistry.obtain(carId);
        if (lock.tryLock()) {
            boolean operate = false;
            try {
                operate = operateService.operate(method, carId);
            } catch (Exception e) {
                e.printStackTrace();
                throw new CommonException(HttpStatus.OK, "10000", "操作错误");
            } finally {
                lock.unlock();
            }
            return operate;
        }
        return false;
    }
}
