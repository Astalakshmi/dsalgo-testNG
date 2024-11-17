package com.DsAlgo.pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.DsAlgo.utilities.CommonUtils;

public class TreePage {
	
	 WebDriver driver;
	    
	    CommonUtils utilsObj = CommonUtils.getInstance();
					//constructor
		
	@FindBy(xpath = "//h5[text()='Tree']/../a[text()='Get Started']")
	WebElement getStarted;

	@FindBy(xpath = "//h4[@class='bg-secondary text-white']/../ul/a[text()='Overview of Trees']")
	WebElement overviewTrees;

	@FindBy(xpath = "//div[@class='container']/div/form/div/div/div/textarea")
	WebElement editorText;

	@FindBy(xpath = "//a[text()='Try here>>>']")
	WebElement tryHere;

	@FindBy(xpath = "//button[text()='Run']")
	WebElement run;

	@FindBy(xpath = "//a[text()='Terminologies']")
	WebElement terminologies;

	@FindBy(xpath = "//a[text()='Types of Trees']")
	WebElement typesOfTrees;

	@FindBy(xpath = "//a[text()='Tree Traversals']")
	WebElement treeTraversals;

	@FindBy(xpath = "//a[text()='Traversals-Illustration']")
	WebElement traversalsIllustrations;

	@FindBy(xpath = "//a[text()='Binary Trees']")
	WebElement binaryTrees;

	@FindBy(xpath = "//a[text()='Types of Binary Trees']")
	WebElement typesOfBinaryTrees;

	@FindBy(xpath = "//a[text()='Implementation in Python']")
	WebElement impInpython;

	@FindBy(xpath = "//a[contains(text(),'Binary Tree Traversals')]")
	WebElement binTreestraversal;

	@FindBy(xpath = "//a[text()='Implementation of Binary Trees']")
	WebElement impBinaryTrees;

	@FindBy(xpath = "//a[text()='Applications of Binary trees']")
	WebElement appBinTree;

	@FindBy(xpath = "//a[text()='Binary Search Trees']")
	WebElement binarySearchTrees;

	@FindBy(xpath = "//a[text()='Implementation Of BST']")
	WebElement impBst;

	@FindBy(xpath = "//div[@align='left']/pre[@id='output']")
	WebElement runOutputvalue;
	
	@FindBy (partialLinkText = "Practice Questions")
	WebElement treePracticeLink;
	
	@FindAll (value = { @FindBy (className = "list-group") })
	List<WebElement> practiceQuestions;
	 
	@FindBy(id = "answer_form")
	WebElement answerForm;  
	
	@FindBy(xpath = "//div[contains(@class , 'CodeMirror') and contains(@class,'cm-s-default')]//textarea")
	WebElement inputCode;
	
	@FindBy(xpath = "//button[@onclick='runit()']")
	WebElement runButton;
	
	@FindBy(xpath = "//*[@id='output']")
	WebElement afterRunOutput;

	public void getStartedclick() {
		getStarted.click();
	}

	public void overviewTreesclick() {
		WebDriverWait w1 = new WebDriverWait(driver, Duration.ofSeconds(10));
		w1.until(ExpectedConditions.elementToBeClickable(overviewTrees)).click();
	}

	public void editorText(String inputCode) {
		editorText.sendKeys(inputCode);
	}

	public void tryHereclick() {
		tryHere.click();
	}

	public void runClick() {
		run.click();
	}

	public void terminologiesClick() {
		terminologies.click();
	}

	public void typesOfTreesclick() {
		typesOfTrees.click();
	}

	public void treeTraversalsclick() {
		treeTraversals.click();
	}

	public void traIllustrationsclick() {
		traversalsIllustrations.click();
	}

	public void binTreesclick() {
		binaryTrees.click();
	}

	public void typesofBintreesclick() {
		typesOfBinaryTrees.click();
	}

	public void impInpythonclick() {
		impInpython.click();
	}

	public void bintreesTraversalclick() {
		binTreestraversal.click();
	}

	public void impBintreesclick() {
		impBinaryTrees.click();
	}

	public void appBintreeclick() {
		appBinTree.click();
	}

	public void binarySearchtreesclick() {
		binarySearchTrees.click();
	}

	public void impBstclick() {
		impBst.click();
	}

	public void practiceQuestionslink() {
		treePracticeLink.click();
	}

	public void backward() {
		driver.navigate().back();
	}

	public int getPracticeQuestionsCount() {
		return practiceQuestions.size();
	}

	public String getActualTitle() {
		return driver.getTitle();
	}
	
	
	public void clickTopicLink(String topicXpath) {
		switch (topicXpath) {
		
		case "OverviewOfTree":
			overviewTrees.click();
			break;

		case "Terminologies":
			terminologies.click();
			break;

		case "TypesOfTrees":
			typesOfTrees.click();
			break;

		case "TreeTraversals":
			treeTraversals.click();	
			break;
		case "Traversals-Illustration":
			traversalsIllustrations.click();
			break;
		case "BinaryTrees":
			binaryTrees.click();
			break;
		case "TypesOfBinaryTrees":
			typesOfBinaryTrees.click();
			break;	
			
			
        case "ImplementationPython":
        	impInpython.click();
			break;
			
			
        case "BinaryTreeTraversals":
        	binTreestraversal.click();
			break;
			
        case "ImplementationBinaryTrees":
        	impBinaryTrees.click();
			break;
			
        case "ApplicationsofBinaryTrees":
        	appBinTree.click();
			break;
        case "BinarySearchTrees":
        	binarySearchTrees.click();
			break;
			
        case "ImplementationOfBST":
        	impBst.click();
			break;
	     default:
			throw new RuntimeException("Please pass the topic name: ");
			
		}
	}
	
	public void setCodePositive(String code) {
		answerForm.click();
		inputCode.sendKeys(code);
	}

	public void setCodeNegative(String code) {
		answerForm.click();
		inputCode.sendKeys(code);
	}

	public void clickRunBtn() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(runButton));
		runButton.click();
	}

//	public void clearFormText() {
//		Actions actions = new Actions(driver);
//		actions.moveToElement(inputCode).click().keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL)
//				.sendKeys(Keys.BACK_SPACE).perform();
//	}

	public String getOutput() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement outputElement = wait.until(ExpectedConditions.visibilityOf(afterRunOutput)); // Wait for output visibility
		return outputElement.getText();
	}


	public TreePage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
