package org.example.jdbc;


import org.example.jdbc.DatabasePool;
import org.example.model.Homework;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HomeworkJdbc {

    //得到全部已发布的作业
    public static List<Homework> selectAllHomework() throws SQLException {
        Connection connection = DatabasePool.getHikarDataSource().getConnection();
        String sqlString = "SELECT * FROM homework";
        List<Homework> list = new ArrayList<>();
        try {
            connection.setAutoCommit(false);//不自动commit
            PreparedStatement pst = connection.prepareStatement(sqlString);
            ResultSet resultSet = pst.executeQuery();
            // 获取执行结果
            while (resultSet.next()) {
                Homework s = new Homework();
                s.setHomeworkId(resultSet.getInt("homework_id"));
                s.setHomeworkTitle(resultSet.getString("homework_title"));
                list.add(s);
            }
            connection.commit(); //提交事务
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback(); //回滚事务
        }
        return list;
    }

    //向数据库中添加老师新发布的作业
    public  boolean addHomework(Homework h) throws ClassNotFoundException, SQLException {
        Connection connection = DatabasePool.getHikarDataSource().getConnection();
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
            connection.setAutoCommit(false);//不自动commit
            PreparedStatement ps = connection.prepareStatement(sqlString);
            ps.setInt(1, h.getHomeworkId());
            ps.setString(2, h.getHomeworkTitle());
            ps.execute();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        }
        return true;
    }

    //通过作业编号得到作业标题
    public static String getTitlebyId(int homeworkId) throws SQLException {
        Connection connection = DatabasePool.getHikarDataSource().getConnection();
        String sqlString = "SELECT homework_title FROM homework where homework_id = '" + homeworkId + "'";
        String homework_title = "";

        try {
            connection.setAutoCommit(false);//不自动commit
            PreparedStatement pst = connection.prepareStatement(sqlString);
            ResultSet resultSet = pst.executeQuery();
            // 获取执行结果
            if (resultSet.next()) {
                homework_title = resultSet.getString("homework_title");
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        }
        return homework_title;
    }

}

