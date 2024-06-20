package com.seleniumFramework.utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.seleniumFramework.common.BaseClass;
import com.seleniumFramework.common.Config;

/**
 * Utility class to interact with web elements in Selenium WebDriver.
 */
public class ElementInteractionUtils {

	private static final Logger logger = LogManager.getLogger(ElementInteractionUtils.class);
	static WebDriverWait wait;
	static Duration timeout = Duration.ofSeconds(Config.MEDIUM_PAUSE);
	static Duration polling = Duration.ofMillis(Config.POLLING_TIME);
	WebDriver driver;

	public ElementInteractionUtils(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	/********************************************************************************************
	 * wait for web element and clears text in it
	 * 
	 * @param webElement {@link WebElement} -webElement to click
	 * 
	 * @return status {@link boolean} - true/false
	 * @author Abhijeet Maske Created June 27,2023
	 * @version 1.0 June 27,2023
	 ********************************************************************************************/
	public static boolean clear(WebElement webElement) {
		boolean status = false;
		try {
			waitForElementToBeVisible(webElement);
			webElement.clear();
			status = true;
			logger.info("Successfully cleared text in webElement: " + webElement.toString());
		} catch (Exception e) {
			logger.error("Unable to clear text in webElement: " + webElement.toString(), e);
		}

		return status;
	}

	/********************************************************************************************
	 * Clicks on a web element.
	 * 
	 * @param element the web element to click
	 * 
	 * @author Abhijeet Maske Created June 27,2023
	 * @version 1.0 June 17,2024
	 ********************************************************************************************/
	public static void click(WebElement element) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.click();
			logger.info("Clicked on element: " + element);
		} catch (NoSuchElementException | TimeoutException e) {
			logger.error("Unable to click on element: " + describeElement(element), e);
		}
	}

	/********************************************************************************************
	 * Click the identified web element by javascript.
	 * 
	 * @param webElement {@link WebElement} -webElement to click
	 * @return status {@link boolean} - true/false
	 * 
	 * @author Abhijeet Maske Created June 27,2023
	 * @version 1.0 June 27,2023
	 ********************************************************************************************/
	@SuppressWarnings("null")
	public static boolean clickUsingJS(WebElement webElement) {
		boolean status = false;
		JavascriptExecutor js = null;

		try {
			waitForElementToBeVisible(webElement);
			js.executeScript("arguments[0].click();", webElement);
			status = true;
			logger.info("Successfully clicked on the web element using JavaScript: " + webElement.toString());
		} catch (Exception e) {
			logger.error("Unable to click on the web element using JavaScript: " + webElement.toString(), e);
		}
		return status;
	}

	/********************************************************************************************
	 * wait for web element and set text in it
	 * 
	 * @param webElement {@link WebElement} -webElement to click
	 * @param text       {@link String} - text to enter
	 * @return status {@link boolean} - true/false
	 * @author Abhijeet Maske Created June 27,2023
	 * @version 1.0 June 27,2023
	 ********************************************************************************************/
	public static boolean sendKeys(WebElement webElement, String text) {
		boolean status = false;

		try {
			waitForElementToBeVisible(webElement);
			webElement.clear();
			webElement.sendKeys(text);
			status = true;

		} catch (Exception e) {
			logger.error("Unable to set text in webElement: " + webElement.toString());
			System.out.println(e);
		}
		return status;
	}

	/********************************************************************************************
	 * Send text in element with javascript.
	 * 
	 * @param webElement {@link WebElement} -webElement to click
	 * @return status {@link boolean} - true/false
	 * @author Abhijeet Maske Created June 27,2023
	 * @version 1.0 June 27,2023
	 ********************************************************************************************/
	public static void sendKeysUsingJS(WebElement webElement, String value) {
		JavascriptExecutor js = null;
		js.executeScript("$('#" + "accountNumber" + "').val('" + value + "');");
	}

	/********************************************************************************************
	 * Gets the description of a web element (tagName and attributes).
	 * 
	 * @param element the web element
	 * @return the description of the element
	 * 
	 * @author Abhijeet Maske Created June 27,2023
	 * @version 1.0 June 27,2023
	 ********************************************************************************************/
	private static String describeElement(WebElement element) {
		String tagName = element.getTagName();
		String attributes = element.toString().split("-> ")[1].replace("]", "");
		return tagName + " [" + attributes + "]";
	}

	/********************************************************************************************
	 * Send text in element with javascript.
	 * 
	 * @param webElement {@link WebElement} -webElement to click
	 * @param value      {@link String} - value too be entered
	 * @return status {@link boolean} - true/false
	 * 
	 * @author Abhijeet Maske Created June 27,2023
	 * @version 1.0 June 27,2023
	 ********************************************************************************************/
	public static void searchClickByJS(WebElement webElement) {
		JavascriptExecutor js = null;
		// js.executeScript("argument[0].value'"+value +"';",webElement);
		js.executeScript("var el = document.querySelector(\"" + webElement + "\").click();");
	}

	/********************************************************************************************
	 * wait for web element and selects value from drop down
	 * 
	 * @param webElement {@link WebElement} -webElement to click
	 * @param value      {@link String} - value too be entered
	 * @return status {@link boolean} - true/false
	 * 
	 * @author Abhijeet Maske Created June 27,2023
	 * @version 1.0 June 27,2023
	 ********************************************************************************************/
	public static boolean selectByValue(WebElement webElement, String value) {
		boolean status = false;
		try {
			waitForElementToBeVisible(webElement);
			Select listBox = new Select(webElement);
			listBox.selectByValue(value);
			status = true;
			logger.info(
					"Successfully selected the value '" + value + "' from the dropdown list: " + webElement.toString());
		} catch (Exception e) {
			logger.error("Unable to select the value '" + value + "' from the dropdown list: " + webElement.toString(),
					e);
		}
		return status;
	}

	/********************************************************************************************
	 * wait for web element and selects value from drop down
	 * 
	 * @param webElement {@link WebElement} -webElement to click
	 * @param value      {@link String} - value too be entered
	 * @return status {@link boolean} - true/false
	 * 
	 * @author Abhijeet Maske Created June 27,2023
	 * @version 1.0 June 27,2023
	 ********************************************************************************************/
	public static boolean selectByVisibleText(WebElement webElement, String value) {
		boolean status = false;
		try {
			waitForElementToBeVisible(webElement);
			Select listBox = new Select(webElement);
			listBox.selectByVisibleText(value);
			status = true;
			logger.info(
					"Successfully selected the value '" + value + "' from the dropdown list: " + webElement.toString());
		} catch (Exception e) {
			logger.error("Unable to select the value '" + value + "' from the dropdown list: " + webElement.toString(),
					e);
		}
		return status;
	}

	/********************************************************************************************
	 * wait for web element and selects value from drop down
	 * 
	 * @param webElement {@link WebElement} -webElement to click
	 * @param index      {@link String} - value index to select
	 * @return status {@link boolean} - true/false
	 * 
	 * @author Abhijeet Maske Created June 27,2023
	 * @version 1.0 June 27,2023
	 ********************************************************************************************/
	public static boolean selectByIndex(WebElement webElement, int index) {
		boolean status = false;

		try {
			waitForElementToBeVisible(webElement);
			Select listBox = new Select(webElement);
			listBox.selectByIndex(index);
			status = true;
			logger.info("Successfully selected the value at index '" + index + "' from the dropdown list: "
					+ webElement.toString());
		} catch (Exception e) {
			logger.error("Unable to select the value at index '" + index + "' from the dropdown list: "
					+ webElement.toString(), e);
		}
		return status;
	}

	/********************************************************************************************
	 * wait for web element visibility
	 * 
	 * @param webElement {@link WebElement} -webElement to click
	 * @param timeOut    {@link int} - The amount of the time in milliseconds to
	 *                   pause
	 * @param polling    The interval at which to check the condition.
	 * @return status {@link boolean} - true/false
	 * 
	 * @author Abhijeet Maske Created June 27,2023
	 * @version 1.0 June 27,2023
	 ********************************************************************************************/
	public static boolean waitForVisibility(WebElement webElement, Duration timeout, Duration polling) {
		boolean status = false;
		FluentWait<WebDriver> fluentWait;
		try {
			// Initialize FluentWait with WebDriver instance and timeout duration
			fluentWait = new FluentWait<>(BaseClass.getDriver()).withTimeout(timeout).pollingEvery(polling)
					.ignoring(NoSuchElementException.class);
			fluentWait.until(ExpectedConditions.elementToBeClickable(webElement));
			status = true;
			logger.info("Element is clickable: " + webElement.toString());
		} catch (Exception e) {
			logger.error("Element is not clickable: " + webElement.toString(), e);
		}
		return status;
	}

	public static boolean waitForElementToBeVisible(WebElement webElement) {
		boolean status = false;
		WebDriverWait wait;
		try {
			wait = new WebDriverWait(BaseClass.getDriver(), timeout);
			wait.until(ExpectedConditions.visibilityOf(webElement));
			status = true;
			logger.info("Element is visible: " + webElement.toString());
		} catch (Exception e) {
			logger.error("Element is not visible: " + webElement.toString(), e);
		}
		return status;
	}

	/********************************************************************************************
	 * wait for web element to be non visible
	 * 
	 * @param webElement {@link WebElement} -webElement to click
	 * @param timeOut    {@link int} - The amount of the time in milliseconds to
	 *                   pause
	 * 
	 * @return status {@link boolean} - true/false
	 * @author Abhijeet Maske Created June 27,2023
	 * @version 1.0 June 27,2023
	 ********************************************************************************************/
	public static boolean waitForInvisibilityOfElement(WebElement webElement, Duration timeout, Duration polling) {
		boolean status = false;
		FluentWait<WebDriver> fluentWait;
		try {
			fluentWait = new FluentWait<>(BaseClass.getDriver()).withTimeout(timeout).pollingEvery(polling)
					.ignoring(NoSuchElementException.class);
			fluentWait.until(ExpectedConditions.invisibilityOf(webElement));
			status = true;
			logger.info("Element is now invisible: " + webElement.toString());
		} catch (Exception e) {
			logger.error("Unable to wait for invisibility of element: " + webElement.toString(), e);
		}
		return status;
	}

	/********************************************************************************************
	 * wait for web element and set text in it.
	 * 
	 * @param webElement {@link WebElement} -webElement to click
	 * @param text       {@link String} - text to enter
	 * @return status {@link boolean} - true/false
	 * 
	 * @author Abhijeet Maske Created June 27,2023
	 * @version 1.0 June 27,2023
	 ********************************************************************************************/
	public static WebElement findElement(By byelement, Duration timeout) {
		WebElement webelement = null;
		try {
			FluentWait<WebDriver> fluentWait = new FluentWait<>(BaseClass.getDriver()).withTimeout(timeout)
					.ignoring(NoSuchElementException.class);
			webelement = fluentWait.until(ExpectedConditions.presenceOfElementLocated(byelement));
			logger.info("Element is visible: " + byelement.toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Unable to find webElement: " + byelement);
		}
		return webelement;
	}

	/********************************************************************************************
	 * verifies element is present
	 * 
	 * @param webElement {@link WebElement} -webElement to click
	 * @param timeout    {@link Integer} - The amount of time in milliseconds to
	 *                   pause.
	 * @return status {@link boolean} - true/false
	 * 
	 * @author Abhijeet Maske Created June 27,2023
	 * @version 1.0 June 27,2023
	 ********************************************************************************************/
	public static boolean isPresent(WebElement webElement, int timeOut) {

		boolean status = false;
		try {
			status = webElement.isDisplayed();
			logger.info("Element is visible: " + webElement.toString());
		} catch (Exception e) {
			logger.error("WebElement is not present: " + webElement.toString());
		}
		return status;

	}

	/********************************************************************************************
	 * verifies element is enabled
	 * 
	 * @param webElement {@link WebElement} -webElement to click
	 * @return status {@link boolean} - true/false
	 * 
	 * @author Abhijeet Maske Created June 27,2023
	 * @version 1.0 June 27,2023
	 ********************************************************************************************/
	public static boolean isEnabled(WebElement webElement) {
		boolean status = false;

		waitForElementToBeVisible(webElement);
		try {
			status = webElement.isEnabled();
			logger.info("Element is enabled: " + webElement.toString());
		} catch (Exception e) {
			logger.error("WebElement is disabled: " + webElement.toString());
		}

		return status;

	}

	/********************************************************************************************
	 * Determine if an element has a specific text value or not.
	 * 
	 * @param webElement {@link WebElement} -webElement to click
	 * @param text       {@link String } - WebElement to verify text
	 * @return status {@link boolean} - true/false
	 * 
	 * @author Abhijeet Maske Created June 27,2023
	 * @version 1.0 June 27,2023
	 ********************************************************************************************/
	public static boolean verifyText(WebElement webElement, String text) {
		boolean status = false;
		waitForElementToBeVisible(webElement);
		try {
			status = webElement.getText().contains(text);
			logger.info("Verifying text: " + text + " within web element: " + webElement);
		} catch (Exception e) {
			logger.error("unable to verify text for webelement: " + webElement.toString());
		}
		return status;
	}

	/********************************************************************************************
	 * Fetches element specific attribute value
	 * 
	 * @param webElement {@link WebElement} -webElement to click
	 * @param attribute  {@link String } - The attribute value
	 * @return status {@link boolean} - true/false
	 * 
	 * @author Abhijeet Maske Created June 27,2023
	 * @version 1.0 June 27,2023
	 ********************************************************************************************/
	public static String getAttribute(WebElement webElement, String attribute) {
		String attributeValue = " ";

		waitForElementToBeVisible(webElement);
		try {
			logger.info("Getting attribute '" + attribute + "' for webElement: " + webElement.toString());
			attributeValue = webElement.getAttribute(attribute);
		} catch (Exception e) {
			logger.error("Unable to get attribute for webElement " + webElement.toString(), e);
		}
		return attributeValue;
	}

	/********************************************************************************************
	 * Determine if an element has a specific attribute value or not.
	 * 
	 * @param webElement     {@link WebElement} -webElement to click
	 * @param attribute      {@link String } - The specific attribute type to
	 *                       evaluate
	 * @param attributeValue {link String} - The value of the attribute to evaluate
	 * @return status {@link boolean} - true/false
	 * 
	 * @author Abhijeet Maske Created June 27,2023
	 * @version 1.0 June 27,2023
	 ********************************************************************************************/
	public static boolean verifyAttributeValue(WebElement webElement, String attribute, String attributeValue) {
		boolean status = false;

		waitForElementToBeVisible(webElement);
		try {
			logger.info("Verifying attribute '" + attribute + "' value to be '" + attributeValue + "' for webElement: "
					+ webElement.toString());
			status = webElement.getAttribute(attribute).equalsIgnoreCase(attributeValue);
		} catch (Exception e) {
			logger.error("Unable to get attribute for webElement " + webElement.toString(), e);
		}
		return status;
	}

	/********************************************************************************************
	 * Determine if an element has a specific attribute value or not.
	 * 
	 * @param webElement     {@link WebElement} -webElement to click
	 * @param attribute      {@link String } - The specific attribute type to
	 *                       evaluate
	 * @param attributeValue {link String} - The value of the attribute to evaluate
	 * @return status {@link boolean} - true/false
	 * 
	 * @author Abhijeet Maske Created June 27,2023
	 * @version 1.0 June 27,2023
	 ********************************************************************************************/
	public static boolean verifyAttributeContains(WebElement webElement, String attribute, String attributeValue) {
		boolean status = false;
		waitForElementToBeVisible(webElement);
		try {
			logger.info("Verifying if attribute '" + attribute + "' contains value '" + attributeValue
					+ "' for webElement: " + webElement.toString());
			status = webElement.getAttribute(attribute).toUpperCase().contains(attributeValue.toUpperCase());
		} catch (Exception e) {
			logger.error("Unable to verify if attribute contains for webElement " + webElement.toString(), e);
		}
		return status;
	}

	/********************************************************************************************
	 * Waits for element to have specific attribute value
	 * 
	 * @param webElement     {@link WebElement} -webElement to click
	 * @param attribute      {@link String } - The specific attribute type to
	 *                       evaluate
	 * @param attributeValue {link String} - The value of the attribute to evaluate
	 * @param timeOut        {@link int} - The value of the timeout
	 * @return status {@link boolean} - true/false
	 * 
	 * @author Abhijeet Maske Created June 27,2023
	 * @version 1.0 June 27,2023
	 ********************************************************************************************/
	public static boolean waitForAttributeContains(WebElement webElement, String attribute, String attributeValue) {
		boolean status = false;
		try {
			logger.info("Waiting for attribute '" + attribute + "' of webElement to contain value '" + attributeValue
					+ "': " + webElement.toString());
			WebDriverWait wait = new WebDriverWait(BaseClass.getDriver(), timeout);
			wait.until(ExpectedConditions.attributeContains(webElement, attribute, attributeValue));
			status = webElement.getAttribute(attribute).toUpperCase().contains(attributeValue.toUpperCase());
			status = true;
		} catch (Exception e) {
			logger.error("Unable to verify if attribute contains for webElement " + webElement.toString(), e);
		}
		return status;
	}

	/********************************************************************************************
	 * Scroll to element
	 * 
	 * @param webElement {@link WebElement} -webElement to click
	 * @return status {@link boolean} - true/false
	 * 
	 * @author Abhijeet Maske Created June 27,2023
	 * @version 1.0 June 27,2023
	 ********************************************************************************************/
	public static boolean scrollIntoView(WebElement webElement) {
		boolean status = false;
		WebElement webelement;

		try {
			WebDriver driver = BaseClass.getDriver();
			logger.info("Scrolling into view: " + webElement.toString());
			((RemoteWebDriver) driver).executeScript("argument[0].scrollIntoView();", webElement);
			webelement = driver.findElement(By.xpath("(//*[contains(text(),'')])[1]"));
			click(webelement);
			Actions action = new Actions(driver);
			action.moveByOffset(8000, 8000).click().perform();
			status = true;
		} catch (Exception e) {
			logger.error("Exception occurred while scrolling into view: " + e.getMessage(), e);
		}
		return status;
	}

	/********************************************************************************************
	 * Pause the test action
	 * 
	 * @param waitTime {@link Integer} - The amount of the time in milliseconds to
	 *                 pause
	 * 
	 * @author Abhijeet Maske Created June 27,2023
	 * @version 1.0 June 27,2023
	 ********************************************************************************************/
	public static void pause(Integer waitTime) {
		try {
			Thread.sleep(waitTime);
		} catch (Exception e) {
			logger.error("Thread was interrupted while pausing for {" + waitTime + "} milliseconds", waitTime, e);
			Thread.currentThread().interrupt();
		}
	}

	/********************************************************************************************
	 * Splits a given string into an array of substrings based on commas, trimming
	 * any leading and trailing whitespace from each substring.
	 * 
	 * @param text The string to be split.
	 * @return An array of substrings.@param waitTime {@link Integer} - The amount
	 *         of the time in milliseconds to pause
	 * 
	 * 
	 * @author Abhijeet Maske Created June 27,2023
	 * @version 1.0 June 27,2023
	 ********************************************************************************************/
	public static String[] splitString(String text) {
		logger.info("Splitting string: {}", text);
		String[] list = text.trim().split("\\s*,\\s*");
		logger.debug("Split result: {}", (Object) list);
		return list;
	}

	/********************************************************************************************
	 * Generates a random email address and sets it in the configuration.
	 * 
	 * @return The generated email address.
	 * 
	 * @author Abhijeet Maske Created June 27,2023
	 * @version 1.0 June 27,2023
	 ********************************************************************************************/
	public static String setEmail() {
		String email = null;
		try {
			final String randomEmail = UUID.randomUUID().toString().replace("-", " ") + "@" + "gmail.com";
			Config.emailaddr = "qa" + "." + randomEmail;
			logger.info("Email generated is: " + Config.emailaddr);
			email = Config.emailaddr;
		} catch (Exception e) {
			logger.error("Unable to generate random email: " + e);
		}
		return email;
	}

	/********************************************************************************************
	 * Checks if an element with the specified text is present on the page. If the
	 * element is found, it scrolls into view and highlights it.
	 * 
	 * @param text The text to search for in the element.
	 * @return True if the element is found and visible, otherwise false.
	 * 
	 * @author Abhijeet Maske Created June 27,2023
	 * @version 1.0 June 27,2023
	 ********************************************************************************************/
	public static boolean getElementByText(String text) {
		boolean status = false;
		WebElement getElementByText;
		WebDriver driver = BaseClass.getDriver();
		try {
			getElementByText = driver.findElement(By.xpath("(//*[contains(text(),\"" + text + "\")])[]1]"));
			ElementInteractionUtils.scrollIntoView(getElementByText);
			if (ElementInteractionUtils.isPresent(getElementByText, 60)
					|| ElementInteractionUtils.waitForElementToBeVisible(getElementByText)) {
				status = true;
				logger.info("Element is present and text is " + getElementByText.getText());
				highlightElement(getElementByText);
			} else {
				status = false;
				logger.warn("Element is not present or not visible.");
			}
		} catch (Exception e) {
			logger.error("Element not found: " + e.getMessage());
			status = false;
		}
		return status;
	}

	/********************************************************************************************
	 * Verifies if the specified value is selected in a drop-down list or
	 * multi-select element.
	 * 
	 * @param webElement The drop-down list or multi-select element.
	 * @param value      The value to verify if selected.
	 * @return True if the specified value is selected, otherwise false.
	 * 
	 * @author Abhijeet Maske Created June 27,2023
	 * @version 1.0 June 27,2023
	 ********************************************************************************************/
	public static boolean isSelected(WebElement webElement, String value) {
		boolean status = false;
		try {
			waitForElementToBeVisible(webElement);
			Select listBox = new Select(webElement);
			listBox.getAllSelectedOptions().size();
			String isSelected = listBox.getFirstSelectedOption().getText();
			if (isSelected.contains(value)) {
				status = true;
			}
		} catch (Exception e) {
			logger.error("Unable to verify if the value is selected from listBox: " + webElement.toString(), e);
		}
		return status;
	}

	/********************************************************************************************
	 * Verifies if the specified value is not selected in a drop-down list or
	 * multi-select element.
	 * 
	 * @param webElement The drop-down list or multi-select element.
	 * @param value      The value to verify if not selected.
	 * @return True if the specified value is not selected, otherwise false.
	 * 
	 * @author Abhijeet Maske Created June 27,2023
	 * @version 1.0 June 27,2023
	 ********************************************************************************************/
	public static boolean isNotSelected(WebElement webElement, String value) {
		boolean status = false;
		try {
			waitForElementToBeVisible(webElement);
			Select listBox = new Select(webElement);
			String actual = listBox.getFirstSelectedOption().getText();
			if (!actual.contains(value)) {
				status = true;
			}
		} catch (Exception e) {
			logger.error("Unable to verify if the value is not selected from listBox: " + webElement.toString(), e);
		}
		return status;
	}

	/********************************************************************************************
	 * Highlights the specified WebElement by adding a border and changing its color
	 * temporarily.
	 * 
	 * @param element The WebElement to highlight.
	 * 
	 * @author Abhijeet Maske Created June 27,2023
	 * @version 1.0 June 27,2023
	 ********************************************************************************************/
	public static void highlightElement(WebElement element) {
		for (int i = 0; i < 3; i++) {
			JavascriptExecutor js;
			js = (JavascriptExecutor) BaseClass.getDriver();
			js.executeScript("arguments[0].setAttribute('style',arguments[1]);", element,
					"color: Orange; border: 2px solid red;");
			pause(200);
			js.executeScript("arguments[0].setAttribute('style',arguments[1]);", element, "");
		}
	}

	/********************************************************************************************
	 * Switches to a different window based on the index provided.
	 * 
	 * @param value The index of the window to switch to.
	 * @return true if the window was successfully switched, false otherwise.
	 * 
	 * @author Abhijeet Maske Created June 27,2023
	 * @version 1.0 June 27,2023
	 ********************************************************************************************/

	public static boolean switchWindow(int value) {
		boolean status = false;
		Set<String> windowHandles;
		WebDriver driver = BaseClass.getDriver();
		windowHandles = driver.getWindowHandles();

		List<String> windowHandlesList = new ArrayList<>(windowHandles);
		int numWindows = windowHandlesList.size();

		if (numWindows > 1 && value >= 0 && value < numWindows) {
			driver.switchTo().window(windowHandlesList.get(value));
			status = true;
		}

		return status;
	}

	/********************************************************************************************
	 * Closes the current window and switches to the first window in the window
	 * handles list.
	 * 
	 * @param value Unused parameter. Keeping for compatibility with other methods.
	 * @return true if the window was successfully closed and switched, false
	 *         otherwise.
	 * 
	 * @author Abhijeet Maske Created June 27,2023
	 * @version 1.0 June 27,2023
	 ********************************************************************************************/

	public static boolean closeWindow(int Value) {
		boolean status = false;
		WebDriver driver = BaseClass.getDriver();

		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windowHandlesList = new ArrayList<>(windowHandles);

		if (windowHandlesList.size() > 1) {
			driver.close();
			driver.switchTo().window(windowHandlesList.get(0));
			status = true;
		}
		return status;
	}

	/********************************************************************************************
	 * Switch to frame
	 * 
	 * @param indexOrNameOrId {@link String} - Id, Name or Index of the frames
	 * @return status {@link boolean} - true/false
	 * 
	 * @author Abhijeet Maske Created June 27,2023
	 * @version 1.0 June 27,2023
	 ********************************************************************************************/
	public static boolean switchToFrame(String indexOrNameOrId) {
		boolean status = false;
		try {
			logger.info("Switching to frame: " + indexOrNameOrId);
			WebDriver driver = BaseClass.getDriver();
			((RemoteWebDriver) driver).switchTo().frame(indexOrNameOrId);
			status = true;
		} catch (Exception e) {
			logger.error("Unable to switch to frame: " + indexOrNameOrId, e);
		}
		return status;
	}

	/********************************************************************************************
	 * Switch to default frame
	 * 
	 * @param idNameIndex {@link String} - Id, Name or Index of the frames
	 * @return status {@link boolean} - true/false
	 * 
	 * @author Abhijeet Maske Created June 27,2023
	 * @version 1.0 June 27,2023
	 ********************************************************************************************/
	public static boolean switchToDefaultContent() {
		boolean status = false;
		try {
			logger.info("Switching to default content");
			WebDriver driver = BaseClass.getDriver();
			((RemoteWebDriver) driver).switchTo().defaultContent();
			status = true;
		} catch (Exception e) {
			logger.error("Unable to switch to default content", e);
		}
		return status;
	}

	/********************************************************************************************
	 * Navigates back to the previous page.
	 * 
	 * @return true if the navigation is successful, false otherwise.
	 * 
	 * @author Abhijeet Maske Created June 27,2023
	 * @version 1.0 June 27,2023
	 ********************************************************************************************/
	public static boolean navigateBack() {
		boolean status = false;
		try {
			WebDriver driver = BaseClass.getDriver();
			driver.navigate().back();
			status = true;
		} catch (Exception e) {
			logger.error("Navigation back failed: ", e);
		}
		return status;
	}

	/********************************************************************************************
	 * Refreshes the current page.
	 * 
	 * @return true if the page refresh was successful, false otherwise.
	 * 
	 * @author Abhijeet Maske Created June 27,2023
	 * @version 1.0 June 27,2023
	 ********************************************************************************************/
	public static boolean refresh() {
		boolean status = false;
		WebDriver driver = null;
		try {
			driver = BaseClass.getDriver();
			driver.navigate().refresh();
			Actions actionObject = new Actions(driver);
			actionObject.keyDown(Keys.CONTROL).sendKeys(Keys.F5).keyUp(Keys.CONTROL).build().perform();
			status = true;
		} catch (Exception e) {
			logger.error("Failed to refresh the page: " + e.getMessage());
		}
		return status;
	}

//	public static boolean clickEleText(Map<String, String> testData) {
//	    boolean status = false;
//	    WebDriver driver = BaseClass.getDriver();
//	    String[] verText = testData.get("ClickElement").split("\\|");
//	    try {
//	        System.out.println(index);
//	        WebElement eleGettxt = driver.findElement(By.xpath("(//*[contains(text(),\"" + verText[index] + "\")])[1]"));
//	        System.out.println();
//	        ElementInteractionUtils.scrollIntoView(eleGettxt);
//	        if (ElementInteractionUtils.isPresent(eleGettxt, 60) || ElementInteractionUtils.waitForElementToBeVisible(eleGettxt)) {
//	            highlightElement(eleGettxt);
//	            System.out.println("Element is present and the text is " + eleGettxt.getText());
//	            if (click(eleGettxt)) {
//	                System.out.println("test3");
//	                //status = true;
//	            }
//	        } else {
//	            System.out.println("Element is not present:" + eleGettxt);
//	        }
//	    } catch (Exception e) {
//	        logger.error("Element not found: ", e);
//	    }
//	    index++;
//	    return status;
//	}

	/********************************************************************************************
	 * Verifies the presence of page text based on the provided test data.
	 * 
	 * @param testData a map containing test data.
	 * @return true if all page texts are present, false otherwise.
	 * 
	 * @author Abhijeet Maske Created June 27,2023
	 * @version 1.0 June 27,2023
	 ********************************************************************************************/
	public static boolean verifyPageContainsText(Map<String, String> testData) {
		boolean status = false;
		try {
			String[] verifyingPageText;
			verifyingPageText = testData.get("VerifyPageText").split("\\|");
			int count = verifyingPageText.length;
			logger.info("Size: " + count);
			for (int i = 0; i < count; i++) {
				boolean webelement = false;
				try {
					webelement = ElementInteractionUtils.getElementByText(verifyingPageText[i]);
				} catch (Exception e) {
					logger.error("Error occurred while verifying page text: ", e);
				}
				if (webelement) {
					logger.info(verifyingPageText[i] + "is present");
					status = true;
				} else {
					logger.error(verifyingPageText[i] + "is not present");
					status = false;
				}
			}
		} catch (Exception e) {
			logger.error("Element not found: ", e);
			status = false;
		}
		return status;
	}

	public static void keyDown(WebElement webElement, String inputString, String string) {
		try {
			Actions action = new Actions(BaseClass.getDriver());

			action.keyDown(webElement, "keys" + "." + "string");
			System.out.println();
			action.sendKeys(webElement, inputString);
			action.keyUp(string).perform();
			logger.info("Performed keyDown action with key: " + string + ", and input string: " + inputString);
		} catch (Exception e) {
			logger.error("Unable to perform keyDown action with key: " + string + ", and input string: " + inputString,
					e);
		}

	}

	/********************************************************************************************
	 * Moves the mouse cursor to the specified WebElement.
	 * 
	 * @param webElement The WebElement to hover over.
	 * @return true if the mouse hover operation was successful, false otherwise.
	 * 
	 * @author Abhijeet Maske Created June 27,2023
	 * @version 1.0 June 27,2023
	 ********************************************************************************************/
	public static boolean mouseHover(WebElement webElement) {
		boolean status = false;
		try {
			if (webElement.isDisplayed()) {
				logger.info("hovering over element: " + webElement);
				Actions action = new Actions(BaseClass.getDriver());
				highlightElement(webElement);
				action.moveToElement(webElement).build().perform();
				status = true;
			}
		} catch (Exception e) {
			logger.error("Problem in hovering over element: " + e.getMessage());
		}
		return status;
	}

	/**
	 * Double clicks on the specified WebElement.
	 *
	 * @param webElement The WebElement to double click on.
	 * @return true if the action was successful, false otherwise.
	 */
	public static boolean doubleClick(WebElement webElement) {
		boolean status = false;
		try {
			if (webElement.isDisplayed()) {
				logger.info("Double clicking on element: " + webElement);
				Actions action = new Actions(BaseClass.getDriver());
				highlightElement(webElement);
				action.doubleClick(webElement).perform();
				status = true;
			}
		} catch (Exception e) {
			logger.error("Problem in double clicking on element:" + e.getMessage());
		}
		return status;
	}

	/**
	 * Right clicks on the specified WebElement.
	 *
	 * @param webElement The WebElement to right click on.
	 * @return true if the action was successful, false otherwise.
	 */
	public static boolean rightClick(WebElement webElement) {
		boolean status = false;
		try {
			if (webElement.isDisplayed()) {
				logger.info("Right clicking on element: " + webElement);
				Actions action = new Actions(BaseClass.getDriver());
				highlightElement(webElement);
				action.contextClick(webElement).perform();
				status = true;
			}
		} catch (Exception e) {
			logger.error("Problem in right clicking on element:" + e.getMessage());
		}
		return status;
	}

	/**
	 * Selects the webelement if it is not already selected and clicks on it.
	 *
	 * @param WebElement to select and click.
	 * @return true if the action was selected and clicked, false otherwise.
	 */
	public static boolean selectOptionAndClick(WebElement webElement) {
		boolean status = false;
		try {
			if (!webElement.isSelected()) {
				webElement.click();
				status = true;
				logger.info("webelement selected and clicked: " + webElement);
			} else {
				logger.info("webelement is already selected: " + webElement);
			}
		} catch (Exception e) {
			logger.error("Problem in selecting and clicking on element:" + e.getMessage());
		}
		return status;
	}

	/**
	 * Clicks the webelement if it is displayed.
	 *
	 * @param webElement to click.
	 * @return true if the element was displayed and clicked, false otherwise.
	 */
	public static boolean isDisplayedAndClick(WebElement webElement) {
		boolean status = false;
		try {
			if (webElement.isDisplayed()) {
				webElement.click();
				status = true;
				logger.info("webelement displyed and clicked: " + webElement);
			} else {
				logger.info("webelement is not displayed: " + webElement);
			}
		} catch (Exception e) {
			logger.error("Problem in displaying and clicking on element: " + e.getMessage());
		}
		return status;
	}

	/**
	 * Clicks the webelement if it is enabled.
	 *
	 * @param WebElement to click.
	 * @return true if the element was enabled and clicked, false otherwise.
	 */
	public static boolean isEnabledAndClick(WebElement webElement) {
		boolean status = false;
		try {
			if (webElement.isEnabled()) {
				webElement.click();
				status = true;
				logger.info("webelement enabled and clicked: " + webElement);
			} else {
				logger.info("webelement is not enabled: " + webElement);
			}
		} catch (Exception e) {
			logger.error("Problem in enabling and clicking on element: " + e.getMessage());
		}
		return status;
	}

	public static boolean webTables(WebElement webElement) {
		boolean status = false;
		String sRow = "1";
		String sCol = "2";
		WebDriver driver = BaseClass.getDriver();
		// Here we are locating the xpath by passing variables in the xpath
		String sCellValue = driver
				.findElement(By.xpath(".//*[@id='content']/table/tbody/tr[" + sRow + "]/td[" + sCol + "]")).getText();
		System.out.println(sCellValue);
		String sRowValue = "Clock Tower Hotel";

		// First loop will find the 'ClOCK TWER HOTEL' in the first column
		for (int i = 1; i <= 5; i++) {
			String sValue = null;
			sValue = driver.findElement(By.xpath(".//*[@id='content']/table/tbody/tr[" + i + "]/th")).getText();
			if (sValue.equalsIgnoreCase(sRowValue)) {
				// If the sValue match with the description, it will initiate one more inner
				// loop for all the columns of 'i' row
				for (int j = 1; j <= 5; j++) {
					String sColumnValue = driver
							.findElement(By.xpath(".//*[@id='content']/table/tbody/tr[" + i + "]/td[" + j + "]"))
							.getText();
					System.out.println(sColumnValue);
				}
				break;
			}
		}

		return status;
	}
	
	public static boolean dragAndDrop(WebElement source,WebElement target ) {
		Actions action = new Actions(BaseClass.getDriver());
		action.dragAndDrop(source, target).perform();
		return false;
	}
}