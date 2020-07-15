package com.dubbo.study.service.Impl;

import com.dubbo.study.api.UserApi;
import com.dubbo.study.model.User;
import com.dubbo.study.service.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zjl
 * @date 2020/07/15 13:42
 */
@Service
public class UserServiceImpl implements UserService {
    @Reference
    UserApi userApi;

    @Override
    public List<User> getUsers() {
        return userApi.providerUsers();
    }
}
