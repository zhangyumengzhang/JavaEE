package com.example.demo.db.service;

import com.example.demo.db.mapper.TeacherMapper;
import com.example.demo.db.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherService  {
    @Autowired
    final TeacherMapper teacherMapper;
    public TeacherService(TeacherMapper teacherMapper) {
        this.teacherMapper = teacherMapper;
    }

    //获取所有老师信息
    public  List<Teacher> selectAllTeacher() {
        return teacherMapper.findAll();
    }

    //增加新的老师
    public  boolean addTeacher(Teacher t)  {
        try{
            teacherMapper.save(t);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    //登录
    public  boolean tlogin(Teacher t) {
        Teacher nt= teacherMapper.findById(t.getTeacher_id());
        if(t.getPassword()==nt.getPassword()){
            return true;
        }else{
            return false;
        }
    }
}
