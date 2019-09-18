package press3Pages.agentPages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import press3Test.BrowserFunctions;

public class CallHistoryPage extends BrowserFunctions {
	
	public WebDriver driver;
	public String profileName;
	public String userName;
	public String passWord;
	final static Logger logger = Logger.getLogger(AgentHomePage.class);
	public Map<String, Integer> counterMap = new HashMap<String, Integer>();

	public CallHistoryPage(WebDriver driver, String profileName, String userName, String passWord) {
		this.driver = driver;
		this.profileName = profileName;
		this.userName = userName;
		this.passWord = passWord;
	}
	
	public By dateFilter = By.id("txtDatefilter");
	public By lastWeakInDateFilter = By.xpath("//*[@data-range-key='Last 7 Days']");
	public By getReportsButton = By.id("btnGetReports"); 
	public By searchBox = By.id("txtSearch");
	
	public By direction = By.xpath("//*[@id='ddlDirection']");
	public By customerDetails = By.xpath("//*[@id='outboundCallHistoryReports']//td[3]");
	
	public By customerNumberForInBound = By.xpath("//*[@id='tblCallHistory']//tr//*[@class='callerDetail'][2]");
	public By customerNumberForOutBound = By.xpath("//*[@id='outboundCallHistoryReports']//tr//td[3]");
	public By callButton = By.xpath(".//*[@type='button']");
	
	public By customerDetail = By.xpath("//*[@id='outboundCallHistoryReports']//tr");
	
	//outBounnd history details
	public By customerDetailsForOutBound = By.xpath("//*[@id='outboundCallHistoryReports']//tr");
	public By timeStamp = By.xpath(".//td[1]");
	public By fromNumber = By.xpath(".//td[2]");
	public By toNumber = By.xpath(".//td[3]");
	public By callOption = By.xpath(".//td[3]//*[@type='button']");
	public By agentName = By.xpath(".//td[4]");
	public By accessType = By.xpath(".//td[5]");
	public By ringTime = By.xpath(".//td[6]");
	public By answerTime = By.xpath(".//td[7]");
	public By endTime = By.xpath(".//td[8]");
	public By duration = By.xpath(".//td[9]");
	public By endReason = By.xpath(".//td[10]");
	public By recording = By.xpath(".//td[11]");
	
	//inBounnd history details
	//*[@class='callerDetail'][2]
	public By customerDetailsForInBound = By.xpath("//*[@id='tblCallHistory']//tr");
	public By timeStampForInBound = By.xpath(".//td[1]");
	public By fromNumberForInBound = By.xpath(".//td[2]");
	public By toNumberForInBound = By.xpath(".//td[3]");
	public By callOptionForInBound = By.xpath(".//td[3]//*[@type='button']");
	
	public By skillGroup = By.xpath(".//td[4]");
	public By skillSelectionFlow = By.xpath(".//td[5]");
	public By agent = By.xpath(".//td[6]");
	public By waitTime = By.xpath(".//td[7]");
	public By durationForInbound = By.xpath(".//td[8]");
	public By holdTime = By.xpath(".//td[9]");
	public By ivrStudio = By.xpath(".//td[10]");
	public By skips = By.xpath(".//td[11]");
	public By transfer = By.xpath(".//td[12]");
	public By conference = By.xpath(".//td[13]//*[@class='conferenceCalls']");
	public By feedback = By.xpath(".//td[14]");
	public By recordingForInBound = By.xpath(".//td[15]");
	
	public void selectLastMonthInDateFilter() throws InterruptedException{
		driver.findElement(dateFilter).click();
		Thread.sleep(1000);
		driver.findElement(lastWeakInDateFilter).click();
		Thread.sleep(1000);
		Select ele = new Select(driver.findElement(direction));
		ele.selectByValue("1");
		Thread.sleep(1000);
		driver.findElement(getReportsButton).click();
		Thread.sleep(7000);
	}
	
	public void searchForCustomerInSearchBox() throws InterruptedException{
		Thread.sleep(1000);
		driver.findElement(searchBox).sendKeys("8331996838");
		Thread.sleep(1000);
	}
	
	public void clickCallOptionToMakeCall() throws InterruptedException{
		Thread.sleep(1000);
		//driver.findElement(callOption).click();
		List<WebElement> list = driver.findElements(customerDetails);
		for(WebElement ele : list){
			if(ele.getText().contains("suryateja")){
				ele.findElement(callButton).click();
				break;
			}
		}
		Thread.sleep(5000);
	}
	
	public void verifyCallDetailsAreDisplayedInCallHistory(List details) throws InterruptedException{
		Thread.sleep(1000);
		//driver.findElement(callOption).click();
		List<WebElement> list = driver.findElements(customerNumberForInBound);
		for(WebElement ele : list){
			if(ele.getText().contains((CharSequence) details.get(0))){
				Assert.assertEquals(ele.getText(), details.add(0));
				break;
			}
		}
		Thread.sleep(1000);
	}
	
	public void verify(List details) throws InterruptedException{
		Thread.sleep(1000);
		List<WebElement> list = driver.findElements(customerDetailsForInBound);
		List<String> det = new ArrayList<String>();
		for(WebElement ele : list){
			if(ele.findElement(fromNumberForInBound).getText().contains((CharSequence) details.get(0))){
				System.out.println("number : " + ele.findElement(fromNumberForInBound).getText());
				System.out.println("time stamp "+ele.findElement(timeStampForInBound).getText());
				System.out.println("duration : "+ ele.findElement(durationForInbound).getText());
				System.out.println("duration : "+ ele.findElement(durationForInbound).getText());
				det.add(ele.findElement(fromNumberForInBound).getText());
				det.add(ele.findElement(timeStampForInBound).getText());
				det.add(ele.findElement(durationForInbound).getText());
				det.add(ele.findElement(fromNumberForInBound).getText());
				Assert.assertEquals(ele.findElement(fromNumberForInBound).getText(), details.add(0));
				break;
			}
		}
		Thread.sleep(1000);
	}
	
	public void verifyCallDetailsAreDisplayedInCallHistoryForOutBound(String number) throws InterruptedException{
		Thread.sleep(1000);
		//driver.findElement(callOption).click();
		List<WebElement> list = driver.findElements(customerNumberForOutBound);
		for(WebElement ele : list){
			if(ele.getText().contains(number)){
				//Assert.assertEquals(ele.getText(), number);
				break;
			}
		}
		Thread.sleep(1000);
	}
	
	
	public String verifyCallDetailAreDisplayedInCallHistoryForOutBound(String number) throws InterruptedException{
		Thread.sleep(1000);
		String time= null;
		//driver.findElement(callOption).click();
		List<WebElement> list = driver.findElements(customerDetail);
		for(WebElement ele : list){
			if(ele.findElement(By.xpath(".//td[3]")).getText().contains(number)){
				 time = ele.findElement(By.xpath(".//td[6]")).getText();
				//Assert.assertEquals(ele.getText(), number);
				break;
			}
		}
		Thread.sleep(1000);
		return time;
	}
}
