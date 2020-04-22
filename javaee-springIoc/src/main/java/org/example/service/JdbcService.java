package org.example.service;

import org.example.bean.Homework;
import org.example.bean.Student;
import org.example.bean.StudentHomework;
import org.example.bean.Teacher;
import org.example.util.DatabasePool;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class JdbcService {

    public  void getid() {
        System.out.println(10);
    }
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
    public static boolean addHomework(Homework h) throws ClassNotFoundException, SQLException {
        Connection connection = DatabasePool.getHikarDataSource().getConnection();
        String sqlString = "insert into homework(homework_id,homework_title)values(?,?)";

        //判断这个作业编号是否已经存在
        boolean ishomework = true;
        List<Homework> hlist = selectAllHomework();
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
    public  static boolean slogin(Student s) throws SQLException {
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

    public static boolean tlogin(Teacher t) throws SQLException {
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

    //添加学生作业
    public static boolean addStudentHomework(StudentHomework sh) throws ClassNotFoundException, SQLException {

        Connection connection = DatabasePool.getHikarDataSource().getConnection();
        String sqlString = "insert into student_homework(id,student_id,homework_id,homework_title,homework_content,update_time)values(?,?,?,?,?,?)";

        //获取系统当前时间
        java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf =
                new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(dt);
        //将新的学生作业信息补充完整
        sh.setHomeworkTitle(getTitlebyId(sh.getHomeworkId()));
        sh.setUpdateTime(currentTime);
        //判断该学生以及该作业是否存在
        boolean isstudent = false;
        boolean ishomework = false;

        List<Student> slist = selectAllStudent();
        List<Homework> hlist = selectAllHomework();

        for (Student s : slist) {
            if (s.getStudentId() == sh.getStudentId()) {
                isstudent = true;
                break;
            }
        }
        for (Homework h : hlist) {
            if (h.getHomeworkId() == sh.getHomeworkId()) {
                ishomework = true;
                break;
            }
        }
        //如果学生或者不存在或者作业不存在均返回FALSE
        if (isstudent == false || ishomework == false) {
            return false;
        }

        try {
            connection.setAutoCommit(false);//不自动commit
            PreparedStatement pst = connection.prepareStatement(sqlString);
            pst.setInt(1, sh.getId());
            pst.setInt(2, sh.getStudentId());
            pst.setInt(3, sh.getHomeworkId());
            pst.setString(4, sh.getHomeworkTitle());
            pst.setString(5, sh.getHomeworkContent());
            pst.setString(6, sh.getUpdateTime());
            pst.execute();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        }
        return true;
    }

    //获取所有学生提交作业
    public static List<StudentHomework> selectAll() throws SQLException {

        Connection connection = DatabasePool.getHikarDataSource().getConnection();
        String sqlString = "SELECT * FROM student_homework";

        List<StudentHomework> list = new ArrayList<>();
        //建立连接

        try {
            connection.setAutoCommit(false);//不自动commit
            PreparedStatement pst = connection.prepareStatement(sqlString);
            ResultSet resultSet = pst.executeQuery();
            // 获取执行结果
            while (resultSet.next()) {
                StudentHomework sh = new StudentHomework();
                sh.setId(resultSet.getInt("id"));
                sh.setStudentId(resultSet.getInt("student_id"));
                sh.setHomeworkId(resultSet.getInt("homework_id"));
                sh.setHomeworkTitle(resultSet.getString("homework_title"));
                sh.setHomeworkContent(resultSet.getString("homework_content"));
                sh.setUpdateTime(resultSet.getString("update_time"));
                list.add(sh);
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        }
        return list;
    }

    //获取某一次所有学生提交作业
    public static List<StudentHomework> selectshomeworkbyid(int id) throws SQLException {

        Connection connection = DatabasePool.getHikarDataSource().getConnection();
        String sqlString = "SELECT * FROM student_homework where homework_id='" + id + "'";

        List<StudentHomework> list = new ArrayList<>();
        //建立连接

        try {
            connection.setAutoCommit(false);//不自动commit
            PreparedStatement pst = connection.prepareStatement(sqlString);
            ResultSet resultSet = pst.executeQuery();
            // 获取执行结果
            while (resultSet.next()) {
                StudentHomework sh = new StudentHomework();
                sh.setId(resultSet.getInt("id"));
                sh.setStudentId(resultSet.getInt("student_id"));
                sh.setHomeworkId(resultSet.getInt("homework_id"));
                sh.setHomeworkTitle(resultSet.getString("homework_title"));
                sh.setHomeworkContent(resultSet.getString("homework_content"));
                sh.setUpdateTime(resultSet.getString("update_time"));
                list.add(sh);
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        }
        return list;
    }
    //获取所有学生信息
    public static List<Student> selectAllStudent() throws SQLException {

        Connection connection = DatabasePool.getHikarDataSource().getConnection();
        String sqlString = "SELECT * FROM student";

        List<Student> list = new ArrayList<>();
        try {
            connection.setAutoCommit(false);//不自动commit
            PreparedStatement pst = connection.prepareStatement(sqlString);
            ResultSet resultSet = pst.executeQuery();
            // 获取执行结果
            while (resultSet.next()) {
                Student s = new Student();
                s.setStudentId(resultSet.getInt("student_id"));
                s.setStudentName(resultSet.getString("student_name"));
                list.add(s);
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        }
        return list;
    }

    //获取所有老师信息
    public static List<Teacher> selectAllTeacher() throws SQLException {

        Connection connection = DatabasePool.getHikarDataSource().getConnection();
        String sqlString = "SELECT * FROM teacher";

        List<Teacher> list = new ArrayList<>();
        try {
            connection.setAutoCommit(false);//不自动commit
            PreparedStatement pst = connection.prepareStatement(sqlString);
            ResultSet resultSet = pst.executeQuery();
            // 获取执行结果
            while (resultSet.next()) {
                Teacher s = new Teacher();
                s.setTeacherId(resultSet.getInt("teacher_id"));
                s.setPassword(resultSet.getString("password"));
                list.add(s);
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        }
        return list;
    }

    //增加新的学生
    public static boolean addStudent(Student s) throws ClassNotFoundException, SQLException {
        Connection connection = DatabasePool.getHikarDataSource().getConnection();
        String sqlString = "insert into student(student_id,student_name,password)values(?,?,?)";

        //判断该学生是否已经存在
        boolean isStudent = true;

        List<Student> slist = selectAllStudent();

        for (Student stud : slist) {
            if (stud.getStudentId() == s.getStudentId()) {
                isStudent = false;
                break;
            }
        }
        //如果存在返回FALSE
        if (isStudent == false) {
            return false;
        }

        //建立连接
        try  {
            connection.setAutoCommit(false);//不自动commit
            PreparedStatement pst = connection.prepareStatement(sqlString);
            pst.setLong(1, s.getStudentId());
            pst.setString(2, s.getStudentName());
            pst.setString(3, s.getPassword());
            pst.execute();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        }
        return true;
    }

    //增加新的老师
    public static boolean addTeacher(Teacher t) throws ClassNotFoundException, SQLException {
        Connection connection = DatabasePool.getHikarDataSource().getConnection();
        String sqlString = "insert into teacher(teacher_id,password,teacher_name)values(?,?,?)";

        //判断该学生是否已经存在
        boolean isTeacher = true;

        List<Teacher> slist = selectAllTeacher();

        for (Teacher stud : slist) {
            if (stud.getTeacherId() == t.getTeacherId()) {
                isTeacher = false;
                break;
            }
        }
        //如果存在返回FALSE
        if (isTeacher == false) {
            return false;
        }

        //建立连接
        try  {
            connection.setAutoCommit(false);//不自动commit
            PreparedStatement pst = connection.prepareStatement(sqlString);
            pst.setLong(1, t.getTeacherId());
            pst.setString(2, t.getPassword());
            pst.setString(3, t.getTeacherName());
            pst.execute();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        }
        return true;
    }
}
