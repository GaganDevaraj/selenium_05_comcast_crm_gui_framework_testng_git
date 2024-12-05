package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class OrganizationsPage {
	WebDriver driver;

	public OrganizationsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//img[@src='themes/softed/images/btnL3Add.gif']")
	private WebElement addNewOrgBtn;

	@FindBy(name = "search_text")
	private WebElement searchOrgEdit;

	@FindBy(id = "bas_searchfield")
	private WebElement searchInDropDown;

	@FindBy(xpath = "//input[@name='submit']")
	private WebElement searchButton;

	public WebElement getAddNewOrgBtn() {
		return addNewOrgBtn;
	}

	public WebElement getSearchOrgEdit() {
		return searchOrgEdit;
	}

	public WebElement getSearchInDropDown() {
		return searchInDropDown;
	}

	public WebElement getSearchButton() {
		return searchButton;
	}

	public void searchOrganizationByName(String orgName) {
		searchOrgEdit.sendKeys(orgName);
		Select select = new Select(searchInDropDown);
		select.selectByVisibleText("Organization Name");
		searchButton.click();
	}
}
