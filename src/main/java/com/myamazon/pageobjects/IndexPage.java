package com.myamazon.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.myamazon.actiondriver.Action;
import com.myamazon.base.BaseClass;

public class IndexPage extends BaseClass {

	@FindBy(xpath = "//a[@class = 'login']")
	WebElement signInButton;
	
	@FindBy(xpath = "//img[@class ='logo img-responsive']") 
	WebElement storeLogo;

	@FindBy(id = "search_query_top")
	WebElement searchBox;

	@FindBy(name = "submit_search")
	WebElement searchButton;

	public IndexPage() {

		PageFactory.initElements(driver, this);
	}

	public LoginPage clickOnSignIn() {
		
		Action.click(driver, signInButton);
		return new LoginPage();// link index page and login page
	}

	// validate storeLogo
	public boolean validateLogo() throws InterruptedException {
		//Action.fluentWait(driver, storeLogo, 10);
		return Action.isDisplayed(driver, storeLogo);// driver is used directly since it is coming from base class
		// return storeLogo.isDisplayed();
	}

	// validate signInButton
	public boolean validateSignIn() throws InterruptedException {
		// return Action.isDisplayed(driver, storeLogo);// driver is used directly since
		// it is coming from base class
		return signInButton.isEnabled();
	}

	// validate page title
	public String getStoreTitle() {

		String storeTitle = driver.getTitle();
		return storeTitle;
	}

	public SearchResultPage searchProduct(String productName) {
		Action.type(searchBox, productName);
		Action.click(driver, searchButton);
		return new SearchResultPage();
	}

}
