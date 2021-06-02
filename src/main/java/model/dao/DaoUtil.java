package model.dao;

import java.sql.*;

public class DaoUtil {

    private final String driver = "jdbc:sqlite:test.db";
    Connection connection = null;

    public DaoUtil() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(driver);
        } catch (Exception e) {
            System.err.println("Database connection failed!");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Database connected successfully!");
    }

    public Connection getDatabaseConnection() {
        return connection;
    }

    public void closeDatabaseConnection(Connection connection) throws SQLException {
        if (connection != null) {
            connection.close();
        } else {
            System.out.println("The connection is null");
        }
    }


}
