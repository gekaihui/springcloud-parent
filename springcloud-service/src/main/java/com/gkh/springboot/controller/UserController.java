package com.gkh.springboot.controller;

import com.gkh.springboot.model.User;
import com.gkh.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/hello")
    public String hello(Model model){
        model.addAttribute("name","Dear");
        return "index";
    }

    @RequestMapping(value = "/getUsers")
    @ResponseBody
    public List<User> getUsers(){
        return this.userService.getUsers();
    }

    @RequestMapping(value = "/getUser")
    @ResponseBody
    public User getUser(String id){
        return this.userService.getUser(id);
    }
}
