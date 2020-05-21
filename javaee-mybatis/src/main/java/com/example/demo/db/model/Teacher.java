package com.example.demo.db.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder
@Getter
@Setter
public class Teacher {

    private Integer teacher_id;

    private String password;

    private String teacher_name;


}
