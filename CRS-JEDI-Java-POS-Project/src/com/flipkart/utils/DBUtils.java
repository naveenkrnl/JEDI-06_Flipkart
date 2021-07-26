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
    static Properties prop = new Properties();
    static InputStream inputStream = DBUtils.class.getClassLoader().getResourceAsStream("./config.properties");
    static String DB_URL;
    static String USER;
    static String PASS;

    static {
        try {
            prop.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        DB_URL = prop.getProperty("url");
        USER = prop.getProperty("user");
        PASS = prop.getProperty("password");
    }

    static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static Connection getConnection() {
        Connection connection = null;
        try {

            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static LocalDateTime parseDate(String str) {
        return LocalDateTime.parse(str, formatter);
    }
}


