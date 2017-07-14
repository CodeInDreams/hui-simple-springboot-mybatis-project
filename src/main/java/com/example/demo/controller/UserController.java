package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.input.InsertUserInput;
import com.example.demo.input.UpdateUserInput;
import com.example.demo.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 张彦辉 on 2017/7/11.
 */

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/users")
@Api(value = "Users")
public class UserController {

    @Autowired
    private IUserService userService;

    @ApiOperation(value="Get All Users")
    @RequestMapping(method=RequestMethod.GET, consumes = {"application/json"})
    @ResponseBody
    public String getUsers() {
        return JSON.toJSONString(userService.getUsers());
    }

    @ApiOperation(value="Get User", notes="Get user by id")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, consumes = {"application/json"})
    @ResponseBody
    public String getUserById(@PathVariable Long id) {
        return JSON.toJSONString(userService.getUserById(id));
    }

    @ApiOperation(value="Register User")
    @RequestMapping(method = RequestMethod.POST, consumes = {"application/json"})
    @ApiModelProperty(dataType = "com.example.demo.input.InsertUserInput")
    @ResponseBody
    public boolean insertUser(@RequestBody InsertUserInput user){
        return userService.insertUser(user.toUser());
    }

    @ApiOperation(value="Update User")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = {"application/json"})
    @ApiModelProperty(dataType = "com.example.demo.input.UpdateUserInput")
    @ResponseBody
    public boolean updateUser(@PathVariable Long id, @RequestBody UpdateUserInput user){
        return userService.updateUser(id, user.toUser());
    }

    @ApiOperation(value="Delete User")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = {"application/json"})
    @ResponseBody
    public boolean deleteUser(@PathVariable Long id){
        return userService.deleteUser(id);
    }

}