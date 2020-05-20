package com.example.demo.db.mapper;

import com.example.demo.db.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TeacherMapper extends JpaRepository<Teacher,Integer>, JpaSpecificationExecutor<Teacher> {
}
