package press3Pages.agentPages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class TicketHistoryPage {
	
	public WebDriver driver;
	public String profileName;
	public String userName;
	public String passWord;
	final static Logger logger = Logger.getLogger(AgentHomePage.class);
	public Map<String, Integer> counterMap = new HashMap<String, Integer>();

	public TicketHistoryPage(WebDriver driver, String profileName, String userName, String passWord) {
		this.driver = driver;
		this.profileName = profileName;
		this.userName = userName;
		this.passWord = passWord;
	}
	public By tickets = By.xpath("//*[@id='divTickets']//*[contains(@class,'ticket_panel ')]");
	public By ticketName = By.xpath(".//h4//*[@class='ticketDetails']");
	public By ticketDescription = By.xpath(".//*[@class='col-sm-10']");
	public By ticketCategory = By.xpath(".//*[contains(@class,'ticket_cate')]//li[2]");
	public By raisedDate = By.xpath(".//*[@data-toggle='tooltip']");
	public By callButton  = By.xpath(".//*[@type='button']");
	public By callerDetails  = By.xpath(".//*[@class='ticket_act']//*[@class='margin-left-5 text-underline']");
	
	public List<String> callToCustomer(){
		List<String> customerDetails = new ArrayList<String>();
		List<WebElement> customers  = driver.findElements(tickets);
		for(WebElement customer : customers){
			customerDetails.add(customer.findElement(ticketName).getText());
			customerDetails.add(customer.findElement(ticketDescription).getText());
			customerDetails.add(customer.findElement(ticketCategory).getText());
			customerDetails.add(customer.findElement(callerDetails).getText());
			driver.findElement(callButton).click();
			break;
		}
		return customerDetails;
	}
	
	public List<String> verifyRaisedTicketDetails(String subject) throws InterruptedException{
		Thread.sleep(2000);
		List<String> customerDetails = new ArrayList<String>();
		List<WebElement> customers  = driver.findElements(tickets);
		
		for(WebElement customer : customers){
			if(customer.findElement(ticketName).getText().contains(subject))
			customerDetails.add(customer.findElement(ticketName).getText());
			customerDetails.add(customer.findElement(ticketDescription).getText());
			customerDetails.add(customer.findElement(ticketCategory).getText());
			customerDetails.add(customer.findElement(callerDetails).getText());
			break;
		}
		return customerDetails;
	}
	
	
}
