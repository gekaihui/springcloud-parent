package com.gkh.springboot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "hello")
public class HelloController {

    @Value("${server.port}")
    private String port;


    @RequestMapping(value = "/name")
    public String helloName(@RequestParam(value="name") String name){
        return "hello" + name + "我是服务器1  port:" + port;
    }
}
