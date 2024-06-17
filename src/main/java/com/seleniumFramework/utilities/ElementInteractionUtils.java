package com.seleniumFramework.utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Utility class to interact with web elements in Selenium WebDriver.
 */
public class ElementInteractionUtils {

    private static final Logger logger = LogManager.getLogger(ElementInteractionUtils.class);
    private WebDriver driver;
    private WebDriverWait wait;
    
    
	public ElementInteractionUtils(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    /********************************************************************************************
	 * Clicks on a web element.
	 * 
	 * @param element the web element to click
	 * 
	 *@author Abhijeet Maske Created June 27,2023
	 *@version 1.0 June 27,2023
	 ********************************************************************************************/
    public void click(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
            logger.info("Clicked on element: " + element);
        } catch (NoSuchElementException | TimeoutException e) {
            logger.error("Unable to click on element: " + getElementDescription(element), e);
        }
    }

    /**
     * Types text into a text field.
     *
     * @param element the text field element
     * @param text    the text to type
     */
    public void typeText(WebElement element, String text) {
        try {
            element.clear();
            element.sendKeys(text);
            logger.info("Typed '" + text + "' into element: " + getElementDescription(element));
        } catch (NoSuchElementException e) {
            logger.error("Unable to type text into element: " + getElementDescription(element), e);
        }
    }

    /**
     * Gets text from a web element.
     *
     * @param element the web element to get text from
     * @return the text of the element, or null if not found
     */
    public String getText(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            String text = element.getText();
            logger.info("Got text '" + text + "' from element: " + getElementDescription(element));
            return text;
        } catch (NoSuchElementException | TimeoutException e) {
            logger.error("Unable to get text from element: " + getElementDescription(element), e);
            return null;
        }
    }

    /**
     * Performs a mouse hover over a web element.
     * @param element the web element to hover over
     */
    public void hoverOverElement(WebElement element) {
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(element).perform();
            logger.info("Hovered over element: " + getElementDescription(element));
        } catch (NoSuchElementException e) {
            logger.error("Unable to hover over element: " + getElementDescription(element), e);
        }
    }

    /**
     * Checks if a web element is displayed.
     * @param element the web element to check
     * @return true if the element is displayed, false otherwise
     */
    public boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            logger.error("Element not found or not in the DOM: " + getElementDescription(element), e);
            return false;
        }
    }

    /**
     * Scrolls to the specified web element.
     * @param element the web element to scroll to
     */
    public void scrollToElement(WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            logger.info("Scrolled to element: " + getElementDescription(element));
        } catch (NoSuchElementException e) {
            logger.error("Unable to scroll to element: " + getElementDescription(element), e);
        }
    }

    /**
     * Gets the description of a web element (tagName and attributes).
     * @param element the web element
     * @return the description of the element
     */
    private String getElementDescription(WebElement element) {
        String tagName = element.getTagName();
        String attributes = element.toString().split("-> ")[1].replace("]", "");
        return tagName + " [" + attributes + "]";
    }
    
    /**
     * Clicks on a web element using JavaScript.
     * 
     * @param element the web element to click
     * 
     * @author Abhijeet Maske Created June 27,2023
     * @version 1.0 June 27,2023
     */
    public void clickByJS(WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
            logger.info("Clicked on element using JavaScript: " + getElementDescription(element));
        } catch (NoSuchElementException e) {
            logger.error("Unable to click on element using JavaScript: " + getElementDescription(element), e);
        }
    }
}