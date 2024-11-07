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

	String expectedOutput;
	ConfigFileReader configFileReader = ConfigFileReader.getInstance();

	@Test(dataProvider = "RegisterData", dataProviderClass = DataProviders.class)
	@Parameters("browser")
	public void verify_validRegister(String username, String password,String confirmPassword,String expectedOutput) {
	
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
		    String actualMessage=registerObj.setExpectedOutPut();
			Assert.assertEquals(actualMessage,expectedOutput);
			LoggerLoad.info("New Account Created. You are logged in as " + username);
		
	}

}

