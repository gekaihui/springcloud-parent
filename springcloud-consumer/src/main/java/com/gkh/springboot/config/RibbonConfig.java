package com.gkh.springboot.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RibbonConfig {
    //restTemplate + ribbon 实现负载均衡（客户端的均衡负载）
    @Bean
    @LoadBalanced
    //代表对服务多个实例调用时开启负载均衡
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
