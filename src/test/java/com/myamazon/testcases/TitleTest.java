package com.myamazon.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.myamazon.base.BaseClass;
import com.myamazon.pageobjects.IndexPage;
import com.myamazon.utility.Log;

public class TitleTest extends BaseClass {

	IndexPage indexPage;// = new IndexPage();;


	@Test(groups = "Smoke")
	public void storeTitleTest() {
		Log.startTestCase("storeTitleTest");
		indexPage = new IndexPage();
		Log.info("Index Page loaded");
		String actualStoreTitle = indexPage.getStoreTitle();
		String expectedStoreTitle = "My Store";
		Assert.assertEquals(actualStoreTitle, expectedStoreTitle);
		Log.info("Store title validated successfully");
		// tearDown();
		Log.endTestCase("storeTitleTest");
	}
	

	@AfterMethod(groups = {"Smoke","Sanity","Regression"})
	public void tearDown() {
		driver.quit();
	}
}
