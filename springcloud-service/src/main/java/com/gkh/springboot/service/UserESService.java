package com.gkh.springboot.service;

import com.gkh.springboot.entity.UserES;
import org.springframework.data.domain.Page;

/**
 * @author gekaihui
 * @Description
 * @date 2020/11/11
 */
public interface UserESService {
    String save(UserES userES);

    Iterable<UserES> findAll();

    Page<UserES> search(String searchKey);
}
