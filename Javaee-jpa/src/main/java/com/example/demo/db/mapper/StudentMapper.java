package com.example.demo.db.mapper;

import com.example.demo.db.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StudentMapper extends JpaRepository<Student,Integer>, JpaSpecificationExecutor<Student> {
}
