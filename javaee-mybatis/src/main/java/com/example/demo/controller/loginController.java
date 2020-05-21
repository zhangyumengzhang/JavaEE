package com.example.demo.controller;


import com.example.demo.db.model.Student;
import com.example.demo.db.model.Teacher;
import com.example.demo.db.service.StudentService;
import com.example.demo.db.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class loginController {

    @Autowired
     private final StudentService studentService;
    @Autowired
     private final TeacherService teacherService;
    public loginController(StudentService studentService,TeacherService teacherService) {
        this.studentService = studentService;
        this.teacherService=teacherService;
    }
    @RequestMapping("index")
    public String test(){
        System.out.println("q12345uyd");
        return "index.jsp";
    }

    @RequestMapping(value = "login",method = RequestMethod.GET)
    public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String userType = req.getParameter("type");
        int id = Integer.parseInt(req.getParameter("id"));
        String password =req.getParameter("password");
        resp.setContentType("text/html;charset=UTF-8");

        if(userType.equals("teacher")){
            Teacher teach=Teacher.builder()
                    .teacher_id(id)
                    .password(password)
                    .build();

            if(teacherService.tlogin(teach)){
                resp.getWriter().write("<script>alert('登陆成功！'); window.location='teachermenu.jsp'; window.close();</script>");
                resp.getWriter().flush();
            }else{
                resp.getWriter().write("<script>alert('用户名或密码错误，请检查后再登录!网页将跳转到登录界面！'); window.location='index.jsp'; window.close();</script>");
                resp.getWriter().flush();
            }
        }else if(userType.equals("student")){

            Student stud=Student.builder()
                    .student_id(id)
                    .password(password)
                    .build();

            if(studentService.slogin(stud)){
                resp.getWriter().write("<script>alert('登陆成功！'); window.location='studentmenu.jsp'; window.close();</script>");
                resp.getWriter().flush();
            }else{
                resp.getWriter().write("<script>alert('用户名或密码错误，请检查后再登录!网页将跳转到登录界面！'); window.location='index.jsp'; window.close();</script>");
                resp.getWriter().flush();
            }
        }
    }

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String userType = req.getParameter("type");
        int id = Integer.parseInt(req.getParameter("id"));
        String name=req.getParameter("name");
        String password =req.getParameter("password");
        resp.setContentType("text/html;charset=UTF-8");
        if(userType.equals("teacher")){
            Teacher teach=Teacher.builder()
                    .teacher_id(id)
                    .password(password)
                    .teacher_name(name)
                    .build();

            if(teacherService.addTeacher(teach)){
                resp.getWriter().write("<script>alert('注册成功！'); window.location='index.jsp'; window.close();</script>");
                resp.getWriter().flush();
            }else{
                resp.getWriter().write("<script>alert('该用户已存在!网页将跳转到登录界面！'); window.location='index.jsp'; window.close();</script>");
                resp.getWriter().flush();
            }
        }else if(userType.equals("student")){
            Student stud=Student.builder()
                    .student_id(id)
                    .password(password)
                    .student_name(name)
                    .build();
            if(studentService.addStudent(stud)){
                resp.getWriter().write("<script>alert('注册成功！'); window.location='index.jsp'; window.close();</script>");
                resp.getWriter().flush();
            }else{
                resp.getWriter().write("<script>alert('该用户已存在!网页将跳转到登录界面！'); window.location='index.jsp'; window.close();</script>");
                resp.getWriter().flush();
            }
        }
    }
}
