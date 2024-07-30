package com.seleniumFramework.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

/**
 * CustomAssertions is a utility class that provides custom assertion methods
 * with detailed error messages to improve the clarity of test failures.
 */
public class CustomAssertions {
	private static final Logger logger = LogManager.getLogger(ApiUtils.class);
	/********************************************************************************************
     * Asserts that two strings are equal. If they are not, an AssertionError is thrown.
     * 
     * @param actual   The actual string value.
     * @param expected The expected string value.
     * @param message  The message to display if the assertion fails.
     * 
     * @author Abhijeet Maske Created June 27,2023
     * @version 1.0 June 27,2023
     ********************************************************************************************/
	public static void assertEquals(String actual, String expected, String message) {
		try {
			Assert.assertEquals(actual, expected, message);
		} catch (AssertionError e) {
			logger.error("Assertion Failed: {}", message);
            logger.error("Expected: {}, Actual: {}", expected, actual);
			throw e;
		}
	}

	/********************************************************************************************
     * Asserts that two integers are equal. If they are not, an AssertionError is thrown.
     * 
     * @param actual   The actual integer value
     * @param expected The expected integer value.
     * @param message  The message to display if the assertion fails.
     * 
     * @author Abhijeet Maske Created June 27,2023
     * @version 1.0 June 27,2023
     ********************************************************************************************/
	public static void assertEquals(int actual, int expected, String message) {
		try {
			Assert.assertEquals(actual, expected, message);
		} catch (AssertionError e) {
			logger.error("Assertion Failed: {}", message);
            logger.error("Expected: {}, Actual: {}", expected, actual);
			throw e;
		}
	}

	/********************************************************************************************
     * Asserts that two booleans are equal. If they are not, an AssertionError is thrown.
     * 
     * @param actual   The actual boolean value
     * @param expected The expected boolean value.
     * @param message  The message to display if the assertion fails.
     * 
     * @author Abhijeet Maske Created June 27,2023
     * @version 1.0 June 27,2023
     ********************************************************************************************/
	public static void assertEquals(boolean actual, boolean expected, String message) {
		try {
			Assert.assertEquals(actual, expected, message);
		} catch (AssertionError e) {
			logger.error("Assertion Failed: {}", message);
            logger.error("Expected: {}, Actual: {}", expected, actual);
			throw e;
		}
	}

	/********************************************************************************************
     * Asserts that a condition is true. If it isn't, an AssertionError is thrown.
     * 
     * @param actual   The actual boolean value
     * @param expected The expected boolean value.
     * @param message  The message to display if the assertion fails.
     * 
     * @author Abhijeet Maske Created June 27,2023
     * @version 1.0 June 27,2023
     ********************************************************************************************/
	public static void assertTrue(boolean condition, String message) {
		try {
			Assert.assertTrue(condition, message);
		} catch (AssertionError e) {
			System.err.println("Assertion Failed: " + message);
			throw e;
		}
	}

	/********************************************************************************************
     * Asserts that a condition is false. If it isn't, an AssertionError is thrown.
     * 
     * @param condition The condition to evaluate.
     * @param message   The message to display if the assertion fails.
     * 
     * @author Abhijeet Maske Created June 27,2023
     * @version 1.0 June 27,2023
     ********************************************************************************************/
	public static void assertFalse(boolean condition, String message) {
		try {
			Assert.assertFalse(condition, message);
		} catch (AssertionError e) {
			logger.error("Assertion Failed: {}", message);
			throw e;
		}
	}

	/********************************************************************************************
     * Asserts that an object is null. If it isn't, an AssertionError is thrown.
     * 
     * @param object  The object to check for nullity.
     * @param message The message to display if the assertion fails.
     * 
     * @author Abhijeet Maske Created June 27,2023
     * @version 1.0 June 27,2023
     ********************************************************************************************/
	public static void assertNull(Object object, String message) {
		try {
			Assert.assertNull(object, message);
		} catch (AssertionError e) {
			logger.error("Assertion Failed: {}", message);
            logger.error("Object was expected to be null, but it was: {}", object);
			throw e;
		}
	}

	/********************************************************************************************
     * Asserts that an object is not null. If it is, an AssertionError is thrown.
     * 
     * @param object  The object to check for non-nullity.
     * @param message The message to display if the assertion fails.
     * 
     * @author Abhijeet Maske Created June 27,2023
     * @version 1.0 June 27,2023
     ********************************************************************************************/
	public static void assertNotNull(Object object, String message) {
		try {
			Assert.assertNotNull(object, message);
		} catch (AssertionError e) {
			logger.error("Assertion Failed: {}", message);
            logger.error("Object was expected to be non-null, but it was null.");
			throw e;
		}
	}

	/********************************************************************************************
     * Asserts that two objects are equal. If they are not, an AssertionError is thrown.
     * 
     * @param actual   The actual object.
     * @param expected The expected object.
     * @param message  The message to display if the assertion fails.
     * 
     * @author Abhijeet Maske Created June 27,2023
     * @version 1.0 June 27,2023
     ********************************************************************************************/
	public static void assertEquals(Object actual, Object expected, String message) {
		try {
			Assert.assertEquals(actual, expected, message);
		} catch (AssertionError e) {
			logger.error("Assertion Failed: {}", message);
            logger.error("Expected: {}, Actual: {}", expected, actual);
			throw e;
		}
	}
}