package com.DsAlgo.testBase;


import java.io.File;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.DsAlgo.utilities.ConfigFileReader;

public class BaseClass {
	
  public static WebDriver driver ;
    
	ConfigFileReader configFileReader = ConfigFileReader.getInstance();
	
	@BeforeMethod
	@Parameters("browser")
	public void setup(@Optional("Chrome") String browser) {
		if (browser.equals("Chrome")) {
			System.out.println("Chrome Browser = "+browser);
			driver = new ChromeDriver();
		}else if (browser.equals("Edge")) {
			System.out.println("Edge Browser = "+browser);
			driver = new EdgeDriver();
		}else {
			System.out.println("Default Browser = "+browser);
			driver = new ChromeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(configFileReader.getHomeUrl());
		 System.out.println("order before METHOD BASE CLASS");
	}
	  @AfterMethod
	  public void close() {

			driver.quit();
			 
		}

	  public WebDriver getDriver() {
	        return driver;
	    }
		
		public String getScreenshot(String testName, WebDriver driver)
	    {
			
	        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
	        File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
	        String screenshotPath = "target/screenshots" + testName +".png";
	        try {
	            FileUtils.copyFile(screenshot,new File(screenshotPath));
	        } catch (Exception e) {
	            System.out.println("Takes Screenshot is null");
	            e.printStackTrace();
	        }
	        return screenshotPath;

	    }
}
