package com.gkh.springboot.service;

import com.gkh.springboot.dao.UserMapper;
import com.gkh.springboot.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    /**
     * 日志管理
     */
    private static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    /**
     * 用户数据访问接口
     */
    @Autowired
    private UserMapper userMapper;



    @Override
    public List<User> getUsers() {
        return this.userMapper.selectUsers();
    }

    @Override
    public User getUser(String id) {
        return this.userMapper.selectByPrimaryKey(id);
    }
}
