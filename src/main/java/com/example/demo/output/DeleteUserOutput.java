package com.example.demo.output;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by 张彦辉 on 2017/7/18.
 */
@ApiModel
public class DeleteUserOutput {
    @ApiModelProperty(value = "Success or not", required = true)
    private boolean success = false;

    public boolean isSuccess() {
        return success;
    }

    public DeleteUserOutput(boolean success) {
        this.success = success;
    }
}
