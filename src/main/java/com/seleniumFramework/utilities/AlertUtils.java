package com.seleniumFramework.utilities;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Utility class to handle interactions with JavaScript alerts in Selenium WebDriver.
 */
public class AlertUtils {

    private static final Logger logger = LogManager.getLogger(AlertUtils.class);
    private WebDriver driver;

    /**
     * Constructor to initialize WebDriver.
     * @param driver the WebDriver instance to interact with alerts
     */
    public AlertUtils(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Accepts the alert.
     * @throws NoAlertPresentException if no alert is present
     */
    public void acceptAlert() {
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
            logger.info("Alert accepted.");
        } catch (NoAlertPresentException e) {
            logger.error("No alert present to accept.", e);
        }
    }

    /**
     * Dismisses the alert.
     * @throws NoAlertPresentException if no alert is present
     */
    public void dismissAlert() {
        try {
            Alert alert = driver.switchTo().alert();
            alert.dismiss();
            logger.info("Alert dismissed.");
        } catch (NoAlertPresentException e) {
            logger.error("No alert present to dismiss.", e);
        }
    }

    /**
     * Retrieves the text from the alert.
     * @return the text present in the alert, or null if no alert is present
     */
    public String getAlertText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            logger.info("Alert text retrieved: " + alertText);
            return alertText;
        } catch (NoAlertPresentException e) {
            logger.warn("No alert present to get text from.");
            return null;
        }
    }

    /**
     * Checks if an alert is present.
     * @return true if an alert is present, false otherwise
     */
    public boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    /**
     * Accepts the alert if present.
     */
    public void acceptAlertIfPresent() {
        if (isAlertPresent()) {
            acceptAlert();
        }
    }

    /**
     * Dismisses the alert if present.
     */
    public void dismissAlertIfPresent() {
        if (isAlertPresent()) {
            dismissAlert();
        }
    }

    /**
     * Accepts the prompt and enters the specified text if present.
     * @param text the text to enter into the prompt
     */
    public void acceptPrompt(String text) {
        if (isAlertPresent()) {
            Alert alert = driver.switchTo().alert();
            alert.sendKeys(text);
            alert.accept();
            logger.info("Prompt accepted with text: " + text);
        }
    }
}