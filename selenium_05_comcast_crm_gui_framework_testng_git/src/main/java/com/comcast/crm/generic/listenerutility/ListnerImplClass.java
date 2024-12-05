package com.comcast.crm.generic.listenerutility;

import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;

public class ListnerImplClass implements ITestListener, ISuiteListener {

	@Override
	public void onStart(ISuite suite) {
		ISuiteListener.super.onStart(suite);
		// Spark report config
		String time = new Date().toString().replace(" ", "_").replace(":", "_");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter("advanced-report/report"+time+".html");
		sparkReporter.config().setDocumentTitle("CRM Test Suite Report");
		sparkReporter.config().setReportName("CRM Name");
		sparkReporter.config().setTheme(Theme.STANDARD);

		// add ENV information and create test
		ExtentReports extentReports = new ExtentReports();
		UtilityClassObject.setExtentReports(extentReports);
		extentReports.attachReporter(sparkReporter);
		extentReports.setSystemInfo("OS", "Mac Seqoia 15");
		extentReports.setSystemInfo("Browser", "Chrome");
	}

	@Override
	public void onFinish(ISuite suite) {
		ISuiteListener.super.onFinish(suite);
		UtilityClassObject.getExtentReports().flush();
	}

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("===========" + result.getMethod().getMethodName() + "========Started=======");
		ExtentTest extentTest = UtilityClassObject.getExtentReports().createTest(result.getMethod().getMethodName());
		UtilityClassObject.setExtentTest(extentTest);
		extentTest.log(Status.INFO, result.getMethod().getMethodName()+"===> STARTED");
	}

//	@Override
//	public void onTestFailure(ITestResult result) {
//
//		String methodName = result.getMethod().getMethodName();
//		System.out.println(WebDriverUtility.tdriver);
//		TakesScreenshot screenshot = (TakesScreenshot) WebDriverUtility.tdriver;
//		File srcFolder = screenshot.getScreenshotAs(OutputType.FILE);
//		String time = new Date().toString().replace(" ", "_").replace(":", "_");
//		File desFolder = new File("screenshots/" + time + "_" + methodName + ".png");
//		
//		try {
//			FileHandler.copy(srcFolder, desFolder);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
	@Override
	public void onTestFailure(ITestResult result) {

		String methodName = result.getMethod().getMethodName();
		System.out.println(UtilityClassObject.getWebDriver());
		
		TakesScreenshot screenshot = (TakesScreenshot) UtilityClassObject.getWebDriver();
		String filePath = screenshot.getScreenshotAs(OutputType.BASE64);
		
		String time = new Date().toString().replace(" ", "_").replace(":", "_");
		UtilityClassObject.getExtentTest().addScreenCaptureFromBase64String(filePath, methodName+"_"+time);
		UtilityClassObject.getExtentTest().log(Status.FAIL, result.getMethod().getMethodName()+"===> FAILED");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("===========" + result.getMethod().getMethodName() + "========End=======");
		UtilityClassObject.getExtentTest().log(Status.PASS, result.getMethod().getMethodName()+"===> COMPLETED");
	}
}
