package com.seleniumFramework.utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BrowserUtils {
	private static final Logger logger = LogManager.getLogger(BrowserUtils.class);

	/**
     * Retrieves the version of the Chrome browser.
     * 
     * @return The version of the Chrome browser.
     */
	
    public static String getChromeBrowserVersion() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        WebDriver driver = new ChromeDriver(options);
        return getBrowserVersion(driver, "Chrome/");
    }
    
    /**
     * Retrieves the version of the Edge browser.
     * 
     * @return The version of the Edge browser.
     */
    public static String getEdgeBrowserVersion() {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        WebDriver driver = new EdgeDriver(options);
        return getBrowserVersion(driver, "Edg/");
    }
    
    /**
     * Retrieves the version of the Firefox browser.
     * 
     * @return The version of the Firefox browser.
     */
    public static String getFirefoxBrowserVersion() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        WebDriver driver = new FirefoxDriver(options);
        return getBrowserVersion(driver, "Firefox/");
    }

//    public static String getOperaBrowserVersion() {
//        WebDriverManager.operadriver().setup();
//        OperaOptions options = new OperaOptions();
//        WebDriver driver = new OperaDriver(options);
//        return getBrowserVersion(driver, "OPR/");
//    }

    private static String getBrowserVersion(WebDriver driver, String browserIdentifier) {
        String version = "";
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            version = (String) js.executeScript("return navigator.userAgent;");
            if (version.contains(browserIdentifier)) {
                version = version.substring(version.indexOf(browserIdentifier) + browserIdentifier.length());
                version = version.split(" ")[0];
            }
        } finally {
            driver.quit();
        }
        return version;
    }
    /**
     * Configures Chrome browser options.
     * 
     * @return The configured ChromeOptions object.
     */
    
    public static void main(String[] args) {
        System.out.println("Chrome Version: " + getChromeBrowserVersion());
        System.out.println("Edge Version: " + getEdgeBrowserVersion());
        System.out.println("Firefox Version: " + getFirefoxBrowserVersion());
        //System.out.println("Opera Version: " + getOperaBrowserVersion());
    }
    
    /********************************************************************************************
     * Configures Chrome browser options.
     * 
     * @return the configured ChromeOptions object
     * 
     * @author Abhijeet Maske Created June 27,2023
     * @version 1.0 June 27,2023
     ********************************************************************************************/
    public static ChromeOptions configureChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-notifications");
        //options.addArguments("--headless");
        options.addArguments("--window-size=540,720");
        // Add other Chrome-specific options here
        logger.info("Chrome options configured");
        return options;
    }

    /********************************************************************************************
     * Configures Firefox browser options.
     * 
     * @return the configured FirefoxOptions object
     * 
     * @author Abhijeet Maske Created June 27,2023
     * @version 1.0 June 27,2023
     ********************************************************************************************/
    public static FirefoxOptions configureFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.addPreference("dom.webnotifications.enabled", false);
        options.addPreference("privacy.popups.showBrowserMessage", false);
        //options.setHeadless(true);
        // Add other Firefox-specific options here
        logger.info("Firefox options configured");
        return options;
    }

    /********************************************************************************************
     * Configures Edge browser options.
     * 
     * @return the configured EdgeOptions object
     * 
     * @author Abhijeet Maske Created June 27,2023
     * @version 1.0 June 27,2023
     ********************************************************************************************/
    public static EdgeOptions configureEdgeOptions() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-notifications");
        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");
        // Add other Edge-specific options here
        logger.info("Edge options configured");
        return options;
    }
}