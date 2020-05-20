package com.example.demo.controller;

import com.example.demo.db.model.StudentHomework;
import com.example.demo.db.service.HomeworkService;
import com.example.demo.db.service.StudentHomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//@WebServlet("/allStudentHomework")
@Controller
public class StudentHomeworkController {

    @Autowired
    private final StudentHomeworkService studenthomeworkService;

    public StudentHomeworkController(StudentHomeworkService studenthomeworkService) {
        this.studenthomeworkService = studenthomeworkService;
    }

    //所有学生作业
    @RequestMapping(value = "/allStudentHomework",method = RequestMethod.GET)
    public void allStudentHomework(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<StudentHomework> list = studenthomeworkService.selectAll();

        req.setAttribute("studentHomeworklist", list);
        req.getRequestDispatcher("allStudentHomework.jsp").forward(req, resp);
    }

    //提交作业
    @RequestMapping(value = "/submitallH")
    public void submitallHomework(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //编码问题需要转换
        String str = new String(req.getParameter("homework_content").getBytes("iso-8859-1"), "UTF-8");

        StudentHomework newsHomework=StudentHomework.builder()
                .homeworkId(Integer.parseInt(req.getParameter("homework_id")))
                .studentId(Integer.parseInt(req.getParameter("student_id")))
                .homeworkContent(str)
                .build();

        resp.setContentType("text/html;charset=UTF-8");
        if (studenthomeworkService.addStudentHomework(newsHomework)) {
            //显示弹窗并且当关闭弹窗后跳到指定页面
            resp.getWriter().write("<script>alert('提交成功！网页将跳转到首页！'); window.location='studentmenu.jsp'; window.close();</script>");
            resp.getWriter().flush();
        } else {
            //显示弹窗并且当关闭弹窗后跳到指定页面
            resp.getWriter().write("<script>alert('提交失败，请仔细检查后再提交！网页将跳转到提交界面！'); window.location='submitHomework.jsp'; window.close();</script>");
            resp.getWriter().flush();
        }
    }

    static boolean isfirst=true;

    @RequestMapping(value = "/submitHomework")
    public void submitHomework(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if(isfirst){
            Integer id= Integer.parseInt(req.getParameter("id"));
            req.setAttribute("id", id);
            req.getRequestDispatcher("submitHomework.jsp").forward(req, resp);
            isfirst=false;
        }

        //编码问题需要转换
        String str = new String(req.getParameter("homework_content").getBytes("iso-8859-1"), "UTF-8");

        //将新的学生作业信息实体化
        StudentHomework newsHomework = StudentHomework.builder()
                .studentId(Integer.parseInt(req.getParameter("student_id")))
                .homeworkId(Integer.parseInt(req.getParameter("homework_id")))
                .homeworkContent(str)
                .build();

        resp.setContentType("text/html;charset=UTF-8");
        if (studenthomeworkService.addStudentHomework(newsHomework)) {
            //显示弹窗并且当关闭弹窗后跳到指定页面
            resp.getWriter().write("<script>alert('提交成功！网页将跳转到首页！'); window.location='studentmenu.jsp'; window.close();</script>");
            resp.getWriter().flush();
        } else {
            //显示弹窗并且当关闭弹窗后跳到指定页面
            resp.getWriter().write("<script>alert('提交失败，请仔细检查后再提交！网页将跳转到提交界面！'); window.location='submitHomework.jsp'; window.close();</script>");
            resp.getWriter().flush();
        }
        isfirst=true;
    }

    //选取某一次作业
    @RequestMapping(value = "/OneHomework",method = RequestMethod.GET)
    public void oneHomework(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id= Integer.parseInt(req.getParameter("id"));
        List<StudentHomework> list = studenthomeworkService.selectshomeworkbyid(id);

        req.setAttribute("oneHomeworklist", list);
        req.getRequestDispatcher("OneHomework.jsp").forward(req, resp);
    }
}