package press3Pages.ManagerPages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import press3Pages.commonPages.CommonMethods;

public class AgentAndSkills {
	public WebDriver driver;
	public String profileName;
	public String userName;
	public String passWord;

	// final static Logger logger = Logger.getLogger(AgentHomePage.class);
	// public Map<String,Integer> counterMap=new HashMap<String,Integer>();
	public AgentAndSkills(WebDriver driver, String profileName, String userName, String passWord) {
		this.driver = driver;
		this.profileName = profileName;
		this.userName = userName;
		this.passWord = passWord;
	}

//	By plusIconForNewSkill = By.xpath("//*[@id=\"ctl01\"]/div[7]/div[1]/div[1]/h4/a/i");
//	By skill = By.id("txt1");
//	By skillDescription = By.id("txtarea1");
//	By createSkillBtn = By.id("create");
//	By addNewGroupBtn = By
//			.xpath("//*[@class='graphs-panel']//*[@class='btn btn-sm btn-success btn-circle pull-right creategroup']");
//	By skillsCheck = By.xpath("//*[@id='skills']/input[1]");
//	By skillCheck = By.xpath("//*[@id='skills']");
//	By skillDescription2 = By.id("txtarea2");
//	By createGroupBtn = By.id("create-group");
//	By addNewAgent = By
//			.xpath("//*[@class='graphs-panel']//*[@class='btn btn-sm btn-success btn-circle pull-right redirectpage']");
//	By displayname = By.id("txtFullName");
//	By firstname = By.id("txtFirstName");
//	By lastname = By.id("txtLastName");
//	By mobile = By.id("txtMobile");
//	By loginid = By.id("txtAgentEmail");
//	By agentpass = By.id("txtAgentPassword");
//	By designation = By.id("ddlDesignation");
//	By deviceType = By.id("ddlDeviceType");
//	By profileStatus = By.id("ddlProfileStatus");
//	By skillclick = By.xpath("//*[@id=\"spnSkills\"]/i");
//	By firstSkillSelect = By.xpath("//*[@id='divSkills']/label[1]");
//	By secondtSkillSelect = By.xpath("//*[@id='divSkills']/label[2]");
//	By saveSkills = By.id("btnSaveSkills");
//	By OutboundAccesss = By.id("ddloutBoundType");
//	By loginType = By.id("ddlLoginType");
//	By reportingManagers = By.id("divReportingMangers");
//	By reportingManager = By.xpath(
//			"/html[1]/body[1]/div[1]/div[2]/form[1]/div[7]/div[1]/div[2]/div[2]/div[3]/div[1]/div[1]/span[1]/div[1]/ul[1]/li[3]/a[1]/label[1]/input[1]");
//	By reportingTeamLeads = By.id("divReportingSupervisors");
//	By reportingLead = By.xpath(
//			"/html[1]/body[1]/div[1]/div[2]/form[1]/div[7]/div[1]/div[2]/div[2]/div[4]/div[1]/div[1]/span[1]/div[1]/ul[1]/li[3]/a[1]/label[1]");
//	By agentSaveBtn = By.id("btnSave");
//	By agentSearch = By.id("txtSearch");
//	By searchBtn = By.id("btnSearch");
//	By NameInSearchResults = By.xpath("//*[@class='btn-link view']");
//	By skillsbody = By.id("skillsBody");
//	
//	By skillSet = By.xpath("//*[@id='skillsBody']//*[@class='skill_label margin-right-10']");
//
//	public void addNewAgent() throws InterruptedException {
//		CommonMethods cm = new CommonMethods(driver, profileName, userName, passWord);
//		driver.findElement(addNewAgent).click();
//		WebElement Displayname = driver.findElement(displayname);
//		Displayname.sendKeys(cm.passingData("DisplayName"));
//		Thread.sleep(1000);
//		WebElement Firstname = driver.findElement(firstname);
//		Firstname.sendKeys(cm.passingData("FirstName"));
//		WebElement Lastname = driver.findElement(lastname);
//		Lastname.sendKeys(cm.passingData("LastName"));
//		WebElement Mobile = driver.findElement(mobile);
//		Mobile.sendKeys(cm.passingData("Mobile"));
//		WebElement Loginid = driver.findElement(loginid);
//		Loginid.sendKeys(cm.passingData("LoginId"));
//		WebElement agentpassword = driver.findElement(agentpass);
//		agentpassword.sendKeys(cm.passingData("AgentPassword"));
//		Thread.sleep(1000);
//		Select Designation = new Select(driver.findElement(designation));
//		Designation.selectByVisibleText("Agent");
//		Thread.sleep(1000);
//		Select DeviceType = new Select(driver.findElement(deviceType));
//		DeviceType.selectByVisibleText("PSTN");
//		Select ProfileStatus = new Select(driver.findElement(profileStatus));
//		ProfileStatus.selectByVisibleText("Active");
//		driver.findElement(skillclick).click();
//		Thread.sleep(2000);
//		driver.findElement(firstSkillSelect).click(); //// Skills dynamic
//		Thread.sleep(1000);
//		driver.findElement(secondtSkillSelect).click();
//		Thread.sleep(1000);
//		driver.findElement(saveSkills).click();
//		Select OutboundAccess = new Select(driver.findElement(OutboundAccesss));
//		OutboundAccess.selectByVisibleText("National");
//		Select LoginReq = new Select(driver.findElement(loginType));
//		LoginReq.selectByVisibleText("Yes");
//		Thread.sleep(1000);
//		driver.findElement(reportingManagers).click();
//		driver.findElement(reportingManager).click(); // hardcode
//		Thread.sleep(1000);
//		driver.findElement(reportingTeamLeads).click();
//		driver.findElement(reportingLead).click(); /// hardcode
//		Thread.sleep(1000);
//		driver.findElement(agentSaveBtn).click();
//		Thread.sleep(1000);
//		driver.switchTo().alert().accept();
//		Thread.sleep(2000);
//
//	}
//
//	public void verifyAgentDetails() {
//		CommonMethods cm = new CommonMethods(driver, profileName, userName, passWord);
//		driver.findElement(agentSearch).sendKeys(cm.passingData("DisplayName"));
//		driver.findElement(searchBtn);
//		List<WebElement> ele = driver.findElements(NameInSearchResults);
//		for (WebElement agentList : ele) {
//			String agentName = agentList.getText();
//			if (agentName.equalsIgnoreCase(cm.passingData("DisplayName"))) {
//				agentList.click();
//				System.out.println("Agent added successfully");
//			}
//		}
//
//	}
//
//	public void verifySkillAndSkillGroup() {
//		CommonMethods cm = new CommonMethods(driver, profileName, userName, passWord);
//		List<WebElement> skillsList = driver.findElements(skillsbody);
//		for (WebElement skills : skillsList) {
//			String skillName = skills.getText();
//			if (skillName.equalsIgnoreCase(cm.passingData("Skill"))) {
//				System.out.println("Skill Added Successfully");
//			}
//		}
//		List<WebElement> ele2 = driver.findElements(skillsbody);
//		for (WebElement skillList : ele2) {
//			String skillName = skillList.getText();
//			if (skillName.equalsIgnoreCase(cm.passingData("Skill"))) {
//				System.out.println("SkillGroupAddedSuccessfully ");
//			}
//		}
//
//	}
//
//	public void clickPlusIconForNewSkill() throws InterruptedException {
//		driver.findElement(plusIconForNewSkill).click();
//		Thread.sleep(1000);
//	}
//
//	public void createNewSkill() throws InterruptedException {
//		CommonMethods cm = new CommonMethods(driver, profileName, userName, passWord);
//		WebElement Skill = driver.findElement(skill);
//		Skill.sendKeys(cm.passingData("Skill"));
//		Thread.sleep(1000);
//		WebElement Description = driver.findElement(skillDescription);
//		Description.sendKeys("This skill was created by automation script");
//		Thread.sleep(1000);
//		driver.findElement(createSkillBtn).click();
//		Thread.sleep(500);
//		driver.switchTo().alert().accept();
//		Thread.sleep(1000);
//	}
//	
//	public void createSkill() throws InterruptedException {
//		driver.findElement(plusIconForNewSkill).click();
//		Thread.sleep(1000);
//		CommonMethods cm = new CommonMethods(driver, profileName, userName, passWord);
//		WebElement Skill = driver.findElement(skill);
//		String skillName = "skill"+System.currentTimeMillis();
//		Skill.sendKeys(cm.passingData(skillName));
//		Thread.sleep(1000);
//		WebElement Description = driver.findElement(skillDescription);
//		String SkillDescription = "This skill was created by automation script "+System.currentTimeMillis();
//		Description.sendKeys( SkillDescription );
//		Thread.sleep(1000);
//		driver.findElement(createSkillBtn).click();
//		Thread.sleep(500);
//		driver.switchTo().alert().accept();
//		Thread.sleep(1000);
//	}
//	
//	
//	public void verifyCreatedSkill() throws InterruptedException {
//		
//		List<WebElement> list = driver.findElements(skillSet);
//		
////		driver.findElement(plusIconForNewSkill).click();
////		Thread.sleep(1000);
////		CommonMethods cm = new CommonMethods(driver, profileName, userName, passWord);
////		WebElement Skill = driver.findElement(skill);
////		String skillName = "skill"+System.currentTimeMillis();
////		Skill.sendKeys(cm.passingData(skillName));
////		Thread.sleep(1000);
////		WebElement Description = driver.findElement(skillDescription);
////		String SkillDescription = "This skill was created by automation script "+System.currentTimeMillis();
////		Description.sendKeys( SkillDescription );
////		Thread.sleep(1000);
////		driver.findElement(createSkillBtn).click();
////		Thread.sleep(500);
////		driver.switchTo().alert().accept();
////		Thread.sleep(1000);
//	}
//
//	public void createSecondSkill() throws InterruptedException {
//		WebElement Skill = driver.findElement(skill);
//		Skill.sendKeys("AutomationSkill2");
//		Thread.sleep(1000);
//		WebElement Description = driver.findElement(skillDescription);
//		Description.sendKeys("This skill was created by automation script");
//		Thread.sleep(1000);
//		driver.findElement(createSkillBtn).click();
//		Thread.sleep(500);
//		driver.switchTo().alert().accept();
//		Thread.sleep(1000);
//	}
//
//	public void addNewGroup() throws InterruptedException {
//		CommonMethods cm = new CommonMethods(driver, profileName, userName, passWord);
//		driver.findElement(addNewGroupBtn).click();
//		Thread.sleep(1000);
//		WebElement skillgroup = driver.findElement(By.id("txt2"));
//		skillgroup.sendKeys(cm.passingData("SkillGroup"));
//		Thread.sleep(1000);
//		driver.findElement(skillsCheck).click();
//		/*
//		 * List<WebElement> ele =driver.findElements(skillCheck); //////// It
//		 * will //////// change //////// dynamic for (WebElement skillList :
//		 * ele){ String skillName =skillList.getText();
//		 * if(skillName.equalsIgnoreCase(cm.passingData("Skill"))){
//		 * driver.findElement(skillsCheck).click(); } }
//		 */
//		Thread.sleep(1000);
//		WebElement description2 = driver.findElement(skillDescription2);
//		description2.sendKeys("This group was created by automation script");
//		Thread.sleep(1000);
//		driver.findElement(createGroupBtn).click();
//		Thread.sleep(1000);
//		driver.switchTo().alert().accept();
//		Thread.sleep(1000);
//
//	}
	
	
	//skills elements
	
	public By plusSymbolToCreateSkill = By.xpath("//*[@class='fa fa-plus-circle margin-left-15 fa-x createskill']");
	public By skillNameField = By.id("txt1");
	public By skillDescription = By.id("txtarea1");
	public By createButtonForSkill = By.id("create");
	
	public void createNewSkill() throws InterruptedException{
		driver.findElement(plusSymbolToCreateSkill).click();
		Thread.sleep(1000);
	}

}
