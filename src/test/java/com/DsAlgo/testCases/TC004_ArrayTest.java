package com.DsAlgo.testCases;

import java.io.IOException;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.DsAlgo.pageObjects.ArrayPage;
import com.DsAlgo.pageObjects.HomePage;
import com.DsAlgo.pageObjects.LoginPage;
import com.DsAlgo.testBase.BaseClass;
import com.DsAlgo.utilities.ConfigFileReader;
import com.DsAlgo.utilities.DataProviders;
import com.DsAlgo.utilities.ExcelFileReader;
import com.DsAlgo.utilities.LoggerLoad;

public class TC004_ArrayTest extends BaseClass {
	HomePage homeObj;
	ArrayPage arrayObj;
	ExcelFileReader excelFileReader = new ExcelFileReader(ConfigFileReader.getInstance().getExcelPath());
	String username;
	String password;
	Map<String, String> keyPair;
	Map<String, String> keyPair1;

	@BeforeClass
	public void reading() throws IOException {
		username = excelFileReader.getCellData("LoginCredentials", 1, 0);
		password = excelFileReader.getCellData("LoginCredentials", 1, 1);
		keyPair = excelFileReader.getKeyPair("ArrayPage");
		keyPair1 = excelFileReader.getKeyPair("PracticeQns");
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
		arrayObj = new ArrayPage(driver);
		arrayObj.clickArrGetStartedBtn();
	}

	private String getCurrentMethodName() {
		return StackWalker.getInstance().walk(s -> s.skip(1).findFirst()).get().getMethodName();
	}

	@Test()
	public void ValidateArrayGetStarted() {
		Assert.assertEquals(arrayObj.getActualTitle(), keyPair.get(getCurrentMethodName()));
		LoggerLoad.info("You are viewing the " + driver.getTitle() + " page.");
	}

	@Test(priority = 1, dataProvider = "ValidateArrayTopicLink", dataProviderClass = DataProviders.class)
	public void ValidateArrayPageAllLinks(String linkNames, String expectedMessage) {
		System.out.println("linkNames--" + linkNames);
		arrayObj.clickTopicLink(linkNames);
		Assert.assertEquals(arrayObj.getActualTitle(), expectedMessage);
		LoggerLoad.info("You are viewing the " + driver.getTitle() + " page.");
	}

	@Test(priority = 2, dataProvider = "ValidateArrayTopicLinkTryHere", dataProviderClass = DataProviders.class)
	public void ValidateArrayTryHere(String linkNames, String expectedMessage) {
		arrayObj.clickTopicLink(linkNames);
		arrayObj.clickArrTryHereBtn();
		Assert.assertEquals(arrayObj.getActualTitle(), expectedMessage);
		LoggerLoad.info("You are viewing the " + driver.getTitle() + " page.");
	}

	@Test(priority = 3, dataProvider = "ArrayTopicLinkTryEditorValidCode", dataProviderClass = DataProviders.class)
	public void ValidateArrayTryEditorPositive(String linkNames, String validPythonCode, String pythonCodeOutput) {
		arrayObj.clickTopicLink(linkNames);
		arrayObj.clickArrTryHereBtn();
		arrayObj.setCodePositive(validPythonCode);
		arrayObj.clickTryHereRunBtn();
		Assert.assertEquals(arrayObj.getTryHereOutputText(), pythonCodeOutput);
		LoggerLoad.info("You are viewing the " + driver.getTitle() + " page.");
	}

	@Test(priority = 4, dataProvider = "ArrayTopicLinkTryEditorInvalidCode", dataProviderClass = DataProviders.class)
	public void ValidateArrayTryEditorNegative(String linkNames, String invalidCodeInput, String expectedError) {
		arrayObj.clickTopicLink(linkNames);
		arrayObj.clickArrTryHereBtn();
		arrayObj.setCodeNegative(invalidCodeInput);
		arrayObj.clickTryHereRunBtn();
		String actualError = driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();
		Assert.assertEquals(actualError, expectedError);
		LoggerLoad.info("You are viewing the " + driver.getTitle() + " page.");
	}

	@Test(priority = 5, dataProvider = "ValidateArrayPracticeQuestionsRun", dataProviderClass = DataProviders.class)
	public void ValidateArrayPracticeQuestionsRun(String pracQuestionLink, String code, String output) {
		arrayObj.clickArrAIPLink();
		arrayObj.clickArrPracticeQnsLink();
		arrayObj.clickPracticeQuestion(pracQuestionLink);
		arrayObj.enterCodePractice(code);
		arrayObj.clickTryHereRunBtn();
		Assert.assertEquals(arrayObj.getTryHereOutputText(), output);
		LoggerLoad.info("You are viewing the " + driver.getTitle() + " page.");
	}

	@Test(priority = 6, dataProvider = "ValidateArrayPracticeQuestionsSubmit", dataProviderClass = DataProviders.class)
	public void ValidateArrayPracticeQuestionsSubmit(String pracQuestionLink, String code, String output) {
		arrayObj.clickArrAIPLink();
		arrayObj.clickArrPracticeQnsLink();
		arrayObj.clickPracticeQuestion(pracQuestionLink);
		arrayObj.enterCodePractice(code);
		arrayObj.clickSubmitBtn();
		Assert.assertEquals(arrayObj.getTryHereOutputText(), output);
		LoggerLoad.info("You are viewing the " + driver.getTitle() + " page.");
	}
}
