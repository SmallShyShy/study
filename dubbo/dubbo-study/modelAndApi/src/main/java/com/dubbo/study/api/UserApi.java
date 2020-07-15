package com.dubbo.study.api;

import com.dubbo.study.model.User;

import java.util.List;

public interface UserApi {
    List<User> providerUsers();


}
