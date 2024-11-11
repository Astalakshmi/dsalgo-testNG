package com.DsAlgo.pageObjects;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.DsAlgo.utilities.CommonUtils;

public class LoginPage {
	WebDriver driver;
	 CommonUtils utilsObj = CommonUtils.getInstance();

	// @FindBy(xpath = "//a[normalize-space()='Sign in']")
	@FindBy(xpath = "//a[text()='Sign in']")
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

	public void signInclick() {
		utilsObj.visibilityOf(signIn);
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//		wait.until(ExpectedConditions.visibilityOf(signIn)).click();
		signIn.click();
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

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
