package com.gkh.springboot.controller;

import com.gkh.springboot.config.redis.RedisUtil;
import com.gkh.springboot.entity.User;
import com.gkh.springboot.service.impl.CacheServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/cache")
public class CacheController {

    @Autowired
    private CacheServiceImpl cacheService;

    @Autowired
    private RedisUtil redisUtil;

    @GetMapping(value = "/getUser")
    @ResponseBody
    public User selectUserById(@RequestParam("id") String id){
        return cacheService.selectById(id);
    }

    @PutMapping(value = "/updateUser")
    @ResponseBody
    public int updateUser(@RequestBody User user){
        return cacheService.updateUser(user);
    }

    @DeleteMapping(value = "/deleteUser")
    @ResponseBody
    public int deleteUserById(@RequestParam("id") String id){
        return cacheService.deleteById(id);
    }

    /**
     * redis添加
     * @param key
     * @param value
     */
    @GetMapping(value = "setKeyValue")
    @ResponseBody
    public void setValue(@RequestParam("key") String key, @RequestParam("value") String value){
        redisUtil.set(key, value);
    }
    /**
     * redis查询
     * @param key
     */
    @GetMapping(value = "/getValueByKey")
    @ResponseBody
    public String getValueByKey(@RequestParam("key") String key){
         return  String.valueOf(redisUtil.get(key));
    }

    /**
     * redis删除
     * @param key
     */
    @GetMapping(value = "/deleteByKey")
    @ResponseBody
    public void deleteByKey(@RequestParam("key") String key){
        redisUtil.del(key);
    }
}
