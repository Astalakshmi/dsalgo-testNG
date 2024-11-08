package com.DsAlgo.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.DsAlgo.pageObjects.ArrayPage;
import com.DsAlgo.pageObjects.HomePage;
import com.DsAlgo.pageObjects.LoginPage;
import com.DsAlgo.testBase.BaseClass;
import com.DsAlgo.utilities.ConfigFileReader;
import com.DsAlgo.utilities.DataProviders;
import com.DsAlgo.utilities.LoggerLoad;

public class TC004_ArrayTest extends BaseClass{

	ConfigFileReader configFileReader = ConfigFileReader.getInstance();
	@Test(priority=1,dataProvider = "ValidLoginData", dataProviderClass = DataProviders.class)
	
	public void landArrayPage(String username, String password, String expectedMessage) {
		HomePage homeObj = new HomePage(driver);
		homeObj.getStartedhomeclick();
		LoginPage loginObj = new LoginPage(driver);
		loginObj.signInclick();
		
		LoggerLoad.info("User Enter Login credential with username as \" " + username + "\" and password as\" "
				+ password + "\" ");
		if (username != null || password != null) {
			loginObj.setUsername(username);
			loginObj.setLoginPassword(password);
			loginObj.loginBtnclick();
		}
	
		Assert.assertEquals(loginObj.getLoginValidationMessage(), expectedMessage);
		LoggerLoad.info("The user is on the " + driver.getTitle() + " home page and successfully logged in.");
		
		ArrayPage arrayObj=new ArrayPage(driver);
		arrayObj.clickArrGetStartedBtn(); 
		Assert.assertEquals(arrayObj.getActualTitle(),"Array" );
		LoggerLoad.info("The user is on the " + driver.getTitle() + " Page and successfully logged in.");
	}


}
