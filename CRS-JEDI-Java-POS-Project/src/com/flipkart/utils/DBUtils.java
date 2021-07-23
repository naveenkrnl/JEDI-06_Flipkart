package com.flipkart.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DBUtils {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/CRS";
    static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    // Database credentials
    static final String USER = "root";
    static final String PASS = "luicifer";

    public static Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            return connection;

        } catch (SQLException se) {
            // TODO : Exception print with custom message
            se.printStackTrace();
        } catch (Exception e) {
            //
            // e.printStackTrace();
        }
        return connection;
    }

    public static LocalDateTime parseDate(String str) {
        return LocalDateTime.parse(str, formatter);
    }
}