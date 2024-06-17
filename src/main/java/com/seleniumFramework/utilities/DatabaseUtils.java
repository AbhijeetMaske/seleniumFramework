package com.seleniumFramework.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Utility class for database operations.
 */

public class DatabaseUtils {

    private static Connection connection;
 
    /********************************************************************************************
     * Connects to the database with the provided URL, user, and password.
     * 
     * @param url      the database URL
     * @param user     the database user
     * @param password the database password
     * 
     * @author Abhijeet Maske Created June 27,2023
     * @version 1.0 June 27,2023
     ********************************************************************************************/
    public static void connectToDatabase(String url, String user, String password) {
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection to the database established successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /********************************************************************************************
     * Executes the given SQL query and returns the result set.
     * 
     * @param query the SQL query to execute
     * @return the result set of the query
     * 
     * @author Abhijeet Maske Created June 27,2023
     * @version 1.0 June 27,2023
     ********************************************************************************************/
    public static ResultSet executeQuery(String query) {
        try {
        	if (connection == null || connection.isClosed()) {
                System.err.println("No active database connection.");
                return null;
            }
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);
        } catch (SQLException e) {
        	System.err.println("Failed to execute query: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Closes the database connection if it is open.
     */
    
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed successfully.");
            }
        } catch (SQLException e) {
        	System.err.println("Failed to close the database connection: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
