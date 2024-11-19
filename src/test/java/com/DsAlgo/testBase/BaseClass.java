package com.DsAlgo.testBase;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.DsAlgo.utilities.ConfigFileReader;
import com.DsAlgo.utilities.LoggerLoad;


public class BaseClass {

	public WebDriver driver;

	ConfigFileReader configFileReader = ConfigFileReader.getInstance();

	@BeforeMethod
	@Parameters("browser")
	public void setup(@Optional("Chrome") String browser) {
		if (browser.equals("Chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equals("Edge")) {
			driver = new EdgeDriver();
			
		} else if (browser.equals("Firefox")) {
			driver = new FirefoxDriver();
		}else {
			driver = new ChromeDriver();
		}
		System.out.println("The BaseClass driver : " +driver);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(configFileReader.getHomeUrl());

	}

	@AfterMethod
public void teardown()
{
 //  public void tearDown(ITestResult result) {
		
	//	getScreenshot( result.getName(),driver);
		driver.quit();
		
		 
	}
	
	public WebDriver getDriver() {
        return driver;
    }
	
//	public String getScreenshot(String testName, WebDriver driver)
//    {
//		
//        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
//        File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
//        String screenshotPath = "target/Screenshots/screenshots" + testName +".png";
//        try {
//            FileUtils.copyFile(screenshot,new File(screenshotPath));
//        } catch (Exception e) {
//            System.out.println("Takes Screenshot is null");
//            e.printStackTrace();
//        }
//        return screenshotPath;
//
//    }
	

}