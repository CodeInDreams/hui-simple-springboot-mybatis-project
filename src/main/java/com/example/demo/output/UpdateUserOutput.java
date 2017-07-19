package com.example.demo.output;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by 张彦辉 on 2017/7/18.
 */
@ApiModel
public class UpdateUserOutput {
    @ApiModelProperty(value = "Success or not", required = true)
    public boolean success = true;

    public UpdateUserOutput(boolean success) {
        this.success = success;
    }
}
