package com.example.demo.input;

import com.example.demo.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by 张彦辉 on 2017/7/14.
 * 用于controller
 */
@ApiModel
public class UpdateUserInput {
    @ApiModelProperty(value = "User name", required = true)
    private String name;
    @ApiModelProperty(value = "User password", required = true)
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
