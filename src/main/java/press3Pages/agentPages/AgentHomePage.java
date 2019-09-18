package press3Pages.agentPages;

import static org.testng.Assert.assertEquals;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import press3Test.AgentTest;

public class AgentHomePage {
	public WebDriver driver;
	public String profileName;
	public String userName;
	public String passWord;
	final static Logger logger = Logger.getLogger(AgentHomePage.class);
	public Map<String, Integer> counterMap = new HashMap<String, Integer>();

	public AgentHomePage(WebDriver driver, String profileName, String userName, String passWord) {
		this.driver = driver;
		this.profileName = profileName;
		this.userName = userName;
		this.passWord = passWord;
	}

	// agent home elements
	public By waitingCallsInQueue = By.id("waitingCalls");
	public By avgWaitTime = By.id("avgWaitTime");
	public By avgCallDuration = By.id("avgCallDuration");

	// agent profile fields
	public By agentStatus = By.id("spanAgentStatus");
	public By agentProfileName = By.id("spanAgentName");
	public By inBreakStatus = By.xpath("//*[@status='In Break']");
	public By readyStatus = By.xpath("//*[@status='Ready']");
	public By workAssignedStatus = By.xpath("//*[@status='Work Assigned']");
	public By myProfile = By.xpath("//*[@class='icon-user']");
	public By logOut = By.id("logout_modal");
	public By invisibility = By.id("btnOk");
	public By logOutOnOverlay = By.xpath("//*[@id='myModal']//*[@value='Log Out']");

	// edit customer details fileds for inbound
	public By editButtonForCustomerDetails = By.id("editCallerDetails");
	public By searchFieldForCustomerDetails = By.id("txtSearch");
	public By searchButtonForCustomerDetails = By.id("btnSearch");
	public By mobileNumber = By.xpath("//*[@id='newcaller']//tr[2]//td[2]");
	public By newCallerNameField = By.xpath("//*[@id='newcaller']//tr[1]//td[2]");
	public By customerEmailField = By.xpath("//*[@id='newcaller']//tr[3]//td[2]");
	public By customerStudentId = By.xpath("//*[@id='newcaller']//tr[4]//td[2]");

	// edit/Update Caller Details
	public By nameFieldOnUpdateCallerOverlay = By.id("txtCallerName");
	public By moblieFiledOnUpdateCallerOverlay = By.id("//*[@id='txtCallerMobile']");
	public By emailFiledOnUpdateCallerOverlay = By.id("//*[@id='txtCallerEmail']");
	public By studentIdFiledOnUpdateCallerOverlay = By.xpath("//*[@class='AlphaNumerics txtRequired restrictChar']");
	public By alternateNuberFiledOnUpdateCallerOverlay = By.id("//*[@id='txtalternatenumber']");
	public By updateButtonOnUpdateCallerOverlay = By.id("//*[@id='btnUpdateCallerdetails']");
	public By cancleButtonOnUpdateCallerOverlay = By.id("//*[@id='btnCancelUpdateCallerDetails']");
	public By plusSymbolOnUpdateCallerOverlay = By
			.xpath("//*[@class='icon-plus text-blue pointer AddAlternatenumber']");
	public By oneMoreAlternateNumberFieldOnUpdateCallerOverlay = By.id("//*[@id='indNumber_1']");

	public ArrayList editCustomerDetails() throws InterruptedException {
		String customerName = "Test_" + System.currentTimeMillis();
		String studentId = "" + System.currentTimeMillis();
		String customerEmail = "email" + System.currentTimeMillis();
		ArrayList listOfCustomerDetails = new ArrayList();
		driver.findElement(editButtonForCustomerDetails).click();
		Thread.sleep(2000);
		driver.findElement(nameFieldOnUpdateCallerOverlay).sendKeys(customerName);
		String mobile = driver.findElement(moblieFiledOnUpdateCallerOverlay).getAttribute("value");
		driver.findElement(emailFiledOnUpdateCallerOverlay).sendKeys(studentId);
		driver.findElement(studentIdFiledOnUpdateCallerOverlay).sendKeys(customerEmail);

		driver.findElement(updateButtonOnUpdateCallerOverlay).click();
		listOfCustomerDetails.add(customerName);
		listOfCustomerDetails.add(mobile);
		listOfCustomerDetails.add(customerEmail);
		listOfCustomerDetails.add(studentId);
		return listOfCustomerDetails;
	}

	public ArrayList verifySavedCustomerDetails() throws InterruptedException {
		ArrayList listOfCustomerDetails = new ArrayList();
		listOfCustomerDetails.add(driver.findElement(newCallerNameField).getText());
		listOfCustomerDetails.add(driver.findElement(mobileNumber).getText());
		listOfCustomerDetails.add(driver.findElement(customerEmailField).getText());
		listOfCustomerDetails.add(driver.findElement(customerStudentId).getText());
		return listOfCustomerDetails;
	}

	// edit customer details fields for outbound
	public By customerNameForOutBound = By.xpath("//*[@id='tblViewCallerDetails']//tr[2]//td[2]");
	public By customerMobileNumberForOutBound = By.xpath("//*[@id='tblViewCallerDetails']//tr[2]//td[2]");
	public By customerNameField = By.xpath("//*[@id='tblViewCallerDetails']//tr[1]//td[2]");
	public By customerEmailFieldForOutBound = By.xpath("//*[@id='tblViewCallerDetails']//tr[3]//td[2]");
	public By customerStudentIdForOutBound = By.xpath("//*[@id='tblViewCallerDetails']//tr[6]//td[2]");

	public void isElementsPresentOnAgentPageForOutBound() {
		driver.findElement(editButtonForCustomerDetails).isDisplayed();
		driver.findElement(customerMobileNumberForOutBound).isDisplayed();
		driver.findElement(customerNameForOutBound).isDisplayed();
		driver.findElement(customerStudentIdForOutBound).isDisplayed();
		driver.findElement(waitingCallsInQueue).isDisplayed();
		driver.findElement(raiseNewButton).isDisplayed();
		driver.findElement(scriptsDropdown).isDisplayed();
		driver.findElement(scriptSectionDropDown).isDisplayed();
		driver.findElement(scriptTopics).isDisplayed();
		System.out.println("isElementsPresentOnAgentPageForoutBound = " + profileName);
	}

	// scripts elements
	public By scriptsDropdown = By.id("ddlScripts");
	public By scriptSectionDropDown = By.id("ddlSections");
	public By scriptTopics = By.id("ddlTopics");

	public By mobileNumberForOutBound = By.xpath("//*[@class='scroller']//tr[2]//td[2]");
	public By inBoundCallInProgress = By.xpath(
			"//*[@id='divSidePanel']/div[3]//*[@class='divHangUpCall']//*[contains(@class,'btn btn-sm btn-danger')]");

	// home elements
	public By homeIcon = By.className("icon-home");
	public By campaignHomeOption = By.xpath("//*[@id='SideBarToggle1']//*[@class='sub-menu']//*[@class='title']");

	public void clickOnCamapignHomeOption(WebDriver driver1) throws InterruptedException {
		WebDriver temp = this.driver;
		this.driver = driver1;
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(homeIcon)).perform();
		driver.findElement(campaignHomeOption).click();
		Thread.sleep(1000);
		this.driver = temp;
	}

	// history elements
	public By historyIcon = By.id("SideBarToggle2");
	public By callHistoryOption = By
			.xpath("//*[@id='SideBarToggle2']//*[@class='nav-item'][1]//*[@class='nav-link '][1]");
	public By cbrHistoryOption = By
			.xpath("//*[@id='SideBarToggle2']//*[@class='nav-item'][2]//*[@class='nav-link '][1]");
	public By voiceMailOption = By
			.xpath("//*[@id='SideBarToggle2']//*[@class='nav-item'][3]//*[@class='nav-link '][1]");
	public By scoreCardHistoryOption = By
			.xpath("//*[@id='SideBarToggle2']//*[@class='nav-item'][4]//*[@class='nav-link '][1]");
	public By ticketHistoryOption = By
			.xpath("//*[@id='SideBarToggle2']//*[@class='nav-item'][5]//*[@class='nav-link '][1]");

	// crm elements
	public By crmIcon = By.className("icon-users");
	public By crmOption = By.xpath("//*[@id='SideBarToggle3']//*[@class='title']");

	// OffLineTicket element
	public By raiseOffLineTicket = By.id("btnOfflineTicket");

	// public By divConference =
	// By.xpath("//*[@id='divConference']//*[@class='btn btn-sm btn-w-lg
	// lite_blue']");

	// div1 elements on call side panel
	public By connectingTocustomerTextOnCallPAnel = By.xpath("//*[@id='callFrom']");
	public By ivrNAme = By.xpath("//*[@id='spn-ivr-details']");
	public By incomingCallFromMsg = By.xpath("//*[@id='answerModal']//*[@id='callFrom']");
	public By mobileNumberOnSidePanel = By.xpath("//*[@id='answerModal']//*[@id='modalHeader']");

	// div2 elements on call side panel
	public By communicationPanelText = By.xpath("//*[@id='divSidePanel']/*[@class='mt-0 f_15']");
	public By outBoundCallInProgressText = By.xpath("//*[@id='lbl-call-type']");
	public By muteOption = By.xpath("//*[@class='fa fa-microphone-slash']");
	public By pauseOption = By.xpath("//*[@class='fa fa-pause']");
	public By unMuteOption = By.xpath("//*[@class='fa fa-microphone']");
	public By playOption = By.xpath("//*[@class='fa fa-play']");
	public By timer = By.xpath("//*[@id='divSidePanel']/*[@class='call_time']");
	public By callTransfer = By.xpath("//*[@class='input-group margin-bottom-5']//*[@class='btn btn-sm lite_blue']");
	public By warmTransferCall = By.xpath("//*[@id='divwarmTransferCall']//*[@class='btn btn-sm btn-w-lg lite_blue']");
	public By addToConference = By.xpath("//*[@id='divConference']//*[@class='btn btn-sm btn-w-lg lite_blue']");
	public By callBackRequest = By.xpath("//*[@id='divCallbackRequest']//*[@class='btn btn-sm btn-w-lg lite_blue']");
	public By wrapUpCallButton = By.xpath(
			"//*[@id='divSidePanel']/div[3]//*[@class='divHangUpCall']//*[contains(@class,'btn btn-sm btn-danger')]");

	// div3 elements on call side panel
	public By callDisconnectedText = By.xpath("//*[@id='divSidePanel']/*[@class='call-prog blocked margin-bottom-10']");
	public By readyForNextCallButton = By.xpath(
			"//*[@id='divSidePanel']/div[3]//*[contains(@class,'divSubmitAcwNew')]//*[contains(@class,'btnActionSubmitAcw')]");

	public By takeMeToBreakButton = By
			.xpath("//*[@id='divSidePanel']//div[3]//*[@class='divsubmitbreak']//*[@class='btn btn-green']");

	// online ticket elements
	public By raiseNewButton = By.id("newToken");
	public By category = By.xpath("//*[@id='categoryInput']");
	// public By clickButtonForCategoryOption = By
	// .xpath("//*[@id='comboTree564367DropDownContainer']//*[@class='ComboTreeItemChlid']//*[@id='4474']");
	public By clickButtonForCategoryOption = By
			.xpath("//*[@class='modal-content text-left']//*[@class='comboTreeDropDownContainer']//li[1]");
	public By categoryText = By
			.xpath("//*[@class='modal-content text-left']//*[@class='comboTreeDropDownContainer']//li[1]");
	public By prioritty = By.id("ddlPriority");
	public By subject = By.id("txtSubject");
	public By description = By.id("txtDescription");
	public By create = By.id("btnCreateTicket");
	public By searchTicketNumberField = By.id("txtTicketNumber");
	public By ticketStatus = By.id("ddlTicketStatus");
	public By ticketPriority = By.id("ddlPriorities");
	public By ticketsTab = By.id("btnTokens");
	public By callsTab = By.id("btnCallHistory");

	// public By editCustomerDetails = By.xpath("//*[@class='fa fa-edit']");
	// public By searchField = By.id("txtSearch");
	// public By searchButton = By.id("btnSearch");

	// create contact fields
	public By myContactsButtonOnAgentProfile = By.xpath("//*[@class='nav nav-tabs ']//li[2]");
	public By createNewContact = By.id("newContact");
	public By nameField = By.id("txtContactName");
	public By mobileField = By.id("txtMobile");
	public By alternateMobileField = By.id("txtAlternateMobile");
	public By emailField = By.id("txtContactEmail");
	public By notesField = By.id("txtNote");
	public By newGroupField = By.id("txtNewGroup");
	public By saveButton = By.id("btnNewContact");

	// make call to contact field
	public By contactName = By.xpath("//*[@id='contatsTable']//td[1]");
	public By selectMobileNumber = By.xpath("//*[@id='mobiledatadetails']//input[1]");
	public By confirmButton = By.id("btnConfirm");

	// createCallBackRequest fields
	public By dateAndTimeFieldOnCBR = By.id("txtDateTime");
	public By dateAndTimeText = By.xpath("//*[@id='callBackRequest']//*[@class='modal-body f_13']//div[1]//label[1]");
	public By notesTabOnCBr = By.id("taNotes");
	public By submitButtonOnCBR = By.id("btnSubmitCallBackRequest");
	public By selectHours = By.xpath("//*[@class='datetimepicker-hours']//*[@class=' table-condensed']//span");
	public By selectMinutes = By.xpath("//*[@class='datetimepicker-minutes']//*[@class=' table-condensed']//span");
	public By cbrNotifier = By.id("unreadNotifier");

	// raisedTicketDetails fields
	public By raisedTickets = By.xpath("//*[@id='ticketHistory']//*[contains(@class,'ticket_panel ')]");
	public By ticketName = By.xpath("//*[@class='pull-left']//*[@class='ticketDetails']");
	public By ticketDescription = By.xpath("//*[@class='col-sm-10']//*[@class='margin-left-20']");
	public By categoryType = By.xpath("//*[@class='ticket_cate pull-left']//li[2]");
	public By callerName = By.xpath("//*[@class='margin-left-5 text-underline']");
	public By ticketHistoryText = By.xpath("//*[@id='tokens']//*[@class='txt-lite-grey bold']");

	public boolean verifyNotificationOfCBR() {
		return driver.findElement(cbrNotifier).isDisplayed();
	}

	public void isAgentReadyStateIsDisplayed() {
		Assert.assertEquals(driver.findElement(agentStatus).getText(), "Ready");
		System.out.println("success  = " + profileName);
	}

	public void isAgentAssignedStateIsDisplayed() {
		Assert.assertEquals(driver.findElement(agentStatus).getText(), "Assigned");
	}

	public void isAgentWrapUpStateIsDisplayed() {
		Assert.assertEquals(driver.findElement(agentStatus).getText(), "Wrap Up");
	}

	public void isAgentOnCallStateIsDisplayed() {
		Assert.assertEquals(driver.findElement(agentStatus).getText(), "On Call");
	}

	public void isAgentOnlineStateIsDisplayed() {
		Assert.assertEquals(driver.findElement(agentStatus).getText(), "Online");
	}

	public void isAgentBreakStateIsDisplayed() {
		Assert.assertEquals(driver.findElement(agentStatus).getText(), "In Break");
	}

	public List<String> raisedTicketDetails() throws InterruptedException {
		Thread.sleep(10000);
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(ticketHistoryText)).perform();
		List<WebElement> list = driver.findElements(raisedTickets);
		List<String> ticketDetails = new ArrayList<String>();
		for (WebElement details : list) {
			Actions action1 = new Actions(driver);
			action1.moveToElement(details).perform();
			String s = details.findElement(ticketName).getText();
			if (details.findElement(ticketName).getText().contains(subjectName)) {
				String subject = details.findElement(ticketName).getText();
				String description = details.findElement(ticketDescription).getText();
				String categoryName = details.findElement(categoryType).getText();
				String callerName1 = details.findElement(callerName).getText();
				ticketDetails.add(subject);
				ticketDetails.add(categoryName);
				ticketDetails.add(description);
				ticketDetails.add(callerName1);
				break;
			}
		}
		return ticketDetails;
	}

	public void isElementsPresentOnAgentPageForInBound() {
		driver.findElement(editButtonForCustomerDetails).isDisplayed();
		driver.findElement(searchFieldForCustomerDetails).isDisplayed();
		driver.findElement(searchButtonForCustomerDetails).isDisplayed();
		driver.findElement(mobileNumber).isDisplayed();
		driver.findElement(waitingCallsInQueue).isDisplayed();
		driver.findElement(raiseNewButton).isDisplayed();
		driver.findElement(scriptsDropdown).isDisplayed();
		driver.findElement(scriptSectionDropDown).isDisplayed();
		driver.findElement(scriptTopics).isDisplayed();
		System.out.println("isElementsPresentOnAgentPageForInBound = " + profileName);
	}

	public String agentStatus() throws InterruptedException {
		Thread.sleep(1000);
		return driver.findElement(agentStatus).getText();
	}

	public void selectDate() throws InterruptedException {
		WebElement mytable = driver
				.findElement(By.xpath("//*[@class='datetimepicker-days']//*[@class=' table-condensed']//tbody"));
		List<WebElement> rows_table = mytable.findElements(By.tagName("tr"));
		boolean result = false;
		int rows_count = rows_table.size();
		for (int row = 0; row < rows_count; row++) {
			List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td"));
			int columns_count = Columns_row.size();
			for (int column = 0; column < columns_count; column++) {
				String celtext = Columns_row.get(column).getText();
				if (Columns_row.get(column).getAttribute("class").contains("day active")) {
					Columns_row.get(column).click();
					result = true;
					break;
				}
			}
			if (result == true) {
				break;
			}

		}
		Thread.sleep(1000);
		List<WebElement> hoursTable = driver.findElements(selectHours);
		for (WebElement ele : hoursTable) {
			if (ele.getAttribute("class").equals("hour")) {
				ele.click();
				break;
			}
		}
		Thread.sleep(1000);
		List<WebElement> minutesTable = driver.findElements(selectMinutes);
		for (WebElement ele1 : minutesTable) {
			if (ele1.getAttribute("class").equals("minute")) {
				ele1.click();
				break;
			}
		}

	}

	public String createCallBackRequest() throws InterruptedException {
		String notesForCallBackRequest = "test " + System.currentTimeMillis();
		driver.findElement(callBackRequest).click();
		Thread.sleep(1000);
		driver.findElement(dateAndTimeFieldOnCBR).click();
		selectDate();
		driver.findElement(dateAndTimeText).click();
		driver.findElement(notesTabOnCBr).sendKeys(notesForCallBackRequest);
		driver.findElement(submitButtonOnCBR).click();
		return notesForCallBackRequest;
	}

	public void makeCallToContact() throws InterruptedException {
		List<WebElement> contacts = driver.findElements(contactName);
		for (WebElement contact : contacts) {
			System.out.println(contact.getText());
			if (contact.getText().contains("srikanth")) {
				contact.findElement(By.xpath(".//*[@type='button']")).click();
				break;
			}
		}
		Thread.sleep(1000);
		driver.findElement(selectMobileNumber).click();
		driver.findElement(confirmButton).click();
		Thread.sleep(6000);
	}

	public void navigateToProfile() throws InterruptedException {
		Thread.sleep(2000);
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(agentProfileName)).perform();
		Thread.sleep(2000);
		driver.findElement(myProfile).click();
		Thread.sleep(2000);

	}

	public void clickMyContactsTab() throws InterruptedException {
		driver.findElement(myContactsButtonOnAgentProfile).click();
	}

	public void createContact() throws InterruptedException {
		driver.findElement(myContactsButtonOnAgentProfile).click();
		driver.findElement(createNewContact).click();
		Thread.sleep(1000);
		driver.findElement(nameField).sendKeys("srikanth");
		driver.findElement(mobileField).sendKeys("8331996838");
		driver.findElement(saveButton).click();
		driver.switchTo().alert().accept();
	}

	public void clickRaiseOfflineTicket() {
		driver.findElement(raiseOffLineTicket).click();
	}

	String subject1 = "test " + System.currentTimeMillis();
	String subjectName = subject1;
	String description1 = "automation " + System.currentTimeMillis();
	String descrition = description1;

	public List<String> raiseNewTicketWhenAgentIsOnCall() throws InterruptedException {
		List<String> ticketDetails = new ArrayList<String>();
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(raiseNewButton)).perform();
		driver.findElement(raiseNewButton).click();
		Thread.sleep(2000);
		driver.findElement(category).click();
		Thread.sleep(1000);
		String categoryText1 = driver.findElement(categoryText).getText();
		driver.findElement(clickButtonForCategoryOption).click();
		Select ele = new Select(driver.findElement(prioritty));
		ele.selectByValue("10");

		ticketDetails.add(subject1);
		ticketDetails.add(description1);
		ticketDetails.add(categoryText1);

		driver.findElement(subject).sendKeys(subject1);
		driver.findElement(description).sendKeys(description1);
		driver.findElement(create).click();
		Thread.sleep(5000);
		return ticketDetails;
	}

	public void clickCRMOption() throws InterruptedException {
		Thread.sleep(1000);
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(crmIcon)).perform();
		Thread.sleep(1000);
		driver.findElement(crmOption).click();
	}

	public void switchToNewTab() throws InterruptedException {
		Set<String> AllwindowHandles = driver.getWindowHandles();
		String w1 = (String) AllwindowHandles.toArray()[0];
		String w2 = (String) AllwindowHandles.toArray()[1];
		driver.switchTo().window(w2);
		Thread.sleep(2000);
	}

	public void switchToNewlyOpendWindowTab() throws InterruptedException {
		Set<String> AllwindowHandles = driver.getWindowHandles();
		String w1 = (String) AllwindowHandles.toArray()[0];
		String w2 = (String) AllwindowHandles.toArray()[1];
		String w3 = (String) AllwindowHandles.toArray()[2];
		driver.switchTo().window(w3);
		Thread.sleep(2000);
	}

	public int numberOfAvailableTabs() throws InterruptedException {
		Set<String> AllwindowHandles = driver.getWindowHandles();
		int count = AllwindowHandles.size();
		return count;
	}

	public void switchToDefaultTab() throws InterruptedException {
		// Set<String> AllwindowHandles=driver.getWindowHandles();
		// String w1=(String) AllwindowHandles.toArray()[0];
		// String w2=(String) AllwindowHandles.toArray()[1];
		// driver.switchTo().window(w1);
		// Thread.sleep(2000);

		Set<String> AllwindowHandles = driver.getWindowHandles();
		String w1 = (String) AllwindowHandles.toArray()[0];
		driver.switchTo().window(w1);
		Thread.sleep(2000);

		// for (String winHandle : driver.getWindowHandles()) {
		// driver.switchTo().window(winHandle);
		// }
	}

	public void closeCurrentTab() {
		driver.close();
	}

	public void clickCallHistoryOption() throws InterruptedException {
		Thread.sleep(1000);
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(historyIcon)).perform();
		driver.findElement(callHistoryOption).click();
	}

	public void clickTicketHistoryOption() {
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(historyIcon)).perform();
		driver.findElement(ticketHistoryOption).click();
	}

	public void clickCBROption() {
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(historyIcon)).perform();
		driver.findElement(cbrHistoryOption).click();
	}

	public void agentMovingToBreakStatus() throws InterruptedException {
		// Thread.sleep(2000);
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(agentProfileName)).perform();
		Thread.sleep(2000);
		driver.findElement(inBreakStatus).click();
		Thread.sleep(2000);
	}

	public void agentLogOut() throws InterruptedException {
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

	public void newAgentLogOut(WebDriver driver1) throws InterruptedException {
		WebDriver temp = this.driver;
		this.driver = driver1;
		Thread.sleep(2000);
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(agentProfileName)).perform();
		// driver.findElement(logOut).click();
		Thread.sleep(3000);
		WebElement element = driver.findElement(logOut);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		Thread.sleep(2000);
		// driver.findElement(logOutOnOverlay).click();
		WebElement element1 = driver.findElement(logOutOnOverlay);
		JavascriptExecutor executor1 = (JavascriptExecutor) driver;
		executor1.executeScript("arguments[0].click();", element1);
		Thread.sleep(3000);
		this.driver = temp;
	}

	public void agentMovingToWorkAssignedStatus() throws InterruptedException {
		Thread.sleep(2000);
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(agentProfileName)).perform();
		Thread.sleep(2000);
		driver.findElement(workAssignedStatus).click();
		Thread.sleep(1000);
	}

	public void agentMovingToReadyStatus() throws InterruptedException {
		Thread.sleep(2000);
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(agentProfileName)).perform();
		Thread.sleep(2000);
		driver.findElement(readyStatus).click();
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

	public boolean verifyAgentGettingAnyCall() throws InterruptedException {
		boolean gettingCall = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 70);
			wait.until(ExpectedConditions.visibilityOfElementLocated(inBoundCallInProgress));
			gettingCall = true;
			System.out.println("agents are getting calls");
		} catch (TimeoutException eTO) {
			System.out.println("agents are not getting calls");
			gettingCall = false;
		}
		return gettingCall;
	}

	// public void handleAlert() throws InterruptedException {
	// WebElement element = driver.findElement(By.id("btnOk"));
	// JavascriptExecutor executor1 = (JavascriptExecutor)driver;
	// executor1.executeScript("arguments[0].click();", element);
	// Thread.sleep(2000);
	// }

	public String waitingCallsInQueue() {
		return driver.findElement(waitingCallsInQueue).getText();
	}

	public void div1() {
		isAgentAssignedStateIsDisplayed();
		Assert.assertTrue(driver.findElement(ivrNAme).isDisplayed());
		Assert.assertTrue(driver.findElement(incomingCallFromMsg).isDisplayed());
		Assert.assertTrue(driver.findElement(mobileNumberOnSidePanel).isDisplayed());
		System.out.println("div1 = " + profileName);
	}

	public void elementsShouldNotBeDisplayedForWarmTranferAgent() {
		Assert.assertFalse(driver.findElement(incomingCallFromMsg).isDisplayed());
		Assert.assertFalse(driver.findElement(mobileNumberOnSidePanel).isDisplayed());
		System.out.println("elementsShouldNotBeDisplayedForWarmTranferAgent = " + profileName);
	}

	public void elementsShouldNotBeDisplayedInDiv1() {
		Assert.assertFalse(driver.findElement(communicationPanelText).isDisplayed());
		Assert.assertFalse(driver.findElement(outBoundCallInProgressText).isDisplayed());
		Assert.assertFalse(driver.findElement(timer).isDisplayed());
		Assert.assertFalse(driver.findElement(pauseOption).isDisplayed());
		Assert.assertFalse(driver.findElement(muteOption).isDisplayed());
		Assert.assertFalse(driver.findElement(callTransfer).isDisplayed());
		Assert.assertFalse(driver.findElement(warmTransferCall).isDisplayed());
		Assert.assertFalse(driver.findElement(addToConference).isDisplayed());
		Assert.assertFalse(driver.findElement(callBackRequest).isDisplayed());
		Assert.assertFalse(driver.findElement(wrapUpCallButton).isDisplayed());
		Assert.assertFalse(driver.findElement(outBoundCallInProgressText).isDisplayed());
		Assert.assertFalse(driver.findElement(readyForNextCallButton).isDisplayed());
		System.out.println("elementsShouldNotBeDisplayedInDiv1 = " + profileName);
	}

	public void div2() {
		isAgentOnCallStateIsDisplayed();
		Assert.assertTrue(driver.findElement(ivrNAme).isDisplayed());
		Assert.assertTrue(driver.findElement(communicationPanelText).isDisplayed());
		Assert.assertTrue(driver.findElement(outBoundCallInProgressText).isDisplayed());
		Assert.assertTrue(driver.findElement(timer).isDisplayed());
		Assert.assertTrue(driver.findElement(pauseOption).isDisplayed());
		Assert.assertTrue(driver.findElement(muteOption).isDisplayed());
		Assert.assertTrue(driver.findElement(callTransfer).isDisplayed());
		Assert.assertTrue(driver.findElement(warmTransferCall).isDisplayed());
		Assert.assertTrue(driver.findElement(addToConference).isDisplayed());
		Assert.assertTrue(driver.findElement(addToConference).isDisplayed());
		Assert.assertTrue(driver.findElement(callBackRequest).isDisplayed());
		Assert.assertTrue(driver.findElement(wrapUpCallButton).isDisplayed());
		System.out.println("div2 = " + profileName);
	}

	public void elementsShouldNotBeDisplayedInDiv2() {
		// Assert.assertFalse(driver.findElement(outBoundCallInProgressText).isDisplayed());
		Assert.assertFalse(driver.findElement(readyForNextCallButton).isDisplayed());
		Assert.assertFalse(driver.findElement(incomingCallFromMsg).isDisplayed());
		Assert.assertFalse(driver.findElement(mobileNumberOnSidePanel).isDisplayed());
		System.out.println("elementsShouldNotBeDisplayedInDiv2 = " + profileName);
	}

	public void div3() {
		isAgentWrapUpStateIsDisplayed();
		Assert.assertTrue(driver.findElement(ivrNAme).isDisplayed());
		Assert.assertTrue(driver.findElement(communicationPanelText).isDisplayed());
		Assert.assertTrue(driver.findElement(outBoundCallInProgressText).isDisplayed());
		Assert.assertTrue(driver.findElement(timer).isDisplayed());
		Assert.assertTrue(driver.findElement(callBackRequest).isDisplayed());
		Assert.assertTrue(driver.findElement(readyForNextCallButton).isDisplayed());
		System.out.println("div3 = " + profileName);
	}

	public void elementsShouldNotBeDisplayedInDiv3() {
		Assert.assertFalse(driver.findElement(incomingCallFromMsg).isDisplayed());
		Assert.assertFalse(driver.findElement(mobileNumberOnSidePanel).isDisplayed());

		// Assert.assertTrue(driver.findElement(outBoundCallInProgressText).isDisplayed());
		Assert.assertFalse(driver.findElement(pauseOption).isDisplayed());
		Assert.assertFalse(driver.findElement(muteOption).isDisplayed());
		Assert.assertFalse(driver.findElement(callTransfer).isDisplayed());
		Assert.assertFalse(driver.findElement(warmTransferCall).isDisplayed());
		Assert.assertFalse(driver.findElement(addToConference).isDisplayed());
		Assert.assertFalse(driver.findElement(wrapUpCallButton).isDisplayed());
		System.out.println("elementsShouldNotBeDisplayedInDiv3 = " + profileName);
	}

	public void div1ForOutBound() {
		// isAgentAssignedStateIsDisplayed();
		Assert.assertTrue(driver.findElement(connectingTocustomerTextOnCallPAnel).isDisplayed());
		Assert.assertTrue(driver.findElement(mobileNumberOnSidePanel).isDisplayed());
	}

	public void elementsShouldNotBeDisplayedInDiv1ForOutBound() {
		Assert.assertFalse(driver.findElement(communicationPanelText).isDisplayed());
		Assert.assertFalse(driver.findElement(outBoundCallInProgressText).isDisplayed());
		Assert.assertFalse(driver.findElement(timer).isDisplayed());
		Assert.assertFalse(driver.findElement(pauseOption).isDisplayed());
		Assert.assertFalse(driver.findElement(muteOption).isDisplayed());
		Assert.assertFalse(driver.findElement(callBackRequest).isDisplayed());
		Assert.assertFalse(driver.findElement(wrapUpCallButton).isDisplayed());

		Assert.assertFalse(driver.findElement(outBoundCallInProgressText).isDisplayed());
		Assert.assertFalse(driver.findElement(readyForNextCallButton).isDisplayed());
		System.out.println("this is div 1 success");
	}

	public void div2ForOutBound() {
		// isAgentOnCallStateIsDisplayed();
		Assert.assertTrue(driver.findElement(communicationPanelText).isDisplayed());
		Assert.assertTrue(driver.findElement(outBoundCallInProgressText).isDisplayed());
		Assert.assertTrue(driver.findElement(timer).isDisplayed());
		Assert.assertTrue(driver.findElement(pauseOption).isDisplayed());
		Assert.assertTrue(driver.findElement(muteOption).isDisplayed());
		Assert.assertTrue(driver.findElement(callBackRequest).isDisplayed());
		Assert.assertTrue(driver.findElement(wrapUpCallButton).isDisplayed());
	}

	public void elementsShouldNotBeDisplayedInDiv2ForOutBound() {
		Assert.assertFalse(driver.findElement(readyForNextCallButton).isDisplayed());
		Assert.assertFalse(driver.findElement(connectingTocustomerTextOnCallPAnel).isDisplayed());
		Assert.assertFalse(driver.findElement(mobileNumberOnSidePanel).isDisplayed());
		System.out.println("this is div 2 success");
	}

	public void div3ForOutBound() {
		// isAgentWrapUpStateIsDisplayed();
		Assert.assertTrue(driver.findElement(communicationPanelText).isDisplayed());
		Assert.assertTrue(driver.findElement(outBoundCallInProgressText).isDisplayed());
		Assert.assertTrue(driver.findElement(timer).isDisplayed());
		Assert.assertTrue(driver.findElement(callBackRequest).isDisplayed());
		Assert.assertTrue(driver.findElement(readyForNextCallButton).isDisplayed());
	}

	public void elementsShouldNotBeDisplayedInDiv3ForOutBound() {
		Assert.assertFalse(driver.findElement(connectingTocustomerTextOnCallPAnel).isDisplayed());
		Assert.assertFalse(driver.findElement(mobileNumberOnSidePanel).isDisplayed());

		Assert.assertFalse(driver.findElement(pauseOption).isDisplayed());
		Assert.assertFalse(driver.findElement(muteOption).isDisplayed());
		Assert.assertFalse(driver.findElement(wrapUpCallButton).isDisplayed());
		System.out.println("this is div 3 success");
	}

	public List<String> verifyRaiseTicketWhenAgentIsOnCall() throws InterruptedException {
		WebElement member;
		String memberNumber = "";
		int noOfCustomerHandledByAgent = 0;
		List<String> details = null;
		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.visibilityOfElementLocated(connectingTocustomerTextOnCallPAnel));
		div1ForOutBound();
		elementsShouldNotBeDisplayedInDiv1ForOutBound();

		wait.until(ExpectedConditions.visibilityOfElementLocated(wrapUpCallButton));
		Assert.assertTrue(driver.findElement(wrapUpCallButton).isDisplayed());

		if (driver.findElement(wrapUpCallButton).isDisplayed()) {
			div2ForOutBound();
			elementsShouldNotBeDisplayedInDiv2ForOutBound();

			details = raiseNewTicketWhenAgentIsOnCall();

			wait.until(ExpectedConditions.visibilityOfElementLocated(readyForNextCallButton));
			Assert.assertTrue(driver.findElement(readyForNextCallButton).isDisplayed());
		}
		return details;
	}

	public void verifyWarmTransferCallOnNewAgent(WebDriver driver, String number) {
		String customerNumber = null;
		int noOfCustomerHandledByAgent = 0;
		ArrayList details = new ArrayList();
		String profile = null;

		try {
			WebDriverWait wait = new WebDriverWait(driver, 100);
			wait.until(ExpectedConditions.visibilityOfElementLocated(incomingCallFromMsg));
			driver.findElement(mobileNumber).isDisplayed();
			customerNumber = driver.findElement(mobileNumber).getText();
			// Assert.assertEquals(customerNumber, number);
		} catch (Exception e) {

			System.out.println("unable to visibled incomingCallFromMsg for userName : " + userName + " profile is: "
					+ profileName + " exception is :::: " + e);

			// AgentTest.customerCount--;
			Assert.assertEquals(e, "success");
		}
		WebDriverWait wait = new WebDriverWait(driver, 100);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(incomingCallFromMsg));
	}

	public void searchFiledForCustomerDetails() {
		driver.findElement(editButtonForCustomerDetails).isDisplayed();
		driver.findElement(searchFieldForCustomerDetails).isDisplayed();
		driver.findElement(searchButtonForCustomerDetails).isDisplayed();

	}

	public void customerDetailsForOutBound() {
		driver.findElement(editButtonForCustomerDetails).isDisplayed();
		driver.findElement(searchFieldForCustomerDetails).isDisplayed();
		driver.findElement(searchButtonForCustomerDetails).isDisplayed();

	}

	public ArrayList verifyInBoundCallsOnAgentPage() throws InterruptedException {
		// WebElement member;
		int noOfCustomerHandledByAgent = 0;
		ArrayList details = new ArrayList();
		String profile = null;
		while (true) {

			if (AgentTest.customerCount >= AgentTest.numberOfCustomers) {
				System.out.println("Breaking the loop for " + AgentTest.customerCount + ": " + profileName);
				break;
			}
			int count = 0;
			try {
				WebDriverWait wait = new WebDriverWait(driver, 100);
				wait.until(ExpectedConditions.visibilityOfElementLocated(incomingCallFromMsg));
				AgentTest.customerCount++;
				count = AgentTest.customerCount;
				logger.info("starting loop for " + profileName + " is::" + count);
				System.out.println("starting loop for " + profileName + " is:: " + count);
				if (AgentTest.customerCount == AgentTest.numberOfCustomers) {
					profile = profileName;
				}
			} catch (Exception e) {

				System.out.println("unable to visibled incomingCallFromMsg for userName : " + userName + " profile is: "
						+ profileName + " exception is :::: " + e);
				// AgentTest.customerCount--;
				Assert.assertEquals(e, "success");
				break;
			}

			try {
				boolean goToOnCallState = false;
				long div1StartTime = System.currentTimeMillis();
				long div1EndTime = div1StartTime + 1000;
				System.out.println("div 1 assertions start : " + profileName);
				while (!goToOnCallState && System.currentTimeMillis() < div1EndTime) {
					if (!driver.findElement(wrapUpCallButton).isDisplayed()) {
						div1();
						elementsShouldNotBeDisplayedInDiv1();
					} else {
						goToOnCallState = true;
					}
					// Thread.sleep(1000);
				}
				System.out.println("div 1 assertions end : " + profileName);
			} catch (Exception e) {
				System.out.println(" profile is: " + profileName + " exception is :::: " + e);
				Assert.assertEquals(e, "success");
				break;
			}
			try {
				WebDriverWait wait = new WebDriverWait(driver, 100);
				wait.until(ExpectedConditions.visibilityOfElementLocated(wrapUpCallButton));

			} catch (Exception e) {
				System.out.println("unable to visibled wrapUpCallButton for userName : " + userName + " profile is: "
						+ profileName + " exception is :::: " + e);
				Assert.assertEquals(e, "success");
				break;
			}
			try {
				Assert.assertTrue(driver.findElement(wrapUpCallButton).isDisplayed());
				if (driver.findElement(wrapUpCallButton).isDisplayed()) {

					System.out.println("inBoundCallInProgress displayed for " + profileName + " in loop :: " + count);
					noOfCustomerHandledByAgent++;

					// driver.findElement(mobileNumber).isDisplayed();
					// driver.findElement(mobileNumber).getText();
					// System.out.println("mobilenumber in driver " +
					// profileName + " is::" + mobile);

					boolean goToReadyForNextCall = false;
					System.out.println("div 2 assertions start : " + profileName);
					long div2StartTime = System.currentTimeMillis();
					long div2EndTime = div2StartTime + 17000;
					while (!goToReadyForNextCall && System.currentTimeMillis() < div2EndTime) {
						if (!driver.findElement(readyForNextCallButton).isDisplayed()) {
							// searchFiledForCustomerDetails();
							div2();
							elementsShouldNotBeDisplayedInDiv2();
						} else {
							goToReadyForNextCall = true;
						}
						Thread.sleep(1000);
					}
					System.out.println("div 2 assertions end : " + profileName);
				}
			} catch (Exception e) {
				System.out.println(" profile is: " + profileName + " exception is :::: " + e);
				Assert.assertEquals(e, "success");
				break;
			}
			try {
				WebDriverWait wait1 = new WebDriverWait(driver, 100);
				wait1.until(ExpectedConditions.visibilityOfElementLocated(readyForNextCallButton));
			} catch (Exception e) {
				System.out.println("unable to visibled readyForNextCallButton for userName : " + userName
						+ " profile is: " + profileName + " exception is :::: " + e);
				Assert.assertEquals(e, "success");
				break;
			}
			try {
				Assert.assertTrue(driver.findElement(readyForNextCallButton).isDisplayed());
				System.out.println("div 3 assertions start : " + profileName);
				long div3StartTime = System.currentTimeMillis();
				long div3EndTime = div3StartTime + 4000;
				while (System.currentTimeMillis() < div3EndTime) {
					div3();
					elementsShouldNotBeDisplayedInDiv3();
					Thread.sleep(1000);
				}
				System.out.println("div 3 assertions end : " + profileName);
				System.out.println("readyForNextCallButton displayed for " + profileName + " in loop ::" + count
						+ " customers handled by agent::::: " + noOfCustomerHandledByAgent);
				Thread.sleep(1000);
				Actions action = new Actions(driver);
				Assert.assertTrue(driver.findElement(readyForNextCallButton).isDisplayed());
				WebElement element = driver.findElement(readyForNextCallButton);
				WebDriverWait wait = new WebDriverWait(driver, 5);
				wait.until(ExpectedConditions.elementToBeClickable(readyForNextCallButton));
				action.moveToElement(element).click(element).build().perform();
				// JavascriptExecutor executor = (JavascriptExecutor) driver;
				// executor.executeScript("arguments[0].click();", element);
				// executor.executeScript("document.getElement(By.xpath('//*[@id='divSidePanel']/div[3]//*[contains(@class,'btn
				// btn-green')]').click()");
				System.out.println("readyForNextCallButton is clicked for " + profileName + " in loop :: " + count);
				System.out.println(
						"noOfCustomerHandledByAgent for " + profileName + " is: " + noOfCustomerHandledByAgent);
				// Thread.sleep(1000);
				// isAgentReadyStateIsDisplayed();
			} catch (Exception e) {
				System.out.println(" profile is: " + profileName + " exception is :::: " + e);
				Assert.assertEquals(e, "success");
				break;
			}

			// Thread.sleep(1000);

			System.out.println(profileName + " with numberOfCustomers " + AgentTest.numberOfCustomers + " == "
					+ AgentTest.customerCount + " customerCount");

			System.out.println("local Profile " + profile + " Global Profile " + profileName);
			if (profile != null && profile.equals(profileName)) {
				// details.add(number);
				details.add(profile);
			}
		}
		// Thread.sleep(1000);

		System.out
				.println("total noOfCustomerHandledByAgent for " + profileName + " is: " + noOfCustomerHandledByAgent);
		details.add(noOfCustomerHandledByAgent);

		System.out.println(details);
		return details;
	}

	public ArrayList verifyInBoundCallsOnAgentPagesWhenOneAgentMovedToBreak() throws InterruptedException {
		// WebElement member;
		int noOfCustomerHandledByAgent = 0;
		ArrayList details = new ArrayList();
		String profile = null;
		while (true) {

			if (AgentTest.customerCount >= AgentTest.numberOfCustomers) {
				System.out.println("Breaking the loop for " + AgentTest.customerCount + ": " + profileName);
				break;
			}
			int count = 0;
			try {
				WebDriverWait wait = new WebDriverWait(driver, 100);
				wait.until(ExpectedConditions.visibilityOfElementLocated(incomingCallFromMsg));
				AgentTest.customerCount++;
				count = AgentTest.customerCount;
				logger.info("starting loop for " + profileName + " is::" + count);
				System.out.println("starting loop for " + profileName + " is:: " + count);
				if (AgentTest.customerCount == AgentTest.numberOfCustomers) {
					profile = profileName;
				}
			} catch (Exception e) {

				System.out.println("unable to visibled incomingCallFromMsg for userName : " + userName + " profile is: "
						+ profileName + " exception is :::: " + e);
				// AgentTest.customerCount--;
				Assert.assertEquals(e, "success");
				break;
			}

			try {
				boolean goToOnCallState = false;
				long div1StartTime = System.currentTimeMillis();
				long div1EndTime = div1StartTime + 1000;
				System.out.println("div 1 assertions start : " + profileName);
				while (!goToOnCallState && System.currentTimeMillis() < div1EndTime) {
					if (!driver.findElement(wrapUpCallButton).isDisplayed()) {
						div1();
						elementsShouldNotBeDisplayedInDiv1();
					} else {
						goToOnCallState = true;
					}
					// Thread.sleep(1000);
				}
				System.out.println("div 1 assertions end : " + profileName);
			} catch (Exception e) {
				System.out.println(" profile is: " + profileName + " exception is :::: " + e);
				Assert.assertEquals(e, "success");
				break;
			}
			try {
				WebDriverWait wait = new WebDriverWait(driver, 100);
				wait.until(ExpectedConditions.visibilityOfElementLocated(wrapUpCallButton));

			} catch (Exception e) {
				System.out.println("unable to visibled wrapUpCallButton for userName : " + userName + " profile is: "
						+ profileName + " exception is :::: " + e);
				Assert.assertEquals(e, "success");
				break;
			}
			try {
				Assert.assertTrue(driver.findElement(wrapUpCallButton).isDisplayed());
				if (driver.findElement(wrapUpCallButton).isDisplayed()) {

					System.out.println("inBoundCallInProgress displayed for " + profileName + " in loop :: " + count);
					noOfCustomerHandledByAgent++;

					// driver.findElement(mobileNumber).isDisplayed();
					// driver.findElement(mobileNumber).getText();
					// System.out.println("mobilenumber in driver " +
					// profileName + " is::" + mobile);

					boolean goToReadyForNextCall = false;
					System.out.println("div 2 assertions start : " + profileName);
					long div2StartTime = System.currentTimeMillis();
					long div2EndTime = div2StartTime + 17000;
					while (!goToReadyForNextCall && System.currentTimeMillis() < div2EndTime) {
						if (!driver.findElement(readyForNextCallButton).isDisplayed()) {
							// searchFiledForCustomerDetails();
							div2();
							elementsShouldNotBeDisplayedInDiv2();
						} else {
							goToReadyForNextCall = true;
						}
						Thread.sleep(1000);
					}
					System.out.println("div 2 assertions end : " + profileName);
				}
			} catch (Exception e) {
				System.out.println(" profile is: " + profileName + " exception is :::: " + e);
				Assert.assertEquals(e, "success");
				break;
			}
			try {
				WebDriverWait wait1 = new WebDriverWait(driver, 100);
				wait1.until(ExpectedConditions.visibilityOfElementLocated(readyForNextCallButton));
			} catch (Exception e) {
				System.out.println("unable to visibled readyForNextCallButton for userName : " + userName
						+ " profile is: " + profileName + " exception is :::: " + e);
				Assert.assertEquals(e, "success");
				break;
			}
			try {
				Assert.assertTrue(driver.findElement(readyForNextCallButton).isDisplayed());
				System.out.println("div 3 assertions start : " + profileName);
				long div3StartTime = System.currentTimeMillis();
				long div3EndTime = div3StartTime + 4000;
				while (System.currentTimeMillis() < div3EndTime) {
					div3();
					elementsShouldNotBeDisplayedInDiv3();
					Thread.sleep(1000);
				}
				System.out.println("div 3 assertions end : " + profileName);
				System.out.println("readyForNextCallButton displayed for " + profileName + " in loop ::" + count
						+ " customers handled by agent::::: " + noOfCustomerHandledByAgent);
				Thread.sleep(1000);
				Actions action = new Actions(driver);
				Assert.assertTrue(driver.findElement(readyForNextCallButton).isDisplayed());
				WebElement element = driver.findElement(readyForNextCallButton);
				WebDriverWait wait = new WebDriverWait(driver, 5);
				wait.until(ExpectedConditions.elementToBeClickable(readyForNextCallButton));

				if (profileName.equalsIgnoreCase("Profile 3")
						&& AgentTest.customerCount >= (AgentTest.numberOfCustomers / 2)) {
					driver.findElement(takeMeToBreakButton).isDisplayed();
					Actions action1 = new Actions(driver);
					action1.moveToElement(driver.findElement(takeMeToBreakButton)).click().build().perform();
					Thread.sleep(1000);
					break;
				}

				action.moveToElement(element).click(element).build().perform();
				System.out.println("readyForNextCallButton is clicked for " + profileName + " in loop :: " + count);
				System.out.println(
						"noOfCustomerHandledByAgent for " + profileName + " is: " + noOfCustomerHandledByAgent);
				// Thread.sleep(1000);
				// isAgentReadyStateIsDisplayed();
			} catch (Exception e) {
				System.out.println(" profile is: " + profileName + " exception is :::: " + e);
				Assert.assertEquals(e, "success");
				break;
			}

			// Thread.sleep(1000);

			System.out.println(profileName + " with numberOfCustomers " + AgentTest.numberOfCustomers + " == "
					+ AgentTest.customerCount + " customerCount");

			System.out.println("local Profile " + profile + " Global Profile " + profileName);
			if (profile != null && profile.equals(profileName)) {
				// details.add(number);
				details.add(profile);
			}

		}
		// Thread.sleep(1000);

		System.out
				.println("total noOfCustomerHandledByAgent for " + profileName + " is: " + noOfCustomerHandledByAgent);
		details.add(noOfCustomerHandledByAgent);

		System.out.println(details);
		return details;
	}

	public void verifyAgentGettingAnyCallsWhenHeMovedToBreakState() throws InterruptedException {
		if (profileName.equalsIgnoreCase("Profile 3")) {
			Assert.assertTrue(driver.findElement(agentStatus).getText().contains("In Break"));
			boolean notGettingCall = verifyAgentGettingAnyCall();
			Assert.assertFalse(notGettingCall);
			while (AgentTest.customerCount >= (AgentTest.numberOfCustomers)) {
				Thread.sleep(1000);
				boolean notGettingCall1 = verifyAgentGettingAnyCall();
				Assert.assertFalse(notGettingCall1);
			}
		}
	}

	public void verifyHangUpCallFromAgentPage() throws InterruptedException {
		int noOfCustomerHandledByAgent = 0;
		ArrayList details = new ArrayList();
		// String profile = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 100);
			wait.until(ExpectedConditions.visibilityOfElementLocated(incomingCallFromMsg));
		} catch (Exception e) {

			System.out.println("unable to visibled incomingCallFromMsg for userName : " + userName + " profile is: "
					+ profileName + " exception is :::: " + e);
			// AgentTest.customerCount--;
			Assert.assertEquals(e, "success");
		}

		try {
			boolean goToOnCallState = false;
			long div1StartTime = System.currentTimeMillis();
			long div1EndTime = div1StartTime + 1500;
			System.out.println("div 1 assertions start : " + profileName);
			while (!goToOnCallState && System.currentTimeMillis() < div1EndTime) {
				if (!driver.findElement(wrapUpCallButton).isDisplayed()) {
					div1();
					elementsShouldNotBeDisplayedInDiv1();
				} else {
					goToOnCallState = true;
				}
				// Thread.sleep(1000);
			}
			System.out.println("div 1 assertions end : " + profileName);
		} catch (Exception e) {
			System.out.println(" profile is: " + profileName + " exception is :::: " + e);
			Assert.assertEquals(e, "success");
		}
		try {
			WebDriverWait wait = new WebDriverWait(driver, 100);
			wait.until(ExpectedConditions.visibilityOfElementLocated(wrapUpCallButton));

		} catch (Exception e) {
			System.out.println("unable to visibled wrapUpCallButton for userName : " + userName + " profile is: "
					+ profileName + " exception is :::: " + e);
			Assert.assertEquals(e, "success");
		}
		try {
			Assert.assertTrue(driver.findElement(wrapUpCallButton).isDisplayed());
			if (driver.findElement(wrapUpCallButton).isDisplayed()) {

				// driver.findElement(mobileNumber).isDisplayed();
				// driver.findElement(mobileNumber).getText();
				// System.out.println("mobilenumber in driver " +
				// profileName + " is::" + mobile);

				boolean goToReadyForNextCall = false;
				System.out.println("div 2 assertions start : " + profileName);
				long div2StartTime = System.currentTimeMillis();
				long div2EndTime = div2StartTime + 10000;
				while (!goToReadyForNextCall && System.currentTimeMillis() < div2EndTime) {
					if (!driver.findElement(readyForNextCallButton).isDisplayed()) {
						// searchFiledForCustomerDetails();
						div2();
						elementsShouldNotBeDisplayedInDiv2();
					} else {
						goToReadyForNextCall = true;
					}
					Thread.sleep(1000);
				}
				System.out.println("div 2 assertions end : " + profileName);
			}
		} catch (Exception e) {
			System.out.println(" profile is: " + profileName + " exception is :::: " + e);
			Assert.assertEquals(e, "success");
		}
		try {
			driver.findElement(wrapUpCallButton).click();
			Thread.sleep(1000);
		} catch (Exception e) {
			System.out.println("unable to click wrapUpCallButton for userName : " + userName + " profile is: "
					+ profileName + " exception is :::: " + e);
			Assert.assertEquals(e, "success");
		}
		try {
			Assert.assertTrue(driver.findElement(readyForNextCallButton).isDisplayed());
			System.out.println("div 3 assertions start : " + profileName);
			long div3StartTime = System.currentTimeMillis();
			long div3EndTime = div3StartTime + 4000;
			while (System.currentTimeMillis() < div3EndTime) {
				div3();
				elementsShouldNotBeDisplayedInDiv3();
				Thread.sleep(1000);
			}
			System.out.println("div 3 assertions end : " + profileName);
			Thread.sleep(1000);
			Actions action = new Actions(driver);
			WebElement element = driver.findElement(readyForNextCallButton);
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.elementToBeClickable(readyForNextCallButton));
			action.moveToElement(element).click(element).build().perform();
			// isAgentReadyStateIsDisplayed();
		} catch (Exception e) {
			System.out.println(" profile is: " + profileName + " exception is :::: " + e);
			Assert.assertEquals(e, "success");
		}
	}

	public void verifyMuteAndPauseOptionsFunctionalityForInBoundCallsOnAgentPage() throws InterruptedException {
		// WebElement member;
		int noOfCustomerHandledByAgent = 0;
		ArrayList details = new ArrayList();
		String profile = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 100);
			wait.until(ExpectedConditions.visibilityOfElementLocated(incomingCallFromMsg));
			AgentTest.customerCount++;
		} catch (Exception e) {

			System.out.println("unable to visibled incomingCallFromMsg for userName : " + userName + " profile is: "
					+ profileName + " exception is :::: " + e);
			// AgentTest.customerCount--;
			Assert.assertEquals(e, "success");
		}

		try {
			boolean goToOnCallState = false;
			long div1StartTime = System.currentTimeMillis();
			long div1EndTime = div1StartTime + 1500;
			System.out.println("div 1 assertions start : " + profileName);
			while (!goToOnCallState && System.currentTimeMillis() < div1EndTime) {
				if (!driver.findElement(wrapUpCallButton).isDisplayed()) {
					div1();
					elementsShouldNotBeDisplayedInDiv1();
				} else {
					goToOnCallState = true;
				}
				// Thread.sleep(1000);
			}
			System.out.println("div 1 assertions end : " + profileName);
		} catch (Exception e) {
			System.out.println(" profile is: " + profileName + " exception is :::: " + e);
			Assert.assertEquals(e, "success");
		}
		try {
			WebDriverWait wait = new WebDriverWait(driver, 100);
			wait.until(ExpectedConditions.visibilityOfElementLocated(wrapUpCallButton));
			Thread.sleep(1000);
			driver.findElement(muteOption).click();
			Thread.sleep(1000);
			driver.findElement(pauseOption).click();
			Thread.sleep(1000);
			driver.findElement(unMuteOption).isDisplayed();
			driver.findElement(playOption).isDisplayed();
			driver.findElement(unMuteOption).click();
			Thread.sleep(1000);
			driver.findElement(playOption).click();
			Thread.sleep(1000);
			driver.findElement(muteOption).isDisplayed();
			driver.findElement(muteOption).isDisplayed();

		} catch (Exception e) {
			System.out.println("unable to visibled wrapUpCallButton for userName : " + userName + " profile is: "
					+ profileName + " exception is :::: " + e);
			Assert.assertEquals(e, "success");
		}
		try {
			Assert.assertTrue(driver.findElement(wrapUpCallButton).isDisplayed());
			if (driver.findElement(wrapUpCallButton).isDisplayed()) {

				boolean goToReadyForNextCall = false;
				System.out.println("div 2 assertions start : " + profileName);
				long div2StartTime = System.currentTimeMillis();
				long div2EndTime = div2StartTime + 8000;
				while (!goToReadyForNextCall && System.currentTimeMillis() < div2EndTime) {
					if (!driver.findElement(readyForNextCallButton).isDisplayed()) {
						// searchFiledForCustomerDetails();
						div2();
						elementsShouldNotBeDisplayedInDiv2();
					} else {
						goToReadyForNextCall = true;
					}
					Thread.sleep(1000);
				}
				System.out.println("div 2 assertions end : " + profileName);
			}
		} catch (Exception e) {
			System.out.println(" profile is: " + profileName + " exception is :::: " + e);
			Assert.assertEquals(e, "success");
		}
		try {
			WebDriverWait wait1 = new WebDriverWait(driver, 100);
			wait1.until(ExpectedConditions.visibilityOfElementLocated(readyForNextCallButton));
		} catch (Exception e) {
			System.out.println("unable to visibled readyForNextCallButton for userName : " + userName + " profile is: "
					+ profileName + " exception is :::: " + e);
			Assert.assertEquals(e, "success");
		}
		try {
			Assert.assertTrue(driver.findElement(readyForNextCallButton).isDisplayed());
			System.out.println("div 3 assertions start : " + profileName);
			long div3StartTime = System.currentTimeMillis();
			long div3EndTime = div3StartTime + 4000;
			while (System.currentTimeMillis() < div3EndTime) {
				div3();
				elementsShouldNotBeDisplayedInDiv3();
				Thread.sleep(1000);
			}
			System.out.println("div 3 assertions end : " + profileName);
			Thread.sleep(1000);
			Actions action = new Actions(driver);
			Assert.assertTrue(driver.findElement(readyForNextCallButton).isDisplayed());
			WebElement element = driver.findElement(readyForNextCallButton);
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.elementToBeClickable(readyForNextCallButton));
			action.moveToElement(element).click(element).build().perform();
			// isAgentReadyStateIsDisplayed();
		} catch (Exception e) {
			System.out.println(" profile is: " + profileName + " exception is :::: " + e);
			Assert.assertEquals(e, "success");
		}
	}

	public void verifyTakeMeToBreakFunctionalityForInBound() throws InterruptedException {
		// WebElement member;
		int noOfCustomerHandledByAgent = 0;
		ArrayList details = new ArrayList();
		String profile = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 100);
			wait.until(ExpectedConditions.visibilityOfElementLocated(incomingCallFromMsg));
			AgentTest.customerCount++;
		} catch (Exception e) {

			System.out.println("unable to visibled incomingCallFromMsg for userName : " + userName + " profile is: "
					+ profileName + " exception is :::: " + e);
			// AgentTest.customerCount--;
			Assert.assertEquals(e, "success");
		}

		try {
			boolean goToOnCallState = false;
			long div1StartTime = System.currentTimeMillis();
			long div1EndTime = div1StartTime + 1500;
			System.out.println("div 1 assertions start : " + profileName);
			while (!goToOnCallState && System.currentTimeMillis() < div1EndTime) {
				if (!driver.findElement(wrapUpCallButton).isDisplayed()) {
					div1();
					elementsShouldNotBeDisplayedInDiv1();
				} else {
					goToOnCallState = true;
				}
				// Thread.sleep(1000);
			}
			System.out.println("div 1 assertions end : " + profileName);
		} catch (Exception e) {
			System.out.println(" profile is: " + profileName + " exception is :::: " + e);
			Assert.assertEquals(e, "success");
		}
		try {
			WebDriverWait wait = new WebDriverWait(driver, 100);
			wait.until(ExpectedConditions.visibilityOfElementLocated(wrapUpCallButton));
			Thread.sleep(1000);

		} catch (Exception e) {
			System.out.println("unable to visibled wrapUpCallButton for userName : " + userName + " profile is: "
					+ profileName + " exception is :::: " + e);
			Assert.assertEquals(e, "success");
		}
		try {
			Assert.assertTrue(driver.findElement(wrapUpCallButton).isDisplayed());
			if (driver.findElement(wrapUpCallButton).isDisplayed()) {

				boolean goToReadyForNextCall = false;
				System.out.println("div 2 assertions start : " + profileName);
				long div2StartTime = System.currentTimeMillis();
				long div2EndTime = div2StartTime + 17000;
				while (!goToReadyForNextCall && System.currentTimeMillis() < div2EndTime) {
					if (!driver.findElement(readyForNextCallButton).isDisplayed()) {
						// searchFiledForCustomerDetails();
						div2();
						elementsShouldNotBeDisplayedInDiv2();
					} else {
						goToReadyForNextCall = true;
					}
					Thread.sleep(1000);
				}
				System.out.println("div 2 assertions end : " + profileName);
			}
		} catch (Exception e) {
			System.out.println(" profile is: " + profileName + " exception is :::: " + e);
			Assert.assertEquals(e, "success");
		}
		try {
			WebDriverWait wait1 = new WebDriverWait(driver, 100);
			wait1.until(ExpectedConditions.visibilityOfElementLocated(readyForNextCallButton));
		} catch (Exception e) {
			System.out.println("unable to visibled readyForNextCallButton for userName : " + userName + " profile is: "
					+ profileName + " exception is :::: " + e);
			Assert.assertEquals(e, "success");
		}
		try {
			Assert.assertTrue(driver.findElement(takeMeToBreakButton).isDisplayed());
			System.out.println("div 3 assertions start : " + profileName);
			long div3StartTime = System.currentTimeMillis();
			long div3EndTime = div3StartTime + 4000;
			while (System.currentTimeMillis() < div3EndTime) {
				div3();
				elementsShouldNotBeDisplayedInDiv3();
				Thread.sleep(1000);
			}
			System.out.println("div 3 assertions end : " + profileName);
			Thread.sleep(1000);

			Actions action = new Actions(driver);
			WebElement element = driver.findElement(takeMeToBreakButton);
			action.moveToElement(element).click(element).build().perform();
			Thread.sleep(1000);
			isAgentBreakStateIsDisplayed();
			// Actions action = new Actions(driver);
			// Assert.assertTrue(driver.findElement(readyForNextCallButton).isDisplayed());
			// WebElement element = driver.findElement(readyForNextCallButton);
			// WebDriverWait wait = new WebDriverWait(driver, 5);
			// wait.until(ExpectedConditions.elementToBeClickable(readyForNextCallButton));
			// action.moveToElement(element).click(element).build().perform();
			// isAgentReadyStateIsDisplayed();
		} catch (Exception e) {
			System.out.println(" profile is: " + profileName + " exception is :::: " + e);
			Assert.assertEquals(e, "success");
		}
	}

	public String callDuration = null;

	public List<String> verifyInBoundCallsAreReflectedInCallHistory() throws InterruptedException {
		int noOfCustomerHandledByAgent = 0;
		String number = null;
		String profile = null;
		String callEnd = null;
		String callStart = null;
		List<String> callDetails = new ArrayList<String>();

		try {
			WebDriverWait wait = new WebDriverWait(driver, 100);
			wait.until(ExpectedConditions.visibilityOfElementLocated(incomingCallFromMsg));
		} catch (Exception e) {

			System.out.println("unable to visibled incomingCallFromMsg for userName : " + userName + " profile is: "
					+ profileName + " exception is :::: " + e);
			Assert.assertEquals(e, "success");
		}

		try {
			boolean goToOnCallState = false;
			long div1StartTime = System.currentTimeMillis();
			long div1EndTime = div1StartTime + 1500;
			System.out.println("div 1 assertions start : " + profileName);
			while (!goToOnCallState && System.currentTimeMillis() < div1EndTime) {
				if (!driver.findElement(wrapUpCallButton).isDisplayed()) {
					div1();
					elementsShouldNotBeDisplayedInDiv1();
				} else {
					goToOnCallState = true;
				}
			}
			System.out.println("div 1 assertions end : " + profileName);
		} catch (Exception e) {
			System.out.println(" profile is: " + profileName + " exception is :::: " + e);
			Assert.assertEquals(e, "success");
		}
		try {
			WebDriverWait wait = new WebDriverWait(driver, 100);
			wait.until(ExpectedConditions.visibilityOfElementLocated(wrapUpCallButton));
			DateFormat logindf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
			Date dateobj = new Date();
			callStart = logindf.format(dateobj);
			System.out.println("call start for " + profileName + "is ::" + callStart);
		} catch (Exception e) {
			System.out.println("unable to visibled wrapUpCallButton for userName : " + userName + " profile is: "
					+ profileName + " exception is :::: " + e);
			Assert.assertEquals(e, "success");
		}
		try {
			Assert.assertTrue(driver.findElement(timer).isDisplayed());
			if (driver.findElement(timer).isDisplayed()) {

				driver.findElement(mobileNumber).isDisplayed();
				number = driver.findElement(mobileNumber).getText();
				System.out.println("mobilenumber in driver " + profileName + " is::" + number);

				// boolean goToReadyForNextCall = false;
				System.out.println("div 2 assertions start : " + profileName);
				// long div2StartTime = System.currentTimeMillis();
				// long div2EndTime = div2StartTime + 15000;
				while (!driver.findElement(timer).isDisplayed()) {
					callDuration = driver.findElement(timer).getText();
					// div2();
					// elementsShouldNotBeDisplayedInDiv2();
				}
				Thread.sleep(1000);
			}else{
				Assert.assertTrue(false);
			}
			System.out.println("div 2 assertions end : " + profileName);

		} catch (Exception e) {
			System.out.println(" profile is: " + profileName + " exception is :::: " + e);
			Assert.assertEquals(e, "success");
		}
		try {
			WebDriverWait wait1 = new WebDriverWait(driver, 100);
			wait1.until(ExpectedConditions.visibilityOfElementLocated(readyForNextCallButton));
			DateFormat logoutdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
			Date dateobj = new Date();
			callEnd = logoutdf.format(dateobj);
			System.out.println("call ends for " + profileName + "is ::" + callEnd);
		} catch (Exception e) {
			System.out.println("unable to visibled readyForNextCallButton for userName : " + userName + " profile is: "
					+ profileName + " exception is :::: " + e);
			Assert.assertEquals(e, "success");
		}
		try {
			Assert.assertTrue(driver.findElement(readyForNextCallButton).isDisplayed());
			System.out.println("div 3 assertions start : " + profileName);
			long div3StartTime = System.currentTimeMillis();
			long div3EndTime = div3StartTime + 4000;
			while (System.currentTimeMillis() < div3EndTime) {
				div3();
				elementsShouldNotBeDisplayedInDiv3();
				Thread.sleep(1000);
			}
			System.out.println("div 3 assertions end : " + profileName);
			Thread.sleep(1000);
			Actions action = new Actions(driver);
			Assert.assertTrue(driver.findElement(readyForNextCallButton).isDisplayed());
			WebElement element = driver.findElement(readyForNextCallButton);
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.elementToBeClickable(readyForNextCallButton));
			action.moveToElement(element).click(element).build().perform();
			// isAgentReadyStateIsDisplayed();
		} catch (Exception e) {
			System.out.println(" profile is: " + profileName + " exception is :::: " + e);
			Assert.assertEquals(e, "success");
		}

		callDetails.add(number);
		callDetails.add(callStart);
		callDetails.add(callEnd);
		callDetails.add(callDuration);
		System.out.println("call details : "+ callDetails);
		return callDetails;
	}

	public String verifyCallBackRequestForInBoundCall() throws InterruptedException {
		Login login1 = new Login(driver, profileName, userName, passWord);
		int noOfCustomerHandledByAgent = 0;
		String cbrName = null;
		String number = null;
		String profile = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 100);
			wait.until(ExpectedConditions.visibilityOfElementLocated(incomingCallFromMsg));
		} catch (Exception e) {

			System.out.println("unable to visibled incomingCallFromMsg for userName : " + userName + " profile is: "
					+ profileName + " exception is :::: " + e);
			// AgentTest.customerCount--;
			Assert.assertEquals(e, "success");
		}

		try {
			boolean goToOnCallState = false;
			long div1StartTime = System.currentTimeMillis();
			long div1EndTime = div1StartTime + 1500;
			System.out.println("div 1 assertions start : " + profileName);
			while (!goToOnCallState && System.currentTimeMillis() < div1EndTime) {
				if (!driver.findElement(wrapUpCallButton).isDisplayed()) {
					div1();
					elementsShouldNotBeDisplayedInDiv1();
				} else {
					goToOnCallState = true;
				}
				// Thread.sleep(1000);
			}
			System.out.println("div 1 assertions end : " + profileName);
		} catch (Exception e) {
			System.out.println(" profile is: " + profileName + " exception is :::: " + e);
			Assert.assertEquals(e, "success");
		}

		try {
			WebDriverWait wait = new WebDriverWait(driver, 100);
			wait.until(ExpectedConditions.visibilityOfElementLocated(wrapUpCallButton));

			// Thread.sleep(2000);
			ArrayList addCustomerDetails = new ArrayList();
			ArrayList savedCustomerDetails = new ArrayList();
			addCustomerDetails = editCustomerDetails();
			System.out.println(addCustomerDetails);

			savedCustomerDetails = verifySavedCustomerDetails();
			System.out.println(addCustomerDetails);

			Assert.assertEquals(addCustomerDetails, savedCustomerDetails);
			cbrName = createCallBackRequest();
			// login1.handleUnexpectedAlert();
			Assert.assertTrue(verifyNotificationOfCBR());

		} catch (Exception e) {
			System.out.println("unable to visibled wrapUpCallButton for userName : " + userName + " profile is: "
					+ profileName + " exception is :::: " + e);
			Assert.assertEquals(e, "success");
		}
		try {
			Assert.assertTrue(driver.findElement(wrapUpCallButton).isDisplayed());
			if (driver.findElement(wrapUpCallButton).isDisplayed()) {

				boolean goToReadyForNextCall = false;
				System.out.println("div 2 assertions start : " + profileName);
				long div2StartTime = System.currentTimeMillis();
				long div2EndTime = div2StartTime + 10000;
				while (!goToReadyForNextCall && System.currentTimeMillis() < div2EndTime) {
					if (!driver.findElement(readyForNextCallButton).isDisplayed()) {
						// searchFiledForCustomerDetails();
						div2();
						elementsShouldNotBeDisplayedInDiv2();
					} else {
						goToReadyForNextCall = true;
					}
					Thread.sleep(1000);
				}
				System.out.println("div 2 assertions end : " + profileName);
			}
		} catch (Exception e) {
			System.out.println(" profile is: " + profileName + " exception is :::: " + e);
			Assert.assertEquals(e, "success");
		}
		try {
			WebDriverWait wait1 = new WebDriverWait(driver, 100);
			wait1.until(ExpectedConditions.visibilityOfElementLocated(readyForNextCallButton));
		} catch (Exception e) {
			System.out.println("unable to visibled readyForNextCallButton for userName : " + userName + " profile is: "
					+ profileName + " exception is :::: " + e);
			Assert.assertEquals(e, "success");
		}
		try {
			Assert.assertTrue(driver.findElement(readyForNextCallButton).isDisplayed());
			System.out.println("div 3 assertions start : " + profileName);
			long div3StartTime = System.currentTimeMillis();
			long div3EndTime = div3StartTime + 4000;
			while (System.currentTimeMillis() < div3EndTime) {
				div3();
				elementsShouldNotBeDisplayedInDiv3();
				Thread.sleep(1000);
			}
			System.out.println("div 3 assertions end : " + profileName);
			Thread.sleep(1000);
			Actions action = new Actions(driver);
			Assert.assertTrue(driver.findElement(readyForNextCallButton).isDisplayed());
			WebElement element = driver.findElement(readyForNextCallButton);
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.elementToBeClickable(readyForNextCallButton));
			action.moveToElement(element).click(element).build().perform();
			Thread.sleep(2000);
		} catch (Exception e) {
			System.out.println("issue : " + userName + " profile is: " + profileName + " exception is :::: " + e);
			Assert.assertEquals(e, "success");
		}

		return cbrName;
	}

	public String verifyRaiseNewTicketForInBoundCall() throws InterruptedException {
		Login login1 = new Login(driver, profileName, userName, passWord);
		int noOfCustomerHandledByAgent = 0;
		String cbrName = null;
		String number = null;
		String profile = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 100);
			wait.until(ExpectedConditions.visibilityOfElementLocated(incomingCallFromMsg));
		} catch (Exception e) {

			System.out.println("unable to visibled incomingCallFromMsg for userName : " + userName + " profile is: "
					+ profileName + " exception is :::: " + e);
			// AgentTest.customerCount--;
			Assert.assertEquals(e, "success");
		}

		try {
			boolean goToOnCallState = false;
			long div1StartTime = System.currentTimeMillis();
			long div1EndTime = div1StartTime + 1500;
			System.out.println("div 1 assertions start : " + profileName);
			while (!goToOnCallState && System.currentTimeMillis() < div1EndTime) {
				if (!driver.findElement(wrapUpCallButton).isDisplayed()) {
					div1();
					elementsShouldNotBeDisplayedInDiv1();
				} else {
					goToOnCallState = true;
				}
				// Thread.sleep(1000);
			}
			System.out.println("div 1 assertions end : " + profileName);
		} catch (Exception e) {
			System.out.println(" profile is: " + profileName + " exception is :::: " + e);
			Assert.assertEquals(e, "success");
		}
		List<String> ticketDetails = new ArrayList<String>();
		try {
			WebDriverWait wait = new WebDriverWait(driver, 100);
			wait.until(ExpectedConditions.visibilityOfElementLocated(wrapUpCallButton));

			// Thread.sleep(2000);
			ArrayList addCustomerDetails = new ArrayList();
			ArrayList savedCustomerDetails = new ArrayList();
			addCustomerDetails = editCustomerDetails();
			System.out.println(addCustomerDetails);

			savedCustomerDetails = verifySavedCustomerDetails();
			System.out.println(addCustomerDetails);

			Assert.assertEquals(addCustomerDetails, savedCustomerDetails);
			ticketDetails = raiseNewTicketWhenAgentIsOnCall();
			// login1.handleUnexpectedAlert();
			Assert.assertTrue(verifyNotificationOfCBR());

		} catch (Exception e) {
			System.out.println("unable to visibled wrapUpCallButton for userName : " + userName + " profile is: "
					+ profileName + " exception is :::: " + e);
			Assert.assertEquals(e, "success");
		}
		try {
			Assert.assertTrue(driver.findElement(wrapUpCallButton).isDisplayed());
			if (driver.findElement(wrapUpCallButton).isDisplayed()) {

				boolean goToReadyForNextCall = false;
				System.out.println("div 2 assertions start : " + profileName);
				long div2StartTime = System.currentTimeMillis();
				long div2EndTime = div2StartTime + 10000;
				while (!goToReadyForNextCall && System.currentTimeMillis() < div2EndTime) {
					if (!driver.findElement(readyForNextCallButton).isDisplayed()) {
						// searchFiledForCustomerDetails();
						div2();
						elementsShouldNotBeDisplayedInDiv2();
					} else {
						goToReadyForNextCall = true;
					}
					Thread.sleep(1000);
				}
				System.out.println("div 2 assertions end : " + profileName);
			}
		} catch (Exception e) {
			System.out.println(" profile is: " + profileName + " exception is :::: " + e);
			Assert.assertEquals(e, "success");
		}
		try {
			WebDriverWait wait1 = new WebDriverWait(driver, 100);
			wait1.until(ExpectedConditions.visibilityOfElementLocated(readyForNextCallButton));
		} catch (Exception e) {
			System.out.println("unable to visibled readyForNextCallButton for userName : " + userName + " profile is: "
					+ profileName + " exception is :::: " + e);
			Assert.assertEquals(e, "success");
		}
		try {
			Assert.assertTrue(driver.findElement(readyForNextCallButton).isDisplayed());
			System.out.println("div 3 assertions start : " + profileName);
			long div3StartTime = System.currentTimeMillis();
			long div3EndTime = div3StartTime + 4000;
			while (System.currentTimeMillis() < div3EndTime) {
				div3();
				elementsShouldNotBeDisplayedInDiv3();
				Thread.sleep(1000);
			}
			System.out.println("div 3 assertions end : " + profileName);
			Thread.sleep(1000);
			Actions action = new Actions(driver);
			Assert.assertTrue(driver.findElement(readyForNextCallButton).isDisplayed());
			WebElement element = driver.findElement(readyForNextCallButton);
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.elementToBeClickable(readyForNextCallButton));
			action.moveToElement(element).click(element).build().perform();
			Thread.sleep(2000);
		} catch (Exception e) {
			System.out.println("issue : " + userName + " profile is: " + profileName + " exception is :::: " + e);
			Assert.assertEquals(e, "success");
		}

		return cbrName;
	}

	public ArrayList verifyFieldsOnAgentPageWhenAgentHaveInBoundCalls() throws InterruptedException {
		// WebElement member;
		int noOfCustomerHandledByAgent = 0;
		ArrayList details = new ArrayList();
		String profile = null;
		while (true) {

			if (AgentTest.customerCount >= AgentTest.numberOfCustomers) {
				System.out.println("Breaking the loop for " + AgentTest.customerCount + ": " + profileName);
				break;
			}
			int count = 0;
			try {
				WebDriverWait wait = new WebDriverWait(driver, 100);
				wait.until(ExpectedConditions.visibilityOfElementLocated(incomingCallFromMsg));
				AgentTest.customerCount++;
				count = AgentTest.customerCount;
				logger.info("starting loop for " + profileName + " is::" + count);
				System.out.println("starting loop for " + profileName + " is:: " + count);
				if (AgentTest.customerCount == AgentTest.numberOfCustomers) {
					profile = profileName;
				}
			} catch (Exception e) {

				System.out.println("unable to visibled incomingCallFromMsg for userName : " + userName + " profile is: "
						+ profileName + " exception is :::: " + e);

				// AgentTest.customerCount--;
				Assert.assertEquals(e, "success");
				break;
			}

			try {
				boolean goToOnCallState = false;
				long div1StartTime = System.currentTimeMillis();
				long div1EndTime = div1StartTime + 1500;
				System.out.println("div 1 assertions start : " + profileName);
				while (!goToOnCallState && System.currentTimeMillis() < div1EndTime) {
					if (!driver.findElement(wrapUpCallButton).isDisplayed()) {
						div1();
						isElementsPresentOnAgentPageForInBound();
						elementsShouldNotBeDisplayedInDiv1();
					} else {
						goToOnCallState = true;
					}
					// Thread.sleep(1000);
				}
				System.out.println("div 1 assertions end : " + profileName);
			} catch (Exception e) {
				System.out.println(" profile is: " + profileName + " exception is :::: " + e);
				Assert.assertEquals(e, "success");
				break;
			}
			try {
				WebDriverWait wait = new WebDriverWait(driver, 100);
				wait.until(ExpectedConditions.visibilityOfElementLocated(wrapUpCallButton));
			} catch (Exception e) {
				System.out.println("unable to visibled wrapUpCallButton for userName : " + userName + " profile is: "
						+ profileName + " exception is :::: " + e);
				Assert.assertEquals(e, "success");
				break;
			}
			try {
				Assert.assertTrue(driver.findElement(wrapUpCallButton).isDisplayed());
				if (driver.findElement(wrapUpCallButton).isDisplayed()) {

					System.out.println("inBoundCallInProgress displayed for " + profileName + " in loop :: " + count);
					noOfCustomerHandledByAgent++;

					// driver.findElement(mobileNumber).isDisplayed();
					// driver.findElement(mobileNumber).getText();
					// System.out.println("mobilenumber in driver " +
					// profileName + " is::" + mobile);

					boolean goToReadyForNextCall = false;
					System.out.println("div 2 assertions start : " + profileName);
					long div2StartTime = System.currentTimeMillis();
					long div2EndTime = div2StartTime + 17000;
					while (!goToReadyForNextCall && System.currentTimeMillis() < div2EndTime) {
						if (!driver.findElement(readyForNextCallButton).isDisplayed()) {
							searchFiledForCustomerDetails();
							div2();
							isElementsPresentOnAgentPageForInBound();
							elementsShouldNotBeDisplayedInDiv2();
						} else {
							goToReadyForNextCall = true;
						}
						Thread.sleep(1000);
					}
					System.out.println("div 2 assertions end : " + profileName);
				}
			} catch (Exception e) {
				System.out.println(" profile is: " + profileName + " exception is :::: " + e);
				Assert.assertEquals(e, "success");
				break;
			}
			try {
				WebDriverWait wait1 = new WebDriverWait(driver, 100);
				wait1.until(ExpectedConditions.visibilityOfElementLocated(readyForNextCallButton));
			} catch (Exception e) {
				System.out.println("unable to visibled readyForNextCallButton for userName : " + userName
						+ " profile is: " + profileName + " exception is :::: " + e);
				Assert.assertEquals(e, "success");
				break;
			}
			try {
				Assert.assertTrue(driver.findElement(readyForNextCallButton).isDisplayed());
				System.out.println("div 3 assertions start : " + profileName);
				long div3StartTime = System.currentTimeMillis();
				long div3EndTime = div3StartTime + 4000;
				while (System.currentTimeMillis() < div3EndTime) {
					div3();
					isElementsPresentOnAgentPageForInBound();
					elementsShouldNotBeDisplayedInDiv3();
					Thread.sleep(1000);
				}
				System.out.println("div 3 assertions end : " + profileName);
				System.out.println("readyForNextCallButton displayed for " + profileName + " in loop ::" + count
						+ " customers handled by agent::::: " + noOfCustomerHandledByAgent);
				Thread.sleep(1000);
				Actions action = new Actions(driver);
				Assert.assertTrue(driver.findElement(readyForNextCallButton).isDisplayed());
				WebElement element = driver.findElement(readyForNextCallButton);
				WebDriverWait wait = new WebDriverWait(driver, 5);
				wait.until(ExpectedConditions.elementToBeClickable(readyForNextCallButton));
				action.moveToElement(element).click(element).build().perform();
				// JavascriptExecutor executor = (JavascriptExecutor) driver;
				// executor.executeScript("arguments[0].click();", element);
				// executor.executeScript("document.getElement(By.xpath('//*[@id='divSidePanel']/div[3]//*[contains(@class,'btn
				// btn-green')]').click()");
				System.out.println("readyForNextCallButton is clicked for " + profileName + " in loop :: " + count);
				System.out.println(
						"noOfCustomerHandledByAgent for " + profileName + " is: " + noOfCustomerHandledByAgent);
				// Thread.sleep(1000);
				isAgentReadyStateIsDisplayed();
			} catch (Exception e) {
				System.out.println(" profile is: " + profileName + " exception is :::: " + e);
				Assert.assertEquals(e, "success");
				break;
			}

			// Thread.sleep(1000);

			System.out.println(profileName + " with numberOfCustomers " + AgentTest.numberOfCustomers + " == "
					+ AgentTest.customerCount + " customerCount");

			System.out.println("local Profile " + profile + " Global Profile " + profileName);
			if (profile != null && profile.equals(profileName)) {
				details.add(profile);
			}
		}
		// Thread.sleep(1000);

		System.out
				.println("total noOfCustomerHandledByAgent for " + profileName + " is: " + noOfCustomerHandledByAgent);
		details.add(noOfCustomerHandledByAgent);

		System.out.println(details);
		return details;
	}

	public void editCustomerDetailsOnAgentPageWhenAgentHaveInBoundCalls() throws InterruptedException {
		ArrayList details = new ArrayList();
		try {
			WebDriverWait wait = new WebDriverWait(driver, 100);
			wait.until(ExpectedConditions.visibilityOfElementLocated(incomingCallFromMsg));
		} catch (Exception e) {

			System.out.println("unable to visibled incomingCallFromMsg for userName : " + userName + " profile is: "
					+ profileName + " exception is :::: " + e);
			// AgentTest.customerCount--;
			Assert.assertEquals(e, "success");
		}

		try {
			boolean goToOnCallState = false;
			long div1StartTime = System.currentTimeMillis();
			long div1EndTime = div1StartTime + 2000;
			System.out.println("div 1 assertions start : " + profileName);
			while (!goToOnCallState && System.currentTimeMillis() < div1EndTime) {
				if (!driver.findElement(wrapUpCallButton).isDisplayed()) {
					div1();
					isElementsPresentOnAgentPageForInBound();
					elementsShouldNotBeDisplayedInDiv1();
				} else {
					goToOnCallState = true;
				}
				// Thread.sleep(1000);
			}
			System.out.println("div 1 assertions end : " + profileName);
		} catch (Exception e) {
			System.out.println(" profile is: " + profileName + " exception is :::: " + e);
			Assert.assertEquals(e, "success");
		}
		try {
			WebDriverWait wait = new WebDriverWait(driver, 100);
			wait.until(ExpectedConditions.visibilityOfElementLocated(wrapUpCallButton));
		} catch (Exception e) {
			System.out.println("unable to visibled wrapUpCallButton for userName : " + userName + " profile is: "
					+ profileName + " exception is :::: " + e);
			Assert.assertEquals(e, "success");
		}
		ArrayList addCustomerDetails = new ArrayList();
		ArrayList savedCustomerDetails = new ArrayList();
		try {
			Assert.assertTrue(driver.findElement(wrapUpCallButton).isDisplayed());
			if (driver.findElement(wrapUpCallButton).isDisplayed()) {

				// driver.findElement(mobileNumber).isDisplayed();
				// driver.findElement(mobileNumber).getText();
				// System.out.println("mobilenumber in driver " +
				// profileName + " is::" + mobile);

				addCustomerDetails = editCustomerDetails();
				System.out.println(addCustomerDetails);

				savedCustomerDetails = verifySavedCustomerDetails();
				System.out.println(addCustomerDetails);

				Assert.assertEquals(addCustomerDetails, savedCustomerDetails);

				boolean goToReadyForNextCall = false;
				System.out.println("div 2 assertions start : " + profileName);
				long div2StartTime = System.currentTimeMillis();
				long div2EndTime = div2StartTime + 10000;
				while (!goToReadyForNextCall && System.currentTimeMillis() < div2EndTime) {
					if (!driver.findElement(readyForNextCallButton).isDisplayed()) {
						searchFiledForCustomerDetails();
						div2();
						isElementsPresentOnAgentPageForInBound();
						elementsShouldNotBeDisplayedInDiv2();
					} else {
						goToReadyForNextCall = true;
					}
					Thread.sleep(1000);
				}
				System.out.println("div 2 assertions end : " + profileName);
			}
		} catch (Exception e) {
			System.out.println(" profile is: " + profileName + " exception is :::: " + e);
			Assert.assertEquals(e, "success");
		}
		try {
			WebDriverWait wait1 = new WebDriverWait(driver, 100);
			wait1.until(ExpectedConditions.visibilityOfElementLocated(readyForNextCallButton));
		} catch (Exception e) {
			System.out.println("unable to visibled readyForNextCallButton for userName : " + userName + " profile is: "
					+ profileName + " exception is :::: " + e);
			Assert.assertEquals(e, "success");
		}
		try {
			Assert.assertTrue(driver.findElement(readyForNextCallButton).isDisplayed());
			System.out.println("div 3 assertions start : " + profileName);
			long div3StartTime = System.currentTimeMillis();
			long div3EndTime = div3StartTime + 4000;
			while (System.currentTimeMillis() < div3EndTime) {
				div3();
				isElementsPresentOnAgentPageForInBound();
				elementsShouldNotBeDisplayedInDiv3();
				Thread.sleep(1000);
			}
			System.out.println("div 3 assertions end : " + profileName);
			Thread.sleep(1000);
			Actions action = new Actions(driver);
			Assert.assertTrue(driver.findElement(readyForNextCallButton).isDisplayed());
			WebElement element = driver.findElement(readyForNextCallButton);
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.elementToBeClickable(readyForNextCallButton));
			action.moveToElement(element).click(element).build().perform();
			// Thread.sleep(1000);
			isAgentReadyStateIsDisplayed();
		} catch (Exception e) {
			System.out.println(" profile is: " + profileName + " exception is :::: " + e);
			Assert.assertEquals(e, "success");
		}

		// return details;
	}

	public void verifyStateOfWarmTransferAgentWhenNormalAgentCancleWarmTransfer(WebDriver driver1) {
		WebDriver temp = this.driver;
		this.driver = driver1;
		elementsShouldNotBeDisplayedForWarmTranferAgent();
		elementsShouldNotBeDisplayedInDiv1();
		this.driver = temp;
	}

	public void verifyStateOfNormalAgentWhenGiveCompleteWarmTransferToNewAgent() throws InterruptedException {
		Thread.sleep(2000);
		elementsShouldNotBeDisplayedInDiv3();
		WebDriverWait wait = new WebDriverWait(driver, 100);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(incomingCallFromMsg));
		WebDriverWait wait1 = new WebDriverWait(driver, 100);
		wait1.until(ExpectedConditions.invisibilityOfElementLocated(wrapUpCallButton));
	}

	public void verifyAgentPageWhenAgentCancleWarmTranfer() throws InterruptedException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 100);
			wait.until(ExpectedConditions.visibilityOfElementLocated(wrapUpCallButton));
		} catch (Exception e) {
			System.out.println("unable to visibled wrapUpCallButton for userName : " + userName + " profile is: "
					+ profileName + " exception is :::: " + e);
			Assert.assertEquals(e, "success");
		}
		try {
			Assert.assertTrue(driver.findElement(wrapUpCallButton).isDisplayed());
			if (driver.findElement(wrapUpCallButton).isDisplayed()) {

				boolean goToReadyForNextCall = false;
				System.out.println("div 2 assertions start : " + profileName);
				long div2StartTime = System.currentTimeMillis();
				long div2EndTime = div2StartTime + 7000;
				while (!goToReadyForNextCall && System.currentTimeMillis() < div2EndTime) {
					if (!driver.findElement(readyForNextCallButton).isDisplayed()) {
						// searchFiledForCustomerDetails();
						div2();
						elementsShouldNotBeDisplayedInDiv2();
					} else {
						goToReadyForNextCall = true;
					}
					Thread.sleep(1000);
				}
				System.out.println("div 2 assertions end : " + profileName);
			}
		} catch (Exception e) {
			System.out.println(" profile is: " + profileName + " exception is :::: " + e);
			Assert.assertEquals(e, "success");
		}
		try {
			WebDriverWait wait1 = new WebDriverWait(driver, 100);
			wait1.until(ExpectedConditions.visibilityOfElementLocated(readyForNextCallButton));
		} catch (Exception e) {
			System.out.println("unable to visibled readyForNextCallButton for userName : " + userName + " profile is: "
					+ profileName + " exception is :::: " + e);
			Assert.assertEquals(e, "success");
		}
		try {
			Assert.assertTrue(driver.findElement(readyForNextCallButton).isDisplayed());
			System.out.println("div 3 assertions start : " + profileName);
			long div3StartTime = System.currentTimeMillis();
			long div3EndTime = div3StartTime + 4000;
			while (System.currentTimeMillis() < div3EndTime) {
				div3();
				elementsShouldNotBeDisplayedInDiv3();
				Thread.sleep(1000);
			}
			Thread.sleep(1000);
			Actions action = new Actions(driver);
			Assert.assertTrue(driver.findElement(readyForNextCallButton).isDisplayed());
			WebElement element = driver.findElement(readyForNextCallButton);
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.elementToBeClickable(readyForNextCallButton));
			action.moveToElement(element).click(element).build().perform();
			isAgentReadyStateIsDisplayed();
		} catch (Exception e) {
			System.out.println(" profile is: " + profileName + " exception is :::: " + e);
			Assert.assertEquals(e, "success");
		}
	}

	public void verifyNewAgentPageWhenNormalAgentMakeWarmTranfer(WebDriver driver1) throws InterruptedException {
		WebDriver temp = this.driver;
		this.driver = driver1;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 100);
			wait.until(ExpectedConditions.visibilityOfElementLocated(wrapUpCallButton));
		} catch (Exception e) {
			System.out.println("unable to visibled wrapUpCallButton for userName : " + userName + " profile is: "
					+ profileName + " exception is :::: " + e);
			Assert.assertEquals(e, "success");
		}
		try {
			Assert.assertTrue(driver.findElement(wrapUpCallButton).isDisplayed());
			if (driver.findElement(wrapUpCallButton).isDisplayed()) {

				boolean goToReadyForNextCall = false;
				System.out.println("div 2 assertions start : " + profileName);
				long div2StartTime = System.currentTimeMillis();
				long div2EndTime = div2StartTime + 7000;
				while (!goToReadyForNextCall && System.currentTimeMillis() < div2EndTime) {
					if (!driver.findElement(readyForNextCallButton).isDisplayed()) {
						// searchFiledForCustomerDetails();
						div2();
						elementsShouldNotBeDisplayedInDiv2();
					} else {
						goToReadyForNextCall = true;
					}
					Thread.sleep(1000);
				}
				System.out.println("div 2 assertions end : " + profileName);
			}
		} catch (Exception e) {
			System.out.println(" profile is: " + profileName + " exception is :::: " + e);
			Assert.assertEquals(e, "success");
		}
		try {
			WebDriverWait wait1 = new WebDriverWait(driver, 100);
			wait1.until(ExpectedConditions.visibilityOfElementLocated(readyForNextCallButton));
		} catch (Exception e) {
			System.out.println("unable to visibled readyForNextCallButton for userName : " + userName + " profile is: "
					+ profileName + " exception is :::: " + e);
			Assert.assertEquals(e, "success");
		}
		try {
			Assert.assertTrue(driver.findElement(readyForNextCallButton).isDisplayed());
			System.out.println("div 3 assertions start : " + profileName);
			long div3StartTime = System.currentTimeMillis();
			long div3EndTime = div3StartTime + 4000;
			while (System.currentTimeMillis() < div3EndTime) {
				div3();
				elementsShouldNotBeDisplayedInDiv3();
				Thread.sleep(1000);
			}
			Thread.sleep(1000);
			Actions action = new Actions(driver);
			Assert.assertTrue(driver.findElement(readyForNextCallButton).isDisplayed());
			WebElement element = driver.findElement(readyForNextCallButton);
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.elementToBeClickable(readyForNextCallButton));
			action.moveToElement(element).click(element).build().perform();
			isAgentReadyStateIsDisplayed();
		} catch (Exception e) {
			System.out.println(" profile is: " + profileName + " exception is :::: " + e);
			Assert.assertEquals(e, "success");
		}
		this.driver = temp;
	}

	public ArrayList verifyCallsOnAgentPageOnNewBrowser(WebDriver driver1, String customerNumber)
			throws InterruptedException {
		int noOfCustomerHandledByAgent = 0;
		WebDriver temp = this.driver;
		this.driver = driver1;
		ArrayList details = new ArrayList();
		String profile = null;
		while (true) {

			if (AgentTest.customerCount >= AgentTest.numberOfCustomers) {
				System.out.println("Breaking the loop for " + AgentTest.customerCount + ": " + profileName);
				break;
			}
			int count = 0;
			try {
				WebDriverWait wait = new WebDriverWait(driver, 100);
				wait.until(ExpectedConditions.visibilityOfElementLocated(incomingCallFromMsg));
				AgentTest.customerCount++;
				count = AgentTest.customerCount;
				logger.info("starting loop for " + profileName + " is::" + count);
				System.out.println("starting loop for " + profileName + " is:: " + count);
				if (AgentTest.customerCount == AgentTest.numberOfCustomers) {
					profile = profileName;
				}
			} catch (Exception e) {

				System.out.println("unable to visibled incomingCallFromMsg for userName : " + userName + " profile is: "
						+ profileName + " exception is :::: " + e);

				// AgentTest.customerCount--;
				Assert.assertEquals(e, "success");
				break;
			}

			try {
				boolean goToOnCallState = false;
				long div1StartTime = System.currentTimeMillis();
				long div1EndTime = div1StartTime + 1500;
				System.out.println("div 1 assertions start : " + profileName);
				while (!goToOnCallState && System.currentTimeMillis() < div1EndTime) {
					if (!driver.findElement(wrapUpCallButton).isDisplayed()) {
						div1();
						elementsShouldNotBeDisplayedInDiv1();
						String number = driver.findElement(mobileNumber).getText();
						Assert.assertEquals(number, mobileNumber);
					} else {
						goToOnCallState = true;
					}
					// Thread.sleep(1000);
				}
				System.out.println("div 1 assertions end : " + profileName);
			} catch (Exception e) {
				System.out.println(" profile is: " + profileName + " exception is :::: " + e);
				Assert.assertEquals(e, "success");
				break;
			}
			try {
				WebDriverWait wait = new WebDriverWait(driver, 100);
				wait.until(ExpectedConditions.visibilityOfElementLocated(wrapUpCallButton));
			} catch (Exception e) {
				System.out.println("unable to visibled wrapUpCallButton for userName : " + userName + " profile is: "
						+ profileName + " exception is :::: " + e);
				Assert.assertEquals(e, "success");
				break;
			}
			try {
				Assert.assertTrue(driver.findElement(wrapUpCallButton).isDisplayed());
				if (driver.findElement(wrapUpCallButton).isDisplayed()) {

					System.out.println("inBoundCallInProgress displayed for " + profileName + " in loop :: " + count);
					noOfCustomerHandledByAgent++;
					String number = driver.findElement(mobileNumber).getText();
					Assert.assertEquals(number, mobileNumber);
					// driver.findElement(mobileNumber).isDisplayed();
					// driver.findElement(mobileNumber).getText();
					// System.out.println("mobilenumber in driver " +
					// profileName + " is::" + mobile);

					boolean goToReadyForNextCall = false;
					System.out.println("div 2 assertions start : " + profileName);
					long div2StartTime = System.currentTimeMillis();
					long div2EndTime = div2StartTime + 17000;
					while (!goToReadyForNextCall && System.currentTimeMillis() < div2EndTime) {
						if (!driver.findElement(readyForNextCallButton).isDisplayed()) {
							// searchFiledForCustomerDetails();
							div2();
							elementsShouldNotBeDisplayedInDiv2();
						} else {
							goToReadyForNextCall = true;
						}
						Thread.sleep(1000);
					}
					System.out.println("div 2 assertions end : " + profileName);
				}
			} catch (Exception e) {
				System.out.println(" profile is: " + profileName + " exception is :::: " + e);
				Assert.assertEquals(e, "success");
				break;
			}
			try {
				WebDriverWait wait1 = new WebDriverWait(driver, 100);
				wait1.until(ExpectedConditions.visibilityOfElementLocated(readyForNextCallButton));
			} catch (Exception e) {
				System.out.println("unable to visibled readyForNextCallButton for userName : " + userName
						+ " profile is: " + profileName + " exception is :::: " + e);
				Assert.assertEquals(e, "success");
				break;
			}
			try {
				Assert.assertTrue(driver.findElement(readyForNextCallButton).isDisplayed());
				System.out.println("div 3 assertions start : " + profileName);
				long div3StartTime = System.currentTimeMillis();
				long div3EndTime = div3StartTime + 4000;
				while (System.currentTimeMillis() < div3EndTime) {
					div3();
					elementsShouldNotBeDisplayedInDiv3();
					Thread.sleep(1000);
				}
				System.out.println("div 3 assertions end : " + profileName);
				System.out.println("readyForNextCallButton displayed for " + profileName + " in loop ::" + count
						+ " customers handled by agent::::: " + noOfCustomerHandledByAgent);
				Thread.sleep(1000);
				Actions action = new Actions(driver);
				Assert.assertTrue(driver.findElement(readyForNextCallButton).isDisplayed());
				WebElement element = driver.findElement(readyForNextCallButton);
				WebDriverWait wait = new WebDriverWait(driver, 5);
				wait.until(ExpectedConditions.elementToBeClickable(readyForNextCallButton));
				action.moveToElement(element).click(element).build().perform();
				// JavascriptExecutor executor = (JavascriptExecutor) driver;
				// executor.executeScript("arguments[0].click();", element);
				// executor.executeScript("document.getElement(By.xpath('//*[@id='divSidePanel']/div[3]//*[contains(@class,'btn
				// btn-green')]').click()");
				System.out.println("readyForNextCallButton is clicked for " + profileName + " in loop :: " + count);
				System.out.println(
						"noOfCustomerHandledByAgent for " + profileName + " is: " + noOfCustomerHandledByAgent);
				// Thread.sleep(1000);
				// isAgentReadyStateIsDisplayed();
			} catch (Exception e) {
				System.out.println(" profile is: " + profileName + " exception is :::: " + e);
				Assert.assertEquals(e, "success");
				break;
			} finally {
				this.driver = temp;
			}

			// Thread.sleep(1000);

			System.out.println(profileName + " with numberOfCustomers " + AgentTest.numberOfCustomers + " == "
					+ AgentTest.customerCount + " customerCount");

			System.out.println("local Profile " + profile + " Global Profile " + profileName);
			if (profile != null && profile.equals(profileName)) {
				// details.add(number);
				details.add(profile);
			}
		}
		// Thread.sleep(1000);
		this.driver = temp;
		System.out
				.println("total noOfCustomerHandledByAgent for " + profileName + " is: " + noOfCustomerHandledByAgent);
		details.add(noOfCustomerHandledByAgent);

		System.out.println(details);
		return details;
	}

	// call or warm transfer fields
	By selectTranferCallTo = By.id("ddlTransfers");
	By transferNowButton = By.id("btnTransferCall");
	By selectAgentDropDown = By.id("ddlAgents");
	By numberToDialField = By.id("txttransferToExtNum");
	By transferNowButtonForWarmTransfer = By.id("btnWarmTransferCall");
	By cancleTransferButtonForWarmTransfer = By.xpath("//*[@id='btnCancelWarmTransfer']");
	By unHoldCustomerButtonForWarmTransfer = By.xpath("//*[@id='btnUnHoldWarmTransfer']");
	By completeTransferButtonForWarmTransfer = By.xpath("//*[@id='btnCompleteWarmTransfer']");

	// btnStartConference
	// btnEndConference
	// btnAddParticipant
	// btnAddParticipant
	// ConfMemb
	// class="f_13 bold-6 font-white conference_call_time"
	public WebDriver driver1;

	public void cancleWarmTransferCall() {
		driver.findElement(cancleTransferButtonForWarmTransfer).click();
	}

	public void changeHoldStateOfCustomerInWarmTransferCall() {
		driver.findElement(unHoldCustomerButtonForWarmTransfer).click();
	}

	public String stateOfHoldButtonForCustomerInWarmTransferCall() {
		return driver.findElement(unHoldCustomerButtonForWarmTransfer).getText();
	}

	public void transferWarmTransferCallToNewAgent() {
		driver.findElement(completeTransferButtonForWarmTransfer).click();
	}

	public boolean callTransferToAgent() throws InterruptedException {
		boolean value11 = false;
		Thread.sleep(4000);
		driver.findElement(callTransfer).click();
		Select transferTo = new Select(driver.findElement(selectTranferCallTo));
		transferTo.selectByValue("1");
		Select agent = new Select(driver.findElement(selectAgentDropDown));
		// agent.selectByValue("automation agent2");
		agent.selectByVisibleText("automation agent2");
		// driver.findElement(numberToDialField).sendKeys("8331996838");
		Actions action = new Actions(driver);
		Thread.sleep(1000);
		action.moveToElement(driver.findElement(transferNowButton));
		driver.findElement(transferNowButton).click();
		return value11 = true;
	}

	public String callTransferToNewAgent() throws InterruptedException {
		WebElement member;
		String memberNumber = null;
		int noOfCustomerHandledByAgent = 0;
		boolean result = false;

		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.visibilityOfElementLocated(incomingCallFromMsg));
		div1();
		elementsShouldNotBeDisplayedInDiv1();

		wait.until(ExpectedConditions.visibilityOfElementLocated(wrapUpCallButton));
		Assert.assertTrue(driver.findElement(wrapUpCallButton).isDisplayed());
		if (driver.findElement(wrapUpCallButton).isDisplayed()) {
			memberNumber = driver.findElement(mobileNumber).getText();
			result = callTransferToAgent();
		}

		WebDriverWait wait11 = new WebDriverWait(driver, 120);
		wait11.until(ExpectedConditions.visibilityOfElementLocated(readyForNextCallButton));
		Assert.assertTrue(driver.findElement(readyForNextCallButton).isDisplayed());
		return memberNumber;
	}

	public void moveToreadyStateFromWrapUp() {
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(readyForNextCallButton)).click().build().perform();
	}

	public String warmTransferToAgent() throws InterruptedException {
		WebElement member;
		String memberNumber = null;
		int noOfCustomerHandledByAgent = 0;
		boolean value11 = false;

		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.visibilityOfElementLocated(incomingCallFromMsg));
		div1();
		elementsShouldNotBeDisplayedInDiv1();

		wait.until(ExpectedConditions.visibilityOfElementLocated(wrapUpCallButton));
		Assert.assertTrue(driver.findElement(wrapUpCallButton).isDisplayed());
		if (driver.findElement(wrapUpCallButton).isDisplayed()) {
			memberNumber = driver.findElement(mobileNumber).getText();
			Thread.sleep(2000);
			driver.findElement(warmTransferCall).click();
			Select transferTo = new Select(driver.findElement(selectTranferCallTo));
			transferTo.selectByValue("1");
			Select agent = new Select(driver.findElement(selectAgentDropDown));
			// agent.selectByValue("automation agent2");
			agent.selectByVisibleText("automation agent2");
			// driver.findElement(numberToDialField).sendKeys("8331996838");
			Actions action = new Actions(driver);
			Thread.sleep(1000);
			action.moveToElement(driver.findElement(transferNowButtonForWarmTransfer));
			driver.findElement(transferNowButtonForWarmTransfer).click();
			Thread.sleep(2000);
			value11 = true;
			System.out.println("warmTransferToAgent method");
		}

		Assert.assertTrue(driver.findElement(cancleTransferButtonForWarmTransfer).isDisplayed());
		Assert.assertTrue(driver.findElement(unHoldCustomerButtonForWarmTransfer).isDisplayed());
		Assert.assertTrue(driver.findElement(completeTransferButtonForWarmTransfer).isDisplayed());

		return memberNumber;
	}

	public void verifyWarmTranferFieldsAreDisplayed() {
		Assert.assertFalse(driver.findElement(cancleTransferButtonForWarmTransfer).isDisplayed());
		Assert.assertFalse(driver.findElement(unHoldCustomerButtonForWarmTransfer).isDisplayed());
		Assert.assertFalse(driver.findElement(completeTransferButtonForWarmTransfer).isDisplayed());
	}

	public boolean warmTransferToExternalNumber() throws InterruptedException {
		WebElement member;
		String memberNumber = "";
		int noOfCustomerHandledByAgent = 0;
		boolean value11 = false;

		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.visibilityOfElementLocated(incomingCallFromMsg));
		div1();
		elementsShouldNotBeDisplayedInDiv1();

		wait.until(ExpectedConditions.visibilityOfElementLocated(wrapUpCallButton));
		Assert.assertTrue(driver.findElement(wrapUpCallButton).isDisplayed());
		if (driver.findElement(wrapUpCallButton).isDisplayed()) {
			Thread.sleep(2000);
			driver.findElement(warmTransferCall).click();
			Select transferTo = new Select(driver.findElement(selectTranferCallTo));
			transferTo.selectByValue("2");
			driver.findElement(numberToDialField).sendKeys("8331996838");
			Actions action = new Actions(driver);
			Thread.sleep(1000);
			action.moveToElement(driver.findElement(transferNowButtonForWarmTransfer));
			driver.findElement(transferNowButtonForWarmTransfer).click();
			Thread.sleep(2000);
			value11 = true;
			System.out.println("warmTransferToAgent method");
		}

		Assert.assertTrue(driver.findElement(cancleTransferButtonForWarmTransfer).isDisplayed());
		Assert.assertTrue(driver.findElement(unHoldCustomerButtonForWarmTransfer).isDisplayed());
		Assert.assertTrue(driver.findElement(completeTransferButtonForWarmTransfer).isDisplayed());

		return value11;
	}

	public boolean callTransferToMobile() throws InterruptedException {
		WebElement member;
		String memberNumber = "";
		int noOfCustomerHandledByAgent = 0;
		boolean value11 = false;

		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.visibilityOfElementLocated(incomingCallFromMsg));
		div1();
		elementsShouldNotBeDisplayedInDiv1();

		wait.until(ExpectedConditions.visibilityOfElementLocated(wrapUpCallButton));
		Assert.assertTrue(driver.findElement(wrapUpCallButton).isDisplayed());
		if (driver.findElement(wrapUpCallButton).isDisplayed()) {
			Thread.sleep(4000);
			driver.findElement(callTransfer).click();
			Select transferTo = new Select(driver.findElement(selectTranferCallTo));
			transferTo.selectByValue("2");
			driver.findElement(numberToDialField).sendKeys("8331996838");
			Actions action = new Actions(driver);
			Thread.sleep(1000);
			action.moveToElement(driver.findElement(transferNowButton));
			driver.findElement(transferNowButton).click();
			value11 = true;
		}

		WebDriverWait wait11 = new WebDriverWait(driver, 120);
		wait11.until(ExpectedConditions.visibilityOfElementLocated(readyForNextCallButton));
		Assert.assertTrue(driver.findElement(readyForNextCallButton).isDisplayed());
		return value11;
	}

	public String raiseCallBackRequest() throws InterruptedException {
		WebElement member;
		String memberNumber = "";
		int noOfCustomerHandledByAgent = 0;
		String noterForCBR = null;

		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.visibilityOfElementLocated(connectingTocustomerTextOnCallPAnel));
		div1ForOutBound();
		elementsShouldNotBeDisplayedInDiv1ForOutBound();

		wait.until(ExpectedConditions.visibilityOfElementLocated(wrapUpCallButton));
		Assert.assertTrue(driver.findElement(wrapUpCallButton).isDisplayed());

		if (driver.findElement(wrapUpCallButton).isDisplayed()) {
			div2ForOutBound();
			elementsShouldNotBeDisplayedInDiv2ForOutBound();

			noterForCBR = createCallBackRequest();

		} else {
			System.out.println("inBoundCallInProgress button is not dispalyed/visibled for : " + userName
					+ " and profile is: " + profileName);
			AgentTest.customerCount--;
			Assert.assertTrue(false);
			// break;
		}
		Thread.sleep(1000);
		return noterForCBR;
	}

	// public void checkOutBoundCall() throws InterruptedException {
	// WebElement member;
	// String memberNumber = "";
	// int noOfCustomerHandledByAgent = 0;
	//
	// try {
	// WebDriverWait wait = new WebDriverWait(driver, 120);
	// wait.until(ExpectedConditions.visibilityOfElementLocated(connectingTocustomerTextOnCallPAnel));
	// div1ForOutBound();
	// elementsShouldNotBeDisplayedInDiv1ForOutBound();
	//
	// boolean goToOnCallState = false;
	// System.out.println("div 1 assertions start");
	// while (!goToOnCallState) {
	// if (!driver.findElement(wrapUpCallButton).isDisplayed()) {
	// System.out.println("this is div 1");
	// elementsShouldNotBeDisplayedInDiv1ForOutBound();
	// } else {
	// goToOnCallState = true;
	// }
	// Thread.sleep(3000);
	// }
	// System.out.println("div 1 assertions end");
	//
	// wait.until(ExpectedConditions.visibilityOfElementLocated(wrapUpCallButton));
	// Assert.assertTrue(driver.findElement(wrapUpCallButton).isDisplayed());
	//
	// if (driver.findElement(wrapUpCallButton).isDisplayed()) {
	// div2ForOutBound();
	// elementsShouldNotBeDisplayedInDiv2ForOutBound();
	//
	// member = driver.findElement(mobileNumberForOutBound);
	// System.out.println("mobilenumber in driver " + profileName + " is::" +
	// member.getText());
	//
	// boolean goToReadyForNextCall = false;
	// System.out.println("div 2 assertions start");
	// while (!goToReadyForNextCall) {
	// if (!driver.findElement(readyForNextCallButton).isDisplayed()) {
	// System.out.println("this is div 2");
	// elementsShouldNotBeDisplayedInDiv2ForOutBound();
	// } else {
	// goToReadyForNextCall = true;
	// }
	// Thread.sleep(3000);
	// }
	// System.out.println("div 2 assertions end");
	//
	// Assert.assertTrue(driver.findElement(readyForNextCallButton).isDisplayed());
	//
	// System.out.println("div 3 assertions start");
	// long t = System.currentTimeMillis();
	// long end = t + 6000;
	// while (System.currentTimeMillis() < end) {
	// System.out.println("this is div 3");
	// div3ForOutBound();
	// elementsShouldNotBeDisplayedInDiv3ForOutBound();
	// Thread.sleep(1000);
	// }
	// System.out.println("div 3 assertions end");
	//
	// Thread.sleep(1000);
	// Actions action = new Actions(driver);
	// action.moveToElement(driver.findElement(readyForNextCallButton)).perform();
	// WebElement element = driver.findElement(readyForNextCallButton);
	// JavascriptExecutor executor = (JavascriptExecutor) driver;
	// executor.executeScript("arguments[0].click();", element);
	// } else {
	// System.out.println("inBoundCallInProgress button is not
	// dispalyed/visibled for : " + userName
	// + " and profile is: " + profileName);
	// AgentTest.customerCount--;
	// Assert.assertTrue(false);
	// // break;
	// }
	// Thread.sleep(1000);
	// } catch (Exception e) {
	//
	// System.out.println(
	// "unable to visibled wrapUp button/clicked readyForNextCallButton for
	// userName : " + userName
	// + " and profile is: " + profileName + " exception is " + e + " and
	// refreshing the page");
	// // System.out.println(e.getMessage());
	// AgentTest.customerCount--;
	// // break;
	// Assert.assertTrue(false);
	// }
	// }

	public String checkOutBoundCall() throws InterruptedException {
		String number = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 100);
			wait.until(ExpectedConditions.visibilityOfElementLocated(connectingTocustomerTextOnCallPAnel));

		} catch (Exception e) {

			System.out.println("unable to visibled incomingCallFromMsg for userName : " + userName + " profile is: "
					+ profileName + " exception is :::: " + e);
			Assert.assertEquals(e, "success");
		}

		try {
			boolean goToOnCallState = false;
			long div1StartTime = System.currentTimeMillis();
			long div1EndTime = div1StartTime + 1500;
			System.out.println("div 1 assertions start : " + profileName);
			while (!goToOnCallState && System.currentTimeMillis() < div1EndTime) {
				if (!driver.findElement(wrapUpCallButton).isDisplayed()) {
					div1ForOutBound();
					elementsShouldNotBeDisplayedInDiv1ForOutBound();
				} else {
					goToOnCallState = true;
				}
			}
			System.out.println("div 1 assertions end : " + profileName);
		} catch (Exception e) {
			System.out.println(" profile is: " + profileName + " exception is :::: " + e);
			Assert.assertEquals(e, "success");
		}

		try {
			WebDriverWait wait = new WebDriverWait(driver, 100);
			wait.until(ExpectedConditions.visibilityOfElementLocated(wrapUpCallButton));
		} catch (Exception e) {
			System.out.println("unable to visibled wrapUpCallButton for userName : " + userName + " profile is: "
					+ profileName + " exception is :::: " + e);
			Assert.assertEquals(e, "success");
		}

		try {
			Assert.assertTrue(driver.findElement(wrapUpCallButton).isDisplayed());
			if (driver.findElement(wrapUpCallButton).isDisplayed()) {
				isElementsPresentOnAgentPageForOutBound();
				driver.findElement(customerMobileNumberForOutBound).isDisplayed();
				number = driver.findElement(customerMobileNumberForOutBound).getText();

				boolean goToReadyForNextCall = false;
				System.out.println("div 2 assertions start : " + profileName);
				long div2StartTime = System.currentTimeMillis();
				long div2EndTime = div2StartTime + 17000;
				while (!goToReadyForNextCall && System.currentTimeMillis() < div2EndTime) {
					if (!driver.findElement(readyForNextCallButton).isDisplayed()) {
						div2ForOutBound();
						elementsShouldNotBeDisplayedInDiv2ForOutBound();
					} else {
						goToReadyForNextCall = true;
					}
					Thread.sleep(1000);
				}
				System.out.println("div 2 assertions end : " + profileName);
			}
		} catch (Exception e) {
			System.out.println(" profile is: " + profileName + " exception is :::: " + e);
			Assert.assertEquals(e, "success");
		}

		try {
			WebDriverWait wait1 = new WebDriverWait(driver, 100);
			wait1.until(ExpectedConditions.visibilityOfElementLocated(readyForNextCallButton));
		} catch (Exception e) {
			System.out.println("unable to visibled readyForNextCallButton for userName : " + userName + " profile is: "
					+ profileName + " exception is :::: " + e);
			Assert.assertEquals(e, "success");
		}
		try {
			Assert.assertTrue(driver.findElement(readyForNextCallButton).isDisplayed());
			System.out.println("div 3 assertions start : " + profileName);
			long div3StartTime = System.currentTimeMillis();
			long div3EndTime = div3StartTime + 4000;
			while (System.currentTimeMillis() < div3EndTime) {
				div3ForOutBound();
				elementsShouldNotBeDisplayedInDiv3ForOutBound();
				Thread.sleep(1000);
			}
			System.out.println("div 3 assertions end : " + profileName);
			Thread.sleep(1000);
			Actions action = new Actions(driver);
			Assert.assertTrue(driver.findElement(readyForNextCallButton).isDisplayed());
			WebElement element = driver.findElement(readyForNextCallButton);
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.elementToBeClickable(readyForNextCallButton));
			action.moveToElement(element).click(element).build().perform();
			// isAgentReadyStateIsDisplayed();
		} catch (Exception e) {
			System.out.println(" profile is: " + profileName + " exception is :::: " + e);
			Assert.assertEquals(e, "success");
		}
		return number;
	}

	public String hangUpCallFromAgentForOutBound() throws InterruptedException {
		String number = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 100);
			wait.until(ExpectedConditions.visibilityOfElementLocated(connectingTocustomerTextOnCallPAnel));

		} catch (Exception e) {
			System.out.println("unable to visibled incomingCallFromMsg for userName : " + userName + " profile is: "
					+ profileName + " exception is :::: " + e);
			Assert.assertEquals(e, "success");
		}

		try {
			boolean goToOnCallState = false;
			long div1StartTime = System.currentTimeMillis();
			long div1EndTime = div1StartTime + 1500;
			System.out.println("div 1 assertions start : " + profileName);
			while (!goToOnCallState && System.currentTimeMillis() < div1EndTime) {
				if (!driver.findElement(wrapUpCallButton).isDisplayed()) {
					div1ForOutBound();
					elementsShouldNotBeDisplayedInDiv1ForOutBound();
				} else {
					goToOnCallState = true;
				}
			}
			System.out.println("div 1 assertions end : " + profileName);
		} catch (Exception e) {
			System.out.println(" profile is: " + profileName + " exception is :::: " + e);
			Assert.assertEquals(e, "success");
		}

		try {
			WebDriverWait wait = new WebDriverWait(driver, 100);
			wait.until(ExpectedConditions.visibilityOfElementLocated(wrapUpCallButton));
		} catch (Exception e) {
			System.out.println("unable to visibled wrapUpCallButton for userName : " + userName + " profile is: "
					+ profileName + " exception is :::: " + e);
			Assert.assertEquals(e, "success");
		}

		try {
			Assert.assertTrue(driver.findElement(wrapUpCallButton).isDisplayed());
			if (driver.findElement(wrapUpCallButton).isDisplayed()) {
				isElementsPresentOnAgentPageForOutBound();
				driver.findElement(customerMobileNumberForOutBound).isDisplayed();
				number = driver.findElement(customerMobileNumberForOutBound).getText();

				boolean goToReadyForNextCall = false;
				System.out.println("div 2 assertions start : " + profileName);
				long div2StartTime = System.currentTimeMillis();
				long div2EndTime = div2StartTime + 10000;
				while (!goToReadyForNextCall && System.currentTimeMillis() < div2EndTime) {
					if (!driver.findElement(readyForNextCallButton).isDisplayed()) {
						div2ForOutBound();
						elementsShouldNotBeDisplayedInDiv2ForOutBound();
					} else {
						goToReadyForNextCall = true;
					}
					Thread.sleep(1000);
				}
				System.out.println("div 2 assertions end : " + profileName);
			}
		} catch (Exception e) {
			System.out.println(" profile is: " + profileName + " exception is :::: " + e);
			Assert.assertEquals(e, "success");
		}

		try {
			Actions action = new Actions(driver);
			action.moveToElement(driver.findElement(wrapUpCallButton)).click().build().perform();
			Thread.sleep(1000);
		} catch (Exception e) {
			Assert.assertEquals(e, "success");
		}
		try {
			Assert.assertTrue(driver.findElement(readyForNextCallButton).isDisplayed());
			System.out.println("div 3 assertions start : " + profileName);
			long div3StartTime = System.currentTimeMillis();
			long div3EndTime = div3StartTime + 4000;
			while (System.currentTimeMillis() < div3EndTime) {
				div3ForOutBound();
				elementsShouldNotBeDisplayedInDiv3ForOutBound();
				Thread.sleep(1000);
			}
			System.out.println("div 3 assertions end : " + profileName);
			Thread.sleep(1000);
			Actions action = new Actions(driver);
			Assert.assertTrue(driver.findElement(readyForNextCallButton).isDisplayed());
			WebElement element = driver.findElement(readyForNextCallButton);
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.elementToBeClickable(readyForNextCallButton));
			action.moveToElement(element).click(element).build().perform();
			// isAgentReadyStateIsDisplayed();
		} catch (Exception e) {
			System.out.println(" profile is: " + profileName + " exception is :::: " + e);
			Assert.assertEquals(e, "success");
		}
		return number;
	}

	public HashMap<String, Integer> verifyCallsOnAgentPageForMenu(Map<String, String> map) throws InterruptedException {
		WebElement member;
		String memberNumber = "";
		int noOfCustomerHandledByAgent = 0;
		int digitPressed1 = 0;
		int digitPressed2 = 0;
		while (true) {

			if (AgentTest.customerCount >= AgentTest.numberOfCustomers) {
				System.out.println("Breaking the loop for " + AgentTest.customerCount + ": " + profileName);
				break;
			}
			try {

				WebDriverWait wait = new WebDriverWait(driver, 150);
				wait.until(ExpectedConditions.visibilityOfElementLocated(wrapUpCallButton));
				Assert.assertTrue(driver.findElement(wrapUpCallButton).isDisplayed());
				// Assert.assertEquals(driver.findElement(inBoundCallInProgress).getText(),
				// "Inbound call in progress");
				// Assert.assertTrue(driver.findElement(inBoundCallInProgress).isDisplayed());
				if (driver.findElement(wrapUpCallButton).isDisplayed()) {
					AgentTest.customerCount++;
					int count = AgentTest.customerCount;
					logger.info("starting loop for " + profileName + " is::" + count);
					System.out.println("starting loop for " + profileName + " is:: " + count);
					System.out.println("inBoundCallInProgress displayed for " + profileName + " in loop :: " + count);
					noOfCustomerHandledByAgent++;
					// Thread.sleep(1000);
					// System.out.println(driver.findElement(inBoundCallInProgress).getText()
					// + " " + profileName);
					member = driver.findElement(mobileNumber);
					// memberNumber = member.getText();
					System.out.println("mobilenumber in driver " + profileName + "  is::" + member.getText());

					// if (profileName.equalsIgnoreCase("Profile 4")) {
					// Set<String> phoneNumbers = map.keySet();
					// if(phoneNumbers.contains(member.getText())){
					// String value=map.get(member.getText());
					// if(counterMap.get(value)!=null)
					// counterMap.put(value,
					// Integer.parseInt(map.get(value))+1);
					// else
					// counterMap.put(value,1);
					//
					// }
					//
					// }

					Set<String> phoneNumbers = map.keySet();
					if (phoneNumbers.contains(member.getText())) {
						String value = map.get(member.getText());
						System.out.println("mobile number: " + member.getText() + " value: " + value);
						// if(counterMap.get(value)!=null)
						// counterMap.put(value,
						// Integer.parseInt(map.get(value))+1);
						// else
						// counterMap.put(value,1);
						if (value.equals("1")) {
							digitPressed1++;

						} else if (value.equals("2")) {
							digitPressed2++;
						}

					}

					System.out.println("giiiiiiiiii");

					// System.out.println("cucstomer mobile number
					// "+driver.findElement(customerMobileNumber).getText());
					// if(driver.findElement(customerMobileNumber).isDisplayed())
					// {
					// System.out.println("cucstomer mobile number:
					// "+userName+": "
					// +driver.findElement(customerMobileNumber).getText());
					// }
					// else
					// {
					// System.out.println("Mobile Number Not Displayed ");
					// //Assert.assertTrue(driver.findElement(customerMobileNumber).isDisplayed());
					// }
					// System.out.println("Middle statement for "+profileName+"
					// and loop is::" + i);
					// System.out.println("mid statement for
					// inBoundCallInProgress "+profileName+" and loop is::" +
					// i);
					// Thread.sleep(1000);
					WebDriverWait wait11 = new WebDriverWait(driver, 150);
					wait11.until(ExpectedConditions.visibilityOfElementLocated(readyForNextCallButton));
					Assert.assertTrue(driver.findElement(readyForNextCallButton).isDisplayed());
					// onCall.add(member.getText());
					// driver.findElement(readyForNextCallButton).click();
					System.out.println("readyForNextCallButton displayed for " + profileName + " in loop ::" + count
							+ " customers handled by agent::::: " + noOfCustomerHandledByAgent);
					Actions action = new Actions(driver);
					action.moveToElement(driver.findElement(readyForNextCallButton)).perform();
					WebElement element = driver.findElement(readyForNextCallButton);
					Thread.sleep(2000);
					JavascriptExecutor executor = (JavascriptExecutor) driver;
					executor.executeScript("arguments[0].click();", element);
					System.out.println("readyForNextCallButton is clicked for " + profileName + " in loop :: " + count);
					// System.out.println("customer count :
					// "+NewTest.customerCount);
				} else {
					System.out.println("inBoundCallInProgress button is not dispalyed/visibled for : " + userName
							+ " and profile is: " + profileName);
					AgentTest.customerCount--;
					// Assert.assertTrue(false);
					break;
				}
				Thread.sleep(2000);
			} catch (Exception e) {

				System.out.println(" Exception for count " + AgentTest.customerCount + "  username : " + userName
						+ " and profile is: " + profileName + " exception is " + e + " and  refreshing the page");
				// System.out.println("Exception at " + e.toString());
				// System.out.println(profileName +" and mobile number is: " +
				// memberNumber);
				// System.out.println(e.getMessage());
				AgentTest.customerCount--;
				break;
				// Assert.assertTrue(false);

			}

			System.out.println(profileName + " with numberOfCustomers " + AgentTest.numberOfCustomers + " == "
					+ AgentTest.customerCount + " customerCount");

		}
		System.out
				.println("total noOfCustomerHandledByAgent for " + profileName + " is: " + noOfCustomerHandledByAgent);
		// static Map<String,Integer> counterMap=new HashMap<String,Integer>();

		counterMap.put("noOfCustomerHandledByAgent", noOfCustomerHandledByAgent);
		counterMap.put("digitPressed1", digitPressed1);
		counterMap.put("digitPressed2", digitPressed2);
		return (HashMap<String, Integer>) counterMap;
	}

}
