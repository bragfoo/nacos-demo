package com.liuhx.nacos.consumer.config;

import java.time.Duration;

import com.google.common.base.Charsets;
import com.google.common.hash.Funnel;
import com.liuhx.nacos.common.config.BloomFilterHelper;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.integration.metadata.MetadataStore;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.integration.zookeeper.config.CuratorFrameworkFactoryBean;
import org.springframework.integration.zookeeper.config.LeaderInitiatorFactoryBean;
import org.springframework.integration.zookeeper.lock.ZookeeperLockRegistry;
import org.springframework.integration.zookeeper.metadata.ZookeeperMetadataStore;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableFeignClients(basePackages = "com.liuhx.nacos.consumer.feign")
@EnableDiscoveryClient
public class AppConfig {
    @Value("${zookeeper.connectionString}")
    private String zookeeperUrl;
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.setConnectTimeout(Duration.ofSeconds(3)).setReadTimeout(Duration.ofSeconds(30)).build();
    }

    @Bean
    public RedisLockRegistry redisLockRegistry(RedisConnectionFactory redisConnectionFactory) {
        return new RedisLockRegistry(redisConnectionFactory, "nacos:lock");
    }
    @Bean
    public CuratorFrameworkFactoryBean curatorFrameworkFactoryBean(){
        return new CuratorFrameworkFactoryBean(zookeeperUrl);
    }
    @Bean
    public ZookeeperLockRegistry zookeeperLockRegistry(CuratorFramework client) {
        return new ZookeeperLockRegistry(client, "zookeeper:lock:");
    }

}
