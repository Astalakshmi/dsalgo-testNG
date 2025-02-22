package com.DsAlgo.pageObjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.DsAlgo.utilities.CommonUtils;

public class DataStructurePage {

	WebDriver driver;

	CommonUtils utilsObj = CommonUtils.getInstance();

	@FindBy(xpath = "//h5[text()='Data Structures-Introduction']/../a[text()='Get Started']")
	WebElement getstarted;

	@FindBy(xpath = "//a[contains(text(),'Time Complexity')]")
	WebElement timecomplex;

	@FindBy(xpath = "//a[contains(text(),'Try here>>>')]")
	WebElement tryhere;

	@FindBy(id = "answer_form")
	WebElement answerForm;

	@FindBy(xpath = "//div[contains(@class , 'CodeMirror') and contains(@class,'cm-s-default')]//textarea")
	WebElement inputCode;

	@FindBy(xpath = "//button[@onclick='runit()']")
	WebElement runButton;

	@FindBy(xpath = "//*[@id='output']")
	WebElement afterRunOutput;

	@FindBy(xpath = "//a[contains(text(),'Practice Questions')]")
	WebElement practice_ques;

	@FindAll(value = { @FindBy(className = "list-group") })
	List<WebElement> practiceQuestions;

	public void getStartedclick() {
		getstarted.click();
	}

	public void timeComplexclick() {
		timecomplex.click();
	}

	public void tryHereclick() {
		tryhere.click();
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
		utilsObj.elementToBeClickableWaitTime(runButton);
		runButton.click();
	}

	public String getOutput() {
		utilsObj.visibilityOfMoreWaitTime(afterRunOutput);
		return afterRunOutput.getText();
	}

	public void pracQuesclick() {
		practice_ques.click();
	}

	public int getPracticeQuestionscount() {
		return practiceQuestions.size();
		
	}

	public String getActualTitle() {
		return driver.getTitle();
	}

	public DataStructurePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
