package com.example.demo.db.mapper;


import com.example.demo.db.model.StudentHomework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StudentHomeworkMapper extends JpaRepository<StudentHomework,Integer>, JpaSpecificationExecutor<StudentHomework> {
}
