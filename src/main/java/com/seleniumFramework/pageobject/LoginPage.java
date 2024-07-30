package com.seleniumFramework.pageobject;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.seleniumFramework.common.BaseClass;
import com.seleniumFramework.utilities.ElementInteractionUtils;
import com.seleniumFramework.utilities.ExtentReportListener;

public class LoginPage extends BaseClass {

	protected WebDriver driver;
	protected WebDriverWait wait;

	// constructor
	public LoginPage(WebDriver webDriver) {
		this.driver = BaseClass.getDriver();
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Create parent test node
	ExtentTest parentTest = ExtentReportListener.createParentTest("Login Test", "Verify that login functionality works")
			.assignAuthor("Abhijeet Maske");

	// Assign tags
	// ExtentReportListener.tags("Regression", "Smoke");

	// Create a child node for the actual test steps
	ExtentTest test = parentTest.createNode("Login Functionality Test");

	LoginPage loginPage = new LoginPage(getDriver());

	// identify WebElements
	@FindBy(xpath = "/html/body/header/div/div/div/a[2]")
	WebElement signUpButton;

	@FindBy(xpath = "//span[text()='Organization Details']")
	WebElement OrganizationDetails;

	ElementInteractionUtils elementInteractionUtils = new ElementInteractionUtils(driver);

	// identify Action on WebElement
	public String getHompageUrl() {

		String currentURL = driver.getCurrentUrl();
		test.log(Status.PASS, "URL fetched " + currentURL);
		return currentURL;
	}

	public boolean signup() throws InterruptedException {
		// Ensure signUpButton is properly initialized
		boolean status = false;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(signUpButton));
			ElementInteractionUtils.highlightElement(signUpButton);
			ElementInteractionUtils.click(signUpButton);
			status = true;
		} catch (Exception e) {
			logger.error("Test case verifyLoginIsWorking failed: " + e.getMessage());
			test.log(Status.FAIL, "Test case verifyLoginIsWorking failed: " + e.getMessage());
		}
		return status;
	}

}