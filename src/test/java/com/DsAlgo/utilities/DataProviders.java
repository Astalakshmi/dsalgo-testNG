package com.DsAlgo.utilities;

import java.io.IOException;
import org.testng.annotations.DataProvider;

public class DataProviders {

	ExcelFileReader excelFileReader = new ExcelFileReader(ConfigFileReader.getInstance().getExcelPath());

	// ------------------Register Page -------------------------------

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
		int totalcols = excelFileReader.getCellCount("RegisterPage", 1);
		String[][] registerInvalidData = new String[8][totalcols];
		for (int i = 2; i <= 9; i++) { // i=2 because row 1 i.e,i=1 is positive testcase. It's worked.
			for (int j = 0; j < totalcols; j++) {
				registerInvalidData[i - 2][j] = excelFileReader.getCellData("RegisterPage", i, j);// [i-2][j] so that
																									// [0][0]
			}
		}
		return registerInvalidData;
	}

	// ---------------------Login Page--------------------------------------------

	@DataProvider(name = "ValidLoginData")
	public String[][] getValidLoginData() throws IOException {
		int totalrows = excelFileReader.getRowCount("LoginCredentials");
		int totalcols = excelFileReader.getCellCount("LoginCredentials", 1);
		System.out.println("The Total Rows : " + totalrows);
		System.out.println("The Total Coulmns : " + totalcols);
		String[][] validLoginData = new String[1][3];
		validLoginData[0][0] = excelFileReader.getCellData("LoginCredentials", 1, 0);
		validLoginData[0][1] = excelFileReader.getCellData("LoginCredentials", 1, 1);
		validLoginData[0][2] = excelFileReader.getCellData("LoginCredentials", 1, 2);
		return validLoginData;
	}

	@DataProvider(name = "InValidLoginData")
	public String[][] getInValidLoginData() throws IOException {
		int totalrows = excelFileReader.getRowCount("LoginCredentials");
		int totalcols = excelFileReader.getCellCount("LoginCredentials", 1);
		System.out.println("Total Rows = " + totalrows + ", Total Columns = " + totalcols);
		String[][] invalidLoginData = new String[totalrows - 4][3];
		System.out.println("Invalid Login data " + invalidLoginData);
		for (int i = 2; i < 7; i++) {
			for (int j = 0; j < 3; j++) {
				invalidLoginData[i - 2][j] = excelFileReader.getCellData("LoginCredentials", i, j);
				System.out.println(invalidLoginData[i - 2][j]);
			}
		}
		return invalidLoginData;
	}

	// ----------------Home Page------------------------------------

	@DataProvider(name = "ValidLoginWithHomepageDropdown", indices = { 1, 2, 3, 4, 5, 6, })
	public String[][] getLoginHomeDropdown() throws IOException {
		int totalcols = excelFileReader.getCellCount("HomePage", 1);
		String[][] loginHomeDropdown = new String[7][totalcols];
		for (int i = 2; i < 8; i++) {
			for (int j = 0; j < 2; j++) {
				loginHomeDropdown[i - 1][0] = excelFileReader.getCellData("HomePage", i, 0); // HomePage,Row- 1,Column-0
				loginHomeDropdown[i - 1][1] = excelFileReader.getCellData("HomePage", i, 1); // HomePage,Row- 1,Column-1
			}
		}
		return loginHomeDropdown;
	}

	@DataProvider(name = "ValidHomepageGetStarted")
	public String[][] getHomeGetStarted() throws IOException {
		int totalcols = excelFileReader.getCellCount("HomePage", 1);
		System.out.println("The total columns " + totalcols);
		String[][] homeGetStarted = new String[7][2];
		for (int i = 1; i < 8; i++) {
			for (int j = 0; j < totalcols; j++) {
				homeGetStarted[i - 1][0] = excelFileReader.getCellData("HomePage", i, 0);
				homeGetStarted[i - 1][1] = excelFileReader.getCellData("HomePage", i, 1);
			}
		}
		return homeGetStarted;
	}

	@DataProvider(name = "ValidLoginDataAccountHolderName")
	public String[] getValidLoginDataAccountHolderName() throws IOException {
		String[] validLoginDataAccountHolder = new String[1];
		validLoginDataAccountHolder[0] = excelFileReader.getCellData("HomePage", 9, 0);
		System.out.println(validLoginDataAccountHolder);
		return validLoginDataAccountHolder;
	}

	// --------------------- DataStructure Module Page---------------------------

	@DataProvider(name = "DataTryEditorValidCode")
	public String[][] getDataTryEditorPositive() throws IOException {
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
		String[][] inValidCode = new String[1][2];
		for (int i = 1; i < 2; i++) {
			inValidCode[i - 1][0] = excelFileReader.getCellData("DataStructurePage", i, 4);
			inValidCode[i - 1][1] = excelFileReader.getCellData("DataStructurePage", i, 5);
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
			inValidCode[i - 2][1] = excelFileReader.getCellData("LinkedListPage", i, 5);
			inValidCode[i - 2][2] = excelFileReader.getCellData("LinkedListPage", i, 6);
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
			inValidCode[i - 2][1] = excelFileReader.getCellData("StackPage", i, 5);
			inValidCode[i - 2][2] = excelFileReader.getCellData("StackPage", i, 6);
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

	// ---------------------------Queue Page----------------------------------------

	@DataProvider(name = "ValidateQueueTopicLink")
	public String[][] getQueueTopicLink() throws IOException {
		int rowsQueuePageSheet = excelFileReader.getRowCount("QueuePage");
		int totalcols = excelFileReader.getCellCount("QueuePage", 2);
		System.out.println("The Row is :" + rowsQueuePageSheet + "Columns : " + totalcols);
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
		String[][] topicData = new String[rowsQueuePageSheet - 1][2];
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

	// -----------------------------Tree Page---------------------------------------

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
		int totalcols = excelFileReader.getCellCount("TreePage", 2);
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
		String[][] validCode = new String[rowsTreePageSheet - 2][3];
		for (int i = 2; i < rowsTreePageSheet; i++) {
			validCode[i - 2][0] = excelFileReader.getCellData("TreePage", i, 0);
			validCode[i - 2][1] = excelFileReader.getCellData("TreePage", i, 3);
			validCode[i - 2][2] = excelFileReader.getCellData("TreePage", i, 4);
		}
		return validCode;
	}

	@DataProvider(name = "TreeTopicLinkTryEditorInvalidCode")
	public String[][] getTreeTopicLinkTryEditorNegative() throws IOException {
		int rowsTreePageSheet = excelFileReader.getRowCount("TreePage");
		String[][] inValidCode = new String[rowsTreePageSheet - 2][3];
		for (int i = 2; i < rowsTreePageSheet; i++) {
			inValidCode[i - 2][0] = excelFileReader.getCellData("TreePage", i, 0);
			inValidCode[i - 2][1] = excelFileReader.getCellData("TreePage", i, 5);
			inValidCode[i - 2][2] = excelFileReader.getCellData("TreePage", i, 6);
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
		}
		return topicData;
	}

	// -------------------Graph Page ---------------------------------------------

	@DataProvider(name = "ValidateGraphTopicLink")
	public String[][] getGraphTopicLink() throws IOException {
		int rowsGraphPageSheet = excelFileReader.getRowCount("GraphPage");
		int totalcols = excelFileReader.getCellCount("GraphPage", 2);
		System.out.println("The Row is :" + rowsGraphPageSheet + "Columns : " + totalcols);
		String[][] topicData = new String[rowsGraphPageSheet - 1][2];
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
		String[][] topicData = new String[rowsGraphPageSheet - 1][2];
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

//	 ---------------------@DataProvider for Array Module Page----------

	@DataProvider(name = "ValidateArrayTopicLink")
	public String[][] getArrayTopicLink() throws IOException {
		int rowsArrayPageSheet = excelFileReader.getRowCount("ArrayPage");
		int totalcols = excelFileReader.getCellCount("ArrayPage", 2);
		System.out.println("The Row is :" + rowsArrayPageSheet + "Columns : " + totalcols);
		String[][] topicData = new String[rowsArrayPageSheet - 1][2];
		for (int i = 2; i <= 5; i++) {
			topicData[i - 2][0] = excelFileReader.getCellData("ArrayPage", i, 0);
			topicData[i - 2][1] = excelFileReader.getCellData("ArrayPage", i, 1);
			System.out.print("The data is " + topicData[i - 2][0] + ",");
			System.out.println("The data is " + topicData[i - 2][1] + ",");
		}
		return topicData;
	}

	@DataProvider(name = "ValidateArrayTopicLinkTryHere")
	public String[][] getArrayTopicLinkTryHere() throws IOException {
		int rowsArrayPageSheet = excelFileReader.getRowCount("ArrayPage");
		int totalcols = excelFileReader.getCellCount("ArrayPage", 2); // WE can skip Lip
		System.out.println("The Row is : " + rowsArrayPageSheet + " Columns : " + totalcols);
		String[][] topicData = new String[rowsArrayPageSheet - 1][2];
		for (int i = 2; i <= 5; i++) {
			topicData[i - 2][0] = excelFileReader.getCellData("ArrayPage", i, 0);
			topicData[i - 2][1] = excelFileReader.getCellData("ArrayPage", i, 2);
			System.out.print("The data is " + topicData[i - 2][0] + ",");
			System.out.println("The data is " + topicData[i - 2][1] + ",");
		}
		return topicData;
	}

	@DataProvider(name = "ArrayTopicLinkTryEditorValidCode")
	public String[][] getArrayTopicLinkTryEditorPositive() throws IOException {
		int rowsArrayPageSheet = excelFileReader.getRowCount("ArrayPage");

		String[][] validCode = new String[rowsArrayPageSheet - 1][3];// [15 rows -2]
		System.out.println("rowsArrayPageSheet==" + rowsArrayPageSheet);
		for (int i = 2; i <= rowsArrayPageSheet; i++) {
			validCode[i - 2][0] = excelFileReader.getCellData("ArrayPage", i, 0);
			validCode[i - 2][1] = excelFileReader.getCellData("ArrayPage", i, 3);
			validCode[i - 2][2] = excelFileReader.getCellData("ArrayPage", i, 4);
			System.out.println("The data is " + validCode[i - 2][0] + ",");
			System.out.println("The data is " + validCode[i - 2][1] + ",");
			System.out.println("The data is " + validCode[i - 2][2] + ",");
		}
		return validCode;
	}

	@DataProvider(name = "ArrayTopicLinkTryEditorInvalidCode")
	public String[][] getArrayTopicLinkTryEditorNegative() throws IOException {
		int rowsArrayPageSheet = excelFileReader.getRowCount("ArrayPage");
		String[][] inValidCode = new String[rowsArrayPageSheet - 1][3];// [15 rows -2]
		for (int i = 2; i <= rowsArrayPageSheet; i++) {
			inValidCode[i - 2][0] = excelFileReader.getCellData("ArrayPage", i, 0);
			inValidCode[i - 2][1] = excelFileReader.getCellData("ArrayPage", i, 5);
			inValidCode[i - 2][2] = excelFileReader.getCellData("ArrayPage", i, 6);
			System.out.println("The data is " + inValidCode[i - 2][0] + ",");
			System.out.println("The data is " + inValidCode[i - 2][1] + ",");
			System.out.println("The data is " + inValidCode[i - 2][2] + ",");
		}
		return inValidCode;
	}

	@DataProvider(name = "ValidateArrayPracticeQuestionsRun")
	public String[][] getArrayPracticeQuestionsRun() throws IOException {
		int rowsForQuestionRun = 8;
		String[][] practiceQuestions = new String[rowsForQuestionRun][3];
		for (int i = 1; i <= rowsForQuestionRun; i++) {
			practiceQuestions[i - 1][0] = excelFileReader.getCellData("PracticeQns", i, 0);
			practiceQuestions[i - 1][1] = excelFileReader.getCellData("PracticeQns", i, 1);
			practiceQuestions[i - 1][2] = excelFileReader.getCellData("PracticeQns", i, 2);
		}
		return practiceQuestions;
	}

	@DataProvider(name = "ValidateArrayPracticeQuestionsSubmit")
	public String[][] getArrayPracticeQuestionsSubmit() throws IOException {
		int rowsForQuestionSubmit = 4;
		String[][] practiceQuestions = new String[rowsForQuestionSubmit][3];
		for (int i = 9; i <= 12; i++) {
			practiceQuestions[i - 9][0] = excelFileReader.getCellData("PracticeQns", i, 0);
			practiceQuestions[i - 9][1] = excelFileReader.getCellData("PracticeQns", i, 1);
			practiceQuestions[i - 9][2] = excelFileReader.getCellData("PracticeQns", i, 2);
		}
		return practiceQuestions;
	}
}
