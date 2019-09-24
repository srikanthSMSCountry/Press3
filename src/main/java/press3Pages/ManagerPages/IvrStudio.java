package press3Pages.ManagerPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class IvrStudio {

	
	public WebDriver driver;
	public String profileName;
	public String userName;
	public String passWord;
	
	
	//data
	public String studionam = "IVR Studio test1";
	public String purposeName = "customer care test1";
	
	
	//manager Login Locators
	By usernameManager = By.id("txtName");
	By passwordManager = By.id("txtPassword");
	By loginBtn = By.id("btnLogin");
	By takeControl = By.id("btnOk");
	
	
	//IVR Creation Locators
	By adminOptions = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/ul[1]/li[5]/a[1]");
	By IVRStudioLink = By.xpath("//span[contains(text(),'IVR-Studio')]");
	By createNewIVR = By.xpath("/html[1]/body[1]/div[1]/div[2]/form[1]/div[7]/div[2]/div[1]/label[1]/a[1]");
	By studioname = By.id("txtStudioName");
	By callDirectionInBound = By.xpath("/html[1]/body[1]/div[1]/div[2]/form[1]/div[10]/div[2]/div[1]/div[2]/table[1]/tbody[1]/tr[2]/td[2]/label[2]/input[1]");
	By purpose = By.id("selStudioPurpose");
	By purposeName1 = By.id("txtStudioPurpose");
	By callerId = By.id("selCallerIdNumbers");
	By proceedBtn = By.id("btnSaveStudioDetails");
	
	
	// Ring Users
	By ringUsers = By.id("ring");
	By setSkillGrp = By.xpath("//*[@class='skillGroupclass form-control']");
	By ringGrp = By.xpath("//*[@class='ringstrategy form-control']");
	By waitClip = By.xpath("//*[@id=\"ivr_popups\"]/div[2]//*[@class=\"flUpload_11\"]");
	By busyClip = By.xpath("//*[@id=\"ivr_popups\"]/div[2]//*[@class=\"flUpload_11_busy_clip\"]");
	By holdClip = By.xpath("//*[@id=\"ivr_popups\"]/div[2]//*[@class=\"flUpload_11_hold_clip\"]");
	By ringUsersSaveBtn = By.xpath("//*[@class='btn green module-save']");
	
	// Menu options
	By menuName = By.name("txtMenuName_3");
	By grtclip = By.xpath("//*[@id='fileupload_div_GttMsg']//input[2]");
	By key1 = By.xpath("//*[@class='module-left']//div[3]//*[@class='opt form-control']");
	By keyValue1 = By.xpath("//*[@class='module-left']//div[3]//*[@class='opt_val form-control']");
	By key2 = By.xpath("//*[@class='module-left']//div[5]//*[@class='opt form-control']");
	By keyValue2 = By.xpath("//*[@class='module-left']//div[5]//*[@class='opt_val form-control']");
	By addNewBtn = By.xpath("//*[@class='module-left']//*[@class='btn btn-default btn-sm addmore font-green']");
	By key3 = By.xpath("//*[@class='module-left']//div[7]//*[@class='opt form-control']");
	By keyValue3 = By.xpath("//*[@class='module-left']//div[7]//*[@class='opt_val form-control']");
	By textMessage = By.xpath("//*[@class='invalid-key module-left']//label[2]//*[@value='text']");
	By gotoDrpDwn = By.xpath("//*[@class='margin-top-15']//*[@class='form-control input-inline']");
	By footerSaveBtn = By.xpath("//*[@class='modal-footer']//*[@class='btn green module-save']");

	// menu1options
	By menu1dropCall = By.xpath("//*[@p_id='5']//td[2][contains(@class,'nodecontent')]//*[@class='nodename']");
	By menu2dropCall = By.xpath("//*[@p_id='7']//td[2][contains(@class,'nodecontent')]//*[@class='nodename']");

	
	public void managerLogin() throws Throwable {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\smsc\\git\\Press3\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://qa.press3.com");
		driver.manage().window().maximize();
		driver.findElement(usernameManager).sendKeys("manager@press3.com");
		driver.findElement(passwordManager).sendKeys("Bhanu@123");
		driver.findElement(loginBtn).click();
		Thread.sleep(2000);
		driver.findElement(takeControl).click();
	}
	
	public void createIVRStudio() throws Throwable { // menu feedback
		Actions ac = new Actions(driver);
		ac.moveToElement(driver.findElement(adminOptions)).build().perform();
		Thread.sleep(2000);
		driver.findElement(IVRStudioLink).click();
		Thread.sleep(2000);
		driver.findElement(createNewIVR).click();
		Thread.sleep(2000);
		driver.findElement(studioname).sendKeys(studionam);
		Thread.sleep(2000);
		driver.findElement(callDirectionInBound).click();
		Thread.sleep(2000);
		Select selectPurpose = new Select(driver.findElement(purpose));
		selectPurpose.selectByVisibleText("Others");
		Thread.sleep(2000);
		driver.findElement(purposeName1).sendKeys(purposeName);
		Thread.sleep(2000);
		Select selectNumber = new Select(driver.findElement(callerId));
		selectNumber.selectByIndex(2);
		Thread.sleep(2000);
		driver.findElement(proceedBtn).click();
		}
	
	@Test
	public static void ivrCreation() throws Throwable {
		IvrStudio ivrStd = new IvrStudio();
		ivrStd.managerLogin();
		ivrStd.createIVRStudio();
	}
}








