package com.DsAlgo.pageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.DsAlgo.utilities.CommonUtils;

public class HomePage {

	WebDriver driver;

	CommonUtils utilsObj = CommonUtils.getInstance();

	// constructor
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	// locators

	@FindBy(xpath = "//button[@class='btn']")
	WebElement getStartedhome;

	@FindBy(xpath = "//a[text()='NumpyNinja']")
	WebElement numpyLogo;

	@FindBy(xpath = "//a[text()='Data Structures']")
	WebElement datastructure;

	@FindBy(xpath = "//div[@class='dropdown-menu show']")
	WebElement datastrcDropdown;

	@FindBy(xpath = "//div[@class='dropdown-menu show']/a[text()='Arrays']")
	WebElement dropDownarrays;

	@FindBy(xpath = "//div[@class='dropdown-menu show']/a[text()='Linked List']")

	WebElement dropDownlinked;

	@FindBy(xpath = "//div[@class='dropdown-menu show']/a[text()='Stack']")

	WebElement dropDownstack;

	@FindBy(xpath = "//div[@class='dropdown-menu show']/a[text()='Queue']")
	WebElement dropDownqueue;

	@FindBy(xpath = "//div[@class='dropdown-menu show']/a[text()='Tree']")
	WebElement dropDowntree;

	@FindBy(xpath = "//div[@class='dropdown-menu show']/a[text()='Graph']")
	WebElement dropDowngraph;

	@FindBy(xpath = "//h5[text()='Data Structures-Introduction']/../a[text()='Get Started']")
	WebElement datastructureGetstarted;

	@FindBy(xpath = "//h5[text()='Array']/../a[text()='Get Started']")
	WebElement arrayGetstarted;

	@FindBy(xpath = "//h5[text()='Linked List']/../a[text()='Get Started']")
	WebElement linkedlistGetstarted;

	@FindBy(xpath = "//h5[text()='Stack']/../a[text()='Get Started']")
	WebElement stackGetstarted;

	@FindBy(xpath = "//h5[text()='Queue']/../a[text()='Get Started']")
	WebElement queueGetstarted;

	@FindBy(xpath = "//h5[text()='Tree']/../a[text()='Get Started']")
	WebElement treeGetstarted;

	@FindBy(xpath = "//h5[text()='Graph']/../a[text()='Get Started']")
	WebElement graphGetstarted;

	@FindBy(xpath = "//div[contains(text(),'You are not logged in')]")
	WebElement alertMessage;

	@FindBy(xpath = "//a[contains(text(),'DreamTeam')]")
	WebElement accountHoldername;

	@FindBy(xpath = "//a[text()='Sign out']")
	WebElement logOut;

	@FindBy(xpath = "//div[@class='alert alert-primary']")
	WebElement alertSignout;

	@FindBy(xpath = "//a[@class='nav-link dropdown-toggle' and @data-toggle='dropdown']")
	WebElement dropdownToggle;

	public void getStartedhomeclick() {
		getStartedhome.click();
	}

	public void getStartedhomeclickwithoutlogin() {
		utilsObj.visibilityOf(getStartedhome);
		getStartedhome.click();
	}

	public void numpyLogoclick() {
		numpyLogo.click();
	}

	public String getActualLogoMessage() {
		return numpyLogo.getText();
	}

	public void dataStructureclick() {
		datastructure.click();
	}

	public String getDatastrucActualString() {
		return datastructure.getText();
	}

	public void clickDropdownWithoutLogin() {
		List<WebElement> dropdownListNames = new ArrayList<>();
		dropdownListNames.add(datastructure);
		dropdownListNames.add(dropDownarrays);
		dropdownListNames.add(datastructure);
		dropdownListNames.add(dropDownlinked);
		dropdownListNames.add(datastructure);
		dropdownListNames.add(dropDownstack);
		dropdownListNames.add(datastructure);
		dropdownListNames.add(dropDownqueue);
		dropdownListNames.add(datastructure);
		dropdownListNames.add(dropDowntree);
		dropdownListNames.add(datastructure);
		dropdownListNames.add(dropDowngraph);

		for (WebElement eachClick : dropdownListNames) {
			utilsObj.visibilityOfMoreWaitTime(eachClick);
			eachClick.click();
		}
	}

	public void accountHoldernameclick() {
		accountHoldername.click();
	}

	public void datastructureGetstartedclick() {
		datastructureGetstarted.click();
	}

	public void arrayGetstartedclick() {
		arrayGetstarted.click();
	}

	public void linkedlistGetstartedclick() {
		linkedlistGetstarted.click();
	}

	public void stackGetstartedclick() {
		stackGetstarted.click();
	}

	public void queueGetstartedclick() {
		queueGetstarted.click();
	}

	public void treeGetstartedclick() {
		treeGetstarted.click();
	}

	public void graphGetstartedclick() {
		graphGetstarted.click();
	}

	public String getActualTitle() {
		return driver.getTitle();
	}

	public void getAnylinkofGetStarted() {
		List<WebElement> getStartedLinks = new ArrayList<>();
		getStartedLinks.add(datastructureGetstarted);
		getStartedLinks.add(arrayGetstarted);
		getStartedLinks.add(linkedlistGetstarted);
		getStartedLinks.add(stackGetstarted);
		getStartedLinks.add(queueGetstarted);
		getStartedLinks.add(treeGetstarted);
		getStartedLinks.add(graphGetstarted);
		for (WebElement link : getStartedLinks) {
			utilsObj.visibilityOf(link);
			link.click();
		}
	}

	public void clickHomeDropdown(String dropdownName) {
		switch (dropdownName) {
		case "Array":
			dropdownToggle.click();
			utilsObj.attributeToBeWaitTime(dropdownToggle, "aria-expanded", "true");
			dropDownarrays.click();
			break;

		case "Linked List":

			WebElement dropdownToggle2 = driver
					.findElement(By.xpath("//a[@class='nav-link dropdown-toggle' and @data-toggle='dropdown']"));
			dropdownToggle2.click();
			WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait2.until(ExpectedConditions.attributeToBe(dropdownToggle2, "aria-expanded", "true"));
			utilsObj.visibilityOf(dropDownlinked);
			dropDownlinked.click();
			break;

		case "Stack":
			dropdownToggle.click();
			utilsObj.attributeToBeWaitTime(dropdownToggle, "aria-expanded", "true");
			dropDownstack.click();
			break;

		case "Queue":
			dropdownToggle.click();
			utilsObj.attributeToBeWaitTime(dropdownToggle, "aria-expanded", "true");
			dropDownqueue.click();
			break;
		case "Tree":
			dropdownToggle.click();
			utilsObj.attributeToBeWaitTime(dropdownToggle, "aria-expanded", "true");
			dropDowntree.click();
			break;
		case "Graph":
			dropdownToggle.click();
			utilsObj.attributeToBeWaitTime(dropdownToggle, "aria-expanded", "true");
			dropDowngraph.click();
			break;

		default:
			throw new RuntimeException("Please click from the dropdown list from home page: ");

		}
	}

	public void clickGetStarted(String getStartedName) {
		switch (getStartedName) {
		case "Data Structures-Introduction":
			datastructureGetstarted.click();
			break;
		case "Array":
			arrayGetstarted.click();
			break;

		case "Linked List":
			linkedlistGetstarted.click();
			break;

		case "Stack":
			stackGetstarted.click();
			break;

		case "Queue":
			queueGetstarted.click();
			break;
		case "Tree":
			treeGetstarted.click();
			break;
		case "Graph":
			graphGetstarted.click();
			break;

		default:
			throw new RuntimeException("Please click GetStarted Click Button from home page: ");

		}
	}

	public String getActualMessage() {
		return alertMessage.getText();
	}

	public String getAccountholdername() {
		return accountHoldername.getText();
	}

	public void clickSignout() {
		logOut.click();
	}

	public String alertSignout() {
		return alertSignout.getText();
	}

}
