package press3Pages.ManagerPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import press3Test.BrowserFunctions;

public class GeneralSettings extends BrowserFunctions {

By serviceLevelThreshold = By.id("SLAThresholdInSeconds");
By serviceLevelType = By.id("selSLAType");
By slaFormula = By.id("DailExtension");
By SLATargetPercentage = By.id("SLATargetPercentage");
By serviceLevelSaveBtn = By.id("btnUpdateSLA");
// Sms And Email Templates
By smsAndEmailTemplates = By.xpath("//*[@id='headingTwo']/h4/a");
By addNewTemp = By.id("lnkAddSmsTemplate");
By smsTempName = By.id("txtSmsTemplateName");
By smsTempContent = By.id("txtSmsTemplateContent");
By btnSaveSmsTemplate = By.id("btnSaveSmsTemplate");
By selSmsTemplate = By.id("selSmsTemplate");
By selectedTemplateSmsContent = By.id("smsContent");
By btnUpdateSmsTemplate = By.id("btnUpdateSmsTemplate");
By btnDeleteSmsTemplate = By.id("btnDeleteSmsTemplate");
By deleteBtnConfirm = By.id("btnCnfm");
By emailTemplete = By.xpath("//li[@class='active']");
By addEmailTemplateBtn = By.id("lnkAddEmailTemplate");
By emailTemplateName = By.id("txtEmailTemplateName");
By emailTemplateSubject = By.id("txtEmailTemplateSubject");
By emailTemplateContent = By
.xpath("//*[@id=\"newEmailTemplateModal\"]/div[2]/div/div[2]/table/tbody/tr[3]/td[2]/div/div");
By emailTemaplateSaveBtn = By.id("btnSaveEmailTemplate");
By selEmailTemplate = By.id("selEmailTemplate");
By selectedEmailTemplateContent = By.xpath("//*[@id=\"tab_15_2\"]/div/div[3]/div/div");
By btnUpdateEmailTemplate = By.id("btnUpdateEmailTemplate");
By btnDeleteEmailTemplate = By.id("btnDeleteEmailTemplate");
// Ticket Management
By tktMngmnt = By.xpath("//*[@id='headingFive']/h4/a");
// Staus
By createStatusBtn = By.xpath("//*[@id=\"createStatus\"]/i");
By statusName = By.id("txtStatusName");
By updateStatus = By.xpath("//*[@id=\"updateStatus\"]/i");
By statusDeleteItem = By.xpath("//*[@id=\"accountStatuses\"]/div[4]/div/div/label/input");
By deleteStatus = By.xpath("//*[@id=\"deleteStatus\"]/i");
// prority
By createPriorityBtn = By.xpath("//*[@id=\"createPriority\"]/i");
By priorityName = By.id("txtPriorityName");
By priorityTargetClosing = By.id("txtClosingValue");
By targetMin = By.id("ddlTargetUnit");
By updatePriorityBtn = By.xpath("//*[@id=\"updatePriority\"]/i");
By prioritySelectedItem = By.xpath("//*[@id=\"accountPriorities\"]/div[5]/label[1]/input");
By editPriority = By.xpath("//*[@id=\"editPriority\"]/i");
By editClosingBtn = By.id("txtClosingValue174");
By deletePriorityBtn = By.xpath("//*[@id=\"deletePriority\"]/i");
// TicketNotifications
By customerSmsDisableBtn = By.xpath(
"//*[@id=\"collapseFive\"]/div/div[1]/div[3]/div/div[2]/div/table/tbody/tr[2]/td[2]/label/div/div/span[3]");
By customerEmailDisableBtn = By.xpath(
"//*[@id=\"collapseFive\"]/div/div[1]/div[3]/div/div[2]/div/table/tbody/tr[2]/td[3]/label/div/div/span[3]");
By ticketManagerSmsDisableBtn = By.xpath(
"//*[@id=\"collapseFive\"]/div/div[1]/div[3]/div/div[2]/div/table/tbody/tr[3]/td[2]/label/div/div/span[3]");
By ticketManagerEmailBtn = By.xpath(
"//*[@id=\"collapseFive\"]/div/div[1]/div[3]/div/div[2]/div/table/tbody/tr[3]/td[3]/label/div/div/span[3]");
By templateForSms = By.id("ddlSMSTemplateType");
By templateForEMail = By.id("ddlEmailTemplateType");
// Cbd
By cbd = By.xpath("//*[@id=\"headingFour\"]/h4/a");
By addNewField = By.id("btnAddField");
By fieldName = By.id("txtFieldName");
By fieldType = By.id("ddlFieldType");
By mandatoryField = By.id("ddlInputType");
By maxCharLimit = By.id("txtMaxchars");
By madatoryField = By.id("ddlMandatory");
By allowSpecialChars = By.id("ddlSpecialchars");
By addNewSaveBtn = By.id("btnSaveField");
By editFieldBtn = By.id("btnEditField");
By editField1 = By.xpath("//*[@class =\"row editRow\"]//*[@fieldname=\"Location\"]");
By editField2 = By.xpath("//*[@class =\"row editRow\"]//*[@fieldname=\"World\"]");
By editField3 = By.xpath("//*[@class =\"row editRow\"]//*[@fieldname=\"Complete\"]");
By updateBtn = By.id("btnUpdateFields");
By option1 = By.xpath("//*[@id=\"optionsData\"]/div/input");
By addOptionBtn = By.id("addOption");
By option2 = By.xpath("//*[@id=\"optionsData\"]/div[2]/input");
By areaMaxChars = By.id("txtareaMaxchars");
By cbdSaveBtn = By.id("btnAddFields");
By cbdUpadteBtn = By.id("btnUpdateFields");

public void clickSmsAndEmailTemlete() throws InterruptedException {
driver.findElement(smsAndEmailTemplates).click();
Thread.sleep(1000);
}

public void addSmsTemplate() throws InterruptedException {
driver.findElement(addNewTemp).click();
Thread.sleep(2000);
WebElement smst = driver.findElement(smsTempName);
smst.sendKeys("Happy");
Thread.sleep(2000);
WebElement smsc = driver.findElement(smsTempContent);
smsc.sendKeys("Happy with Press3");
Thread.sleep(2000);
driver.findElement(btnSaveSmsTemplate).click();
Thread.sleep(2000);
}

public void addEmailTemplate() throws InterruptedException {
driver.findElement(emailTemplete).click();
Thread.sleep(1000);
driver.findElement(addEmailTemplateBtn).click();
Thread.sleep(2000);
WebElement etn = driver.findElement(emailTemplateName);
etn.sendKeys("Welcome Email");
Thread.sleep(2000);
WebElement ets = driver.findElement(emailTemplateSubject);
ets.sendKeys("Email for welcome");
Thread.sleep(2000);
WebElement etc = driver.findElement(emailTemplateContent);
etc.sendKeys("Welcome to Press3, we are glad you opted for Press3");
Thread.sleep(2000);
driver.findElement(emailTemaplateSaveBtn).click();
Thread.sleep(2000);
}

public void updateSmsTemplate() throws InterruptedException {
Select smss = new Select(driver.findElement(selSmsTemplate));
smss.selectByVisibleText("Happy");
Thread.sleep(2000);
WebElement upsms = driver.findElement(selectedTemplateSmsContent);
upsms.sendKeys(" Thank you for calling press3");
Thread.sleep(2000);
driver.findElement(btnUpdateSmsTemplate).click();
Thread.sleep(2000);
}

public void updateEmailTemplate() throws InterruptedException {

Select emails = new Select(driver.findElement(selEmailTemplate));
emails.selectByVisibleText("Welcome Email");
Thread.sleep(2000);
WebElement upemail = driver.findElement(selectedEmailTemplateContent);
upemail.sendKeys(" Thank You");
Thread.sleep(2000);
driver.findElement(btnUpdateEmailTemplate).click();
Thread.sleep(2000);
}

public void deleteSmsTemplate() throws InterruptedException {
Select del = new Select(driver.findElement(selSmsTemplate));
del.selectByVisibleText("Happy");
Thread.sleep(1000);
driver.findElement(btnDeleteSmsTemplate).click();
Thread.sleep(2000);
driver.findElement(deleteBtnConfirm).click();
Thread.sleep(2000);
}

public void deleteEmailTemplate() throws InterruptedException {
Select demail = new Select(driver.findElement(selEmailTemplate));
demail.selectByVisibleText("Welcome Email");
Thread.sleep(1000);
driver.findElement(btnDeleteEmailTemplate).click();
Thread.sleep(2000);
driver.findElement(deleteBtnConfirm).click();
Thread.sleep(2000);
}

public void smsTemplate() throws InterruptedException {
addSmsTemplate();
updateSmsTemplate();
deleteSmsTemplate();
}

public void emailTemplate() throws InterruptedException {
addEmailTemplate();
updateEmailTemplate();
deleteEmailTemplate();

}

public void clickOnTicketManagement() throws InterruptedException {
driver.findElement(tktMngmnt).click();
Thread.sleep(5000);
}

public void statusTicketManagement() throws InterruptedException {
driver.findElement(createStatusBtn).click();
Thread.sleep(2000);
WebElement status = driver.findElement(statusName);
status.sendKeys("Invalid");
Thread.sleep(2000);
driver.findElement(updateStatus).click();
Thread.sleep(2000);
driver.findElement(statusDeleteItem).click();
Thread.sleep(2000);
driver.findElement(deleteStatus).click();
Thread.sleep(2000);
}

public void ticketNotificationsTicketManagement() throws InterruptedException {
driver.findElement(customerSmsDisableBtn).click();
Thread.sleep(2000);
driver.findElement(customerEmailDisableBtn).click();
Thread.sleep(2000);
driver.findElement(ticketManagerSmsDisableBtn).click();
Thread.sleep(2000);
driver.findElement(ticketManagerEmailBtn).click();
Thread.sleep(2000);
Select tsdp = new Select(driver.findElement(templateForSms));
tsdp.selectByVisibleText("Thank You");
Thread.sleep(2000);
Select sedrp = new Select(driver.findElement(templateForEMail));
sedrp.selectByVisibleText("hi");
Thread.sleep(2000);
}

public void priorityTicketManagement() throws InterruptedException {
driver.findElement(createPriorityBtn).click();
Thread.sleep(2000);
WebElement pname = driver.findElement(priorityName);
pname.sendKeys("Trivial");
Thread.sleep(2000);
WebElement tcv = driver.findElement(priorityTargetClosing);
tcv.sendKeys("5");
Thread.sleep(2000);
Select tdd = new Select(driver.findElement(targetMin));
tdd.selectByVisibleText("Days");
Thread.sleep(2000);
driver.findElement(updatePriorityBtn).click();
Thread.sleep(2000);
driver.findElement(prioritySelectedItem).click();
Thread.sleep(2000);
driver.findElement(editPriority).click();
Thread.sleep(2000);
WebElement upp = driver.findElement(editClosingBtn);
upp.clear();
upp.sendKeys("3");
Thread.sleep(2000);
driver.findElement(updatePriorityBtn).click();
Thread.sleep(2000);
driver.findElement(prioritySelectedItem).click();
Thread.sleep(2000);
driver.findElement(deletePriorityBtn).click();
Thread.sleep(2000);

}

public void clickCallerBasicDetails() throws InterruptedException {
driver.findElement(cbd).click();
Thread.sleep(2000);
}

public void callerBasicDetails() throws InterruptedException {
driver.findElement(addNewField).click();
Thread.sleep(2000);
WebElement fname = driver.findElement(fieldName);
fname.sendKeys("Location");
Thread.sleep(2000);
Select ftype = new Select(driver.findElement(fieldType));
ftype.selectByVisibleText("Textbox");
Thread.sleep(2000);
Select itype = new Select(driver.findElement(mandatoryField));
itype.selectByVisibleText("AlphaNumerics");
Thread.sleep(2000);
WebElement mchar = driver.findElement(maxCharLimit);
mchar.sendKeys("25");
Thread.sleep(2000);
Select mand = new Select(driver.findElement(madatoryField));
mand.selectByVisibleText("Yes");
Thread.sleep(2000);
Select asc = new Select(driver.findElement(allowSpecialChars));
asc.selectByVisibleText("Yes");
Thread.sleep(2000);
driver.findElement(addNewSaveBtn).click();
Thread.sleep(2000);
driver.findElement(editFieldBtn).click();
Thread.sleep(2000);
driver.findElement(editField1).click();
Thread.sleep(2000);
driver.findElement(updateBtn).click();
Thread.sleep(2000);
driver.findElement(addNewField).click();
Thread.sleep(2000);
WebElement pname = driver.findElement(fieldName);
pname.sendKeys("World Places");
Thread.sleep(2000);
Select ddp = new Select(driver.findElement(fieldType));
ddp.selectByVisibleText("Dropdown");
Thread.sleep(2000);
WebElement dropn = driver.findElement(option1);
dropn.sendKeys("USA");
Thread.sleep(2000);
driver.findElement(addOptionBtn).click();
Thread.sleep(2000);
WebElement dropm = driver.findElement(option2);
dropm.sendKeys("Australia");
Thread.sleep(2000);
Select mannn = new Select(driver.findElement(madatoryField));
mannn.selectByVisibleText("No");
Thread.sleep(2000);
driver.findElement(addNewSaveBtn).click();
Thread.sleep(2000);
driver.findElement(editFieldBtn).click();
Thread.sleep(2000);
driver.findElement(editField2).click();
Thread.sleep(2000);
driver.findElement(updateBtn).click();
Thread.sleep(2000);
driver.findElement(addNewField).click();
Thread.sleep(2000);
WebElement tname = driver.findElement(fieldName);
tname.sendKeys("Complete Address");
Thread.sleep(2000);
Select ddds = new Select(driver.findElement(fieldType));
ddds.selectByVisibleText("Textarea");
Thread.sleep(2000);
WebElement machar = driver.findElement(areaMaxChars);
machar.sendKeys("30");
Thread.sleep(2000);
Select dddp = new Select(driver.findElement(madatoryField));
dddp.selectByVisibleText("Yes");
Thread.sleep(2000);
driver.findElement(addNewSaveBtn).click();
Thread.sleep(2000);
driver.findElement(cbdSaveBtn).click();
Thread.sleep(2000);
driver.findElement(editFieldBtn).click();
Thread.sleep(2000);
driver.findElement(editField3).click();
Thread.sleep(2000);
driver.findElement(cbdUpadteBtn).click();
Thread.sleep(2000);
}

public void serviceLevel() throws InterruptedException {
WebElement slat = driver.findElement(serviceLevelThreshold);
slat.clear();
slat.sendKeys("30");
Thread.sleep(2000);
Select ssstype = new Select(driver.findElement(serviceLevelType));
ssstype.selectByVisibleText("Positively Impact");
Thread.sleep(2000);
driver.findElement(slaFormula).click();
Thread.sleep(1000);
WebElement tsla = driver.findElement(SLATargetPercentage);
tsla.clear();
tsla.sendKeys("90");
Thread.sleep(2000);
driver.findElement(serviceLevelSaveBtn).click();
Thread.sleep(1000);

}

}