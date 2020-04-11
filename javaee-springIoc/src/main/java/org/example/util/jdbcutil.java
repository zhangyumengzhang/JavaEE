package org.example.util;


import java.sql.Connection;
import java.sql.SQLException;

public class jdbcutil {

    private static Connection connection = null;

    private jdbcutil() {
    }

    public static Connection getConnection() {
        if (connection != null) return connection;

        String driver = "com.mysql.cj.jdbc.Driver";

        try {
            Class.forName(driver);
            connection = DatabasePool.getHikarDataSource().getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.close();
            } catch (SQLException e1) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
