package com.example.demo.db.mapper;


import com.example.demo.db.model.StudentHomework;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Mapper
@Repository
public interface StudentHomeworkMapper {

    @Insert("insert into student_homework(id,student_id,homework_id,homework_title,homework_content,updata_time) " +
            "         values(#{id},#{student_id},#{homework_id},#{homework_title},#{homework_content},#{updata_time})")
    void save(StudentHomework sh);

    @Select("select * from student_homework")
    List<StudentHomework> findAll();

    @Select("select * from student_homework where id = #{id}")
    List<StudentHomework> findAllById(Integer id);
}
