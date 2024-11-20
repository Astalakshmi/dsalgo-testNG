package com.DsAlgo.utilities;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import com.DsAlgo.testBase.BaseClass;
import com.aventstack.extentreports.Status;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;

import java.io.ByteArrayInputStream;

public class ItestListener extends BaseClass implements ITestListener {
	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}
	
	@Attachment
	public byte[] saveFailureScreenshot(WebDriver driver) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}
	
	@Attachment(value= "{0}", type="text/plain")
	public static String saveTextLog(String message) {
		return message;
	}

	@Override
	public void onStart(ITestContext iTestContext) {
		LoggerLoad.info(" onStart method " + iTestContext.getName());
		Reporter.log("onStart method " + iTestContext.getName());
	}

	@Override
	public void onFinish(ITestContext iTestContext) {
		LoggerLoad.info("onFinish method " + iTestContext.getName());
		Reporter.log("onFinish method " + iTestContext.getName());
		// Do tier down operations for ExtentReports reporting!
		ExtentManager.extentReports.flush();
	}

	@Override
	public void onTestStart(ITestResult iTestResult) {
		LoggerLoad.info(getTestMethodName(iTestResult) + " Test is starting.");
		Reporter.log(getTestMethodName(iTestResult) + " Test is starting.");
		ExtentTestManager.startTest(getTestMethodName(iTestResult), "");
	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		LoggerLoad.info(getTestMethodName(iTestResult) + " Test is succeed.");
		Reporter.log(getTestMethodName(iTestResult) + " Test is succeed.");
		// ExtentReports log operation for passed tests.
		// ExtentTest
		ExtentTestManager.getTest().log(Status.PASS, "Test passed");
	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {
		LoggerLoad.info(getTestMethodName(iTestResult) + " Test is failed.");
		Reporter.log("onTestFailure" + iTestResult.getName());
		
		// Get driver from BaseTest and assign to local webdriver variable.
		Object testClass = iTestResult.getInstance();
		WebDriver driver = ((BaseClass) testClass).driver;

		// Take base64Screenshot screenshot for extent reports
		String base64Screenshot = "data:image/png;base64,"
				+ ((TakesScreenshot) Objects.requireNonNull(driver)).getScreenshotAs(OutputType.BASE64);

		// ExtentReports log and screenshot operations for failed tests.
		ExtentTestManager.getTest().log(Status.FAIL, "Test Failed", ExtentTestManager.getTest()
				.addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));
        //Screenshot for Allure
//		File screenshotAsFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//		try {
//			
//			Allure.addAttachment("screenshot", FileUtils.openInputStream(screenshotAsFile));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		//add screenshot to target folder
		getScreenshot(iTestResult.getName(), driver);
		
		
		if(driver instanceof WebDriver) {
			saveFailureScreenshot(driver);
		}
		saveTextLog(getTestMethodName(iTestResult)+"failed screenshot taken");
	}

	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		LoggerLoad.info(getTestMethodName(iTestResult) + " Test is skipped.");
		Reporter.log(getTestMethodName(iTestResult) + " Test is skipped.");
		// ExtentReports log operation for skipped tests.
		ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
		LoggerLoad.info("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
		Reporter.log("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
	}

	public String getScreenshot(String testName, WebDriver driver) {

		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String screenshotPath = "target/Screenshots/screenshots" + testName + ".png";
		try {
			FileUtils.copyFile(screenshot, new File(screenshotPath));
		} catch (Exception e) {
			System.out.println("Takes Screenshot is null");
			e.printStackTrace();
		}
		return screenshotPath;

	}

}