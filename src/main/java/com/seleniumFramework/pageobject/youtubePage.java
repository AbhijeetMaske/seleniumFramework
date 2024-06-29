package com.seleniumFramework.pageobject;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.Status;
import com.seleniumFramework.utilities.ElementInteractionUtils;
import com.seleniumFramework.utilities.ExtentReportListener;

public class youtubePage {
	protected WebDriver driver;
	protected WebDriverWait wait;

	// constructor
	public youtubePage(WebDriver driver) {
		if (driver == null) {
            throw new IllegalArgumentException("WebDriver instance cannot be null.");
        }		
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// identify WebElements
		@FindBy(xpath = "/html/body/header/div/div/div/a[2]")
		WebElement signUpButton;
		
		@FindBy (xpath = "//*[@id=\"chips\"]")
		WebElement feedFilter;
	
	public String getHompageUrl() {
		System.out.println("homepage");
		String currentURL = driver.getCurrentUrl();
		ExtentReportListener.getExtent().log(Status.PASS, "URL fetched " + currentURL);
		return currentURL;
	}
	
	public void feedFilter() {
		ElementInteractionUtils.selectByValue(feedFilter, "Music");
		
	}

}
