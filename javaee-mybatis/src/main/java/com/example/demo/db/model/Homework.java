package com.example.demo.db.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Builder
@Getter
@Setter
public class Homework {

    private Integer homework_id;

    private String homework_title;
}
