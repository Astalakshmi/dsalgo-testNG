package com.DsAlgo.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;


public class DataProviders{

	
	public String path="./src/test/resources/testData/TestData.xlsx";//taking excel file from testData
	
					//DataProvider
	
	
	@DataProvider(name="RegisterValidData")
	public String[][] getRegisterValidData() throws IOException
	{
		
		ExcelFileReader xlutil = new ExcelFileReader(path); // creating object for xlutility
		int totalcols=xlutil.getCellCount("RegisterPage",1);
		String[][] registerValidData=new String[1][totalcols]; // Create an Object array to store data for only the first row
				// Read data from the first row (row index 1, column 0 to totalCols)		 
			for(int j=0;j<totalcols;j++) 
			{
				registerValidData[0][j]=xlutil.getCellData("RegisterPage", 1, j);
			}
	
		return registerValidData;
		
	}
	
	@DataProvider(name="RegisterInvalidData")
	public String[][] getRegisterInvalidData() throws IOException
	{
		
		ExcelFileReader xlutil = new ExcelFileReader(path); // creating object for xlutility
		int totalrows=xlutil.getRowCount("RegisterPage");
		int totalcols=xlutil.getCellCount("RegisterPage",1);
		String[][] registerInvalidData=new String[totalrows-1][totalcols];
		for(int i=2;i<=totalrows;i++) {				//i=2 because row 1 ie,i=1 is positive testcase. It's worked. 
			for(int j=0;j<totalcols;j++) {
				registerInvalidData[i-2][j]=xlutil.getCellData("RegisterPage", i, j);//[i-2][j] so that [0][0]
			}
		}

		return registerInvalidData;
		
	}
}
