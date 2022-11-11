
package com.myamazon.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.myamazon.actiondriver.Action;
import com.myamazon.base.BaseClass;

public class AddressPage extends BaseClass {

	@FindBy(xpath = "//span[text()='Proceed to checkout']")
	WebElement proceedToCheckoutButton;

	public AddressPage() {
		PageFactory.initElements(driver, this);
	}

public ShippingPage clickProceedToCheckout() {
	
	Action.click(driver,proceedToCheckoutButton);
	return new ShippingPage();
}

}
