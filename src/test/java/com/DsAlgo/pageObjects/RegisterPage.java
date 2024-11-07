package com.DsAlgo.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class RegisterPage{
	
	 WebDriver driver;


	 			//constructor
	 
	public RegisterPage(WebDriver driver) 
	{
		this.driver = driver;
	PageFactory.initElements(driver, this);
	 }
	
	// locators
	@FindBy(xpath="//a[normalize-space()='Register']")
	WebElement registerLink;
	@FindBy(xpath="//input[@id='id_username']")
	WebElement username;
	@FindBy(xpath="//input[@id='id_password1']")
	WebElement password;
	@FindBy(xpath="//input[@id='id_password2']")
	WebElement confirmPassword;
	@FindBy(xpath="//input[@value='Register']")
	WebElement registerBtn;
	@FindBy(xpath="//*[contains(text(),'New Account')]")
	WebElement successTextOfRegister;
	@FindBy(xpath="//div[contains(text(),'password_mismatch')]")
	WebElement failureTextOfRegister;
	@FindBy(xpath="//a[normalize-space()='Login']")
	WebElement clickLoginLink;
	//After signin password 
	@FindBy(xpath ="//input[@id='id_password']")
	WebElement signInPassword;
	
	
	//Action Methods
	
	public void registerLink() {
	
	registerLink.click();
	}
	public void setUsername(String Username) {
	
	username.sendKeys(Username);
	
	}
	public String setUsernameMsgAttribute() {
		
	 return username.getAttribute("validationMessage");
	}
	
	public void setPassword(String Password) {
	
	password.sendKeys(Password);	
	
	}
	public String setPasswordMsgAttribute() {
	
	return password.getAttribute("validationMessage");
	}
	
	public void setConfirmPassword(String ConfirmPwd) {
	
	confirmPassword.sendKeys(ConfirmPwd);	

	   }
	public String setConfirmPwdMsgAttribute() {
	
	 return confirmPassword.getAttribute("validationMessage");
	}
	
	public void clickRegisterBtn() {
	
	registerBtn.click();
	}
	public void clickLoginLink() {
	clickLoginLink.click();
	}
	
	public String getActualTitle() {
	String actualTitle = driver.getTitle();
	return actualTitle;
	}
	
	public String setExpectedOutPut() {
	return successTextOfRegister.getText();     //"New Account Created. You are logged in as " + username
	
	}
	
	public String setMismatchExpOutput() {
	return failureTextOfRegister.getText();		//password_mismatch:The two password fields didn’t match.
	}
	
	
	
	}