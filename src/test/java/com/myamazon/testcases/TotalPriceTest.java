package com.myamazon.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.myamazon.base.BaseClass;
import com.myamazon.dataprovider.DataProviders;
import com.myamazon.pageobjects.AddToCartPage;
import com.myamazon.pageobjects.IndexPage;
import com.myamazon.pageobjects.OrderPage;
import com.myamazon.pageobjects.SearchResultPage;
import com.myamazon.utility.Log;

public class TotalPriceTest extends BaseClass{
	
	IndexPage indexPage;
	SearchResultPage searchResultPage;
	AddToCartPage addToCartPage;
	OrderPage orderPage;

	@Test(groups = "Regression",dataProvider = "getProduct", dataProviderClass = DataProviders.class)
	public void totalPriceTest(String productName, String qty, String size) throws InterruptedException {
		Log.startTestCase("totalPriceTest");
//		
		indexPage= new IndexPage();
		Log.info("Index Page loaded");
		searchResultPage=indexPage.searchProduct(productName);
		Log.info("Search Result Page loaded");
		addToCartPage=searchResultPage.clickOnProduct();
		Log.info("Clicked on product on Search Result Page");

		addToCartPage.enterQuantity(qty);
		Log.info("Entered quantity");
		addToCartPage.selectSize(size);
		Log.info("Selected size");
		addToCartPage.clickAddToCart();
		Log.info("Clicked on Add to Cart button");
		orderPage=addToCartPage.clickProceedToCheckout();
		Log.info("Clicked on Proceed to Checkout button on Add to Cart page");
		Double unitPrice=orderPage.getUnitPrice();
		Double totalPrice=orderPage.getTotalPrice();
		Double totalExpectedPrice=(unitPrice*(Double.parseDouble(qty)))+2;
		Assert.assertEquals(totalPrice, totalExpectedPrice);
		Log.info("totalExpectedPrice validated successfully");
		Log.endTestCase("totalPriceTest");
	}

	@AfterMethod(groups = {"Smoke","Sanity","Regression"})
	public void tearDown() {
		driver.quit();
	}
}
