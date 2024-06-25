package com.seleniumFramework.testcases;

import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.seleniumFramework.common.BaseClass;
import com.seleniumFramework.pageobject.youtubePage;
import com.seleniumFramework.utilities.ElementInteractionUtils;

public class youtube extends BaseClass{
    public ElementInteractionUtils elementUtils;
    public youtubePage yp;
    private static final Logger logger = LogManager.getLogger(youtube.class);
    
    @BeforeMethod
    public void setUp(Method method) {
        super.setup(method);  // Initialize the driver in the parent BaseClass

     // Assign the driver from the BaseClass
        WebDriver driver = getDriver();
        
        // Initialize the WebElementInteractionUtils with the WebDriver
        elementUtils = new ElementInteractionUtils(driver);
        
        // Initialize the LoginPage with the WebDriver
        yp = new youtubePage(driver);
    }
	
		
	@Test
	//@Test(retryAnalyzer = com.seleniumFramework.utilities.RetryAnalyzer.class)
	public void youtubePage() throws InterruptedException {	
		getDriver().get(url);
		logger.info("url opened");
		yp.getHompageUrl();
	}
}