package org.example.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author zjl
 * @date 2020/07/20 17:45
 */
@Aspect
@Component
@EnableAspectJAutoProxy
public class UserAspectJ {
    private static String preFix = "user:";
    @Autowired
    private JedisPool jedisPool;

    @Pointcut("execution(* org.example.service.impl.UserServiceImpl.insert(..))")
    public void userInsert() {
    }

    @Pointcut("execution(* org.example.service.impl.UserServiceImpl.deleteById(..))")
    public void userDelete() {
    }

    @Pointcut("execution(* org.example.service.impl.UserServiceImpl.findById(..))")
    public void findById() {
    }

    @Around("findById()")
    public Object processUserFindById(ProceedingJoinPoint joinPoint) throws Throwable {

           System.out.println("aop findone");
           Integer id = (Integer) joinPoint.getArgs()[0];
           System.out.println(id);
           Jedis resource = jedisPool.getResource();
        try {
           if (id != null) {
               String s = resource.get(preFix + id);
               if (!StringUtils.isEmpty(s)) {
                   System.out.println("from redis");
                   User user = JSON.parseObject(s, User.class);
                   return user;
               } else {
                   System.out.println("from db");
                   User obj = (User) joinPoint.proceed();
                   if(obj!=null) {
                       String json = JSONObject.toJSONString(obj);
                       resource.set(preFix + id, json);
                   }
                   return obj;
               }
           }
            return null;
       }finally {
            resource.close();
       }

    }

    @Around("userInsert()")
    public void processUserInsert(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("aop");
        User u = (User) joinPoint.proceed();
        Jedis resource = jedisPool.getResource();
        String s = JSONObject.toJSONString(u);
        resource.set(preFix + u.getId(), s);
        System.out.println("新增：" + s);
        resource.close();
    }

    @Around("userDelete()")
    public int processUserDelete(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("aop");
        Integer id = (Integer) joinPoint.getArgs()[0];
        joinPoint.proceed();
        Jedis resource = jedisPool.getResource();
        //   String s = JSONObject.toJSONString(u);
        resource.del(preFix + id);
        System.out.println("删除：" + id);
        resource.close();
        return id;
    }
}
