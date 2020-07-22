package org.example.test;

import org.example.controller.UserController;
import org.example.mapper.UserDao;
import org.example.model.User;
import org.example.service.UserService;
import org.example.service.impl.UserServiceImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @author zjl
 * @date 2020/07/20 17:52
 */
public class Test {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext cpc=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

        UserService userService = cpc.getBean(UserService.class);
        User u=new User("Jerry",1);
      //  userService.insert(u);
       User user= userService.findById(1);

//         List<User> all = userService.findAll();
//        System.out.println(all);
//        for (User u:all){
//            System.out.println(u);
//        }
    }
}
