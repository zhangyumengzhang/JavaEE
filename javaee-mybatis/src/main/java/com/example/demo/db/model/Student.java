package com.example.demo.db.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder
@Getter
@Setter
public class Student {


    private Integer student_id;

    private String password;

    private String student_name;


}
