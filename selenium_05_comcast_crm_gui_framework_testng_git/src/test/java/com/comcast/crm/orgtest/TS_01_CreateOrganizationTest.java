package com.comcast.crm.orgtest;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.comcast.crm.generic.basetest.BaseClass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.objectrepositoryutility.HomePage;

public class TS_01_CreateOrganizationTest extends BaseClass {

	@Test(groups = "smokeTesting")
	public void createOrgTest() throws Exception {

		ExtentTest extentTest = UtilityClassObject.getExtentTest();
		
		// reading data from excel file
		extentTest.log(Status.INFO, "reading data from excel file");
		String orgName = excelUtility.getDataFromExcelFile("org", 1, 2) + javaUtility.getRandomNumber();

		// navigate to ORGANIZATION page
		extentTest.log(Status.INFO, "navigate to ORGANIZATION page");
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.getOrganizationsLink().click();

		// verifying the ORGANIZATION page
		extentTest.log(Status.INFO, "verifying the ORGANIZATION page");
		String orgPageUrl = driver.getCurrentUrl();
		boolean verifyOrganizaton = orgPageUrl.contains("module=Accounts");
		Assert.assertEquals(verifyOrganizaton, true);

		// create new organization
		extentTest.log(Status.INFO, "create new organization");
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();

		// enter organization name
		extentTest.log(Status.INFO, "enter organization name");
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(orgName);

		// save organization
		extentTest.log(Status.INFO, "save organization");
		driver.findElement(By.xpath("//input[@accesskey='S']")).click();

		// verify organization name from Header
		extentTest.log(Status.INFO, "verify organization name from Header");
		String createdOrgName = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();

		boolean result = createdOrgName.contains(orgName);
		Assert.assertEquals(result, false);
	}

	@Test(groups = "regressionTesting")
	public void createOrganizationWithTypeTest() throws Exception {
		// reading data from excel file
		String orgName = excelUtility.getDataFromExcelFile("org", 1, 2) + javaUtility.getRandomNumber();
		String industry = excelUtility.getDataFromExcelFile("org", 4, 3);
		String type = excelUtility.getDataFromExcelFile("org", 4, 4);

		// navigate to ORGANIZATION page
		driver.findElement(By.linkText("Organizations")).click();

		// verifying the ORGANIZATION page
		String orgPageUrl = driver.getCurrentUrl();
		boolean verifyOrganizaton = orgPageUrl.contains("module=Accounts");
		Assert.assertEquals(verifyOrganizaton, true);

		// create new organization
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();

		// enter organization name
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(orgName);

		// select industry
		WebElement industryElement = driver.findElement(By.xpath("//select[@name='industry']"));
		driverUtility.selectValueFromDropdownByVisibleText(industryElement, industry);

		// select type
		WebElement typeElement = driver.findElement(By.xpath("//select[@name='accounttype']"));
		driverUtility.selectValueFromDropdownByVisibleText(typeElement, type);

		// save organization
		driver.findElement(By.xpath("//input[@accesskey='S']")).click();

		// verify organization name from Header
		String createdOrgName = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();

		boolean result = createdOrgName.contains(orgName);
		Assert.assertEquals(result, true);
	}

	@Test(groups = "regressionTesting")
	public void createOrganizationWithPhoneNumberTest() throws Exception {

		String orgName = excelUtility.getDataFromExcelFile("org", 4, 2) + javaUtility.getRandomNumber();
		String industry = excelUtility.getDataFromExcelFile("org", 4, 3);
		String type = excelUtility.getDataFromExcelFile("org", 4, 4);
		String phone = excelUtility.getDataFromExcelFile("org", 7, 3);

		// navigate to ORGANIZATION page
		driver.findElement(By.linkText("Organizations")).click();

		// verifying the ORGANIZATION page
		String orgPageUrl = driver.getCurrentUrl();

		boolean result = orgPageUrl.contains("module=Accounts");
		Assert.assertEquals(result, true);

		// create new organization
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();

		// enter organization name
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(orgName);

		// select industry
		WebElement industryElement = driver.findElement(By.xpath("//select[@name='industry']"));
		Select selectIndustry = new Select(industryElement);
		selectIndustry.selectByVisibleText(industry);

		// select type
		WebElement typeElement = driver.findElement(By.xpath("//select[@name='accounttype']"));
		Select selectType = new Select(typeElement);
		selectType.selectByVisibleText(type);

		// enter phone number
		driver.findElement(By.xpath("//input[@name='phone']")).sendKeys(phone);

		// save organization
		driver.findElement(By.xpath("//input[@accesskey='S']")).click();

		// verify organization name from Header
		String createdOrgName = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();

		boolean verifiedResult = createdOrgName.contains(orgName);
		Assert.assertEquals(verifiedResult, true);

		// verify phone number
		String createdPhone = driver.findElement(By.id("dtlview_Phone")).getText();

		boolean verifyPhone = phone.equals(createdPhone);
		Assert.assertEquals(verifyPhone, true);
	}
}
