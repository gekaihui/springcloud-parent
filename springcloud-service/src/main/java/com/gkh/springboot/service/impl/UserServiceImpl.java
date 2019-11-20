package com.gkh.springboot.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gkh.springboot.entity.User;
import com.gkh.springboot.mapper.primary.UserMapper;
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
    public User getUserById(Long id) {
        return this.userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> getUsers() {
        return this.userMapper.selectUsers();
    }

    @Override
    public List<User> getUsers2() {
        return this.userMapper.selectUsers2();
    }

    @Override
    public int addUser(User user) {
        return this.userMapper.insertSelective(user);
    }

    @Override
    public int updUser(User user) {
        return this.userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int delUser(long id) {
        return this.userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public PageInfo<User> findAllByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> list = userMapper.selectUsers();
        PageInfo<User> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
