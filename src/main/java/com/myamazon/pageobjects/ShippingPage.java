package com.myamazon.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.myamazon.actiondriver.Action;
import com.myamazon.base.BaseClass;

public class ShippingPage extends BaseClass {

	@FindBy(id = "cgv")
	WebElement termsOfServiceCheckbox;

	@FindBy(xpath = "//button/span[contains(text(),'Proceed to checkout')]")
	WebElement proceedToCheckoutButton;

	public ShippingPage() {
		PageFactory.initElements(driver, this);
	}

	public void checkTermsCheckbox() {
		Action.click(driver, termsOfServiceCheckbox);
	}

	public PaymentPage clickProceedToCheckout() {
		Action.click(driver, proceedToCheckoutButton);
		return new PaymentPage();
	}

}
