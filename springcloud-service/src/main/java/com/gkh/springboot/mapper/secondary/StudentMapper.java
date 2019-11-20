package com.gkh.springboot.mapper.secondary;

import com.gkh.springboot.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper //注意接口上的注解是@mapper,代替之前扫描接口的操作
@Component(value="studentMapper")
public interface StudentMapper {
    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(int id);
}