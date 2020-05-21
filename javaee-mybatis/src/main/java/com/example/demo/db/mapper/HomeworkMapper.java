package com.example.demo.db.mapper;

import com.example.demo.db.model.Homework;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface HomeworkMapper{

    void save(Homework h);
    @Select("select * from homework")
    List<Homework> findAll();

    @Select("select * from homework where homework_id = #{homeworkId}")
    Homework findById(int homeworkId);
}
