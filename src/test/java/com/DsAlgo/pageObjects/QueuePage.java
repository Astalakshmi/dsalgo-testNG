package com.DsAlgo.pageObjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.DsAlgo.utilities.CommonUtils;


public class QueuePage {
	
	WebDriver driver ;
	CommonUtils utilsObj = CommonUtils.getInstance();
	   
			//constructor
	public QueuePage (WebDriver driver) {
	
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	   //locator
	
				//Get_Started_Queue
	@FindBy(xpath="//a[@href='queue']")
	WebElement queueStarted;
	
				//Topic covered queueInPython
	@FindBy(xpath="//a[normalize-space()='Implementation of Queue in Python']")
	WebElement queueInPython;
	
	@FindBy(linkText="Try here>>>")
	WebElement queueInPythonTryHere;

	
	             //Topic covered collection.deque
	@FindBy(xpath="//a[normalize-space()='Implementation using collections.deque']")
	WebElement deque;
	
					//Topic covered collection in Array
	@FindBy(xpath="//a[normalize-space()='Implementation using array']")
	WebElement queueImplArray;

			//Topic covered Queue Operation
	@FindBy(xpath="//a[normalize-space()='Queue Operations']")
	WebElement queueOperation;

				//Practice Question
	@FindBy(xpath="//a[normalize-space()='Practice Questions']")
	WebElement  queuePracticeQuestion;
	
	@FindAll (value = { @FindBy (className = "list-group") })
	List<WebElement> practiceQuestions;
	
	@FindBy(id = "answer_form")
	WebElement answerForm;
	
	@FindBy(xpath = "//div[contains(@class , 'CodeMirror') and contains(@class,'cm-s-default')]//textarea")
	WebElement inputCode;
	
	@FindBy(xpath = "//button[@onclick='runit()']")
	WebElement runButton;
	
	@FindBy(xpath = "//*[@id='output']")
	WebElement output;
	
	//Action Methods
	
	public void clickQueueStarted()
	{
		 queueStarted.click();
	}

	public void clickQueueInPythonTryHere()
	{
		queueInPythonTryHere.click();
	}
	
	public void handlePracticeQuestionClick() {
 
		queuePracticeQuestion.click();
	
	}
	public int getPracticeQuestionsCount() {
		return practiceQuestions.size();
	}
	
	public String getActualTitle() {
		return driver.getTitle();
	}

	public void clickTopicLink(String topicXpath) {
		switch (topicXpath) {
		
		case "QueueInPython":
			queueInPython.click();
			break;

		case "CollectionsDequeue":
			deque.click();
			break;
			
		case "ImplementationUsingArray":
			queueImplArray.click();
			break;
			
		case "QueueOperations":
			queueOperation.click();
			break;

		  default:
				throw new RuntimeException("Please pass the topic name: ");
				
		   }
		}
	public void setCodePositive(String code) {
		answerForm.click();
		inputCode.sendKeys(code);
	}
	
	public void clickRunBtn() {
	
		utilsObj.visibilityOf(runButton);
		runButton.click();
	}

	public String getOutput() {
		
		utilsObj.visibilityOfMoreWaitTime(output);
		return output.getText();
	}
}