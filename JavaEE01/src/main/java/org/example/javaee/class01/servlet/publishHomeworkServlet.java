package org.example.javaee.class01.servlet;


import org.example.javaee.class01.Model.Homework;
import org.example.javaee.class01.jdbc.HomeworkJdbc;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/publishHomework")
public class publishHomeworkServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //跳转到该界面
        req.getRequestDispatcher("publishHomework.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Homework newHomework = new Homework();
        //将新的作业信息实体化
        newHomework.setHomeworkId(Integer.parseInt(req.getParameter("homework_id")));
        //编码问题，需要转换
        String str = new String(req.getParameter("homework_title").getBytes("iso-8859-1"), "UTF-8");
        newHomework.setHomeworkTitle(str);

        resp.setContentType("text/html;charset=UTF-8");

        try {
            if (newHomework.getHomeworkTitle().equals("")) {

                //显示弹窗并且当关闭弹窗后跳到指定页面
                resp.getWriter().write("<script>alert('作业标题不得为空！网页将跳转到发布界面！'); window.location='publishHomework.jsp'; window.close();</script>");
                resp.getWriter().flush();
            } else {
                if (HomeworkJdbc.addHomework(newHomework)) {
                    //显示弹窗并且当关闭弹窗后跳到指定页面
                    resp.getWriter().write("<script>alert('发布成功！网页将跳转到首页！'); window.location='teachermenu.jsp'; window.close();</script>");
                    resp.getWriter().flush();
                } else {
                    //显示弹窗并且当关闭弹窗后跳到指定页面
                    resp.getWriter().write("<script>alert('发布失败，请检查后再发布！网页将跳转到发布界面！'); window.location='publishHomework.jsp'; window.close();</script>");
                    resp.getWriter().flush();
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
