package com.seleniumFramework.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
// [2025-08-23] Use NIO for cross-platform paths
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Utility class to read configurations from a properties file.
 */

public class ReadConfig {
	
	private static final Logger logger = LogManager.getLogger(ReadConfig.class);
    Properties properties;
    
    /********************************************************************************************
     * Constructor to load properties from the config file.
     *
     * @author Abhijeet Maske Created June 27,2023
     * @version 1.0 June 27,2023
     ********************************************************************************************/
    public ReadConfig() {
        // Get the current working directory
        String userDir = System.getProperty("user.dir");
        // [2025-08-23] Construct cross-platform path to the config.properties file
        Path configPath = Paths.get(userDir, "Configuration", "config.properties");
        logger.info("Loading configuration from: {}", configPath);
        properties = new Properties();
        try {
        	if (Files.exists(configPath)) {
        		try (FileInputStream fis = new FileInputStream(configPath.toFile())) {
        			properties.load(fis);
        		}
        		logger.info("Configuration loaded successfully from: {}", configPath);
        	} else {
        		logger.warn("Configuration file not found at: {}. Proceeding with defaults and suite parameters.", configPath);
        	}
        } catch (IOException e) {
        	logger.error("Error loading config properties from {}: {}", configPath, e.getMessage(), e);
        }
    }

    /********************************************************************************************
     * Gets the base URL from the properties file.
     *
     * @return The base URL.
     *
     * @author Abhijeet Maske Created June 27,2023
     * @version 1.0 June 27,2023
     ********************************************************************************************/
    public String getBaseUrl() {
    	String baseUrl = properties.getProperty("baseUrl");
        logger.info("Base URL retrieved: {}", baseUrl);
        return baseUrl;
    }

    /********************************************************************************************
     * Gets the browser type from the properties file.
     *
     * @return The browser type.
     *
     * @author Abhijeet Maske Created June 27,2023
     * @version 1.0 June 27,2023
     ********************************************************************************************/
    public String getBrowser() {
    	String browser = properties.getProperty("browser");
        logger.info("Browser type retrieved: {}", browser);
        return browser;
    }
}
