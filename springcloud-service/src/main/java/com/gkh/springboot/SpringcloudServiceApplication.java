package com.gkh.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * eureka，那么就推荐@EnableEurekaClient，如果是其他的注册中心，那么推荐使用@EnableDiscoveryClient。
 *  # @EnableEurekaClient基于spring-cloud-netflix。
 */
@SpringBootApplication //启动注解,表示此为spring-boot的一个入口类
@EnableEurekaClient// 让服务使用eureka服务器,实现服务注册和发现
public class SpringcloudServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudServiceApplication.class, args);
	}
}
