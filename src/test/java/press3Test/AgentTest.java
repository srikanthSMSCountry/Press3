package press3Test;

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
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import press3AutomationTest.NewTest;
import press3Initialzers_Processors.ExcelReadAndWriteData;
import press3Initialzers_Processors.storeProfilesData;
import press3Pages.ManagerPages.AgentAndSkills;
import press3Pages.ManagerPages.CreateCampaign;
import press3Pages.ManagerPages.ManagerDashboard;
import press3Pages.agentPages.AgentHomePage;
import press3Pages.agentPages.CallBackRequestsPage;
import press3Pages.agentPages.CallHistoryPage;
import press3Pages.agentPages.CrmPage;
import press3Pages.agentPages.Login;
import press3Pages.agentPages.TicketHistoryPage;

public class AgentTest extends BrowserFunctions {

	public static String projectDirectory = System.getProperty("user.dir");
	// public static ExtentReports testCase;
	// public static ExtentTest testSteps;
	public static String userDirectory;
	public List<String> onCall = new ArrayList<String>();
	public List<String> hangUp = new ArrayList<String>();
	public static int numberOfCustomers = 10;
	public static int oneCustomer = 1;
	public static int customerCount = 0;
	public static boolean writeExcel = false;
	public int noOfCallsTakenByProfile;
	final static Logger logger = Logger.getLogger(NewTest.class);
	public static Map<String, String> map = null;
	public static Map<String, String> map1 = new LinkedHashMap<String, String>();

	// AgentHomePage agentHome = new AgentHomePage(driver, profileName,
	// userName, passWord);
	// CrmPage crmPage = new CrmPage(driver, profileName, userName, passWord);
	// CallHistoryPage callHistory = new CallHistoryPage();
	// Login login = new Login(driver, profileName, userName, passWord);

	// -------------------------------------------------------------InBound
	// Calls

	// InBound Calls
	@Test
	public void verifyInBoundCallFunctionalityOfAnAgnet() throws InterruptedException, IOException {
		testSteps = testCase.createTest("verifyInBoundCallFunctionalityOfAnAgnet",
				"verifyInBoundCallFunctionalityOfAnAgnet");
		Login login1 = new Login(driver, profileName, userName, passWord);
		AgentHomePage agentPage = new AgentHomePage(driver, profileName, userName, passWord);

		testSteps.log(Status.INFO, "loginToProfile");
		login1.loginToProfile();
		testSteps.log(Status.INFO, "isAgentReadyStateIsDisplayed");
		agentPage.isAgentReadyStateIsDisplayed();

		DateFormat logindf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date dateobj = new Date();
		logger.info("logintime loop for " + profileName + " is::" + logindf.format(dateobj));
		System.out.println("LoggedIn time for" + profileName + "is ::" + logindf.format(dateobj));

		testSteps.log(Status.INFO, "generateInBoundJson");
		generateInBoundJson(numberOfCustomers);

		testSteps.log(Status.INFO, "verifyInBoundCallsOnAgentPage");
		ArrayList callsCount = agentPage.verifyInBoundCallsOnAgentPage();

		DateFormat logoutdf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date logoutdateobj = new Date();
		logger.info("logout time for loop for " + profileName + " is::" + logoutdf.format(logoutdateobj));
		System.out.println("LoggedOut time for" + profileName + "is ::" + logoutdf.format(logoutdateobj));
		Thread.sleep(2000);

		testSteps.log(Status.INFO, "Add all profiles call data to array");
		int rowIndex = Integer.parseInt(profilesCount.split(",")[0]);
		int coloumnIndex = Integer.parseInt(profilesCount.split(",")[1]);
		storeProfilesData.profileData[0][0] = "agentName";
		storeProfilesData.profileData[0][1] = "CallsCount";
		storeProfilesData.profileData[0][2] = "logInTime";
		storeProfilesData.profileData[0][3] = "logOutTime";
		storeProfilesData.profileData[rowIndex][0] = userName;
		if (callsCount.size() == 2)
			storeProfilesData.profileData[rowIndex][1] = Integer.toString((Integer) callsCount.get(1));
		else
			storeProfilesData.profileData[rowIndex][1] = Integer.toString((Integer) callsCount.get(0));
		storeProfilesData.profileData[rowIndex][2] = logindf.format(dateobj);
		storeProfilesData.profileData[rowIndex][3] = logoutdf.format(logoutdateobj);
		ExcelReadAndWriteData excelData = new ExcelReadAndWriteData();
		if (callsCount.size() == 2) {
			testSteps.log(Status.INFO, "write data in excel");
			excelData.testSheet();
		}

		Thread.sleep(2000);
		testSteps.log(Status.INFO, "agentLogOut");
		agentPage.agentLogOut();
	}
	
	// InBound Calls
	@Test
	public void verifyInBoundCallFunctionalityWhenAgentHangUpFromHisEnd() throws InterruptedException, IOException {
		testSteps = testCase.createTest("verifyInBoundCallFunctionalityWhenAgentHangUpFromHisEnd",
				"verifyInBoundCallFunctionalityWhenAgentHangUpFromHisEnd");
		Login login1 = new Login(driver, profileName, userName, passWord);
		AgentHomePage agentPage = new AgentHomePage(driver, profileName, userName, passWord);

		testSteps.log(Status.INFO, "loginToProfile");
		login1.loginToProfile();
		testSteps.log(Status.INFO, "isAgentReadyStateIsDisplayed");
		agentPage.isAgentReadyStateIsDisplayed();

		DateFormat logindf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date dateobj = new Date();
		logger.info("logintime loop for " + profileName + " is::" + logindf.format(dateobj));
		System.out.println("LoggedIn time for" + profileName + "is ::" + logindf.format(dateobj));

		testSteps.log(Status.INFO, "generateInBoundJson");
		generateInBoundJson(oneCustomer);

		testSteps.log(Status.INFO, "verifyInBoundCallsOnAgentPage");
		agentPage.verifyHangUpCallFromAgentPage();

		Thread.sleep(2000);
		testSteps.log(Status.INFO, "agentLogOut");
		agentPage.agentLogOut();
	}

	// InBound Calls
	@Test
	public void verifyAllFieldsAreGettingDisplayedOnAgentPageForInBoundCall() throws InterruptedException, IOException {
		testSteps = testCase.createTest("verifyAllFieldsAreGettingDisplayedOnAgentPageForInBoundCall",
				"verifyAllFieldsAreGettingDisplayedOnAgentPageForInBoundCall");
		Login login1 = new Login(driver, profileName, userName, passWord);
		AgentHomePage agentPage = new AgentHomePage(driver, profileName, userName, passWord);

		testSteps.log(Status.INFO, "loginToProfile");
		login1.loginToProfile();
		testSteps.log(Status.INFO, "isAgentReadyStateIsDisplayed");
		agentPage.isAgentReadyStateIsDisplayed();

		DateFormat logindf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date dateobj = new Date();
		logger.info("logintime loop for " + profileName + " is::" + logindf.format(dateobj));
		System.out.println("LoggedIn time for" + profileName + "is ::" + logindf.format(dateobj));

		testSteps.log(Status.INFO, "generateInBoundJson");
		generateInBoundJson(numberOfCustomers);

		testSteps.log(Status.INFO, "verifyFieldsOnAgentPageWhenAgentHaveInBoundCalls");
		ArrayList callsCount = agentPage.verifyFieldsOnAgentPageWhenAgentHaveInBoundCalls();

		DateFormat logoutdf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date logoutdateobj = new Date();
		logger.info("logout time for loop for " + profileName + " is::" + logoutdf.format(logoutdateobj));
		System.out.println("LoggedOut time for" + profileName + "is ::" + logoutdf.format(logoutdateobj));
		Thread.sleep(2000);

		testSteps.log(Status.INFO, "Add all profiles call data to array");
		int rowIndex = Integer.parseInt(profilesCount.split(",")[0]);
		int coloumnIndex = Integer.parseInt(profilesCount.split(",")[1]);
		storeProfilesData.profileData[0][0] = "agentName";
		storeProfilesData.profileData[0][1] = "CallsCount";
		storeProfilesData.profileData[0][2] = "logInTime";
		storeProfilesData.profileData[0][3] = "logOutTime";
		storeProfilesData.profileData[rowIndex][0] = userName;
		if (callsCount.size() == 2)
			storeProfilesData.profileData[rowIndex][1] = Integer.toString((Integer) callsCount.get(1));
		else
			storeProfilesData.profileData[rowIndex][1] = Integer.toString((Integer) callsCount.get(0));
		storeProfilesData.profileData[rowIndex][2] = logindf.format(dateobj);
		storeProfilesData.profileData[rowIndex][3] = logoutdf.format(logoutdateobj);
		ExcelReadAndWriteData excelData = new ExcelReadAndWriteData();
		if (callsCount.size() == 2) {
			testSteps.log(Status.INFO, "Write data in excel");
			excelData.testSheet();
		}

		Thread.sleep(2000);
		testSteps.log(Status.INFO, "agentLogOut");
		agentPage.agentLogOut();
	}

	// InBound Calls
	@Test
	public void verifyEditCustomerDetailsOnAgentPageForInBoundCall()
			throws InterruptedException, IOException {
		testSteps = testCase.createTest("verifyEditCustomerDetailsOnAgentPageForInBoundCall",
				"verifyEditCustomerDetailsOnAgentPageForInBoundCall");
		Login login1 = new Login(driver, profileName, userName, passWord);
		AgentHomePage agentPage = new AgentHomePage(driver, profileName, userName, passWord);

		testSteps.log(Status.INFO, "loginToProfile");
		login1.loginToProfile();
		testSteps.log(Status.INFO, "isAgentReadyStateIsDisplayed");
		agentPage.isAgentReadyStateIsDisplayed();

		DateFormat logindf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date dateobj = new Date();
		logger.info("logintime loop for " + profileName + " is::" + logindf.format(dateobj));
		System.out.println("LoggedIn time for" + profileName + "is ::" + logindf.format(dateobj));

		testSteps.log(Status.INFO, "generateInBoundJson");
		generateInBoundJson(oneCustomer);

		testSteps.log(Status.INFO, "verifyFieldsOnAgentPageWhenAgentHaveInBoundCalls");
		agentPage.editCustomerDetailsOnAgentPageWhenAgentHaveInBoundCalls();

		Thread.sleep(2000);
		testSteps.log(Status.INFO, "agentLogOut");
		agentPage.agentLogOut();
	}

	// InBound Calls
	@Test
	public void verifyInBoundCallsAregettingRefelectedInCallHistory() throws InterruptedException, IOException {
		testSteps = testCase.createTest("verifyInBoundCallsAregettingRefelectedInCallHistory",
				"verifyInBoundCallsAregettingRefelectedInCallHistory");
		Login login1 = new Login(driver, profileName, userName, passWord);
		AgentHomePage agentPage = new AgentHomePage(driver, profileName, userName, passWord);
		CallHistoryPage callHistory = new CallHistoryPage(driver, profileName, userName, passWord);

		testSteps.log(Status.INFO, "loginToProfile");
		login1.loginToProfile();

		testSteps.log(Status.INFO, "isAgentReadyStateIsDisplayed");
		agentPage.isAgentReadyStateIsDisplayed();

		DateFormat logindf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date dateobj = new Date();
		logger.info("logintime loop for " + profileName + " is::" + logindf.format(dateobj));
		System.out.println("LoggedIn time for" + profileName + "is ::" + logindf.format(dateobj));

		testSteps.log(Status.INFO, "generateInBoundJson");
		generateInBoundJson(oneCustomer);

		testSteps.log(Status.INFO, "verifyFieldsOnAgentPageWhenAgentHaveInBoundCalls");
		List<String> customerDetails = agentPage.verifyInBoundCallsAreReflectedInCallHistory();

		testSteps.log(Status.INFO, "clickCallHistoryOption");
		agentPage.clickCallHistoryOption();
		testSteps.log(Status.INFO, "switchToNewTab");
		agentPage.switchToNewTab();
		testSteps.log(Status.INFO, "verifyCallDetailsAreDisplayedInCallHistory");
		callHistory.verify(customerDetails);
		testSteps.log(Status.INFO, "closeCurrentTab");
		agentPage.closeCurrentTab();
		testSteps.log(Status.INFO, "switchToDefaultTab");
		agentPage.switchToDefaultTab();

		Thread.sleep(2000);
		testSteps.log(Status.INFO, "agentLogOut");
		agentPage.agentLogOut();
		
	}
	
	// InBound Calls
	@Test
	public void verifyRaiseNewTicketFunctionalityWhenAgentIsOnCallForInBoundCalls() throws InterruptedException, IOException {
		testSteps = testCase.createTest("verifyRaiseNewTicketFunctionalityWhenAgentIsOnCallForInBoundCalls",
				"verifyRaiseNewTicketFunctionalityWhenAgentIsOnCallForInBoundCalls");
		Login login1 = new Login(driver, profileName, userName, passWord);
		AgentHomePage agentPage = new AgentHomePage(driver, profileName, userName, passWord);
		CallHistoryPage callHistory = new CallHistoryPage(driver, profileName, userName, passWord);
		CallBackRequestsPage cbr = new CallBackRequestsPage(driver, profileName, userName, passWord);
		
		testSteps.log(Status.INFO, "loginToProfile");
		login1.loginToProfile();

		testSteps.log(Status.INFO, "isAgentReadyStateIsDisplayed");
		agentPage.isAgentReadyStateIsDisplayed();

		DateFormat logindf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date dateobj = new Date();
		logger.info("logintime loop for " + profileName + " is::" + logindf.format(dateobj));
		System.out.println("LoggedIn time for" + profileName + "is ::" + logindf.format(dateobj));

		testSteps.log(Status.INFO, "generateInBoundJson");
		generateInBoundJson(oneCustomer);

		testSteps.log(Status.INFO, "verifyRaiseNewTicketForInBoundCall");
		String notesForCbr = agentPage.verifyRaiseNewTicketForInBoundCall();

		testSteps.log(Status.INFO, "clickCBROption");
		agentPage.clickCBROption();
		
		agentPage.switchToNewTab();
		testSteps.log(Status.INFO, "verifyCallDetailsAreDisplayedInCallHistory");
		
		Assert.assertTrue(cbr.verifyDetailsOfCBR(notesForCbr));

		testSteps.log(Status.INFO, "closeCurrentTab");
		agentPage.closeCurrentTab();
		testSteps.log(Status.INFO, "switchToDefaultTab");
		agentPage.switchToDefaultTab();

		Thread.sleep(2000);
		testSteps.log(Status.INFO, "agentLogOut");
		agentPage.agentLogOut();
	}
	
	// InBound Calls
	@Test
	public void verifyCallBackRequestFunctionalityForInBoundCalls() throws InterruptedException, IOException {
		testSteps = testCase.createTest("verifyCallBackRequestFunctionalityForInBoundCalls",
				"verifyCallBackRequestFunctionalityForInBoundCalls");
		Login login1 = new Login(driver, profileName, userName, passWord);
		AgentHomePage agentPage = new AgentHomePage(driver, profileName, userName, passWord);
		CallHistoryPage callHistory = new CallHistoryPage(driver, profileName, userName, passWord);
		CallBackRequestsPage cbr = new CallBackRequestsPage(driver, profileName, userName, passWord);
		
		testSteps.log(Status.INFO, "loginToProfile");
		login1.loginToProfile();

		testSteps.log(Status.INFO, "isAgentReadyStateIsDisplayed");
		agentPage.isAgentReadyStateIsDisplayed();

		DateFormat logindf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date dateobj = new Date();
		logger.info("logintime loop for " + profileName + " is::" + logindf.format(dateobj));
		System.out.println("LoggedIn time for" + profileName + "is ::" + logindf.format(dateobj));

		testSteps.log(Status.INFO, "generateInBoundJson");
		generateInBoundJson(oneCustomer);

		testSteps.log(Status.INFO, "verifyFieldsOnAgentPageWhenAgentHaveInBoundCalls");
		String notesForCbr = agentPage.verifyCallBackRequestForInBoundCall();

		testSteps.log(Status.INFO, "clickCBROption");
		agentPage.clickCBROption();
		
		agentPage.switchToNewTab();
		testSteps.log(Status.INFO, "verifyCallDetailsAreDisplayedInCallHistory");
		
		Assert.assertTrue(cbr.verifyDetailsOfCBR(notesForCbr));

		testSteps.log(Status.INFO, "closeCurrentTab");
		agentPage.closeCurrentTab();
		testSteps.log(Status.INFO, "switchToDefaultTab");
		agentPage.switchToDefaultTab();

		Thread.sleep(2000);
		testSteps.log(Status.INFO, "agentLogOut");
		agentPage.agentLogOut();
	}

	@Test
	public void verifyAgentInBoundCallFunctionalityWhenAgentIsInBreakStateAndMovedToReadyState()
			throws InterruptedException, IOException {
		testSteps = testCase.createTest("verifyAgentFunctionalityWhenAgentIsInBreakStateAndMovedToReadyState",
				"verifyAgentFunctionalityWhenAgentIsInBreakStateAndMovedToReadyState");
		AgentHomePage agentPage = new AgentHomePage(driver, profileName, userName, passWord);
		Login login1 = new Login(driver, profileName, userName, passWord);

		testSteps.log(Status.INFO, "loginToProfile");
		login1.loginToProfile();

		testSteps.log(Status.INFO, "isAgentReadyStateIsDisplayed");
		agentPage.isAgentReadyStateIsDisplayed();

		DateFormat logindf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date dateobj = new Date();
		dateobj = new Date();
		logger.info("logintime loop for " + profileName + " is::" + logindf.format(dateobj));
		System.out.println("LoggedIn time for" + profileName + "is ::" + logindf.format(dateobj));

		if (profileName.equalsIgnoreCase("Profile 3")) {
			testSteps.log(Status.INFO, " Profile 3 agentMovingToBreakStatus");
			agentPage.agentMovingToBreakStatus();
			testSteps.log(Status.INFO, " Profile 3 verifyAgentGettingAnyCall");
			boolean notGettingCall = agentPage.verifyAgentGettingAnyCall();
			Assert.assertFalse(notGettingCall);
			while (customerCount < (numberOfCustomers / 2)) {
				Thread.sleep(10000);
				boolean notGettingCall1 = agentPage.verifyAgentGettingAnyCall();
				Assert.assertFalse(notGettingCall1);
			}
			testSteps.log(Status.INFO, "agentMovingToReadyStatus");
			agentPage.agentMovingToReadyStatus();
			boolean gettingCall = agentPage.verifyAgentGettingAnyCall();
			Assert.assertTrue(gettingCall);
		}
		Thread.sleep(20000);
		testSteps.log(Status.INFO, "generateInBoundJson");
		generateInBoundJson(numberOfCustomers);

		testSteps.log(Status.INFO, "verifyInBoundCallsOnAgentPage");
		ArrayList callsCount = agentPage.verifyInBoundCallsOnAgentPage();

		DateFormat logoutdf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date logoutdateobj = new Date();
		logger.info("logout time for loop for " + profileName + " is::" + logoutdf.format(logoutdateobj));
		System.out.println("LoggedOut time for" + profileName + "is ::" + logoutdf.format(logoutdateobj));
		Thread.sleep(2000);

		testSteps.log(Status.INFO, "Add all profiles call data to array");
		int rowIndex = Integer.parseInt(profilesCount.split(",")[0]);
		int coloumnIndex = Integer.parseInt(profilesCount.split(",")[1]);
		storeProfilesData.profileData[0][0] = "agentName";
		storeProfilesData.profileData[0][1] = "CallsCount";
		storeProfilesData.profileData[0][2] = "logInTime";
		storeProfilesData.profileData[0][3] = "logOutTime";
		storeProfilesData.profileData[rowIndex][0] = userName;
		if (callsCount.size() == 2)
			storeProfilesData.profileData[rowIndex][1] = Integer.toString((Integer) callsCount.get(1));
		else
			storeProfilesData.profileData[rowIndex][1] = Integer.toString((Integer) callsCount.get(0));
		storeProfilesData.profileData[rowIndex][2] = logindf.format(dateobj);
		storeProfilesData.profileData[rowIndex][3] = logoutdf.format(logoutdateobj);
		ExcelReadAndWriteData excelData = new ExcelReadAndWriteData();
		if (callsCount.size() == 2) {
			testSteps.log(Status.INFO, "Write data in excel");
			excelData.testSheet();
		}

		Thread.sleep(2000);
		testSteps.log(Status.INFO, "agentLogOut");
		agentPage.agentLogOut();
	}
	
	@Test
	public void verifyAgentInBoundCallFunctionalityWhenAgentIsInReadyStateAndMovedToBreakStateAfterHandlingFewCalls()
			throws InterruptedException, IOException {
		testSteps = testCase.createTest("verifyAgentInBoundCallFunctionalityWhenAgentIsInReadyStateAndMovedToBreakStateAfterHandlingFewCalls",
				"verifyAgentInBoundCallFunctionalityWhenAgentIsInReadyStateAndMovedToBreakStateAfterHandlingFewCalls");
		AgentHomePage agentPage = new AgentHomePage(driver, profileName, userName, passWord);
		Login login1 = new Login(driver, profileName, userName, passWord);

		testSteps.log(Status.INFO, "loginToProfile");
		login1.loginToProfile();

		testSteps.log(Status.INFO, "isAgentReadyStateIsDisplayed");
		agentPage.isAgentReadyStateIsDisplayed();

		DateFormat logindf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date dateobj = new Date();
		dateobj = new Date();
		logger.info("logintime loop for " + profileName + " is::" + logindf.format(dateobj));
		System.out.println("LoggedIn time for" + profileName + "is ::" + logindf.format(dateobj));

		testSteps.log(Status.INFO, "generateInBoundJson");
		generateInBoundJson(numberOfCustomers);

		testSteps.log(Status.INFO, "verifyInBoundCallsOnAgentPage");
		ArrayList callsCount = agentPage.verifyInBoundCallsOnAgentPagesWhenOneAgentMovedToBreak();
		agentPage.verifyAgentGettingAnyCallsWhenHeMovedToBreakState();
		agentPage.agentMovingToReadyStatus();
		DateFormat logoutdf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date logoutdateobj = new Date();
		logger.info("logout time for loop for " + profileName + " is::" + logoutdf.format(logoutdateobj));
		System.out.println("LoggedOut time for" + profileName + "is ::" + logoutdf.format(logoutdateobj));
		Thread.sleep(2000);

		testSteps.log(Status.INFO, "Add all profiles call data to array");
		int rowIndex = Integer.parseInt(profilesCount.split(",")[0]);
		int coloumnIndex = Integer.parseInt(profilesCount.split(",")[1]);
		storeProfilesData.profileData[0][0] = "agentName";
		storeProfilesData.profileData[0][1] = "CallsCount";
		storeProfilesData.profileData[0][2] = "logInTime";
		storeProfilesData.profileData[0][3] = "logOutTime";
		storeProfilesData.profileData[rowIndex][0] = userName;
		if (callsCount.size() == 2)
			storeProfilesData.profileData[rowIndex][1] = Integer.toString((Integer) callsCount.get(1));
		else
			storeProfilesData.profileData[rowIndex][1] = Integer.toString((Integer) callsCount.get(0));
		storeProfilesData.profileData[rowIndex][2] = logindf.format(dateobj);
		storeProfilesData.profileData[rowIndex][3] = logoutdf.format(logoutdateobj);
		ExcelReadAndWriteData excelData = new ExcelReadAndWriteData();
		if (callsCount.size() == 2) {
			testSteps.log(Status.INFO, "Write data in excel");
			excelData.testSheet();
		}

		Thread.sleep(2000);
		testSteps.log(Status.INFO, "agentLogOut");
		agentPage.agentLogOut();
	}
	

	@Test
	public void verifyAgentInBoundCallFunctionalityWhenAgentIsInBreakState() throws InterruptedException, IOException {
		testSteps = testCase.createTest("verifyAgentInBoundCallFunctionalityWhenAgentIsInBreakState",
				"verifyAgentInBoundCallFunctionalityWhenAgentIsInBreakState");
		AgentHomePage agentPage = new AgentHomePage(driver, profileName, userName, passWord);
		Login login1 = new Login(driver, profileName, userName, passWord);

		testSteps.log(Status.INFO, "loginToProfile");
		login1.loginToProfile();

		testSteps.log(Status.INFO, "isAgentReadyStateIsDisplayed");
		agentPage.isAgentReadyStateIsDisplayed();

		DateFormat logindf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date dateobj = new Date();
		dateobj = new Date();
		logger.info("logintime loop for " + profileName + " is::" + logindf.format(dateobj));
		System.out.println("LoggedIn time for" + profileName + "is ::" + logindf.format(dateobj));

		if (profileName.equalsIgnoreCase("Profile 3")) {
			testSteps.log(Status.INFO, "agentMovingToBreakStatus");
			agentPage.agentMovingToBreakStatus();
			testSteps.log(Status.INFO, "verifyAgentGettingAnyCall");
			boolean notGettingCall = agentPage.verifyAgentGettingAnyCall();
			Assert.assertFalse(notGettingCall);
			while (customerCount < (numberOfCustomers)) {
				Thread.sleep(10000);
				boolean notGettingCall1 = agentPage.verifyAgentGettingAnyCall();
				Assert.assertFalse(notGettingCall1);
			}
		}
		Thread.sleep(20000);

		testSteps.log(Status.INFO, "generateInBoundJson");
		generateInBoundJson(numberOfCustomers);

		testSteps.log(Status.INFO, "verifyInBoundCallsOnAgentPage");
		ArrayList callsCount = agentPage.verifyInBoundCallsOnAgentPage();

		DateFormat logoutdf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date logoutdateobj = new Date();
		logger.info("logout time for loop for " + profileName + " is::" + logoutdf.format(logoutdateobj));
		System.out.println("LoggedOut time for" + profileName + "is ::" + logoutdf.format(logoutdateobj));
		Thread.sleep(2000);

		testSteps.log(Status.INFO, "Add all profiles call data to array");
		int rowIndex = Integer.parseInt(profilesCount.split(",")[0]);
		int coloumnIndex = Integer.parseInt(profilesCount.split(",")[1]);
		storeProfilesData.profileData[0][0] = "agentName";
		storeProfilesData.profileData[0][1] = "CallsCount";
		storeProfilesData.profileData[0][2] = "logInTime";
		storeProfilesData.profileData[0][3] = "logOutTime";
		storeProfilesData.profileData[rowIndex][0] = userName;
		if (callsCount.size() == 2)
			storeProfilesData.profileData[rowIndex][1] = Integer.toString((Integer) callsCount.get(1));
		else
			storeProfilesData.profileData[rowIndex][1] = Integer.toString((Integer) callsCount.get(0));
		storeProfilesData.profileData[rowIndex][2] = logindf.format(dateobj);
		storeProfilesData.profileData[rowIndex][3] = logoutdf.format(logoutdateobj);
		ExcelReadAndWriteData excelData = new ExcelReadAndWriteData();
		if (callsCount.size() == 2) {
			testSteps.log(Status.INFO, "Write data in excel");
			excelData.testSheet();
		}

		Thread.sleep(2000);
		testSteps.log(Status.INFO, "agentLogOut");
		agentPage.agentLogOut();
	}

	@Test
	public void verifyAgentInBoundCallFunctionalityWhenAgentIsInWorkAssignedState()
			throws InterruptedException, IOException {
		testSteps = testCase.createTest("verifyAgentInBoundCallFunctionalityWhenAgentIsInWorkAssignedState",
				"verifyAgentInBoundCallFunctionalityWhenAgentIsInWorkAssignedState");
		AgentHomePage agentPage = new AgentHomePage(driver, profileName, userName, passWord);
		Login login1 = new Login(driver, profileName, userName, passWord);

		testSteps.log(Status.INFO, "loginToProfile");
		login1.loginToProfile();

		testSteps.log(Status.INFO, "isAgentReadyStateIsDisplayed");
		agentPage.isAgentReadyStateIsDisplayed();

		DateFormat logindf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date dateobj = new Date();
		logger.info("logintime loop for " + profileName + " is::" + logindf.format(dateobj));
		System.out.println("LoggedIn time for" + profileName + "is ::" + logindf.format(dateobj));

		if (profileName.equalsIgnoreCase("Profile 3")) {
			testSteps.log(Status.INFO, "agentMovingToWorkAssignedStatus");
			agentPage.agentMovingToWorkAssignedStatus();
			testSteps.log(Status.INFO, "verifyAgentGettingAnyCall");
			boolean notGettingCall = agentPage.verifyAgentGettingAnyCall();
			Assert.assertFalse(notGettingCall);
			while (customerCount < (numberOfCustomers)) {
				Thread.sleep(10000);
				boolean notGettingCall1 = agentPage.verifyAgentGettingAnyCall();
				Assert.assertFalse(notGettingCall1);
			}
			// boolean gettingCall = agentPage.verifyAgentGettingAnyCall();
			// Assert.assertTrue(gettingCall);
		}
		Thread.sleep(20000);

		testSteps.log(Status.INFO, "generateInBoundJson");
		generateInBoundJson(numberOfCustomers);

		testSteps.log(Status.INFO, "verifyInBoundCallsOnAgentPage");
		ArrayList callsCount = agentPage.verifyInBoundCallsOnAgentPage();

		DateFormat logoutdf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date logoutdateobj = new Date();
		logger.info("logout time for loop for " + profileName + " is::" + logoutdf.format(logoutdateobj));
		System.out.println("LoggedOut time for" + profileName + "is ::" + logoutdf.format(logoutdateobj));
		Thread.sleep(2000);

		testSteps.log(Status.INFO, "Add all profiles call data to array");
		int rowIndex = Integer.parseInt(profilesCount.split(",")[0]);
		int coloumnIndex = Integer.parseInt(profilesCount.split(",")[1]);
		storeProfilesData.profileData[0][0] = "agentName";
		storeProfilesData.profileData[0][1] = "CallsCount";
		storeProfilesData.profileData[0][2] = "logInTime";
		storeProfilesData.profileData[0][3] = "logOutTime";
		storeProfilesData.profileData[rowIndex][0] = userName;
		if (callsCount.size() == 2)
			storeProfilesData.profileData[rowIndex][1] = Integer.toString((Integer) callsCount.get(1));
		else
			storeProfilesData.profileData[rowIndex][1] = Integer.toString((Integer) callsCount.get(0));
		storeProfilesData.profileData[rowIndex][2] = logindf.format(dateobj);
		storeProfilesData.profileData[rowIndex][3] = logoutdf.format(logoutdateobj);
		ExcelReadAndWriteData excelData = new ExcelReadAndWriteData();
		if (callsCount.size() == 2) {
			testSteps.log(Status.INFO, "Write data in excel");
			excelData.testSheet();
		}

		Thread.sleep(2000);
		testSteps.log(Status.INFO, "agentLogOut");
		agentPage.agentLogOut();
	}

	@Test
	public void verifyAgentInBoundCallFunctionalityWhenAgentIsChangedFromWorkAssignedStateToReadyState()
			throws InterruptedException, IOException {
		testSteps = testCase.createTest(
				"verifyAgentInBoundCallFunctionalityWhenAgentIsChangedFromWorkAssignedStateToReadyState",
				"verifyAgentInBoundCallFunctionalityWhenAgentIsChangedFromWorkAssignedStateToReadyState");
		AgentHomePage agentPage = new AgentHomePage(driver, profileName, userName, passWord);
		Login login1 = new Login(driver, profileName, userName, passWord);

		testSteps.log(Status.INFO, "loginToProfile");
		login1.loginToProfile();

		testSteps.log(Status.INFO, "isAgentReadyStateIsDisplayed");
		agentPage.isAgentReadyStateIsDisplayed();

		DateFormat logindf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date dateobj = new Date();
		dateobj = new Date();
		logger.info("logintime loop for " + profileName + " is::" + logindf.format(dateobj));
		System.out.println("LoggedIn time for" + profileName + "is ::" + logindf.format(dateobj));

		if (profileName.equalsIgnoreCase("Profile 2")) {
			testSteps.log(Status.INFO, "agentMovingToWorkAssignedStatus");
			agentPage.agentMovingToWorkAssignedStatus();

			testSteps.log(Status.INFO, "verifyAgentGettingAnyCall");
			boolean notGettingCall = agentPage.verifyAgentGettingAnyCall();
			Assert.assertFalse(notGettingCall);
			while (customerCount < (numberOfCustomers / 2)) {
				Thread.sleep(10000);
				boolean notGettingCall1 = agentPage.verifyAgentGettingAnyCall();
				Assert.assertFalse(notGettingCall1);
			}
			testSteps.log(Status.INFO, "agentMovingToReadyStatus");
			agentPage.agentMovingToReadyStatus();
			boolean gettingCall = agentPage.verifyAgentGettingAnyCall();
			Assert.assertTrue(gettingCall);
		}
		Thread.sleep(20000);

		testSteps.log(Status.INFO, "generateInBoundJson");
		generateInBoundJson(numberOfCustomers);

		testSteps.log(Status.INFO, "verifyInBoundCallsOnAgentPage");
		ArrayList callsCount = agentPage.verifyInBoundCallsOnAgentPage();

		DateFormat logoutdf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date logoutdateobj = new Date();
		logger.info("logout time for loop for " + profileName + " is::" + logoutdf.format(logoutdateobj));
		System.out.println("LoggedOut time for" + profileName + "is ::" + logoutdf.format(logoutdateobj));
		Thread.sleep(2000);

		testSteps.log(Status.INFO, "Add all profiles call data to array");
		int rowIndex = Integer.parseInt(profilesCount.split(",")[0]);
		int coloumnIndex = Integer.parseInt(profilesCount.split(",")[1]);
		storeProfilesData.profileData[0][0] = "agentName";
		storeProfilesData.profileData[0][1] = "CallsCount";
		storeProfilesData.profileData[0][2] = "logInTime";
		storeProfilesData.profileData[0][3] = "logOutTime";
		storeProfilesData.profileData[rowIndex][0] = userName;
		if (callsCount.size() == 2)
			storeProfilesData.profileData[rowIndex][1] = Integer.toString((Integer) callsCount.get(1));
		else
			storeProfilesData.profileData[rowIndex][1] = Integer.toString((Integer) callsCount.get(0));
		storeProfilesData.profileData[rowIndex][2] = logindf.format(dateobj);
		storeProfilesData.profileData[rowIndex][3] = logoutdf.format(logoutdateobj);
		ExcelReadAndWriteData excelData = new ExcelReadAndWriteData();
		if (callsCount.size() == 2) {
			testSteps.log(Status.INFO, "Write data in excel");
			excelData.testSheet();
		}

		Thread.sleep(2000);
		testSteps.log(Status.INFO, "agentLogOut");
		agentPage.agentLogOut();
	}

	// in progress
	@Test
	public void verifyAgentFunctionalityForMenu() throws InterruptedException {
		testSteps = testCase.createTest("verifyAgentFunctionalityForMenu", "verifyAgentFunctionalityForMenu");
		AgentHomePage agentPage = new AgentHomePage(driver, profileName, userName, passWord);
		Login login1 = new Login(driver, profileName, userName, passWord);
		Login login = new Login(driver, profileName, userName, passWord);

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
			map = handlePostRequestForCallsFor();
			testSteps.log(Status.INFO, "verifyCalls");
			handlePostRequestForMenuDigits(map);
			testSteps.log(Status.INFO, "handlePostRequestForMenuDigits");
		}
		Thread.sleep(20000);
		HashMap<String, Integer> profileSummary;
		profileSummary = agentPage.verifyCallsOnAgentPageForMenu(map);
		testSteps.log(Status.INFO, "verifyCallsOnAgentPage");
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
		// agentPage.agentLogOut();
	}

	@Test
	public void verifyCallTransferFunctionalityFromOneAgentToAnotherAgentForInBound() throws InterruptedException {
		testSteps = testCase.createTest("verifyCallTransferFunctionalityFromOneAgentToAnotherAgentForInBound",
				"verifyCallTransferFunctionalityFromOneAgentToAnotherAgentForInBound");

		BrowserFunctions browesr = new BrowserFunctions();
		Login login1 = new Login(driver, profileName, userName, passWord);
		AgentHomePage agentPage = new AgentHomePage(driver, profileName, userName, passWord);

		testSteps.log(Status.INFO, "loginToProfile");
		login1.loginToProfile();
		testSteps.log(Status.INFO, "isAgentReadyStateIsDisplayed");
		agentPage.isAgentReadyStateIsDisplayed();

		testSteps.log(Status.INFO, "openNewBrowser");
		WebDriver driver1 = browesr.openNewBrowser();

		testSteps.log(Status.INFO, "loginToNewAgentProfile");
		login1.loginToNewAgentProfile(driver1);

		testSteps.log(Status.INFO, "generateInBoundJson");
		generateInBoundJson(oneCustomer);

		testSteps.log(Status.INFO, "callTransferToNewAgent");
		String number = agentPage.callTransferToNewAgent();
		testSteps.log(Status.INFO, "verifyCallsOnAgentPageOnNewBrowser");
		agentPage.verifyCallsOnAgentPageOnNewBrowser(driver1, number);
		agentPage.newAgentLogOut(driver1);
		driver1.close();
		agentPage.moveToreadyStateFromWrapUp();
	}

	// major
	// agent home
	@Test
	public void verifyCallTransferFunctionalityFromOneAgentToExternalNumberForInBound() throws InterruptedException {
		testSteps = testCase.createTest("verifyCallTransferFunctionalityFromOneAgentToExternalNumberForInBound",
				"verifyCallTransferFunctionalityFromOneAgentToExternalNumberForInBound");
		BrowserFunctions browesr = new BrowserFunctions();
		Login login1 = new Login(driver, profileName, userName, passWord);
		AgentHomePage agentPage = new AgentHomePage(driver, profileName, userName, passWord);

		testSteps.log(Status.INFO, "loginToProfile");
		login1.loginToProfile();
		testSteps.log(Status.INFO, "isAgentReadyStateIsDisplayed");
		agentPage.isAgentReadyStateIsDisplayed();
		testSteps.log(Status.INFO, "generateInBoundJson");
		generateInBoundJson(oneCustomer);
		testSteps.log(Status.INFO, "callTransferToMobile");
		agentPage.callTransferToMobile();
		agentPage.moveToreadyStateFromWrapUp();

	}

	// priority 1
	// agent home
	@Test
	public void verifyWarmTranferCallFromOneAgentToAnotherAgentForInBound() throws InterruptedException {
		testSteps = testCase.createTest("verifyWarmTranferCallFromOneAgentToAnotherAgentForInBound",
				"verifyWarmTranferCallFromOneAgentToAnotherAgentForInBound");
		BrowserFunctions browesr = new BrowserFunctions();
		Login login1 = new Login(driver, profileName, userName, passWord);
		AgentHomePage agentPage = new AgentHomePage(driver, profileName, userName, passWord);

		testSteps.log(Status.INFO, "loginToProfile");
		login1.loginToProfile();
		testSteps.log(Status.INFO, "isAgentReadyStateIsDisplayed");
		agentPage.isAgentReadyStateIsDisplayed();

		testSteps.log(Status.INFO, "openNewBrowser");
		driver1 = browesr.openNewBrowser();

		testSteps.log(Status.INFO, "loginToNewAgentProfile");
		login1.loginToNewAgentProfile(driver1);

		testSteps.log(Status.INFO, "generateInBoundJson");
		generateInBoundJson(oneCustomer);
		testSteps.log(Status.INFO, "warmTransferToAgent");
		String number = agentPage.warmTransferToAgent();
		testSteps.log(Status.INFO, "verifyWarmTransferCallOnNewAgent");
		agentPage.verifyWarmTransferCallOnNewAgent(driver1, number);
		testSteps.log(Status.INFO, "changeHoldStateOfCustomerInWarmTransferCall");
		agentPage.changeHoldStateOfCustomerInWarmTransferCall();
		Assert.assertEquals(agentPage.stateOfHoldButtonForCustomerInWarmTransferCall(), "Hold Customer");
		agentPage.changeHoldStateOfCustomerInWarmTransferCall();
		Assert.assertEquals(agentPage.stateOfHoldButtonForCustomerInWarmTransferCall(), "UnHold Customer");
		testSteps.log(Status.INFO, "cancleWarmTransferCall");
		agentPage.cancleWarmTransferCall();
		testSteps.log(Status.INFO, "verifyStateOfWarmTransferAgentWhenNormalAgentCancleWarmTransfer");
		agentPage.verifyStateOfWarmTransferAgentWhenNormalAgentCancleWarmTransfer(driver1);
		testSteps.log(Status.INFO, "newAgentLogOut");
		agentPage.newAgentLogOut(driver1);
		driver1.close();
		testSteps.log(Status.INFO, "verifyWarmTranferFieldsAreDisplayed");
		agentPage.verifyWarmTranferFieldsAreDisplayed();
		testSteps.log(Status.INFO, "verifyAgentPageWhenAgentCancleWarmTranfer");
		agentPage.verifyAgentPageWhenAgentCancleWarmTranfer();
		testSteps.log(Status.INFO, "agentLogOut");
		agentPage.agentLogOut();
	}

	// priority 1
	// agent home
	@Test
	public void verifyWarmTranferCallFromOneAgentToExternalNumberForInBound() throws InterruptedException {
		testSteps = testCase.createTest("verifyWarmTranferCallFromOneAgentToExternalNumberForInBound",
				"verifyWarmTranferCallFromOneAgentToExternalNumberForInBound");
		BrowserFunctions browesr = new BrowserFunctions();
		Login login1 = new Login(driver, profileName, userName, passWord);
		AgentHomePage agentPage = new AgentHomePage(driver, profileName, userName, passWord);

		testSteps.log(Status.INFO, "loginToProfile");
		login1.loginToProfile();
		testSteps.log(Status.INFO, "isAgentReadyStateIsDisplayed");
		agentPage.isAgentReadyStateIsDisplayed();
		testSteps.log(Status.INFO, "generateInBoundJson");
		generateInBoundJson(oneCustomer);
		testSteps.log(Status.INFO, "warmTransferToExternalNumber");
		agentPage.warmTransferToExternalNumber();
		testSteps.log(Status.INFO, "changeHoldStateOfCustomerInWarmTransferCall");
		agentPage.changeHoldStateOfCustomerInWarmTransferCall();
		Assert.assertEquals(agentPage.stateOfHoldButtonForCustomerInWarmTransferCall(), "Hold Customer");
		agentPage.changeHoldStateOfCustomerInWarmTransferCall();
		Assert.assertEquals(agentPage.stateOfHoldButtonForCustomerInWarmTransferCall(), "UnHold Customer");
		testSteps.log(Status.INFO, "cancleWarmTransferCall");
		agentPage.cancleWarmTransferCall();
		testSteps.log(Status.INFO, "verifyWarmTranferFieldsAreDisplayed");
		agentPage.verifyWarmTranferFieldsAreDisplayed();
		testSteps.log(Status.INFO, "verifyAgentPageWhenAgentCancleWarmTranfer");
		agentPage.verifyAgentPageWhenAgentCancleWarmTranfer();
		agentPage.agentLogOut();
	}

	WebDriver driver1;

	public void closeNewBrowser() {
		driver1.quit();
	}

	// priority 1
	// agent home
	@Test
	public void verifyCompleteWarmTranferCallFromOneAgentToAnotherAgentForInBound() throws InterruptedException {
		testSteps = testCase.createTest("verifyCompleteWarmTranferCallFromOneAgentToAnotherAgentForInBound",
				"verifyCompleteWarmTranferCallFromOneAgentToAnotherAgentForInBound");
		BrowserFunctions browesr = new BrowserFunctions();
		Login login1 = new Login(driver, profileName, userName, passWord);
		AgentHomePage agentPage = new AgentHomePage(driver, profileName, userName, passWord);

		testSteps.log(Status.INFO, "loginToProfile");
		login1.loginToProfile();
		testSteps.log(Status.INFO, "isAgentReadyStateIsDisplayed");
		agentPage.isAgentReadyStateIsDisplayed();

		testSteps.log(Status.INFO, "openNewBrowser");
		driver1 = browesr.openNewBrowser();

		testSteps.log(Status.INFO, "loginToNewAgentProfile");
		login1.loginToNewAgentProfile(driver1);

		testSteps.log(Status.INFO, "generateInBoundJson");
		generateInBoundJson(oneCustomer);

		testSteps.log(Status.INFO, "warmTransferToAgent");
		String number = agentPage.warmTransferToAgent();

		testSteps.log(Status.INFO, "verifyWarmTransferCallOnNewAgent");
		agentPage.verifyWarmTransferCallOnNewAgent(driver1, number);

		testSteps.log(Status.INFO, "changeHoldStateOfCustomerInWarmTransferCall");
		agentPage.changeHoldStateOfCustomerInWarmTransferCall();
		Assert.assertEquals(agentPage.stateOfHoldButtonForCustomerInWarmTransferCall(), "Hold Customer");
		agentPage.changeHoldStateOfCustomerInWarmTransferCall();
		Assert.assertEquals(agentPage.stateOfHoldButtonForCustomerInWarmTransferCall(), "UnHold Customer");
		testSteps.log(Status.INFO, "transferWarmTransferCallToNewAgent");
		agentPage.transferWarmTransferCallToNewAgent();
		testSteps.log(Status.INFO, "verifyStateOfNormalAgentWhenGiveCompleteWarmTransferToNewAgent");
		agentPage.verifyStateOfNormalAgentWhenGiveCompleteWarmTransferToNewAgent();
		testSteps.log(Status.INFO, "verifyNewAgentPageWhenNormalAgentMakeWarmTranfer");
		agentPage.verifyNewAgentPageWhenNormalAgentMakeWarmTranfer(driver1);
		testSteps.log(Status.INFO, "newAgentLogOut");
		agentPage.newAgentLogOut(driver1);
		driver1.close();
		testSteps.log(Status.INFO, "moveToreadyStateFromWrapUp");
		agentPage.moveToreadyStateFromWrapUp();
		agentPage.agentLogOut();
	}

	// priority 1
	// agent home
	@Test
	public void verifyCompleteWarmTranferCallFromOneAgentToExternalNumberForInBound() throws InterruptedException {
		testSteps = testCase.createTest("verifyWarmTranferCallFromOneAgentToExternalNumberForInBound",
				"verifyWarmTranferCallFromOneAgentToExternalNumberForInBound");
		BrowserFunctions browesr = new BrowserFunctions();
		Login login1 = new Login(driver, profileName, userName, passWord);
		AgentHomePage agentPage = new AgentHomePage(driver, profileName, userName, passWord);

		testSteps.log(Status.INFO, "loginToProfile");
		login1.loginToProfile();
		testSteps.log(Status.INFO, "isAgentReadyStateIsDisplayed");
		agentPage.isAgentReadyStateIsDisplayed();
		testSteps.log(Status.INFO, "generateInBoundJson");
		generateInBoundJson(oneCustomer);
		testSteps.log(Status.INFO, "warmTransferToExternalNumber");
		agentPage.warmTransferToExternalNumber();
		testSteps.log(Status.INFO, "changeHoldStateOfCustomerInWarmTransferCall");
		agentPage.changeHoldStateOfCustomerInWarmTransferCall();
		Assert.assertEquals(agentPage.stateOfHoldButtonForCustomerInWarmTransferCall(), "Hold Customer");
		agentPage.changeHoldStateOfCustomerInWarmTransferCall();
		Assert.assertEquals(agentPage.stateOfHoldButtonForCustomerInWarmTransferCall(), "UnHold Customer");
		testSteps.log(Status.INFO, "cancleWarmTransferCall");
		agentPage.transferWarmTransferCallToNewAgent();
		testSteps.log(Status.INFO, "verifyStateOfNormalAgentWhenGiveCompleteWarmTransferToNewAgent");
		agentPage.verifyStateOfNormalAgentWhenGiveCompleteWarmTransferToNewAgent();
		testSteps.log(Status.INFO, "moveToreadyStateFromWrapUp");
		agentPage.moveToreadyStateFromWrapUp();
		testSteps.log(Status.INFO, "agentLogOut");
		agentPage.agentLogOut();
	}

	// priority 1
	// agent home
	@Test
	public void verifyMuteAndPauseOptionsFunctionalityOnCallSidePanel() throws InterruptedException, IOException {

		testSteps = testCase.createTest("verifyMuteAndPauseOptionsFunctionalityOnCallSidePanel",
				"verifyMuteAndPauseOptionsFunctionalityOnCallSidePanel");
		Login login1 = new Login(driver, profileName, userName, passWord);
		AgentHomePage agentPage = new AgentHomePage(driver, profileName, userName, passWord);

		testSteps.log(Status.INFO, "loginToProfile");
		login1.loginToProfile();
		testSteps.log(Status.INFO, "isAgentReadyStateIsDisplayed");
		agentPage.isAgentReadyStateIsDisplayed();

		DateFormat logindf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date dateobj = new Date();
		logger.info("logintime loop for " + profileName + " is::" + logindf.format(dateobj));
		System.out.println("LoggedIn time for" + profileName + "is ::" + logindf.format(dateobj));

		testSteps.log(Status.INFO, "generateInBoundJson");
		generateInBoundJson(oneCustomer);

		testSteps.log(Status.INFO, "verifyMuteAndPauseOptionsFunctionalityForInBoundCallsOnAgentPage");
		agentPage.verifyMuteAndPauseOptionsFunctionalityForInBoundCallsOnAgentPage();

		Thread.sleep(2000);
		testSteps.log(Status.INFO, "agentLogOut");
		agentPage.agentLogOut();
	}
	
	
	@Test
	public void verifyTakeMeToBreakFunctionalityOnCallSidePanelAfterCompletionOfInBoundCall() throws InterruptedException, IOException {

		testSteps = testCase.createTest("verifyTakeMeToBreakFunctionalityOnCallSidePanelAfterCompletionOfInBoundCall",
				"verifyTakeMeToBreakFunctionalityOnCallSidePanelAfterCompletionOfInBoundCall");
		Login login1 = new Login(driver, profileName, userName, passWord);
		AgentHomePage agentPage = new AgentHomePage(driver, profileName, userName, passWord);

		testSteps.log(Status.INFO, "loginToProfile");
		login1.loginToProfile();
		testSteps.log(Status.INFO, "isAgentReadyStateIsDisplayed");
		agentPage.isAgentReadyStateIsDisplayed();

		DateFormat logindf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date dateobj = new Date();
		logger.info("logintime loop for " + profileName + " is::" + logindf.format(dateobj));
		System.out.println("LoggedIn time for" + profileName + "is ::" + logindf.format(dateobj));

		testSteps.log(Status.INFO, "generateInBoundJson");
		generateInBoundJson(oneCustomer);

		testSteps.log(Status.INFO, "verifyMuteAndPauseOptionsFunctionalityForInBoundCallsOnAgentPage");
		agentPage.verifyTakeMeToBreakFunctionalityForInBound();

		Thread.sleep(2000);
		testSteps.log(Status.INFO, "agentLogOut");
		agentPage.agentLogOut();
	}

	// ------------------------------------------------------------ InBound
	// Calls end

	// ------------------------------------------------------------OutBOund
	// CAlls

	// done
	// crm
	@Test
	public void verifyOutBoundCallFromCRM() throws InterruptedException {
		testSteps = testCase.createTest("verifyOutBoundCallFromCRM", "verifyOutBoundCallFromCRM");

		Login login1 = new Login(driver, profileName, userName, passWord);
		AgentHomePage agentHome = new AgentHomePage(driver, profileName, userName, passWord);
		CrmPage crmPage = new CrmPage(driver, profileName, userName, passWord);
		CallHistoryPage callHistory = new CallHistoryPage(driver, profileName, userName, passWord);

		testSteps.log(Status.INFO, "loginToProfile");
		login1.loginToProfile();
		testSteps.log(Status.INFO, "isAgentReadyStateIsDisplayed");
		agentHome.isAgentReadyStateIsDisplayed();
		testSteps.log(Status.INFO, "clickCRMOption");
		agentHome.clickCRMOption();
		testSteps.log(Status.INFO, "switchToNewTab");
		agentHome.switchToNewTab();
		testSteps.log(Status.INFO, "clickCallButtonToMakeCall");
		crmPage.clickCallButtonToMakeCall();
		testSteps.log(Status.INFO, "checkOutBoundCall");
		String number = agentHome.checkOutBoundCall();
		testSteps.log(Status.INFO, "switchToDefaultTab");
		agentHome.switchToDefaultTab();
		testSteps.log(Status.INFO, "clickCallHistoryOption");
		agentHome.clickCallHistoryOption();
		testSteps.log(Status.INFO, "switchToNewTab");
		agentHome.switchToNewTab();
		testSteps.log(Status.INFO, "selectLastMonthInDateFilter");
		callHistory.selectLastMonthInDateFilter();
		testSteps.log(Status.INFO, "verifyCallDetailsAreDisplayedInCallHistoryForOutBound");
		callHistory.verifyCallDetailsAreDisplayedInCallHistoryForOutBound(number);
	}
	

	// done
	@Test
	public void verifyOutBoundCallFromCallHistory() throws InterruptedException {
		testSteps = testCase.createTest("verifyOutBoundCallFromCallHistory", "verifyOutBoundCallFromCallHistory");
		Login login = new Login(driver, profileName, userName, passWord);
		AgentHomePage agentHome = new AgentHomePage(driver, profileName, userName, passWord);
		CrmPage crmPage = new CrmPage(driver, profileName, userName, passWord);
		CallHistoryPage callHistory = new CallHistoryPage(driver, profileName, userName, passWord);

		testSteps.log(Status.INFO, "loginToProfile");
		login.loginToProfile();
		testSteps.log(Status.INFO, "clickCallHistoryOption");
		agentHome.clickCallHistoryOption();
		testSteps.log(Status.INFO, "switchToNewTab");
		agentHome.switchToNewTab();
		testSteps.log(Status.INFO, "selectLastMonthInDateFilter");
		callHistory.selectLastMonthInDateFilter();
		testSteps.log(Status.INFO, "clickCallOptionToMakeCall");
		callHistory.clickCallOptionToMakeCall();
		testSteps.log(Status.INFO, "checkOutBoundCall");
		String number = agentHome.checkOutBoundCall();
		Assert.assertEquals(agentHome.numberOfAvailableTabs(), 1);
		testSteps.log(Status.INFO, "switchToDefaultTab");
		agentHome.switchToDefaultTab();
		testSteps.log(Status.INFO, "clickCallHistoryOption");
		agentHome.clickCallHistoryOption();
		testSteps.log(Status.INFO, "switchToNewTab");
		agentHome.switchToNewTab();
		testSteps.log(Status.INFO, "selectLastMonthInDateFilter");
		callHistory.selectLastMonthInDateFilter();
		testSteps.log(Status.INFO, "verifyCallDetailsAreDisplayedInCallHistoryForOutBound");
		callHistory.verifyCallDetailsAreDisplayedInCallHistoryForOutBound(number);
	}

	// agent Profile
	@Test
	public void verifyOutBoundCallFromAgentProfile() throws InterruptedException {
		testSteps = testCase.createTest("verifyOutBoundCallFromAgentProfile", "verifyOutBoundCallFromAgentProfile");
		Login login = new Login(driver, profileName, userName, passWord);
		AgentHomePage agentHome = new AgentHomePage(driver, profileName, userName, passWord);
		CrmPage crmPage = new CrmPage(driver, profileName, userName, passWord);
		CallHistoryPage callHistory = new CallHistoryPage(driver, profileName, userName, passWord);

		testSteps.log(Status.INFO, "loginToProfile");
		login.loginToProfile();
		testSteps.log(Status.INFO, "navigateToProfile");
		agentHome.navigateToProfile();
		testSteps.log(Status.INFO, "switchToNewTab");
		agentHome.switchToNewTab();
		testSteps.log(Status.INFO, "clickMyContactsTab");
		agentHome.clickMyContactsTab();
		testSteps.log(Status.INFO, "makeCallToContact");
		agentHome.makeCallToContact();
		testSteps.log(Status.INFO, "checkOutBoundCall");
		String number = agentHome.checkOutBoundCall();
		testSteps.log(Status.INFO, "switchToDefaultTab");
		agentHome.switchToDefaultTab();
		testSteps.log(Status.INFO, "clickCallHistoryOption");
		agentHome.clickCallHistoryOption();
		testSteps.log(Status.INFO, "switchToNewTab");
		agentHome.switchToNewTab();
		testSteps.log(Status.INFO, "selectLastMonthInDateFilter");
		callHistory.selectLastMonthInDateFilter();
		testSteps.log(Status.INFO, "verifyCallDetailsAreDisplayedInCallHistoryForOutBound");
		callHistory.verifyCallDetailsAreDisplayedInCallHistoryForOutBound(number);
	}

	// done
	@Test
	public void verifyOutBoundCallFromCBR() throws InterruptedException {
		testSteps = testCase.createTest("verifyOutBoundCallFromCBR", "verifyOutBoundCallFromCBR");
		Login login = new Login(driver, profileName, userName, passWord);
		AgentHomePage agentHome = new AgentHomePage(driver, profileName, userName, passWord);
		CrmPage crmPage = new CrmPage(driver, profileName, userName, passWord);
		CallBackRequestsPage cbrPage = new CallBackRequestsPage(driver, profileName, userName, passWord);
		CallHistoryPage callHistory = new CallHistoryPage(driver, profileName, userName, passWord);
		testSteps.log(Status.INFO, "loginToProfile");
		login.loginToProfile();
		testSteps.log(Status.INFO, "clickCBROption");
		agentHome.clickCBROption();
		testSteps.log(Status.INFO, "switchToNewTab");
		agentHome.switchToNewTab();
		testSteps.log(Status.INFO, "clickCallOptionToMakeCall");
		cbrPage.clickCallOptionToMakeCall();
		testSteps.log(Status.INFO, "checkOutBoundCall");
		String number = agentHome.checkOutBoundCall();
		testSteps.log(Status.INFO, "switchToDefaultTab");
		agentHome.switchToDefaultTab();
		testSteps.log(Status.INFO, "clickCallHistoryOption");
		agentHome.clickCallHistoryOption();
		DateFormat logindf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
		Date dateobj = new Date();
		System.out.println("LoggedIn time for" + profileName + "is ::" + logindf.format(dateobj));
		testSteps.log(Status.INFO, "switchToNewTab");
		agentHome.switchToNewTab();
		testSteps.log(Status.INFO, "selectLastMonthInDateFilter");
		callHistory.selectLastMonthInDateFilter();
		testSteps.log(Status.INFO, "verifyCallDetailsAreDisplayedInCallHistoryForOutBound");
		String time = callHistory.verifyCallDetailAreDisplayedInCallHistoryForOutBound(number);
		System.out.println(time);

	}

	// in progress
	@Test
	public void verifyOutBoundCallFromTicketHistory() throws InterruptedException {
		testSteps = testCase.createTest("verifyOutBoundCallFromTicketHistory", "verifyOutBoundCallFromTicketHistory");
		Login login = new Login(driver, profileName, userName, passWord);
		AgentHomePage agentHome = new AgentHomePage(driver, profileName, userName, passWord);
		CrmPage crmPage = new CrmPage(driver, profileName, userName, passWord);
		CallBackRequestsPage cbrPage = new CallBackRequestsPage(driver, profileName, userName, passWord);
		CallHistoryPage callHistory = new CallHistoryPage(driver, profileName, userName, passWord);
		TicketHistoryPage ticketHistory = new TicketHistoryPage(driver, profileName, userName, passWord);
		
		testSteps.log(Status.INFO, "loginToProfile");
		login.loginToProfile();
		testSteps.log(Status.INFO, "clickTicketHistoryOption");
		agentHome.clickTicketHistoryOption();
		testSteps.log(Status.INFO, "switchToNewTab");
		agentHome.switchToNewTab();
		testSteps.log(Status.INFO, "clickCallOptionToMakeCall");
		List<String> details = ticketHistory.callToCustomer();
		testSteps.log(Status.INFO, "checkOutBoundCall");
		String number = agentHome.checkOutBoundCall();
		testSteps.log(Status.INFO, "switchToDefaultTab");
		agentHome.switchToDefaultTab();
		testSteps.log(Status.INFO, "clickCallHistoryOption");
		agentHome.clickCallHistoryOption();
		DateFormat logindf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
		Date dateobj = new Date();
		System.out.println("LoggedIn time for" + profileName + "is ::" + logindf.format(dateobj));
		testSteps.log(Status.INFO, "switchToNewTab");
		agentHome.switchToNewTab();
		testSteps.log(Status.INFO, "selectLastMonthInDateFilter");
		callHistory.selectLastMonthInDateFilter();
		testSteps.log(Status.INFO, "verifyCallDetailsAreDisplayedInCallHistoryForOutBound");
		String time = callHistory.verifyCallDetailAreDisplayedInCallHistoryForOutBound(number);
		System.out.println(time);

	}
	
	
	@Test
	public void verifyHangUpCallFunctionalityFromAgentForOutBoundCallFromCRM() throws InterruptedException {
		testSteps = testCase.createTest("verifyHangUpCallFunctionalityFromAgentForOutBoundCallFromCRM", "verifyHangUpCallFunctionalityFromAgentForOutBoundCallFromCRM");

		Login login1 = new Login(driver, profileName, userName, passWord);
		AgentHomePage agentHome = new AgentHomePage(driver, profileName, userName, passWord);
		CrmPage crmPage = new CrmPage(driver, profileName, userName, passWord);
		CallHistoryPage callHistory = new CallHistoryPage(driver, profileName, userName, passWord);

		testSteps.log(Status.INFO, "loginToProfile");
		login1.loginToProfile();
		testSteps.log(Status.INFO, "isAgentReadyStateIsDisplayed");
		agentHome.isAgentReadyStateIsDisplayed();
		testSteps.log(Status.INFO, "clickCRMOption");
		agentHome.clickCRMOption();
		testSteps.log(Status.INFO, "switchToNewTab");
		agentHome.switchToNewTab();
		testSteps.log(Status.INFO, "clickCallButtonToMakeCall");
		crmPage.clickCallButtonToMakeCall();
		testSteps.log(Status.INFO, "checkOutBoundCall");
		String number = agentHome.hangUpCallFromAgentForOutBound();
		testSteps.log(Status.INFO, "switchToDefaultTab");
		agentHome.switchToDefaultTab();
		testSteps.log(Status.INFO, "clickCallHistoryOption");
		agentHome.clickCallHistoryOption();
		testSteps.log(Status.INFO, "switchToNewTab");
		agentHome.switchToNewTab();
		testSteps.log(Status.INFO, "selectLastMonthInDateFilter");
		callHistory.selectLastMonthInDateFilter();
		testSteps.log(Status.INFO, "verifyCallDetailsAreDisplayedInCallHistoryForOutBound");
		callHistory.verifyCallDetailsAreDisplayedInCallHistoryForOutBound(number);
	}

	// done
	// AgentHome
	@Test
	public void verifyRaiseNewTicketWhenAgentIsOnCallForOutBound() throws InterruptedException {
		Login login = new Login(driver, profileName, userName, passWord);
		AgentHomePage agentHome = new AgentHomePage(driver, profileName, userName, passWord);
		CrmPage crmPage = new CrmPage(driver, profileName, userName, passWord);
		TicketHistoryPage ticketHistory = new TicketHistoryPage(driver, profileName, userName, passWord);
		login.loginToProfile();
		agentHome.clickCRMOption();
		agentHome.switchToNewTab();
		String customerName = crmPage.clickCallButtonToMakeCall();
		List<String> ticketDetails = agentHome.verifyRaiseTicketWhenAgentIsOnCall();
		ticketDetails.add(customerName);
		System.out.println(" ticketDetails : "+ticketDetails);
		List<String> raisedTicketDetails = agentHome.raisedTicketDetails();
		System.out.println("............");
		System.out.println("raisedTicketDetails : "+raisedTicketDetails);
		Assert.assertTrue(ticketDetails.containsAll(raisedTicketDetails));

		agentHome.clickTicketHistoryOption();
		List<String> list = ticketHistory.verifyRaisedTicketDetails(ticketDetails.get(0));
		System.out.println("------------");
		System.out.println(list);
	}

	// done
	// AgentHome
	@Test
	public void verifyCallBackRequestFunctionalityForOutBound() throws InterruptedException {
		Login login = new Login(driver, profileName, userName, passWord);
		AgentHomePage agentHome = new AgentHomePage(driver, profileName, userName, passWord);
		CrmPage crmPage = new CrmPage(driver, profileName, userName, passWord);
		CallBackRequestsPage cbr = new CallBackRequestsPage(driver, profileName, userName, passWord);
		login.loginToProfile();
		agentHome.clickCRMOption();
		agentHome.switchToNewTab();
		crmPage.clickCallButtonToMakeCall();
		String notesForCbr = agentHome.raiseCallBackRequest();
		Assert.assertTrue(agentHome.verifyNotificationOfCBR());
		agentHome.clickCBROption();
		agentHome.switchToNewlyOpendWindowTab();
		Assert.assertTrue(cbr.verifyDetailsOfCBR(notesForCbr));
	}

	// priority 1
	// agent home
	@Test
	public void verifyMuteAndPauseOptionFunctionalityOnCallSidePanelForOutBound() throws InterruptedException {

	}

	// priority 1
	// agent home
	@Test
	public void verifyEditCustomerBasicDetailsWhenAgentIsOnCallForOutBound() throws InterruptedException {

	}

	// crm testcase
	@Test
	public void verifyOutBoundCallFromCallerDetailsOnCRM() {

	}

	@Test
	public void validateTheDialButtonFunctionalityWhetherTheCallIsGettingConnectedToUserWhenAgentClickOnDailButtonOnAgentHomeCampaign() {

	}

	@Test
	public void validateWhetherTheCallsAreRoutingAsPerManagerUploadedContactsOnAgentHomeCampaign() {

	}

	@Test
	public void verifyOutBoundCallFunctionalityOnAgentHomeCampaign() throws InterruptedException {
		testSteps = testCase.createTest("verifyOutBoundCallFunctionalityOnAgentHomeCampaign",
				"verifyOutBoundCallFunctionalityOnAgentHomeCampaign");
		ManagerDashboard md = new ManagerDashboard(driver, profileName, userName, passWord);
		CreateCampaign cc = new CreateCampaign(driver, profileName, userName, passWord);
		BrowserFunctions browesr = new BrowserFunctions();
		Login login = new Login(driver, profileName, userName, passWord);
		AgentHomePage agentPage = new AgentHomePage(driver, profileName, userName, passWord);

		login.loginToProfile();
		md.clickCreateCompaignIcon();
		String campaignNAme = cc.autoDialerCompaign();
		cc.startCampaign(campaignNAme);
		testSteps.log(Status.INFO, "openNewBrowser");
		WebDriver driver1 = browesr.openNewBrowser();

		testSteps.log(Status.INFO, "loginToNewAgentProfile");
		login.loginToCampaignAgent(driver1);
		agentPage.clickOnCamapignHomeOption(driver1);
		agentPage.switchToNewTab();

	}

	@Test
	public void verifyTheCallsThatWereLeftInTheCampaignOnAgentHomeCampaign() {

	}

	// ------------------------------------------------------------OutBOund
	// CAlls end

	// AgentHome
	@Test
	public void verifyWhetherTheAvgWaitTimeAndAvgCallDurationAndTheQueueIsAppearingCorrectlyOrNot() {

	}

	// AgentHome
	@Test
	public void verifyWhetherTheScriptsAreDiplayedBasedOnTheSkillsThatWereCreatedByManager() {

	}

	// in progress
	// AgentHome
	@Test
	public void verifySkillsAreDisplayedInAgentProfileAsPerTheSkillsAssignedByTheManagerWhileCreatingAnAgent()
			throws InterruptedException {
		Login login = new Login(driver, profileName, userName, passWord);
		AgentHomePage agentHome = new AgentHomePage(driver, profileName, userName, passWord);
		CrmPage crmPage = new CrmPage(driver, profileName, userName, passWord);
		AgentAndSkills agentSkilss = new AgentAndSkills(driver, profileName, userName, passWord);
		ManagerDashboard managerDashBoard = new ManagerDashboard(driver, profileName, userName, passWord);
		ManagerTest managetTest = new ManagerTest();
		login.loginToManagerProfile();

		managerDashBoard.clickAgentAndSkillsIcon();
		agentSkilss.createSkill();
		agentSkilss.verifyCreatedSkill();
		// managetTest.verifyCreateAgent();
	}

	// inprogress - getting issue while creating offline ticket
	// AgentHome
	@Test
	public void verifyRaiseOfflineTicket() throws InterruptedException {
		Login login = new Login(driver, profileName, userName, passWord);
		AgentHomePage agentHome = new AgentHomePage(driver, profileName, userName, passWord);
		CrmPage crmPage = new CrmPage(driver, profileName, userName, passWord);
		login.loginToProfile();
		agentHome.clickRaiseOfflineTicket();
		agentHome.switchToNewTab();
		String ticketSubject = crmPage.raiseOfflineTicket();
		crmPage.verifyRaisedTickeDetails(ticketSubject);
	}

	// done
	// AgentHome
	@Test
	public void verifyTheStatusChangeOfTheAgentIsGettingUpdatedOrNot() throws InterruptedException {
		Login login = new Login(driver, profileName, userName, passWord);
		AgentHomePage agentHome = new AgentHomePage(driver, profileName, userName, passWord);
		CrmPage crmPage = new CrmPage(driver, profileName, userName, passWord);
		login.loginToProfile();
		Assert.assertEquals(agentHome.agentStatus(), "Ready");
		agentHome.agentMovingToBreakStatus();
		Assert.assertEquals(agentHome.agentStatus(), "In Break");
		agentHome.agentMovingToReadyStatus();
		Assert.assertEquals(agentHome.agentStatus(), "Ready");
		agentHome.agentMovingToWorkAssignedStatus();
		Assert.assertEquals(agentHome.agentStatus(), "Work Assigned");
		agentHome.agentMovingToReadyStatus();
		Assert.assertEquals(agentHome.agentStatus(), "Ready");
	}

	// done
	// crm
	@Test
	public void verifyAddCustomerContactFromCRM() throws InterruptedException {
		Login login = new Login(driver, profileName, userName, passWord);
		AgentHomePage agentHome = new AgentHomePage(driver, profileName, userName, passWord);
		CrmPage crmPage = new CrmPage(driver, profileName, userName, passWord);
		login.loginToProfile();
		agentHome.clickCRMOption();
		agentHome.switchToNewTab();
		List<String> contactDetails = crmPage.addCustomerContact();
		System.out.println(contactDetails);
		System.out.println("-------------");
		List<String> savedDetails = crmPage.verifySavedContacts(contactDetails);
		System.out.println(savedDetails);
		Assert.assertTrue(contactDetails.containsAll(savedDetails));
	}

	// CallHistory
	@Test
	public void verifyTheFunctionalityOfFiltersPresentInCallHistory() {

	}

	// CallHistory
	@Test
	public void verifyFunctionalityOfDownloadToExcelOnCallHistory() {

	}

	// CallHistory
	@Test
	public void verifyFunctionalityOfDownloadFeedbackReportToExcelOnCallHistory() {

	}

	// CallHistory
	@Test
	public void verifySearchFunctionalityOnCallHistory() {

	}

	// CallHistory
	@Test
	public void verifyInBoundCallHistoryOnCallHistory() {

	}

	// CallHistory
	@Test
	public void verifyOutBoundCallHistoryOnCallHistory() {

	}

	// CBR History
	@Test
	public void verifySearchFunctionalityOnCBR_History() {

	}

	// CBR History
	@Test
	public void verifyHistoryPresentOnCBR_History() {

	}

	// Ticket History
	@Test
	public void VoiceMailHistoryDetialsOnVoiceMails() {

	}

	// VoiceMails
	@Test
	public void verifyFiltersOnVoiceMails() {

	}

	// VoiceMails
	@Test
	public void verifySearchfunctionalityPresentOnVoiceMails() {

	}

	// ScoreCard history
	@Test
	public void verifyScoreCardDetailsPresentOnScoreCardHistoryPage() {

	}

	// Ticket History
	@Test
	public void verifyFiltersPresentOnTicketHistoryPage() {

	}

	// Ticket History
	@Test
	public void verifySearchFieldOnTicketHistoryPage() {

	}

	// Ticket History
	@Test
	public void verifyTicketDetailsOnTicketHistoryPage() {

	}

	// priority 1
	// agent home
	@Test
	public void verifyAddToConferenceFunctionality() throws InterruptedException {
	}

	// priority 1
	// agent home
	@Test
	public void verifyWhetherTheScriptsAreDiplayedBasedOnTheSkillsThatWereCreatedByManagerOnAgentHomeCampaign() {

	}

	@Test
	public void verifyTheCustomerDetailsOnAgentHomeCampaign() {

	}

	@Test
	public void verifyTheCampaignProgressOnAgentHomeCampaign() {

	}

	// crm testcase

	@Test
	public void verifySearchFunctionalityOnCRM() {

	}

	// crm testcase

	@Test
	public void verifyCreateGroupOnCRM() {

	}

	// crm testcase

	@Test
	public void verifyCreateLabelOnCRM() {

	}

	// crm testcase

	@Test
	public void verifyCreateGroupFromMoveOptionOnCRM() {

	}

	// crm testcase

	@Test
	public void verifyCreateLabelFromAddOptionOnCRM() {

	}

	// crm testcase

	@Test
	public void verifyRaiseOfflineTicketOnCRM() {

	}

	@Test
	public void verifyRaiseTicketWhenCustomerIsInOnCallForInBound() {

	}

	// crm testcase

	@Test
	public void verifyDownloadToExcelFunctionalityOnCRM() {

	}

	// crm testcase

	@Test
	public void verifySelectedRecordsAreDisplayingOrNotOnCRM() {

	}

	// crm testcase

	@Test
	public void verifyRaiseOfflineTicketFromCallerDetailsOnCRM() {

	}

	// agent profile
	@Test
	public void createContactFromAgentProfile() throws InterruptedException {
		Login login = new Login(driver, profileName, userName, passWord);
		AgentHomePage agentHome = new AgentHomePage(driver, profileName, userName, passWord);
		CrmPage crmPage = new CrmPage(driver, profileName, userName, passWord);
		CallHistoryPage callHistory = new CallHistoryPage(driver, profileName, userName, passWord);
		login.loginToProfile();
		agentHome.navigateToProfile();
		agentHome.switchToNewTab();
		agentHome.createContact();
	}

	// agent profile
	@Test
	public void verifyEditAgentProfile() {

	}

	// @Test
	// public void waitMethod() throws InterruptedException {
	// if (profileName.equalsIgnoreCase("Profile 2") ||
	// profileName.equalsIgnoreCase("Profile 3")
	// || profileName.equalsIgnoreCase("Profile 4") ||
	// profileName.equalsIgnoreCase("Profile 5")
	// || profileName.equalsIgnoreCase("Profile 6")) {
	// Thread.sleep(8000);
	// }
	// }

	// @Test
	// public void loginlogout() throws InterruptedException {
	// AgentHomePage agentPage = new AgentHomePage(driver, profileName,
	// userName, passWord);
	// login.loginToProfile();
	// agentPage.agentLogOut();
	// }

	// @Test
	// public void allMembers() throws InterruptedException {
	// System.out.println("all contact members in " + profileName + " are : " +
	// onCall);
	// }

	// --------------------------------------------------------------------------Mock
	// requests
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
					+ "\",\"toNumber\":\"04023008609\"}";
			request.body(sourcekey.toString());
			Response response = request.post("Mock/");
			System.out.println("response for post resquest is ::" + response.getStatusCode());
			Thread.sleep(3000);
		}
	}

	public void postRequestForUnlimittedCallDuration() throws InterruptedException {
		if (profileName.equalsIgnoreCase("Profile 2")) {

			RestAssured.baseURI = "http://192.168.72.155:8888/v0.1/";
			RequestSpecification request = RestAssured.given();
			long number = 9666361000l;
			String sourcekey = "[";
			for (int i = 0; i < numberOfCustomers; i++) {
				long fromNumber = (number + i);
				if (i != numberOfCustomers - 1)
					sourcekey = sourcekey + "{\"source\":\"Press3\",\"fromNumber\":\"" + fromNumber
							+ "\",\"toNumber\":\"04023008623\",\"callDuration\":\"20\",\"loginType\":\"online\",\"hangupBeforeAnswer\":\"false\"},";
				else
					sourcekey = sourcekey + "{\"source\":\"Press3\",\"fromNumber\":\"" + fromNumber
							+ "\",\"toNumber\":\"04023008623\",\"callDuration\":\"20\",\"loginType\":\"online\",\"hangupBeforeAnswer\":\"false\"}";
			}
			sourcekey = sourcekey + "]";
			request.body(sourcekey.toString());
			Response response = request.post("Mock/");
			System.out.println("response for post resquest is ::" + response.getStatusCode());
			Thread.sleep(3000);
		}
	}

	public void generateInBoundJson(int customers) throws InterruptedException {
		if (profileName.equalsIgnoreCase("Profile 2")) {
			RestAssured.baseURI = "http://192.168.72.155:8888/v0.1/";
			RequestSpecification request = RestAssured.given();
			long number = 9666361000l;
			String sourcekey = "[";
			for (int i = 0; i < customers; i++) {
				long fromNumber = (number + i);
				if (i != customers - 1)
					sourcekey = sourcekey + "{\"source\":\"Press3\",\"fromNumber\":\"" + fromNumber
							+ "\",\"toNumber\":\"04023008612\",\"callDuration\":\"20\",\"loginType\":\"online\",\"hangupBeforeAnswer\":\"false\"},";
				else
					sourcekey = sourcekey + "{\"source\":\"Press3\",\"fromNumber\":\"" + fromNumber
							+ "\",\"toNumber\":\"04023008612\",\"callDuration\":\"20\",\"loginType\":\"online\",\"hangupBeforeAnswer\":\"false\"}";
			}
			sourcekey = sourcekey + "]";
			request.body(sourcekey.toString());
			Response response = request.post("Mock/");
			System.out.println("response for post resquest is ::" + response.getStatusCode());
			// Thread.sleep(2000);
		}
	}

	// public void generateInBoundJsonForOneCustomer() throws
	// InterruptedException {
	// if (profileName.equalsIgnoreCase("Profile 2")) {
	// RestAssured.baseURI = "http://192.168.72.155:8888/v0.1/";
	// RequestSpecification request = RestAssured.given();
	// long number = 9666361000l;
	// String sourcekey = "[";
	// for (int i = 0; i < oneCustomer; i++) {
	// long fromNumber = (number + i);
	// if (i != oneCustomer - 1)
	// sourcekey = sourcekey + "{\"source\":\"Press3\",\"fromNumber\":\"" +
	// fromNumber
	// +
	// "\",\"toNumber\":\"04023008609\",\"callDuration\":\"500\",\"loginType\":\"online\",\"hangupBeforeAnswer\":\"false\"},";
	// else
	// sourcekey = sourcekey + "{\"source\":\"Press3\",\"fromNumber\":\"" +
	// fromNumber
	// +
	// "\",\"toNumber\":\"04023008609\",\"callDuration\":\"500\",\"loginType\":\"online\",\"hangupBeforeAnswer\":\"false\"}";
	// }
	// sourcekey = sourcekey + "]";
	// request.body(sourcekey.toString());
	// Response response = request.post("Mock/");
	// System.out.println("response for post resquest is ::" +
	// response.getStatusCode());
	// //Thread.sleep(2000);
	// }
	// }

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
				map.put((number + i) + "", keys.charAt(randomKey) + "");
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
			// String keys = "12";
			// Random r = new Random();
			int k = 1;
			for (int i = 0; i < numberOfCustomers; i++) {
				// int randomKey = r.nextInt(keys.length());
				if (i != numberOfCustomers - 1)
					fromNumber = fromNumber + (number + i) + ",";
				else
					fromNumber = fromNumber + (number + i);
				map1.put((number + i) + "", k + "");
				if (k < 2) {
					k++;
				} else {
					k = 1;
				}
			}

			System.out.println(Collections.frequency(map1.values(), 1) + " Pressed 1");
			System.out.println(Collections.frequency(map1.values(), 2) + " Pressed 2");

			String sourcekey = "{\"source\":\"Press3\",\"fromNumber\":\"" + fromNumber
					+ "\",\"toNumber\":\"04023008623\"}";
			request.body(sourcekey.toString());
			Response response = request.post("Mock/");
			System.out.println("response for post resquest for calls is :: " + response.getStatusCode());
			Thread.sleep(3000);
		}
		return map1;
	}

	public void handlePostRequestForMenuDigits(Map<String, String> map) throws InterruptedException {
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
	// --------------------------------------------------------------------------Mock
	// requests
}
