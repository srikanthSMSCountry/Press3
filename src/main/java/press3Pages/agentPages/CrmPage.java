package press3Pages.agentPages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import junit.framework.Assert;
import press3Test.BrowserFunctions;

public class CrmPage{
	
	public WebDriver driver;
	public String profileName;
	public String userName;
	public String passWord;
	final static Logger logger = Logger.getLogger(AgentHomePage.class);
	public Map<String,Integer> counterMap=new HashMap<String,Integer>();
	public CrmPage(WebDriver driver,String profileName,String userName, String passWord){
		this.driver= driver;
		this.profileName=profileName;
		this.userName=userName;
		this.passWord = passWord;
	}
	
	//add contact fields
	public By addContactsButton = By.id("addCont");
	public By nameFiled = By.id("txtCallerName");
	public By mobileFiled = By.id("txtCallerMobile");
	public By emailField = By.id("txtCallerEmail");
	public By studentIdField = By.xpath("//*[@key='Student id']");
	public By alternateNumberField = By.id("txtalternatenumber");
	public By plusSymbol = By.xpath("//*[@class='icon-plus text-blue AddAlternatenumber pointer ']");
	public By addButtonToSave = By.id("btnCreateCaller");
	public By cancleButton = By.id("btnCancelUpdateCallerDetails");
	
	//raise offline ticket
	public By selectContact = By.xpath("//*[@id='callersDetails']//tr[1]//td//*[@class='caller']");
	public By raiseOfflineTicket = By.id("btnOffRaiseTicket");
	public By selectCategory = By.xpath("//*[@id='categoryDropDiv']//*[@class='ComboTreeItemChlid']//*[@type='checkbox']");
	public By category = By.xpath("//*[@id='categoryInput']");
	public By prioritty = By.id("ddlPriority");
	public By subject = By.id("txtSubject");
	public By description = By.id("txtDescription");
	public By create = By.id("btnCreateTicket");
	
	//element for select one number on overlay if have more than one number to make call
	public By selectOneNumber = By.xpath("//*[@id='mobiledatadetails']/input[1]");
	public By confirmButtonOnOverlay = By.id("btnConfirm");
	
	//contact details 
	public By callerDetails = By.xpath("//*[@id='callersDetails']//tbody//tr");
	public By selectOption = By.xpath(".//td[1]//*[@class='caller']");
	public By contactName = By.xpath(".//td[2]//*[contains(@class,'callerDetails')]");
	public By callOption = By.xpath(".//td[2]//*[@type='button']");
	public By mobile = By.xpath(".//td[3]");
	public By email = By.xpath(".//td[4]");
	public By alternateNumber = By.xpath(".//td[5]");
	public By studentId = By.xpath(".//td[6]");
	
	public String selectCustomer(){
		List<WebElement> list = driver.findElements(callerDetails);
		String text1= null;
		for(WebElement ele: list){
			if(ele.findElement(By.xpath(".//td[2]")).getText().contains("suryateja")){
				text1 = ele.findElement(By.xpath(".//td[2]")).getText();
				ele.findElement(By.xpath(".//td[1]//*[@class='caller']")).click();
				break;
			}
		}
		return text1;
	}
	
	public String callToCustomer() throws InterruptedException{
		Thread.sleep(2000);
		List<WebElement> list = driver.findElements(callerDetails);
		System.out.println(list.size());
		String text1= null;
		for(WebElement ele: list){
			if(ele.findElement(By.xpath(".//td[2]")).getText().contains("suryateja")){
				text1 = ele.findElement(By.xpath(".//td[2]//*[@class='callerDetails margin-right-5']")).getText();
				ele.findElement(By.xpath(".//td[2]//*[@type='button']")).click();
				break;
			}
		}
		return text1;
	}
	
	public String clickCallButtonToMakeCall() throws InterruptedException{
		Actions action = new Actions(driver); 
		String text = callToCustomer();
		//System.out.println(text);
		//action.moveToElement(driver.findElement(callOption)).perform();
		//driver.findElement(callOption).click();
		Thread.sleep(1000);
		driver.findElement(selectOneNumber).click();
		driver.findElement(confirmButtonOnOverlay).click();
		Thread.sleep(3000);
		text.replaceAll("[^a-zA-Z0-9 -]", "");
		return text;
	}
	
	public List<String> verifySavedContacts(List<String> details) throws InterruptedException{
		Thread.sleep(2000);
		List<WebElement> list = driver.findElements(callerDetails);
		System.out.println(list.size());
		List<String> customerDetails = new ArrayList<String>();
		String name= null;
		String mobile= null;
		String email= null;
		String accountNumber= null;
		for(WebElement ele: list){
			if(ele.findElement(By.xpath(".//td[3]")).getText().contains(details.get(0))){
				name = ele.findElement(By.xpath(".//td[2]//*[@class='callerDetails margin-right-5']")).getText();
				mobile = ele.findElement(By.xpath(".//td[3]")).getText();
				email = ele.findElement(By.xpath(".//td[4]")).getText();
				accountNumber = ele.findElement(By.xpath(".//td[6]")).getText();
				customerDetails.add(name);
				customerDetails.add(email);
				customerDetails.add(mobile);
				customerDetails.add(accountNumber);
				break;
			}
		}
		return customerDetails;
	}
	

	
	public long generateRandomMobileNumber(){
	//	Random objGenerator = new Random();
		long number = getRandomInteger(100000000, 199999999);
		return number;
	}
	
	  public static int getRandomInteger(int maximum, int minimum){
	        return ((int) (Math.random()*(maximum - minimum))) + minimum;
	    }
	
	public List<String> addCustomerContact() throws InterruptedException{
		List<String> customerDetails = new ArrayList<String>();
		driver.findElement(addContactsButton).click();
		Thread.sleep(1000);
		String name = "srik "+System.currentTimeMillis();
		driver.findElement(nameFiled).sendKeys(name);
		Thread.sleep(1000);
		long number = generateRandomMobileNumber();
		String mobileNumber = "8"+Long.toString(number);
		driver.findElement(mobileFiled).sendKeys(mobileNumber);
		Thread.sleep(1000);
		String email = "sri"+System.currentTimeMillis()+"@gmail.com";
		driver.findElement(emailField).sendKeys(email);
		Thread.sleep(1000);
		String accNumber = Long.toString(System.currentTimeMillis());
		driver.findElement(studentIdField).sendKeys(accNumber);
		Thread.sleep(1000);
		driver.findElement(addButtonToSave).click();
		customerDetails.add(mobileNumber);
		customerDetails.add(name);
		customerDetails.add(email);
		customerDetails.add(accNumber);
		Thread.sleep(2000);
		return customerDetails;
	}
	
	public String raiseOfflineTicket() throws InterruptedException{
		//driver.findElement(selectContact).click();
		selectCustomer();
		Thread.sleep(1000);
		driver.findElement(raiseOfflineTicket).click();
		Thread.sleep(1000);
		driver.findElement(category).click();
		Thread.sleep(1000);
		driver.findElement(selectCategory).click();
		Thread.sleep(1000);
		Select ele = new Select(driver.findElement(prioritty));
		ele.selectByValue("10");
		String subject1 = "testing" +System.currentTimeMillis(); 
		driver.findElement(subject).sendKeys(subject1);
		driver.findElement(description).sendKeys("automation testing");
		driver.findElement(create).click();
		Thread.sleep(1000);
		return subject1;
	}
	
	//raised ticket elements
	public By subjectOfRaisedTicket = By.xpath("//*[@class='ticket_panel moderate']//*[@class='ticket_cont']//div[2]//*[@class='ticketDetails']");
	
	public void verifyRaisedTickeDetails(String subject){
		boolean result = false;
		List<WebElement> raisedTicketDetails = driver.findElements(subjectOfRaisedTicket);
		for(WebElement sub : raisedTicketDetails){
			if(sub.getText().contains(subject)){
				Assert.assertTrue(true);
				break;
			}
			else{
				result = false;
			}
		}
		if(result ==  true){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false);
		}
	}
	
	

}
