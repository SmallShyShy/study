package com.study.redis.springbootredisannotation.service;




import com.study.redis.springbootredisannotation.model.User;

import java.util.List;

public interface UserService{
    public List<User> findAll();
    User findById(int id);
    User insert(User user);
    int deleteById(int id);
    User updateUser(User user);

}
