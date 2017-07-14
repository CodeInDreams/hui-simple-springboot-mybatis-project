package com.example.demo.mapper;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by 张彦辉 on 2017/7/11.
 */

@Mapper
@Component
public interface UserMapper {
    @Select("select * from user where id = #{id}")
    User getUserById(@Param("id") Long id);
    @Select("select * from user")
    List<User> getUsers();
    @Insert("insert into user (name, password) values (#{user.getName()}, #{user.getPassword()})")
    int insertUser (@Param("user") User user);
    @Delete("delete from user where id = #{id}")
    int deleteUser (@Param("id") Long id);
    @Update("update user set name = #{user.getName()}, password = #{user.getPassword()} where id = #{id}")
    int updateUser(@Param("id") Long id, @Param("user") User user);
}
