package com.myamazon.utility;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.LocalDateTime;   
//import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.myamazon.actiondriver.Action;
import com.myamazon.base.BaseClass;

public class ListenerClass extends ExtentManager implements ITestListener {

	Action action = new Action();

	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getName());

	}

	/*
	 * @Override public void onStart(ITestContext arg0) { File sourceFile = new
	 * File( System.getProperty("user.dir") +
	 * "\\test-output\\extentreport\\" + "MyExtentReport.html"); if
	 * (sourceFile.exists()) { //Date date = new Date(); //String s = "" +
	 * date.getTime(); DateTimeFormatter dtf =
	 * DateTimeFormatter.ofPattern("yyyyMMddhhmm");//.ofPattern(
	 * "yyyy-MM-dd-HH-mm-ss");
	 * 
	 * LocalDateTime now = LocalDateTime.now();
	 * //now.truncatedTo(ChronoUnit.DAYS).format(dtf); System.out.println(now);
	 * String s = "" + now.truncatedTo(ChronoUnit.MINUTES).format(dtf); // // String
	 * s = "" + now.toString(); System.out.println(s); //DateFormat dateFormat = new
	 * SimpleDateFormat("yyyyMMddHHmm") ;//= new SimpleDateFormat("yyyyMMddHHmm");
	 * //DateTimeFormatter formatter = .dateHourMinuteSecond(); String foldername =
	 * "TestResults" + s;// dateFormat.format(date);
	 * 
	 * File dir = new File( System.getProperty("user.dir") +
	 * "\\test-output\\extentreport\\archivedreports\\" + foldername); dir.mkdir();
	 * File destFile = new File(System.getProperty("user.dir") +
	 * "\\test-output\\extentreport\\archivedreports\\" + foldername +
	 * "\\MyExtentReport.html");
	 * 
	 * if (sourceFile.renameTo(destFile)) {
	 * System.out.println("File moved successfully"); } else {
	 * System.out.println("Failed to move file"); } } }
	 */	
	public void onStart(ITestContext arg0) {
		File sourceFile = new File(System.getProperty("user.dir") + "\\test-output\\extentreport\\" +"MyExtentReport.html");
		if(sourceFile.exists()) {
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm");
			Date date = new Date();
			String foldername = "TestResults" + dateFormat.format(date);
			
			File dir = new File(System.getProperty("user.dir") + "\\test-output\\extentreport\\archivedreports\\" + foldername);
			dir.mkdir();
			File destFile = new File(System.getProperty("user.dir") + "\\test-output\\extentreport\\archivedreports\\" + foldername + "\\MyExtentReport.html");
			 
			if (sourceFile.renameTo(destFile)) {
			    System.out.println("File moved successfully");
			} else {
			    System.out.println("Failed to move file");
			}
		}
	}

	public void onTestSuccess(ITestResult result) {
		if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, "Test Case passed: " + result.getName());
			//test.log(Status.PASS, "Extent getClass: " + extent.getClass());
			getExtent();
		}
	}

	public void onTestFailure(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			try {
				// test.log(Status.FAIL,
				// MarkupHelper.createLabel("Test Case Failed: " + result.getTestName(),
				// ExtentColor.RED));
				test.log(Status.FAIL,
						MarkupHelper.createLabel("Test Case Failed: " + result.getThrowable(), ExtentColor.RED));
				String imgPath = Action.screenShot(BaseClass.driver, result.getName());

				test.fail("Attachment: ", MediaEntityBuilder.createScreenCaptureFromPath(imgPath).build());

			} catch (IOException e) { // TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, "Test Case Skipped: " + result.getName());
		}
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) { // TODO Auto-generated method stub
	}

	@Override
	public void onFinish(ITestContext context) { // TODO Auto-generated method stub }

	}
}