package com.study.cloud.controller;

import com.study.cloud.model.User;
import com.study.cloud.service.UserService;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zjl
 * @date 2020/07/15 18:59
 */
@RestController
@RequestMapping("/user")
@RefreshScope //刷新git配置
public class UserController {
    @Autowired
    private UserService userService;
    @Value("${server.port}")
    private String port;
    @Value("${test.name}")
    private String name;

    @GetMapping("/getUser/{id}")
    public User getUserById(@PathVariable Integer id){
      //  System.out.println(port);
        User user=userService.findUserById(id);
        user.setName(name);
        user.setAge(Integer.parseInt(port));
       return user;
    }

}
