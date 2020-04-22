package org.example.controller;

import org.example.bean.Homework;
import org.example.bean.StudentHomework;
import org.example.service.HomeworkJdbc;
import org.example.service.StudentHomeworkJdbc;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@Controller
public class HomeworkController {

    @RequestMapping(value = "/allHomework",method= RequestMethod.GET)
    public void allHomework(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Homework> list = HomeworkJdbc.selectAllHomework();
        req.setAttribute("homeworklist", list);
        req.getRequestDispatcher("allHomework.jsp").forward(req, resp);
    }

    @RequestMapping(value = "/OneHomework",method = RequestMethod.GET)
    public void oneHomework(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id= Integer.parseInt(req.getParameter("id"));
        List<StudentHomework> list = StudentHomeworkJdbc.selectshomeworkbyid(id);

        req.setAttribute("oneHomeworklist", list);
        req.getRequestDispatcher("OneHomework.jsp").forward(req, resp);
    }

    @RequestMapping(value = "/publishHomework")
    public void publishHomework(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
    @RequestMapping(value = "/sallHomework",method = RequestMethod.GET)
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Homework> list = HomeworkJdbc.selectAllHomework();
        req.setAttribute("homeworklist", list);
        req.getRequestDispatcher("sallHomework.jsp").forward(req, resp);
    }
}
