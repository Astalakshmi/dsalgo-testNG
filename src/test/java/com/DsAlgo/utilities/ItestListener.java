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

import com.DsAlgo.testBase.BaseClass;
import com.aventstack.extentreports.Status;

import io.qameta.allure.Allure;




public class ItestListener extends BaseClass implements ITestListener {
	//ExtentTestManager extent = new ExtentTestManager();
    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }
    
//    
//    @Attachment(value="" type="image/png")
//    public byte[] saveScreenshotPNG(WebDriver driver) {
//    	return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
//    }
//    
    

    @Override
    public void onStart(ITestContext iTestContext) {
        LoggerLoad.info("I am in onStart method " + iTestContext.getName());
        //iTestContext.setAttribute("WebDriver", this.driver);
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
    	LoggerLoad.info("I am in onFinish method " + iTestContext.getName());
        //Do tier down operations for ExtentReports reporting!
        ExtentManager.extentReports.flush();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
    	LoggerLoad.info(getTestMethodName(iTestResult) + " test is starting.");
    	ExtentTestManager.startTest(getTestMethodName(iTestResult),"");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
    	LoggerLoad.info(getTestMethodName(iTestResult) + " test is succeed.");
        //ExtentReports log operation for passed tests.
    	//ExtentTest
    	 ExtentTestManager.getTest().log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
    	LoggerLoad.info(getTestMethodName(iTestResult) + " test is failed.");

        //Get driver from BaseTest and assign to local webdriver variable.
        Object testClass = iTestResult.getInstance();
        WebDriver driver = ((BaseClass) testClass).driver;

        //Take base64Screenshot screenshot for extent reports
        String base64Screenshot =
        		"data:image/png;base64," + ((TakesScreenshot) Objects.requireNonNull(driver)).getScreenshotAs(OutputType.BASE64);

        //ExtentReports log and screenshot operations for failed tests.
        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed",
        		ExtentTestManager.getTest().addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));
        
        File screenshotAsFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//        File destinationScreenshotFile = new File("./target/screenshots/"+Math.random()+".png");
//        try {
//			FileUtils.copyFile(screenshotAsFile, destinationScreenshotFile);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        
        try {
        	 
            // ExtentTestManager.getTest().addScreenCaptureFromPath(destinationScreenshotFile.getAbsolutePath());
			Allure.addAttachment("screenshot", FileUtils.openInputStream(screenshotAsFile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//       String screenshot = "<img src='data:image/png;base64,"+ ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
//        //Allure.addAttachment("screenshot", "Image/png", base64Screenshot);
//		Allure.addAttachment("Failed Screenshot","text/html", (screenshot));
        
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
    	LoggerLoad.info(getTestMethodName(iTestResult) + " test is skipped.");
        //ExtentReports log operation for skipped tests.
    	ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
    	LoggerLoad.info("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
    }
}