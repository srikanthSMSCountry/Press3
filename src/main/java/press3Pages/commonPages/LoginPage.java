package press3Pages.commonPages;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import press3Pages.agentPages.AgentHomePage;

public class LoginPage {
	
	
	public WebDriver driver;
	public String profileName;
	public String userName;
	public String passWord;
	final static Logger logger = Logger.getLogger(AgentHomePage.class);
	public Map<String, Integer> counterMap = new HashMap<String, Integer>();

	public LoginPage(WebDriver driver, String profileName, String userName, String passWord) {
		this.driver = driver;
		this.profileName = profileName;
		this.userName = userName;
		this.passWord = passWord;
	}
	
	public By loginButton = By.id("btnLogin");
	public By invisibility = By.id("btnOk");
	public By userNameField = By.id("txtName");
	public By passWordField = By.id("txtPassword");
	
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
	
	public void handleAlert() throws InterruptedException {
		
		WebElement element = driver.findElement(By.id("btnOk"));
		JavascriptExecutor executor1 = (JavascriptExecutor)driver;
		executor1.executeScript("arguments[0].click();", element);
		Thread.sleep(2000);
	}
	
	public void loginToProfile(String userName,String password) throws InterruptedException {
		//System.out.println("Browser profile is::" + profileName +" and username is: "+userName);
		driver.findElement(userNameField).sendKeys(userName);
		driver.findElement(passWordField).sendKeys(password);
		clickLoginButton();
	
		Thread.sleep(2000);
		if (isAlertPresent()) {
			handleAlert();
		}
		Thread.sleep(5000);
	}
	
	public void loginToExistingProfile() throws InterruptedException {
		//System.out.println("Browser profile is::" + profileName +" and username is: "+userName);
		driver.findElement(userNameField).sendKeys("email1569226796728@gmail.com");
		driver.findElement(passWordField).sendKeys("Password123");
		clickLoginButton();
	
		Thread.sleep(2000);
		if (isAlertPresent()) {
			handleAlert();
		}
		Thread.sleep(5000);
	}
	
	public void clickLoginButton() throws InterruptedException {
		Thread.sleep(2000);
//		driver.findElement(loginButton).click();
		WebElement element = driver.findElement(loginButton);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
		Thread.sleep(2000);
	}
	
	public By agentProfileName = By.id("spanAgentName");
	public By logOut = By.id("logout_modal");
	public By logOutOnOverlay = By.xpath("//*[@id='myModal']//*[@value='Log Out']");
	
	public void logOutProfile() throws InterruptedException {
		Thread.sleep(1000);
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(agentProfileName)).perform();
		// driver.findElement(logOut).click();
		Thread.sleep(2000);
		WebElement element = driver.findElement(logOut);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		Thread.sleep(2000);
		// driver.findElement(logOutOnOverlay).click();
		WebElement element1 = driver.findElement(logOutOnOverlay);
		JavascriptExecutor executor1 = (JavascriptExecutor) driver;
		executor1.executeScript("arguments[0].click();", element1);
		Thread.sleep(2000);
	}

}
