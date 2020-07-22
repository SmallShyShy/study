package com.study.redis.springbootredisannotation.controller;

import com.study.redis.springbootredisannotation.model.User;
import com.study.redis.springbootredisannotation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zjl
 * @date 2020/07/20 17:17
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("get")
    public User getUserById(int id){
        return userService.findById(id);
    }
    @RequestMapping("delete")
    public int deleteUserById(int id){
        return userService.deleteById(id);
    }
    @RequestMapping("update")
    public User updateUser(String name,int id){
        User u=new User();
        u.setAge(11);
        u.setId(id);
        u.setName(name);
        return userService.updateUser(u);
    }
    @RequestMapping("add")
    public User add(String name,int age){
        User u=new User(name,age);
        return userService.insert(u);
    }

}
