package com.myamazon.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.myamazon.utility.ExtentManager;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public static Properties prop;
	public static WebDriver driver;

	@BeforeSuite(groups = { "Smoke", "Sanity", "Regression" })
	public void beforeSuite() throws IOException {
		ExtentManager.setExtent();
		// ExtentManager.startReport();
		// DOMConfigurator.configure("log4j2.xml");
		// BasicConfigurator.configure();

//		String log4jConfPath = System.getProperty("user.dir")+"\\configuration\\log4j.properties";
		// PropertyConfigurator.configure(log4jConfPath);
//		/System.setProperty("log4j.configurationFile", System.getProperty("user.dir")+"\\log4j2.xml");
		prop = new Properties();

		FileInputStream fileInput = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\configuration\\config.properties");
		System.out.println(System.getProperty("user.dir"));
		prop.load(fileInput);
	}

	public void launchApplication(String browser) throws IOException, InterruptedException {

		switch (browser.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			// driver = new ChromeDriver();
			driver = new ChromeDriver();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			// driver = new FirefoxDriver();
			driver = new FirefoxDriver();
			break;
		case "internetexplorer":
			WebDriverManager.iedriver().setup();
			// driver = new InternetExplorerDriver();
			driver = new InternetExplorerDriver();
			break;
		}

		// Maximize the screen
		driver.manage().window().maximize();
		// Delete all the cookies
		// PageLoad TimeOuts
		// Launching the URL
		driver.get(prop.getProperty("url"));
//		
	}

	@Parameters("browser")
	@BeforeMethod(groups = { "Smoke", "Sanity", "Regression" })
	// public void setup(String browser) throws IOException,
	// InterruptedException {
	public void setup(String browser) throws IOException, InterruptedException {
		launchApplication(browser);
		// openApplication();
	}

	@AfterSuite(groups = { "Smoke", "Sanity", "Regression" })
	public void afterSuite() {
		ExtentManager.endReport();
		//driver.quit();

	}

}
