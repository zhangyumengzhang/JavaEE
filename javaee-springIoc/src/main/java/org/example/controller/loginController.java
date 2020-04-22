package org.example.controller;


import org.example.bean.Student;
import org.example.bean.Teacher;
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

@EnableAspectJAutoProxy
@Controller
public class loginController {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
    JdbcService jdbcService = (JdbcService) applicationContext.getBean("JdbcService");

    @RequestMapping(value = "login",method = RequestMethod.GET)
    public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        String userType = req.getParameter("type");
        int id = Integer.parseInt(req.getParameter("id"));
        String password =req.getParameter("password");
        resp.setContentType("text/html;charset=UTF-8");
        if(userType.equals("teacher")){
            Teacher teach=(Teacher)applicationContext.getBean("Teacher");

            teach.setTeacherId(id);
            teach.setPassword(password);
            if(jdbcService.tlogin(teach)){
                resp.getWriter().write("<script>alert('登陆成功！'); window.location='teachermenu.jsp'; window.close();</script>");
                resp.getWriter().flush();
                System.out.println("Before Service");
            }else{
                resp.getWriter().write("<script>alert('用户名或密码错误，请检查后再登录!网页将跳转到登录界面！'); window.location='index.jsp'; window.close();</script>");
                resp.getWriter().flush();
            }
        }else if(userType.equals("student")){

            Student stud=(Student)applicationContext.getBean("Student");

            stud.setStudentId(id);
            stud.setPassword(password);
            if(jdbcService.slogin(stud)){
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
            Teacher teach=(Teacher)applicationContext.getBean("Teacher");
            teach.setTeacherId(id);
            teach.setPassword(password);
            teach.setTeacherName(name);
            try {
                if(jdbcService.addTeacher(teach)){
                    resp.getWriter().write("<script>alert('注册成功！'); window.location='index.jsp'; window.close();</script>");
                    resp.getWriter().flush();
                }else{
                    resp.getWriter().write("<script>alert('该用户已存在!网页将跳转到登录界面！'); window.location='index.jsp'; window.close();</script>");
                    resp.getWriter().flush();
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }else if(userType.equals("student")){
            Student stud=(Student)applicationContext.getBean("Student");
            stud.setStudentId(id);
            stud.setStudentName(name);
            stud.setPassword(password);
            try {
                if(jdbcService.addStudent(stud)){
                    resp.getWriter().write("<script>alert('注册成功！'); window.location='index.jsp'; window.close();</script>");
                    resp.getWriter().flush();
                }else{
                    resp.getWriter().write("<script>alert('该用户已存在!网页将跳转到登录界面！'); window.location='index.jsp'; window.close();</script>");
                    resp.getWriter().flush();
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
