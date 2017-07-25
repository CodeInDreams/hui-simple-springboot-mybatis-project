package com.example.demo.output;

import com.example.demo.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by 张彦辉 on 2017/7/18.
 */
@ApiModel
public class InsertUserOutput {
    @ApiModelProperty(value = "Success or not", required = true)
    private Long id;
    private boolean success = false;

    public Long getId() {
        return id;
    }

    public boolean isSuccess() {
        return success;
    }

    public InsertUserOutput(Long id) {
        if(id != null){
            this.success = true;
            this.id = id;
        }
    }
}
