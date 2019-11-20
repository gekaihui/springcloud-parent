package com.gkh.springboot.service;

import com.gkh.springboot.entity.Student;

public interface StudentService {
    Student selectByPrimaryKey(int id);
}
