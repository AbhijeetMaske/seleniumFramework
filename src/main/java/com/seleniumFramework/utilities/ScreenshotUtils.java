package com.seleniumFramework.utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Utility class to capture screenshots during test execution.
 */

public class ScreenshotUtils {

	private static final Logger logger = LogManager.getLogger(ScreenshotUtils.class);
	
	/********************************************************************************************
     * Captures a screenshot of the current WebDriver instance.
     *
     * @param driver   The WebDriver instance from which to capture the screenshot.
     * @param testName The name of the test for which the screenshot is captured.
     * @return The file path where the screenshot is saved.
     *
     * @author Abhijeet Maske Created June 27,2023
     * @version 1.0 June 27,2023
     ********************************************************************************************/
    public static synchronized String captureScreenShot(WebDriver driver, String testName) {
        String filePath = System.getProperty("user.dir") + "//screenshots//" + testName + ".png";
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(src, new File(filePath));
            logger.info("Screenshot captured: " + filePath);
        } catch (IOException e) {
        	logger.error("Failed to capture screenshot: " + e.getMessage());
            e.printStackTrace();
        }
        return filePath;
    }
}
