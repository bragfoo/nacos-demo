package com.liuhx.nacos.user.config;

import java.time.Duration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
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

}
