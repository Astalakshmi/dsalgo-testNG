package com.DsAlgo.pageObjects;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

	WebDriver driver;
	
	            //constructor
	public LoginPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
			// locators
	@FindBy(xpath = "//a[normalize-space()='Sign in']")
	WebElement signIn;

	@FindBy(xpath = "//input[@id='id_username']")
	WebElement username;

	@FindBy(xpath = "//input[@id='id_password']")
	WebElement loginPassword;

	@FindBy(xpath = "//input[@value='Login']")
	WebElement loginbtn;

	@FindBy(xpath = "//div[@class='alert alert-primary']")
	WebElement alertInvalidcredentials;

	@FindBy(xpath = "//div[@class='alert alert-primary']")
	WebElement validcredentialstext;

	@FindBy(xpath = "//a[text()='Register!']")
	WebElement loginpageRegisterlink;

	@FindBy(xpath = "//a[normalize-space()='Register']")
	WebElement registerLink;
	
	//Action Methods

	public void signInclick() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(signIn)).click();
	//	signIn.click();
	}

	public void setUsername(String userName) {
		username.sendKeys(userName);
	}

	public void setLoginPassword(String passWord) {
		loginPassword.sendKeys(passWord); 
	}

	public void loginBtnclick() {
		loginbtn.click();
	}

	public String getLoginValidationMessage() {
		return validcredentialstext.getText();
	}

	public String getInvalidLoginValidationMessage() {
		return alertInvalidcredentials.getText();
	}

	public String getActualTitle() {
		return driver.getTitle();
	}

	public void loginpageRegisterlinkclick() {
		loginpageRegisterlink.click();
	}

	public void registerLinkclick() {
		registerLink.click();
	}

	public String getUserNameValidationMessage() {
		return username.getAttribute("validationMessage");
	}

	public String getPasswordNameValidationMessage() {
		return loginPassword.getAttribute("validationMessage");
	}

	

}