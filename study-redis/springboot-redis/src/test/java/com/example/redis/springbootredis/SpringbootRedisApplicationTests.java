package com.example.redis.springbootredis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class SpringbootRedisApplicationTests {
    @Autowired
    private StringRedisTemplate template;
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void contextLoads() {
       String str= template.randomKey();
        System.out.println(str);
    }

}
