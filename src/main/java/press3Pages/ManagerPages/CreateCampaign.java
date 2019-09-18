package press3Pages.ManagerPages;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import press3Test.BrowserFunctions;

public class CreateCampaign {

	public WebDriver driver;
	public String profileName;
	public String userName;
	public String passWord;

	// final static Logger logger = Logger.getLogger(AgentHomePage.class);
	// public Map<String,Integer> counterMap=new HashMap<String,Integer>();
	public CreateCampaign(WebDriver driver, String profileName, String userName, String passWord) {
		this.driver = driver;
		this.profileName = profileName;
		this.userName = userName;
		this.passWord = passWord;
	}

	By autodialerSelect = By.xpath("//*[@id=\"ctl01\"]/div[7]/div/div[1]/div[1]/div/div/a[2]");
	By compaignName = By.id("txt-campaign-name");
	By attemptss = By.id("ddl-max-dial-attempts");
	By descriptionn = By.id("txt-area-campaign-description");
	By timezonee = By.id("ddl-timeZone");
	By callrecordingg = By.id("ddl-is-call-record");
	By calleridd = By.id("ddl-callerid");
	By contactdisplayy = By.id("ddl-contact-display-type");
	By handleTimee = By.id("txt-target-call-handle-time");
	By ringLimitt = By.id("txt-ring-limit");
	By nextBtn = By.id("btn-next");
	By distributionType = By.id("ddl-agent-distribution-type");
	By selectAgents = By.id("ddl-select-agents");
	By searchh = By.id("txt-search-agents");
	By selectKoushik = By.xpath("//*[@agent-id=\"3278\"]//input");
	By selectSrikanth = By.xpath("//*[@class='clearfix padBox']//*[@agent-name='srikanth']");
	By callDispositionsBusyOption = By.xpath("//*[@id='call_disposition']//*[@class='margin-right-5 chk-dispositions pointer']");
	By excelUpload = By.id("excelUploadFile");
	// =========================================
	By voiceBroadcast = By.xpath("//*[@id=\"ctl01\"]/div[7]/div/div[1]/div[2]/div/div/a[2]");
	By ivrSurvey = By.xpath("//*[@id=\"ctl01\"]/div[7]/div/div[2]/div[2]/div/div/a[2]");
	By ivrConnect = By.xpath("//*[@id=\"ctl01\"]/div[7]/div/div[2]/div[2]/div/div/a[2]");
	By contactSheet = By.xpath("//*[@class='field form-control columns']");
	By koushikCheckbox = By.xpath("//*[@agent-name='koushik']");

	public void IvrConnectCompaign() throws InterruptedException {

		driver.findElement(ivrConnect).click();
		Thread.sleep(1000);
		driver.findElement(compaignName).sendKeys("Automation Ivr Connect");
		Select attempts = new Select(driver.findElement(attemptss));
		attempts.selectByVisibleText("2");
		Thread.sleep(1000);
		WebElement description = driver.findElement(descriptionn);
		description.sendKeys("This Ivr Connect was created by Automation Script");
		Thread.sleep(1000);
		Select timezone = new Select(driver.findElement(timezonee));
		timezone.selectByVisibleText("UTC +05:30");
		Thread.sleep(1000);
		Select callrecording = new Select(driver.findElement(callrecordingg));
		callrecording.selectByVisibleText("Enable");
		Thread.sleep(1000);
		Select callerid = new Select(driver.findElement(calleridd));
		callerid.selectByVisibleText("04023008623");
		Thread.sleep(1000);
		Select contactdisplay = new Select(driver.findElement(contactdisplayy));
		contactdisplay.selectByVisibleText("Full Display");
		Thread.sleep(1000);
		WebElement handletime = driver.findElement(handleTimee);
		handletime.sendKeys("20");
		WebElement ringlimit = driver.findElement(ringLimitt);
		ringlimit.sendKeys("10");
		driver.findElement(nextBtn).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"step\"]/div/label/input")).click();
		Thread.sleep(1000);
		driver.findElement(nextBtn).click();
		Select distributiontype = new Select(driver.findElement(distributionType));
		distributiontype.selectByVisibleText("Dedicated");
		Thread.sleep(1000);
		Select selectagents = new Select(driver.findElement(selectAgents));
		selectagents.selectByVisibleText("Agents");
		WebElement search = driver.findElement(searchh);
		search.sendKeys("kous");
		driver.findElement(koushikCheckbox).click();
		Thread.sleep(500);
		driver.findElement(By.id("txt-search-agents")).clear();
		driver.findElement(nextBtn).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"call_disposition\"]/div/div/div[1]/div[1]/ul/li[1]/label/input"))
				.click();
		Thread.sleep(1000);
		driver.findElement(nextBtn).click();
		Thread.sleep(1000);
		WebElement upload = driver.findElement(excelUpload);
		upload.sendKeys("E:\\Campaign Callers New.xlsx");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"upl_cont\"]/div[1]/div[2]/div/label[1]/input")).click();
		Thread.sleep(1000);
		Select contacts = new Select(driver.findElement(contactSheet));
		contacts.selectByVisibleText("Number");
		Thread.sleep(1000);
		driver.findElement(nextBtn).click();
		Thread.sleep(5000);
	}

	public void ivrSurveyCompaign() throws InterruptedException {

		driver.findElement(ivrSurvey).click();
		Thread.sleep(1000);
		driver.findElement(compaignName).sendKeys("Automation Ivr Connect");
		Select attempts = new Select(driver.findElement(attemptss));
		attempts.selectByVisibleText("2");
		Thread.sleep(1000);
		driver.findElement(descriptionn).sendKeys("This Ivr Connect was created by Automation Script");
		Thread.sleep(1000);
		Select timezone = new Select(driver.findElement(timezonee));
		timezone.selectByVisibleText("UTC +05:30");
		Thread.sleep(1000);
		Select callrecording = new Select(driver.findElement(callrecordingg));
		callrecording.selectByVisibleText("Enable");
		Thread.sleep(1000);
		Select callerid = new Select(driver.findElement(calleridd));
		callerid.selectByVisibleText("04023008623");
		Thread.sleep(1000);
		Select contactdisplay = new Select(driver.findElement(contactdisplayy));
		contactdisplay.selectByVisibleText("Full Display");
		Thread.sleep(1000);
		WebElement handletime = driver.findElement(handleTimee);
		handletime.sendKeys("20");
		WebElement ringlimit = driver.findElement(ringLimitt);
		ringlimit.sendKeys("10");
		driver.findElement(nextBtn).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"step\"]/div/label/input")).click();
		Thread.sleep(1000);
		driver.findElement(nextBtn).click();
		Select distributiontype = new Select(driver.findElement(distributionType));
		distributiontype.selectByVisibleText("Dedicated");
		Thread.sleep(1000);
		Select selectagents = new Select(driver.findElement(selectAgents));
		selectagents.selectByVisibleText("Agents");
		WebElement search = driver.findElement(searchh);
		search.sendKeys("kous");
		driver.findElement(koushikCheckbox).click();
		Thread.sleep(500);
		driver.findElement(By.id("txt-search-agents")).clear();
		driver.findElement(nextBtn).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"call_disposition\"]/div/div/div[1]/div[1]/ul/li[1]/label/input"))
				.click();
		Thread.sleep(1000);
		driver.findElement(nextBtn).click();
		Thread.sleep(1000);
		WebElement upload = driver.findElement(excelUpload);
		upload.sendKeys("E:\\Campaign Callers New.xlsx");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"upl_cont\"]/div[1]/div[2]/div/label[1]/input")).click();
		Thread.sleep(1000);
		Select contacts = new Select(driver.findElement(contactSheet));
		contacts.selectByVisibleText("Number");
		Thread.sleep(1000);
		driver.findElement(nextBtn).click();
		Thread.sleep(5000);
	}

	public void voiceBroadcastCompaign() throws InterruptedException {
		driver.findElement(voiceBroadcast).click();
		Thread.sleep(1000);
		driver.findElement(compaignName).sendKeys("Automation Broadcast");
		;
		Thread.sleep(1000);
		Select attempts = new Select(driver.findElement(attemptss));
		attempts.selectByVisibleText("2");
		Thread.sleep(1000);
		WebElement description = driver.findElement(descriptionn);
		description.sendKeys("This Broadcast was created by Automation Script");
		Thread.sleep(1000);
		Select timezone = new Select(driver.findElement(timezonee));
		timezone.selectByVisibleText("UTC +05:30");
		Thread.sleep(1000);
		Select callerid = new Select(driver.findElement(calleridd));
		callerid.selectByVisibleText("04023008623");
		Thread.sleep(1000);
		driver.findElement(ringLimitt).sendKeys("10");
		driver.findElement(By.id("btn-next")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"step\"]/div/label/input")).click();
		Thread.sleep(1000);
		driver.findElement(nextBtn).click();
		driver.findElement(excelUpload).sendKeys("E:\\Campaign Callers New.xlsx");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"upl_cont\"]/div[1]/div[2]/div/label[1]/input")).click();
		Thread.sleep(1000);
		Select contacts = new Select(driver.findElement(contactSheet));
		contacts.selectByVisibleText("Number");
		Thread.sleep(1000);
		driver.findElement(By.id("btn-next")).click();
		Thread.sleep(5000);
	}

	public String autoDialerCompaign() throws InterruptedException {
		driver.findElement(autodialerSelect).click();
		WebElement Campaignname = driver.findElement(compaignName);
		String campaignName = "automation "+ System.currentTimeMillis();
		Campaignname.sendKeys(campaignName);
		Select attempts = new Select(driver.findElement(attemptss));
		attempts.selectByVisibleText("2");
		Thread.sleep(1000);
		WebElement description = driver.findElement(descriptionn);
		description.sendKeys("This Campaign was created by Automation Script");
		Thread.sleep(1000);
		Select timezone = new Select(driver.findElement(timezonee));
		timezone.selectByVisibleText("UTC +05:30");
		Thread.sleep(1000);
		Select callrecording = new Select(driver.findElement(callrecordingg));
		callrecording.selectByVisibleText("Enable");
		Thread.sleep(1000);
		Select callerid = new Select(driver.findElement(calleridd));
		callerid.selectByVisibleText("04071045045");
		Thread.sleep(1000);
		Select contactdisplay = new Select(driver.findElement(contactdisplayy));
		contactdisplay.selectByVisibleText("Full Display");
		Thread.sleep(1000);
		WebElement handletime = driver.findElement(handleTimee);
		handletime.sendKeys("20");
		WebElement ringlimit = driver.findElement(ringLimitt);
		ringlimit.sendKeys("10");
		driver.findElement(nextBtn).click();
		Thread.sleep(1000);
		Select distributiontype = new Select(driver.findElement(distributionType));
		distributiontype.selectByVisibleText("Dedicated");
		Thread.sleep(1000);
		Select selectagents = new Select(driver.findElement(selectAgents));
		selectagents.selectByVisibleText("Agents");
		WebElement search = driver.findElement(searchh);
		search.sendKeys("srikanth");
		driver.findElement(selectSrikanth).click();
		Thread.sleep(500);
		//driver.findElement(By.id("txt-search-agents")).clear();
		//driver.findElement(nextBtn).click();
		driver.findElement(By.id("btn-next")).click();
		Thread.sleep(1000);
		driver.findElement(callDispositionsBusyOption).click();
		Thread.sleep(1000);
		driver.findElement(nextBtn).click();
		Thread.sleep(1000);
		WebElement upload = driver.findElement(excelUpload);
		upload.sendKeys("D:\\Campaign Callers New.xlsx");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"upl_cont\"]/div[1]/div[2]/div/label[1]/input")).click();
		Thread.sleep(1000);
		Select contacts = new Select(driver.findElement(contactSheet));
		contacts.selectByVisibleText("Number");
		Thread.sleep(1000);
		driver.findElement(nextBtn).click();
		Thread.sleep(5000);
		return campaignName;
	}
	
	public By campaignDetails = By.xpath("//*[@class='portlet perform_sur_box']");
	public By camPaignNAme = By.xpath(".//*[@class='col-sm-6'][1]//*[@id='spanCampainName']");
	public By startCampaignButton = By.xpath(".//*[@class='play_optns']//*[@id='imgCampaignStart']");
	public By yesButtonOnAlert = By.xpath("//*[@id='btnOK']");
	
	public void startCampaign(String campaignNAme) throws InterruptedException{
		List<WebElement> campaigns = driver.findElements(campaignDetails);
		for(WebElement ele : campaigns){
			if(ele.findElement(camPaignNAme).getText().contains(campaignNAme)){
				ele.findElement(startCampaignButton).click();
				break;
			}
		}
		Thread.sleep(1000);
		driver.findElement(yesButtonOnAlert).click();
		driver.switchTo().alert().accept();
		Thread.sleep(1000);
	}
}
