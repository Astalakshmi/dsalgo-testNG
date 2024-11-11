package com.DsAlgo.utilities;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class CommonUtils {
	WebDriver driver;
	String pagetitle;
	 WebDriverWait wait;
	 private static CommonUtils utilsObj = null;
	 private CommonUtils()
	 {
		 
	 }
	 public static CommonUtils getInstance() {
			if (utilsObj != null) {
				return utilsObj;
			} else {
				utilsObj = new CommonUtils();
				return utilsObj;
			}
		} 
	 
	public void visibilityOf(WebElement webElementName) {
		 wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(webElementName));
	}

	public void visibilityOfMoreWaitTime(WebElement webElementName) {
		 wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(webElementName));
	}
	public void navigate_back() {
		driver.navigate().back();
	}

	public void switch_to_frame() {
		driver.switchTo().frame(0);
	}

//	public void clickWebElement(WebElement webelementName) {
//		webelementName.click();
//	}

//	public String sendKeys(WebElement webElementName) {
//		webElementName.sendKeys(null);
//	}

//	public void get_title(String pagetitle) {
//		pagetitle = driver.getTitle();
//		this.pagetitle = pagetitle;
//		System.out.println(pagetitle);
//	}

//	public void getActualTitle(String actualpagetitle) {
//		this.pagetitle= driver.getTitle();
//	}
	
	
	/*
	 * public boolean click(WebElement element) { try { WebElement eleToClick = new
	 * WebDriverWait(driver, Duration.ofSeconds(IMPLICIT_WAIT))
	 * .until(ExpectedConditions.visibilityOf(element));
	 * 
	 * if (eleToClick.isEnabled()) { eleToClick.click(); return true; } else { throw
	 * new IllegalStateException("Element is not enabled"); } } catch (Exception e)
	 * { e.printStackTrace(); } return false; }
	 * 
	 * 
	 * 
	 */
	public void checkout_page() {
		driver.getTitle();

		while (!(driver.getTitle().equals(pagetitle))) {
			driver.navigate().back();
		}

		// Constructor initializes WebDriver and WebDriverWait
//		    public CommonUtils(WebDriver driver, int timeoutInSeconds) {
//		        this.driver = driver;
//		        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
//		    }
		// Wait for element to be visibility in element locator
//		    public WebElement visibilityOfElementLocated(By locator)
//		    {
//		    	
//		   	 return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));	
//
//		    }
//
//		// Wait for element to be invisibility in element locator
//		  public boolean invisibilityOfElementLocated(By locator)
//		  {
//				
//				 return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
//							
//
//			}
//
//		 // Wait for element to be visibility in element locator
//		    public List<WebElement> visibilityOfAllElementsLocated(By locator)
//		    {
//		    	
//		   	 return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));	
//
//		    }
//		    
//		   // Wait for element to be enabled
//		    public WebElement waitForElementToBeEnabled(By locator) {
//		        WebElement e = visibilityOfElementLocated(locator);
//		        wait.until(driver -> e.isEnabled());
//		        return e;
//		    }
//		    // Wait for element to be displayed
//		    public WebElement ElementToBeDisplayed(By locator) {
//		        WebElement e = visibilityOfElementLocated(locator);
//		        wait.until(driver -> e.isDisplayed());
//		        return e;
//		    }
//		    // Wait for element to be displayed
//		    public WebElement ElementToBeSelected(By locator) {
//		        WebElement e = visibilityOfElementLocated(locator);
//		        wait.until(driver -> e.isSelected());
//		        return e;
//		    }
//		   
//		    
//		    // Wait for element to be clickable
//		    public WebElement elementToBeClickable(By locator) {
//		        return wait.until(ExpectedConditions.elementToBeClickable(locator));
//		    }
//
//
//		    // Wait for element to be present in the DOM
//		    public WebElement presenceOfElementLocated(By locator) {
//		        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
//		    }
//		    public List<WebElement> presenceOfElementsLocated(By locator) {
//		        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
//		    }
//		    //get URL
//		    public String currentUrl()
//			{
//				return driver.getCurrentUrl();
//			}
//		    //Get Title
//			public String title()
//			{
//				return driver.getTitle();
//			}
//
//		    // Wait for text to be present in element
//		    public boolean TextToBePresentInElement(By locator, String text) {
//		        return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
//		    }
//
//		    // Wait for an element to be invisible
//		    public boolean ElementToBeInvisible(By locator) {
//		        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
//		    }

	}

}
