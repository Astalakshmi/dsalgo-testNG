package com.DsAlgo.testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.DsAlgo.pageObjects.HomePage;
import com.DsAlgo.pageObjects.LinkedListPage;
import com.DsAlgo.pageObjects.LoginPage;
import com.DsAlgo.testBase.BaseClass;import com.DsAlgo.utilities.DataProviders;
import com.DsAlgo.utilities.LoggerLoad;


public class TC005_LinkedListTest extends BaseClass {
private LinkedListPage linkedlistObject;
	
	
	@Test(dataProvider = "ValidLoginData", dataProviderClass = DataProviders.class)
	public void testLinkedListGetStarted(String userName, String password,String expectedMessage) {
		linkedlistObject= new LinkedListPage(driver);
		HomePage homeObj = new HomePage(driver);
		homeObj.getStartedhomeclick();
		LoginPage loginObj = new LoginPage(driver);
		loginObj.signInclick();
		
//		LoggerLoad.info("User Enter Login credential with username as \" " + userName + "\" and password as\" "
//				+ password + "\" ");
		if (userName != null || password != null) {
			loginObj.setUsername(userName);
			loginObj.setLoginPassword(password);
			loginObj.loginBtnclick();
		}
		linkedlistObject.clickGetStartedBtn();
		
	}

	
	@Test(priority = 2, dataProvider ="LoginAndCheckTopicLinkedList", dataProviderClass = DataProviders.class)
	public void testTopicsLink(String link,String expectedTitle, String userName, String password) {
		linkedlistObject= new LinkedListPage(driver);
		HomePage homeObj = new HomePage(driver);
		homeObj.getStartedhomeclick();
		LoginPage loginObj = new LoginPage(driver);
		loginObj.signInclick();
		
		LoggerLoad.info("User Enter Login credential with username as \" " + userName + "\" and password as\" "
				+ password + "\" ");
		if (userName != null || password != null) {
			loginObj.setUsername(userName);
			loginObj.setLoginPassword(password);
			loginObj.loginBtnclick();
		}
		linkedlistObject.clickGetStartedBtn();
		//linkedlistObject.clickIntroductionLink();
		linkedlistObject.clickTopicLink(link);
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle,expectedTitle);
	}
	
	
//	@Test(priority = 3)
//	public void testLinkedListTopicLinks() {
//		linkedlistObject= new LinkedListPage(driver);
//		linkedlistObject.clickGetStartedBtn();
//		linkedlistObject.clickCreatingLinkedListLink();
//	}
	

}
