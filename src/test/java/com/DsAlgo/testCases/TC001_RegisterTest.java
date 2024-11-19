
package com.DsAlgo.testCases;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.DsAlgo.pageObjects.HomePage;
import com.DsAlgo.pageObjects.LoginPage;
import com.DsAlgo.pageObjects.RegisterPage;
import com.DsAlgo.testBase.BaseClass;
import com.DsAlgo.utilities.DataProviders;
import com.DsAlgo.utilities.LoggerLoad;

@Listeners(com.DsAlgo.utilities.ItestListener.class)
public class TC001_RegisterTest extends BaseClass {

	String unameValidateMsg, pwdValidateMsg, confirmPwdValidateMsg;
	int tempRow;
	int rowNumber;
	public static final int maxLengthOfRow = 8;
	LoginPage loginObj = new LoginPage(driver);

	@Test(enabled = false, dataProvider = "RegisterValidData", dataProviderClass = DataProviders.class)

	public void testValidRegister(String username, String password, String confirmPassword, String expectedOutput) {

		HomePage homeObj = new HomePage(driver);
		homeObj.getStartedhomeclickwithoutlogin();

		RegisterPage registerObj = new RegisterPage(driver);
		registerObj.registerLink();

		if (username != null || password != null || confirmPassword != null) {
			registerObj.setUsername(username);
			registerObj.setPassword(password);
			registerObj.setConfirmPassword(confirmPassword);
			registerObj.clickRegisterBtn();

		} else {
			LoggerLoad.warn("Invalid data in sheet for username, password, or confirm password");
		}
		// Assertion
		String actualMessage = registerObj.setExpectedOutPut();
		Assert.assertEquals(actualMessage, expectedOutput);
		LoggerLoad.info("New Account Created. You are logged in as " + username);

	}

	@Test(priority = 1, dataProvider = "RegisterInvalidData", dataProviderClass = DataProviders.class)

	public void testInValidRegister(String username, String password, String confirmPassword,
			String invalidExpectedOutput) {
		HomePage homeObj = new HomePage(driver);
		homeObj.getStartedhomeclickwithoutlogin();

		RegisterPage registerObj = new RegisterPage(driver);
		registerObj.registerLink();
		// Validation messages
		unameValidateMsg = registerObj.setUsernameMsgAttribute();
		pwdValidateMsg = registerObj.setPasswordMsgAttribute();
		confirmPwdValidateMsg = registerObj.setConfirmPwdMsgAttribute();
		if (rowNumber == maxLengthOfRow) {
			tempRow = rowNumber + 1;
		}

		if (username != null || password != null || confirmPassword != null) {
			registerObj.setUsername(username);
			registerObj.setPassword(password);
			registerObj.setConfirmPassword(confirmPassword);
			registerObj.clickRegisterBtn();
		} else {
			LoggerLoad.warn("Invalid data in sheet for username, password, or confirm password");
		}

		// Assertion
		if (password.isBlank() && confirmPassword.isBlank()) {

			Assert.assertEquals(unameValidateMsg, invalidExpectedOutput); // Assert.assertEquals(validationMessage,
																			// "Please fill out this field");
			LoggerLoad.error("Password and confirmPassword field are Blank- " + invalidExpectedOutput);

		} else if (username.isBlank() && confirmPassword.isBlank()) {

			Assert.assertEquals(pwdValidateMsg, invalidExpectedOutput);
			LoggerLoad.error("Username and confirmPassword field are Blank- " + invalidExpectedOutput);
		} else if (username.isBlank() && password.isBlank()) {

			Assert.assertEquals(confirmPwdValidateMsg, invalidExpectedOutput);
			LoggerLoad.error("Username and Password field are Blank- " + invalidExpectedOutput);
		} else if (username.isBlank() && password.isBlank() && confirmPassword.isBlank()) {
			Assert.assertEquals(unameValidateMsg, invalidExpectedOutput);
			LoggerLoad.error("Username,Password and ConfirmPassword field are Blank- " + invalidExpectedOutput);
		} else {
			String failureMessage = registerObj.setMismatchExpOutput();
			Assert.assertEquals(failureMessage, invalidExpectedOutput);
			LoggerLoad.error("password_mismatch:The two password fields didn’t match.");
		}

		// Loginlink and Signin Navigation
		if (tempRow > maxLengthOfRow)

		{
			registerObj.clickLoginLink();
			System.out.println("clicked login link");
			LoggerLoad.info("clicked login link");
			driver.navigate().back();
			LoggerLoad.info("Back to Register page");
			loginObj.signInclick();
			System.out.println("clicked sign in  nav link");
			LoggerLoad.info("We are in Register Page ,Navigate to login page using Menubar 'Sign-in' link");
		}
	}

}
