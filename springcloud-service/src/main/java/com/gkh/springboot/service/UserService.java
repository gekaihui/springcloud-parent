package com.gkh.springboot.service;

import com.gkh.springboot.model.User;

import java.util.List;

public interface UserService {

     List<User> getUsers();

     User getUser(String id);

}
