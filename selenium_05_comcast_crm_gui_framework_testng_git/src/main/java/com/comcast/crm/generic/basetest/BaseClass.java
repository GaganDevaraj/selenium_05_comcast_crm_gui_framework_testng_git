package com.comcast.crm.generic.basetest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class BaseClass {

	public WebDriverUtility driverUtility = new WebDriverUtility();
	public FileUtility fileUtility = new FileUtility();
	public ExcelUtility excelUtility = new ExcelUtility();
	public JavaUtility javaUtility = new JavaUtility();
	public WebDriver driver;
	
	@BeforeSuite(groups = { "smokeTesting", "regressionTesting" })
	public void connectToDB() throws Exception {
		System.out.println("-------------Establishing DB connection-------------");
	}

	@BeforeClass(groups = { "smokeTesting", "regressionTesting" })
	public void openBrowser() throws Exception {
		System.out.println("-------------Launching Browser-------------");
		String browser = fileUtility.getDataFromPropertyFile("browser");
		String url = fileUtility.getDataFromPropertyFile("url");
		driver = driverUtility.launchBrowserWithMaximizingWindow(browser);
		driverUtility.waitForPageToLoad(driver);
		driver.get(url);
	}

//	@Parameters("BROWSER")
//	@BeforeClass(groups = { "smokeTesting", "regressionTesting" })
//	public void openBrowser(String browserName) throws Exception {
//		System.out.println("-------------Launching Browser-------------");
//		String browser = browserName;
//		String url = fileUtility.getDataFromPropertyFile("url");
//		driver = driverUtility.launchBrowserWithMaximizingWindow(browser);
//		driverUtility.waitForPageToLoad(driver);
//		driver.get(url);
//	}

	@BeforeMethod(groups = { "smokeTesting", "regressionTesting" })
	public void login() throws Exception {
		System.out.println("-------------Login-------------");
		// login
		String username = fileUtility.getDataFromPropertyFile("username");
		String password = fileUtility.getDataFromPropertyFile("password");
		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();

		// verify login
		String homePageUrl = driver.getCurrentUrl();
		boolean loginVerify = homePageUrl.contains("action=index");
		Assert.assertEquals(loginVerify, true);
	}

	@AfterMethod(groups = { "smokeTesting", "regressionTesting" })
	public void logout() {
		System.out.println("-------------Logout-------------");
		// logout
		driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")).click();
		driver.findElement(By.linkText("Sign Out")).click();

		// verify logout
		String loginPageUrl = driver.getCurrentUrl();
		boolean logoutVerify = loginPageUrl.contains("action=Login");
		Assert.assertEquals(logoutVerify, true);
	}
	
	@AfterClass(groups = { "smokeTesting", "regressionTesting" })
	public void closeBrowser() {
		System.out.println("-------------Closing Browser-------------");
		driver.quit();
	}
	
	@AfterSuite(groups = { "smokeTesting", "regressionTesting" })
	public void disconnectFromDB() {
		System.out.println("-------------Closing Db connection-------------");
	}
}
