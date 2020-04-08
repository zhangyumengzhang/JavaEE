package org.example.jdbc;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DatabasePool {
   private  static HikariDataSource hikariDataSource;

    public static HikariDataSource getHikarDataSource(){

       if(null!=hikariDataSource){
           return hikariDataSource;
       }

       synchronized (DatabasePool.class){
           if(null==hikariDataSource){
               String driver = "com.mysql.cj.jdbc.Driver";
               String jdbcurl="jdbc:mysql://localhost:3306/javaee?serverTimezone=UTC&useSSL=false";
               HikariConfig hikariConfig=new HikariConfig();
               hikariConfig.setUsername("root");
               hikariConfig.setPassword("123456");
               hikariConfig.setDriverClassName(driver);
               hikariConfig.setJdbcUrl(jdbcurl);
               hikariDataSource=new HikariDataSource(hikariConfig);
               return hikariDataSource;
           }
       }
       return hikariDataSource;
    }

}
