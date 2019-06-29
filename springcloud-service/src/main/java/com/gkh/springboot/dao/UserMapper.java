package com.gkh.springboot.dao;

import com.gkh.springboot.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper //注意接口上的注解是@mapper,代替之前扫描接口的操作
@Component(value="userMapper")
public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    //通过注解自定义sql语句
    @Select("select * from USER_TEST a")
    List<User> selectUsers();
}