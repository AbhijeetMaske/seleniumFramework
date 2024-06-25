package com.seleniumFramework.testcases;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.seleniumFramework.common.BaseClass;
import com.seleniumFramework.pageobject.LoginPage;
import com.seleniumFramework.utilities.ElementInteractionUtils;

public class Login extends BaseClass{
	private LoginPage loginPage;
    private ElementInteractionUtils elementUtils;
	
    @BeforeMethod
    public void setUp(Method method) {
    	 super.setup(method);  // Initialize WebDriver in the parent BaseClass
         WebDriver driver = getDriver();
         elementUtils = new ElementInteractionUtils(driver);  // Ensure WebDriver is passed correctly
         loginPage = new LoginPage(driver);
    }
	 
	@Test
	//@Test(retryAnalyzer = com.seleniumFramework.utilities.RetryAnalyzer.class)
	public void gmailLogin() throws InterruptedException {	
		System.out.println("url data in login: "+url);
		getDriver().get(url);
		logger.info("url opeed");
		Thread.sleep(500);
		getDriver().manage().window().maximize();
		Thread.sleep(500);
		loginPage.getHompageUrl();
		loginPage.signup();
	}
}