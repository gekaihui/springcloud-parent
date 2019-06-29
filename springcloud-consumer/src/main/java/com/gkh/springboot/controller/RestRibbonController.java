package com.gkh.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 客户端的均衡负载  restTemplate+ ribbon
 */
@RestController
@RequestMapping
public class RestRibbonController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/rest")
    public String rest(@RequestParam(value = "name") String  name){
        String url = "http://service/hello/name?name="+name;
        String s = restTemplate.getForObject(url, String.class);
        return s;
    }
}
