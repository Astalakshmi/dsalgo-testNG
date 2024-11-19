package com.DsAlgo.testCases;

import java.io.IOException;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.DsAlgo.pageObjects.HomePage;
import com.DsAlgo.pageObjects.LoginPage;
import com.DsAlgo.pageObjects.QueuePage;
import com.DsAlgo.testBase.BaseClass;
import com.DsAlgo.utilities.ConfigFileReader;
import com.DsAlgo.utilities.DataProviders;
import com.DsAlgo.utilities.ExcelFileReader;
import com.DsAlgo.utilities.LoggerLoad;

public class TC007_QueueTest extends BaseClass{
	HomePage homeObj ;
	LoginPage loginObj;
	QueuePage queueObj;
	String username ,password;
	Map<String,String> keyPair;
	ExcelFileReader excelFileReader = new ExcelFileReader(ConfigFileReader.getInstance().getExcelPath());
	@BeforeClass
	public void excelLoginData() throws IOException {
		 username = excelFileReader.getCellData("LoginCredentials", 1, 0);
		 password = excelFileReader.getCellData("LoginCredentials", 1, 1);
		 keyPair = excelFileReader.getKeyPair("QueuePage");
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
		queueObj= new QueuePage(driver);
		queueObj.clickQueueStarted();
	}
	
	
	private String getCurrentMethodName() {
		return StackWalker.getInstance().walk(s -> s.skip(1).findFirst()).get().getMethodName();
	}
	
	@Test()
	public void ValidateQueueGetStarted() {

		Assert.assertEquals(queueObj.getActualTitle(), keyPair.get(getCurrentMethodName()));
		LoggerLoad.info("You are viewing the " + driver.getTitle() + " page.");
	}
		
	@Test(priority=1,dataProvider = "ValidateQueueTopicLink", dataProviderClass = DataProviders.class)
	public void ValidateQueuePageAllLinks(String linkNames, String expectedMessage) {
		
		
		queueObj.clickTopicLink(linkNames);
		
		Assert.assertEquals(queueObj.getActualTitle(), expectedMessage);
		LoggerLoad.info("You are viewing the " + driver.getTitle() + " page.");
	}
	
	@Test(priority = 2,dataProvider = "ValidateQueueTopicLinkTryHere", dataProviderClass = DataProviders.class)
	public void ValidateQueueTryHere(String linkNames,String TryHereExpectedOutput) {
		
		queueObj.clickTopicLink(linkNames);
		queueObj.clickQueueInPythonTryHere();
		Assert.assertEquals(queueObj.getActualTitle(), TryHereExpectedOutput);
		LoggerLoad.info("You are viewing the " + driver.getTitle() + " page.");

	}
	@Test(priority = 3,dataProvider = "ValidateQueueTopicLinkTryEditorPositive", dataProviderClass = DataProviders.class)
	public void ValidateQueueTryEditor(String linkNames,String positiveFirstInput,String positiveFirstOutput) {
		queueObj.clickTopicLink(linkNames);
		queueObj.clickQueueInPythonTryHere();
		queueObj.setCodePositive(positiveFirstInput);
		queueObj.clickRunBtn();
		
		Assert.assertEquals(queueObj.getOutput(), positiveFirstOutput);
		LoggerLoad.info("You are viewing the " + driver.getTitle() + " page.");
	}
	
	@Test(priority = 4,dataProvider = "ValidateQueueTopicLinkTryEditorNegative", dataProviderClass = DataProviders.class)
	public void ValidateQueueTryEditorNegative(String linkNames,String negativeInput,String negativeOutput) {
		queueObj.clickTopicLink(linkNames);
		queueObj.clickQueueInPythonTryHere();
		queueObj.setCodePositive(negativeInput);
		queueObj.clickRunBtn();
		String actualError = driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();
		Assert.assertEquals(actualError, negativeOutput);
		LoggerLoad.info("You are viewing the " + driver.getTitle() + " page.");
		
	}

	@Test(priority = 5, dataProvider = "ValidateQueuePracticeQuestions", dataProviderClass = DataProviders.class)
	public void ValidateQueuePracticeQuestions(String linkNames) {
		queueObj.clickTopicLink(linkNames);
		queueObj.handlePracticeQuestionClick();
		int practiceCount = queueObj.getPracticeQuestionsCount();
		if (practiceCount < 1) {
			Assert.fail("No questions found");
		}
	}
}