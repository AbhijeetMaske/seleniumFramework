package com.seleniumFramework.utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.seleniumFramework.common.BaseClass;

public class ExtentReportListener implements ITestListener {

    private static final String REPORTS_DIR = System.getProperty("user.dir") + "\\extentReports\\";
    private static ExtentReports extentReport;
    private static ExtentSparkReporter sparkReporter;
    private static ThreadLocal<ExtentTest> localExtent = new ThreadLocal<>();
    private static File reportFile;  // Class-level variable to store the report file
    private static final Logger logger = (Logger) LogManager.getLogger(ExtentReportListener.class);
    
    public static ExtentTest getExtent() {
        return localExtent.get();
    }

    @Override
    public void onStart(ITestContext context) {
        extentReport = new ExtentReports();
        reportFile = new File(REPORTS_DIR + DateTimeUtils.getCurrentDateTime() + "_extentReport.html");      
        sparkReporter = new ExtentSparkReporter(reportFile);
        extentReport.attachReporter(sparkReporter);
        logger.info("Extent report initialized at: " + reportFile.getAbsolutePath());
    }

    @Override
    public void onFinish(ITestContext context) {
        if (extentReport != null) {
            extentReport.flush();
            try {
            	Desktop.getDesktop().browse(reportFile.toURI());
            	logger.info("Extent report opened: " + reportFile.getAbsolutePath());
            } catch (IOException e) {
            	 logger.error("Error opening extent report: " + e.getMessage(), e);
            }
        }
        localExtent.remove();
    }

    @Override
    public void onTestStart(ITestResult result) {
    	logger.info("Test started: " + result.getMethod().getMethodName());
        ExtentTest extentTest = extentReport.createTest(result.getMethod().getMethodName());
        localExtent.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
    	logger.info("Test passed: " + result.getMethod().getMethodName());
        ExtentTest extentTest = localExtent.get();
        if (extentTest != null) {
            extentTest.log(Status.PASS, "Test passed: " + result.getMethod().getMethodName());
        } else {
        	logger.error("ExtentTest is null in onTestSuccess for method: " + result.getMethod().getMethodName());
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        logger.error("Test failed: " + methodName);
        ExtentTest extentTest = localExtent.get();
        if (extentTest != null) {
            String screenshotPath = null;
            try {
            	// Ensure the browser is initialized before taking a screenshot
                if (BaseClass.getDriver() != null) {
                	screenshotPath = ScreenshotUtils.captureScreenShot(methodName);
                    extentTest.log(Status.FAIL, "Test failed: " + methodName);
                    extentTest.log(Status.FAIL, result.getThrowable(),
                            MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
                    logger.error("Screenshot saved at: " + screenshotPath);
                } else {
                	extentTest.log(Status.FAIL, "Browser is not initialized, cannot capture screenshot.");
                    logger.error("Browser is not initialized, cannot capture screenshot.");
                }
            } catch (Exception e) {
            	 logger.error("Error capturing screenshot: " + e.getMessage(), e);
            }
        } else {
            System.err.println("ExtentTest is null in onTestFailure for method: " + methodName);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
    	logger.warn("Test skipped: " + result.getMethod().getMethodName());
        ExtentTest extentTest = localExtent.get();
        if (extentTest != null) {
            extentTest.log(Status.SKIP, "Test skipped: " + result.getMethod().getMethodName());
        } else {
            System.err.println("ExtentTest is null in onTestSkipped for method: " + result.getMethod().getMethodName());
        }
    }
}
