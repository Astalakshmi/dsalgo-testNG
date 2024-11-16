package com.DsAlgo.testCases;

import java.io.IOException;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.DsAlgo.pageObjects.GraphPage;
import com.DsAlgo.pageObjects.HomePage;
import com.DsAlgo.pageObjects.LoginPage;
import com.DsAlgo.testBase.BaseClass;
import com.DsAlgo.utilities.ConfigFileReader;
import com.DsAlgo.utilities.DataProviders;
import com.DsAlgo.utilities.ExcelFileReader;
import com.DsAlgo.utilities.LoggerLoad;

public class TC009_GraphTest  extends BaseClass{
	HomePage homeObj ;
	LoginPage loginObj;
	GraphPage graphObj;
	String username ,password;
	Map<String,String> keyPair;
	ExcelFileReader excelFileReader = new ExcelFileReader(ConfigFileReader.getInstance().getExcelPath());
	@BeforeClass
	public void excelLoginData() throws IOException {
		 username = excelFileReader.getCellData("LoginCredentials", 1, 0);
		 password = excelFileReader.getCellData("LoginCredentials", 1, 1);
		 keyPair = excelFileReader.getKeyPair("GraphPage");
	}
	
	@BeforeMethod	
	public void ExcelReadingdata(){
				 		    	
		homeObj = new HomePage(driver);
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
		graphObj= new GraphPage(driver);
		graphObj.clickGraphStarted();
	}
	
	
	private String getCurrentMethodName() {
		return StackWalker.getInstance().walk(s -> s.skip(1).findFirst()).get().getMethodName();
	}
	@Test()
	public void ValidateGraphGetStarted() {

		Assert.assertEquals(graphObj.getActualTitle(), keyPair.get(getCurrentMethodName()));
		LoggerLoad.info("You are viewing the " + driver.getTitle() + " page.");
	}
		
	@Test(priority=1,dataProvider = "ValidateGraphTopicLink", dataProviderClass = DataProviders.class)
	public void ValidateGraphPageAllLinks(String linkNames, String ExceptedTitle) {
		
		graphObj.clickTopicLink(linkNames);	
		Assert.assertEquals(graphObj.getActualTitle(), ExceptedTitle);
		LoggerLoad.info("You are viewing the " + driver.getTitle() + " page.");
	}
	

	@Test(priority = 2,dataProvider = "ValidateGraphTopicLinkTryHere", dataProviderClass = DataProviders.class)
	public void ValidateGraphTryHere(String linkNames,String TryHereExpectedOutput) {
		
		graphObj.clickTopicLink(linkNames);
		graphObj.clickGraphTryHereBtn();
		Assert.assertEquals(graphObj.getActualTitle(), TryHereExpectedOutput);
		LoggerLoad.info("You are viewing the " + driver.getTitle() + " page.");

	}
	@Test(priority = 3,dataProvider = "ValidateGraphTopicLinkTryEditorPositive", dataProviderClass = DataProviders.class)
	public void ValidateGraphTryEditorPositive(String linkNames,String positiveInput,String positiveOutput) {
		graphObj.clickTopicLink(linkNames);
		graphObj.clickGraphTryHereBtn();
		graphObj.setCodePositive(positiveInput);
		graphObj.clickRunBtn();
		
		Assert.assertEquals(graphObj.getOutput(), positiveOutput);
		LoggerLoad.info("You are viewing the " + driver.getTitle() + " page.");
		
	}
	
	@Test(priority = 4,dataProvider = "ValidateGraphTopicLinkTryEditorNegative", dataProviderClass = DataProviders.class)
	public void ValidateGraphTryEditorNegative(String linkNames,String negativeInput,String negativeOutput) {
		graphObj.clickTopicLink(linkNames);
		graphObj.clickGraphTryHereBtn();
		graphObj.setCodePositive(negativeInput);
		graphObj.clickRunBtn();
		String actualError = driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();
		Assert.assertEquals(actualError, negativeOutput);
		LoggerLoad.info("You are viewing the " + driver.getTitle() + " page.");
		
	}
	@Test(priority = 5, dataProvider = "ValidateGraphPracticeQuestions", dataProviderClass = DataProviders.class)
	public void ValidateGraphPracticeQuestions(String linkNames) {
		graphObj.clickTopicLink(linkNames);
		graphObj.clickHandleGraphPracticeQuestion();
		int practiceCount = graphObj.getPracticeQuestionsCount();
		if (practiceCount < 1) {
			Assert.fail("No questions found");
		}
	}
	
  }


