package com.myamazon.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.myamazon.actiondriver.Action;
import com.myamazon.base.BaseClass;

public class LoginPage extends BaseClass {
	@FindBy(id = "email")
	WebElement emailAddress;

	@FindBy(name = "passwd")
	WebElement password;

	@FindBy(id = "SubmitLogin")
	WebElement signInButton;

	@FindBy(id = "email_create")
	WebElement emailAddressNewAcc;

	@FindBy(name = "SubmitCreate")
	WebElement createAccountButton;

	public LoginPage() {

		PageFactory.initElements(driver, this);
	}

	public HomePage userLogin(String emailAddress, String password) {

		Action.type(this.emailAddress, emailAddress);
		Action.type(this.password, password);
		Action.click(driver, signInButton);
		return new HomePage();

	}

	public AddressPage userLoginToAddress(String emailAddress, String password) {
	
		Action.type(this.emailAddress, emailAddress);
		Action.type(this.password, password);
		Action.click(driver, signInButton);
		return new AddressPage();

	}

	public AccountCreationPage createNewAccount(String emailAddressNewAcc) {
		Action.type(this.emailAddressNewAcc, emailAddressNewAcc);
		Action.click(driver, createAccountButton);
		return new AccountCreationPage();

	}

}
