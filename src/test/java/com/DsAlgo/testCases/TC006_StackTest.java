package com.DsAlgo.testCases;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.DsAlgo.pageObjects.HomePage;
import com.DsAlgo.pageObjects.LinkedListPage;
import com.DsAlgo.pageObjects.LoginPage;
import com.DsAlgo.pageObjects.StackPage;
import com.DsAlgo.testBase.BaseClass;
import com.DsAlgo.utilities.ConfigFileReader;
import com.DsAlgo.utilities.DataProviders;
import com.DsAlgo.utilities.ExcelFileReader;
import com.DsAlgo.utilities.LoggerLoad;


public class TC006_StackTest extends BaseClass {
private StackPage stackObject;
HomePage homeObj;
ExcelFileReader excelFileReader = new ExcelFileReader(ConfigFileReader.getInstance().getExcelPath());
String username ="";
String password = "";
Map<String, String> keyPair;

@BeforeClass
public void reading() throws IOException {
	username = excelFileReader.getCellData("LoginCredentials", 1, 0);
	password = excelFileReader.getCellData("LoginCredentials", 1, 1);
	keyPair = excelFileReader.getKeyPair("StackPage");
	
	LoggerLoad.info("efhdjfhjfhdjUser Enter Login credential with username as \" " + username + "\" and password as\" "
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
	stackObject = new StackPage(driver);
	stackObject.clickGetStartedBtn();

}

private String getCurrentMethodName() {
	return StackWalker.getInstance().walk(s -> s.skip(1).findFirst()).get().getMethodName();
}

@Test()
public void ValidateStackGetStarted() {
	Assert.assertEquals(stackObject.getActualTitle(), keyPair.get(getCurrentMethodName()));
	LoggerLoad.info("You are viewing the " + driver.getTitle() + " page. " + getCurrentMethodName());
}

@Test(priority = 1, dataProvider = "ValidateStackTopicLink", dataProviderClass = DataProviders.class)
public void ValidateStackPageAllLinks(String linkNames, String expectedMessage) {
	stackObject.clickTopicLink(linkNames);
	Assert.assertEquals(stackObject.getActualTitle(), expectedMessage);
	//LoggerLoad.info("You are viewing the " + driver.getTitle() + " page.");
}

@Test(priority = 2, dataProvider = "ValidateStackTopicLinkTryHere", dataProviderClass = DataProviders.class)
public void ValidateStackTryHere(String linkNames, String expectedMessage) {
	stackObject.clickTopicLink(linkNames);
	stackObject.clicktryEditor();
	Assert.assertEquals(stackObject.getActualTitle(), expectedMessage);
	//LoggerLoad.info("You are viewing the " + driver.getTitle() + " page.");
}

@Test(priority = 3, dataProvider = "StackTopicLinkTryEditorValidCode", dataProviderClass = DataProviders.class)
public void ValidateStackTryEditorPositive(String linkNames, String validPythonCode, String pythonCodeOutput) {
	stackObject.clickTopicLink(linkNames);
	stackObject.clicktryEditor();
	stackObject.setCodePositive(validPythonCode);
	stackObject.clickRunBtn();
	Assert.assertEquals(stackObject.getOutput(), pythonCodeOutput);
	LoggerLoad.info("You are viewing the " + driver.getTitle() + " page.");

}

@Test(priority = 4, dataProvider = "StackTopicLinkTryEditorInvalidCode", dataProviderClass = DataProviders.class)
public void ValidateStackTryEditorNegative(String linkNames, String invalidCodeInput, String expectedError) {
	stackObject.clickTopicLink(linkNames);
	stackObject.clicktryEditor();
	stackObject.setCodeNegative(invalidCodeInput);
	stackObject.clickRunBtn();
	String actualError = driver.switchTo().alert().getText();
	driver.switchTo().alert().accept();
	Assert.assertEquals(actualError, expectedError);
	LoggerLoad.info("You are viewing the " + driver.getTitle() + " page.");

}

@Test(priority = 5, dataProvider = "ValidateStackPracticeQuestions", dataProviderClass = DataProviders.class)
public void ValidateStackPracticeQuestions(String linkName) {
	stackObject.clickTopicLink(linkName);
	stackObject.clickPracticeLink();
	int practiceCount = stackObject.getPracticeQuestionsCount();
	if (practiceCount < 1) {
		Assert.fail("No questions found");
	}
}
	
}