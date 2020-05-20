package com.example.demo.db.mapper;

import com.example.demo.db.model.Homework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HomeworkMapper extends JpaRepository<Homework,Integer>, JpaSpecificationExecutor<Homework> {
}
