package com.study.redis;

import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * @author zjl
 * @date 2020/07/20 14:38
 */
public class JedisMain {
    public static void main(String[] args) {
        Jedis jedis=new Jedis("127.0.0.1",6379);
        String ping = jedis.ping();
        System.out.println(ping);
        jedis.select(0);
        Set<String> keys = jedis.keys("*");
        System.out.println(keys);
        jedis.set("zjl","123");
        System.out.println(jedis.get("zjl"));
        jedis.mset("k1", "v1", "k2", "v2", "k3", "v3");
        System.out.println(jedis.get("k3"));
//        jedis.lpush("lp","1");
//
//        String lp = jedis.getrange("lp", 0, -1);
//        System.out.println(lp);
        jedis.close();

    }
}
