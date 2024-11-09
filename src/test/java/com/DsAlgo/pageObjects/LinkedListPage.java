package com.DsAlgo.pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.DsAlgo.testBase.BaseClass;
import com.DsAlgo.utilities.CommonUtils;


public class LinkedListPage extends BaseClass {
	  WebDriver driver;
	 CommonUtils utilsObj = CommonUtils.getInstance();
    
//		@FindBy (name = "username")
//		WebElement name;
//		
//		@FindBy (name="password")
//		WebElement password;
//		
//		@FindBy(xpath="//input[@type='submit']")
//		WebElement login;
		
		@FindBy(xpath = "//h5[text()='Linked List']/../a")
		WebElement getStartedLinkedListButton;

		@FindBy(partialLinkText = "Introduction")
		WebElement introductionLink;

		@FindBy(partialLinkText = "Creating Linked LIst")
		WebElement creatingLinkedListLink;

		@FindBy(partialLinkText = "Types of Linked List")
		WebElement typesOfLinkedListLink;

		@FindBy(partialLinkText = "Implement Linked List in Python")
		WebElement implementLinkedListLink;

		@FindBy(partialLinkText = "Traversal")
		WebElement traversalLink;

		@FindBy(partialLinkText = "Insertion")
		WebElement insertionLink;

		@FindBy(partialLinkText = "Deletion")
		WebElement deletionLink;

		@FindBy(partialLinkText = "Practice Questions")
		WebElement linkedListPracticeLink;

		@FindAll(value = { @FindBy(className = "list-group") })
		List<WebElement> practiceQuestions;

		@FindBy(partialLinkText = "Try here>>>")
		WebElement tryEditor;
		
		@FindBy(xpath = "//div[contains(@class , 'CodeMirror') and contains(@class,'cm-s-default')]//textarea")
		WebElement inputCode;

		@FindBy(xpath = "//button")
		WebElement runButton;

		@FindBy(xpath = "//*[@id='output']")
		WebElement output;

		@FindBy(id = "answer_form")
		WebElement answerForm;


		public void clickGetStartedBtn() {
			getStartedLinkedListButton.click();
		}
		
		public void clickTopicLink(String topicXpath) {
			switch (topicXpath) {
			case "Linked List":
				getStartedLinkedListButton.click();
				break;
			case "Introduction":
				introductionLink.click();
				break;

			case "Creating Linked LIst":
				creatingLinkedListLink.click();
				break;

			case "Types of Linked List":
				typesOfLinkedListLink.click();
				break;

			case "Implement Linked List in Python":
				implementLinkedListLink.click();	
				break;
			case "Traversal":
				traversalLink.click();
				break;
			case "Insertion":
				insertionLink.click();
				break;
				
            case "Deletion":
				deletionLink.click();
				break;
			
		     default:
				throw new RuntimeException("Please pass the topic name: ");
				
			}
		}

		public void clickIntroductionLink() {
			introductionLink.click();
		}

		public void clickCreatingLinkedListLink() {
			creatingLinkedListLink.click();
		}

		public void clickTypesOfLinkedListLink() {
			typesOfLinkedListLink.click();
		}

		public void clickImplementLinkedListLink() {
			implementLinkedListLink.click();
		}

		public void clickinsertionLink() {
			insertionLink.click();
		}

		public void clicktraversalLink() {
			traversalLink.click();
		}

		public void clickdeletionLink() {
			deletionLink.click();
		}

		public void clicktryEditor() {
			tryEditor.click();
		}

		public void setCodePositive(String code) {
			answerForm.click();
			inputCode.sendKeys(code);
		}

		public void setCodeNegative(String input) {
			answerForm.click();
			inputCode.sendKeys(input);
		}

		public void clickRunBtn() {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.elementToBeClickable(runButton));
			runButton.click();
		}

		public void clearFormText() {
			Actions actions = new Actions(driver);
			actions.moveToElement(inputCode).click().keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL)
					.sendKeys(Keys.BACK_SPACE).perform();
		}

		public String getOutput() {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			WebElement outputElement = wait.until(ExpectedConditions.visibilityOf(output)); // Wait for output visibility
			return outputElement.getText();
		}

		public void clickPracticeLink() {
			linkedListPracticeLink.click();
		}

		public int getPracticeQuestionsCount() {
			return practiceQuestions.size();
		}

		public String getActualTitle() {
			return driver.getTitle();
		}

		public LinkedListPage(WebDriver driver) {
			//this.driver = driver;
			PageFactory.initElements(driver, this);
		}

}
