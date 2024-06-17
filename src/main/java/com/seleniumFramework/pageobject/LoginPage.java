package com.seleniumFramework.pageobject;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.Status;
import com.seleniumFramework.common.BaseClass;
import com.seleniumFramework.utilities.ElementInteractionUtils;
import com.seleniumFramework.utilities.ExtentReportListener;

public class LoginPage {
	protected ElementInteractionUtils elementInteractionUtils;
	protected WebDriver driver;
	protected WebDriverWait wait;

	// identify WebElements
	@FindBy(xpath = "/html/body/header/div/div/div/a[2]")
	WebElement signUpButton;

	// constructor
	public LoginPage(WebDriver driver) {
		if (driver == null) {
            throw new IllegalArgumentException("WebDriver instance cannot be null.");
        }
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
		elementInteractionUtils = new ElementInteractionUtils(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// identify Action on WebElement
	public String getHompageUrl() {

		String currentURL = BaseClass.getDriver().getCurrentUrl();
		ExtentReportListener.getExtent().log(Status.PASS, "URL fetched " + currentURL);
		return currentURL;
	}

	public boolean signup() {
		// Ensure signUpButton is properly initialized
        if (signUpButton == null) {
            throw new NullPointerException("signUpButton is not initialized.");
        }
		boolean status = false;
		try {
            wait.until(ExpectedConditions.elementToBeClickable(signUpButton));
            ElementInteractionUtils.highlightElement(signUpButton);
            ElementInteractionUtils.click(signUpButton);
            System.out.println("clicked on signup button");
            status = true;
        } catch (Exception e) {
            ExtentReportListener.getExtent().log(Status.FAIL, "Signup button click failed: " + e.getMessage());
        }
        return status;
	}

}
