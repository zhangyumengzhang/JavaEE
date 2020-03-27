package org.example.javaee.class01.servlet;

import org.example.javaee.class01.Model.StudentHomework;
import org.example.javaee.class01.jdbc.StudentHomeworkJdbc;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/OneHomework")
public class OneHomeworkServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id= Integer.parseInt(req.getParameter("id"));
        List<StudentHomework> list = StudentHomeworkJdbc.selectshomeworkbyid(id);

        req.setAttribute("oneHomeworklist", list);
        req.getRequestDispatcher("OneHomework.jsp").forward(req, resp);
    }
}