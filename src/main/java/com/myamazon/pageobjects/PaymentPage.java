package com.myamazon.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.myamazon.actiondriver.Action;
import com.myamazon.base.BaseClass;

public class PaymentPage extends BaseClass{
	@FindBy(xpath = "//a[contains(text(),'Pay by bank wire')]")
	WebElement payByBankWireButton;

	@FindBy(xpath = "//a[contains(text(),'Pay by check')]")
	WebElement payByCheckButton;

	public PaymentPage() {
		PageFactory.initElements(driver, this);
	}

	public OrderSummaryPage clickOnPaymentMethod() {
		Action.click(driver, payByBankWireButton);
		return new OrderSummaryPage();
	}

}
