package com.liuhx.nacos.consumer;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class SpringCloudConsumerApplication {

	public static void main(String[] args) {
		System.setProperty("nacos.logging.default.config.enabled", "false");
		SpringApplication.run(SpringCloudConsumerApplication.class, args);
	}

}
