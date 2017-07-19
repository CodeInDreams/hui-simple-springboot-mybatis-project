package com.example.demo;

import com.example.demo.entity.Message;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Test3Application.class,webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Transactional
public class MapperTests {

    @Autowired
    private UserMapper userMapper;

    /*
    drop table if exists user;
    CREATE TABLE `user` (
        `id` bigint(16) AUTO_INCREMENT NOT NULL,
        `name` varchar(255) DEFAULT NULL,
        `password` varchar(255) DEFAULT NULL,
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
    private User newUser(Long id, String name, String password){
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setPassword(password);
        return user;
    }

    @Test
    public void getUserByIdTest() {
        User user = userMapper.getUserById(8L);
        assertThat(user.getId()).isEqualTo(8L);
        assertThat(user.getName()).isEqualTo("name8");
        assertThat(user.getPassword()).isEqualTo("password8");
    }

    @Test
    public void getUsersTest() {
        List<User> users = userMapper.getUsers();
        List<User> correctUsers = new ArrayList<>();
        users.add(newUser(1L, "name1", "password1"));
        users.add(newUser(2L, "name2", "password2"));
        users.add(newUser(3L, "name3", "password3"));
        users.add(newUser(4L, "name4", "password4"));
        users.add(newUser(5L, "name5", "password5"));
        users.add(newUser(6L, "name6", "password6"));
        users.add(newUser(7L, "name7", "password7"));
        users.add(newUser(8L, "name8", "password8"));
        users.add(newUser(9L, "name9", "password9"));
        int n = correctUsers.size();
        for (int i = 0; i < n; ++i){
            assertThat(users.get(i).getId()).isEqualTo(correctUsers.get(i).getId());
            assertThat(users.get(i).getName()).isEqualTo(correctUsers.get(i).getName());
            assertThat(users.get(i).getPassword()).isEqualTo(correctUsers.get(i).getPassword());
        }
    }

    @Test
    public void insertUserTest() {
        User user = new User();
        user.setName("test name");
        user.setPassword("test password");
        Assert.assertTrue(userMapper.insertUser(user) == 1);
    }

    @Test
    public void updateUserTest() {
        User user = new User();
        user.setName("test name after update");
        user.setPassword("test password after update");
        Assert.assertTrue(userMapper.updateUser(9L, user) == 1);
    }

    @Test
    public void deleteUserTest() {
        Assert.assertTrue(userMapper.deleteUser(7L) == 1);
    }

    @Test
    public void insertMessageTest() {
        Message message = new Message("test message");
        Assert.assertTrue(userMapper.insertMessage(message) == 1);
    }
}
