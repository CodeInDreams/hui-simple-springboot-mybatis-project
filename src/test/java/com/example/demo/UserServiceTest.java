package com.example.demo;

import com.example.demo.entity.User;
import com.example.demo.service.IUserService;
import com.example.demo.service.NotFoundException;
import com.example.demo.service.ServiceException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class,webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Transactional
public class UserServiceTest {
    @Autowired
    public IUserService userService;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    /*
    drop table if exists user;
    CREATE TABLE `user` (
        `id` bigint(16) AUTO_INCREMENT NOT NULL,
        `name` varchar(255) NOT NULL,
        `password` varchar(255) NOT NULL,
        PRIMARY KEY (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
    insert into user values
            (1, "name1", "password1"),
            (2, "name2", "password2"),
            (3, "name3", "password3"),
            (4, "name4", "password4"),
            (5, "name5", "password5"),
            (6, "name6", "password6"),
            (7, "name7", "password7"),
            (8, "name8", "password8"),
            (9, "name9", "password9");
    */

    @Test
    public void getUsers(){
        assertThat(userService.getUsers().size()).isEqualTo(9);
    }

    @Test
    public void getUserById(){
        User user = userService.getUserById(3L);
        assertThat(user.getName()).isEqualTo("name3");
    }

    @Test(expected = NotFoundException.class)
    public void getUserById_unknownId(){
        userService.getUserById(0L);
    }

    @Test
    public void updateUser(){
        User user = new User();
        user.setName("name after update");
        user.setPassword("password after update");
        Assert.assertTrue(userService.updateUser(2L, user));
    }

    @Test(expected = NotFoundException.class)
    public void updateUser_unknownId(){
        User user = new User();
        user.setName("name after update");
        user.setPassword("password after update");
        userService.updateUser(0L, user);
    }

    @Test(expected = ServiceException.class)
    public void updateUser_invalidName(){
        User user = new User();
        user.setPassword("password after update");
        userService.updateUser(2L, user);
    }

    @Test(expected = ServiceException.class)
    public void updateUser_invalidPassword(){
        User user = new User();
        user.setName("name after update");
        userService.updateUser(2L, user);
    }

    @Test
    public void deleteUser(){
        Assert.assertTrue(userService.deleteUser(1L));
    }

    @Test(expected = NotFoundException.class)
    public void deleteUser_knownId(){
        userService.deleteUser(0L);
    }

    @Test
    public void insertUser(){
        User user = new User();
        user.setName("new user name");
        user.setPassword("new user password");
        Assert.assertTrue(userService.insertUser(user) > 0);
    }

    @Test(expected = ServiceException.class)
    public void insertUser_invalidName(){
        User user = new User();
        user.setPassword("new user password");
        userService.insertUser(user);
    }

    @Test(expected = ServiceException.class)
    public void insertUser_invalidPassword(){
        User user = new User();
        user.setName("new user name");
        userService.insertUser(user);
    }
}
