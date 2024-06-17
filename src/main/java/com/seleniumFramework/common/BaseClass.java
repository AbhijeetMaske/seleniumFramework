package com.seleniumFramework.common;

import java.io.File;
import java.lang.reflect.Method;
import java.time.Duration;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.seleniumFramework.utilities.BrowserUtils;
import com.seleniumFramework.utilities.ScreenshotUtils;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Base class for setting up and tearing down WebDriver instances for tests.
 */
public class BaseClass {

	protected String url;
	protected String browser;
	protected String browserVersion;
	protected static WebDriver driver;
	protected static Logger logger;
	private static ThreadLocal<WebDriver> driverObject = new ThreadLocal<>();
	
	
	public static void initializeLogs() {
        File logDir = new File("logs");
        if (!logDir.exists()) {
            logDir.mkdir();
        }
    }
	
	static {
        initializeLogs(); // Ensure logs directory exists
    }

	/********************************************************************************************
	 * Gets the current WebDriver instance for the thread.
	 * 
	 * @return the WebDriver instance
	 * 
	 *@author Abhijeet Maske Created June 27,2023
	 *@version 1.0 June 27,2023
	 ********************************************************************************************/
	public static WebDriver getDriver() {
		return driverObject.get();
	}

	/********************************************************************************************
	 * Sets up the test suite by configuring the base URL and browser.
	 * @param baseUrl the base URL for the tests
	 * param browser the browser to be used for the tests
	 * 
	 *@author Abhijeet Maske Created June 27,2023
	 *@version 1.0 June 27,2023
	 ********************************************************************************************/
	@Parameters({ "baseUrl", "browser" })
	@BeforeSuite
	public void setupSuite(String baseUrl, String browser) {
		this.url = baseUrl;
		this.browser = browser;

		// Get the browser version
		switch (browser.toLowerCase()) {
		case "chrome":
			browserVersion = BrowserUtils.getChromeBrowserVersion();
			break;
		case "firefox":
			browserVersion = BrowserUtils.getFirefoxBrowserVersion();
			break;
		case "edge":
			browserVersion = BrowserUtils.getEdgeBrowserVersion();
			break;
		default:
			 String errorMessage = "Unsupported browser: " + browser;
             logger.error(errorMessage);
			throw new IllegalArgumentException(errorMessage);
			
		}
		System.out.println("Using " + browser + " version: " + browserVersion);
	}

	/********************************************************************************************
	 * Sets up the WebDriver instance before each test method.
	 * @param method the test method
	 * 
	 *@author Abhijeet Maske Created June 27,2023
	 *@version 1.0 June 27,2023
	 ********************************************************************************************/
	
	public void setup(Method method) {
		logger = LogManager.getLogger(method.getDeclaringClass());
		logger.info("Starting test method: " + method.getName());

		switch (browser.toLowerCase()) {
        case "chrome":
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(BrowserUtils.configureChromeOptions());
            break;
        case "firefox":
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver(BrowserUtils.configureFirefoxOptions());
            break;
        case "edge":
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver(BrowserUtils.configureEdgeOptions());
            break;
        default:
            throw new IllegalArgumentException("Unsupported browser: " + browser);
    }
		driverObject.set(driver);

		// Set timeouts
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));

		logger.info("WebDriver setup complete for method: " + method.getName());
	}
	
	/********************************************************************************************
	 * Tears down the WebDriver instance after each test method.
	 * @param method the test method
	 * 
	 *@author Abhijeet Maske Created June 27,2023
	 *@version 1.0 June 27,2023
	 ********************************************************************************************/	
	@AfterMethod
	public void tearDown(Method method, ITestResult result) {
		logger.info("Tearing down test method: " + method.getName());
		if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotPath = ScreenshotUtils.captureScreenShot(getDriver(), method.getName());
            logger.error("Test failed. Screenshot saved at: " + screenshotPath);
        }
		
		if (getDriver() != null) {
			try {
				getDriver().close();
				getDriver().quit();
				driverObject.remove();
			} catch (Exception e) {
				logger.error("Error while closing WebDriver: " + e.getMessage(), e);
			}
		}
	}
}
