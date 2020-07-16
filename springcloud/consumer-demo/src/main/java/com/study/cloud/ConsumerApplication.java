package com.study.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

//@SpringBootApplication
//@EnableDiscoveryClient  //服务被发现
//@EnableCircuitBreaker  //开启熔断
@SpringCloudApplication //包含以上3个注解
@EnableFeignClients //开启feign
public class ConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }
    @Bean
    @LoadBalanced //robbin 负载均衡 ，将服务名替换成可以访问的地址 没有这个注解访问eurake上的服务无效
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
