package org.example.service;

import org.example.mapper.HomeworkMapper;
import org.example.model.Homework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeworkService {

    @Autowired
    final HomeworkMapper homeworkMapper;

    public HomeworkService(HomeworkMapper homeworkMapper) {
        this.homeworkMapper = homeworkMapper;
    }

    //得到全部已发布的作业
    public  List<Homework> selectAllHomework() {
        return homeworkMapper.findAll();
    }

    //向数据库中添加老师新发布的作业
    public  boolean addHomework(Homework h) {
        try{
            homeworkMapper.save(h);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //通过作业编号得到作业标题
    public String getTitlebyId(int homeworkId) {
       Homework homework=homeworkMapper.findById(homeworkId);
       return homework.getHomework_title();
    }

}

