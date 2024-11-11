package com.DsAlgo.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import com.DsAlgo.utilities.ExcelFileReader;

public class DataProviders {

	public String path = "./src/test/resources/testData/TestData.xlsx";// taking excel file from testData

	// DataProvider

//	@DataProvider(name = "RegisterData")
//	public String[][] getData() throws IOException {
//
//		ExcelFileReader xlutil = new ExcelFileReader(path); // creating object for xlutility
//		int totalrows = xlutil.getRowCount("RegisterPage");
//		int totalcols = xlutil.getCellCount("RegisterPage", 1);
//		String[][] registerData = new String[totalrows - 2][totalcols];
//		for (int i = 2; i <= totalrows; i++) { // i=2 because row 1 ie,i=1 is positive testcase. It's worked.
//			for (int j = 0; j < totalcols; j++) {
//				registerData[i - 2][j] = xlutil.getCellData("RegisterPage", i, j);// [i-2][j] so that [0][0]
//			}
//		}
//
//		return registerData;
//
//	}

	@DataProvider(name = "RegisterValidData")
	public Object[][] getRegisterValidData() throws IOException {

		ExcelFileReader xlutil = new ExcelFileReader(path); // creating object for xlutility
		int totalcols = xlutil.getCellCount("RegisterPage", 1);
		Object[][] registerValidData = new Object[1][totalcols]; // Create an Object array to store data for only the
																	// first row
		// Read data from the first row (row index 1, column 0 to totalCols)
		for (int j = 0; j < totalcols; j++) {
			registerValidData[0][j] = xlutil.getCellData("RegisterPage", 1, j);
		}

		return registerValidData;

	}

	@DataProvider(name = "RegisterInvalidData")
	public Object[][] getRegisterInvalidData() throws IOException {

		ExcelFileReader xlutil = new ExcelFileReader(path); // creating object for xlutility
		int totalrows = xlutil.getRowCount("RegisterPage");
		int totalcols = xlutil.getCellCount("RegisterPage", 1);
		Object[][] registerInvalidData = new Object[totalrows - 1][totalcols];
		for (int i = 2; i <= totalrows; i++) { // i=2 because row 1 ie,i=1 is positive testcase. It's worked.
			for (int j = 0; j < totalcols; j++) {
				registerInvalidData[i - 2][j] = xlutil.getCellData("RegisterPage", i, j);// [i-2][j] so that [0][0]
			}
		}

		return registerInvalidData;

	}
// -----------------Login Credentials------------------------

	@DataProvider(name = "ValidLoginData")
	public String[][] getValidLoginData() throws IOException {
		ExcelFileReader xlutil = new ExcelFileReader(path); // creating object for xlutility
		String[][] validLoginData = new String[1][3];
		validLoginData[0][0] = xlutil.getCellData("LoginCredentials", 1, 0);
		validLoginData[0][1] = xlutil.getCellData("LoginCredentials", 1, 1);
		validLoginData[0][2] = xlutil.getCellData("LoginCredentials", 1, 2);
		return validLoginData;
	}

	@DataProvider(name = "InValidLoginData")
	public String[][] getInValidLoginData() throws IOException {
		ExcelFileReader xlutil = new ExcelFileReader(path); // creating object for xlutility
		int totalrows = xlutil.getRowCount("LoginCredentials");
		int totalcols = xlutil.getCellCount("LoginCredentials", 0);
		System.out.println("Total Rows = " + totalrows + ", Total Columns = " + totalcols);
		String[][] invalidLoginData = new String[totalrows - 2][totalcols];
		for (int i = 2; i < totalrows; i++) {
			for (int j = 0; j < 3; j++) {
				invalidLoginData[i - 2][j] = xlutil.getCellData("LoginCredentials", i, j);
				System.out.print(invalidLoginData[i - 2][j] + ",");
			}
			System.out.println();
		}
		return invalidLoginData;
	}

	@DataProvider(name = "ValidLoginDataNoExpectedMsg")
	public String[][] getValidLoginDataNoExpectedMessage() throws IOException {
		ExcelFileReader xlutil = new ExcelFileReader(path); // creating object for xlutility
		String[][] validLoginData = new String[1][3];
		validLoginData[0][0] = xlutil.getCellData("LoginCredentials", 1, 0);
		validLoginData[0][1] = xlutil.getCellData("LoginCredentials", 1, 1);
		// validLoginData[0][2]=xlutil.getCellData("LoginCredentials", 1, 2);
		validLoginData[0][2]= xlutil.getCellData("DataStructuresPage", 1, 0);
		return validLoginData;
	}

	@DataProvider(name="ValidLoginWithHomepageDropdown")
	public Object[][] getLoginHomeDropdown() throws IOException{
		ExcelFileReader xlutil = new ExcelFileReader(path); // creating object for xlutility
		
		int rowsHomePageSheet=xlutil.getRowCount("HomePage");
		System.out.println("No of Rows :" +rowsHomePageSheet);
		
		int totalcols = xlutil.getCellCount("HomePage", 1);
		System.out.println("Total Columns "+totalcols);
	//int newArrRow 
		
//		int rowsPositiveinput=2; //only for positive input
//		int newArrcols=5; // total columns needed to return
		//int newArrRow = rowsinTopicSheet * rowsPositiveinput ;
		
	//	Object[][] loginHomeDropdown=new Object[rowsHomePageSheet-1][totalcols];//[7][2]
	//	int k=0;
		
		String[][] loginHomeDropdown = new String[7][4];
		for(int i=1;i<=7;i++) {				
			for(int j=0;j<4;j++) {
				loginHomeDropdown[i-1][0]=xlutil.getCellData("LoginCredentials", 1, 0); // username
			//System.out.println(loginHomeDropdown[i-1][0]);
			
			loginHomeDropdown[i-1][1]=xlutil.getCellData("LoginCredentials", 1, 1); //password
		//	System.out.println(loginHomeDropdown[i-1][1]);
			
			loginHomeDropdown[i-1][2]=xlutil.getCellData("HomePage", i, 0); //topic link
		//	System.out.println(loginHomeDropdown[i-1][2]);
			
			loginHomeDropdown[i-1][3]=xlutil.getCellData("HomePage", i, 1); //code
		//	System.out.println(loginHomeDropdown[i-1][3]);
			
			System.out.println("The data is "+loginHomeDropdown[i - 1][j] + ",");
		
//			System.out.println("HomePAge " + i +"col" + j);
			//loginAndPythonData[k][4]=xlutil.getCellData("TryEditor", j, 1); //output
			//	k++;
			}
		}
		//System.out.println(Arrays.deepToString(loginHomeDropdown));
		return loginHomeDropdown;
	}
	
	@DataProvider(name = "ValidLoginDataAccountHolderName")
	public String[][] getValidLoginDataAccountHolderName() throws IOException {
		ExcelFileReader xlutil = new ExcelFileReader(path); // creating object for xlutility
		String[][] validLoginDataAccountHolder = new String[1][1];
		validLoginDataAccountHolder[0][0] = xlutil.getCellData("HomePage", 9, 1);
		return validLoginDataAccountHolder;
	}

}
