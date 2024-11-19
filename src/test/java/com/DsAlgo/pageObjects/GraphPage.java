package com.DsAlgo.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.DsAlgo.utilities.CommonUtils;

public class GraphPage {
	WebDriver driver ;
	
	CommonUtils utilsObj = CommonUtils.getInstance();
	
	//constructor
		public GraphPage(WebDriver driver) 
		{ 
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}
	
	//locator
	
				//Get_Started_Graph
	
	@FindBy(xpath="//a[@href='graph']")
	WebElement graphStarted;
	
	     //Topic covered Graph
	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Graph']")
	WebElement graphLink;
	
	@FindBy(xpath="//a[normalize-space()='Try here>>>']")
	WebElement graphTryHere;
	
	
	  			//Topic covered Graph Representation
	@FindBy(xpath="//a[normalize-space()='Graph Representations']")
	WebElement graphRepresent;
	

	@FindBy(xpath="//div[@class='CodeMirror-scroll']")
	WebElement graphRepresentEditor;
	

			   //Practice Questions
	@FindBy(xpath="//a[normalize-space()='Practice Questions']")
	WebElement graphPracticeQuestion;
	
	
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
	public void clickGraphStarted() 
	{
		graphStarted.click();	
	}
	
	public void clickGraphTryHereBtn()
	{
		graphTryHere.click();
	}

	public void clickHandleGraphPracticeQuestion() {
		graphPracticeQuestion.click();
	}
	
	public int getPracticeQuestionsCount() {
		return practiceQuestions.size();
	}
	
	public String getActualTitle() {
		return driver.getTitle();
	}
	
	public void clickTopicLink(String topicXpath) {
		switch (topicXpath) {
		
		case "Graph":
			graphLink.click();
			break;

		case "Graph Representations":
			graphRepresent.click();
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