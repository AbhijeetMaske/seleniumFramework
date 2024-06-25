package com.seleniumFramework.common;

import java.lang.reflect.Method;
import java.time.Duration;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.*;

import com.seleniumFramework.utilities.BrowserUtils;
import com.seleniumFramework.utilities.ReadConfig;

public class BaseClass {

	ReadConfig readConfig = new ReadConfig();

	protected String url;
	String browser;
	public static WebDriver driver;
	protected static Logger logger;
	protected String browserVersion;
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
		System.out.println("base URL: " + baseUrl);

		try {
			switch (browser.toLowerCase()) {
			case "chrome":

				ChromeOptions option = new ChromeOptions();
				option.setBrowserVersion("126");
				driver = new ChromeDriver(option);
				break;
			case "edge":
				EdgeOptions edgeOptions = new EdgeOptions();
				edgeOptions.setBrowserVersion("126");
				driver = new EdgeDriver(edgeOptions);
				break;
			case "firefox":
				FirefoxOptions firefoxOptions = new FirefoxOptions();
				firefoxOptions.setBrowserVersion("108");
				driver = new FirefoxDriver(firefoxOptions);
				break;
			default:
				throw new IllegalArgumentException("Unsupported browser: " + this.browser);
			}
			driverObject.set(driver);
			getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			logger.info("Initialized WebDriver before suite with browser: " + this.browser);

		} catch (Exception e) {
			logger.error("Error during initializing WebDriver before suite: " + e.getMessage(), e);
			throw e;
		}
	}

	@BeforeMethod
	public void setup(Method method) {
		logger = LogManager.getLogger(method.getDeclaringClass());
		logger.info("@Before Method: " + method.getName());

		if (getDriver() == null) {
			setupSuite(url, browser);
		}

		// Use parameters from XML if provided, otherwise fall back to config file
//		try {
//			switch (browser.toLowerCase()) {
//			case "chrome":
//				ChromeOptions option = new ChromeOptions();
//				option.setBrowserVersion("126");
//				driver = new ChromeDriver(option);
//				logger.info("Initialized WebDriver in method");
//				break;
//			default:
//				throw new IllegalArgumentException("Unsupported browser: " + this.browser);
//			}
//			driverObject.set(driver);
//			// implicit wait of 10 second
//			getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//			logger.info("Initialized WebDriver for method: " + method.getName() + " with browser: " + this.browser);
//
//		} catch (Exception e) {
//			logger.error("Error during WebDriver setup: " + e.getMessage(), e);
//			throw e;
//		}

		try {
			logger.info("Initialized WebDriver for method: " + method.getName() + " with browser: " + this.browser);
		} catch (Exception e) {
			logger.error("Error during WebDriver setup: " + e.getMessage(), e);
			throw e;
		}
	}

	@AfterMethod
	public void tearDown(Method method) {
		logger.info("@After Method: " + method.getName());
		try {
			if (getDriver() != null) {
				getDriver().close();
				getDriver().quit();
				driverObject.remove();
			}
			logger.info("Torn down WebDriver for method: " + method.getName());
		} catch (Exception e) {
			logger.error("Error during WebDriver teardown: " + e.getMessage(), e);
		}
	}

}
