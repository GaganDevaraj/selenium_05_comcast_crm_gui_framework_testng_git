package com.comcast.crm.contacttest;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.comcast.crm.generic.basetest.BaseClass;
import com.comcast.crm.objectrepositoryutility.ContactInformationPage;

public class TS_02_CreateContactTest extends BaseClass {

	@Test(groups = { "smokeTesting", "regressionTesting" })
	public void createContactTest() throws Exception {

		String lastName = excelUtility.getDataFromExcelFile("contact", 1, 2);

		// navigate to Contact page
		driver.findElement(By.linkText("Contacts")).click();

		// verifying the CONTACT page
		String contactPageUrl = driver.getCurrentUrl();
		boolean verifyContactPage = contactPageUrl.contains("module=Contacts");
		Assert.assertEquals(verifyContactPage, true);

		// create new contact
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();

		// enter last name
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(lastName);

		// save contact
		driver.findElement(By.xpath("//input[@accesskey='S']")).click();

		// verify contact from Header
		ContactInformationPage contactInformationPage = new ContactInformationPage(driver);
		String createdContactName = contactInformationPage.getContactHeader().getText();
		boolean verifyContactName = createdContactName.contains(lastName);
		Assert.assertEquals(verifyContactName, true);

		// verify last name
		String actLastName = contactInformationPage.getLastName().getText();
		boolean verifyLastName = lastName.equals(actLastName);
		Assert.assertEquals(verifyLastName, true);
	}

	@Test(groups = "regressionTesting")
	public void createContactWithSupportDateTest() throws Exception {
		String lastName = excelUtility.getDataFromExcelFile("contact", 1, 2);

		// navigate to CONTACT page
		driver.findElement(By.linkText("Contacts")).click();

		// verifying the CONTACT page
		String contactPageUrl = driver.getCurrentUrl();
		boolean verifyContactPage = contactPageUrl.contains("module=Contacts");
		Assert.assertEquals(verifyContactPage, true);

		// create new contact
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();

		// enter last name
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(lastName);

		// enter start date
		String currDate = javaUtility.getCurrentDateDDMMYYYY();
		driver.findElement(By.xpath("//input[@name='support_start_date']")).clear();
		driver.findElement(By.xpath("//input[@name='support_start_date']")).sendKeys(currDate);

		// enter end date
		String futureDate = javaUtility.getPostMonthDateDDMMYYYY(1);
		driver.findElement(By.xpath("//input[@name='support_end_date']")).clear();
		driver.findElement(By.xpath("//input[@name='support_end_date']")).sendKeys(futureDate);

		// save contact
		driver.findElement(By.xpath("//input[@accesskey='S']")).click();

		// verify contact from Header
		String createdContactName = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		boolean verifyContactName = createdContactName.contains(lastName);
		Assert.assertEquals(verifyContactName, true);

		// verify last name
		String actLastName = driver.findElement(By.id("dtlview_Last Name")).getText();
		boolean verifyLastName = lastName.equals(actLastName);
		Assert.assertEquals(verifyLastName, true);

		// verify start date and end date
		String enteredStartDate = driver.findElement(By.id("dtlview_Support Start Date")).getText();
		System.out.println("Given start date: " + currDate);
		System.out.println("Added start date: " + enteredStartDate);

		String enteredEndDate = driver.findElement(By.id("dtlview_Support End Date")).getText();
		System.out.println("Given end date: " + futureDate);
		System.out.println("Added end date: " + enteredEndDate);

		boolean verifyStartDate = (currDate.toString()).contains(enteredStartDate);
		Assert.assertEquals(verifyStartDate, true);

		boolean verifyEndDate = (futureDate.toString()).contains(enteredEndDate);
		Assert.assertEquals(verifyEndDate, true);
	}

	@Test(groups = "regressionTesting")
	public void createContactWithOrganizationTest() throws Exception {
		// reading data from excel file
		String orgName = excelUtility.getDataFromExcelFile("org", 1, 2) + javaUtility.getRandomNumber();
		String lastName = excelUtility.getDataFromExcelFile("contact", 1, 2);

		// navigate to ORGANIZATION page
		driver.findElement(By.linkText("Organizations")).click();

		// verifying the ORGANIZATION page
		String orgPageUrl = driver.getCurrentUrl();
		if (orgPageUrl.contains("module=Accounts")) {
			System.out.println("Landed into organization page: PASS");
		} else {
			System.out.println("Landed into organization page: FAIL");
		}

		// create new organization
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();

		// enter organization name
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(orgName);

		// save organization
		driver.findElement(By.xpath("//input[@accesskey='S']")).click();

		// verify organization name from Header
		String createdOrgName = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		boolean verifyOrganizationPage = createdOrgName.contains(orgName);
		Assert.assertEquals(verifyOrganizationPage, true);

		// navigate to CONTACT page
		driver.findElement(By.linkText("Contacts")).click();

		// verifying the CONTACT page
		String contactPageUrl = driver.getCurrentUrl();
		boolean verifyContactPage = contactPageUrl.contains("module=Contacts");
		Assert.assertEquals(verifyContactPage, true);

		// create new contact
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();

		// enter last name
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(lastName);

		// click on search organization button
		driver.findElement(By.xpath("//img[@src='themes/softed/images/select.gif']")).click();

		// switching from parent window to child window

		// switch to child window
		driverUtility.switchToTabOnURL(driver, "module=Accounts");

		// search for organization
		driver.findElement(By.id("search_txt")).sendKeys(orgName);

		// click on search Now button
		driver.findElement(By.xpath("//input[@name='search']")).click();

		// click on searched result
		driver.findElement(By.xpath("//a[contains(text(),'" + orgName + "')]")).click();

		// switch to parent window
		driverUtility.switchToTabOnURL(driver, "module=Contacts");

		// save contact
		driver.findElement(By.xpath("//input[@accesskey='S']")).click();

		// verify contact from Header
		String createdContactName = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		boolean verifyContactName = createdContactName.contains(lastName);
		Assert.assertEquals(verifyContactName, true);

		// verify last name
		String actLastName = driver.findElement(By.id("dtlview_Last Name")).getText();
		boolean verifyLastName = lastName.equals(actLastName);
		Assert.assertEquals(verifyLastName, true);

	}
}
