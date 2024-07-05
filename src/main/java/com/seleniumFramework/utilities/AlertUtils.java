package com.seleniumFramework.utilities;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/********************************************************************************************
 * Utility class to handle interactions with JavaScript alerts in Selenium WebDriver.
 * 
 * @return the configured ChromeOptions object
 * 
 ********************************************************************************************/
public class AlertUtils {

    private static final Logger logger = LogManager.getLogger(AlertUtils.class);
    private WebDriver driver;
    /********************************************************************************************
     * Constructor to initialize WebDriver.
     * 
     * @param driver the WebDriver instance to interact with alerts
     * 
     * @author Abhijeet Maske Created June 27,2023
     * @version 1.0 June 27,2023
     ********************************************************************************************/
    public AlertUtils(WebDriver driver) {
        this.driver = driver;
    }

    /********************************************************************************************
     * Accepts the alert.
     * 
     * @throws NoAlertPresentException if no alert is present
     * 
     * @author Abhijeet Maske Created June 27,2023
     * @version 1.0 June 27,2023
     ********************************************************************************************/
    public void acceptAlert() {
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
            logger.info("Alert accepted.");
        } catch (NoAlertPresentException e) {
            logger.error("No alert present to accept.", e);
        }
    }

    /********************************************************************************************
     * Dismisses the alert.
     * 
     * @throws NoAlertPresentException if no alert is present
     * 
     * @author Abhijeet Maske Created June 27,2023
     * @version 1.0 June 27,2023
     ********************************************************************************************/
    public void dismissAlert() {
        try {
            Alert alert = driver.switchTo().alert();
            alert.dismiss();
            logger.info("Alert dismissed.");
        } catch (NoAlertPresentException e) {
            logger.error("No alert present to dismiss.", e);
        }
    }

    /********************************************************************************************
     * Retrieves the text from the alert
     * 
     * @return the text present in the alert, or null if no alert is present
     * 
     * @author Abhijeet Maske Created June 27,2023
     * @version 1.0 June 27,2023
     ********************************************************************************************/
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

    /********************************************************************************************
     * Checks if an alert is present.
     * 
     * @return true if an alert is present, false otherwise
     * 
     * @author Abhijeet Maske Created June 27,2023
     * @version 1.0 June 27,2023
     ********************************************************************************************/
    public boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    /********************************************************************************************
     * Accepts the alert if present.  
     * @author Abhijeet Maske Created June 27,2023
     * @version 1.0 June 27,2023
     ********************************************************************************************/
    public void acceptAlertIfPresent() {
        if (isAlertPresent()) {
            acceptAlert();
        }
    }

    /********************************************************************************************
     * Dismisses the alert if present.
     * @author Abhijeet Maske Created June 27,2023
     * @version 1.0 June 27,2023
     ********************************************************************************************/
    public void dismissAlertIfPresent() {
        if (isAlertPresent()) {
            dismissAlert();
        }
    }
    
    /********************************************************************************************
     * Accepts the prompt and enters the specified text if present.
     * 
     * @param text the text to enter into the prompt
     * 
     * @author Abhijeet Maske Created June 27,2023
     * @version 1.0 June 27,2023
     ********************************************************************************************/
    
    public void acceptPrompt(String text) {
        if (isAlertPresent()) {
            Alert alert = driver.switchTo().alert();
            alert.sendKeys(text);
            alert.accept();
            logger.info("Prompt accepted with text: " + text);
        }
    }
    /********************************************************************************************
     * Return the text of toaster if present.
     * 
     * @return text if an toaster alert is present, false otherwise
     * 
     * @author Abhijeet Maske Created June 27,2023
     * @version 1.0 June 27,2023
     ********************************************************************************************/
    public String getToasterText() {
		String text = null;
		try {
			text = driver.findElement(By.className("toast-message")).getText();
			logger.info("Toaster Text: "+text);
		} catch (Exception e) {
			logger.error("Unable to get toaster text ", e);
		}
		return text;
	}
}