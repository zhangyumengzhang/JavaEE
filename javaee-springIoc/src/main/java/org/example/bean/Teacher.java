package org.example.bean;

import org.springframework.stereotype.Component;

public class Teacher {
    public Teacher() {

    }

    private int teacherId;

    private String password;

    private String teacherName;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String studentName) {
        this.teacherName = studentName;
    }


}
