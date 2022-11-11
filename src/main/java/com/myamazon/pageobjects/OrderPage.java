package com.myamazon.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.myamazon.actiondriver.Action;
import com.myamazon.base.BaseClass;

public class OrderPage extends BaseClass {

	@FindBy(xpath = "//td[@class='cart_unit']/span/span[@class='price']")
	WebElement unitPrice;

	@FindBy(id = "total_price")
	WebElement totalPrice;

	@FindBy(xpath = "//span[text()='Proceed to checkout']")
	WebElement proceedToCheckoutButton;

	public OrderPage() {

		PageFactory.initElements(driver, this);
	}

	public double getUnitPrice() {
		Action.explicitWait(driver, unitPrice, 30);

		String unitPrice = this.unitPrice.getText();
		String unit = unitPrice.replaceAll("[^a-zA-Z0-9]", "");
		Double finalUnitPrice = Double.parseDouble(unit);
		return finalUnitPrice / 100;
	}

	public double getTotalPrice() {
		Action.explicitWait(driver, totalPrice, 30);

		String totalPrice = this.totalPrice.getText();
		String total = totalPrice.replaceAll("[^a-zA-Z0-9]", "");
		Double finalTotalPrice = Double.parseDouble(total);
		return finalTotalPrice / 100;
	}

	public LoginPage clickProceedToCheckout() {
		Action.explicitWait(driver, proceedToCheckoutButton, 30);

		Action.click(driver, proceedToCheckoutButton);
		return new LoginPage();

	}

	
}
