package org.example.controller;


import org.example.bean.StudentHomework;
import org.example.service.JdbcService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@EnableAspectJAutoProxy
@Controller
public class StudentHomeworkController {

    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
    JdbcService jdbcService = (JdbcService) applicationContext.getBean("JdbcService");

    @RequestMapping(value = "/allStudentHomework",method = RequestMethod.GET)
    public void allStudentHomework(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        List<StudentHomework> list = jdbcService.selectAll();
        req.setAttribute("studentHomeworklist", list);
        req.getRequestDispatcher("allStudentHomework.jsp").forward(req, resp);
    }

    @RequestMapping(value = "/submitallH")
    public void submitallHomework(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
            if (jdbcService.addStudentHomework(newsHomework)) {
                //显示弹窗并且当关闭弹窗后跳到指定页面
                resp.getWriter().write("<script>alert('提交成功！网页将跳转到首页！'); window.location='studentmenu.jsp'; window.close();</script>");
                resp.getWriter().flush();
            } else {
                //显示弹窗并且当关闭弹窗后跳到指定页面
                resp.getWriter().write("<script>alert('提交失败，请仔细检查后再提交！网页将跳转到提交界面！'); window.location='submitHomework.jsp'; window.close();</script>");
                resp.getWriter().flush();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
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
            if (jdbcService.addStudentHomework(newsHomework)) {
                //显示弹窗并且当关闭弹窗后跳到指定页面
                resp.getWriter().write("<script>alert('提交成功！网页将跳转到首页！'); window.location='studentmenu.jsp'; window.close();</script>");
                resp.getWriter().flush();
            } else {
                //显示弹窗并且当关闭弹窗后跳到指定页面
                resp.getWriter().write("<script>alert('提交失败，请仔细检查后再提交！网页将跳转到提交界面！'); window.location='submitHomework.jsp'; window.close();</script>");
                resp.getWriter().flush();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        isfirst=true;
    }

}