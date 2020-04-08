package org.example.controller;


import org.example.dao.Homework;
import org.example.jdbc.HomeworkJdbc;
import org.example.jdbc.StudentHomeworkJdbc;
import org.example.dao.StudentHomework;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//@WebServlet("/allStudentHomework")
@Controller
public class StudentHomeworkController {


    @RequestMapping(value = "/allStudentHomework",method = RequestMethod.GET)
    public void allStudentHomework(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        //HomeworkJdbc homeworkJdbc = (HomeworkJdbc) applicationContext.getBean("HomeworkJdbc");
        StudentHomeworkJdbc StudentHomeworkJdbc=(StudentHomeworkJdbc)applicationContext.getBean("StudentHomeworkJdbc");
        List<StudentHomework> list = StudentHomeworkJdbc.selectAll();

        req.setAttribute("studentHomeworklist", list);
        req.getRequestDispatcher("allStudentHomework.jsp").forward(req, resp);
    }

    @RequestMapping(value = "/submitallH")
    public void submitallHomework(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

       //HomeworkJdbc homeworkJdbc = (HomeworkJdbc) applicationContext.getBean("HomeworkJdbc");
        //将新的学生作业信息实体化
        StudentHomework newsHomework=(StudentHomework)applicationContext.getBean("StudentHomework");
        //获取表单信息
        newsHomework.setStudentId(Integer.parseInt(req.getParameter("student_id")));
        newsHomework.setHomeworkId(Integer.parseInt(req.getParameter("homework_id")));
        //编码问题需要转换
        String str = new String(req.getParameter("homework_content").getBytes("iso-8859-1"), "UTF-8");
        newsHomework.setHomeworkContent(str);

        resp.setContentType("text/html;charset=UTF-8");
        try {
            if (StudentHomeworkJdbc.addStudentHomework(newsHomework)) {
                //显示弹窗并且当关闭弹窗后跳到指定页面
                resp.getWriter().write("<script>alert('提交成功！网页将跳转到首页！'); window.location='studentmenu.jsp'; window.close();</script>");
                resp.getWriter().flush();
            } else {
                //显示弹窗并且当关闭弹窗后跳到指定页面
                resp.getWriter().write("<script>alert('提交失败，请仔细检查后再提交！网页将跳转到提交界面！'); window.location='submitHomework.jsp'; window.close();</script>");
                resp.getWriter().flush();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    static boolean isfirst=true;

    @RequestMapping(value = "/submitHomework")
    public void submitHomework(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        if(isfirst){
            Integer id= Integer.parseInt(req.getParameter("id"));
            req.setAttribute("id", id);
            req.getRequestDispatcher("submitHomework.jsp").forward(req, resp);
            isfirst=false;
        }
        //将新的学生作业信息实体化
        StudentHomework newsHomework = (StudentHomework)applicationContext.getBean("StudentHomework");
        //获取表单信息
        newsHomework.setStudentId(Integer.parseInt(req.getParameter("student_id")));
        newsHomework.setHomeworkId(Integer.parseInt(req.getParameter("homework_id")));
        //编码问题需要转换
        String str = new String(req.getParameter("homework_content").getBytes("iso-8859-1"), "UTF-8");
        newsHomework.setHomeworkContent(str);

        resp.setContentType("text/html;charset=UTF-8");
        try {
            if (StudentHomeworkJdbc.addStudentHomework(newsHomework)) {
                //显示弹窗并且当关闭弹窗后跳到指定页面
                resp.getWriter().write("<script>alert('提交成功！网页将跳转到首页！'); window.location='studentmenu.jsp'; window.close();</script>");
                resp.getWriter().flush();
            } else {
                //显示弹窗并且当关闭弹窗后跳到指定页面
                resp.getWriter().write("<script>alert('提交失败，请仔细检查后再提交！网页将跳转到提交界面！'); window.location='submitHomework.jsp'; window.close();</script>");
                resp.getWriter().flush();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        isfirst=true;
    }

}