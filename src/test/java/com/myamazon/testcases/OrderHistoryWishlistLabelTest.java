package com.myamazon.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.myamazon.base.BaseClass;
import com.myamazon.dataprovider.DataProviders;
import com.myamazon.pageobjects.HomePage;
import com.myamazon.pageobjects.IndexPage;
import com.myamazon.pageobjects.LoginPage;
import com.myamazon.utility.Log;

public class OrderHistoryWishlistLabelTest extends BaseClass {
	LoginPage loginPage;// = new IndexPage();;
	IndexPage indexPage;
	HomePage homePage;

	@Test(groups = "Smoke")
	public void wishlistTest() throws InterruptedException {
		indexPage = new IndexPage();
		loginPage = indexPage.clickOnSignIn();
		homePage = loginPage.userLogin(prop.getProperty("email"), prop.getProperty("password"));
		boolean wishList = homePage.validateMyWishlists();
		Assert.assertTrue(wishList);
	}
	
	@Test(groups = "Smoke",dataProvider = "credentials", dataProviderClass = DataProviders.class)
	public void orderHistoryDetailTest(String userName, String password) throws InterruptedException {
		Log.startTestCase("orderHistoryTest");
		indexPage = new IndexPage();
		Log.info("Index Page loaded");
		loginPage = indexPage.clickOnSignIn();
		Log.info("Clicked on Sign in button");
		homePage = loginPage.userLogin(userName, password);
		Log.info("Entered Email address and password for login");
		boolean orderHistory = homePage.validateOrderHistory();
		Assert.assertTrue(orderHistory);
		Log.info("orderHistory label validated successfully");
		Log.endTestCase("orderHistoryTest");

	}	

	@AfterMethod(groups = {"Smoke","Sanity","Regression"})
	public void tearDown() {
		driver.quit();
	}
}
