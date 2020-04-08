package org.example.jdbc;

import org.example.dao.Homework;
import org.example.util.jdbcutil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Configuration
public class HomeworkJdbc {
    private static final Logger logger = LoggerFactory.getLogger(HomeworkJdbc.class);
    //得到全部已发布的作业
    public static List<Homework> selectAllHomework() {
        Connection connection =jdbcutil.getConnection();
        String sqlString = "SELECT * FROM homework";
        List<Homework> list = new ArrayList<>();
        try {
            PreparedStatement pst = connection.prepareStatement(sqlString);
            ResultSet resultSet = pst.executeQuery();
            // 获取执行结果
            while (resultSet.next()) {
                Homework s = new Homework();
                s.setHomeworkId(resultSet.getInt("homework_id"));
                s.setHomeworkTitle(resultSet.getString("homework_title"));
                list.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //向数据库中添加老师新发布的作业
    public static boolean addHomework(Homework h) throws ClassNotFoundException {
        Connection connection = jdbcutil.getConnection();
        String sqlString = "insert into homework(homework_id,homework_title)values(?,?)";

        //判断这个作业编号是否已经存在
        boolean ishomework = true;
        List<Homework> hlist = HomeworkJdbc.selectAllHomework();
        //循环判断
        for (Homework home : hlist) {
            if (home.getHomeworkId() == h.getHomeworkId()) {
                ishomework = false;
                break;
            }
        }
        //如果存在返回FALSE
        if (ishomework == false) {
            return false;
        }
        //进行操作
        try {
            PreparedStatement ps = connection.prepareStatement(sqlString);
            ps.setInt(1, h.getHomeworkId());
            ps.setString(2, h.getHomeworkTitle());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    //通过作业编号得到作业标题
    public static String getTitlebyId(int homeworkId) {
        Connection connection = jdbcutil.getConnection();
        String sqlString = "SELECT homework_title FROM homework where homework_id = '" + homeworkId + "'";
        String homework_title = "";

        try {
            PreparedStatement pst = connection.prepareStatement(sqlString);
            ResultSet resultSet = pst.executeQuery();
            // 获取执行结果
            if (resultSet.next()) {
                homework_title = resultSet.getString("homework_title");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return homework_title;
    }

}

