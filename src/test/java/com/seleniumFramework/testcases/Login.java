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
    public ElementInteractionUtils elementUtils;
    private WebDriver driver;
    
    @BeforeMethod
    public void setUp(Method method) {
        super.setup(method);  // Initialize the driver in the parent BaseClass

     // Assign the driver from the BaseClass
        WebDriver driver = getDriver();
        
        // Initialize the WebElementInteractionUtils with the WebDriver
        elementUtils = new ElementInteractionUtils(driver);
        
        // Initialize the LoginPage with the WebDriver
        loginPage = new LoginPage(driver);
    }
	
		
	@Test
	//@Test(retryAnalyzer = com.seleniumFramework.utilities.RetryAnalyzer.class)
	public void gmailLogin() throws InterruptedException {
		
		getDriver().get(url);
		logger.info("url opened");
		Thread.sleep(500);
		getDriver().manage().window().maximize();
		Thread.sleep(500);		
		System.out.println("gmail Home page");
		Thread.sleep(500);	
		System.out.println("clicking on signup");
		loginPage.getHompageUrl();
		loginPage.signup();
		
		//Assert.assertTrue(status,"sign up click failed");
		
	}
}