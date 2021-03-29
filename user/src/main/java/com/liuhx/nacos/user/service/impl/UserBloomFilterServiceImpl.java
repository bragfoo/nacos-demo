package com.liuhx.nacos.user.service.impl;

import com.liuhx.nacos.common.config.BloomFilterHelper;
import com.liuhx.nacos.user.config.RedisBloomFilter;
import com.liuhx.nacos.user.service.UserBloomFilterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @program spring-cloud-consumer
 * @description:
 * @author: liuhx
 * @create: 2021/03/23 14:00
 */
@Service
@Slf4j
public class UserBloomFilterServiceImpl implements UserBloomFilterService {
    @Resource
    private RedisBloomFilter redisBloomFilter;
    @Resource
    private BloomFilterHelper bloomFilterHelper;
    @Override
    public void insert(String userId) {
        redisBloomFilter.addByBloomFilter(bloomFilterHelper,"user",userId);
    }

    @Override
    public Boolean checkUser(String userId) {
        return redisBloomFilter.includeByBloomFilter(bloomFilterHelper,"user",userId);
    }
}
