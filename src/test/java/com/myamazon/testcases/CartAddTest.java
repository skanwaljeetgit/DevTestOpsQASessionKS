package com.myamazon.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.myamazon.base.BaseClass;
import com.myamazon.dataprovider.DataProviders;
import com.myamazon.pageobjects.AddToCartPage;
import com.myamazon.pageobjects.IndexPage;
import com.myamazon.pageobjects.SearchResultPage;
import com.myamazon.utility.Log;

public class CartAddTest extends BaseClass {

	IndexPage indexPage;
	SearchResultPage searchResultPage;
	AddToCartPage addToCartPage;

	@Test(groups = {"Regression", "Sanity"},dataProvider = "getProduct", dataProviderClass = DataProviders.class)
	public void addToCartTest(String productName, String qty, String size) throws InterruptedException {
		Log.startTestCase("addToCartTest");
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
		//addToCartPage.clickAddToCartQuickView();
		boolean addedToCart = addToCartPage.validateAddToCart();
		Assert.assertTrue(addedToCart);
		Log.info("addToCartMessage validated successfully");
		Log.endTestCase("addToCartTest");
	}
	@AfterMethod(groups = {"Smoke","Sanity","Regression"})
	public void tearDown() {
		driver.quit();
	}
}
