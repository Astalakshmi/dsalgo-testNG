package com.DsAlgo.testCases;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.DsAlgo.pageObjects.HomePage;
import com.DsAlgo.pageObjects.LinkedListPage;
import com.DsAlgo.pageObjects.LoginPage;
import com.DsAlgo.testBase.BaseClass;
import com.DsAlgo.utilities.ConfigFileReader;
import com.DsAlgo.utilities.DataProviders;
import com.DsAlgo.utilities.ExcelFileReader;
import com.DsAlgo.utilities.ExtentTestManager;
import com.DsAlgo.utilities.ItestListener;
import com.DsAlgo.utilities.LoggerLoad;


public class TC005_LinkedListTest extends BaseClass {
private LinkedListPage linkedlistObject;
HomePage homeObj;
ExcelFileReader excelFileReader = new ExcelFileReader(ConfigFileReader.getInstance().getExcelPath());
String username ="";
String password = "";
Map<String, String> keyPair;

@BeforeClass
public void reading() throws IOException {
	DataProviders.sheetName = "LinkedListPage";
	username = excelFileReader.getCellData("LoginCredentials", 1, 0);
	password = excelFileReader.getCellData("LoginCredentials", 1, 1);
	keyPair = excelFileReader.getKeyPair("LinkedListPage");
	
	LoggerLoad.info("User Enter Login credential with username as \" " + username + "\" and password as\" "
			+ password + "\" ");
	
	for (String name : this.keyPair.keySet()) {
		 String key = name.toString();
		    String value = keyPair.get(name).toString();
		    System.out.println(key + " " + value);
	}
	
}
	
@BeforeMethod
public void ExcelReadingdata() {

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
	linkedlistObject = new LinkedListPage(driver);
	linkedlistObject.clickGetStartedBtn();

}

private String getCurrentMethodName() {
	return StackWalker.getInstance().walk(s -> s.skip(1).findFirst()).get().getMethodName();
}

@Test()
public void ValidateLinkedListGetStarted(Method method) {

	Assert.assertEquals(linkedlistObject.getActualTitle(), keyPair.get(getCurrentMethodName()));
	LoggerLoad.info("You are viewing the " + driver.getTitle() + " page. " + getCurrentMethodName());
}

@Test(priority = 1, dataProvider = "ValidateLinkedListTopicLink", dataProviderClass = DataProviders.class)
public void ValidateLinkedListPageAllLinks(String linkNames, String expectedMessage) {
	linkedlistObject.clickTopicLink(linkNames);
	Assert.assertEquals(linkedlistObject.getActualTitle(), expectedMessage);
	//LoggerLoad.info("You are viewing the " + driver.getTitle() + " page.");
}

@Test(priority = 2, dataProvider = "ValidateLinkedListTopicLinkTryHere", dataProviderClass = DataProviders.class)
public void ValidateLinkedListTryHere(String linkNames, String expectedMessage) {
	linkedlistObject.clickTopicLink(linkNames);
	linkedlistObject.clicktryEditor();
	Assert.assertEquals(linkedlistObject.getActualTitle(), expectedMessage);
	//LoggerLoad.info("You are viewing the " + driver.getTitle() + " page.");
}

@Test(priority = 3, dataProvider = "LinkedListTopicLinkTryEditorValidCode", dataProviderClass = DataProviders.class)
public void ValidateLinkedListTryEditorPositive(String linkNames, String validPythonCode, String pythonCodeOutput) {
	linkedlistObject.clickTopicLink(linkNames);
	linkedlistObject.clicktryEditor();
	linkedlistObject.setCodePositive(validPythonCode);
	linkedlistObject.clickRunBtn();
	Assert.assertEquals(linkedlistObject.getOutput(), pythonCodeOutput);
	LoggerLoad.info("You are viewing the " + driver.getTitle() + " page.");

}

@Test(priority = 4, dataProvider = "LinkedListTopicLinkTryEditorInvalidCode", dataProviderClass = DataProviders.class)
public void ValidateLinkedListTryEditorNegative(String linkNames, String invalidCodeInput, String expectedError) {
	linkedlistObject.clickTopicLink(linkNames);
	linkedlistObject.clicktryEditor();
	linkedlistObject.setCodeNegative(invalidCodeInput);
	linkedlistObject.clickRunBtn();
	String actualError = driver.switchTo().alert().getText();
	driver.switchTo().alert().accept();
	Assert.assertEquals(actualError, expectedError);
	LoggerLoad.info("You are viewing the " + driver.getTitle() + " page.");

}

@Test(priority = 5, dataProvider = "ValidateLinkedListPracticeQuestions", dataProviderClass = DataProviders.class)
public void ValidateLinkedListPracticeQuestions(String linkName) {
	linkedlistObject.clickTopicLink(linkName);
	linkedlistObject.clickPracticeLink();
	int practiceCount = linkedlistObject.getPracticeQuestionsCount();
	if (practiceCount < 1) {
		Assert.fail("No questions found");
	}
}
	
}