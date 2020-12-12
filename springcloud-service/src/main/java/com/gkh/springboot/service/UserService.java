package com.gkh.springboot.service;


import com.github.pagehelper.PageInfo;
import com.gkh.springboot.entity.User;

import java.util.List;

public interface UserService {

     User getUserById(Long id);

     List<User> getUsers();

     List<User> getUsers2();

     PageInfo<User> findAllByPage(int pageNum, int pageSize);

     int addUser(User user);

     int updUser(User user);

     int delUser(long id);
}
