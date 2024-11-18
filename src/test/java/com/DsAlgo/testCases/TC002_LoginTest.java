package com.DsAlgo.testCases;

import java.io.IOException;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


import com.DsAlgo.pageObjects.HomePage;
import com.DsAlgo.pageObjects.LoginPage;
import com.DsAlgo.pageObjects.RegisterPage;
import com.DsAlgo.testBase.BaseClass;
import com.DsAlgo.utilities.ConfigFileReader;
import com.DsAlgo.utilities.DataProviders;
import com.DsAlgo.utilities.ExcelFileReader;
import com.DsAlgo.utilities.LoggerLoad;


@Listeners(com.DsAlgo.utilities.ItestListener.class)

public class TC002_LoginTest extends BaseClass {
	ConfigFileReader configFileReader = ConfigFileReader.getInstance();

	
	HomePage homeObj;
	ExcelFileReader excelFileReader = new ExcelFileReader(ConfigFileReader.getInstance().getExcelPath());
	Map<String, String> keyPair;

	@BeforeClass
	public void reading() throws IOException {
		keyPair = excelFileReader.getKeyPair("LoginCredentials");
	}
	
	private String getCurrentMethodName() {
		return StackWalker.getInstance().walk(s -> s.skip(1).findFirst()).get().getMethodName();
	}

	@Test(dataProvider = "ValidLoginData", dataProviderClass = DataProviders.class)

	public void verify_validLogin(String username, String password, String expectedMessage) {
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

	}

	@Test(priority=1,dataProvider = "InValidLoginData", dataProviderClass = DataProviders.class)

	public void verify_inValidLogin(String username, String password, String expectedMessage) {
		HomePage homeObj = new HomePage(driver);
		homeObj.getStartedhomeclick();
		LoginPage loginObj = new LoginPage(driver);
		loginObj.signInclick();

		if (username != null || password != null) {
			loginObj.setUsername(username);
			loginObj.setLoginPassword(password);
			loginObj.loginBtnclick();
		}

		LoggerLoad.info("User Enter Login credential with username as \" " + username + "\" and password as\" "
				+ password + "\" ");
		String userNameValidationMessage = loginObj.getUserNameValidationMessage();
		String passwordValidationMessage = loginObj.getPasswordNameValidationMessage();
		if (username.isEmpty() && password.isEmpty()) {
			Assert.assertEquals(loginObj.getUserNameValidationMessage(), expectedMessage);
			LoggerLoad.error("The username textfield alert : " + expectedMessage);
		} else if (username.isEmpty() && !password.isEmpty()) {
			Assert.assertEquals(userNameValidationMessage, expectedMessage);
			LoggerLoad.error("The username textfield alert : " + expectedMessage);
		} else if (!username.isEmpty() && password.isEmpty()) {
			Assert.assertEquals(passwordValidationMessage, expectedMessage);
			LoggerLoad.error("The password textfield alert : " + expectedMessage);
		} else if (!username.isEmpty() && !password.isEmpty()) {
			String invalidgetMessage = loginObj.getInvalidLoginValidationMessage();
			Assert.assertEquals(invalidgetMessage, expectedMessage);
			if (expectedMessage.equals("Invalid Username and Password")) {
				LoggerLoad.info("If you don't have an account, please Register");
				loginObj.loginpageRegisterlinkclick();
				driver.navigate().back();
			}
			if (expectedMessage.equals("Invalid Username and Password")) {
				LoggerLoad.info("If you don't have an account, please Register");
				RegisterPage registerObj = new RegisterPage(driver);
				registerObj.registerLink();
			}
		} else {
			LoggerLoad.info("User can able to register");
		}
	}

	@Test(priority=2)

	public void verifyDropdownWithoutLogin() {
		HomePage homeObj = new HomePage(driver);
		homeObj.getStartedhomeclick();
		homeObj.clickDropdownWithoutLogin();
		Assert.assertEquals(homeObj.getActualMessage(), keyPair.get(getCurrentMethodName()));
		LoggerLoad.info("The User is seeing the alert message " + homeObj.getActualMessage());
	}

	@Test(priority = 3)

	public void verifyGetStartedWithoutLogin() {
		HomePage homeObj = new HomePage(driver);
		homeObj.getStartedhomeclick();
		homeObj.getAnylinkofGetStarted();
		Assert.assertEquals(homeObj.getActualMessage(), keyPair.get(getCurrentMethodName()));
		LoggerLoad.info("The User is seeing the alert message " + homeObj.getActualMessage());
	}

	}
