package com.comcast.crm.generic.webdriverutility;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class UtilityClassObject {

	public static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();

	public static ThreadLocal<ExtentReports> extentReports = new ThreadLocal<ExtentReports>();
	public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	public static WebDriver getWebDriver() {
		return webDriver.get();
	}

	public static void setWebDriver(WebDriver webDriver) {
		UtilityClassObject.webDriver.set(webDriver);
	}

	public static ExtentTest getExtentTest() {
		return extentTest.get();
	}

	public static void setExtentTest(ExtentTest extentTest) {
		UtilityClassObject.extentTest.set(extentTest);
	}

	public static ExtentReports getExtentReports() {
		return extentReports.get();
	}

	public static void setExtentReports(ExtentReports extentReports) {
		UtilityClassObject.extentReports.set(extentReports);
		;
	}
}
