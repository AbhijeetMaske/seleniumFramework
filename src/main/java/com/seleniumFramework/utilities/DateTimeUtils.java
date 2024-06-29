package com.seleniumFramework.utilities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DateTimeUtils {
	private static final Logger logger = LogManager.getLogger(DateTimeUtils.class);
	/********************************************************************************************
     * Gets the current date in the format "yyyy-MM-dd".
     *
     * @return The current date as a string.
     * @author Abhijeet Maske Created June 27,2023
     * @version 1.0 June 27,2023
     ********************************************************************************************/
    public static String getCurrentDate() {
        LocalDate currentDate = LocalDate.now();
        return currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /********************************************************************************************
     * Gets the current date and time in the format "yyyy-MM-dd HH:mm:ss".
     *
     * @return The current date and time as a string.
     * 
     * @author Abhijeet Maske Created June 27,2023
     * @version 1.0 June 27,2023
     ********************************************************************************************/
    public static String getCurrentDateTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        return currentDateTime.format(DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss"));
    }

    /********************************************************************************************
     * Formats a given LocalDateTime object to a string with the given pattern.
     *
     * @param dateTime The LocalDateTime object to format.
     * @param pattern  The pattern to format the date and time.
     * @return The formatted date and time as a string.
     * 
     * @author Abhijeet Maske Created June 27,2023
     * @version 1.0 June 27,2023
     ********************************************************************************************/
    public static String formatDateTime(LocalDateTime dateTime, String pattern) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            return dateTime.format(formatter);
        } catch (Exception e) {
        	logger.error("Error occurred while formatting DateTime: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    /********************************************************************************************
     * Parses a string into a LocalDateTime object based on the given pattern.
     *
     * @param dateTimeStr The date and time string to parse.
     * @param pattern     The pattern to parse the date and time.
     * @return The parsed LocalDateTime object, or null if parsing fails.
     * 
     * @author Abhijeet Maske Created June 27,2023
     * @version 1.0 June 27,2023
     ********************************************************************************************/
    public static LocalDateTime parseDateTime(String dateTimeStr, String pattern) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            return LocalDateTime.parse(dateTimeStr, formatter);
        } catch (Exception e) {
        	 logger.error("Error occurred while parsing DateTime: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /********************************************************************************************
     * Calculates the difference between two LocalDateTime objects in the given unit.
     *
     * @param startDateTime The start date and time.
     * @param endDateTime   The end date and time.
     * @param unit          The unit to measure the difference (e.g., ChronoUnit.DAYS).
     * @return The difference between the two dates and times in the given unit.
     * 
     * @author Abhijeet Maske Created June 27,2023
     * @version 1.0 June 27,2023
     ********************************************************************************************/
    public static long calculateDifference(LocalDateTime startDateTime, LocalDateTime endDateTime, ChronoUnit unit) {
        try {
            return unit.between(startDateTime, endDateTime);
        } catch (Exception e) {
        	logger.error("Error occurred while calculating DateTime difference: " + e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }
}
