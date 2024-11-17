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
import org.testng.Assert;
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
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		wait.until(ExpectedConditions.elementToBeClickable(runButton));
		utilsObj.elementToBeClickableWaitTime(runButton);
		runButton.click();
	}

//	public void clearFormText() {
//		Actions actions = new Actions(driver);
//		actions.moveToElement(inputCode).click().keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL)
//				.sendKeys(Keys.BACK_SPACE).perform();
//	}

	public String getOutput() {
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//		WebElement outputElement = wait.until(ExpectedConditions.visibilityOf(afterRunOutput)); // Wait for output visibility
		utilsObj.visibilityOfMoreWaitTime(afterRunOutput);
		return afterRunOutput.getText();
	}


	public void pracQuesclick() {
		practice_ques.click();
	}

	public void getPracticeQuestionscount() {
		int practiceCount = practiceQuestions.size();
		if (practiceCount < 1) {
			Assert.fail("No questions found");
		}
	}

	public String getActualTitle() {
		return driver.getTitle();
	}

	public DataStructurePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
