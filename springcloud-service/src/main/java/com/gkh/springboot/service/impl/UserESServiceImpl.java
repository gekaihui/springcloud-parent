package com.gkh.springboot.service.impl;

import com.gkh.springboot.entity.UserES;
import com.gkh.springboot.mapper.elasticsearch.UserESRepository;
import com.gkh.springboot.service.UserESService;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

/**
 * @author gekaihui
 * @Description
 * @date 2020/11/11
 */
@Service
public class UserESServiceImpl implements UserESService {

    @Autowired
    UserESRepository userESRepository;

    @Override
    public String save(UserES userES) {
        return userESRepository.save(userES).toString();
    }

    @Override
    public Iterable<UserES> findAll() {
        Iterable<UserES> userESList = userESRepository.findAll();
        return userESList;
    }

    @Override
    public Page<UserES> search(String searchKey) {
        // 构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 添加基本分词查询
        queryBuilder.withQuery(QueryBuilders.matchQuery("userName", searchKey));
        // 搜索，获取结果
        Page<UserES> items = userESRepository.search(queryBuilder.build());
        return items;
    }


}
