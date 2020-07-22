package com.study.redis.springbootredisannotation.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * t_user
 * @author 
 */
@Data
@NoArgsConstructor
public class User implements Serializable {
    private Integer id;

    private String name;

    private Integer age;

    private static final long serialVersionUID = 1L;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}