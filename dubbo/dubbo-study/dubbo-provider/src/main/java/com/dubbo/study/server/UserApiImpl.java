package com.dubbo.study.server;

import com.dubbo.study.api.UserApi;
import com.dubbo.study.model.User;
import org.apache.dubbo.config.annotation.Service;


import java.util.Arrays;
import java.util.List;

/**
 * @author zjl
 * @date 2020/07/14 16:38
 */
@Service
public class UserApiImpl implements UserApi {
    private static List<User> list= Arrays.asList(new User("Tom1",11),new User("Jerry1",21));
    @Override
    public List<User> providerUsers() {
        return list;
    }
}
