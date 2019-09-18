package press3Test;

import org.testng.annotations.Test;
import press3Pages.ManagerPages.AgentAndSkills;
import press3Pages.ManagerPages.CreateCampaign;
import press3Pages.ManagerPages.GeneralSettings;
import press3Pages.ManagerPages.IvrStudios;
import press3Pages.ManagerPages.ManagerDashboard;
import press3Pages.agentPages.Login;
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
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import press3AutomationTest.NewTest;
import press3Initialzers_Processors.storeProfilesData;
import press3Pages.agentPages.AgentHomePage;
import press3Pages.agentPages.CallHistoryPage;
import press3Pages.agentPages.CrmPage;
import press3Pages.agentPages.Login;

public class ManagerTest extends BrowserFunctions {

	public static String projectDirectory = System.getProperty("user.dir");
	public static ExtentReports testCase;
	public static ExtentTest testSteps;
	public static String userDirectory;
	public List<String> onCall = new ArrayList<String>();
	public List<String> hangUp = new ArrayList<String>();
	public static int numberOfCustomers = 3;
	public static int customerCount = 0;
	public int noOfCallsTakenByProfile;
	final static Logger logger = Logger.getLogger(NewTest.class);
	public static Map<String, String> map = null;
	public static Map<String, String> map1 = new LinkedHashMap<String, String>();

	ManagerDashboard md = new ManagerDashboard(driver, profileName, userName, passWord);
	AgentAndSkills as = new AgentAndSkills(driver, profileName, userName, passWord);
	CreateCampaign cc = new CreateCampaign(driver, profileName, userName, passWord);
	IvrStudios ivr = new IvrStudios(driver, profileName, userName, passWord);
	GeneralSettings gs = new GeneralSettings();
	// AgentHomePage agentHome = new AgentHomePage(driver, profileName,
	// userName, passWord);
	// CrmPage crmPage = new CrmPage(driver, profileName, userName, passWord);
	// CallHistoryPage callHistory = new CallHistoryPage();
	// Login login = new Login(driver, profileName, userName, passWord);
	
	
	public void verifyCreateScript() throws InterruptedException{
		ManagerDashboard md = new ManagerDashboard(driver, profileName, userName, passWord);
		AgentAndSkills as = new AgentAndSkills(driver, profileName, userName, passWord);
		Login login = new Login(driver, profileName, userName, passWord);
		login.loginToProfile();
		
	}

	public void verifyCreateAgent() throws InterruptedException {
		ManagerDashboard md = new ManagerDashboard(driver, profileName, userName, passWord);
		AgentAndSkills as = new AgentAndSkills(driver, profileName, userName, passWord);
		Login login = new Login(driver, profileName, userName, passWord);
		login.loginToProfile();
		md.clickAgentAndSkillsIcon();
		as.clickPlusIconForNewSkill();
		as.createNewSkill();
		as.addNewGroup();
		as.addNewAgent();
		md.logoutManager();
		as.verifyAgentDetails();
		as.verifySkillAndSkillGroup();
	}

	public void verifyCreateCompaign() throws InterruptedException {
		ManagerDashboard md = new ManagerDashboard(driver, profileName, userName, passWord);
		CreateCampaign cc = new CreateCampaign(driver, profileName, userName, passWord);
		Login login = new Login(driver, profileName, userName, passWord);
		login.loginToProfile();
		md.clickCreateCompaignIcon();
		cc.autoDialerCompaign();
		md.clickCreateCompaignIcon();
		cc.voiceBroadcastCompaign();
		md.clickCreateCompaignIcon();
		cc.ivrSurveyCompaign();
		md.clickCreateCompaignIcon();
		cc.IvrConnectCompaign();
	}
	
	public void createNewIvr() throws InterruptedException {
		ManagerDashboard md = new ManagerDashboard(driver, profileName, userName, passWord);
		IvrStudios ivr = new IvrStudios(driver, profileName, userName, passWord);
		Login login = new Login(driver, profileName, userName, passWord);
		login.loginToProfile();
		md.clickIvrStudioIcon();
		ivr.createNewIvrStudio();
		ivr.setPlayMessge();
		ivr.setRingUser();
		ivr.createNewIvr();
	}

	public void createMenuIvr() throws InterruptedException {
		ManagerDashboard md = new ManagerDashboard(driver, profileName, userName, passWord);
		IvrStudios ivr = new IvrStudios(driver, profileName, userName, passWord);
		Login login = new Login(driver, profileName, userName, passWord);
		login.loginToProfile();
		md.clickIvrStudioIcon();
		ivr.createNewIvrStudio();
		ivr.setPlayMessge();
		ivr.setMenu();
		ivr.setRingUserForMenuIvr();
		// ivr.setmenu1();
		// ivr.menu1EndFlow();
		ivr.setMenu2();
		ivr.menu2Endflow();
		ivr.setMenu3();
		ivr.menu3EndFlow();
	}

	public void genSettings() throws InterruptedException {
		md.clickOnGenSettings();
		gs.serviceLevel();
		gs.clickSmsAndEmailTemlete();
		gs.smsTemplate();
		gs.emailTemplate();
		gs.clickOnTicketManagement();
		gs.statusTicketManagement();
		gs.priorityTicketManagement();
		gs.ticketNotificationsTicketManagement();
		gs.clickCallerBasicDetails();
		gs.callerBasicDetails();

	}

	public void verifyChangeProfileStatus_break_ready_workAssigned() throws InterruptedException {
		ManagerDashboard md = new ManagerDashboard(driver, profileName, userName, passWord);
		Login login = new Login(driver, profileName, userName, passWord);
		login.loginToProfile();
		md.profileStatus_break();
		md.profileStatus_ready();
		md.profileStatus_workAssigned();
		md.profileStatus_break();
		md.profileStatus_ready();
		md.profileStatus_workAssigned();
	}

	@Test
	public void verifyManagerProfileDetails() throws InterruptedException {
		ManagerDashboard md = new ManagerDashboard(driver, profileName, userName, passWord);
		Login login = new Login(driver, profileName, userName, passWord);
		login.loginToProfile();
		md.clickOnMyProfile();
		Assert.assertEquals(md.getProfileName(), md.getProfileNameFromMyProfile());

	}

	// Manager
	public void verifyTheStatusChangeOfManager() {

	}

	// Manager dashboard
	public void verifyDetailsOnManagerDashBoard() {

	}

	// Manager dashboard
	public void verifySupervisorViewOnManagerDashBoard() {

	}

	// Manager dashboard
	public void verifyAverageHandleTimeByHourDetailsOnManagerDashBoard() {

	}

	// Manager dashboard
	public void verifyAverageTalkTimeVsWaitTimeByHourDetailsOnManagerDashBoard() {

	}

	// Manager dashboard
	public void verifyCallReportByHourDetailsOnManagerDashBoard() {

	}
	// campaign dashboard
	public void verifyTheDetailsOnCampaignDashBoard() {

	}

	// -------------------

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
	public void verifyOutBoundCallFromCallerDetailsOnCRM() {

	}

	// crm testcase

	@Test
	public void verifyRaiseOfflineTicketFromCallerDetailsOnCRM() {

	}

	// crm testcase
	@Test
	public void verifyAddCustomerContactFromCRM() throws InterruptedException {
	}

	// crm testCase
	@Test
	public void verifyOutBoundCallFromCRM() throws InterruptedException {
		Login login = new Login(driver, profileName, userName, passWord);
		AgentHomePage agentHome = new AgentHomePage(driver, profileName, userName, passWord);
		CrmPage crmPage = new CrmPage(driver, profileName, userName, passWord);
		login.loginToProfile();
		agentHome.clickCRMOption();
		agentHome.switchToNewTab();
		crmPage.clickCallButtonToMakeCall();
		agentHome.checkOutBoundCall();
	}

	// ----------------------

	// low priority
	// agent home
	@Test
	public void verifyWhetherTheAvgWaitTimeAndAvgCallDurationAndTheQueueIsAppearingCorrectlyOrNot() {

	}

	// low priority
	// agent home
	@Test
	public void verifyWhetherTheScriptsAreDiplayedBasedOnTheSkillsThatWereCreatedByManager() {

	}

	// low priority
	// agent home
	@Test
	public void verifyRaiseOfflineTicket() throws InterruptedException {
	}

	// agent home

	@Test
	public void verifyRaiseNewTicket() throws InterruptedException {
	}

	// ---------------------

	// CallHistory

	@Test
	public void verifyOutBoundCallFromCallHistory() throws InterruptedException {
		Login login = new Login(driver, profileName, userName, passWord);
		AgentHomePage agentHome = new AgentHomePage(driver, profileName, userName, passWord);
		CrmPage crmPage = new CrmPage(driver, profileName, userName, passWord);
		CallHistoryPage callHistory = new CallHistoryPage(driver, profileName, userName, passWord);
		login.loginToProfile();
		agentHome.clickCallHistoryOption();
		agentHome.switchToNewTab();
		callHistory.selectLastMonthInDateFilter();
		callHistory.clickCallOptionToMakeCall();
		agentHome.checkOutBoundCall();
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

	// ---------------------

	// CBR History
	@Test
	public void verifyOutBoundCallFromCBR() throws InterruptedException {
	}

	// CBR History
	@Test
	public void verifyCBRFunctionality() {

	}

	// CBR History
	@Test
	public void verifySearchFunctionalityOnCBR_History() {

	}

	// CBR History
	@Test
	public void verifyHistoryPresentOnCBR_History() {

	}

	// ---------------------

	// AgentHistory
	@Test
	public void verifyTheFunctionalityOfFiltersPresentInAgentHistory() {

	}

	// AgentHistory
	@Test
	public void verifyFunctionalityOfDownloadToExcelOnAgentHistory() {

	}

	// AgentHistory
	@Test
	public void verifySearchFunctionalityOnAgentHistory() {

	}

	// AgentHistory
	@Test
	public void verifyOutBoundCallFromAgentPRofileOnAgentHistory() {

	}
	// ---------------

	// VoiceMails
	@Test
	public void verifyVoiceMailHistoryDetialsOnVoiceMails() {

	}

	// VoiceMails
	@Test
	public void verifyFiltersOnVoiceMails() {

	}

	// VoiceMails
	@Test
	public void verifySearchfunctionalityPresentOnVoiceMails() {

	}

	// ---------------

	// ScoreCard history
	@Test
	public void verifyScoreCardDetailsPresentOnScoreCardHistoryPage() {

	}

	// ---------------

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

	// ---------------

	// campaign history
	@Test
	public void verifyFiltersPresentOnCampaignHistory() {

	}

	// campaign history
	@Test
	public void verifyCampaignHistoryReportsOnCampaignHistory() {

	}
	// ---------------

	// CallcenterPerformanceReports
	@Test
	public void verifyCallcenterPerformanceReportsInMonthlyReports() {

	}

	// AgentPerformance
	@Test
	public void verifyAgentPerformanceDetailsInMonthlyReports() {

	}

	// TicketsOverview
	@Test
	public void verifyTicketsOverviewDetailsInMonthlyReports() {

	}
	// ---------------

	// agent
	@Test
	public void verifyFunctionalityOfCallTranferToNormalMObileNumber() throws InterruptedException {

	}

	// agent
	@Test
	public void verifyWarmTranferFunctionality() throws InterruptedException {
	}

	// agent
	@Test
	public void verifyAddToConferenceFunctionality() throws InterruptedException {
	}

	// agent
	@Test
	public void verifyCallBackRequestFunctionality() throws InterruptedException {
	}

	// agent
	@Test
	public void verifyMuteOptionFunctionalityOnCallSidePanel() throws InterruptedException {
	}

	// agent
	@Test
	public void verifyPauseOptionFunctionalityOnCallSidePanel() throws InterruptedException {

	}

	// agent
	@Test
	public void verifyEditCustomerBasicDetailsWhenAgentIsOnCall() throws InterruptedException {

	}

	// agent
	@Test
	public void verifyWhetherTheScriptsAreDiplayedBasedOnTheSkillsThatWereCreatedByManagerOnAgentHomeCampaign() {

	}

	// // agent
	// @Test
	// public void
	// validateTheDialButtonFunctionalityWhetherTheCallIsGettingConnectedToUserWhenAgentClickOnDailButtonOnAgentHomeCampaign()
	// {
	//
	// }

	// // agent
	// @Test
	// public void
	// validateWhetherTheCallsAreRoutingAsPerManagerUploadedContactsOnAgentHomeCampaign()
	// {
	//
	// }
	//
	// // agent
	// @Test
	// public void verifyCallFunctionalityOnAgentHomeCampaign() {
	//
	// }
	//
	// // agent
	// @Test
	// public void verifyTheCallsThatWereLeftInTheCampaignOnAgentHomeCampaign()
	// {
	//
	// }
	//
	// @Test
	// public void verifyTheCustomerDetailsOnAgentHomeCampaign() {
	//
	// }
	//
	// @Test
	// public void verifyTheCampaignProgressOnAgentHomeCampaign() {
	//
	// }

	// ---------------------------

	// manager profile

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

	// manager Profile
	@Test
	public void verifyOutBoundCallFromAgentProfile() throws InterruptedException {
		Login login = new Login(driver, profileName, userName, passWord);
		AgentHomePage agentHome = new AgentHomePage(driver, profileName, userName, passWord);
		CrmPage crmPage = new CrmPage(driver, profileName, userName, passWord);
		CallHistoryPage callHistory = new CallHistoryPage(driver, profileName, userName, passWord);
		login.loginToProfile();
		agentHome.navigateToProfile();
		agentHome.switchToNewTab();
		agentHome.clickMyContactsTab();
		agentHome.makeCallToContact();
		agentHome.checkOutBoundCall();
	}

	// manager profile
	@Test
	public void verifyEditManagerProfile() {

	}
	// ---------------

	// agent & skills
	public void verifyCreateSkillOnAgentAndSkillsPage() {

	}

	// agent & skills
	public void verifyCreateSkillGroupOnAgentAndSkillsPage() {

	}

	// agent & skills
	public void verifyCreateAgentAsPSTN_OnAgentAndSkillsPage() {

	}

	// agent & skills
	public void verifyCreateAgentAsExternalSIP_AccountOnAgentAndSkillsPage() {

	}

	// agent & skills
	public void verifyCreateAgentAsPress3SoftPhoneOnAgentAndSkillsPage() {

	}

	// agent & skills
	public void verifyCreateManagerOnAgentAndSkillsPage() {

	}

	// agent & skills
	public void verifyCreateSupervisorOnAgentAndSkillsPage() {

	}

	// agent & skills
	public void verifyTeamManagementFunctionalityOnAgentAndSkillsPage() {

	}

	// agent & skills
	public void verifyManageTimeSlotsFunctionalityOnAgentAndSkillsPage() {

	}

	// IVR_Studio
	public void verifyCreateIVROnIvr_StudioPage() {

	}

	// IVR_Studio
	public void verifyUpdateIVROnIvr_StudioPage() {

	}

	// Scripts
	public void verifyCreateSCriptOnScriptsPage() {

	}

	// Scripts
	public void verifyUpdateScriptOnScriptsPage() {

	}

	// ScoreCard
	public void verifyCreateScoreCardOnScoreCardsPage() {

	}

	// Scripts
	public void verifyUpdateScoreCardOnScoreCardsPage() {

	}

	// CreateCampaign
	public void verifyCreateCampaignOnCreateCampaignPage() {

	}

	// CreateCampaign
	public void verifyAutoDiallerFunctionalityOnCreateCampaignPage() {

	}

	// CreateCampaign
	public void verifyVoiceBroadcastFunctionalityOnCreateCampaignPage() {

	}

	// CreateCampaign
	public void verifyIVR_SurveyfunctionalityOnCreateCampaignPage() {

	}

	// CreateCampaign
	public void verifyIVR_ConnectFunctionalityOnCreateCampaignPage() {

	}

	// ManageAgentDids
	public void verifyAddCallerID_FunctionalityOnManageAgentDids() {

	}

	// ManageAgentDids
	public void verifyEditAgentDids_FunctionalityOnManageAgentDids() {

	}

	// NumberManagement
	public void verifyCreateNewGateWayOnNumberManagementPage() {

	}

	// NumberManagement
	public void verifySerachAvailableNumbersOnNumberManagementPage() {

	}

	// GeneralSettings
	public void verifyServiceLevelFunctionalityOnGeneralSettingsPage() {

	}

	// GeneralSettings
	public void verifySMS_TemplatesFunctionalityOnGeneralSettingsPage() {

	}

	// GeneralSettings
	public void verifyEmailTemplatesFunctionalityOnGeneralSettingsPage() {

	}

	// GeneralSettings
	public void verifyAccessControlFunctionalityOnGeneralSettingsPage() {

	}

	// GeneralSettings
	public void verifyCallerBasicDetailsAndCRM_SourceFunctionalityOnGeneralSettingsPage() {

	}

	// GeneralSettings
	public void verifyTicketManagementFunctionalityOnGeneralSettingsPage() {

	}

	// GeneralSettings
	public void verifycreateCategoryFunctionalityOnGeneralSettingsPage() {

	}
	// -------------
}
