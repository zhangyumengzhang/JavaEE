package org.example.mapper;

import org.example.model.Homework;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeworkMapper{

    void save(Homework h);
    List<Homework> findAll();

    Homework findById(int homeworkId);
}
