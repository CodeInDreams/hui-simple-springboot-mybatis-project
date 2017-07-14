package com.example.demo.input;

import com.example.demo.entity.User;

/**
 * Created by 张彦辉 on 2017/7/14.
 * 用于controller
 */
public class UpdateUserInput {
    private String name;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User toUser(){
        User user = new User();
        user.setName(this.name);
        user.setPassword(this.password);
        return user;
    }
}
