package com.example.demo.db.mapper;

import com.example.demo.db.model.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface StudentMapper  {

    @Select("select * from student where student_id = #{studentId}")
    Student findById(Integer studentId);

    @Select("select * from student")
    List<Student> findAll();

    @Insert("insert into student(student_id,student_name,password) " +
            "         values(#{student_id},#{student_name},#{password})")
    void save(Student student);
}
