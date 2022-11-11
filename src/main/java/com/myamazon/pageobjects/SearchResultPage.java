package com.myamazon.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.myamazon.actiondriver.Action;
import com.myamazon.base.BaseClass;

public class SearchResultPage extends BaseClass {


	@FindBy(xpath = "//a[@class = 'product-name' and contains(text(),'Faded Short Sleeve T-shirts') and @itemprop='url']")
	WebElement resultTopSellers;

	@FindBy(xpath = "//*[@id=\"center_column\"]//img")
	WebElement resultTopSellersQuickView;

	@FindBy(xpath = "//div[@id='best-sellers_block_right']//ul[@class='block_content products-block']/li[3]/a/img")
	WebElement resultTopSellersBlouse;

	@FindBy(xpath = "//span[text()='Add to cart']")
	WebElement addToCartUnderResult;
	
	@FindBy(xpath = "//i[@class='icon-eye-open']")
	WebElement eyeIconOnResult;

	public SearchResultPage() {
		PageFactory.initElements(driver, this);
	}

	public boolean validateProductAvailability() throws InterruptedException {
		return Action.isDisplayed(driver, resultTopSellers);
	}


	public AddToCartPage clickOnProduct() {
		Action.click(driver, resultTopSellers);
		return new AddToCartPage();
	}
	public AddToCartPage clickOnProductQuickView() {
		Action.click(driver, resultTopSellersQuickView);
		return new AddToCartPage();
		}
}
