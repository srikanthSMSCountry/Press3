package press3Pages.agentPages;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import press3AutomationTest.NewTest;

public class Login{
	public WebDriver driver;
	public String profileName;
	public String userName;
	public String passWord;
	final static Logger logger = Logger.getLogger(AgentHomePage.class);
	public Map<String,Integer> counterMap=new HashMap<String,Integer>();
	
	public Login(WebDriver driver,String profileName,String userName, String passWord){
		this.driver= driver;
		this.profileName=profileName;
		this.userName=userName;
		this.passWord = passWord;
	}
	
	public By loginButton = By.id("btnLogin");
	public By invisibility = By.id("btnOk");
	public By userNameField = By.id("txtName");
	public By passWordField = By.id("txtPassword");
	public By logOutOnOverlay = By.xpath("//*[@id='myModal']//*[@value='Log Out']");

	public void loginToProfile() throws InterruptedException {
		System.out.println("Browser profile is::" + profileName +" and username is: "+userName);
		driver.findElement(userNameField).sendKeys(userName);
		driver.findElement(passWordField).sendKeys(passWord);
		clickLoginButton();
		Thread.sleep(1000);
		if (isAlertPresent()) {
			handleAlert();
		}
	}
		
		
		public void loginToManagerProfile() throws InterruptedException {
		System.out.println("Browser profile is::" + profileName +" and username is: "+userName);
		driver.findElement(userNameField).sendKeys(userName);
		driver.findElement(passWordField).sendKeys(passWord);
		clickLoginButton();
		Thread.sleep(2000);
		if (isAlertPresent()) {
			handleAlert();
		}
		Thread.sleep(2000);
	}
		
		public void loginToNewAgentProfile(WebDriver driver) throws InterruptedException {
		WebDriver temp=this.driver;
		this.driver=driver;
		System.out.println("Browser profile is::" + profileName +" and username is: "+userName);
		driver.findElement(userNameField).sendKeys("automationagent2@press3.com");
		driver.findElement(passWordField).sendKeys("Press3@123");
		clickLoginButton();
		Thread.sleep(2000);
		if (isAlertPresent()) {
			handleAlert();
		}
		Thread.sleep(2000);
		this.driver=temp;
	}
		public void loginToCampaignAgent(WebDriver driver) throws InterruptedException {
		WebDriver temp=this.driver;
		this.driver=driver;
		System.out.println("Browser profile is::" + profileName +" and username is: "+userName);
		driver.findElement(userNameField).sendKeys("srikanthagent@gmail.com");
		driver.findElement(passWordField).sendKeys("Rsneha@1");
		clickLoginButton();
		Thread.sleep(2000);
		if (isAlertPresent()) {
			handleAlert();
		}
		Thread.sleep(2000);
		this.driver=temp;
	}
	
	public void clickLoginButton() throws InterruptedException {
		Thread.sleep(2000);
//		driver.findElement(loginButton).click();
		WebElement element = driver.findElement(loginButton);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
		Thread.sleep(2000);
	}

	public boolean isAlertPresent() throws InterruptedException {
		boolean foundAlert = false;
		WebDriverWait wait = new WebDriverWait(driver, 3);
		try {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(invisibility));
			foundAlert = false;
			System.out.println("alert not present");
		} catch (TimeoutException eTO) {
			System.out.println("alert present");
			foundAlert = true;
		}
		return foundAlert;
	}
	
	public boolean isUnexpectedAlertPresent() throws InterruptedException {
		boolean foundAlert = false;
		
		try {
			WebDriverWait wait = new WebDriverWait(driver, 2);
			wait.until(ExpectedConditions.alertIsPresent());
			foundAlert = true;
			System.out.println("alert present");
		} catch (TimeoutException eTO) {
			System.out.println("alert not present");
			foundAlert = false;
		}
		return foundAlert;
	}
	
	public void handleUnexpectedAlert() throws InterruptedException{
		if (isUnexpectedAlertPresent()) {
			driver.switchTo().alert().accept();
		}
	}

	public void handleAlert() throws InterruptedException {
		WebElement element = driver.findElement(By.id("btnOk"));
		JavascriptExecutor executor1 = (JavascriptExecutor)driver;
		executor1.executeScript("arguments[0].click();", element);
		Thread.sleep(2000);
	}
}
