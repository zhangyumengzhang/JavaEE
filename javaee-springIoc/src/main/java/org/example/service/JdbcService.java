package org.example.service;

import org.example.jdbc.*;
import org.example.model.Homework;
import org.example.model.Student;
import org.example.model.StudentHomework;
import org.example.model.Teacher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class JdbcService {

    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

    HomeworkJdbc homeworkJdbc=(HomeworkJdbc)applicationContext.getBean("HomeworkJdbc");

    LoginJdbc loginJdbc=(LoginJdbc)applicationContext.getBean("LoginJdbc");

    StudentHomeworkJdbc studentHomeworkJdbc=(StudentHomeworkJdbc)applicationContext.getBean("StudentHomeworkJdbc");

    UserJdbc userJdbc=(UserJdbc)applicationContext.getBean("UserJdbc");

    //测试
    public void getid() {
        System.out.println(10);
    }

    //得到全部已发布的作业
    public  List<Homework> selectAllHomework() throws SQLException {

        List<Homework> list=homeworkJdbc.selectAllHomework();
        return list;
    }

    //向数据库中添加老师新发布的作业
    public  boolean addHomework(Homework h) throws ClassNotFoundException, SQLException {
       boolean b=homeworkJdbc.addHomework(h);
       return b;
    }

    //通过作业编号得到作业标题
    public  String getTitlebyId(int homeworkId) throws SQLException {

        String homework_title = homeworkJdbc.getTitlebyId(homeworkId);

        return homework_title;
    }

    public boolean slogin(Student s) throws SQLException {
        boolean b = loginJdbc.slogin(s);
        return b;
    }

    public  boolean tlogin(Teacher t) throws SQLException {
        boolean b = loginJdbc.tlogin(t);
        return b;
    }

    //添加学生作业
    public boolean addStudentHomework(StudentHomework sh) throws ClassNotFoundException, SQLException {
        boolean b = studentHomeworkJdbc.addStudentHomework(sh);
        return b;
    }

    //获取所有学生提交作业
    public  List<StudentHomework> selectAll() throws SQLException {

        List<StudentHomework> list = studentHomeworkJdbc.selectAll();

        return list;
    }

    //获取某一次所有学生提交作业
    public  List<StudentHomework> selectshomeworkbyid(int id) throws SQLException {

        List<StudentHomework> list = studentHomeworkJdbc.selectshomeworkbyid(id);
        return list;
    }

    //获取所有学生信息
    public  List<Student> selectAllStudent() throws SQLException {

        List<Student> list = userJdbc.selectAllStudent();
        return list;
    }

    //获取所有老师信息
    public  List<Teacher> selectAllTeacher() throws SQLException {

        List<Teacher> list = userJdbc.selectAllTeacher();
        return list;
    }

    //增加新的学生
    public  boolean addStudent(Student s) throws ClassNotFoundException, SQLException {
       boolean b=userJdbc.addStudent(s);
       return b;
    }

    //增加新的老师
    public  boolean addTeacher(Teacher t) throws ClassNotFoundException, SQLException {
        boolean b=userJdbc.addTeacher(t);
        return b;
    }
}
