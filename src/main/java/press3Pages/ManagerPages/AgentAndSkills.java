package press3Pages.ManagerPages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

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
	
	
	//create skills elements
	public By plusSymbolToCreateSkill = By.xpath("//*[@class='fa fa-plus-circle margin-left-15 fa-x createskill']");
	public By skillNameField = By.id("txt1");
	public By skillDescription = By.id("txtarea1");
	public By createButtonForSkill = By.id("create");
	public By createdSkills = By.xpath("//*[@id='skillsBody']//*[contains(@id,'lblskill')]");
	
	
	String skillName = "Skill_"+System.currentTimeMillis();
	String description = "Description_"+System.currentTimeMillis();
	List<String> skillDetails = new ArrayList<String>();
	
	public List<String> createNewSkill() throws InterruptedException{
		driver.findElement(plusSymbolToCreateSkill).click();
		Thread.sleep(1000);
		driver.findElement(skillNameField).sendKeys(skillName);
		driver.findElement(skillDescription).sendKeys(description);
		driver.findElement(createButtonForSkill).click();
		driver.switchTo().alert().accept();
		Thread.sleep(1000);
		skillDetails.add(skillName);
		skillDetails.add(description);
		return skillDetails;
	}
	
	public void verifyCreatedSkill(){
		List<WebElement> details = driver.findElements(createdSkills);
		boolean result = false;
		for(WebElement detail: details){
			if(skillDetails.contains(detail.getText())){
				Assert.assertTrue(true);
				result = true;
				break;
			}
			else{
				result = false;
			}
		}
		if(result == true){
			Assert.assertTrue(result);
		}else{
			Assert.assertTrue(result);
		}
	}
	
	//create skill group
	public By addNewGrpButton = By.xpath("//*[contains(@class,'creategroup')]");
	public By skillGrpName = By.id("txt2");
	public By skillsCheckBox = By.xpath("//*[@id='skills']//*[@class='skills']");
	public By skills = By.xpath("//*[@id='skills']");
	public By descriptionForSkillGroup = By.id("txtarea2");
	public By createGrpBuuton = By.id("create-group");
	public By savedGrpDetails = By.xpath("//*[@id='skillGroup']//tr");
	public By grpName = By.xpath(".//*[contains(@id,'lblskill')]");
	public By savedSkills = By.xpath(".//*[@class='label_round_sm margin-right-5']");
	public By grpDescription = By.xpath(".//td[3]");
	
	
	String skillsGrpName = "SkillGrp_"+System.currentTimeMillis();
	String skillsGrpDescription = "SkillGrpDescription_"+System.currentTimeMillis();
	List<String> listOfGrpDetails = null;
	
	public void createSkillGroup() throws InterruptedException{
		String skillsText = null; 
		driver.findElement(addNewGrpButton).click();
		Thread.sleep(1000);
		skillsText = driver.findElement(skills).getText();
		driver.findElement(skillGrpName).sendKeys(skillsGrpName);
		List<WebElement> list = driver.findElements(skillsCheckBox);
		String[] splitedSkills = skillsText.split("\\s+");
		listOfGrpDetails = new ArrayList<String>(Arrays.asList(splitedSkills));
		listOfGrpDetails.add(skillsGrpName);
		System.out.println(listOfGrpDetails);
		int i=1;
		for(WebElement ele: list){
			if(i<=2){
				ele.click();
			}
			else{
				break;
			}
		}
		driver.findElement(descriptionForSkillGroup).sendKeys(skillsGrpDescription);
		listOfGrpDetails.add(skillsGrpDescription);
		driver.findElement(createGrpBuuton).click();
		Thread.sleep(1000);
		driver.switchTo().alert().accept();
		Thread.sleep(1000);
	}
	
	public void verifySavedSkillGrp(){
		List<WebElement> list = driver.findElements(savedGrpDetails);
		boolean result = false;
		int i=1;
		for(WebElement ele: list){
			if(listOfGrpDetails.contains(ele.findElement(grpName).getText())){
				List<WebElement> skillsSelected = ele.findElements(savedSkills);
				for(WebElement res: skillsSelected){
					System.out.println(res.getText());
					listOfGrpDetails.contains(res.getText());
					if(i==skillsSelected.size()){
						System.out.println("---------------");
						result = true;
					}
					i++;
				}
				break;
			}else{
				result = false;
			}
		}
		if(result == true){
			Assert.assertTrue(result);
		}else{
			Assert.assertTrue(result);
		}
	}
	
	//create agent elements
	
	public By addNewAgent = By.xpath("//*[@class='btn btn-sm btn-success btn-circle pull-right redirectpage']");
	
	//AgentProfileSettings elements
	
	public By displayName = By.id("txtFullName");
	public By firstName = By.id("txtFirstName");
	public By lastName  = By.id("txtLastName");
	public By mobileNumber  = By.id("txtMobile");
	public By email  = By.id("txtAgentEmail");
	public By password  = By.id("txtAgentPassword");
	public By designation = By.id("ddlDesignation");
	//Select
	//Agent
	//Team Lead
	//Manager
	//Back Office
	//Display Board
	//Floor Manager
	//Admin
	public By deviceType = By.id("ddlDeviceType");
	//External Sip Account
	//Press3 Soft Phone
	//PSTN
	public By profieStatus = By.id("ddlProfileStatus");
	//Active
	//Blocked
	//Deleted
	public By outBoundAccess = By.id("ddloutBoundType");
	//Select
	//Internal
	//National
	//International
	//No OutBound
	public By loginRequired = By.id("ddlLoginType");
	//Yes
	//No
	public By reportingManagers = By.xpath("//*[@id='divReportingMangers']//*[@type='button']");
	public By selectManager = By.xpath("//*[@id='divReportingMangers']//li");
	//  Select all
	// Madhu-Manager
	public By reportingTeamLeads = By.xpath("//*[@id='divReportingSupervisors']//*[@type='button']");
	public By teamLead = By.xpath("//*[@id='divReportingSupervisors']//*[@class='checkbox']");
	//  Select all
	// qwdd
	// rajaSupervisor
	
	public By priority1 = By.xpath("//*[@id='divpriority1']//*[@class='multiselect-native-select']//*[@type='button']");
	public By priority1Skills = By.xpath("//*[@id='divpriority1']//*[@class='multiselect-native-select']//li");
	
	public By priority2 = By.xpath("//*[@id='divpriority2']//*[@class='multiselect-native-select']//*[@type='button']");
	public By priority2Skills = By.xpath("//*[@id='divpriority2']//*[@class='multiselect-native-select']//li");
	
	public By priority3 = By.xpath("//*[@id='divpriority3']//*[@class='multiselect-native-select']//*[@type='button']");
	public By priority3Skills = By.xpath("//*[@id='divpriority3']//*[@class='multiselect-native-select']//li");
	
	public By saveButton = By.id("btnSave");
	public By cancleButton = By.xpath("//*[@class='btn btn-circle blue margin-right-10 btn-100 cancel']");
	
	String displayNameForAgent = "name_"+System.currentTimeMillis();
	String firstNameForAgent = "firstNAme_"+System.currentTimeMillis();
	String lastNameForAgent = "lastNAme_"+System.currentTimeMillis();
	String emailForAgent = "email"+System.currentTimeMillis()+"@gmail.com";
	String passwordForAgent = "Password123";
	
	
	public long generateNumber()
	{
	  return (long)(Math.random()*100000 + 7333300000L);
	}
	
	List<String> agentDetails = null;
	public List<String> createAgent() throws InterruptedException{
		agentDetails = new ArrayList<String>();
		driver.findElement(addNewAgent).click();
		Thread.sleep(1000);
		driver.findElement(displayName).sendKeys(displayNameForAgent);
		agentDetails.add(displayNameForAgent);
		driver.findElement(firstName).sendKeys(firstNameForAgent);
		agentDetails.add(firstNameForAgent);
		driver.findElement(lastName).sendKeys(lastNameForAgent);
		agentDetails.add(lastNameForAgent);
		long number = generateNumber();
		String mobileNum = Long.toString(number);
		driver.findElement(mobileNumber).sendKeys(mobileNum);
		agentDetails.add(mobileNum);
		driver.findElement(email).sendKeys(emailForAgent);
		agentDetails.add(emailForAgent);
		driver.findElement(password).sendKeys(passwordForAgent);
		agentDetails.add(passwordForAgent);
		Select selectDesignation = new Select(driver.findElement(designation));
		selectDesignation.selectByVisibleText("Agent");
		agentDetails.add("Agent");
		Select selectDeviceType = new Select(driver.findElement(deviceType));
		selectDeviceType.selectByVisibleText("PSTN");
		agentDetails.add("PSTN");
		Select selectProfileStatus = new Select(driver.findElement(profieStatus));
		selectProfileStatus.selectByVisibleText("Active");
		agentDetails.add("Active");
		Select selectOutBoundAccess = new Select(driver.findElement(outBoundAccess));
		selectOutBoundAccess.selectByVisibleText("National");
		agentDetails.add("National");
		Select selectLoginRequired = new Select(driver.findElement(loginRequired));
		selectLoginRequired.selectByVisibleText("Yes");
		agentDetails.add("Yes");
		driver.findElement(reportingManagers).click();
		Thread.sleep(1000);
		List<WebElement> managers= driver.findElements(selectManager);
		for(WebElement manager: managers){
			System.out.println("managers : "+manager.getText());
			if(manager.getText().contains("Madhu-Manager")){
				manager.click();
				agentDetails.add("Madhu-Manager");
				break;
			}
		}
		driver.findElement(reportingTeamLeads).click();
		Thread.sleep(1000);
		List<WebElement> leads= driver.findElements(teamLead);
		for(WebElement lead: leads){
			System.out.println("leads : "+lead.getText());
			if(lead.getText().contains("qwdd")){
				lead.click();
				agentDetails.add("qwdd");
				break;
			}
		}
		driver.findElement(priority1).click();
		Thread.sleep(1000);
		List<WebElement> priority1Skill= driver.findElements(priority1Skills);
		for(WebElement skill: priority1Skill){
			System.out.println("priority1 : "+skill.getText());
			if(skill.getText().contains("skill1")){
				skill.click();
				agentDetails.add("skill1");
				break;
			}
		}
		driver.findElement(priority2).click();
		Thread.sleep(1000);
		List<WebElement> priority2Skill= driver.findElements(priority2Skills);
		for(WebElement skill: priority2Skill){
			System.out.println("priority2 : "+skill.getText());
			if(skill.getText().contains("skill2")){
				skill.click();
				agentDetails.add("skill2");
				break;
			}
		}
		driver.findElement(saveButton).click();
		System.out.println(agentDetails);
		Thread.sleep(1000);
		driver.switchTo().alert().accept();
		
		return agentDetails;
	}
	List<String> allAgentDetails = agentDetails;
	
	//agent details display fields
	public By agentSearchFields = By.id("txtSearch");
	public By createdAgents = By.xpath("//*[@id='agentSkills']//tr");
	public By name = By.xpath("./td[1]");
	public By designationOfcreatedAgent = By.xpath("./td[2]");
	public By skill = By.xpath(".//td[3]//*[@class='label_round_sm margin-right-5']");
	public By loginType = By.xpath("./td[4]");
	public By deviceTypeOfcreatedAgent = By.xpath("./td[5]");
	public By deviceUserName = By.xpath("./td[6]");
	public By devicePassword = By.xpath("./td[7]");
	public By ipAddress = By.xpath("./td[8]");
	public By portNumber = By.xpath("./td[9]");
	public By gateWay = By.xpath("./td[10]");
	public By deviceStatus = By.xpath("./td[11]");
	public By activeStatus = By.xpath("./td[12]");
	public By obAccessType = By.xpath("./td[13]");
	public By lastSignalReceivedOn = By.xpath("./td[14]");
	public By loggedInFromWeb = By.xpath("./td[15]");
	
	public void verifyCreatedAgent(List<String> details)
	{
		boolean result= false;
		List<WebElement> allDetails = driver.findElements(createdAgents);
		for(WebElement detail : allDetails){
			if(details.get(0).equalsIgnoreCase(detail.findElement(name).getText())){
				System.out.println(detail.findElement(name).getText());
				System.out.println(detail.findElement(designationOfcreatedAgent).getText());
				Assert.assertTrue(details.contains(detail.findElement(designationOfcreatedAgent).getText()));
				//Assert.assertTrue(details.contains(detail.findElement(designationOfcreatedAgent).getText()));
				result= true;
				break;
			}
		}
		if(result  ==true){
			System.out.println("hello");
			Assert.assertTrue(true);
		}else{
			System.out.println("hi");
			Assert.assertTrue(false);
		}
	}
	
	

}
