package press3Pages.agentPages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CallBackRequestsPage {
	
	public WebDriver driver;
	public String profileName;
	public String userName;
	public String passWord;
	final static Logger logger = Logger.getLogger(AgentHomePage.class);
	public Map<String,Integer> counterMap=new HashMap<String,Integer>();
	
	public CallBackRequestsPage(WebDriver driver,String profileName,String userName, String passWord){
		this.driver= driver;
		this.profileName=profileName;
		this.userName=userName;
		this.passWord = passWord;
	}
	
	public By callOption = By.xpath("//*[@id='callBackRequests']//tr[1]//*[@type='button']");
	public By cbrCustomers = By.xpath("//*[@id='callBackRequests']//tr");
	public By customerName = By.xpath("//td[5]");
	public By callButton = By.xpath("//*[@class='btn btn-xs btn-success f_11 btn-outbound-call']");
	public By cbrNotes = By.xpath("//td[7]");
	
	public void clickCallOptionToMakeCall() throws InterruptedException{
		//driver.findElement(callOption).click();
		Thread.sleep(1000);
		List<WebElement> customers = driver.findElements(cbrCustomers);
		for(WebElement customer : customers){
			if(customer.findElement(customerName).getText().contains("suryateja")){
				customer.findElement(callButton).click();
				break;
			}
		}
	}
	
	public boolean verifyDetailsOfCBR(String notes) throws InterruptedException{
		boolean result = false;
		Thread.sleep(1000);
		List<WebElement> customers = driver.findElements(cbrCustomers);
		for(WebElement customer : customers){
			if(customer.findElement(cbrNotes).getText().contains(notes)){
				result = true;
				break;
			}
		}
		return result;
	}
	

}
