package com.example.demo.controller;

import com.example.demo.core.response.BaseResponse;
import com.example.demo.core.response.DataResponse;
import com.example.demo.db.model.Homework;
import com.example.demo.db.model.StudentHomework;
import com.example.demo.db.service.HomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private final HomeworkService homeworkService;

    public HomeworkController(HomeworkService homeworkService) {
        this.homeworkService = homeworkService;
    }

    @RequestMapping(value = "/allHomework",method= RequestMethod.GET)
    public void allHomework(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //DataResponse response = new DataResponse();
        List<Homework> list = homeworkService.selectAllHomework();
       // response.setCode(1);
       // response.setData(list);
        System.out.println(list);
        req.setAttribute("homeworklist", list);
        req.getRequestDispatcher("allHomework.jsp").forward(req, resp);
    //return response;
    }


    //发布新作业
    @RequestMapping(value = "/publishHomework")
    public void publishHomework(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String str = new String(req.getParameter("homework_title").getBytes("iso-8859-1"), "UTF-8");

        Homework newHomework = Homework.builder()
                .homeworkId(Integer.parseInt(req.getParameter("homework_id")))
                .homeworkTitle(str)
                .build();

        resp.setContentType("text/html;charset=UTF-8");

        if (newHomework.getHomeworkTitle().equals("")) {

            //显示弹窗并且当关闭弹窗后跳到指定页面
            resp.getWriter().write("<script>alert('作业标题不得为空！网页将跳转到发布界面！'); window.location='publishHomework.jsp'; window.close();</script>");
            resp.getWriter().flush();
        } else {
            if (homeworkService.addHomework(newHomework)) {
                //显示弹窗并且当关闭弹窗后跳到指定页面
                resp.getWriter().write("<script>alert('发布成功！网页将跳转到首页！'); window.location='teachermenu.jsp'; window.close();</script>");
                resp.getWriter().flush();
            } else {
                //显示弹窗并且当关闭弹窗后跳到指定页面
                resp.getWriter().write("<script>alert('发布失败，请检查后再发布！网页将跳转到发布界面！'); window.location='publishHomework.jsp'; window.close();</script>");
                resp.getWriter().flush();
            }
        }
    }
    //所有作业
    @RequestMapping(value = "/sallHomework",method = RequestMethod.GET)
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Homework> list = homeworkService.selectAllHomework();
        req.setAttribute("homeworklist", list);
        req.getRequestDispatcher("sallHomework.jsp").forward(req, resp);
    }
}
