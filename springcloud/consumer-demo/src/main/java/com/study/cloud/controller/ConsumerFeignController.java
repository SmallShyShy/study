package com.study.cloud.controller;

import com.study.cloud.feign.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zjl
 * @date 2020/07/16 15:12
 */
@RestController
@RequestMapping("/cf")
public class ConsumerFeignController {
    @Autowired
    private UserFeignClient feignClient;

    @GetMapping("/getUserByFeign/{id}")
    public String getUserByFeign(@PathVariable Integer id){
        return feignClient.getUserByFeign(id);
    }
}
