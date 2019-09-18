package press3Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import press3AutomationTest.NewTest;
import press3Initialzers_Processors.storeProfilesData;

public class BrowserFunctions {
	
	public WebDriver driver;
	public static String projectDirectory = System.getProperty("user.dir");
	public ExtentReports testCase;
	public ExtentTest testSteps;
	public static String userDirectory;
	public String userName = "";
	public String passWord = "";
	public String profileName = "";
	public List<String> onCall = new ArrayList<String>();
	public List<String> hangUp = new ArrayList<String>();
	public static int numberOfCustomers = 50;
	public static int customerCount = 0;
	public String profilesCount;
	public int noOfCallsTakenByProfile;
	final static Logger logger = Logger.getLogger(NewTest.class);
	//public static ExtentReports extent;
	//public static ExtentTest logger_ss;
	public static Map<String,String> map=null;
	public static Map<String, String> map1 = new LinkedHashMap<String, String>();
	
	@Parameters({ "username", "password", "profilename", "profilesCount" })
	@BeforeTest
	public void browserInitialize(String username, String password, String profilename, String profilesCount) throws InterruptedException {
		this.profilesCount = profilesCount;
		this.userName = username;
		this.passWord = password;
		this.profileName = profilename;
		ExtentHtmlReporter reporter = new ExtentHtmlReporter(profileName+"_"+System.currentTimeMillis()+".html");
		testCase = new ExtentReports();
		testCase.attachReporter(reporter);
		testSteps = testCase.createTest("Press3 TestCases");
		System.out.println("browser started");
		
		ChromeDriverService chSvc = new ChromeDriverService.Builder()
				.usingDriverExecutable(new File("D:\\press3Automation\\Drivers\\chromedriver.exe")).usingAnyFreePort()
				.build();
		ChromeOptions options = new ChromeOptions();
		options.addArguments(
				"user-data-dir=C:\\Users\\smsc\\AppData\\Local\\Google\\Chrome\\User Data\\" + profilename);
		options.addArguments("--start-maximized");
		driver = new ChromeDriver(chSvc, options);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		testSteps.log(Status.INFO, "Browser Launched");

		driver.get("http://qa.press3.com/");
		testSteps.log(Status.INFO, "press3 home page opend");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@BeforeSuite()
	public void clearStaticVariables() {
		customerCount = 0;
		storeProfilesData.profileData = new String[100][100];
		map=null;
		map1=new LinkedHashMap<String, String>();
		
	}
//	@BeforeClass
//	public void beforeCalss() {
//		ExtentHtmlReporter reporter = new ExtentHtmlReporter(profileName+System.currentTimeMillis()+".html");
//		testCase = new ExtentReports();
//		testCase.attachReporter(reporter);
//		testSteps = testCase.createTest("agent");
//		testSteps.log(Status.INFO, "agentTest");
//	}
	
	@AfterTest
	public void quiteBrowser() {
		// customerCount= 0;
		//driver.quit();
	}

	@AfterMethod
	public void tearDown(ITestResult result) throws IOException, InterruptedException {
		if (result.getStatus() == ITestResult.FAILURE) {
			System.out.println("TestCase failed");
			String temp = getScreenshot();
			//testSteps.log(Status.INFO, "TestCase failed");
			testSteps.fail(result.getThrowable().getMessage(),
					MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		} else {
			System.out.println("TestCase passed");
		}
		testCase.flush();
	}

	public String getScreenshot() {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/Screenshot/" + profileName+System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			System.out.println("Capture Failed " + e.getMessage());
		}
		return path;
	}
	public WebDriver openNewBrowser(){
		System.setProperty("webdriver.chrome.driver","D:\\press3Automation\\Drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://mock.press3.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
	
	public WebDriver getDriver(){
		
		return driver;
	}

}
