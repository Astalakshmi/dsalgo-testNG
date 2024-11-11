package com.DsAlgo.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.DsAlgo.utilities.ExcelFileReader;
import com.DsAlgo.pageObjects.HomePage;
import com.DsAlgo.pageObjects.LoginPage;
import com.DsAlgo.testBase.BaseClass;
import com.DsAlgo.utilities.ConfigFileReader;
import com.DsAlgo.utilities.DataProviders;
import com.DsAlgo.utilities.LoggerLoad;

public class TC010_HomeTest extends BaseClass {

	HomePage homeObj;

	@BeforeMethod
	public void ExcelReadingdata() throws IOException {
		String path = "./src/test/resources/Excel/TestData.xlsx";// taking excel file from testData
		ExcelFileReader xlutil = new ExcelFileReader(path); // creating object for xlutility
		// String[][] validLoginDatafromExcel = new String[1][2];
		String username = xlutil.getCellData("LoginCredentials", 1, 0);
		String password = xlutil.getCellData("LoginCredentials", 1, 1);
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

	ConfigFileReader configFileReader = ConfigFileReader.getInstance();

	@Test(dataProvider = "ValidLoginDataNoExpectedMsg", dataProviderClass = DataProviders.class)

	public void ValidateHomePageLogo(String username, String password, String expecetedMessage) {
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
		homeObj.numpyLogoclick();
		Assert.assertEquals(homeObj.getActualLogoMessage(), "NumpyNinja");
		LoggerLoad.info("The user is able to see the " + driver.getTitle() + " Logo ");
		
	}
	//-------- @HomepageDropdown @DTTC_002

	@Test(priority=1,dataProvider = "ValidLoginDataNoExpectedMsg", dataProviderClass = DataProviders.class)

	public void ValidateHomePageDropdown(String username, String password, String expecetedMessage) {
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
		homeObj.dataStructureclick();
		Assert.assertEquals(homeObj.getDatastrucActualString(), "Data Structures");
		LoggerLoad.info(
				"The user is able to view the Data Structure dropdown List from " + driver.getTitle() + " home page.");

	}
	
	//----
	@Test(priority=1,dataProvider = "ValidLoginDataNoExpectedMsg", dataProviderClass = DataProviders.class)

	public void ValidateArrayFromDropdownList(String username, String password, String expecetedMessage) {
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
		homeObj.dataStructureclick();
		homeObj.dropDownarraysclick();
		Assert.assertEquals(homeObj.getActualTitle(),"Array");
		LoggerLoad.info("The user is able to view the " + driver.getTitle() + " page.");
		

}
	
	//-------all dropdowns
	
	@Test(dataProvider = "ValidLoginWithHomepageDropdown", dataProviderClass = DataProviders.class)

	public void ValidateHomePageDropdownList(String username, String password, String dropdownListNames,String expectedMesaage) {
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

		homeObj.clickHomeDropdown(dropdownListNames);
		Assert.assertEquals(homeObj.getActualTitle(),expectedMesaage);
		LoggerLoad.info("The user is able to view the " + driver.getTitle() + " page.");
		
	}
	
	//-------@HomeAccountTC_009
	@Test(priority = 1, dataProvider = "ValidLoginDataAccountHolderName", dataProviderClass = DataProviders.class)

	public void ValidateHomePageAccountholderName(String expectedMessage) {

		homeObj.accountHoldernameclick();
		Assert.assertEquals(homeObj.getAccountholdername(), expectedMessage);

		LoggerLoad.info("The User can able to view the account hoidername  " + homeObj.getAccountholdername());

	}
	
}
