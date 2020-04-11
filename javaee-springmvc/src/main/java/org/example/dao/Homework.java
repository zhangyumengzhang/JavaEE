package org.example.dao;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


public class Homework {

    private int homeworkId;

    private String homeworkTitle;

    public int getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(int homeworkId) {
        this.homeworkId = homeworkId;
    }

    public String getHomeworkTitle() {
        return homeworkTitle;
    }

    public void setHomeworkTitle(String homeworkTitle) {
        this.homeworkTitle = homeworkTitle;
    }


}
