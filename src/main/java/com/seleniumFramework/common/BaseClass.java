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
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	protected String url;
	protected String browser;
	protected String browserVersion;

	public static WebDriver driver;
	public static Logger logger;
	public static ThreadLocal<WebDriver> driverObject = new ThreadLocal<WebDriver>();

	public static WebDriver getDriver() {
		return driverObject.get();
	}

	// From XML file
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
//            case "opera":
//                browserVersion = BrowserUtils.getOperaBrowserVersion();
//                break;
		default:
			throw new IllegalArgumentException("Unsupported browser: " + browser);
		}
		System.out.println(browser + " version: " + browserVersion);
	}

	@BeforeMethod
	public void setup(Method method) {
		System.out.println("@Before Method: " + method.getName());
		switch (browser.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			ChromeOptions chromeOptions = new ChromeOptions();
			driver = new ChromeDriver(chromeOptions);
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			driver = new FirefoxDriver(firefoxOptions);
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			EdgeOptions edgeOptions = new EdgeOptions();
			driver = new EdgeDriver(edgeOptions);
			break;
//            case "opera":
//                WebDriverManager.operadriver().setup();
//                OperaOptions operaOptions = new OperaOptions();
//                driver = new OperaDriver(operaOptions);
//                break;
		default:
			throw new IllegalArgumentException("Unsupported browser: " + browser);
		}
		driverObject.set(driver);
		// Implicit wait of 10 seconds
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		// Page load timeout
		getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));

		// For logging
		logger = LogManager.getLogger("SynergyConnect");
	}

	@AfterMethod
	public void tearDown(Method method) {
		System.out.println("@After Method: " + method.getName());
		if (getDriver() != null) {
			try {
				getDriver().close();
				getDriver().quit();
			} catch (Exception e) {
				logger.error("Error while closing WebDriver: " + e.getMessage(), e);
			}
		}
	}
}