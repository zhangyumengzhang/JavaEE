package org.example.mapper;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.model.StudentHomework;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface StudentHomeworkMapper {

    void save(StudentHomework sh);
    List<StudentHomework> findAll();

    List<StudentHomework> findAllById(Integer id);
}
