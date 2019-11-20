package com.gkh.springboot.service;

import com.gkh.springboot.entity.User;

public interface CacheService {

    User selectById(String id);

    int updateUser(User user);

    int deleteById(String id);
}
