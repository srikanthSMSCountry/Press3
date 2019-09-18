package press3AutomationTest;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import press3Initialzers_Processors.storeProfilesData;
import press3Pages.agentPages.AgentHomePage;
import press3Pages.agentPages.Login;
import press3Pages.commonPages.LoginPage;

public class NewTest extends BaseDriver {
	public static String projectDirectory = System.getProperty("user.dir");
	public static ExtentReports testCase;
	public static ExtentTest testSteps;
	public static String userDirectory;
	public String userName = "";
	public String passWord = "";
	public String profileName = "";
	public List<String> onCall = new ArrayList<String>();
	public List<String> hangUp = new ArrayList<String>();
	public static int numberOfCustomers = 10;
	public static int customerCount = 0;
	public String profilesCount;
	public int noOfCallsTakenByProfile;
	final static Logger logger = Logger.getLogger(NewTest.class);
	public static ExtentReports extent;
	public static ExtentTest logger_ss;
	public static Map<String,String> map=null;
	public static Map<String, String> map1 = new LinkedHashMap<String, String>();
	
	
	@Test
	public void verifyAgentFunctionalityForMenu() throws InterruptedException {
		logger_ss = extent.createTest("verifyAgentFunctionalityForMenu", "verifyAgentFunctionalityForMenu");
		Login login = new Login(driver, profileName, userName, passWord);
		AgentHomePage agentPage = new AgentHomePage(driver, profileName, userName, passWord);
		login.loginToProfile();
		DateFormat logindf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date dateobj = new Date();
		dateobj = new Date();
		logger.info("logintime loop for " + profileName + " is::" + logindf.format(dateobj));
		System.out.println("LoggedIn time for" + profileName + "is ::" + logindf.format(dateobj));

		// if (profileName.equalsIgnoreCase("Profile 2")) {
		// agentPage.agentMovingToBreakStatus();
		// boolean notGettingCall = agentPage.verifyAgentGettingAnyCall();
		// Assert.assertFalse(notGettingCall);
		// while (customerCount < (numberOfCustomers / 2)) {
		// Thread.sleep(10000);
		// boolean notGettingCall1 = agentPage.verifyAgentGettingAnyCall();
		// Assert.assertFalse(notGettingCall1);
		// }
		// agentPage.agentMovingToReadyStatus();
		// boolean gettingCall = agentPage.verifyAgentGettingAnyCall();
		// Assert.assertTrue(gettingCall);
		// }
		
		if (profileName.equalsIgnoreCase("Profile 2")) {
			map= handlePostRequestForCallsFor();
			logger_ss.log(Status.INFO, "verifyCalls");
			handlePostRequestForMenuDigits(map);
			logger_ss.log(Status.INFO, "handlePostRequestForMenuDigits");
		}
		Thread.sleep(20000);
		HashMap<String, Integer> profileSummary;
		profileSummary = agentPage.verifyCallsOnAgentPageForMenu(map);
		logger_ss.log(Status.INFO, "verifyCallsOnAgentPage");
		DateFormat logoutdf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date logoutdateobj = new Date();
		logoutdateobj = new Date();
		logger.info("logout time for loop for " + profileName + " is::" + logoutdf.format(logoutdateobj));
		System.out.println("LoggedOut time for" + profileName + "is ::" + logoutdf.format(logoutdateobj));
		Thread.sleep(2000);
		// System.out.println("contact members in " + profileName + " are : " +
		// onCall);

		int rowIndex = Integer.parseInt(profilesCount.split(",")[0]);
		int coloumnIndex = Integer.parseInt(profilesCount.split(",")[1]);
		storeProfilesData.profileData[0][0] = "agentName";
		storeProfilesData.profileData[0][1] = "CallsCount";
		storeProfilesData.profileData[0][2] = "logInTime";
		storeProfilesData.profileData[0][3] = "logOutTime";
		storeProfilesData.profileData[0][4] = "digitPressed 1";
		storeProfilesData.profileData[0][5] = "digitPressed 2";
		storeProfilesData.profileData[rowIndex][0] = userName;
		storeProfilesData.profileData[rowIndex][1] = Integer.toString(profileSummary.get("noOfCustomerHandledByAgent"));
		storeProfilesData.profileData[rowIndex][2] = logindf.format(dateobj);
		storeProfilesData.profileData[rowIndex][3] = logoutdf.format(logoutdateobj);
		storeProfilesData.profileData[rowIndex][4] = Integer.toString(profileSummary.get("digitPressed1"));
		storeProfilesData.profileData[rowIndex][5] = Integer.toString(profileSummary.get("digitPressed2"));

		Thread.sleep(5000);
		//agentPage.agentLogOut();
	}

//	@Test
//	public void verifyAgentFunctionalityWhenAgentIsInBreakStateAndMovedToReadyState() throws InterruptedException {
//		logger_ss = extent.createTest("verifyAgentFunctionalityWhenAgentIsInBreakStateAndMovedToReadyState",
//				"verifyAgentFunctionalityWhenAgentIsInBreakStateAndMovedToReadyState");
//		AgentHomePage agentPage = new AgentHomePage(driver, profileName, userName, passWord);
//		Login login = new Login(driver, profileName, userName, passWord);
//		login.loginToProfile();
//		DateFormat logindf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
//		Date dateobj = new Date();
//		dateobj = new Date();
//		logger.info("logintime loop for " + profileName + " is::" + logindf.format(dateobj));
//		System.out.println("LoggedIn time for" + profileName + "is ::" + logindf.format(dateobj));
//
//		if (profileName.equalsIgnoreCase("Profile 2")) {
//			agentPage.agentMovingToBreakStatus();
//			boolean notGettingCall = agentPage.verifyAgentGettingAnyCall();
//			Assert.assertFalse(notGettingCall);
//			while (customerCount < (numberOfCustomers / 2)) {
//				Thread.sleep(10000);
//				boolean notGettingCall1 = agentPage.verifyAgentGettingAnyCall();
//				Assert.assertFalse(notGettingCall1);
//			}
//			agentPage.agentMovingToReadyStatus();
//			boolean gettingCall = agentPage.verifyAgentGettingAnyCall();
//			Assert.assertTrue(gettingCall);
//		}
//		Thread.sleep(20000);
//		handlePostRequestForCalls();
//		logger_ss.log(Status.INFO, "verifyCalls");
//		int callsCount = agentPage.verifyCallsOnAgentPage();
//		logger_ss.log(Status.INFO, "verifyCallsOnAgentPage");
//		DateFormat logoutdf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
//		Date logoutdateobj = new Date();
//		logoutdateobj = new Date();
//		logger.info("logout time for loop for " + profileName + " is::" + logoutdf.format(logoutdateobj));
//		System.out.println("LoggedOut time for" + profileName + "is ::" + logoutdf.format(logoutdateobj));
//		Thread.sleep(2000);
//		// System.out.println("contact members in " + profileName + " are : " +
//		// onCall);
//
//		int rowIndex = Integer.parseInt(profilesCount.split(",")[0]);
//		int coloumnIndex = Integer.parseInt(profilesCount.split(",")[1]);
//		storeProfilesData.profileData[0][0] = "agentName";
//		storeProfilesData.profileData[0][1] = "CallsCount";
//		storeProfilesData.profileData[0][2] = "logInTime";
//		storeProfilesData.profileData[0][3] = "logOutTime";
//		storeProfilesData.profileData[rowIndex][0] = userName;
//		storeProfilesData.profileData[rowIndex][1] = Integer.toString(callsCount);
//		storeProfilesData.profileData[rowIndex][2] = logindf.format(dateobj);
//		storeProfilesData.profileData[rowIndex][3] = logoutdf.format(logoutdateobj);
//
//		Thread.sleep(5000);
//		agentPage.agentLogOut();
//	}

//	@Test
//	public void verifyAgentFunctionalityWhenAgentIsInBreakState() throws InterruptedException {
//		logger_ss = extent.createTest("verifyAgentFunctionalityWhenAgentIsInBreakState",
//				"verifyAgentFunctionalityWhenAgentIsInBreakState");
//		AgentHomePage agentPage = new AgentHomePage(driver, profileName, userName, passWord);
//		Login login = new Login(driver, profileName, userName, passWord);
//		login.loginToProfile();
//		DateFormat logindf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
//		Date dateobj = new Date();
//		dateobj = new Date();
//		logger.info("logintime loop for " + profileName + " is::" + logindf.format(dateobj));
//		System.out.println("LoggedIn time for" + profileName + "is ::" + logindf.format(dateobj));
//
//		if (profileName.equalsIgnoreCase("Profile 3")) {
//			agentPage.agentMovingToBreakStatus();
//			boolean notGettingCall = agentPage.verifyAgentGettingAnyCall();
//			Assert.assertFalse(notGettingCall);
//			while (customerCount < (numberOfCustomers)) {
//				Thread.sleep(10000);
//				boolean notGettingCall1 = agentPage.verifyAgentGettingAnyCall();
//				Assert.assertFalse(notGettingCall1);
//			}
//			// boolean gettingCall = agentPage.verifyAgentGettingAnyCall();
//			// Assert.assertTrue(gettingCall);
//		}
//		Thread.sleep(20000);
//		handlePostRequestForCalls();
//		logger_ss.log(Status.INFO, "verifyCalls");
//		int callsCount = agentPage.verifyCallsOnAgentPage();
//		logger_ss.log(Status.INFO, "verifyCallsOnAgentPage");
//		DateFormat logoutdf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
//		Date logoutdateobj = new Date();
//		logoutdateobj = new Date();
//		logger.info("logout time for loop for " + profileName + " is::" + logoutdf.format(logoutdateobj));
//		System.out.println("LoggedOut time for" + profileName + "is ::" + logoutdf.format(logoutdateobj));
//		Thread.sleep(2000);
//		// System.out.println("contact members in " + profileName + " are : " +
//		// onCall);
//
//		int rowIndex = Integer.parseInt(profilesCount.split(",")[0]);
//		int coloumnIndex = Integer.parseInt(profilesCount.split(",")[1]);
//		storeProfilesData.profileData[0][0] = "agentName";
//		storeProfilesData.profileData[0][1] = "CallsCount";
//		storeProfilesData.profileData[0][2] = "logInTime";
//		storeProfilesData.profileData[0][3] = "logOutTime";
//		storeProfilesData.profileData[rowIndex][0] = userName;
//		storeProfilesData.profileData[rowIndex][1] = Integer.toString(callsCount);
//		storeProfilesData.profileData[rowIndex][2] = logindf.format(dateobj);
//		storeProfilesData.profileData[rowIndex][3] = logoutdf.format(logoutdateobj);
//
//		Thread.sleep(5000);
//		agentPage.agentLogOut();
//
//	}
//
//	@Test
//	public void verifyAgentFunctionalityWhenAgentIsInWorkAssignedState() throws InterruptedException {
//		logger_ss = extent.createTest("verifyAgentFunctionalityWhenAgentIsInWorkAssignedState",
//				"verifyAgentFunctionalityWhenAgentIsInWorkAssignedState");
//		AgentHomePage agentPage = new AgentHomePage(driver, profileName, userName, passWord);
//		Login login = new Login(driver, profileName, userName, passWord);
//		login.loginToProfile();
//		DateFormat logindf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
//		Date dateobj = new Date();
//		dateobj = new Date();
//		logger.info("logintime loop for " + profileName + " is::" + logindf.format(dateobj));
//		System.out.println("LoggedIn time for" + profileName + "is ::" + logindf.format(dateobj));
//
//		if (profileName.equalsIgnoreCase("Profile 3")) {
//			agentPage.agentMovingToWorkAssignedStatus();
//			boolean notGettingCall = agentPage.verifyAgentGettingAnyCall();
//			Assert.assertFalse(notGettingCall);
//			while (customerCount < (numberOfCustomers)) {
//				Thread.sleep(10000);
//				boolean notGettingCall1 = agentPage.verifyAgentGettingAnyCall();
//				Assert.assertFalse(notGettingCall1);
//			}
//			// boolean gettingCall = agentPage.verifyAgentGettingAnyCall();
//			// Assert.assertTrue(gettingCall);
//		}
//		Thread.sleep(20000);
//		handlePostRequestForCalls();
//		logger_ss.log(Status.INFO, "verifyCalls");
//		int callsCount = agentPage.verifyCallsOnAgentPage();
//		logger_ss.log(Status.INFO, "verifyCallsOnAgentPage");
//		DateFormat logoutdf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
//		Date logoutdateobj = new Date();
//		logoutdateobj = new Date();
//		logger.info("logout time for loop for " + profileName + " is::" + logoutdf.format(logoutdateobj));
//		System.out.println("LoggedOut time for" + profileName + "is ::" + logoutdf.format(logoutdateobj));
//		Thread.sleep(2000);
//		// System.out.println("contact members in " + profileName + " are : " +
//		// onCall);
//
//		int rowIndex = Integer.parseInt(profilesCount.split(",")[0]);
//		int coloumnIndex = Integer.parseInt(profilesCount.split(",")[1]);
//		storeProfilesData.profileData[0][0] = "agentName";
//		storeProfilesData.profileData[0][1] = "CallsCount";
//		storeProfilesData.profileData[0][2] = "logInTime";
//		storeProfilesData.profileData[0][3] = "logOutTime";
//		storeProfilesData.profileData[rowIndex][0] = userName;
//		storeProfilesData.profileData[rowIndex][1] = Integer.toString(callsCount);
//		storeProfilesData.profileData[rowIndex][2] = logindf.format(dateobj);
//		storeProfilesData.profileData[rowIndex][3] = logoutdf.format(logoutdateobj);
//
//		Thread.sleep(5000);
//		agentPage.agentLogOut();
//	}
//
//	@Test
//	public void verifyAgentFunctionalityWhenAgentIsChangedFromWorkAssignedStateToReadyState()
//			throws InterruptedException {
//		logger_ss = extent.createTest("verifyAgentFunctionalityWhenAgentIsChangedFromWorkAssignedStateToReadyState",
//				"verifyAgentFunctionalityWhenAgentIsChangedFromWorkAssignedStateToReadyState");
//		AgentHomePage agentPage = new AgentHomePage(driver, profileName, userName, passWord);
//		Login login = new Login(driver, profileName, userName, passWord);
//		login.loginToProfile();
//		DateFormat logindf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
//		Date dateobj = new Date();
//		dateobj = new Date();
//		logger.info("logintime loop for " + profileName + " is::" + logindf.format(dateobj));
//		System.out.println("LoggedIn time for" + profileName + "is ::" + logindf.format(dateobj));
//
//		if (profileName.equalsIgnoreCase("Profile 2")) {
//			agentPage.agentMovingToWorkAssignedStatus();
//			boolean notGettingCall = agentPage.verifyAgentGettingAnyCall();
//			Assert.assertFalse(notGettingCall);
//			while (customerCount < (numberOfCustomers / 2)) {
//				Thread.sleep(10000);
//				boolean notGettingCall1 = agentPage.verifyAgentGettingAnyCall();
//				Assert.assertFalse(notGettingCall1);
//			}
//			agentPage.agentMovingToReadyStatus();
//			boolean gettingCall = agentPage.verifyAgentGettingAnyCall();
//			Assert.assertTrue(gettingCall);
//		}
//		Thread.sleep(20000);
//		handlePostRequestForCalls();
//		logger_ss.log(Status.INFO, "verifyCalls");
//		int callsCount = agentPage.verifyCallsOnAgentPage();
//		logger_ss.log(Status.INFO, "verifyCallsOnAgentPage");
//		DateFormat logoutdf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
//		Date logoutdateobj = new Date();
//		logoutdateobj = new Date();
//		logger.info("logout time for loop for " + profileName + " is::" + logoutdf.format(logoutdateobj));
//		System.out.println("LoggedOut time for" + profileName + "is ::" + logoutdf.format(logoutdateobj));
//		Thread.sleep(2000);
//		// System.out.println("contact members in " + profileName + " are : " +
//		// onCall);
//
//		int rowIndex = Integer.parseInt(profilesCount.split(",")[0]);
//		int coloumnIndex = Integer.parseInt(profilesCount.split(",")[1]);
//		storeProfilesData.profileData[0][0] = "agentName";
//		storeProfilesData.profileData[0][1] = "CallsCount";
//		storeProfilesData.profileData[0][2] = "logInTime";
//		storeProfilesData.profileData[0][3] = "logOutTime";
//		storeProfilesData.profileData[rowIndex][0] = userName;
//		storeProfilesData.profileData[rowIndex][1] = Integer.toString(callsCount);
//		storeProfilesData.profileData[rowIndex][2] = logindf.format(dateobj);
//		storeProfilesData.profileData[rowIndex][3] = logoutdf.format(logoutdateobj);
//
//		Thread.sleep(5000);
//		agentPage.agentLogOut();
//
//	}
//
//	// @Test
//	// public void
//	// verifyAgentFunctionalityWhenAgentIsChangedFromInBreakStateToReadyState()
//	// throws InterruptedException {
//	// AgentHomePage agentPage = new AgentHomePage(driver, profileName,
//	// userName, passWord);
//	// agentPage.agentMovingToBreakStatus();
//	// boolean gettingCall = agentPage.verifyAgentGettingAnyCall();
//	// Assert.assertFalse(gettingCall);
//	// agentPage.agentMovingToReadyStatus();
//	// }
//
//	@Parameters({ "username", "password", "profilename", "profilesCount" })
//	@BeforeTest
//	public void browserInitialize(String username, String password, String profilename, String profilesCount) throws InterruptedException {
//		this.profilesCount = profilesCount;
//		this.userName = username;
//		this.passWord = password;
//		this.profileName = profilename;
//		ExtentHtmlReporter reporter = new ExtentHtmlReporter(profileName+"_Report.html");
//		testCase = new ExtentReports();
//		testCase.attachReporter(reporter);
//		testSteps = testCase.createTest("Press3 TestCases");
//		System.out.println("browser started");
//		
//
//		ChromeDriverService chSvc = new ChromeDriverService.Builder()
//				.usingDriverExecutable(new File("D:\\press3Automation\\Drivers\\chromedriver.exe")).usingAnyFreePort()
//				.build();
//		ChromeOptions options = new ChromeOptions();
//		options.addArguments(
//				"user-data-dir=C:\\Users\\smsc\\AppData\\Local\\Google\\Chrome\\User Data\\" + profilename);
//		options.addArguments("--start-maximized");
//		driver = new ChromeDriver(chSvc, options);
//
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		driver.manage().window().maximize();
//		testSteps.log(Status.INFO, "Browser Launched");
//
//		driver.get("http://qa.press3.com/");
//		testSteps.log(Status.INFO, "press3 home page opend");
//
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//	}
//
//	@BeforeSuite()
//	public void clearStaticVariables() {
//		customerCount = 0;
//		storeProfilesData.profileData = new String[100][100];
//		map=null;
//		map1=new LinkedHashMap<String, String>();
//		
//	}
//
//	@AfterMethod
//	public void tearDown(ITestResult result) throws IOException, InterruptedException {
//		if (result.getStatus() == ITestResult.FAILURE) {
//			System.out.println("TestCase failed");
//			String temp = getScreenshot();
//			logger_ss.fail(result.getThrowable().getMessage(),
//					MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
//		} else {
//			System.out.println("TestCase passed");
//		}
//		extent.flush();
//	}
//
//	public String getScreenshot() {
//		TakesScreenshot ts = (TakesScreenshot) driver;
//		File src = ts.getScreenshotAs(OutputType.FILE);
//		String path = System.getProperty("user.dir") + "/Screenshot/" + System.currentTimeMillis() + ".png";
//		File destination = new File(path);
//		try {
//			FileUtils.copyFile(src, destination);
//		} catch (IOException e) {
//			System.out.println("Capture Failed " + e.getMessage());
//		}
//		return path;
//	}
//
//	@Test
//	public void waitMethod() throws InterruptedException {
//		if (profileName.equalsIgnoreCase("Profile 2") || profileName.equalsIgnoreCase("Profile 3")
//				|| profileName.equalsIgnoreCase("Profile 4") || profileName.equalsIgnoreCase("Profile 5")
//				|| profileName.equalsIgnoreCase("Profile 6")) {
//			Thread.sleep(8000);
//		}
//	}

	@BeforeClass
	public void beforeCalss() {
		ExtentHtmlReporter reporter = new ExtentHtmlReporter(profileName + ".html");
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		logger_ss = extent.createTest("agent");
		logger_ss.log(Status.INFO, "agentTest");
	}
	
//	@Test
//	public void verifyCallFunctionalityOnAgnetPage() throws InterruptedException {
//		logger_ss = extent.createTest("verifyCallFunctionalityOnAgnetPage", "verifyCallFunctionalityOnAgnetPage");
//
//		AgentHomePage agentPage = new AgentHomePage(driver, profileName, userName, passWord);
//		Login login = new Login(driver, profileName, userName, passWord);
//		login.loginToProfile();
//		DateFormat logindf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
//		Date dateobj = new Date();
//		dateobj = new Date();
//		logger.info("logintime loop for " + profileName + " is::" + logindf.format(dateobj));
//		System.out.println("LoggedIn time for" + profileName + "is ::" + logindf.format(dateobj));
//
//		handlePostRequestForCalls();
//		logger_ss.log(Status.INFO, "verifyCalls");
//		int callsCount = agentPage.verifyCallsOnAgentPage();
//		logger_ss.log(Status.INFO, "verifyCallsOnAgentPage");
//		DateFormat logoutdf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
//		Date logoutdateobj = new Date();
//		logoutdateobj = new Date();
//		logger.info("logout time for loop for " + profileName + " is::" + logoutdf.format(logoutdateobj));
//		System.out.println("LoggedOut time for" + profileName + "is ::" + logoutdf.format(logoutdateobj));
//		Thread.sleep(2000);
//		// System.out.println("contact members in " + profileName + " are : " +
//		// onCall);
//
//		int rowIndex = Integer.parseInt(profilesCount.split(",")[0]);
//		int coloumnIndex = Integer.parseInt(profilesCount.split(",")[1]);
//		storeProfilesData.profileData[0][0] = "agentName";
//		storeProfilesData.profileData[0][1] = "CallsCount";
//		storeProfilesData.profileData[0][2] = "logInTime";
//		storeProfilesData.profileData[0][3] = "logOutTime";
//		storeProfilesData.profileData[rowIndex][0] = userName;
//		storeProfilesData.profileData[rowIndex][1] = Integer.toString(callsCount);
//		storeProfilesData.profileData[rowIndex][2] = logindf.format(dateobj);
//		storeProfilesData.profileData[rowIndex][3] = logoutdf.format(logoutdateobj);
//
//		Thread.sleep(5000);
//		//agentPage.agentLogOut();
//
//	}

	@Test
	public void loginlogout() throws InterruptedException {
		AgentHomePage agentPage = new AgentHomePage(driver, profileName, userName, passWord);
		Login login = new Login(driver, profileName, userName, passWord);
		login.loginToProfile();
		agentPage.agentLogOut();
	}

	@AfterTest
	public void quiteBrowser() {
		// customerCount= 0;
		//driver.quit();
	}

	@Test
	public void allMembers() throws InterruptedException {
		System.out.println("all contact members in " + profileName + " are : " + onCall);
	}

	public void handlePostRequestForCalls() throws InterruptedException {
		if (profileName.equalsIgnoreCase("Profile 2")) {

			RestAssured.baseURI = "http://192.168.72.155:8888/v0.1/";
			RequestSpecification request = RestAssured.given();
			long number = 9666361000l;
			String fromNumber = "";
			for (int i = 0; i < numberOfCustomers; i++) {
				if (i != numberOfCustomers - 1)
					fromNumber = fromNumber + (number + i) + ",";
				else
					fromNumber = fromNumber + (number + i);
			}
			String sourcekey = "{\"source\":\"Press3\",\"fromNumber\":\"" + fromNumber
					+ "\",\"toNumber\":\"04023008610\"}";
			request.body(sourcekey.toString());
			Response response = request.post("Mock/");
			System.out.println("response for post resquest is ::" + response.getStatusCode());
			Thread.sleep(3000);
		}
	}

	public Map<String, String> handlePostRequestForCallsForMenu() throws InterruptedException {
		Map<String, String> map = new LinkedHashMap<String, String>();
		if (profileName.equalsIgnoreCase("Profile 2")) {
			RestAssured.baseURI = "http://192.168.75.75:8888/v0.1/";
			RequestSpecification request = RestAssured.given();
			long number = 9666361000l;
			String fromNumber = "";
			String keys = "12";
			Random r = new Random();
			for (int i = 0; i < numberOfCustomers; i++) {
				int randomKey = r.nextInt(keys.length());
				if (i != numberOfCustomers - 1)
					fromNumber = fromNumber + (number + i) + ",";
				else
					fromNumber = fromNumber + (number + i);
				map.put((number + i)+"", keys.charAt(randomKey) + "");
			}
			String sourcekey = "{\"source\":\"Press3\",\"fromNumber\":\"" + fromNumber
					+ "\",\"toNumber\":\"04023008623\"}";
			request.body(sourcekey.toString());
			Response response = request.post("Mock/");
			System.out.println("response for post resquest for calls is :: " + response.getStatusCode());
			Thread.sleep(3000);
		}
		return map;
	}
	
	
	
	public Map<String, String> handlePostRequestForCallsFor() throws InterruptedException {
		
		if (profileName.equalsIgnoreCase("Profile 2")) {
			RestAssured.baseURI = "http://192.168.75.75:8888/v0.1/";
			RequestSpecification request = RestAssured.given();
			long number = 9666361000l;
			String fromNumber = "";
			//String keys = "12";
			//Random r = new Random();
			int k=1;
			for (int i = 0; i < numberOfCustomers; i++) {
				//int randomKey = r.nextInt(keys.length());
				if (i != numberOfCustomers - 1)
					fromNumber = fromNumber + (number + i) + ",";
				else
					fromNumber = fromNumber + (number + i);
				map1.put((number + i)+"", k + "");
				if(k<2){
					k++;
				}
				else{
					k=1;
				}
			}
			
			System.out.println(Collections.frequency(map1.values(), 1)+" Pressed 1");
			System.out.println(Collections.frequency(map1.values(), 2)+" Pressed 2");
			
			String sourcekey = "{\"source\":\"Press3\",\"fromNumber\":\"" + fromNumber
					+ "\",\"toNumber\":\"04023008623\"}";
			request.body(sourcekey.toString());
			Response response = request.post("Mock/");
			System.out.println("response for post resquest for calls is :: " + response.getStatusCode());
			Thread.sleep(3000);
		}
		return map1;
	}
	
	

	public void handlePostRequestForMenuDigits(Map<String,String> map) throws InterruptedException {
		if (profileName.equalsIgnoreCase("Profile 2")) {
			RestAssured.baseURI = "http://192.168.75.75:8888/v0.1/";
			RequestSpecification request = RestAssured.given();
			Set<String> phoneNumbers = map.keySet();
			String sourcekey = "[";
			int keyCount = 0;
			for (String phoneNumber : phoneNumbers) {
				String digit = map.get(phoneNumber);
				if (keyCount != phoneNumbers.size() - 1)
					sourcekey = sourcekey + "{\"fromNumber\":" + phoneNumber + ",\"digitPress\":\"" + digit + "\"},";
				else
					sourcekey = sourcekey + "{\"fromNumber\":" + phoneNumber + ",\"digitPress\":\"" + digit + "\"}";
				keyCount++;
			}
			sourcekey = sourcekey + "]";
			request.body(sourcekey.toString());
			Response response = request.post("Mock/");
			System.out.println("response: " + sourcekey);
			System.out.println("response for post resquest for menu is :: " + response.getStatusCode());
			Thread.sleep(3000);
		}
	}
	
	
}
