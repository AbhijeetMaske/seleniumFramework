package com.seleniumFramework.utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.seleniumFramework.common.BaseClass;

public class ExtentReportListener implements ITestListener {

	private static ExtentReports extentReport;
    private static ThreadLocal<ExtentTest> testThreadLocal = new ThreadLocal<>();
    private static final Logger logger = LogManager.getLogger(ExtentReportListener.class);    
    private static final String REPORT_PATH = System.getProperty("user.dir") + "/extentReports/";
    private static final String REPORT_NAME = "ExtentReport_" + DateTimeUtils.getCurrentDateTime() + ".html";

	@Override
	public void onStart(ITestContext context) {
        logger.info("Starting Test Suite: " + context.getName());
        extentReport = new ExtentReports();
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(REPORT_PATH + REPORT_NAME);
        
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setDocumentTitle("Automation Test Report");
        sparkReporter.config().setReportName("Test Execution Report");        
        extentReport.attachReporter(sparkReporter);

        logger.info("ExtentReport initialized with SparkReporter");
    }

	@Override
    public void onFinish(ITestContext context) {
        logger.info("Finishing Test Suite: " + context.getName());
        extentReport.flush();
        try {
            Desktop.getDesktop().browse(new File(REPORT_PATH + REPORT_NAME).toURI());
            logger.info("Test report opened in browser: " + REPORT_PATH + REPORT_NAME);
        } catch (IOException e) {
            logger.error("Error opening test report in browser", e);
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("Starting Test: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("Test Passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        logger.error("Test Failed: " + methodName);
        String screenshotPath = null;
        
        try {
            screenshotPath = UtilMethods.captureScreenShot(BaseClass.getDriver(), methodName);
            logger.info("Screenshot captured for failed test: " + methodName);
        } catch (IOException e) {
            logger.error("Error capturing screenshot for failed test: " + methodName, e);
        }

        testThreadLocal.get().log(Status.FAIL, "Test failed: " + result.getThrowable());
        try {
            testThreadLocal.get().log(Status.FAIL, "Test Failed: ", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        } catch (Exception e) {
            logger.error("Error attaching screenshot to Extent report", e);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn("Test Skipped: " + result.getMethod().getMethodName());
        testThreadLocal.get().log(Status.SKIP, "Test skipped: " + result.getThrowable());
    }

    public static ExtentTest getCurrentTest() {
        return testThreadLocal.get();
    }

    public static ExtentTest createParentTest(String name, String description) {
        ExtentTest parentTest = extentReport.createTest(name, description);
        testThreadLocal.set(parentTest);
        return parentTest;
    }

    public static ExtentTest createChildTest(String name) {
        ExtentTest childTest = getCurrentTest().createNode(name);
        testThreadLocal.set(childTest);
        return childTest;
    }

    public static void tags(String... tags) {
    	ExtentTest currentTest = testThreadLocal.get();
        if (currentTest != null) {
            for (String tag : tags) {
                currentTest.assignCategory(tag.toLowerCase());
            }
        }
    }
    public static void author(String... authors) {
        ExtentTest currentTest = testThreadLocal.get();
        if (currentTest != null) {
            currentTest.assignAuthor(authors);
        }
    }
}