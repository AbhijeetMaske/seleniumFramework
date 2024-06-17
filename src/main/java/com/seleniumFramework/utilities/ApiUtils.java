package com.seleniumFramework.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.restassured.RestAssured;
import io.restassured.response.Response;

/**
 * Utility class for making API requests using RestAssured.
 */

public class ApiUtils {
	private static final Logger logger = LogManager.getLogger(ApiUtils.class);
	/********************************************************************************************
     * Makes a GET request to the specified URL.
     * 
     * @param url The URL to send the GET request to.
     * @return The response from the GET request.
     * 
     * @author Abhijeet Maske Created June 27,2023
     * @version 1.0 June 27,2023
     ********************************************************************************************/
    public static Response get(String url) {
        try {
        	logger.info("Sending GET request to: " + url);
            return RestAssured.get(url);
        } catch (Exception e) {
        	logger.error("Error while sending GET request to: " + url, e);
            e.printStackTrace();
            return null;
        }
    }

    /********************************************************************************************
     * Makes a POST request to the specified URL with the given body.
     * 
     * @param url The URL to send the POST request to.
     * @param body The body of the POST request.
     * @return The response from the POST request.
     * 
     * @author Abhijeet Maske Created June 27,2023
     * @version 1.0 June 27,2023
     ********************************************************************************************/
    public static Response post(String url, String body) {
        try {
        	logger.info("Sending POST request to: " + url);
            return RestAssured.given().body(body).post(url);
        } catch (Exception e) {
        	logger.error("Error while sending POST request to: " + url, e);
            e.printStackTrace();
            return null;
        }
    }
}
