package com.gkh.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * eureka，那么就推荐@EnableEurekaClient，如果是其他的注册中心，那么推荐使用@EnableDiscoveryClient。
 *  # @EnableEurekaClient基于spring-cloud-netflix。
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class}) //启动注解,表示此为spring-boot的一个入口类; exclude:这个注解去除掉默认的数据库配置，然后我们自己去配置database.
@EnableEurekaClient// 让服务使用eureka服务器,实现服务注册和发现
@EnableCaching //开启缓存
public class SpringcloudServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudServiceApplication.class, args);
	}
}
