package com.DsAlgo.testCases;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

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
	
	@BeforeMethod
	 public void beforeMethod(Method method, Object[] params) {
        //String methodName = method.getName();
        //List<Object> paramsList = Arrays.asList(params); 
        String[] strArr = Arrays.asList(params).toArray(new String[params.length]);
        //LoggerLoad.info(methodName);
        linkedlistObject= new LinkedListPage(driver);
		HomePage homeObj = new HomePage(driver);
		homeObj.getStartedhomeclick();
		LoginPage loginObj = new LoginPage(driver);
		loginObj.signInclick();
		String userName=strArr[0];
		String password = strArr[1];
		
		LoggerLoad.info("User Enter Login credential with username as \" " + userName + "\" and password as\" "
				+ password + "\" ");
		if (userName != null || strArr[1] != null) {
			loginObj.setUsername(userName);
			loginObj.setLoginPassword(password);
			loginObj.loginBtnclick();
		}
		linkedlistObject.clickGetStartedBtn();
  
	}
	@Test(dataProvider = "ValidLoginData", dataProviderClass = DataProviders.class)
	public void getUserData(String userName, String password,String expectedMessage) {

		Assert.assertEquals(driver.getTitle(), "Linked List");
		
	}

	
	@Test(priority = 2, dataProvider ="LoginAndCheckTopicLinkedList", dataProviderClass = DataProviders.class)
	public void testTopicsLink(String userName, String password,String link,String expectedTitle ) {

		linkedlistObject.clickTopicLink(link);
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle,expectedTitle);
	}
	
	@Test(priority = 3, dataProvider ="LoginAndCheckTopicLinkedList", dataProviderClass = DataProviders.class)
	public void testTryherePage(String userName, String password, String link,String expectedTitle ) {

		linkedlistObject.clickTopicLink(link);
		linkedlistObject.clicktryEditor();
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle,"Assessment");
	}
	
//	@Test(priority = 3, dataProvider ="LoginAndCheckTopicLinkedList", dataProviderClass = DataProviders.class)
//	public void testTryEditor(String userName, String password, String link,String expectedTitle) {
//		
//		linkedlistObject.clickTopicLink(link);
//		linkedlistObject.clicktryEditor();
//		
//	}
	
	

	

}
