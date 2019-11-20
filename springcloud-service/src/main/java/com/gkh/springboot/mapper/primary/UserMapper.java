package com.gkh.springboot.mapper.primary;

import com.gkh.springboot.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;


@Mapper //注意接口上的注解是@mapper,代替之前扫描接口的操作
@Component(value="userMapper")
public interface UserMapper {
    int deleteByPrimaryKey(long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> selectUsers2();

    //通过注解自定义sql语句
    @Select("select * from gkh_user")
    List<User> selectUsers();
}