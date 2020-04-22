package org.example.jdbc;
import org.example.model.Student;
import org.example.model.Teacher;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginJdbc {

    public   boolean slogin(Student s) throws SQLException {
        Connection connection = DatabasePool.getHikarDataSource().getConnection();
        String sqlString = "select * from student where student_id=?";
        //建立连接
        try  {
            connection.setAutoCommit(false);//不自动commit
            PreparedStatement pst = connection.prepareStatement(sqlString);
            pst.setLong(1, s.getStudentId());
            ResultSet resultSet=pst.executeQuery();
            connection.commit();
            if (resultSet.next()){
                if(s.getPassword().equals(resultSet.getString("password"))){
                    return true;
                }else{
                    return false;
                }
            }else{
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
            return false;
        }
    }

    public  boolean tlogin(Teacher t) throws SQLException {
        Connection connection = DatabasePool.getHikarDataSource().getConnection();
        String sqlString = "select * from teacher where teacher_id=?";
        //建立连接
        try {
            connection.setAutoCommit(false);//不自动commit
            PreparedStatement pst = connection.prepareStatement(sqlString);
            pst.setLong(1, t.getTeacherId());
            ResultSet resultSet = pst.executeQuery();
            connection.commit();
            if (resultSet.next()) {
                if (t.getPassword().equals(resultSet.getString("password"))) {

                    return true;
                } else {

                    return false;
                }
            } else {

                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
            return false;
        }
    }
}
