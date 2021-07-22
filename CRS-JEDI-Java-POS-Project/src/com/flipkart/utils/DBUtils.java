package com.flipkart.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBUtils {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/CRS";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "luicifer";

    public static Connection getConnection() {
        Connection connection = null;
        if (connection != null)
            return connection;
        else {

            Connection conn = null;
            PreparedStatement stmt = null;

            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(DB_URL, USER, PASS);
                return conn;

            } catch (SQLException se) {
                se.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}