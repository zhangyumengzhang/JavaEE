package com.example.demo.db.mapper;

import com.example.demo.db.model.Teacher;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TeacherMapper{

    @Select("select * from teacher")
    List<Teacher> findAll();

    @Insert("insert into teacher(teacher_id,teacher_name,password) " +
            "         values(#{teacher_id},#{teacher_name},#{password})")
    void save(Teacher t);

    @Select("select * from teacher where teacher_id = #{teacherId}")
    Teacher findById(Integer teacherId);
}
