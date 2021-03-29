package com.liuhx.nacos.user.config;

import java.time.Duration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import com.google.common.base.Charsets;
import com.google.common.hash.Funnel;
import com.liuhx.nacos.common.config.BloomFilterHelper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.client.RestTemplate;
import org.springframework.data.repository.query.QueryLookupStrategy;

@Configuration
@EnableDiscoveryClient
@EnableMongoRepositories(basePackages = {"com.liuhx.nacos.common.repo"}, queryLookupStrategy = QueryLookupStrategy.Key.CREATE_IF_NOT_FOUND)
public class AppConfig {
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.setConnectTimeout(Duration.ofSeconds(3)).setReadTimeout(Duration.ofSeconds(30)).build();
    }
    @Resource
    MappingMongoConverter mappingMongoConverter;

    @PostConstruct
    public void init() {
        // 消除 spring-data-mongo 自动映射生成的collection中带有的_class属性
        mappingMongoConverter.setTypeMapper(defaultMongoTypeMapper());
    }

    @Bean
    public DefaultMongoTypeMapper defaultMongoTypeMapper() {
        return new DefaultMongoTypeMapper(null, mappingMongoConverter.getMappingContext());
    }

    //初始化布隆过滤器，放入到spring容器里面
    @Bean
    public BloomFilterHelper<String> initBloomFilterHelper() {
        return new BloomFilterHelper<>((Funnel<String>) (from, into) -> into.putString(from, Charsets.UTF_8).putString(from, Charsets.UTF_8), 1000000, 0.01);
    }

}
