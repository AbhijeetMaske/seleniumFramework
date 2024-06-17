package com.seleniumFramework.utilities;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Logger utility class for logging application behavior and troubleshooting.
 */
public class LoggerUtils {

    private static final Logger logger = LogManager.getLogger(LoggerUtils.class);

    /********************************************************************************************
     * Logs an info message.
     *
     * @param message The message to be logged.
     *
     * @author Abhijeet Maske Created June 27,2023
     * @version 1.0 June 27,2023
     ********************************************************************************************/
    public static void info(String message) {
        logger.info(message);
    }

    /********************************************************************************************
     * Logs a debug message.
     *
     * @param message The message to be logged.
     *
     * @author Abhijeet Maske Created June 27,2023
     * @version 1.0 June 27,2023
     ********************************************************************************************/
    public static void debug(String message) {
        logger.debug(message);
    }

    /********************************************************************************************
     * Logs an error message.
     *
     * @param message The message to be logged.
     *
     * @author Abhijeet Maske Created June 27,2023
     * @version 1.0 June 27,2023
     ********************************************************************************************/
    public static void error(String message) {
        logger.error(message);
    }

    /********************************************************************************************
     * Logs a warning message.
     *
     * @param message The message to be logged.
     *
     * @author Abhijeet Maske Created June 27,2023
     * @version 1.0 June 27,2023
     ********************************************************************************************/
    public static void warn(String message) {
        logger.warn(message);
    }
    
    /********************************************************************************************
     * Logs a fatal message.
     *
     * @param message The message to be logged.
     *
     * @author Abhijeet Maske Created June 27,2023
     * @version 1.0 June 27,2023
     ********************************************************************************************/
    public static void fatal(String message) {
        logger.fatal(message);
    }

    /********************************************************************************************
     * Logs a trace message.
     *
     * @param message The message to be logged.
     *
     * @author Abhijeet Maske Created June 27,2023
     * @version 1.0 June 27,2023
     ********************************************************************************************/
    public static void trace(String message) {
        logger.trace(message);
    }
}