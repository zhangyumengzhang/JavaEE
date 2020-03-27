package org.example.javaee.class01.servlet;


import org.example.javaee.class01.Model.Student;
import org.example.javaee.class01.Model.Teacher;
import org.example.javaee.class01.jdbc.UserJdbc;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class registerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userType = req.getParameter("type");
        int id = Integer.parseInt(req.getParameter("id"));
        String name=req.getParameter("name");
        String password =req.getParameter("password");
        resp.setContentType("text/html;charset=UTF-8");
        if(userType.equals("teacher")){
            Teacher teach=new Teacher();
            teach.setTeacherId(id);
            teach.setPassword(password);
            teach.setTeacherName(name);
            try {
                if(UserJdbc.addTeacher(teach)){
                    resp.getWriter().write("<script>alert('注册成功！'); window.location='index.jsp'; window.close();</script>");
                    resp.getWriter().flush();
                }else{
                    resp.getWriter().write("<script>alert('该用户已存在!网页将跳转到登录界面！'); window.location='index.jsp'; window.close();</script>");
                    resp.getWriter().flush();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else if(userType.equals("student")){
            Student stud=new Student();
            stud.setStudentId(id);
            stud.setStudentName(name);
            stud.setPassword(password);
            try {
                if(UserJdbc.addStudent(stud)){
                    resp.getWriter().write("<script>alert('注册成功！'); window.location='index.jsp'; window.close();</script>");
                    resp.getWriter().flush();
                }else{
                    resp.getWriter().write("<script>alert('该用户已存在!网页将跳转到登录界面！'); window.location='index.jsp'; window.close();</script>");
                    resp.getWriter().flush();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
