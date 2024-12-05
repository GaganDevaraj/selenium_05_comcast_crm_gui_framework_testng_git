package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreateNewOrganizationPage {

	WebDriver driver;

	public CreateNewOrganizationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "accountname")
	private WebElement organizationNameEdit;

	@FindBy(xpath = "//input[@accesskey='S']")
	private WebElement saveBtn;

	@FindBy(xpath = "//select[@name='industry']")
	private WebElement industryDropDown;

	@FindBy(xpath = "//select[@name='accounttype']")
	private WebElement typeDropDown;

	public WebElement getOrganizationNameEdit() {
		return organizationNameEdit;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}

	public void createOrg(String orgName) {
		organizationNameEdit.sendKeys(orgName);
		saveBtn.click();
	}

	public void createOrg(String orgName, String industry) {
		organizationNameEdit.sendKeys(orgName);

		Select selectIndustry = new Select(industryDropDown);
		selectIndustry.selectByVisibleText(industry);
		saveBtn.click();
	}

	public void createOrg(String orgName, String industry, String type) {
		organizationNameEdit.sendKeys(orgName);

		Select selectIndustry = new Select(industryDropDown);
		selectIndustry.selectByVisibleText(industry);

		Select selectType = new Select(typeDropDown);
		selectType.selectByVisibleText(type);

		saveBtn.click();
	}

}
