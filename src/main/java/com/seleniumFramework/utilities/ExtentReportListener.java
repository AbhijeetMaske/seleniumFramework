package com.seleniumFramework.utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    private static final String SCREENSHOT_DIR = System.getProperty("user.dir") + "\\screenshots\\";
    private static ExtentReports extentReport;
    private static ExtentSparkReporter sparkReporter;
    private static ThreadLocal<ExtentTest> localExtent = new ThreadLocal<>();
    private static File reportFile;  // Class-level variable to store the report file
    
    public static ExtentTest getExtent() {
        return localExtent.get();
    }

    private static String getCurrentTimeDate() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy_MM_dd");
        String formattedDate = now.format(dateFormatter);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH_mm_ss_SSS");
        String formattedTime = now.format(timeFormatter);
        return formattedDate + "_" + formattedTime;
    }

    @Override
    public void onStart(ITestContext context) {
        extentReport = new ExtentReports();
        reportFile = new File(REPORTS_DIR + getCurrentTimeDate() + "_extentReport.html");      
        sparkReporter = new ExtentSparkReporter(reportFile);
        extentReport.attachReporter(sparkReporter);
    }

    @Override
    public void onFinish(ITestContext context) {
        if (extentReport != null) {
            extentReport.flush();
            try {
            	Desktop.getDesktop().browse(reportFile.toURI());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        localExtent.remove();
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("On test start called...." + result.getMethod().getMethodName());
        ExtentTest extentTest = extentReport.createTest(result.getMethod().getMethodName());
        localExtent.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test pass called...." + result.getMethod().getMethodName());
        ExtentTest extentTest = localExtent.get();
        if (extentTest != null) {
            extentTest.log(Status.PASS, "Test passed: " + result.getMethod().getMethodName());
        } else {
            System.err.println("ExtentTest is null in onTestSuccess for method: " + result.getMethod().getMethodName());
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        System.out.println("On test failure called...." + methodName);
        ExtentTest extentTest = localExtent.get();
        if (extentTest != null) {
            String screenshotPath = null;
            try {
                screenshotPath = UtilMethods.captureScreenShot(BaseClass.getDriver(), methodName);
            } catch (IOException e) {
                e.printStackTrace();
            }
            extentTest.log(Status.FAIL, "Test failed: " + methodName);
            extentTest.log(Status.FAIL, result.getThrowable(),
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        } else {
            System.err.println("ExtentTest is null in onTestFailure for method: " + methodName);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test skipped called...." + result.getMethod().getMethodName());
        ExtentTest extentTest = localExtent.get();
        if (extentTest != null) {
            extentTest.log(Status.SKIP, "Test skipped: " + result.getMethod().getMethodName());
        } else {
            System.err.println("ExtentTest is null in onTestSkipped for method: " + result.getMethod().getMethodName());
        }
    }
}
