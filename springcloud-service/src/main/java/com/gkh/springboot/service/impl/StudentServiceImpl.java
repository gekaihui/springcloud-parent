package com.gkh.springboot.service.impl;

import com.gkh.springboot.entity.Student;
import com.gkh.springboot.mapper.secondary.StudentMapper;
import com.gkh.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentMapper studentMapper;

    @Override
    public Student selectByPrimaryKey(int id) {
        return studentMapper.selectByPrimaryKey(id);
    }
}
