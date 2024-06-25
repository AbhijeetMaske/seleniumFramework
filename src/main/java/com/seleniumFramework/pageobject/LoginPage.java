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
	
	protected WebDriver driver;
	protected WebDriverWait wait;
	
	// constructor
	public LoginPage(WebDriver webDriver) {
		this.driver = BaseClass.getDriver();
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	// identify WebElements
		@FindBy(xpath="/html/body/header/div/div/div/a[2]")
		WebElement signUpButton;
		
		@FindBy(xpath="//span[text()='Organization Details']")
		WebElement OrganizationDetails;

	
	ElementInteractionUtils elementInteractionUtils = new ElementInteractionUtils(driver);
	// identify Action on WebElement
	public String getHompageUrl() {

		String currentURL = driver.getCurrentUrl();
		System.out.println(currentURL);
		ExtentReportListener.getExtent().log(Status.PASS, "URL fetched " + currentURL);
		return currentURL;
	}

	public boolean signup() throws InterruptedException {
		// Ensure signUpButton is properly initialized
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