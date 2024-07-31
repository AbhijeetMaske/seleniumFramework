package com.seleniumFramework.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Utility class for database operations.
 */

public class DatabaseUtils {

	private static final Logger logger = LogManager.getLogger(DatabaseUtils.class);
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
			logger.info("Connection to the database established successfully.");
		} catch (SQLException e) {
			logger.error("Failed to connect to the database: {}", e.getMessage(), e);
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
				logger.error("No active database connection.");
				return null;
			}
			Statement statement = connection.createStatement();
			logger.info("Executing query: {}", query);
			return statement.executeQuery(query);
		} catch (SQLException e) {
			logger.error("Failed to execute query: {}", e.getMessage(), e);
			return null;
		}
	}

	/********************************************************************************************
	 * Closes the database connection if it is open.
	 * 
	 * @version 1.0 June 27,2023
	 ********************************************************************************************/

	public static void closeConnection() {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
				logger.info("Database connection closed successfully.");
			}
		} catch (SQLException e) {
			logger.error("Failed to close the database connection: {}", e.getMessage(), e);
		}
	}
}
