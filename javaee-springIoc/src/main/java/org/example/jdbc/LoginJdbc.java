package org.example.jdbc;
import org.example.dao.Student;
import org.example.dao.Teacher;
import org.example.util.jdbcutil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
@Configuration
public class LoginJdbc {
    private static final Logger logger = LoggerFactory.getLogger(LoginJdbc.class);
    public  static boolean slogin(Student s){
        Connection connection = jdbcutil.getConnection();
        String sqlString = "select * from student where student_id=?";
        //建立连接
        try  {
            PreparedStatement pst = connection.prepareStatement(sqlString);
            pst.setLong(1, s.getStudentId());
            ResultSet resultSet=pst.executeQuery();
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
            return false;
        }
    }

    public static boolean tlogin(Teacher t) {
        Connection connection = jdbcutil.getConnection();
        String sqlString = "select * from teacher where teacher_id=?";
        //建立连接
        try {
            PreparedStatement pst = connection.prepareStatement(sqlString);
            pst.setLong(1, t.getTeacherId());
            ResultSet resultSet = pst.executeQuery();
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

            return false;
        }
    }
}