package org.example.service.impl;

import org.example.mapper.UserDao;
import org.example.model.User;
import org.example.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zjl
 * @date 2020/07/20 17:07
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;


    @Override
    public List<User> findAll() {
        return userDao.selectAll();
    }

    @Override
    public User findById(int id) {
        return userDao.selectByPrimaryKey(id);
    }

    @Override
    public User insert(User user) {
        userDao.insert(user);
        return user;

    }

    @Override
    public int deleteById(int id) {
        return userDao.deleteByPrimaryKey(id);
    }

    @Override
    public boolean updateUser(User user) {
        return userDao.updateByPrimaryKey(user)>0;
    }
}
