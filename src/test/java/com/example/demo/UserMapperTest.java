package com.example.demo;

import com.example.demo.entity.Message;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class,webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Transactional
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;
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
    private User newUser(Long id, String name, String password){
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setPassword(password);
        return user;
    }

    @Test
    public void getUserById() {
        User user = userMapper.getUserById(8L);
        assertThat(user.getId()).isEqualTo(8L);
        assertThat(user.getName()).isEqualTo("name8");
        assertThat(user.getPassword()).isEqualTo("password8");
    }

    @Test
    public void getUserById_unknownId() {
        User user = userMapper.getUserById(0L);
        Assert.assertNull(user);
    }

    @Test
    public void getUsers() {
        List<User> users = userMapper.getUsers();
        List<User> correctUsers;
        correctUsers = new ArrayList<>();
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
    public void insertUser() {
        User user = new User();
        user.setName("test name");
        user.setPassword("test password");
        Assert.assertTrue(userMapper.insertUser(user) == 1);
    }

    @Test
    public void insertUser_invalidName() {
        User user = new User();
        user.setName(null);
        user.setPassword("test password");
        exception.expect(DataIntegrityViolationException.class);
        userMapper.insertUser(user);
    }

    @Test
    public void insertUser_invalidPassword() {
        User user = new User();
        user.setName("test name");
        user.setPassword(null);
        exception.expect(DataIntegrityViolationException.class);
        userMapper.insertUser(user);
    }

    @Test
    public void updateUser() {
        User user = new User();
        user.setName("test name after update");
        user.setPassword("test password after update");
        Assert.assertTrue(userMapper.updateUser(7L, user) == 1);
    }

    @Test
    public void updateUser_unknownId() {
        User user = new User();
        user.setName("test name after update");
        user.setPassword("test password after update");
        Assert.assertTrue(userMapper.updateUser(0L, user) == 0);
    }

    @Test
    public void updateUser_invalidName() {
        User user = new User();
        user.setName(null);
        user.setPassword("test password after update");
        exception.expect(DataIntegrityViolationException.class);
        userMapper.updateUser(7L, user);
    }

    @Test
    public void updateUser_invalidPassword() {
        User user = new User();
        user.setName("test name after update");
        user.setPassword(null);
        exception.expect(DataIntegrityViolationException.class);
        userMapper.updateUser(7L, user);
    }

    @Test
    public void deleteUser() {
        Assert.assertTrue(userMapper.deleteUser(6L) == 1);
    }

    @Test
    public void deleteUser_unknownId() {
        Assert.assertTrue(userMapper.deleteUser(0L) == 0);
    }

    @Test
    public void insertMessage() {
        Message message = new Message("test message");
        Assert.assertTrue(userMapper.insertMessage(message) == 1);
    }

    @Test
    public void insertMessage_invalidMsg() {
        Message message = new Message(null);
        exception.expect(DataIntegrityViolationException.class);
        userMapper.insertMessage(message);
    }
}
