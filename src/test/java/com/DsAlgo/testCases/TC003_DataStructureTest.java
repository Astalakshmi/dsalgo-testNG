package com.DsAlgo.testCases;

import java.io.IOException;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


import com.DsAlgo.pageObjects.DataStructurePage;
import com.DsAlgo.pageObjects.HomePage;
import com.DsAlgo.pageObjects.LoginPage;
import com.DsAlgo.testBase.BaseClass;
import com.DsAlgo.utilities.ConfigFileReader;
import com.DsAlgo.utilities.DataProviders;
import com.DsAlgo.utilities.ExcelFileReader;
import com.DsAlgo.utilities.LoggerLoad;


@Listeners(com.DsAlgo.utilities.ItestListener.class)

public class TC003_DataStructureTest extends BaseClass {

	HomePage homeObj;
	DataStructurePage datastructureObj;
	ExcelFileReader excelFileReader = new ExcelFileReader(ConfigFileReader.getInstance().getExcelPath());
	String username;
	String password;
	Map<String, String> keyPair;

	@BeforeClass
	public void reading() throws IOException {
		username = excelFileReader.getCellData("LoginCredentials", 1, 0);
		password = excelFileReader.getCellData("LoginCredentials", 1, 1);
		keyPair = excelFileReader.getKeyPair("DataStructurePage");
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

		datastructureObj = new DataStructurePage(driver);
		datastructureObj.getStartedclick();
	}

	private String getCurrentMethodName() {
		return StackWalker.getInstance().walk(s -> s.skip(1).findFirst()).get().getMethodName();
	}

	@Test()
	public void ValidateDataStructureGetStarted() {

		Assert.assertEquals(datastructureObj.getActualTitle(), keyPair.get(getCurrentMethodName()));
		LoggerLoad.info("You are viewing the " + driver.getTitle() + " page.");
	}

	@Test(priority = 1)

	public void ValidateDataStructureTimeComplexity() {

		datastructureObj.timeComplexclick();
		Assert.assertEquals(datastructureObj.getActualTitle(), keyPair.get(getCurrentMethodName()));
		LoggerLoad.info("You are viewing the " + driver.getTitle() + " page.");

	}

	@Test(priority = 2)

	public void ValidateTimeComplexityTryHere() {
		datastructureObj.timeComplexclick();
		datastructureObj.tryHereclick();
		Assert.assertEquals(datastructureObj.getActualTitle(), keyPair.get(getCurrentMethodName()));
		LoggerLoad.info("You are viewing the Assessment Title in the Try Editor Page" + driver.getTitle() + " page.");

	}

	// -----@DataTC_006

	@Test(priority = 3, dataProvider = "DataTryEditorValidCode", dataProviderClass = DataProviders.class)

	public void ValidateTimeComplexityTryEditorPositive(String validPythonCode, String pythonCodeOutput) {
		datastructureObj.timeComplexclick();
		datastructureObj.tryHereclick();
		datastructureObj.setCodePositive(validPythonCode);
		datastructureObj.clickRunBtn();
		Assert.assertEquals(datastructureObj.getOutput(), pythonCodeOutput);
		LoggerLoad.info("You are viewing the Assessment Title in the Try Editor Page" + driver.getTitle() + " page.");

	}

	@Test(priority = 4, dataProvider = "DataTryEditorInvalidCode", dataProviderClass = DataProviders.class)
	public void ValidateDataTryEditorNegative(String invalidCodeInput, String expectedError) {
		datastructureObj.timeComplexclick();
		datastructureObj.tryHereclick();
		datastructureObj.setCodeNegative(invalidCodeInput);
		datastructureObj.clickRunBtn();
		String actualError = driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();
		Assert.assertEquals(actualError, expectedError);
		LoggerLoad.info("You are viewing the " + driver.getTitle() + " page.");

	}

	@Test(priority = 5)

	public void ValidatePraticeQuestions() {
		datastructureObj.timeComplexclick();
		datastructureObj.pracQuesclick();
		datastructureObj.getPracticeQuestionscount();// Assert
	}
}
