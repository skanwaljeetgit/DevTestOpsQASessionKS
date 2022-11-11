package com.myamazon.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.myamazon.base.BaseClass;
import com.myamazon.dataprovider.DataProviders;
import com.myamazon.pageobjects.AddToCartPage;
import com.myamazon.pageobjects.AddressPage;
import com.myamazon.pageobjects.IndexPage;
import com.myamazon.pageobjects.LoginPage;
import com.myamazon.pageobjects.OrderConfirmationPage;
import com.myamazon.pageobjects.OrderPage;
import com.myamazon.pageobjects.OrderSummaryPage;
import com.myamazon.pageobjects.PaymentPage;
import com.myamazon.pageobjects.SearchResultPage;
import com.myamazon.pageobjects.ShippingPage;
import com.myamazon.utility.Log;

public class EndToEndTest extends BaseClass {

	AddressPage addressPage;
	LoginPage loginPage;
	IndexPage indexPage;
	SearchResultPage searchResultPage;
	AddToCartPage addToCartPage;
	OrderPage orderPage;
	ShippingPage shippingPage;
	PaymentPage paymentPage;
	OrderSummaryPage orderSummaryPage;
	OrderConfirmationPage orderConfirmationPage;

	@Test(groups = "Regression", dataProvider = "getProduct", dataProviderClass = DataProviders.class)
	public void endToEndTest(String productName, String qty, String size) throws InterruptedException {
		Log.startTestCase("endToEndTest");
		indexPage = new IndexPage();
		Log.info("Index Page loaded");
		searchResultPage = indexPage.searchProduct(productName);
		Log.info("Search Result Page loaded");
		addToCartPage = searchResultPage.clickOnProduct();
		Log.info("Clicked on product on Search Result Page");
		addToCartPage.enterQuantity(qty);
		Log.info("Entered quantity");
		addToCartPage.selectSize(size);
		Log.info("Selected size");
		addToCartPage.clickAddToCart();
		Log.info("Clicked on Add to Cart button");
		orderPage = addToCartPage.clickProceedToCheckout();
		Log.info("Clicked on Proceed to Checkout button on Add to Cart page");
		loginPage = orderPage.clickProceedToCheckout();
		Log.info("Clicked on Proceed to Checkout button on Order page");
		addressPage = loginPage.userLoginToAddress(prop.getProperty("email"), prop.getProperty("password"));
		Log.info("User logged in");
		shippingPage = addressPage.clickProceedToCheckout();
		Log.info("Clicked on Proceed to Checkout button on Shipping Page");
		shippingPage.checkTermsCheckbox();
		Log.info("Checked Terms checkbox on Shipping Page");
		paymentPage = shippingPage.clickProceedToCheckout();
		Log.info("Clicked on Proceed to Checkout button on Payment Page");
		orderSummaryPage = paymentPage.clickOnPaymentMethod();
		Log.info("Clicked on Payment Method on Order Summary Page");
		orderConfirmationPage = orderSummaryPage.clickConfirmOrder();
		Log.info("Clicked on Confirm Order button on Order Summary Page");
		String actualConfirmOrderMessage = orderConfirmationPage.validateConfirmOrderMessage();
		String expectedConfirmOrderMessage = prop.getProperty("expectedMessage");
		Assert.assertEquals(actualConfirmOrderMessage, expectedConfirmOrderMessage);
		Log.info("confirmOrderMessage validated successfully");
		Log.endTestCase("endToEndTest");
	}


	@AfterMethod(groups = {"Smoke","Sanity","Regression"})
	public void tearDown() {
		driver.quit();
	}
}
