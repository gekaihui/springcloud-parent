package com.gkh.springboot.controller;


import com.github.pagehelper.PageInfo;
import com.gkh.springboot.entity.User;
import com.gkh.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** spring常用注解
 *  1、@Controller
 * @Controller 用来响应页面，表示当前的类为控制器。
 * 2、@RestController
 * @RestController 是@ResponseBody和@Controller的结合
 * 表明当前类是控制器且返回的是一组数据，不是页面
 * 3、@Autowired
 * 这个注解的作用是将其他的类，接口引入，类似于之前的类的初始化等，用这个注解，类中或接口的方法就可以直接调用了。
 * 4、@RequestMapping
 * 当前台界面调用Controller处理数据时候告诉控制器怎么操作
 * 作用：URL映射。
 * 5、@GetMapping
 * @RequestMapping(method = RequestMethod.GET)的简写
 * 作用：对应查询，表明是一个查询URL映射
 * 6、@PostMapping
 * @RequestMapping(method = RequestMethod.POST)的简写
 * 作用：对应增加，表明是一个增加URL映射
 * 7、@PutMapping
 * @RequestMapping(method = RequestMethod.PUT)的简写
 * 作用：对应更新，表明是一个更新URL映射
 * 8、@DeleteMapping
 * @RequestMapping(method = RequestMethod.DELETE)的简写
 * 作用：对应删除，表明是一个删除URL映射

 *  Restful接口规范
    RESTFul风格的设定是基于数据的元操作，即CRUD(create, read, update和delete,即数据的增删查改)
    GET（SELECT）：从服务器取出资源（一项或多项）。
    POST（CREATE）：在服务器新建一个资源。
    PUT（UPDATE）：在服务器更新资源（客户端提供完整资源数据）。
    PATCH（UPDATE）：在服务器更新资源（客户端提供需要修改的资源数据）。
    DELETE（DELETE）：从服务器删除资源。
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 通过thymeleaf访问页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/hello")
    public String hello(Model model){
        model.addAttribute("name","Dear");
        return "index";
    }

    /**
     * 通过主键id查询
     * @param id
     * @return
     */
    @GetMapping(value = "/getUser")
    @ResponseBody
    public User getUserById(@RequestParam("id") Long id){
        return this.userService.getUserById(id);
    }

    /**
     * 查询所有数据(dao接口层通过动态sql查询)
     * @return
     */
    @GetMapping(value = "/getUsers")
    @ResponseBody
    public List<User> getUsers(){
        return this.userService.getUsers();
    }

    /**
     * 查询所有数据(dao接口层通过xml配置查询)
     * @return
     */
    @GetMapping(value = "/getUsers2")
    @ResponseBody
    public List<User> getUsers2(){
        return this.userService.getUsers2();
    }

    /***
     * 分页查询
     * @Author: gekaihui
     * @Date: 2020/6/30
     */
    @RequestMapping(value = "/findByPage")
    @ResponseBody
    public PageInfo<User> findAllByPage(@RequestParam(value = "pageNum") String pageNum, @RequestParam(value = "pageSize") String pageSize) {
        return this.userService.findAllByPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
    }
    /**
     * 新增
     * @param user
     * @return
     */
    @PostMapping(value = "/addUser")
    @ResponseBody
    public int addUser(@RequestBody User user){
        return this.userService.addUser(user);
    }

    /**
     * 修改
     * @param user
     * @return
     */
    @PutMapping(value = "/updUser")
    @ResponseBody
    public int updUser(@RequestBody User user){
        return this.userService.updUser(user);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping(value = "/delUser/{id}")
    @ResponseBody
    public int delUser(@PathVariable String id){
        return this.userService.delUser(Long.valueOf(id));
    }
}
