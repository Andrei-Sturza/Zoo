package org.example;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/project_database"; // Replace with your DB name
    private static final String USERNAME = "admin_user"; // Replace with your DB username
    private static final String PASSWORD = "admin_password"; // Replace with your DB password

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}

