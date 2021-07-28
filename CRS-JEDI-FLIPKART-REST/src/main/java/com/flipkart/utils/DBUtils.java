package com.flipkart.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtils {
//    static final Properties prop = new Properties();
//    static final InputStream inputStream = DBUtils.class.getClassLoader().getResourceAsStream("config.properties");
    static String DB_URL;
    static String USER;
    static String PASS;
    static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Connection getConnection() {
        Connection connection = null;
//        try {
//            prop.load(inputStream);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        url=jdbc:mysql://localhost:3306/crs
//        user=root
//        password=Something@123
        DB_URL = "jdbc:mysql://localhost:3307/crs?useUnicode=true&characterEncoding=UTF-8&useSSL=false";
        USER = "root";
        PASS = "root";
        try {
            System.out.println("connection");
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println(connection);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static LocalDateTime parseDate(String str) {
        return LocalDateTime.parse(str, formatter);
    }
}


