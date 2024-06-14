package com.seleniumFramework.testcases;

import org.testng.annotations.Test;
import com.seleniumFramework.common.BaseClass;

public class Login extends BaseClass{
	@Test
	//@Test(retryAnalyzer = com.seleniumFramework.utilities.RetryAnalyzer.class)
	public void verifyLoginisworking() throws InterruptedException {
		
		getDriver().get(url);
		logger.info("url opened");
		Thread.sleep(500);
		getDriver().manage().window().maximize();
		Thread.sleep(500);
		
		System.out.println("gmail Home page");
		
	}
}