package com.example.demo.db.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder
@Getter
@Setter
public class StudentHomework {


    private Integer id;

    private Integer student_id;

    private Integer homework_id;

    private String homework_title;

    private String homework_content;

    private String update_time;
}
