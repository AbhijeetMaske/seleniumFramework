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
// [2025-08-23] Removed inheritance from BaseClass to keep POM pure
import com.seleniumFramework.utilities.ElementInteractionUtils;
// [2025-08-23] Keep reporting in tests/listeners; remove listener usage in POM

public class LoginPage {

	protected WebDriver driver;
	protected WebDriverWait wait;

	// constructor
	public LoginPage(WebDriver webDriver) {
		// [2025-08-23] Use the driver provided by the test, not BaseClass
		this.driver = webDriver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// [2025-08-23] Reporting should be handled by tests/listeners; removed Extent nodes here
	
	// identify WebElements
	@FindBy(xpath = "/html/body/header/div/div/div/a[2]")
	WebElement signUpButton;

	@FindBy(xpath = "//span[text()='Organization Details']")
	WebElement OrganizationDetails;

	ElementInteractionUtils elementInteractionUtils = new ElementInteractionUtils(driver);

	// identify Action on WebElement
	public String getHompageUrl() {

		String currentURL = driver.getCurrentUrl();
		return currentURL;
	}

	public boolean signup() throws InterruptedException {
		boolean status = false;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(signUpButton));
			ElementInteractionUtils.highlightElement(signUpButton);
			ElementInteractionUtils.click(signUpButton);
			status = true;
		} catch (Exception e) {
			// [2025-08-23] Logging should be done at test level; keep POM silent or throw
		}
		return status;
	}

}