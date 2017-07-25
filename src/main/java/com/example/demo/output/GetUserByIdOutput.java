package com.example.demo.output;

import com.example.demo.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * Created by 张彦辉 on 2017/7/18.
 */
@ApiModel
public class GetUserByIdOutput {
    @ApiModelProperty(value = "User id", required = true)
    private Long id;
    @ApiModelProperty(value = "User name", required = true)
    private String name;
    @ApiModelProperty(value = "User password", required = true)
    private String password;
    @ApiModelProperty(value = "Success or not", required = true)
    private boolean success = false;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public boolean isSuccess() {
        return success;
    }

    public GetUserByIdOutput(User user) {
        if (user != null){
            this.success = true;
            this.id = user.getId();
            this.name = user.getName();
            this.password = user.getPassword();
        }
    }
}
