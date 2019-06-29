package com.gkh.springcloudregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@SpringBootApplication //启动注解,表示此为spring-boot的一个入口类
@EnableEurekaServer // 用来指定该项目为Eureka的服务注册中心
public class SpringcloudRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudRegistryApplication.class, args);
	}
}
