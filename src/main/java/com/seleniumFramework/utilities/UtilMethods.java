package com.seleniumFramework.utilities;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class UtilMethods {

    public static synchronized String captureScreenShot(WebDriver driver, String testName) throws IOException {
        // Convert WebDriver object to TakesScreenshot interface
        TakesScreenshot screenshot = ((TakesScreenshot) driver);

        // Call getScreenshotAs method to create an image file
        File src = screenshot.getScreenshotAs(OutputType.FILE);

        // Get the current working directory
        String userDir = System.getProperty("user.dir");
        // Construct the path to the screenshots directory
        String destPath = userDir + "\\screenshots\\" + testName + ".png";
        File dest = new File(destPath);

        // Copy image file to the destination
        FileUtils.copyFile(src, dest);
        return destPath;
    }
}
