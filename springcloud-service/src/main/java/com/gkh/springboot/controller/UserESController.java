package com.gkh.springboot.controller;

import com.gkh.springboot.entity.UserES;
import com.gkh.springboot.service.UserESService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gekaihui
 * @Description
 * @date 2020/11/11
 */
@RestController
@RequestMapping("/es")
public class UserESController {
    @Autowired
    private UserESService userESService;

    @GetMapping("/create")
    public String create(
            @RequestParam("id") Long id,
            @RequestParam("userName") String userName,
            @RequestParam("userPhone") String userPhone) {
        UserES userES = new UserES();
        userES.setId(id);
        userES.setUserName(userName);
        userES.setUserPhone(userPhone);
        return userESService.save(userES);
    }


    @GetMapping("/get")
    public Iterable<UserES> get() {
        return userESService.findAll();
    }


    @GetMapping("/search")
    public Page<UserES>  search(@RequestParam("searchKey") String searchKey) {
        return userESService.search(searchKey);
    }
}
