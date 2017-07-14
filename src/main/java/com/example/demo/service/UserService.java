package com.example.demo.service;

import com.example.demo.Test3Application;
import com.example.demo.entity.User;
import com.example.demo.input.InsertUserInput;
import com.example.demo.mapper.UserMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 张彦辉 on 2017/7/11.
 */
@Service()
public class UserService implements IUserService {

    private static Logger logger = LogManager.getLogger(Test3Application.class);
    @Autowired private UserMapper userMapper;

    public boolean insertUser(User user) {
        int result = -1;
        try {
            result = userMapper.insertUser(user);
        } finally {
            logger.info("Insert user " + (result > 0 ? "succeed" : "failed") + " with name " + user.getName()
                    + " and password " + user.getPassword() + ".");
            return result > 0;
        }
    }

    public List<User> getUsers() {
        return userMapper.getUsers();
    }

    public User getUserById(Long id) {
        User user = userMapper.getUserById(id);
        if(user == null)
            throw new com.example.demo.service.NotFoundException();
        return user;
    }

    public boolean updateUser(Long id, User user){
        int result = -1;
        try {
            result = userMapper.updateUser(id, user);
            if (result <= 0)
                throw new com.example.demo.service.NotFoundException();
        } finally {
            logger.info("Update user " + id + (result > 0 ? "succeed" : "failed") + " with name " + user.getName()
                    + " and password " + user.getPassword() + ".");
            return result > 0;
        }
    }

    public boolean deleteUser(Long id){
        int result = -1;
        try {
            result = userMapper.deleteUser(id);
            if (result <= 0)
                throw new com.example.demo.service.NotFoundException();
        } finally {
            logger.info("Delete user " + id + (result > 0 ? "succeed" : "failed") + ".");
            return result > 0;
        }
    }
}