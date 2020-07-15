package com.dubbo.study.controller;

import com.dubbo.study.model.User;
import com.dubbo.study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author zjl
 * @date 2020/07/15 13:48
 */
@Controller
public class CustomerController {
    @Autowired
    private UserService userService;
    @RequestMapping("/test")
    @ResponseBody
    public List<User> getUser(){
        return userService.getUsers();
    }
}
