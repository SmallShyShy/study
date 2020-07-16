package com.study.cloud.controller;

import com.study.cloud.model.User;
import com.study.cloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zjl
 * @date 2020/07/15 18:59
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getUser/{id}")
    public User getUserById(@PathVariable Integer id){
       return userService.findUserById(id);
    }

}
