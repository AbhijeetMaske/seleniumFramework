package com.seleniumFramework.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Logger utility class for logging application behavior and troubleshooting.
 */
public class LoggerUtils {

    private static final Logger logger = LogManager.getLogger(LoggerUtils.class);

    /**
     * Logs an info message.
     * @param message The message to be logged.
     */
    public static void info(String message) {
        logger.info(message);
    }

    /**
     * Logs a debug message.
     * @param message The message to be logged.
     */
    public static void debug(String message) {
        logger.debug(message);
    }

    /**
     * Logs an error message.
     * @param message The message to be logged.
     */
    public static void error(String message) {
        logger.error(message);
    }

    /**
     * Logs a warning message.
     * @param message The message to be logged.
     */
    public static void warn(String message) {
        logger.warn(message);
    }
}