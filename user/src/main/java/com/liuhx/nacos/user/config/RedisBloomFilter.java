package com.liuhx.nacos.user.config;

import com.google.common.base.Preconditions;
import com.liuhx.nacos.common.config.BloomFilterHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @program spring-cloud-consumer
 * @description:
 * @author: liuhx
 * @create: 2021/03/23 11:45
 */
@Service
@Slf4j
public class RedisBloomFilter {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 根据给定的布隆过滤器添加值
     */
    public <T> void addByBloomFilter(BloomFilterHelper<T> bloomFilterHelper, String key, T value) {
        Preconditions.checkArgument(bloomFilterHelper != null, "bloomFilterHelper不能为空");
        int[] offset = bloomFilterHelper.murmurHashOffset(value);
        for (int i : offset) {
            log.info("key : {}, value :{}", key, i);
            stringRedisTemplate.opsForValue().setBit(key, i, true);
        }
    }

    /**
     * 根据给定的布隆过滤器判断值是否存在
     */
    public <T> boolean includeByBloomFilter(BloomFilterHelper<T> bloomFilterHelper, String key, T value) {
        Preconditions.checkArgument(bloomFilterHelper != null, "bloomFilterHelper不能为空");
        int[] offset = bloomFilterHelper.murmurHashOffset(value);
        for (int i : offset) {
            log.info("key : {}, value :{}", key, i);
            if (!stringRedisTemplate.opsForValue().getBit(key, i)) {
                return false;
            }
        }

        return true;
    }
}
