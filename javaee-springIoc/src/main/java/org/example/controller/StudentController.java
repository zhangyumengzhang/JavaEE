package org.example.controller;

import org.example.bean.Student;
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
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

@EnableAspectJAutoProxy
@Controller
public class StudentController {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
    JdbcService jdbcService = (JdbcService) applicationContext.getBean("JdbcService");

    @RequestMapping(value = "/allStudent",method = RequestMethod.GET)
    public void allStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {

        List<Student> list = jdbcService.selectAllStudent();

        req.setAttribute("studentlist", list);
        req.getRequestDispatcher("allStudent.jsp").forward(req, resp);
    }

    @RequestMapping(value = "/addS", method=RequestMethod.GET)
    public void addStudent(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {

        int id=Integer.parseInt(req.getParameter("student_id"));
        //编码问题需要转换
        String str = new String(req.getParameter("student_name").getBytes("iso-8859-1"), "UTF-8");
        String password=req.getParameter("password");
        //从该界面获取到新的学生信息
        Student newStudent=(Student)applicationContext.getBean("Student");
        newStudent.setStudentId(id);
        newStudent.setStudentName(str);
        newStudent.setPassword(password);
        resp.setContentType("text/html;charset=UTF-8");

        try {
            if (newStudent.getStudentName().equals("")||newStudent.getPassword().isEmpty()) {
                //显示弹窗并且当关闭弹窗后跳到指定页面
                resp.getWriter().write("<script>alert('姓名或密码不能为空，请检查后再添加!网页将跳转到添加界面！'); window.location='addStudent.jsp'; window.close();</script>");
                resp.getWriter().flush();
            } else {
                if (jdbcService.addStudent(newStudent)) {

                    //显示弹窗并且当关闭弹窗后跳到指定页面
                    resp.getWriter().write("<script>alert('添加成功！'); window.location='teachermenu.jsp'; window.close();</script>");
                    resp.getWriter().flush();

                } else {
                    //显示弹窗并且当关闭弹窗后跳到指定页面
                    resp.getWriter().write("<script>alert('添加失败，请检查信息后再添加！网页将跳转到添加界面！'); window.location='addStudent.jsp'; window.close();</script>");
                    resp.getWriter().flush();
                }
            }
        } catch (ClassNotFoundException | IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
