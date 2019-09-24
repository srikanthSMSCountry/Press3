package press3Pages.ManagerPages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class Scripts {
	

	public WebDriver driver;
	public String profileName;
	public String userName;
	public String passWord;

	// final static Logger logger = Logger.getLogger(AgentHomePage.class);
	// public Map<String,Integer> counterMap=new HashMap<String,Integer>();
	public Scripts(WebDriver driver, String profileName, String userName, String passWord) {
		this.driver = driver;
		this.profileName = profileName;
		this.userName = userName;
		this.passWord = passWord;
	}
	
	//create script elements
	public By createNewScriptButton = By.id("createScript");
	public By scriptTile = By.id("txtscriptTitle");
	public By attachSkillGrp = By.id("selSkillGroup");
	//grp
	//SkillGrp_1568806058808
	//SkillGrp_1568812829606
	//SkillGrp_1568812906504
	//SkillGrp_1568813118931
	public By composeButton = By.id("composeScript");
	public By sectionField = By.xpath("//*[@class='form-control input-circle sectionName']");
	public By topicField = By.xpath("//*[@class='form-control input-circle topicName']");
	public By description = By.xpath("//*[@class='form-control brd topicDesc']");
	public By saveButton = By.id("saveScript");
	
	String scriptName = "script_"+System.currentTimeMillis();
	String sectionName = "section_"+System.currentTimeMillis();
	String topicName = "topic_"+System.currentTimeMillis();
	String descriptionForScript = "Des_"+System.currentTimeMillis();
	
	public List<String> createScript() throws InterruptedException{
		List<String> list = new ArrayList<String>();
		driver.findElement(createNewScriptButton).click();
		Thread.sleep(1000);
		driver.findElement(scriptTile).sendKeys(scriptName);
		list.add(scriptName);
		Select element = new Select(driver.findElement(attachSkillGrp));
		element.selectByVisibleText("grp");
		list.add("grp");
		Thread.sleep(1000);
		driver.findElement(composeButton).click();
		Thread.sleep(1000);
		driver.findElement(sectionField).sendKeys(sectionName);
		list.add(sectionName);
		driver.findElement(topicField).sendKeys(topicName);
		list.add(topicName);
		driver.findElement(description).sendKeys(descriptionForScript);
		list.add(descriptionForScript);
		driver.findElement(saveButton).click();
		Thread.sleep(1000);
		driver.switchTo().alert().accept();
		return list;
	}
	
	//saved script elements
	public By totalScripts = By.xpath("//*[@id='scripts']//*[@class='scriptRows']");
	public By title  = By.xpath("./td[1]");
	public By createDate  = By.xpath("./td[2]");
	public By lastUpdate  = By.xpath("./td[3]");
	public By skillGroupSkillsAttached   = By.xpath("./td[4]//*[@class='label_round_sm margin-right-5']");
	public By totalSections  = By.xpath("./td[5]");
	public By totalTopics  = By.xpath("./td[6]");
	
	public void verifyCreatedScript(List<String> details){
		List<WebElement> scripts = driver.findElements(totalScripts);
		boolean result = false;
		for(WebElement script : scripts){
			if(details.get(0).equalsIgnoreCase(script.findElement(title).getText())){
				result = true;
				break;
			}
		}
		if(result == true){
			Assert.assertTrue(result);
		}else{
			Assert.assertTrue(result);
		}
	}
}
