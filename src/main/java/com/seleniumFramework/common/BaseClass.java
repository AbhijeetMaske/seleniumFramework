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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

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
		try {
            setupDriver(this.browser);
            getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            logger.info("Initialized WebDriver before suite with browser: " + this.browser);
        } catch (Exception e) {
            logger.error("Error during initializing WebDriver before suite: " + e.getMessage(), e);
            throw e;
        }
    }
	
	private void setupDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setBrowserVersion("126");
                //chromeOptions.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(chromeOptions);
                break;
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                driver = new FirefoxDriver(firefoxOptions);
                break;
            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                driver = new EdgeDriver(edgeOptions);
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        driverObject.set(driver);
    }

	@BeforeMethod
	public void setup(Method method) {
		logger = LogManager.getLogger(method.getDeclaringClass());
		logger.info("@Before Method: " + method.getName());

		if (getDriver() == null) {
			setupSuite(url, browser);
		}
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
