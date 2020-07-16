package com.study.cloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import com.study.cloud.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author zjl
 * @date 2020/07/15 19:15
 */
@RestController
@RequestMapping("/consumer")
@Slf4j
@DefaultProperties(defaultFallback = "defaultFallback")
public class ConsumerController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;
    @GetMapping("getUser/{id}")
  //  @HystrixCommand(fallbackMethod = "queryByIdFallback") //针对单个方法
    @HystrixCommand
    public String queryById(@PathVariable Integer id){
      //  List<ServiceInstance> instances = discoveryClient.getInstances("user-server");
      //  ServiceInstance serviceInstance = instances.get(0);
       // String url="http://localhost:9091/getUser/"+id;
       // String url="http://"+serviceInstance.getHost()+":"+serviceInstance.getPort()+"/getUser/"+id;

        //多次失败后整个服务降级 id不是1的也会走defaultFallback
        //过一定时间以后id不是1的会恢复 时间可配置
        if(id==1){
            throw new RuntimeException("busy");
        }

        String url="http://user-server/getUser/"+id;
        return restTemplate.getForObject(url,String.class);
    }

    public String queryByIdFallback(Integer id){
        log.error("查询失败");
        return "ops,失败";
    }
    public String defaultFallback(){
        return "default fallback";
    }

}
