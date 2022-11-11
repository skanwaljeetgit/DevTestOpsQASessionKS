package com.myamazon.actiondriver;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;

import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.myamazon.actioninterface.ActionInterface;
import com.myamazon.base.BaseClass;
import com.myamazon.utility.Log;

public class Action extends BaseClass {// implements ActionInterface {

	public static void click(WebDriver driver, WebElement ele) {

		Actions act = new Actions(driver);
		act.moveToElement(ele).click().build().perform();

	}

	public static boolean isDisplayed(WebDriver driver, WebElement ele) throws InterruptedException {
		boolean flag = false;
		flag = ele.isDisplayed();
		if (flag) {
			flag = ele.isDisplayed();
			if (flag) {
				// System.out.println("The element is Displayed");
			} else {
				System.out.println("The element is not Displayed");
			}
		} else {
			Log.info("Element not displayed");// System.out.println("Not displayed ");
		}
		return flag;
	}

	public static boolean type(WebElement ele, String text) {
		boolean flag = false;
		try {
			flag = ele.isDisplayed();
			ele.clear();
			ele.sendKeys(text);
			Log.info("Entered text :" + text);
			flag = true;
		} catch (Exception e) {
			System.out.println("Location Not found");
			flag = false;
		} finally {
			if (flag) {
				System.out.println("Successfully entered value");
			} else {
				System.out.println("Unable to enter value");
			}

		}
		return flag;
	}

	public static boolean selectByVisibleText(String visibletext, WebElement ele) {
		boolean flag = false;
		try {
			Select s = new Select(ele);
			s.selectByVisibleText(visibletext);
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (flag) {
				Log.info("Dropdown option selected");// System.out.println("Option selected by VisibleText");
			} else {
				Log.info("Dropdown option not selected");// System.out.println("Option not selected by VisibleText");
			}
		}
	}

	public static boolean JSClick(WebDriver driver, WebElement ele) {
		boolean flag = false;
		try {
			// WebElement element = driver.findElement(locator);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", ele);
			// driver.executeAsyncScript("arguments[0].click();", element);

			flag = true;

		}

		catch (Exception e) {
			throw e;

		} finally {
			if (flag) {
				Log.info("Javascript click performed");// System.out.println("Click Action is performed");
			} else if (!flag) {
				Log.info("Javascript click not performed");// System.out.println("Click Action is not performed");
			}
		}
		return flag;
	}

	public static boolean isAlertPresent(WebDriver driver) {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException Ex) {
			return false;
		}
	}
//
//	public static void fluentWait(WebDriver driver, WebElement element, int timeOut) {
//		Wait<WebDriver> wait = null;
//		try {
//			wait = new FluentWait<WebDriver>((WebDriver) driver).withTimeout(Duration.ofSeconds(20))
//					.pollingEvery(Duration.ofSeconds(2)).ignoring(Exception.class);
//			wait.until(ExpectedConditions.visibilityOf(element));
//			element.click();
//		} catch (Exception e) {
//		}
//	}

	public static void explicitWait(WebDriver driver, WebElement element, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static String screenShot(WebDriver driver, String filename) {
		String dateName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		Date date = new Date();
		//String s = "" + date.getTime();

		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm");
		//String foldername = // "TestResults" + s;
				dateFormat.format(date);

		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "\\screenshots\\" + filename + "_" + dateName + ".png";

		try {
			FileUtils.copyFile(source, new File(destination));
		} catch (Exception e) {
			e.getMessage();
		}

		return destination;
	}

}