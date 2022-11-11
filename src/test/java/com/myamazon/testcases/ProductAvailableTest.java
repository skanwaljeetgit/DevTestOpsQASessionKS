package com.myamazon.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.myamazon.base.BaseClass;
import com.myamazon.dataprovider.DataProviders;
import com.myamazon.pageobjects.IndexPage;
import com.myamazon.pageobjects.SearchResultPage;
import com.myamazon.utility.Log;

public class ProductAvailableTest extends BaseClass {

	IndexPage indexPage;
	SearchResultPage searchResultPage;

	@Test(groups ="Smoke" ,dataProvider = "searchProduct", dataProviderClass = DataProviders.class)
	public void productAvailabilityTest(String productName) throws InterruptedException {
		Log.startTestCase("productAvailabilityTest");
		indexPage = new IndexPage();
		Log.info("Index Page loaded");
		searchResultPage = indexPage.searchProduct(productName);
		Log.info("Search Result Page loaded");
		boolean productAvailable = searchResultPage.validateProductAvailability();
		Assert.assertTrue(productAvailable);
		Log.info("productAvailability validated successfully");
		Log.endTestCase("productAvailabilityTest");
	}

	@AfterMethod(groups = {"Smoke","Sanity","Regression"})
	public void tearDown() {
		driver.quit();
	}
}
