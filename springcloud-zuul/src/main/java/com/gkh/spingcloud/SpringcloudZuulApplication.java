package com.gkh.spingcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/** zuul 相当于是设备和 Netflix 流应用的 Web 网站后端所有请求的前门。
 *  zuul的核心是一系列的filters, 其作用可以类比Servlet框架的Filter.
 *  Zuul的主要功能是路由转发和过滤器,zuul默认和Ribbon结合实现了服务端的负载均衡的功能
 */
@SpringBootApplication //启动注解,表示此为spring-boot的一个入口类
@EnableEurekaClient // 让服务使用eureka服务器
@EnableZuulProxy  //开启zuul的功能
public class SpringcloudZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudZuulApplication.class, args);
	}
}
