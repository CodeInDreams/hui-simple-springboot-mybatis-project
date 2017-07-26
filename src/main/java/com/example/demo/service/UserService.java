package com.example.demo.service;

import com.example.demo.entity.Message;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.redis.IRedisService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 张彦辉 on 2017/7/11.
 */
@Service()
public class UserService implements IUserService {

    private static Logger logger = LogManager.getLogger(UserService.class);
    private final UserMapper userMapper;
    private final IRedisService<Long, User> redisUserService;

    @Autowired
    public UserService(UserMapper userMapper, IRedisService<Long, User> redisUserService) {
        this.userMapper = userMapper;
        this.redisUserService = redisUserService;
    }

    @Transactional
    public Long insertUser(User user) {
        try {
            userMapper.insertUser(user);
            String msg = "User created successfully with id " + user.getId() + ", name \"" + user.getName() + "\" and password \"" + user.getPassword() + "\".";
            userMapper.insertMessage(new Message(msg));
            redisUserService.put(user);
            logger.info(msg);
            return user.getId();
        } catch (Throwable e){
            logger.error("Create user failed with name \"" + user.getName() + "\" and password \"" + user.getPassword() + "\".");
            if (e instanceof ServiceException)
                throw e;
            else
                throw new ServiceException(e);
        }
    }

    public List<User> getUsers() {
        return userMapper.getUsers();
    }

    public User getUserById(Long id) {
        User user = redisUserService.get(id);
        if (user != null)
            return user;
        logger.debug("Calling getUserById(" + id + ").");
        try{
            user = userMapper.getUserById(id);
            if(user == null)
                throw new NotFoundException();
            redisUserService.put(user);
            logger.info("Got user info successfully with id " + id);
            return user;
        } catch (Throwable e){
            logger.error("Get user info failed with id " + id);
            if (e instanceof ServiceException)
                throw e;
            else
                throw new ServiceException(e);
        }
    }

    @Transactional
    public boolean updateUser(Long id, User user){
        String msg = "User " + id + " updated successfully with name \"" + user.getName() + "\" and password \"" + user.getPassword() + "\".";
        Message message = new Message(msg);
        try {
            if (userMapper.updateUser(id, user) > 0) {
                userMapper.insertMessage(message);
                user.setId(id);
                redisUserService.put(user);
            }
            else
                throw new NotFoundException();
        } catch (Throwable e){
            logger.error("Update user " + id + " failed with name \"" + user.getName() + "\" and password \"" + user.getPassword() + "\".");
            if (e instanceof ServiceException)
                throw e;
            else
                throw new ServiceException(e);
        }
        logger.info(msg);
        return true;
    }

    @CacheEvict(value = "user", key = "#id", condition = "#result")
    @Transactional
    public boolean deleteUser(Long id){
        String msg = "User " + id + " deleted successfully.";
        Message message = new Message(msg);
        try {
            if (userMapper.deleteUser(id) > 0){
                userMapper.insertMessage(message);
                redisUserService.delete(id);
            }
            else
                throw new NotFoundException();
        } catch (Throwable e){
            logger.error("Delete user " + id + " failed.");
            if (e instanceof ServiceException)
                throw e;
            else
                throw new ServiceException(e);
        }
        logger.info(msg);
        return true;
    }
}