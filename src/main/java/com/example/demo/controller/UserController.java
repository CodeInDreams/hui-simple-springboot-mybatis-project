package com.example.demo.controller;

import com.example.demo.annotation.BusinessInfo;
import com.example.demo.input.InsertUserInput;
import com.example.demo.input.UpdateUserInput;
import com.example.demo.output.*;
import com.example.demo.service.IUserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 张彦辉 on 2017/7/11.
 */

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/user")
@Api(value = "User")
public class UserController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value="Get All Users")
    @RequestMapping(method=RequestMethod.GET)
    @ResponseBody
    public GetUsersOutput getUsers() {
        return new GetUsersOutput(userService.getUsers());
    }

    @BusinessInfo(name = "Get user", description = "Get user by id")
    @ApiOperation(value="Get User", notes="Get user by id")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public GetUserByIdOutput getUserById(@ApiParam(value = "User id", required = true) @PathVariable Long id) {
        return new GetUserByIdOutput(userService.getUserById(id));
    }

    @BusinessInfo(name = "Register user")
    @ApiOperation(value="Register User")
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public InsertUserOutput insertUser(@ApiParam(value = "User info", required = true) @RequestBody InsertUserInput user){
        return new InsertUserOutput(userService.insertUser(user.toUser()));
    }

    @BusinessInfo(name = "Update user")
    @ApiOperation(value="Update User")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public UpdateUserOutput updateUser(@ApiParam(value = "User id", required = true) @PathVariable Long id, @ApiParam(value = "User info", required = true) @RequestBody UpdateUserInput user){
        return new UpdateUserOutput(userService.updateUser(id, user.toUser()));
    }

    @BusinessInfo(name = "Delete user")
    @ApiOperation(value="Delete User")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public DeleteUserOutput deleteUser(@ApiParam(value = "User id", required = true) @PathVariable Long id){
        return new DeleteUserOutput(userService.deleteUser(id));
    }

}