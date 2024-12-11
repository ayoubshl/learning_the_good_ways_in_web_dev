package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Hard-coded database connection details
                String url = "jdbc:mysql://localhost:3306/EMS";
                String username = "root"; // Replace with your DB username
                String password = ""; // Replace with your DB password

                // Establish connection
                connection = DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
