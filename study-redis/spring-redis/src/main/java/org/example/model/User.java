package org.example.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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