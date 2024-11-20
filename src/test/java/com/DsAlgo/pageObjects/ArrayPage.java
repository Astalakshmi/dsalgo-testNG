package com.DsAlgo.pageObjects;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.DsAlgo.utilities.CommonUtils;

public class ArrayPage {
WebDriver driver ;

CommonUtils utilsObj=CommonUtils.getInstance();

@FindBy(xpath="//h5[text()='Array']/../a")
WebElement btnArrGetStarted;
@FindBy(xpath="//a[text()='Arrays in Python']")
WebElement arrArrInPy;
@FindBy(xpath="//a[text()='Arrays Using List']")
WebElement arrArrUseList;
@FindBy(xpath="//a[text()='Basic Operations in Lists']")
WebElement arrBaseOperInList;
@FindBy(xpath="//a[text()='Applications of Array']")
WebElement arrAppOfArr;
@FindBy(xpath="//a[text()='Practice Questions']")
WebElement practiceQnsLink;
@FindBy(xpath="//a[text()='Search the array']")
WebElement pracQnsQn1;
@FindBy(xpath="//span[.='search']")
WebElement tryEditorSearchPQ1;
@FindBy(xpath="//a[text()='Max Consecutive Ones']")
WebElement pracQnsQn2;
@FindBy(xpath="//span[.='findMaxConsecutiveOnes']")
WebElement tryEditorSearchPQ2;
@FindBy(xpath="//a[text()='Find Numbers with Even Number of Digits']")
WebElement pracQnsQn3;
@FindBy(xpath="//span[.='findNumbers']")
WebElement tryEditorSearchPQ3;
@FindBy(xpath="//a[text()='Squares of  a Sorted Array']")
WebElement pracQnsQn4;
@FindBy(xpath="//span[.='sortedSquares']")
WebElement tryEditorSearchPQ4;
@FindBy(xpath="//a[text()='Try here>>>']")
WebElement tryHereBtn;
@FindBy(xpath="//div[contains(@class,'CodeMirror') and contains(@class,'cm-s-default')]//textarea")
WebElement tryHereEditor;
@FindBy(xpath="//button[text()='Run']")
WebElement tryHereRun;
@FindBy(id="output")
WebElement tryHereOutput;
@FindBy(xpath="//input[@class='button']")
WebElement clickSubmitBtn;
@FindBy(id="answer_form")
WebElement answerForm;
@FindAll(value = { @FindBy(className = "list-group") })
List<WebElement> practiceQuestions;

public void clickArrGetStartedBtn() {
	btnArrGetStarted.click();
}
public void clickArrAIPLink() {
	arrArrInPy.click();
}
public void clickArrAULLink() {
	arrArrUseList.click();
}
public void clickArrBOLLink() {
	arrBaseOperInList.click();
}
public void clickArrAOALink() {
	arrAppOfArr.click();
}

public void clickArrPracticeQnsLink() {
	practiceQnsLink.click();
}
public void clickArrPracQnsQn1() {
	pracQnsQn1.click();
}
public String landArrPQ1_TryEditorPage() {
	String textSearch=tryEditorSearchPQ1.getText();
	return textSearch;
}
public String landArrPQ2_TryEditorPage() {
	String textSearch=tryEditorSearchPQ2.getText();
	return textSearch;
}
public String landArrPQ3_TryEditorPage() {
	String textSearch=tryEditorSearchPQ3.getText();
	return textSearch;
}
public String landArrPQ4_TryEditorPage() {
	String textSearch=tryEditorSearchPQ4.getText();
	return textSearch;
}

public void clickPracticeQuestion(String question) {
switch (question) {
	
	case "Search the array":
		pracQnsQn1.click();
		break;

	case "Max Consecutive Ones":
		pracQnsQn2.click();
		break;

	case "Find Numbers with Even Number of Digits":
		pracQnsQn3.click();
		break;

	case "Squares of a Sorted Array":
		pracQnsQn4.click();
		break;

	  default:
			throw new RuntimeException("Please pass the question name correctly: ");
			
	   }
}

public void clickArrTryHereBtn() {
	tryHereBtn.click();
}

public void clickSubmitBtn() {
	WebDriverWait w1= new WebDriverWait(driver, Duration.ofSeconds(40));
	w1.until(ExpectedConditions.elementToBeClickable(clickSubmitBtn));
	clickSubmitBtn.click();
}

public String getTryHereEditorValue() {
	return tryHereEditor.getText();
}

public void enterCodePractice(String code) {
		
	answerForm.click();
	String[] str1 = code.split("\\\\n");
	
	String os = System.getProperty("os.name");
	if (os.equals("WINDOWS")){
		tryHereEditor.sendKeys(Keys.CONTROL+"a");
	}else{
		tryHereEditor.sendKeys(Keys.COMMAND+"a");
	}

	tryHereEditor.sendKeys(Keys.DELETE);
	
	for (int i = 0; i < str1.length; i++) {
		if (str1[i].contains("\\b")) {

			tryHereEditor.sendKeys(Keys.BACK_SPACE);
		} else {
			tryHereEditor.sendKeys(str1[i]);
			
			tryHereEditor.sendKeys(Keys.ENTER);
		}
	}
}

public void clickTryHereRunBtn() {
	WebDriverWait w1= new WebDriverWait(driver, Duration.ofSeconds(40));
	w1.until(ExpectedConditions.elementToBeClickable(tryHereRun));
	tryHereRun.click();
}
public String getTryHereOutputText() {
	WebDriverWait w1= new WebDriverWait(driver, Duration.ofSeconds(40));
	w1.until(ExpectedConditions.visibilityOf(tryHereOutput));
	return tryHereOutput.getText().trim();
}

public String getActualTitle() {
	return driver.getTitle();
}

public void clickTopicLink(String topicXpath) {
	switch (topicXpath) {
	
	case "ArraysInPython":
		arrArrInPy.click();
		break;

	case "ArraysUsingList":
		arrArrUseList.click();
		break;

	case "BasicOperationsinLists":
		arrBaseOperInList.click();
		break;

	case "ApplicationsofArray":
		arrAppOfArr.click();
		break;

	  default:
			throw new RuntimeException("Please pass the topic name: ");
			
	   }
	}

public void setCodePositive(String code) {
	answerForm.click();
	tryHereEditor.sendKeys(code);
}

public void setCodeNegative(String input) {
	answerForm.click();
	tryHereEditor.sendKeys(input);
}
public int getPracticeQuestionsCount() {
	return practiceQuestions.size();
}

public ArrayPage(WebDriver driver) {	
	this.driver=driver;
	PageFactory.initElements( driver,this);
}
}
