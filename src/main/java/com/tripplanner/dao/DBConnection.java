package com.tripplanner.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/tripplanner"; // Updated database ID
    private static final String USER = "root"; // Username is root
    private static final String PASSWORD = "root"; // Update password

    // Private constructor to prevent instantiation
    private DBConnection() {}

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Properties properties = new Properties();
            properties.put("user", USER);
            properties.put("password", PASSWORD);
            connection = DriverManager.getConnection(URL, properties);
        } catch (ClassNotFoundException e) {
            System.err.println("Database Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Connection failed: " + e.getMessage());
        }
        return connection;
    }
}