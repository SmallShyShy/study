package com.dubbo.study;

import com.dubbo.study.api.UserApi;
import lombok.extern.log4j.Log4j2;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author zjl
 * @date 2020/07/15 13:49
 */
@SpringBootApplication
@EnableDubbo
@Log4j2
public class ConsumerApplication{
    @Reference
    UserApi userApi;
    public static void main(String[] args) {

            SpringApplication.run(ConsumerApplication.class, args);
    }
    @Bean
    public ApplicationRunner runner() {
        return args -> {
            log.error(userApi.providerUsers());
        };
    }

}
