package com.seleniumFramework.utilities;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import com.seleniumFramework.common.BaseClass;
import com.seleniumFramework.common.Config;
/**
 * Utility class for locating and interacting with web elements. Requires a
 * WebDriver instance for its operations.
 * 
 * This class provides various methods to find web elements based on different
 * attributes like tag name, text, class name, and id. It also includes methods
 * to handle elements that require a wait for their presence or visibility.
 * 
 * @RequiresDriver Indicates that this class requires a WebDriver instance.
 * 
 * @author Abhijeet Maske
 * @version 1.0
 * @since August 17, 2024
 */
public class WebElementLocators {
	private static final Logger logger = LogManager.getLogger(WebElementLocators.class);
	private static WebDriverWait wait;
	static Duration timeout = Duration.ofSeconds(Config.MEDIUM_PAUSE);
	static Duration polling = Duration.ofMillis(Config.POLLING_TIME);
	public static WebDriver driver;
	static private Actions action;

	/**
	 * Constructor for WebElementLocators. Initializes the WebDriver instance and
	 * sets up the wait and actions utilities.
	 * 
	 * @param webDriver The WebDriver instance to be used by this class.
	 */
	public WebElementLocators(WebDriver webDriver) {
		driver = BaseClass.getDriver();
		if (driver == null) {
			logger.error("WebDriver is null in WebElementLocators  constructor.");
			throw new IllegalStateException("WebDriver is null in WebElementLocators  constructor.");
		} else {
			logger.info("WebDriver initialized in WebElementLocators: {}", driver);
		}
		setWait(driver);
		WebElementLocators.action = new Actions(driver);
	}

	private static void setWait(WebDriver driver) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Setting in a static method
	}

	/********************************************************************************************
	 * Finds a web element using the specified locator and waits until the element
	 * is present within the given timeout period.
	 * 
	 * This method uses a FluentWait to wait for the presence of the element located
	 * by the provided `By` locator. If the element is found within the timeout
	 * period, it is returned.
	 * 
	 * @param byelement the `By` locator used to identify the web element
	 * @param timeout   the duration to wait for the element to be located
	 * @return the found `WebElement`, or `null` if the element is not found
	 * 
	 * @author Abhijeet Maske Created August 17, 2024
	 * @version 1.0 August 17, 2024
	 ********************************************************************************************/
	public static WebElement findElement(By byElement, Duration timeout) {
		WebElement webElement = null;
		try {
			FluentWait<WebDriver> fluentWait = new FluentWait<>(driver).withTimeout(timeout)
					.ignoring(NoSuchElementException.class);
			webElement = fluentWait.until(ExpectedConditions.presenceOfElementLocated(byElement));
			logger.info("Element is visible: {}", byElement);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Unable to find webElement: {}. Class: {}", byElement, WebElementLocators.class.getName(), e);
		}
		return webElement;
	}

	/********************************************************************************************
	 * Finds a web element based on its tag name and the text it contains.
	 * 
	 * This method constructs an XPath expression using the provided tag name and
	 * text, then finds and returns the matching web element.
	 * 
	 * @param tagName the tag name of the web element
	 * @param text    the text contained within the web element
	 * @return the found `WebElement`, or `null` if no matching element is found
	 * 
	 * @author Abhijeet Maske Created August 17, 2024
	 * @version 1.0 August 17, 2024
	 ********************************************************************************************/
	public static WebElement xpathByTagnameAndText(String tagName, String text) {
		WebElement webElement = null;
		String xpathExpression = String.format("//%s[contains(text(),'%s')]", tagName, text);
		try {
			webElement = driver.findElement(By.xpath(xpathExpression));
			logger.info("Element found with tag name: {} and text: {}", tagName, text);
		} catch (Exception e) {
			logger.error("Error locating element with tag name: {} and text: {}", tagName, text, e);
			throw e;
		}
		return webElement;
	}

	/********************************************************************************************
	 * Finds a web element using an XPath expression and waits until the element is
	 * visible.
	 * 
	 * This method uses a `WebDriverWait` to wait for the visibility of the element
	 * located by the provided XPath expression within a small timeout period.
	 * 
	 * @param text the XPath expression used to locate the web element
	 * @return the found `WebElement`, or `null` if the element is not found
	 * @throws Exception if an error occurs while waiting for the element to become
	 *                   visible
	 * 
	 * @author Abhijeet Maske Created August 17, 2024
	 * @version 1.0 August 17, 2024
	 ********************************************************************************************/
	public static WebElement xpathBytext(String text) {
		Duration XSMALL_PAUSE = Duration.ofSeconds(Config.XSMALL_PAUSE);
		wait = new WebDriverWait(driver, XSMALL_PAUSE);
		try {
			WebElement webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(text)));
			return webElement;
		} catch (Exception e) {
			System.out.println("Error in xpath by text: " + e.getMessage());
			throw e;
		}
	}

	/********************************************************************************************
	 * Finds a web element using its class name and the exact text it contains.
	 * 
	 * This method constructs an XPath expression using the provided class name and
	 * text, then finds and returns the matching web element.
	 * 
	 * @param className the class name of the web element
	 * @param text      the exact text contained within the web element
	 * @return the found `WebElement`, or `null` if no matching element is found
	 * 
	 * @author Abhijeet Maske Created August 17, 2024
	 * @version 1.0 August 17, 2024
	 ********************************************************************************************/
	public static WebElement xpathByClassAndText(String className, String text) {
		WebElement element = null;
		String xpathExpression = "//*[@class='" + className + "' and text()='" + text + "']";
		try {
			element = driver.findElement(By.xpath(xpathExpression));
			logger.info("Element found with class name: {} and text: {}", className, text);
		} catch (Exception e) {
			logger.error("Error locating element with class name: {} and text: {}", className, text, e);
			throw e;
		}
		return element;
	}

	/********************************************************************************************
	 * Finds a web element using its id and the exact text it contains.
	 * 
	 * This method constructs an XPath expression using the provided id and text,
	 * then finds and returns the matching web element.
	 * 
	 * @param id   the id attribute of the web element
	 * @param text the exact text contained within the web element
	 * @return the found `WebElement`, or `null` if no matching element is found
	 * 
	 * @throws Exception if an error occurs while locating the element
	 * 
	 * @author Abhijeet Maske Created August 17, 2024
	 * @version 1.0 August 17, 2024
	 ********************************************************************************************/
	public static WebElement xpathByIdAndText(String id, String text) {
		String xpathExpression = "//*[@id='" + id + "' and text()='" + text + "']";
		WebElement webElement = null;
		try {
			webElement = driver.findElement(By.xpath(xpathExpression));
			logger.info("Element found with id: {} and text: {}", id, text);
		} catch (Exception e) {
			logger.error("Error in locating element with id: {} and text: {}", id, text, e);
			throw e;
		}
		return webElement;
	}
}