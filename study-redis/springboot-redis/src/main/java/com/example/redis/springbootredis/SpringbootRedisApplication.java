package com.example.redis.springbootredis;

import com.example.redis.springbootredis.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class SpringbootRedisApplication {
    @Autowired
    private StringRedisTemplate redisTemplate; //k-v都必须是String类型

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate<Object,Object> temple; //可以指定key-value类型


    @RequestMapping("getTemp")
    public Object getTemp(){
        RedisSerializer<?> keySerializer = temple.getKeySerializer();
        return "keySerializer";
    }

    @RequestMapping("/test")
    public Object test(){

//        redisTemplate.opsForValue();//操作字符串
//        redisTemplate.opsForHash();//操作hash
//        redisTemplate.opsForList();//操作list
//        redisTemplate.opsForSet();//操作set
//        redisTemplate.opsForZSet();//操作有序set

        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        ListOperations<String, String> listOps = redisTemplate.opsForList();
        ops.getOperations().delete("zjl");
        ops.set("redisTest","xxxxxxxxxx");
        listOps.leftPush("list", "zjl" );
        listOps.leftPush("list", "lbw");

        //修改序列化方式 防止乱码
        temple.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        temple.setKeySerializer(new StringRedisSerializer());
        ValueOperations<Object, Object> objOps = temple.opsForValue();

        objOps.set("user:2",new User("周家乐",11));
        User o = (User) objOps.get("user:2");

        return o;


    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootRedisApplication.class, args);
    }


}
