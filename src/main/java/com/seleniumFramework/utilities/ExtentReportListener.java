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
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.model.ScreenCapture;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.seleniumFramework.common.BaseClass;

public class ExtentReportListener implements ITestListener {

	public static ExtentReports extentReport;

	public static File file = new File(
			System.getProperty("user.dir") + "\\extentReports\\" + getCurrentTimeDate() + "_extentReport.html");

	public static ExtentSparkReporter sparkReporter;
	public static ExtentTest extentTest;

	public static ThreadLocal<ExtentTest> localExtent = new ThreadLocal<>();

	public static ExtentTest getExtent() {
		return localExtent.get();
	}

	public static String getCurrentTimeDate() {
		LocalDateTime now = LocalDateTime.now();

		// Format date
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy_MM_dd");
		String formattedDate = now.format(dateFormatter);

		// Format time with milliseconds
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH_mm_ss_SSS");
		String formattedTime = now.format(timeFormatter);

		// Concatenate formatted date and time
		String result = formattedDate + "_" + formattedTime;
		return result;
	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println("On start called....");
		extentReport = new ExtentReports();
		sparkReporter = new ExtentSparkReporter(file);
//		extentReport.setSystemInfo("machine:", "testlp1");
//		extentReport.setSystemInfo("os","windows 10");
//		extentReport.setSystemInfo("browser:", "chrome");

		// configuration to change look and feel of report
//		sparkReporter.config().setDocumentTitle("Extent Listner Report");
//		sparkReporter.config().setReportName("This is my first report");
		extentReport.attachReporter(sparkReporter);

	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("On Finished Called....");
		extentReport.flush();
		try {
			Desktop.getDesktop().browse(file.toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("on test start called...." + result.getMethod().getMethodName());
		extentTest = extentReport.createTest(result.getMethod().getMethodName());
		localExtent.set(extentTest);

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("Test pass called...." + result.getMethod().getMethodName());
		localExtent.get().log(Status.PASS, "Test is passed");

	}

	@Override
	public void onTestFailure(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		System.out.println("on test FAILED called...." + methodName);
		String screenshot = null;
		try {
			screenshot = UtilMethods.captureScreenShot(BaseClass.getDriver(), methodName);
		} catch (IOException e) {
			// TODO Auto-generated` catch block
			e.printStackTrace();
		}

		localExtent.get().log(Status.FAIL, "Test is Failed : ");
//		localExtent.get().log(Status.FAIL,MediaEntityBuilder.createScreenCaptureFromPath(screenshot),"methodName");

		localExtent.get().log(Status.FAIL, "Test Failed ", result.getThrowable(),
				MediaEntityBuilder.createScreenCaptureFromPath(screenshot).build());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

}
