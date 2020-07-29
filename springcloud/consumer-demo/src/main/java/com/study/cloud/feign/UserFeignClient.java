package com.study.cloud.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author zjl
 * @date 2020/07/16 15:06
 */
//@FeignClient(url = "${userServer.url}",name="userFeign") //不通过eureka 直接访问其他服务接口
@FeignClient(value="user-server",fallback = UserFeignClient.DefaultFallback.class) //user-server 是eureka中的服务
public interface UserFeignClient {

    @GetMapping("/getUser/{id}")
    public String getUserByFeign(@PathVariable Integer id);

    @Component
    class DefaultFallback implements UserFeignClient{

        @Override
        public String getUserByFeign(Integer id) {
            return "粗错啦！！"+id;
        }
    }
}
