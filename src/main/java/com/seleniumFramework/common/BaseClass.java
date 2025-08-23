package com.seleniumFramework.common;

import java.lang.reflect.Method;
import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.seleniumFramework.utilities.ReadConfig;
// [2025-08-23] Added WebDriverManager for reliable driver binaries
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	ReadConfig readConfig = new ReadConfig();

	protected String url;
	protected String browser;
	protected String browserVersion;
	
	// [2025-08-23] Removed static shared driver; use ThreadLocal exclusively for test isolation
	protected static Logger logger;	
	public static ThreadLocal<WebDriver> driverObject = new ThreadLocal<WebDriver>();

	public static WebDriver getDriver() {
		return driverObject.get();
	}

	@Parameters({ "baseUrl", "browser" })
	@BeforeSuite
	public void setupSuite(@Optional String baseUrl, @Optional String browser) {
		logger = LogManager.getLogger(BaseClass.class);
		this.url = baseUrl != null ? baseUrl : readConfig.getBaseUrl();
		this.browser = browser != null ? browser : readConfig.getBrowser();

		if (this.url == null || this.browser == null) {
			String errorMessage = "Base URL or browser not provided in XML or config file.";
			logger.error(errorMessage);
			throw new IllegalArgumentException(errorMessage);
		}
		// [2025-08-23] Moved WebDriver initialization to @BeforeMethod for per-test isolation
	}
	
	// [2025-08-23] Initialize driver per test method
	@BeforeMethod
	public void setup(Method method) {
		logger = LogManager.getLogger(method.getDeclaringClass());
		logger.info("Executing test method: {}", method.getName());
		try {
			setupDriver(this.browser);
			// getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // [2025-08-23] Removed implicit waits; prefer explicit waits
			logger.info("WebDriver initialized for method: {} with browser: {}", method.getName(), this.browser);
		} catch (Exception e) {
			logger.error("Error during WebDriver setup: {}", e.getMessage(), e);
			throw e;
		}
	}
	
	private void setupDriver(String browser) {
		switch (browser.toLowerCase()) {
			case "chrome":
				// [2025-08-23] Setup driver binary via WebDriverManager and remove pinned version
				WebDriverManager.chromedriver().setup();
				ChromeOptions chromeOptions = new ChromeOptions();
				// chromeOptions.addArguments("--remote-allow-origins=*");
				driverObject.set(new ChromeDriver(chromeOptions));
				break;
			case "firefox":
				WebDriverManager.firefoxdriver().setup();
				FirefoxOptions firefoxOptions = new FirefoxOptions();
				driverObject.set(new FirefoxDriver(firefoxOptions));
				break;
			case "edge":
				WebDriverManager.edgedriver().setup();
				EdgeOptions edgeOptions = new EdgeOptions();
				driverObject.set(new EdgeDriver(edgeOptions));
				break;
			default:
				throw new IllegalArgumentException("Unsupported browser: " + browser);
		}
	}

	 @AfterMethod
	    public void afterMethod(Method method) {
	    	logger.info("Completed test method: {}", method.getName());
	    	// [2025-08-23] Ensure driver is closed after each test for isolation
	    	try {
	    		if (getDriver() != null) {
	    			getDriver().quit();
	    			driverObject.remove();
	    			logger.info("WebDriver session ended successfully.");
	    		}
	    	} catch (Exception e) {
	    		logger.error("Error during WebDriver teardown: {}", e.getMessage(), e);
	    	}
	    }
	
	@AfterSuite
	public void tearDown() {
		// [2025-08-23] Suite-level safeguard; primary teardown happens in @AfterMethod
		try {
			if (getDriver() != null) {
				getDriver().quit();
				driverObject.remove();
				logger.info("WebDriver closed at suite end.");
			}
		} catch (Exception e) {
			logger.error("Error during WebDriver teardown at suite end: {}", e.getMessage(), e);
		}
	}

}
