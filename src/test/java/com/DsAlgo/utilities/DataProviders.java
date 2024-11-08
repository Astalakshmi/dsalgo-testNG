package com.DsAlgo.utilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.collections.Lists;



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
	
	@DataProvider(name="RegisterValidData")
	public Object[][] getRegisterValidData() throws IOException
	{
		
		ExcelFileReader xlutil = new ExcelFileReader(path); // creating object for xlutility
		int totalcols=xlutil.getCellCount("RegisterPage",1);
		Object[][] registerValidData=new Object[1][totalcols]; // Create an Object array to store data for only the first row
				// Read data from the first row (row index 1, column 0 to totalCols)		 
			for(int j=0;j<totalcols;j++) 
			{
				registerValidData[0][j]=xlutil.getCellData("RegisterPage", 1, j);
			}

		return registerValidData;
		
	}
	
	@DataProvider(name="RegisterInvalidData")
	public Object[][] getRegisterInvalidData() throws IOException
	{
		
		ExcelFileReader xlutil = new ExcelFileReader(path); // creating object for xlutility
		int totalrows=xlutil.getRowCount("RegisterPage");
		int totalcols=xlutil.getCellCount("RegisterPage",1);
		Object[][] registerInvalidData=new Object[totalrows-1][totalcols];
		for(int i=2;i<=totalrows;i++) {				//i=2 because row 1 ie,i=1 is positive testcase. It's worked. 
			for(int j=0;j<totalcols;j++) {
				registerInvalidData[i-2][j]=xlutil.getCellData("RegisterPage", i, j);//[i-2][j] so that [0][0]
			}
		}

		return registerInvalidData;
		
	}
// -----------------Login Credentials------------------------

	@DataProvider(name = "InValidLoginData")
	public String[][] getInValidLoginData() throws IOException {
		ExcelFileReader xlutil = new ExcelFileReader(path); // creating object for xlutility
		int totalrows = xlutil.getRowCount("LoginCredentials");
		int totalcols = xlutil.getCellCount("LoginCredentials", 1);
		String[][] invalidLoginData = new String[totalrows - 2][totalcols];
		for (int i = 2; i <= totalrows; i++) {
			for (int j = 0; j < totalcols; j++) {
				invalidLoginData[i - 2][j] = xlutil.getCellData("LoginCredentials", i, j);
			}
		}
		return invalidLoginData;
	}

	@DataProvider(name = "ValidLoginData")
	public Object[][] getValidLoginData() throws IOException {
		ExcelFileReader xlutil = new ExcelFileReader(path); // creating object for xlutility
		Object[][] validLoginData = new Object[1][3];
		validLoginData[0][0]=xlutil.getCellData("LoginCredentials", 1, 0);
		validLoginData[0][1]=xlutil.getCellData("LoginCredentials", 1, 1);
		validLoginData[0][2]=xlutil.getCellData("LoginCredentials", 1, 2);
		return validLoginData;
	}
	
	
// -----------------Linked list topics------------------------
	
	@DataProvider(name= "Topics")
	public Object[][] gettopicValue() throws IOException {
		ExcelFileReader xlutil = new ExcelFileReader(path); // creating object for xlutility
		int totalrows = xlutil.getRowCount("Topics");
		System.out.println("total rows "+totalrows);
		//int totalcols = xlutil.getCellCount("Topics", 1);
		Object[][] topicsData = new Object[totalrows][1];
		for (int i = 1; i <= totalrows; i++) {
			//System.out.println("wwwwww "+i);
			for (int j = 0; j < 1; j++) {
				//System.out.println("wwwwww "+i+j+ xlutil.getCellData("Topics", i, j));
				topicsData[i-1][j] = xlutil.getCellData("Topics", i, j);
			}
		}
		System.out.println(Arrays.deepToString(topicsData));
		return topicsData;
	}
	
	
	@DataProvider(name = "LoginAndCheckTopicLinkedList")
	public Object[][] getLoginAndTopicCheck() throws IOException {
		
		ExcelFileReader xlutil = new ExcelFileReader(path); // creating object for xlutility
		int totalrowsinTopicSheet=xlutil.getRowCount("LinkedListTopics");
		int totalcols=4;
		Object[][] loginAndTopicData=new Object[totalrowsinTopicSheet][totalcols];
		for(int i=1;i<=totalrowsinTopicSheet;i++) {				
			//for(int j=0;j<1;j++) {
				loginAndTopicData[i-1][0]=xlutil.getCellData("LinkedListTopics", i, 0);
				loginAndTopicData[i-1][1]=xlutil.getCellData("LinkedListTopics", i, 1);
				loginAndTopicData[i-1][2]=xlutil.getCellData("LoginCredentials", 1, 0);
				loginAndTopicData[i-1][3]=xlutil.getCellData("LoginCredentials", 1, 1);//[i-2][j] so that [0][0]
			//}
		}
		
		System.out.println(Arrays.deepToString(loginAndTopicData));
		return loginAndTopicData;
		
	}
	
	

}

