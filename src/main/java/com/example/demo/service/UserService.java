package com.example.demo.service;

import com.example.demo.entity.Message;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

/**
 * Created by 张彦辉 on 2017/7/11.
 */
@Service()
public class UserService implements IUserService {

    private static Logger logger = LogManager.getLogger(UserService.class);
    @Autowired private UserMapper userMapper;

    @Transactional
    public boolean insertUser(User user) {
        String msg = "User created successfully with name " + user.getName() + " and password " + user.getPassword() + ".";
        Message message = new Message(msg);
        try {
            userMapper.insertUser(user);
            userMapper.insertMessage(message);
        } catch (Throwable e){
            logger.info("Create user failed with name " + user.getName() + " and password " + user.getPassword() + ".");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
        logger.info(msg);
        return true;
    }

    public List<User> getUsers() {
        return userMapper.getUsers();
    }

    public User getUserById(Long id) {
        User user = userMapper.getUserById(id);
        if(user == null)
            throw new NotFoundException();
        return user;
    }

    @Transactional
    public boolean updateUser(Long id, User user){
        String msg = "User " + id + " updated successfully with name " + user.getName() + " and password " + user.getPassword() + ".";
        Message message = new Message(msg);
        try {
            if (userMapper.updateUser(id, user) > 0)
                userMapper.insertMessage(message);
            else
                throw new NotFoundException();
        } catch (Throwable e){
            logger.info("Update user " + id + " failed with name " + user.getName() + " and password " + user.getPassword() + ".");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            if (e instanceof NotFoundException)
                throw e;
            return false;
        }
        logger.info(msg);
        return true;
    }

    @Transactional
    public boolean deleteUser(Long id){
        String msg = "User " + id + " deleted successfully.";
        Message message = new Message(msg);
        try {
            if (userMapper.deleteUser(id) > 0)
                userMapper.insertMessage(message);
            else
                throw new NotFoundException();
        } catch (Throwable e){
            logger.info("Delete user " + id + " failed.");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            if (e instanceof NotFoundException)
                throw e;
            return false;
        }
        logger.info(msg);
        return true;
    }
}