package com.myamazon.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.myamazon.base.BaseClass;

public class OrderConfirmationPage extends BaseClass {
	@FindBy(xpath = "//p/strong[text() = 'Your order on My Store is complete.']")
	WebElement confirmOrderMessage;

	public OrderConfirmationPage() {
		PageFactory.initElements(driver, this);
	}

	public String validateConfirmOrderMessage() {
		String confirmOrderMessage = this.confirmOrderMessage.getText();
		return confirmOrderMessage;
	}

}
