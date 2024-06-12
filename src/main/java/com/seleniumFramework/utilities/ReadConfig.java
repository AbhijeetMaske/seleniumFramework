package com.seleniumFramework.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {

    Properties properties;

    // Constructor to load properties from the config file
    public ReadConfig() {
        // Get the current working directory
        String userDir = System.getProperty("user.dir");
        // Construct the path to the config.properties file
        String path = userDir + "\\Configuration\\config.properties";

        try {
            FileInputStream fis = new FileInputStream(path);
            properties = new Properties();
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to get the base URL from the properties file
    public String getBaseUrl() {
        return properties.getProperty("baseUrl");
    }

    // Method to get the browser type from the properties file
    public String getBrowser() {
        return properties.getProperty("browser");
    }
}
