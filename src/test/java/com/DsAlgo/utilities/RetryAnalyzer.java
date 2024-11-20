package com.DsAlgo.utilities;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.DsAlgo.testBase.BaseClass;
import com.aventstack.extentreports.Status;

import io.qameta.allure.Allure;
public class RetryAnalyzer implements IRetryAnalyzer {
	   private static final int  maxTry = 3;
	   private int count  = 0;
	    @Override
	    public boolean retry(ITestResult iTestResult) {
	        if (!iTestResult.isSuccess()) {                     //Check if test not succeed
	            if (count < maxTry) {                           //Check if maxTry count is reached
	                count++;                                    //Increase the maxTry count by 1
	                iTestResult.setStatus(ITestResult.FAILURE); //Mark test as failed and take base64Screenshot
	                failOperations(iTestResult);   //ExtentReports fail operations
	                return true;                                //Tells TestNG to re-run the test
	            }
	        } else {
	            iTestResult.setStatus(ITestResult.SUCCESS);     //If test passes, TestNG marks it as passed
	        }
	        return false;
	    }
	    public void failOperations(ITestResult iTestResult) {
	        Object testClass = iTestResult.getInstance();
	        WebDriver driver = ((BaseClass) testClass).driver;
	        String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
	        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed",
	        		ExtentTestManager.getTest().addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));
	        File screenshotAsFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			try {
				
				Allure.addAttachment("screenshot", FileUtils.openInputStream(screenshotAsFile));
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
		
	}