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

public class StackPage {
	WebDriver driver;
	CommonUtils utilsObj = CommonUtils.getInstance();

	@FindBy(xpath = "//h5[text()='Stack']/../a")
	WebElement getStartedButton;

	@FindBy(partialLinkText = "Operations in Stack")
	WebElement operationsLink;

	@FindBy(partialLinkText = "Implementation")
	WebElement implementationLink;

	@FindBy(partialLinkText = "Applications")
	WebElement applicationsLink;

	@FindBy(partialLinkText = "Practice Questions")
	WebElement stackPracticeLink;

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

	@FindAll(value = { @FindBy(className = "list-group") })
	List<WebElement> practiceQuestions;

	public void clickGetStartedBtn() {
		getStartedButton.click();
	}

	public void clickTopicLink(String topicXpath) {
		switch (topicXpath) {
		case "Operations in Stack":
			operationsLink.click();
			break;

		case "Implementation":
			implementationLink.click();
			break;

		case "Applications":
			applicationsLink.click();
			break;

		default:
			throw new RuntimeException("Please pass the topic name: ");
		}
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
		stackPracticeLink.click();
	}

	public int getPracticeQuestionsCount() {
		return practiceQuestions.size();
	}

	public String getActualTitle() {
		return driver.getTitle();
	}

	public StackPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
