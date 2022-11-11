package com.myamazon.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.myamazon.base.BaseClass;
import com.myamazon.dataprovider.DataProviders;
import com.myamazon.pageobjects.AccountCreationPage;
import com.myamazon.pageobjects.HomePage;
import com.myamazon.pageobjects.IndexPage;
import com.myamazon.pageobjects.LoginPage;
import com.myamazon.utility.Log;

public class CreateAccountTest extends BaseClass{

	LoginPage loginPage;// = new IndexPage();;
	IndexPage indexPage;
	HomePage homePage;
	AccountCreationPage accountCreationPage;
	
	@Test(groups = "Sanity",dataProvider = "newAcountDetailsData", dataProviderClass = DataProviders.class)
	public void accountCreatePageTest(String accountCreationEmail) throws InterruptedException, IOException {
	Log.startTestCase("accountCreatePageTest");
	indexPage = new IndexPage();
	Log.info("Index Page loaded");
	loginPage = indexPage.clickOnSignIn();
	Log.info("Clicked on Sign in button");
	accountCreationPage = loginPage.createNewAccount(accountCreationEmail);
	Log.info("Entered Email address for account creation");
	boolean formHeader = accountCreationPage.validateAccountCreatePage();
	Assert.assertTrue(formHeader);
	Log.info("createAccountFormHeader validated successfully");
	Log.endTestCase("accountCreatePageTest");

	}
	@AfterMethod(groups = {"Smoke","Sanity","Regression"})
	public void tearDown() {
		driver.quit();
	}
}