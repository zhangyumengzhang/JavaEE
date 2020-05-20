package com.example.demo.db.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "student_homework")
public class StudentHomework {

    @Id
    private Integer id;

    private Integer studentId;

    private Integer homeworkId;

    private String homeworkTitle;

    private String homeworkContent;

    private String updateTime;
}
