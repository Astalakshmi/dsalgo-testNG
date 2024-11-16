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

	public void elementToBeClickableWaitTime(WebElement webElementName) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.elementToBeClickable(webElementName));
	}

	public void attributeToBeWaitTime(WebElement webElementName, String attributeName, String attributeValue) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.attributeToBe(webElementName, attributeName, attributeValue));
	}

}
