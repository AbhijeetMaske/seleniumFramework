package com.seleniumFramework.utilities;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 * Utility class containing various helper methods.
 */
public class UtilMethods {
	private static final Logger logger = LogManager.getLogger(UtilMethods.class);

	/********************************************************************************************
	 * Captures a screenshot of the current WebDriver instance.
	 *
	 * @param driver   The WebDriver instance from which to capture the screenshot.
	 * @param testName The name of the test for which the screenshot is captured.
	 * @return The file path where the screenshot is saved.
	 * @throws IOException If an I/O error occurs while capturing or saving the
	 *                     screenshot.
	 *
	 * @author Abhijeet Maske Created June 27,2023
	 * @version 1.0 June 27,2023
	 ********************************************************************************************/
	public static synchronized String captureScreenShot(WebDriver driver, String testName) throws IOException {
		// Convert WebDriver object to TakesScreenshot interface
		TakesScreenshot screenshot = (TakesScreenshot) driver;

		// Call getScreenshotAs method to create an image file
		File src = screenshot.getScreenshotAs(OutputType.FILE);

		// Get the current working directory
		String userDir = System.getProperty("user.dir");
		// Construct the path to the screenshots directory
		String destPath = userDir + "\\screenshots\\" + testName + ".png";
		File dest = new File(destPath);

		try {
			// Copy image file to the destination
			FileUtils.copyFile(src, dest);
			logger.info("Screenshot captured for test: {} at path: {}", testName, destPath);
		} catch (IOException e) {
			logger.error("Failed to capture screenshot for test: {}", testName, e);
			throw e;
		}

		return destPath;
	}
}