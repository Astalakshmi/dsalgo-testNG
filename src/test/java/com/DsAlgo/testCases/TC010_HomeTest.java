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
import com.DsAlgo.testBase.BaseClass;
import com.DsAlgo.utilities.ConfigFileReader;
import com.DsAlgo.utilities.DataProviders;
import com.DsAlgo.utilities.ExcelFileReader;
import com.DsAlgo.utilities.LoggerLoad;



public class TC010_HomeTest extends BaseClass {

	HomePage homeObj;
	ExcelFileReader excelFileReader = new ExcelFileReader(ConfigFileReader.getInstance().getExcelPath());
	String username;
	String password;
	Map<String, String> keyPair;

	@BeforeClass
	public void reading() throws IOException {
		username = excelFileReader.getCellData("LoginCredentials", 1, 0);
		password = excelFileReader.getCellData("LoginCredentials", 1, 1);
		keyPair = excelFileReader.getKeyPair("HomePage");
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
	}

	private String getCurrentMethodName() {
		return StackWalker.getInstance().walk(s -> s.skip(1).findFirst()).get().getMethodName();
	}

	@Test()

	public void ValidateHomePageLogo() {
		homeObj.numpyLogoclick();
		Assert.assertEquals(homeObj.getActualLogoMessage(), keyPair.get(getCurrentMethodName()));
		LoggerLoad.info("The user is able to see the " + driver.getTitle() + " Logo ");
	}

	@Test(priority = 1, dataProvider = "ValidLoginWithHomepageDropdown", dataProviderClass = DataProviders.class)

	public void ValidateHomePageDropdownList(String dropdownListNames, String expectedMessage) {

		homeObj.clickHomeDropdown(dropdownListNames);
		Assert.assertEquals(homeObj.getActualTitle(), expectedMessage);
		LoggerLoad.info("The user is able to view the " + driver.getTitle() + " page.");
	}

	@Test(priority = 2, dataProvider = "ValidHomepageGetStarted", dataProviderClass = DataProviders.class)

	public void ValidateHomePageAllGetStartedButton(String getStartedNames, String expectedMessage) {
		homeObj.clickGetStarted(getStartedNames);
		Assert.assertEquals(homeObj.getActualTitle(), expectedMessage);
		LoggerLoad.info("The user is able to view the " + driver.getTitle() + " page.");
	}

	@Test(priority = 3)

	public void ValidateHomePageAccountholderName() {
		System.out.println("KeyPair = " + keyPair);
		homeObj.accountHoldernameclick();
		Assert.assertEquals(homeObj.getAccountholdername(), keyPair.get(getCurrentMethodName()));
		LoggerLoad.info("The user can able to view the account holdername  " + homeObj.getAccountholdername());
	}

	@Test(priority = 4)

	public void ValidateSignOut() {
		System.out.println("KeyPair = " + keyPair);
		homeObj.clickSignout();
		System.out.println(homeObj.alertSignout());
		Assert.assertEquals(homeObj.alertSignout(), keyPair.get(getCurrentMethodName()));
		LoggerLoad.info("The user is able to signed out from the account.  " + homeObj.alertSignout());
	}
}
