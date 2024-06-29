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
import com.seleniumFramework.common.BaseClass;

public class ExtentReportListener implements ITestListener {

	public static ExtentReports extentReport;
	private static final Logger logger = LogManager.getLogger(ElementInteractionUtils.class);

	public static File file = new File(
			System.getProperty("user.dir") + "\\extentReports\\" + DateTimeUtils.getCurrentDateTime() + "_extentReport.html");

	public static ExtentSparkReporter sparkReporter;
	public static ExtentTest extentTest;

	public static ThreadLocal<ExtentTest> localExtent = new ThreadLocal<>();

	public static ExtentTest getExtent() {
		return localExtent.get();
	}

	@Override
	public void onStart(ITestContext context) {
		logger.info("Starting Test Suite: " + context.getName());
        extentReport = new ExtentReports();
        sparkReporter = new ExtentSparkReporter(file);

//		// Configuration to change look and feel of the report
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
            Desktop.getDesktop().browse(file.toURI());
            logger.info("Test report opened in browser: " + file.getAbsolutePath());
        } catch (IOException e) {
            logger.error("Error opening test report in browser", e);
        }
	}

	@Override
	public void onTestStart(ITestResult result) {
		logger.info("Starting Test: " + result.getMethod().getMethodName());
        extentTest = extentReport.createTest(result.getMethod().getMethodName());
        localExtent.set(extentTest);

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		logger.info("Test Passed: " + result.getMethod().getMethodName());
        localExtent.get().log(Status.PASS, "Test passed successfully");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
        logger.error("Test Failed: " + methodName);
        String screenshot = null;
        try {
            screenshot = UtilMethods.captureScreenShot(BaseClass.getDriver(), methodName);
            logger.info("Screenshot captured for failed test: " + methodName);
        } catch (IOException e) {
            logger.error("Error capturing screenshot for failed test: " + methodName, e);
        }

        localExtent.get().log(Status.FAIL, "Test failed: " + result.getThrowable());
        try {
            localExtent.get().log(Status.FAIL, "Test Failed: ", MediaEntityBuilder.createScreenCaptureFromPath(screenshot).build());
        } catch (Exception e) {
            logger.error("Error attaching screenshot to Extent report", e);
        }
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		logger.warn("Test Skipped: " + result.getMethod().getMethodName());
        localExtent.get().log(Status.SKIP, "Test skipped: " + result.getThrowable());
	}

}
