package com.seleniumFramework.testcases;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.seleniumFramework.common.BaseClass;
import com.seleniumFramework.pageobject.LoginPage;

public class Login extends BaseClass{
	private LoginPage loginPage;
	
    @BeforeMethod
    public void setUp(Method method) {
    	 super.setup(method);  // Initialize WebDriver in the parent BaseClass
         WebDriver driver = getDriver();
         loginPage = new LoginPage(driver);
    }
	 
	@Test
	//@Test(retryAnalyzer = com.seleniumFramework.utilities.RetryAnalyzer.class)
	public void gmailLogin() throws InterruptedException {	
		getDriver().get(url);
		logger.info("url opeed");
		Thread.sleep(500);
		getDriver().manage().window().maximize();
		Thread.sleep(500);
		loginPage.getHompageUrl();
		loginPage.signup();
	}
	@Test
	public void gmailStatus() {
		System.out.println("XYZ");
	}
}