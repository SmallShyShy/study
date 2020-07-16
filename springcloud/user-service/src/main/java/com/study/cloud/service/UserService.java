package com.study.cloud.service;

import com.study.cloud.mapper.UserMapper;
import com.study.cloud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zjl
 * @date 2020/07/15 18:55
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User findUserById(int id){
        return userMapper.selectByPrimaryKey(id);
    }


}
