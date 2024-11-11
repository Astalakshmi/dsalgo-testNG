package com.DsAlgo.pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.DsAlgo.utilities.CommonUtils;



public class HomePage {
	
    WebDriver driver;
    
    CommonUtils utilsObj = CommonUtils.getInstance();
				//constructor
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
			//locators
	

	@FindBy(xpath="//button[@class='btn']")
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

	@FindBy(xpath = "//h5[text()='Data Structures-Introduction']")
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

	
	
	public void getStartedhomeclick() {
		getStartedhome.click();
		System.out.println("Get Started Click");
	}

	public void getStartedhomeclickwithoutlogin() {
		utilsObj.visibilityOf(getStartedhome);
		getStartedhome.click();
//		WebDriverWait w1 = new WebDriverWait(driver, Duration.ofSeconds(10));
//		w1.until(ExpectedConditions.visibilityOf(getStartedhome)).click();
		
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

	public void dropdownWithoutLogin() {
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
		
		for(WebElement eachClick : dropdownListNames)
		{
//			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//			wait.until(ExpectedConditions.visibilityOf(eachClick)).click();
			utilsObj.visibilityOfMoreWaitTime(eachClick);
			eachClick.click();
		}
	}
	
	//List<WebElement> clickAnyDropdown = dropdownListNames

	public void clickspecificdropdownNames(WebElement eachclick) {
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//		wait.until(ExpectedConditions.visibilityOf(eachclick)).click();
		
	}

	public void datastrcDropdownclick() {
		datastrcDropdown.click();
	}

	public void dropDownarraysclick() {
		dropDownarrays.click();
	}

	public void dropDownlinkedclick() {
		dropDownlinked.click();
	}

	public void dropDownstackclick() {
		dropDownstack.click();
	}

	public void dropDownqueueclick() {
		dropDownqueue.click();
	}

	public void dropDowntreeclick() {
		dropDowntree.click();
	}

	public void dropDowngraphclick() {
		dropDowngraph.click();
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
		for(WebElement link:getStartedLinks) {
			utilsObj.visibilityOf(link);
			link.click();	
	}
	
		//return getStartedLinks;
	}
	
	public void clickHomeDropdown(String dropdownName) {
		switch (dropdownName) {
		case "DataStructuresDropdown":
			datastrcDropdown.click();
			break;
		case "Array":
			dropDownarrays.click();
			break;

		case "Linked List":
			dropDownlinked.click();
			break;

		case "Stack":
			dropDownstack.click();
			break;

		case "Queue":
			dropDownqueue.click();	
			break;
		case "Tree":
			dropDowntree.click();
			break;
		case "Graph":
			dropDowngraph.click();
			break;
	        		
	     default:
			throw new RuntimeException("Please click from the dropdown list from home page: ");
			
		}
	}

//	public void clickspecificGetStartedlink(WebElement link) {
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		wait.until(ExpectedConditions.visibilityOf(link)).click();
//	}

	public String getActualMessage() {
		return alertMessage.getText();
	}

	public String getAccountholdername() {
		return accountHoldername.getText();
	}

	public void logoutClick() {
		logOut.click();
	}

	public String alertSignout() {
		return alertSignout.getText();
	}

	
}
