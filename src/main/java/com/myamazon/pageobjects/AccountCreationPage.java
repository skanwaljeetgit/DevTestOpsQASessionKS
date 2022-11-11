package com.myamazon.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.myamazon.actiondriver.Action;
import com.myamazon.base.BaseClass;


public class AccountCreationPage extends BaseClass {

	@FindBy(xpath = "//h1[text()='Create an account']")
	WebElement createAccountFormHeader;

	public AccountCreationPage() {
		PageFactory.initElements(driver, this);

	}

	public boolean validateAccountCreatePage() throws InterruptedException {
		return Action.isDisplayed(driver, createAccountFormHeader);
	}

}
