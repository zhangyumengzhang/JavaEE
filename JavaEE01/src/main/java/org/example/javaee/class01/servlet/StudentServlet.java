package org.example.javaee.class01.servlet;

import org.example.javaee.class01.Model.Student;
import org.example.javaee.class01.jdbc.UserJdbc;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/allStudent")
public class StudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Student> list = UserJdbc.selectAllStudent();

        req.setAttribute("studentlist", list);
        req.getRequestDispatcher("allStudent.jsp").forward(req, resp);
    }
}
