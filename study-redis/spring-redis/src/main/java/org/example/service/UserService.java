package org.example.service;


import org.example.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService{
    public List<User> findAll();
    User findById(int id);
    User insert(User user);
    int deleteById(int id);
    boolean updateUser(User user);

}
