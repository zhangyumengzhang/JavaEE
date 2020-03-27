package org.example.javaee.class01.servlet;


import org.example.javaee.class01.Model.Student;
import org.example.javaee.class01.jdbc.UserJdbc;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addStudent")
public class addStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //跳转到该界面
        req.getRequestDispatcher("addStudent.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //从该界面获取到新的学生信息
        Student newStudent = new Student();
        newStudent.setStudentId(Integer.parseInt(req.getParameter("student_id")));
        //编码问题需要转换
        String str = new String(req.getParameter("student_name").getBytes("iso-8859-1"), "UTF-8");
        newStudent.setStudentName(str);
        newStudent.setPassword(req.getParameter("password"));
        resp.setContentType("text/html;charset=UTF-8");

        try {
            if (newStudent.getStudentName().equals("")||newStudent.getPassword().isEmpty()) {
                //显示弹窗并且当关闭弹窗后跳到指定页面
                resp.getWriter().write("<script>alert('姓名或密码不能为空，请检查后再添加!网页将跳转到添加界面！'); window.location='addStudent.jsp'; window.close();</script>");
                resp.getWriter().flush();
            } else {
                if (UserJdbc.addStudent(newStudent)) {

                    //显示弹窗并且当关闭弹窗后跳到指定页面
                    resp.getWriter().write("<script>alert('添加成功！'); window.location='teachermenu.jsp'; window.close();</script>");
                    resp.getWriter().flush();

                } else {
                    //显示弹窗并且当关闭弹窗后跳到指定页面
                    resp.getWriter().write("<script>alert('添加失败，请检查信息后再添加！网页将跳转到添加界面！'); window.location='addStudent.jsp'; window.close();</script>");
                    resp.getWriter().flush();
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
