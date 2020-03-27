package org.example.javaee.class01.servlet;


import org.example.javaee.class01.Model.Homework;
import org.example.javaee.class01.jdbc.HomeworkJdbc;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/sallHomework")
public class sallHomeworkServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Homework> list = HomeworkJdbc.selectAllHomework();
        req.setAttribute("homeworklist", list);
        req.getRequestDispatcher("sallHomework.jsp").forward(req, resp);
    }
}
