package com.DsAlgo.utilities;

import java.io.IOException;
import java.util.Arrays;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	
	public static String sheetName = "";

	ExcelFileReader excelFileReader = new ExcelFileReader(ConfigFileReader.getInstance().getExcelPath());

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

//	@DataProvider(name = "ValidLoginDataNoExpectedMsg")
//	public String[][] getValidLoginDataNoExpectedMessage() throws IOException {
//	
//		String[][] validLoginData = new String[1][3];
//		validLoginData[0][0] = xlutil.getCellData("LoginCredentials", 1, 0);
//		validLoginData[0][1] = xlutil.getCellData("LoginCredentials", 1, 1);
//		// validLoginData[0][2]=xlutil.getCellData("LoginCredentials", 1, 2);
//		validLoginData[0][2] = xlutil.getCellData("DataStructuresPage", 1, 0);
//		return validLoginData;
//	}

	@DataProvider(name = "ValidLoginWithHomepageDropdown", indices = { 1, 2, 3, 4, 5, 6 })
	public String[][] getLoginHomeDropdown() throws IOException {

		int rowsHomePageSheet = excelFileReader.getRowCount("HomePage");
		System.out.println("No of Rows :" + rowsHomePageSheet);

		int totalcols = excelFileReader.getCellCount("HomePage", 1);
		System.out.println("Total Columns " + totalcols);

		String[][] loginHomeDropdown = new String[7][totalcols];
		for (int i = 1; i < 8; i++) {

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

	// --------@DataProvider(name = "ValidateTreeTopicLink")

	@DataProvider(name = "ValidateTreeTopicLink")
	public String[][] getTreeTopicLink() throws IOException {
		int rowsTreePageSheet = excelFileReader.getRowCount("TreePage");
		int totalcols = excelFileReader.getCellCount("TreePage", 2);
		System.out.println("The Row is :" + rowsTreePageSheet + "Columns : " + totalcols);
		String[][] topicData = new String[rowsTreePageSheet - 1][2];
		for (int i = 2; i < 15; i++) {
			topicData[i - 2][0] = excelFileReader.getCellData("TreePage", i, 0);
			topicData[i - 2][1] = excelFileReader.getCellData("TreePage", i, 1);
			System.out.print("The data is " + topicData[i - 2][0] + ",");
			System.out.println("The data is " + topicData[i - 2][1] + ",");
		}
		return topicData;
	}

	@DataProvider(name = "ValidateTreeTopicLinkTryHere")
	public String[][] getTreeTopicLinkTryHere() throws IOException {
		int rowsTreePageSheet = excelFileReader.getRowCount("TreePage");
		// int rowsTryEditorSheet=excelFileReader.getRowCount("TryEditor");
		int totalcols = excelFileReader.getCellCount("TreePage", 2); // WE can skip Lip
		System.out.println("The Row is : " + rowsTreePageSheet + " Columns : " + totalcols);
		String[][] topicData = new String[rowsTreePageSheet - 1][2];
		for (int i = 2; i < 15; i++) {
			topicData[i - 2][0] = excelFileReader.getCellData("TreePage", i, 0);
			topicData[i - 2][1] = excelFileReader.getCellData("TreePage", i, 2);
			System.out.print("The data is " + topicData[i - 2][0] + ",");
			System.out.println("The data is " + topicData[i - 2][1] + ",");
		}
		return topicData;
	}

	@DataProvider(name = "TreeTopicLinkTryEditorValidCode")
	public String[][] getTreeTopicLinkTryEditorPositive() throws IOException {
		int rowsTreePageSheet = excelFileReader.getRowCount("TreePage");

//		int totalcols = excelFileReader.getCellCount("TreePage", 2);
//
//		System.out.println("The Total Row is : " + rowsTreePageSheet + " Total Columns : " + totalcols);

		String[][] validCode = new String[rowsTreePageSheet - 2][3];// [15 rows -2]
		for (int i = 2; i < rowsTreePageSheet; i++) {
			validCode[i - 2][0] = excelFileReader.getCellData("TreePage", i, 0);
			validCode[i - 2][1] = excelFileReader.getCellData("TreePage", i, 3);
			validCode[i - 2][2] = excelFileReader.getCellData("TreePage", i, 4);
//			System.out.println("The data is " + topicData[i - 2][0] + ",");
//			System.out.println("The data is " + topicData[i - 2][1] + ",");
//			System.out.println("The data is " + topicData[i - 2][2] + ",");

		}
		return validCode;
	}

	@DataProvider(name = "TreeTopicLinkTryEditorInvalidCode")
	public String[][] getTreeTopicLinkTryEditorNegative() throws IOException {
		int rowsTreePageSheet = excelFileReader.getRowCount("TreePage");

//		int totalcols = excelFileReader.getCellCount("TreePage", 2);
//
//		System.out.println("The Total Row is : " + rowsTreePageSheet + " Total Columns : " + totalcols);

		String[][] inValidCode = new String[rowsTreePageSheet - 2][3];// [15 rows -2]
		for (int i = 2; i < rowsTreePageSheet; i++) {
			inValidCode[i - 2][0] = excelFileReader.getCellData("TreePage", i, 0);
			inValidCode[i - 2][1] = excelFileReader.getCellData("TreePage", i, 7);
			inValidCode[i - 2][2] = excelFileReader.getCellData("TreePage", i, 8);
//			System.out.println("The data is " + inValidCode[i - 2][0] + ",");
//			System.out.println("The data is " + inValidCode[i - 2][1] + ",");
//			System.out.println("The data is " + topicData[i - 2][2] + ",");

		}
		return inValidCode;
	}

	@DataProvider(name = "ValidateTreePracticeQuestions")
	public String[][] getTreePracticeQuestions() throws IOException {
		int rowsTreePageSheet = excelFileReader.getRowCount("TreePage");
		int totalcols = excelFileReader.getCellCount("TreePage", 2);
		System.out.println("The Row is :" + rowsTreePageSheet + "Columns : " + totalcols);
		String[][] topicData = new String[rowsTreePageSheet - 1][1];
		for (int i = 2; i < 15; i++) {
			topicData[i - 2][0] = excelFileReader.getCellData("TreePage", i, 0);
			// topicData[i - 2][1] = excelFileReader.getCellData("TreePage", i, 1);
			System.out.print("The data is " + topicData[i - 2][0] + ",");
			// System.out.println("The data is " + topicData[i - 2][1] + ",");
		}
		return topicData;
	}

	// ---------------------@DataProvider for DataStructure Module Page----------
	@DataProvider(name = "DataTryEditorValidCode")
	public String[][] getDataTryEditorPositive() throws IOException {
		int rowsDataPageSheet = excelFileReader.getRowCount("DataStructurePage");

		int totalcols = excelFileReader.getCellCount("DataStructurePage", 1);

		System.out.println("The Total Row is : " + rowsDataPageSheet + " Total Columns : " + totalcols);

		String[][] validCode = new String[1][2];
		for (int i = 1; i < 2; i++) {
			validCode[i - 1][0] = excelFileReader.getCellData("DataStructurePage", i, 2);
			validCode[i - 1][1] = excelFileReader.getCellData("DataStructurePage", i, 3);

			System.out.println("The data is " + validCode[i - 1][0] + ",");
			System.out.println("The data is " + validCode[i - 1][1] + ",");

		}
		return validCode;
	}

	@DataProvider(name = "DataTryEditorInvalidCode")
	public String[][] getDataTryEditorNegative() throws IOException {
		int rowsDataPageSheet = excelFileReader.getRowCount("DataStructurePage");

		int totalcols = excelFileReader.getCellCount("DataStructurePage", 1);

		System.out.println("The Total Row is : " + rowsDataPageSheet + " Total Columns : " + totalcols);

		String[][] inValidCode = new String[1][2];
		for (int i = 1; i < 2; i++) {
			inValidCode[i - 1][0] = excelFileReader.getCellData("DataStructurePage", i, 6);
			inValidCode[i - 1][1] = excelFileReader.getCellData("DataStructurePage", i, 7);

			System.out.println("The data is " + inValidCode[i - 1][0] + ",");
			System.out.println("The data is " + inValidCode[i - 1][1] + ",");

		}
		return inValidCode;
	}
	
	// ---------------------@DataProvider for Linked List Module Page----------

	@DataProvider(name = "ValidateLinkedListTopicLink")
	public String[][] getLinkedListTopicLink() throws IOException {
		return getTopicLinks("LinkedList");
	}

	private String[][] getTopicLinks(String sheetName) throws IOException {
		int rowsLinkedListPageSheet = excelFileReader.getRowCount("LinkedListPage");
		int totalcols = excelFileReader.getCellCount("LinkedListPage", 2);
		System.out.println("The Row is :" + rowsLinkedListPageSheet + "Columns : " + totalcols);
		String[][] topicData = new String[rowsLinkedListPageSheet - 1][2];
		for (int i = 2; i <= rowsLinkedListPageSheet; i++) {
			topicData[i - 2][0] = excelFileReader.getCellData("LinkedListPage", i, 0);
			topicData[i - 2][1] = excelFileReader.getCellData("LinkedListPage", i, 1);
			//System.out.print("The data is " + topicData[i - 2][0] + ",");
			//System.out.println("The data is " + topicData[i - 2][1] + ",");
		}
		return topicData;
	}

	@DataProvider(name = "ValidateLinkedListTopicLinkTryHere")
	public String[][] getLinkedListTopicLinkTryHere() throws IOException {
		int rowsLinkedListPageSheet = excelFileReader.getRowCount("LinkedListPage");
		int totalcols = excelFileReader.getCellCount("LinkedListPage", 2); // WE can skip Lip
		System.out.println("The Row is : " + rowsLinkedListPageSheet + " Columns : " + totalcols);
		String[][] topicData = new String[rowsLinkedListPageSheet - 1][2];
		for (int i = 2; i <= rowsLinkedListPageSheet; i++) {
			topicData[i - 2][0] = excelFileReader.getCellData("LinkedListPage", i, 0);
			topicData[i - 2][1] = excelFileReader.getCellData("LinkedListPage", i, 2);
			//System.out.print("The data is " + topicData[i - 2][0] + ",");
			//System.out.println("The data is " + topicData[i - 2][1] + ",");
		}
		return topicData;
	}

	@DataProvider(name = "LinkedListTopicLinkTryEditorValidCode")
	public String[][] getLinkedListTopicLinkTryEditorPositive() throws IOException {
		int rowsLinkedListPageSheet = excelFileReader.getRowCount("LinkedListPage");

		String[][] validCode = new String[rowsLinkedListPageSheet - 1][3];// 
		for (int i = 2; i <= rowsLinkedListPageSheet; i++) {
			validCode[i - 2][0] = excelFileReader.getCellData("LinkedListPage", i, 0);
			validCode[i - 2][1] = excelFileReader.getCellData("LinkedListPage", i, 3);
			validCode[i - 2][2] = excelFileReader.getCellData("LinkedListPage", i, 4);

		}
		return validCode;
	}

	@DataProvider(name = "LinkedListTopicLinkTryEditorInvalidCode")
	public String[][] getLinkedListTopicLinkTryEditorNegative() throws IOException {
		int rowsStackPageSheet = excelFileReader.getRowCount("LinkedListPage");

		String[][] inValidCode = new String[rowsStackPageSheet - 1][3];// [15 rows -2]
		for (int i = 2; i <= rowsStackPageSheet; i++) {
			inValidCode[i - 2][0] = excelFileReader.getCellData("LinkedListPage", i, 0);
			inValidCode[i - 2][1] = excelFileReader.getCellData("LinkedListPage", i, 7);
			inValidCode[i - 2][2] = excelFileReader.getCellData("LinkedListPage", i, 8);


		}
		return inValidCode;
	}

	@DataProvider(name = "ValidateLinkedListPracticeQuestions")
	public String[][] getLinkedListPracticeQuestions() throws IOException {
		int rowsLinkedListPageSheet = excelFileReader.getRowCount("LinkedListPage");
		int totalcols = excelFileReader.getCellCount("LinkedListPage", 2);
		System.out.println("The Row is :" + rowsLinkedListPageSheet + "Columns : " + totalcols);
		String[][] topicData = new String[rowsLinkedListPageSheet - 1][1];
		for (int i = 2; i <= rowsLinkedListPageSheet; i++) {
			topicData[i - 2][0] = excelFileReader.getCellData("LinkedListPage", i, 0);
			System.out.print("The data is " + topicData[i - 2][0] + ",");
			
		}
		return topicData;

	}
	
	// ---------------------@DataProvider for Stack Module Page----------

	@DataProvider(name = "ValidateStackTopicLink")
	public String[][] getLStackTopicLink() throws IOException {
		int rowsLinkedListPageSheet = excelFileReader.getRowCount("StackPage");
		int totalcols = excelFileReader.getCellCount("StackPage", 2);
		System.out.println("The Row is :" + rowsLinkedListPageSheet + "Columns : " + totalcols);
		String[][] topicData = new String[rowsLinkedListPageSheet - 1][2];
		for (int i = 2; i <= rowsLinkedListPageSheet; i++) {
			topicData[i - 2][0] = excelFileReader.getCellData("StackPage", i, 0);
			topicData[i - 2][1] = excelFileReader.getCellData("StackPage", i, 1);
			//System.out.print("The data is " + topicData[i - 2][0] + ",");
			//System.out.println("The data is " + topicData[i - 2][1] + ",");
		}
		return topicData;
	}

	@DataProvider(name = "ValidateStackTopicLinkTryHere")
	public String[][] getStackTopicLinkTryHere() throws IOException {
		int rowsLinkedListPageSheet = excelFileReader.getRowCount("StackPage");
		int totalcols = excelFileReader.getCellCount("StackPage", 2); // WE can skip Lip
		System.out.println("The Row is : " + rowsLinkedListPageSheet + " Columns : " + totalcols);
		String[][] topicData = new String[rowsLinkedListPageSheet - 1][2];
		for (int i = 2; i <= rowsLinkedListPageSheet; i++) {
			topicData[i - 2][0] = excelFileReader.getCellData("StackPage", i, 0);
			topicData[i - 2][1] = excelFileReader.getCellData("StackPage", i, 2);
			//System.out.print("The data is " + topicData[i - 2][0] + ",");
			//System.out.println("The data is " + topicData[i - 2][1] + ",");
		}
		return topicData;
	}

	@DataProvider(name = "StackTopicLinkTryEditorValidCode")
	public String[][] getStackTopicLinkTryEditorPositive() throws IOException {
		int rowsStackPageSheet = excelFileReader.getRowCount("StackPage");

		String[][] validCode = new String[rowsStackPageSheet - 1][3];// [15 rows -2]
		for (int i = 2; i <= rowsStackPageSheet; i++) {
			validCode[i - 2][0] = excelFileReader.getCellData("StackPage", i, 0);
			validCode[i - 2][1] = excelFileReader.getCellData("StackPage", i, 3);
			validCode[i - 2][2] = excelFileReader.getCellData("StackPage", i, 4);

		}
		return validCode;
	}

	@DataProvider(name = "StackTopicLinkTryEditorInvalidCode")
	public String[][] getStackTopicLinkTryEditorNegative() throws IOException {
		int rowsStackPageSheet = excelFileReader.getRowCount("StackPage");

		String[][] inValidCode = new String[rowsStackPageSheet - 1][3];// [15 rows -2]
		for (int i = 2; i <= rowsStackPageSheet; i++) {
			inValidCode[i - 2][0] = excelFileReader.getCellData("StackPage", i, 0);
			inValidCode[i - 2][1] = excelFileReader.getCellData("StackPage", i, 7);
			inValidCode[i - 2][2] = excelFileReader.getCellData("StackPage", i, 8);


		}
		return inValidCode;
	}

	@DataProvider(name = "ValidateStackPracticeQuestions")
	public String[][] getStackPracticeQuestions() throws IOException {
		int rowsStackPageSheet = excelFileReader.getRowCount("StackPage");
		int totalcols = excelFileReader.getCellCount("StackPage", 2);
		System.out.println("The Row is :" + rowsStackPageSheet + "Columns : " + totalcols);
		String[][] topicData = new String[rowsStackPageSheet - 1][1];
		for (int i = 2; i <= rowsStackPageSheet; i++) {
			topicData[i - 2][0] = excelFileReader.getCellData("StackPage", i, 0);
			System.out.print("The data is " + topicData[i - 2][0] + ",");
			
		}
		return topicData;
	}
	
	

}
