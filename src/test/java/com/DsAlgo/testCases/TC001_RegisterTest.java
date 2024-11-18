package com.DsAlgo.testCases;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.DsAlgo.pageObjects.HomePage;
import com.DsAlgo.pageObjects.RegisterPage;
import com.DsAlgo.testBase.BaseClass;
import com.DsAlgo.utilities.ConfigFileReader;
import com.DsAlgo.utilities.DataProviders;
import com.DsAlgo.utilities.LoggerLoad;



public class TC001_RegisterTest extends BaseClass {

//	String expectedOutput;
//	ConfigFileReader configFileReader = ConfigFileReader.getInstance();
//
//	@Test(dataProvider = "RegisterData", dataProviderClass = DataProviders.class)
//	@Parameters("browser")
//	public void verify_validRegister(String username, String password,String confirmPassword,String expectedOutput) {
//	
//	HomePage homeObj = new HomePage(driver);
//	homeObj.getStartedhomeclickwithoutlogin();
//
//	RegisterPage registerObj = new RegisterPage(driver);
//	registerObj.registerLink();
//		
//		 if(username != null || password != null || confirmPassword != null)
//		   {
//			   registerObj.setUsername(username);
//			   registerObj.setPassword(password);
//			   registerObj.setConfirmPassword(confirmPassword);	   
//			   registerObj.clickRegisterBtn();
//				
//		   }
//		   else {
//		      LoggerLoad.warn("Invalid data in sheet for username, password, or confirm password");
//		   }
//		    String actualMessage=registerObj.setExpectedOutPut();
//			Assert.assertEquals(actualMessage,expectedOutput);
//			LoggerLoad.info("New Account Created. You are logged in as " + username);
//		
//	}

	
	ConfigFileReader configFileReader = ConfigFileReader.getInstance();
	String expectedOutput, invalidExpectedOutput;
	String unameValidateMsg, pwdValidateMsg, confirmPwdValidateMsg;

	@Test(enabled = false,dataProvider = "RegisterValidData", dataProviderClass = DataProviders.class)
	@Parameters("browser")
	public void testVerify_validRegister(String username, String password,String confirmPassword,String expectedOutput) {
	
	HomePage homeObj = new HomePage(driver);
	homeObj.getStartedhomeclickwithoutlogin();

	RegisterPage registerObj = new RegisterPage(driver);
	registerObj.registerLink();
		
		 if(username != null || password != null || confirmPassword != null)
		   {
			   registerObj.setUsername(username);
			   registerObj.setPassword(password);
			   registerObj.setConfirmPassword(confirmPassword);	   
			   registerObj.clickRegisterBtn();
				
		   }
		   else {
		      LoggerLoad.warn("Invalid data in sheet for username, password, or confirm password");
		   }
		//Assertion
		    String actualMessage=registerObj.setExpectedOutPut();
			Assert.assertEquals(actualMessage,expectedOutput);
			LoggerLoad.info("New Account Created. You are logged in as " + username);
		
		}

	@Test(priority = 1,dataProvider = "RegisterInValidData", dataProviderClass = DataProviders.class)
	@Parameters("browser")
	public void testverify_inValidRegister(String username, String password,String confirmPassword,String invalidExpectedOutput) {
		HomePage homeObj = new HomePage(driver);
		homeObj.getStartedhomeclickwithoutlogin();

		RegisterPage registerObj = new RegisterPage(driver);
		registerObj.registerLink();
		
		unameValidateMsg = registerObj.setUsernameMsgAttribute();
		pwdValidateMsg = registerObj.setPasswordMsgAttribute();
		confirmPwdValidateMsg = registerObj.setConfirmPwdMsgAttribute();
		
		if (username != null || password != null || confirmPassword != null) {
			registerObj.setUsername(username);
			registerObj.setPassword(password);
			registerObj.setConfirmPassword(confirmPassword);
			registerObj.clickRegisterBtn();
		}
		 else {
		      LoggerLoad.warn("Invalid data in sheet for username, password, or confirm password");
		   }
	
		//Assertion
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
	}



	
	
}

