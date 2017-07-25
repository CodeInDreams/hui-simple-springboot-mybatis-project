package com.example.demo;

import com.example.demo.entity.Message;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.redis.IRedisService;
import com.example.demo.service.IUserService;
import com.example.demo.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class,webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UserServiceMockTest {

    private IUserService userService;
    @Mock
    private UserMapper userMapper;
    @Autowired
    private IRedisService<Long, User> redisUserService;
    @Autowired(required = false)
    private RedisTemplate<String, User> redisTemplate;
    private User user;

    @Before
    public void setupMock() {
        MockitoAnnotations.initMocks(this);
        userService = new UserService(userMapper, redisUserService);
        user = new User();
        user.setId(1L);
        user.setName("mock name");
        user.setPassword("mock password");
        redisTemplate.opsForHash().delete(User.class.toString(), 1L);
        // Arrange
        Mockito.doReturn(user).when(userMapper).getUserById(1L);
        Mockito.doReturn(1).when(userMapper).insertUser(user);
        Mockito.doReturn(1).when(userMapper).updateUser(1L, user);
        Mockito.doReturn(1).when(userMapper).deleteUser(1L);
        Mockito.doReturn(1).when(userMapper).insertMessage(any(Message.class));
    }

    @Test
    public void verifyCallTimes_whenGetUserByIdIsCalled() throws Exception {
        // Act
        userService.getUserById(1L);
        userService.getUserById(1L);
        // Assert
        Mockito.verify(userMapper, times(1)).getUserById(1L);
    }

    @Test
    public void verifyCallTimes_whenInsertUserIsCalled() throws Exception {
        // Act
        userService.insertUser(user);
        userService.getUserById(1L);
        // Assert
        Mockito.verify(userMapper, times(1)).insertUser(user);
        Mockito.verify(userMapper, times(1)).insertMessage(any(Message.class));
        Mockito.verify(userMapper, never()).getUserById(1L);
    }

    @Test
    public void verifyCallTimes_whenUpdateUserIsCalled() throws Exception {
        // Act
        userService.updateUser(1L, user);
        userService.getUserById(1L);
        // Assert
        Mockito.verify(userMapper, times(1)).updateUser(1L, user);
        Mockito.verify(userMapper, times(1)).insertMessage(any(Message.class));
        Mockito.verify(userMapper, never()).getUserById(1L);
    }

    @Test
    public void verifyCallTimes_whenDeleteUserIsCalled() throws Exception {
        // Act
        userService.deleteUser(1L);
        userService.getUserById(1L);
        // Assert
        Mockito.verify(userMapper, times(1)).deleteUser(1L);
        Mockito.verify(userMapper, times(1)).insertMessage(any(Message.class));
        Mockito.verify(userMapper, times(1)).getUserById(1L);
    }
}
