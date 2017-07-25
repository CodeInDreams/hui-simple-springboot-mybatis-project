package com.example.demo.service;

import com.example.demo.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by 张彦辉 on 2017/7/11.
 */
@Component
public interface IUserService {
    Long insertUser(User user);
    List<User> getUsers();
    User getUserById(Long id);
    boolean updateUser(Long id, User user);
    boolean deleteUser(Long id);
}