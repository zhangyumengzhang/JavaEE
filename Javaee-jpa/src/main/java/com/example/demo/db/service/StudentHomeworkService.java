package com.example.demo.db.service;


import com.example.demo.db.mapper.HomeworkMapper;
import com.example.demo.db.mapper.StudentHomeworkMapper;
import com.example.demo.db.model.StudentHomework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class StudentHomeworkService {
    @Autowired
    final StudentHomeworkMapper studenthomeworkMapper;
    public StudentHomeworkService(StudentHomeworkMapper studenthomeworkMapper) {
        this.studenthomeworkMapper = studenthomeworkMapper;
    }

    //添加学生作业
    public boolean addStudentHomework(StudentHomework sh) {
        try{
            studenthomeworkMapper.save(sh);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //获取所有学生提交作业
    public  List<StudentHomework> selectAll() {

     return studenthomeworkMapper.findAll();
    }


    //获取某一次所有学生提交作业
    public  List<StudentHomework> selectshomeworkbyid(int id) {

        return studenthomeworkMapper.findAllById(Collections.singleton(id));
    }
}