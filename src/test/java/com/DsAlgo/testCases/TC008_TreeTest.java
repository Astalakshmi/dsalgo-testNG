package com.DsAlgo.testCases;

import java.io.IOException;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.DsAlgo.pageObjects.HomePage;
import com.DsAlgo.pageObjects.LoginPage;
import com.DsAlgo.pageObjects.TreePage;
import com.DsAlgo.testBase.BaseClass;
import com.DsAlgo.utilities.ConfigFileReader;
import com.DsAlgo.utilities.DataProviders;
import com.DsAlgo.utilities.ExcelFileReader;
import com.DsAlgo.utilities.LoggerLoad;

public class TC008_TreeTest extends BaseClass {

	HomePage homeObj;
	TreePage treeObj;
	ExcelFileReader excelFileReader = new ExcelFileReader(ConfigFileReader.getInstance().getExcelPath());
	String username;
	String password;
	Map<String, String> keyPair;

	@BeforeClass
	public void reading() throws IOException {
		username = excelFileReader.getCellData("LoginCredentials", 1, 0);
		password = excelFileReader.getCellData("LoginCredentials", 1, 1);
		keyPair = excelFileReader.getKeyPair("TreePage");
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
		treeObj = new TreePage(driver);
		treeObj.getStartedclick();

	}

	private String getCurrentMethodName() {
		return StackWalker.getInstance().walk(s -> s.skip(1).findFirst()).get().getMethodName();
	}

	@Test()
	public void ValidateTreeGetStarted() {
		Assert.assertEquals(treeObj.getActualTitle(), keyPair.get(getCurrentMethodName()));
		LoggerLoad.info("You are viewing the " + driver.getTitle() + " page.");
	}

	@Test(priority = 1, dataProvider = "ValidateTreeTopicLink", dataProviderClass = DataProviders.class)
	public void ValidateTreePageAllLinks(String linkNames, String expectedMessage) {
		treeObj.clickTopicLink(linkNames);
		Assert.assertEquals(treeObj.getActualTitle(), expectedMessage);
		LoggerLoad.info("You are viewing the " + driver.getTitle() + " page.");
	}

	@Test(priority = 2, dataProvider = "ValidateTreeTopicLinkTryHere", dataProviderClass = DataProviders.class)
	public void ValidateTreeTryHere(String linkNames, String expectedMessage) {
		treeObj.clickTopicLink(linkNames);
		treeObj.tryHereclick();
		Assert.assertEquals(treeObj.getActualTitle(), expectedMessage);
		LoggerLoad.info("You are viewing the " + driver.getTitle() + " page.");
	}

	@Test(priority = 3, dataProvider = "TreeTopicLinkTryEditorValidCode", dataProviderClass = DataProviders.class)
	public void ValidateTreeTryEditorPositive(String linkNames, String validPythonCode, String pythonCodeOutput) {
		treeObj.clickTopicLink(linkNames);
		treeObj.tryHereclick();
		treeObj.setCodePositive(validPythonCode);
		treeObj.clickRunBtn();
		Assert.assertEquals(treeObj.getOutput(), pythonCodeOutput);
		LoggerLoad.info("You are viewing the " + driver.getTitle() + " page.");
	}

	@Test(priority = 4, dataProvider = "TreeTopicLinkTryEditorInvalidCode", dataProviderClass = DataProviders.class)
	public void ValidateTreeTryEditorNegative(String linkNames, String invalidCodeInput, String expectedError) {
		treeObj.clickTopicLink(linkNames);
		treeObj.tryHereclick();
		treeObj.setCodeNegative(invalidCodeInput);
		treeObj.clickRunBtn();
		String actualError = driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();
		Assert.assertEquals(actualError, expectedError);
		LoggerLoad.info("You are viewing the " + driver.getTitle() + " page.");
	}

	@Test(priority = 5, dataProvider = "ValidateTreePracticeQuestions", dataProviderClass = DataProviders.class)
	public void ValidateTreePracticeQuestions(String linkNames) {
		treeObj.clickTopicLink(linkNames);
		treeObj.practiceQuestionslink();
		int practiceCount = treeObj.getPracticeQuestionsCount();
		if (practiceCount < 1) {
			Assert.fail("No questions found");
		}
	}
}
