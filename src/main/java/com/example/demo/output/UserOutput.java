package com.example.demo.output;

import com.example.demo.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by 张彦辉 on 2017/7/18.
 */

@ApiModel
public class UserOutput {
    @ApiModelProperty(value = "User id", required = true)
    public Long id;
    @ApiModelProperty(value = "User name", required = true)
    public String name;
    @ApiModelProperty(value = "User password", required = true)
    public String password;

    UserOutput(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.password = user.getPassword();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
