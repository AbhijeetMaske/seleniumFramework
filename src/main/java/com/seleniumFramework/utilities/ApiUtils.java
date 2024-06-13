package com.seleniumFramework.utilities;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ApiUtils {

    /**
     * Makes a GET request to the specified URL.
     *
     * @param url The URL to send the GET request to.
     * @return The response from the GET request.
     */
    public static Response get(String url) {
        try {
            return RestAssured.get(url);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Makes a POST request to the specified URL with the given body.
     *
     * @param url The URL to send the POST request to.
     * @param body The body of the POST request.
     * @return The response from the POST request.
     */
    public static Response post(String url, String body) {
        try {
            return RestAssured.given().body(body).post(url);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
