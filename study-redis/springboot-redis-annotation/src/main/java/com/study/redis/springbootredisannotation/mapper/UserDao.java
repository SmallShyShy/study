package com.study.redis.springbootredisannotation.mapper;

import com.study.redis.springbootredisannotation.model.User;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;
@Mapper  //使用此注解 可以不配置mapperscan
public interface UserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> selectAll();
}