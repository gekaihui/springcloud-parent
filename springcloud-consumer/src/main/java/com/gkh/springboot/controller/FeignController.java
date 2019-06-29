package com.gkh.springboot.controller;

import com.gkh.springboot.service.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignController {

    @Autowired
    FeignService feignService;

    @RequestMapping(value = "hi")
    public String sayHi(@RequestParam(value = "name") String name){
        return feignService.hello(name);
    }


}
