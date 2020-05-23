package org.example.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.model.Student;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StudentMapper  {

    Student findById(Integer studentId);

    List<Student> findAll();

    void save(Student student);
}
