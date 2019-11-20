package com.gkh.springboot.controller;

import com.gkh.springboot.entity.Student;
import com.gkh.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/student")
public class StudentController {

    @Autowired
    private  StudentService studentService;

    @GetMapping(value = "/getStudent/{id}")
    @ResponseBody
    public Student getStudent(@PathVariable int id){
        return studentService.selectByPrimaryKey(id);
    }
}
