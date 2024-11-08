package com.DsAlgo.testBase;


import java.time.Duration;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestResult;
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
		
	}
	  @AfterMethod
	    public void tearDown(ITestResult result) {
	        if (result.getStatus() == ITestResult.FAILURE) {
	            // Take a screenshot if the test failed
	           final byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
	            // Attach the screenshot to the report (optional)
	            // Use any reporting framework to attach the screenshot, such as Allure or ExtentReports
	            System.out.println("Test failed: " + result.getName());
	            // Save or log the screenshot here if needed
	        }
	      
	       driver.quit();
	    }

	
}
