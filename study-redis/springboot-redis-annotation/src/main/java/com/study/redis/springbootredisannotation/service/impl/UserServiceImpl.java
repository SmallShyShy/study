package com.study.redis.springbootredisannotation.service.impl;

import com.study.redis.springbootredisannotation.mapper.UserDao;
import com.study.redis.springbootredisannotation.model.User;
import com.study.redis.springbootredisannotation.service.UserService;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zjl
 * @date 2020/07/20 17:07
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;


    @Override
    public List<User> findAll() {
        return userDao.selectAll();
    }

    @Override
    @Cacheable(cacheNames = "user",key = "#id") //参数相同就往缓存中去取
    public User findById(int id) {
        return userDao.selectByPrimaryKey(id);
    }

    @Override
   // @CachePut(cacheNames = "user",key="#result.id") //不检查缓存 每次执行将值放入缓存中
    @CachePut(cacheNames = "user",key="#result.id+'-'+#result.name")
    public User insert(User user) {
        userDao.insert(user);
        return user;

    }

    @Override
    @CacheEvict(cacheNames = "user",key="#id")
    public int deleteById(int id) {
        return userDao.deleteByPrimaryKey(id);
    }

    @Override
    @CachePut(cacheNames = "user",key = "#user.id")
    public User updateUser(User user) {
        userDao.updateByPrimaryKey(user);
        return user;
    }
}
