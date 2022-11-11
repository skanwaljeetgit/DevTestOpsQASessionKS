package com.myamazon.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.myamazon.actiondriver.Action;
import com.myamazon.base.BaseClass;

public class OrderSummaryPage extends BaseClass {
	@FindBy(xpath = "//span[text()='I confirm my order']")
	WebElement confirmOrderButton;

	public OrderSummaryPage() {
		PageFactory.initElements(driver, this);
	}

	public OrderConfirmationPage clickConfirmOrder() {

		Action.click(driver, confirmOrderButton);
		return new OrderConfirmationPage();
	}
	
}
