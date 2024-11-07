package com.DsAlgo.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;


public class DataProviders{

	
	public String path="./src/test/resources/testData/TestData.xlsx";//taking excel file from testData
	
					//DataProvider
	
	@DataProvider(name="RegisterData")
	public String[][] getData() throws IOException
	{
		
		ExcelFileReader xlutil = new ExcelFileReader(path); // creating object for xlutility
		int totalrows=xlutil.getRowCount("RegisterPage");
		int totalcols=xlutil.getCellCount("RegisterPage",1);
		String[][] registerData=new String[totalrows-1][totalcols];
		for(int i=2;i<=totalrows;i++) {				//i=2 because row 1 ie,i=1 is positive testcase. It's worked. 
			for(int j=0;j<totalcols;j++) {
				registerData[i-2][j]=xlutil.getCellData("RegisterPage", i, j);//[i-2][j] so that [0][0]
			}
		}

		return registerData;
		
	}
}
