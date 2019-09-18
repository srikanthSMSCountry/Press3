package press3Pages.agentPages;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ManagerPage {
	public WebDriver driver;
	public String profileName;
	public String userName;
	public String passWord;
	final static Logger logger = Logger.getLogger(AgentHomePage.class);
	public ManagerPage(WebDriver driver,String profileName,String userName, String passWord){
		this.driver= driver;
		this.profileName=profileName;
		this.userName=userName;
		this.passWord = passWord;
	}
	
	public By userNameField = By.id("txtName");
	public By passWordField = By.id("txtPassword");
	public By loginButton = By.id("btnLogin");
	public By invisibility = By.id("btnOk");
	
	public void loginToManagerProfile() throws InterruptedException {
		System.out.println("Browser profile is::" + profileName +" and username is: "+userName);
		driver.findElement(userNameField).sendKeys("srikanthm@press3.com");
		driver.findElement(passWordField).sendKeys("Press3@123");
		clickLoginButton();
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date dateobj = new Date();
		dateobj = new Date();
		System.out.println("LoggedIn time for" + profileName + "is ::" + df.format(dateobj));
		Thread.sleep(2000);
		if (isAlertPresent()) {
			handleAlert();
		}
		Thread.sleep(5000);
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
	public void clickLoginButton() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(loginButton).click();
	}
	public void handleAlert() throws InterruptedException {
		driver.findElement(By.id("btnOk")).click();
		Thread.sleep(2000);
	}

}
