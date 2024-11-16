package com.DsAlgo.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	ExcelFileReader excelFileReader = new ExcelFileReader(ConfigFileReader.getInstance().getExcelPath());

	// -----------------Register Page------------------------
	
	@DataProvider(name = "RegisterValidData")
	public String[][] getRegisterValidData() throws IOException {

		int totalcols = excelFileReader.getCellCount("RegisterPage", 1);
		String[][] registerValidData = new String[1][totalcols]; // Create an Object array to store data for only the
																	// first row
		// Read data from the first row (row index 1, column 0 to totalCols)
		for (int j = 0; j < totalcols; j++) {
			registerValidData[0][j] = excelFileReader.getCellData("RegisterPage", 1, j);
		}

		return registerValidData;

	}

	@DataProvider(name = "RegisterInvalidData")
	public String[][] getRegisterInvalidData() throws IOException {

		int totalrows = excelFileReader.getRowCount("RegisterPage");
		int totalcols = excelFileReader.getCellCount("RegisterPage", 1);
		String[][] registerInvalidData = new String[totalrows - 1][totalcols];
		for (int i = 2; i <= totalrows; i++) { // i=2 because row 1 ie,i=1 is positive testcase. It's worked.
			for (int j = 0; j < totalcols; j++) {
				registerInvalidData[i - 2][j] = excelFileReader.getCellData("RegisterPage", i, j);// [i-2][j] so that
																									// [0][0]
			}
		}

		return registerInvalidData;

	}

	// -----------------Login Credentials------------------------

	@DataProvider(name = "ValidLoginData")
	public String[][] getValidLoginData() throws IOException {

		String[][] validLoginData = new String[1][3];
		validLoginData[0][0] = excelFileReader.getCellData("LoginCredentials", 1, 0);
		validLoginData[0][1] = excelFileReader.getCellData("LoginCredentials", 1, 1);
		validLoginData[0][2] = excelFileReader.getCellData("LoginCredentials", 1, 2);
		return validLoginData;
	}

	@DataProvider(name = "InValidLoginData")
	public String[][] getInValidLoginData() throws IOException {

		int totalrows = excelFileReader.getRowCount("LoginCredentials");
		int totalcols = excelFileReader.getCellCount("LoginCredentials", 0);
		System.out.println("Total Rows = " + totalrows + ", Total Columns = " + totalcols);
		String[][] invalidLoginData = new String[totalrows - 2][totalcols];
		for (int i = 2; i < totalrows; i++) {
			for (int j = 0; j < 3; j++) {
				invalidLoginData[i - 2][j] = excelFileReader.getCellData("LoginCredentials", i, j);
				System.out.print(invalidLoginData[i - 2][j] + ",");
			}
			System.out.println();
		}
		return invalidLoginData;
	}

	@DataProvider(name = "ValidLoginWithHomepageDropdown", indices = { 1, 2, 3, 4, 5, 6, 7 })
	public String[][] getLoginHomeDropdown() throws IOException {

		int rowsHomePageSheet = excelFileReader.getRowCount("HomePage");
		System.out.println("No of Rows :" + rowsHomePageSheet);

		int totalcols = excelFileReader.getCellCount("HomePage", 1);
		System.out.println("Total Columns " + totalcols);

		String[][] loginHomeDropdown = new String[rowsHomePageSheet][totalcols];
		for (int i = 1; i < rowsHomePageSheet; i++) {
			for (int j = 0; j < totalcols; j++) {
				loginHomeDropdown[i - 1][0] = excelFileReader.getCellData("HomePage", i, 0); // SheetName - HomePage,
																								// Row- 1,Column-0

				loginHomeDropdown[i - 1][1] = excelFileReader.getCellData("HomePage", i, 1); // SheetName - HomePage,
																								// Row- 1,Column-1

			}
			System.out.print("The data is " + loginHomeDropdown[i - 1][0] + ",");
			System.out.println("The data is " + loginHomeDropdown[i - 1][1] + ",");
		}

		return loginHomeDropdown;
	}

	@DataProvider(name = "ValidLoginDataAccountHolderName")
	public String[] getValidLoginDataAccountHolderName() throws IOException {
		String[] validLoginDataAccountHolder = new String[1];
		validLoginDataAccountHolder[0] = excelFileReader.getCellData("HomePage", 8, 0);
		System.out.println(validLoginDataAccountHolder);
		return validLoginDataAccountHolder;
	}

	// --------@DataProvider(name = "LoginAndCheckTopicLinkedList")

	@DataProvider(name = "ValidateTreeTopicLink")
	public String[][] getTreeTopicLink() throws IOException {
		int rowsTreePageSheet = excelFileReader.getRowCount("TreePage");
		int totalcols = excelFileReader.getCellCount("TreePage", 2);
		System.out.println("The Row is :" + rowsTreePageSheet + "Columns : " +totalcols);
		String[][] topicData = new String[rowsTreePageSheet - 1][totalcols];
		for (int i = 2; i < 15; i++) {
			topicData[i - 2][0] = excelFileReader.getCellData("TreePage", i, 0);
			topicData[i - 2][1] = excelFileReader.getCellData("TreePage", i, 1);
			System.out.print("The data is " + topicData[i - 2][0] + ",");
			System.out.println("The data is " + topicData[i - 2][1] + ",");
		}
		return topicData;
	}
	
	//-------------------Graphpage   -------------------------------------
	
	@DataProvider(name = "ValidateGraphTopicLink")
	public String[][] getGraphTopicLink() throws IOException {
		int rowsGraphPageSheet = excelFileReader.getRowCount("GraphPage");
		int totalcols = excelFileReader.getCellCount("GraphPage", 2);
		System.out.println("The Row is :" + rowsGraphPageSheet + "Columns : " +totalcols);
		String[][] topicData = new String[rowsGraphPageSheet- 1][2];
		for (int i = 2; i < 4; i++) {
			topicData[i - 2][0] = excelFileReader.getCellData("GraphPage", i, 0);
			topicData[i - 2][1] = excelFileReader.getCellData("GraphPage", i, 1);
		}
		return topicData;
	}
    

	@DataProvider(name = "ValidateGraphTopicLinkTryHere")
	public String[][] getGraphTopicLinkTryHere() throws IOException {
		int rowsGraphPageSheet = excelFileReader.getRowCount("GraphPage");
		int totalcols = excelFileReader.getCellCount("GraphPage", 2);       
		System.out.println("The Row is : " + rowsGraphPageSheet + " Columns : " + totalcols);
		String[][] topicData = new String[rowsGraphPageSheet- 1][2];
		for (int i = 2; i < 4; i++) {
			topicData[i - 2][0] = excelFileReader.getCellData("GraphPage", i, 0);
			topicData[i - 2][1] = excelFileReader.getCellData("GraphPage", i, 2);
		}
		return topicData;
	}
	
	@DataProvider(name = "ValidateGraphTopicLinkTryEditorPositive")
	public String[][] getGraphTopicLinkTryEditorPositive() throws IOException {
		int rowsGraphPageSheet = excelFileReader.getRowCount("GraphPage");
		
		int totalcols = excelFileReader.getCellCount("GraphPage", 2);
		
		System.out.println("The Total Row is : " + rowsGraphPageSheet + " Total Columns : " + totalcols);
		
		String[][] topicData = new String[rowsGraphPageSheet - 2][3];
		for (int i = 2; i < rowsGraphPageSheet; i++) {
			topicData[i - 2][0] = excelFileReader.getCellData("GraphPage", i, 0);
			topicData[i - 2][1] = excelFileReader.getCellData("GraphPage", i, 3);
			topicData[i - 2][2] = excelFileReader.getCellData("GraphPage", i, 4);
		}
		return topicData;

	}
	
	@DataProvider(name = "ValidateGraphTopicLinkTryEditorNegative")
	public String[][] getGraphTopicLinkTryEditorNegative() throws IOException {
		int rowsGraphPageSheet = excelFileReader.getRowCount("GraphPage");
		
		int totalcols = excelFileReader.getCellCount("GraphPage", 2);
		
		System.out.println("The Total Row is : " + rowsGraphPageSheet + " Total Columns : " + totalcols);
		
		String[][] topicData = new String[rowsGraphPageSheet - 2][3];
		for (int i = 2; i < rowsGraphPageSheet; i++) {
			topicData[i - 2][0] = excelFileReader.getCellData("GraphPage", i, 0);
			topicData[i - 2][1] = excelFileReader.getCellData("GraphPage", i, 5);
			topicData[i - 2][2] = excelFileReader.getCellData("GraphPage", i, 6);
		}
		return topicData;

	}
	@DataProvider(name = "ValidateGraphPracticeQuestions")
	public String[][] getGraphPracticeQuestions() throws IOException {
		int rowsGraphPageSheet = excelFileReader.getRowCount("GraphPage");
		int totalcols = excelFileReader.getCellCount("GraphPage", 2);
		System.out.println("The Row is :" + rowsGraphPageSheet + "Columns : " + totalcols);
		String[][] topicData = new String[rowsGraphPageSheet - 1][1];
		for (int i = 2; i < 4; i++) {
			topicData[i - 2][0] = excelFileReader.getCellData("GraphPage", i, 0);
						
		}
		return topicData;
	}
	//-------------------Queuepage   -------------------------------------
	
		@DataProvider(name = "ValidateQueueTopicLink")
		public String[][] getQueueTopicLink() throws IOException {
			int rowsQueuePageSheet = excelFileReader.getRowCount("QueuePage");
			int totalcols = excelFileReader.getCellCount("QueuePage", 2);
			System.out.println("The Row is :" + rowsQueuePageSheet  + "Columns : " +totalcols);
			String[][] topicData = new String[rowsQueuePageSheet - 1][2];
			for (int i = 2; i < 6; i++) {
				topicData[i - 2][0] = excelFileReader.getCellData("QueuePage", i, 0);
				topicData[i - 2][1] = excelFileReader.getCellData("QueuePage", i, 1);
			}
			return topicData;
		}
		

		@DataProvider(name = "ValidateQueueTopicLinkTryHere")
		public String[][] getQueueTopicLinkTryHere() throws IOException {
			int rowsQueuePageSheet = excelFileReader.getRowCount("QueuePage");
			int totalcols = excelFileReader.getCellCount("QueuePage", 2);       
			System.out.println("The Row is : " + rowsQueuePageSheet + " Columns : " + totalcols);
			String[][] topicData = new String[rowsQueuePageSheet- 1][2];
			for (int i = 2; i < 6; i++) {
				topicData[i - 2][0] = excelFileReader.getCellData("QueuePage", i, 0);
				topicData[i - 2][1] = excelFileReader.getCellData("QueuePage", i, 2);
			}
			return topicData;
		}
		
		@DataProvider(name = "ValidateQueueTopicLinkTryEditorPositive")
		public String[][] getQueueTopicLinkTryEditor() throws IOException {
			int rowsQueuePageSheet = excelFileReader.getRowCount("QueuePage");
			
			int totalcols = excelFileReader.getCellCount("QueuePage", 2);
			
			System.out.println("The Total Row is : " + rowsQueuePageSheet + " Total Columns : " + totalcols);
			
			String[][] topicData = new String[rowsQueuePageSheet - 2][3];
			for (int i = 2; i < rowsQueuePageSheet; i++) {
				topicData[i - 2][0] = excelFileReader.getCellData("QueuePage", i, 0);
				topicData[i - 2][1] = excelFileReader.getCellData("QueuePage", i, 3);
				topicData[i - 2][2] = excelFileReader.getCellData("QueuePage", i, 4);
			}
			return topicData;
		}
		
		@DataProvider(name = "ValidateQueueTopicLinkTryEditorNegative")
		public String[][] getQueueTopicLinkTryEditorNegative() throws IOException {
			int rowsQueuePageSheet = excelFileReader.getRowCount("QueuePage");
			
			int totalcols = excelFileReader.getCellCount("QueuePage", 2);
			
			System.out.println("The Total Row is : " + rowsQueuePageSheet + " Total Columns : " + totalcols);
			
			String[][] topicData = new String[rowsQueuePageSheet - 2][3];
			for (int i = 2; i < rowsQueuePageSheet; i++) {
				topicData[i - 2][0] = excelFileReader.getCellData("QueuePage", i, 0);
				topicData[i - 2][1] = excelFileReader.getCellData("QueuePage", i, 5);
				topicData[i - 2][2] = excelFileReader.getCellData("QueuePage", i, 6);
			}
			return topicData;

		}
		
		@DataProvider(name = "ValidateQueuePracticeQuestions")
		public String[][] getQueuePracticeQuestions() throws IOException {
			int rowsQueuePageSheet = excelFileReader.getRowCount("QueuePage");
			int totalcols = excelFileReader.getCellCount("QueuePage", 2);
			System.out.println("The Row is :" + rowsQueuePageSheet + "Columns : " + totalcols);
			String[][] topicData = new String[rowsQueuePageSheet - 1][1];
			for (int i = 2; i < 6; i++) {
				topicData[i - 2][0] = excelFileReader.getCellData("QueuePage", i, 0);		
				
			}
			return topicData;
		}
}
