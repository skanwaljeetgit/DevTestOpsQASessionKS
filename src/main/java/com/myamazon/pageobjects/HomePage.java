
package com.myamazon.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.myamazon.actiondriver.Action;
import com.myamazon.base.BaseClass;

public class HomePage extends BaseClass {
	@FindBy(xpath = "//span[text()='My wishlists']")
	WebElement myWishlists;

	@FindBy(xpath = "//span[text()='Order history and details']")
	WebElement orderHistory;

	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	public boolean validateMyWishlists() throws InterruptedException {
		return Action.isDisplayed(driver, myWishlists);
	}

	public boolean validateOrderHistory() throws InterruptedException {
		return Action.isDisplayed(driver, orderHistory);
	}
	
	public String getCurrentUrl() {
		String homePageUrl = driver.getCurrentUrl();
		return homePageUrl;
	}
}
