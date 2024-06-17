package com.seleniumFramework.utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RetryAnalyzer implements IRetryAnalyzer {
	private static final Logger logger = LogManager.getLogger(RetryAnalyzer.class);
    private int retryCount = 0;
    private static final int maxRetryCount = 3;

    /********************************************************************************************
     * Determines whether to retry a failed test case based on the maximum retry count.
     *
     * @param result The result of the test method that just ran.
     * @return True if the test should be retried, false otherwise.
     *
     * @author Abhijeet Maske Created June 27,2023
     * @version 1.0 June 27,2023
     ********************************************************************************************/
    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            logger.info("Retrying test case: " + result.getMethod().getMethodName() +
                    ", Retry attempt: " + retryCount);
            return true;
        }
        return false;
    }
}
