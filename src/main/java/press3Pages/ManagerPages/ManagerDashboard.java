package press3Pages.ManagerPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import junit.framework.Assert;
import press3Pages.commonPages.CommonMethods;
import press3Pages.commonPages.LoginPage;
import press3Test.BrowserFunctions;

public class ManagerDashboard {

	public WebDriver driver;
	public String profileName;
	public String userName;
	public String passWord;

	// final static Logger logger = Logger.getLogger(AgentHomePage.class);
	// public Map<String,Integer> counterMap=new HashMap<String,Integer>();
	public ManagerDashboard(WebDriver driver, String profileName, String userName, String passWord) {
		this.driver = driver;
		this.profileName = profileName;
		this.userName = userName;
		this.passWord = passWord;
	}

	By managerIcon = By.xpath("//*[@id='SideBarToggle4']//*[@class='icon-user']");
	By agentAndSkillsOption = By.xpath("//*[@id=\"SideBarToggle4\"]/ul/li[3]/a/span");
	By createCompaign = By.xpath("//*[@id=\"SideBarToggle4\"]/ul/li[7]/a/span");
	By profileNameDrpDwn = By.id("spanAgentName");
	By logoutBtn = By.xpath("//*[@id='logout_modal']//*[@class='fa fa-sign-out']");
	By logoutConfirmBtn = By.xpath("//*[@class='btn btn-primary rounded-4']");
	By inbreakOption = By.xpath("//*[@class='status_agent'][1]");
	By readyOption = By.xpath("//*[@class='status_agent'][2]");
	By workAssigned = By.xpath("//*[@class='status_agent'][3]");
	By getStatus = By.xpath("//*[@class='label label-circle showforAgent']");
	By myProfileBtn = By.xpath("//*[@href='/AgentProfile.aspx?AgentId=3367']");
	By managerProfileName = By.xpath("//*[@class='username username-hide-on-mobile']");
	By managerProfileNameFromMyProfile = By.xpath("//*[@id='full_name']");
	
	
	//manager dash board elements
	By homeIcon = By.id("SideBarToggle1");
	By campaignDashBoard = By.xpath("//*[@id='SideBarToggle1']//*[@class='sub-menu']//*[@class='title']");
	By historyIcon = By.id("SideBarToggle2");
	By callHistory = By.xpath("//*[@id='SideBarToggle2']//*[@class='nav-item'][1]//*[@class='title']");
	By agentHistory = By.xpath("//*[@id='SideBarToggle2']//*[@class='nav-item'][2]//*[@class='title']");
	By cbrHistory = By.xpath("//*[@id='SideBarToggle2']//*[@class='nav-item'][3]//*[@class='title']");
	By voiceMails = By.xpath("//*[@id='SideBarToggle2']//*[@class='nav-item'][4]//*[@class='title']");
	By scoreCardHistory = By.xpath("//*[@id='SideBarToggle2']//*[@class='nav-item'][5]//*[@class='title']");
	By ticketHistory = By.xpath("//*[@id='SideBarToggle2']//*[@class='nav-item'][6]//*[@class='title']");
	By campaignHistory = By.xpath("//*[@id='SideBarToggle2']//*[@class='nav-item'][7]//*[@class='title']");
	By monthlyREports = By.xpath("//*[@id='SideBarToggle2']//*[@class='nav-item'][8]//*[@class='title']");
	
	By crmIcon = By.id("SideBarToggle3");
	By crmOption = By.xpath("//*[@id='SideBarToggle3']//*[@class='title']");
	
	By adminIcon = By.id("SideBarToggle4");
	public By generalSettings = By.xpath("//*[@id='SideBarToggle4']//*[@class='sub-menu']//*[@class='nav-item'][1]//*[@class='title']");
	public By numberManagement = By.xpath("//*[@id='SideBarToggle4']//*[@class='sub-menu']//*[@class='nav-item'][2]//*[@class='title']");
	public By agentAndSkills = By.xpath("//*[@id='SideBarToggle4']//*[@class='sub-menu']//*[@class='nav-item'][3]//*[@class='title']");
	public By ivrStudio = By.xpath("//*[@id='SideBarToggle4']//*[@class='sub-menu']//*[@class='nav-item'][4]//*[@class='title']");
	public By script = By.xpath("//*[@id='SideBarToggle4']//*[@class='sub-menu']//*[@class='nav-item'][5]//*[@class='title']");
	public By scoreCard = By.xpath("//*[@id='SideBarToggle4']//*[@class='sub-menu']//*[@class='nav-item'][6]//*[@class='title']");
	public By createCampaign = By.xpath("//*[@id='SideBarToggle4']//*[@class='sub-menu']//*[@class='nav-item'][7]//*[@class='title']");

	
	public void selectAgentAndSkillsOption() throws InterruptedException{
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(adminIcon)).perform();
		driver.findElement(agentAndSkills).click();
		Thread.sleep(1000);
	}
	

	
	

	public void clickAgentAndSkillsIcon() throws InterruptedException {
		Actions act = new Actions(driver);
		WebElement ele = driver.findElement(managerIcon);
		act.moveToElement(ele).perform();
		Thread.sleep(2000);
		driver.findElement(agentAndSkillsOption).click();
		Thread.sleep(2000);

	}

	public void clickCreateCompaignIcon() throws InterruptedException {
		Actions act = new Actions(driver);
		WebElement ele = driver.findElement(managerIcon);
		act.moveToElement(ele).build().perform();
		// driver.findElement(managerIcon).click();
		Thread.sleep(2000);
		driver.findElement(createCompaign).click();
		Thread.sleep(2000);

	}

	public void clickIvrStudioIcon() throws InterruptedException {
		driver.findElement(By.id("SideBarToggle4")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"SideBarToggle4\"]/ul/li[4]/a/span")).click();
		Thread.sleep(1000);
	}

	public void clickOnGenSettings() throws InterruptedException {
		driver.findElement(By.id("SideBarToggle4")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"SideBarToggle4\"]/ul/li[1]/a")).click();
		Thread.sleep(2000);
	}

	public void logoutManager() throws InterruptedException {
		driver.findElement(profileNameDrpDwn).click();
		driver.findElement(logoutBtn).click();
		Thread.sleep(2000);
		driver.findElement(logoutConfirmBtn).click();
	}

	public void profileStatus_break() throws InterruptedException {
		driver.findElement(profileNameDrpDwn).click();
		Thread.sleep(1000);
		driver.findElement(inbreakOption).click();
		String status = driver.findElement(getStatus).getText();
		Thread.sleep(1000);
		Assert.assertEquals("In Break", status);
		System.out.println("In Break Status Changed Successfully");

	}

	public void profileStatus_ready() throws InterruptedException {
		driver.findElement(profileNameDrpDwn).click();
		Thread.sleep(1000);
		driver.findElement(readyOption).click();
		String status = driver.findElement(getStatus).getText();
		Thread.sleep(1000);
		Assert.assertEquals("Ready", status);
		System.out.println("Ready Status Changed Successfully");

	}

	public void profileStatus_workAssigned() throws InterruptedException {
		driver.findElement(profileNameDrpDwn).click();
		Thread.sleep(1500);
		driver.findElement(workAssigned).click();
		String status = driver.findElement(getStatus).getText();
		Thread.sleep(1000);
		Assert.assertEquals("Work Assigned", status);
		System.out.println("Work Assigned Status Changed Successfully");

	}

	public void clickOnMyProfile() throws InterruptedException {
		driver.findElement(profileNameDrpDwn).click();
		Thread.sleep(1000);
		driver.findElement(myProfileBtn).click();
	}

	public String getProfileName() {
		return driver.findElement(managerProfileName).getText();
	}

	public String getProfileNameFromMyProfile() {
		return driver.findElement(managerProfileNameFromMyProfile).getText();

	}

	LoginPage login = new LoginPage();

	@Test
	public void verifyCreateNewSkill() throws InterruptedException {
		login.loginToProfile();

	}

	@Test
	public void verifyCreateNewSkillGroup() throws InterruptedException {
		login.loginToProfile();

	}

	@Test
	public void verifyCreateNewAgent() throws InterruptedException {
		login.loginToProfile();

	}

	@Test
	public void verifyCreateNewIVR() throws InterruptedException {
		login.loginToProfile();

	}

	@Test
	public void verifyEditIVR() throws InterruptedException {
		login.loginToProfile();

	}

	@Test
	public void verifyCreateCampaign() throws InterruptedException {
		login.loginToProfile();

	}

}
