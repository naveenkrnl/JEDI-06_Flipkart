package com.flipkart.utils;

import java.sql.Connection;
import java.sql.DriverManager;
public class DBUtils {

    public static Connection getConnection() {
        Connection connection = null;
        if (connection != null)
            return connection;
        else
        {
                String JDBC_DRIVER = "com.mysql.jdbc.Driver";
                String DB_URL = "jdbc:mysql://localhost/CRS";

                //  Database credentials
                String USER = "root";
                String PASS = "luicifer";
                connection = DriverManager.getConnection(DB_URL, USER, PASS);

            return connection;
        }

    }
}