package com.seleniumFramework.utilities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateTimeUtils {

    /**
     * Gets the current date in the format "yyyy-MM-dd".
     *
     * @return The current date as a string.
     */
    public static String getCurrentDate() {
        LocalDate currentDate = LocalDate.now();
        return currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Gets the current date and time in the format "yyyy-MM-dd HH:mm:ss".
     *
     * @return The current date and time as a string.
     */
    public static String getCurrentDateTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        return currentDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * Formats a given LocalDateTime object to a string with the given pattern.
     *
     * @param dateTime The LocalDateTime object to format.
     * @param pattern  The pattern to format the date and time.
     * @return The formatted date and time as a string.
     */
    public static String formatDateTime(LocalDateTime dateTime, String pattern) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            return dateTime.format(formatter);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Parses a string into a LocalDateTime object based on the given pattern.
     *
     * @param dateTimeStr The date and time string to parse.
     * @param pattern     The pattern to parse the date and time.
     * @return The parsed LocalDateTime object, or null if parsing fails.
     */
    public static LocalDateTime parseDateTime(String dateTimeStr, String pattern) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            return LocalDateTime.parse(dateTimeStr, formatter);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Calculates the difference between two LocalDateTime objects in the given unit.
     *
     * @param startDateTime The start date and time.
     * @param endDateTime   The end date and time.
     * @param unit          The unit to measure the difference (e.g., ChronoUnit.DAYS).
     * @return The difference between the two dates and times in the given unit.
     */
    public static long calculateDifference(LocalDateTime startDateTime, LocalDateTime endDateTime, ChronoUnit unit) {
        try {
            return unit.between(startDateTime, endDateTime);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
