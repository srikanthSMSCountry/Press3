package press3Pages.ManagerPages;

import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import press3Test.BrowserFunctions;

public class IvrStudios extends BrowserFunctions {

public WebDriver driver;
public String profileName;
public String userName;
public String passWord;

// final static Logger logger = Logger.getLogger(AgentHomePage.class);
// public Map<String,Integer> counterMap=new HashMap<String,Integer>();
public IvrStudios(WebDriver driver, String profileName, String userName, String passWord) {
this.driver = driver;
this.profileName = profileName;
this.userName = userName;
this.passWord = passWord;
}

By createNewIvrBtn = By.id("lblCreateNewIVR");
By studioname = By.id("txtStudioName");
By purpose = By.id("selStudioPurpose");
By callerId = By.id("selCallerIdNumbers");
By callDirectionInbound = By.xpath("//*[@class=\"table no-border\"]//tr[2]//label[2]//input");
By proceedBtn = By.id("btnSaveStudioDetails");
By dropCallBtn = By
.xpath("//*[@class='ui-widget-content node next-step ui-draggable ui-droppable']//tr[3]//td[2]//div[2]");
By playMessage = By.id("pl_msg");
By playmessageUpload = By.xpath("//*[@class=\"module-list\"]//div[1]//input");

By playMessageSave = By.xpath("//*[@id=\"ivr_popups\"]/div[1]//*[@class=\"btn green module-save\"]");
By menuBtn = By.id("menu");
// Ring Users
By ringUsers = By.id("ring");
By setSkillGrp = By.xpath("//*[@class='skillGroupclass form-control']");
By ringGrp = By.xpath("//*[@class='ringstrategy form-control']");
By waitClip = By.xpath("//*[@id=\"ivr_popups\"]/div[2]//*[@class=\"flUpload_11\"]");
By busyClip = By.xpath("//*[@id=\"ivr_popups\"]/div[2]//*[@class=\"flUpload_11_busy_clip\"]");
By holdClip = By.xpath("//*[@id=\"ivr_popups\"]/div[2]//*[@class=\"flUpload_11_hold_clip\"]");
By ringUsersSaveBtn = By.xpath("//*[@class='btn green module-save']");
// Menu options
By menuName = By.name("txtMenuName_3");
By grtclip = By.xpath("//*[@id='fileupload_div_GttMsg']//input[2]");
By key1 = By.xpath("//*[@class='module-left']//div[3]//*[@class='opt form-control']");
By keyValue1 = By.xpath("//*[@class='module-left']//div[3]//*[@class='opt_val form-control']");
By key2 = By.xpath("//*[@class='module-left']//div[5]//*[@class='opt form-control']");
By keyValue2 = By.xpath("//*[@class='module-left']//div[5]//*[@class='opt_val form-control']");
By addNewBtn = By.xpath("//*[@class='module-left']//*[@class='btn btn-default btn-sm addmore font-green']");
By key3 = By.xpath("//*[@class='module-left']//div[7]//*[@class='opt form-control']");
By keyValue3 = By.xpath("//*[@class='module-left']//div[7]//*[@class='opt_val form-control']");
By textMessage = By.xpath("//*[@class='invalid-key module-left']//label[2]//*[@value='text']");
By gotoDrpDwn = By.xpath("//*[@class='margin-top-15']//*[@class='form-control input-inline']");
By footerSaveBtn = By.xpath("//*[@class='modal-footer']//*[@class='btn green module-save']");

// menu1options

By menu1dropCall = By.xpath("//*[@p_id='5']//td[2][contains(@class,'nodecontent')]//*[@class='nodename']");
By menu2dropCall = By.xpath("//*[@p_id='7']//td[2][contains(@class,'nodecontent')]//*[@class='nodename']");

public void setmenu1() throws InterruptedException { // menu feedback
driver.findElement(menu1dropCall).click();
Thread.sleep(500);
driver.findElement(ringUsers).click();
Thread.sleep(1000);
Select skillgroup = new Select(driver.findElement(setSkillGrp));
skillgroup.selectByVisibleText("feedback");
Thread.sleep(500);
Select ringgroup = new Select(driver.findElement(ringGrp));
ringgroup.selectByVisibleText("Circular Call Distribution");
Thread.sleep(1000);
WebElement waitclip = driver.findElement(By.xpath("//*[@id='fileupload_div']/div/input[1]"));
waitclip.sendKeys("E:\\urban dart wait clip.mp3");
Thread.sleep(1000);
Actions act=new Actions(driver);
WebElement busyclip = driver.findElement(By.xpath("//*[@id='fileupload_div_busy_clip']/div/input[1]"));
act.moveToElement(busyclip).perform();
busyclip.sendKeys("E:\\urban dart wait clip.mp3");
Thread.sleep(1000);
WebElement holdclip = driver.findElement(By.xpath("//*[@id='fileupload_div_hold_clip']/div/input[1]"));
holdclip.sendKeys("E:\\urban dart wait clip.mp3");
Thread.sleep(1000);
driver.findElement(footerSaveBtn).click();
Thread.sleep(1000);
}

public void setMenu2() throws InterruptedException { // menu complaint
driver.findElement(menu2dropCall).click();
driver.findElement(ringUsers).click();
Thread.sleep(1000);
Select skillgroup1 = new Select(driver.findElement(setSkillGrp));
skillgroup1.selectByVisibleText("Complaint");
Thread.sleep(500);
Select ringgroup1 = new Select(driver.findElement(ringGrp));
ringgroup1.selectByVisibleText("Circular Call Distribution");
Thread.sleep(1000);
WebElement waitclip1 = driver.findElement(waitClip);
waitclip1.sendKeys("E:\\urban dart wait clip.mp3");
Thread.sleep(1000);
WebElement busyclip1 = driver.findElement(busyClip);
busyclip1.sendKeys("E:\\Urban Dart Busy Clip.mp3");
Thread.sleep(1000);
WebElement holdclip1 = driver.findElement(holdClip);
holdclip1.sendKeys("E:\\urban dart wait clip.mp3");
Thread.sleep(1000);
driver.findElement(ringUsersSaveBtn).click();
Thread.sleep(1000);

}

public void setMenu3() throws InterruptedException { // menu complaint
driver.findElement(menu2dropCall).click();
driver.findElement(ringUsers).click();
Thread.sleep(1000);
Select skillgroup1 = new Select(driver.findElement(setSkillGrp));
skillgroup1.selectByVisibleText("Enquiry");
Thread.sleep(500);
Select ringgroup1 = new Select(driver.findElement(ringGrp));
ringgroup1.selectByVisibleText("Circular Call Distribution");
Thread.sleep(1000);
WebElement waitclip1 = driver.findElement(waitClip);
waitclip1.sendKeys("E:\\urban dart wait clip.mp3");
Thread.sleep(1000);
WebElement busyclip1 = driver.findElement(busyClip);
busyclip1.sendKeys("E:\\Urban Dart Busy Clip.mp3");
Thread.sleep(1000);
WebElement holdclip1 = driver.findElement(holdClip);
holdclip1.sendKeys("E:\\urban dart wait clip.mp3");
Thread.sleep(1000);
driver.findElement(ringUsersSaveBtn).click();
Thread.sleep(1000);

}

protected String getSaltString() {
String SALTCHARS = "123456789";
StringBuilder salt = new StringBuilder();
Random rnd = new Random();
while (salt.length() < 3) { // length of the random string.
int index = (int) (rnd.nextFloat() * SALTCHARS.length());
salt.append(SALTCHARS.charAt(index));
}
String saltStr = salt.toString();
return saltStr;

}

public void createNewIvrStudio() throws InterruptedException { // 1
driver.findElement(createNewIvrBtn).click();
Thread.sleep(1000);
driver.findElement(studioname).sendKeys("Automated IVR" + getSaltString());
Thread.sleep(500);
driver.findElement(callDirectionInbound).click();
Thread.sleep(1000);
Select purpos = new Select(driver.findElement(purpose));
purpos.selectByVisibleText("Testing");
Thread.sleep(500);
Select callerid = new Select(driver.findElement(callerId));
callerid.selectByVisibleText("80192218710");
Thread.sleep(500);
driver.findElement(proceedBtn).click();
Thread.sleep(2000);
this.switchToSecondWindow();
}

public void setPlayMessge() throws InterruptedException { // 2
driver.findElement(dropCallBtn).click();
Thread.sleep(500);
driver.findElement(playMessage).click();
Thread.sleep(1000);
driver.findElement(playmessageUpload).sendKeys("E:\\Urban Dart Welcome Clip.mp3"); /// File
/// needed
/// in
/// local
Thread.sleep(1000);
driver.findElement(playMessageSave).click();
Thread.sleep(1000);
}

public void setRingUserForMenuIvr() throws InterruptedException{
driver.findElement(dropCallBtn).click();
Thread.sleep(500);
driver.findElement(ringUsers).click();
Thread.sleep(1000);
Select skillgroup = new Select(driver.findElement(setSkillGrp));
skillgroup.selectByVisibleText("feedback");
Thread.sleep(500);
Select ringgroup = new Select(driver.findElement(ringGrp));
ringgroup.selectByVisibleText("Circular Call Distribution");
Thread.sleep(1000);
WebElement waitclip = driver.findElement(By.xpath("//*[@id='fileupload_div']/div/input[1]"));
waitclip.sendKeys("E:\\urban dart wait clip.mp3"); // file need
// =====================================
Thread.sleep(1000);
// JavascriptExecutor js = (JavascriptExecutor) driver;
// js.executeScript("javascript:window.scrollBy(0,500)");
Thread.sleep(1000);
Actions act= new Actions(driver);
WebElement busyclip = driver.findElement(By.xpath("//*[@id='fileupload_div_busy_clip']/div/input[1]"));
act.moveToElement(busyclip).perform();
busyclip.sendKeys("E:\\urban dart wait clip.mp3");
Thread.sleep(1000);
WebElement holdclip = driver.findElement(By.xpath("//*[@id='fileupload_div_hold_clip']/div/input[1]"));
holdclip.sendKeys("E:\\urban dart wait clip.mp3");
Thread.sleep(1000);
driver.findElement(ringUsersSaveBtn).click();
Thread.sleep(1000);
}
public void setRingUser() throws InterruptedException { // 3
driver.findElement(dropCallBtn).click();
Thread.sleep(500);
driver.findElement(ringUsers).click();
Thread.sleep(1000);
Select skillgroup = new Select(driver.findElement(setSkillGrp));
skillgroup.selectByVisibleText("feedback");
Thread.sleep(500);
Select ringgroup = new Select(driver.findElement(ringGrp));
ringgroup.selectByVisibleText("Circular Call Distribution");
Thread.sleep(1000);
WebElement waitclip = driver.findElement(busyClip);
waitclip.sendKeys("E:\\urban dart wait clip.mp3"); // file need
// =====================================
Thread.sleep(1000);
// JavascriptExecutor js = (JavascriptExecutor) driver;
// js.executeScript("javascript:window.scrollBy(0,500)");
Thread.sleep(1000);
Actions act= new Actions(driver);
WebElement busyclip = driver.findElement(busyClip);
act.moveToElement(busyclip).perform();
busyclip.sendKeys("E:\\urban dart wait clip.mp3");
Thread.sleep(1000);
WebElement holdclip = driver.findElement(holdClip);
holdclip.sendKeys("E:\\urban dart wait clip.mp3");
Thread.sleep(1000);
driver.findElement(ringUsersSaveBtn).click();
Thread.sleep(1000);
}

public void setMenu() throws InterruptedException {
driver.findElement(dropCallBtn).click();
Thread.sleep(500);
driver.findElement(menuBtn).click();
Thread.sleep(1000);
driver.findElement(menuName).sendKeys("Automated Demo");
Thread.sleep(500);
driver.findElement(grtclip).sendKeys("E:\\AT Menu clip.mp3"); // File
// needed
// in
// local
Thread.sleep(500);
driver.findElement(key1).sendKeys("1");
Thread.sleep(500);
driver.findElement(keyValue1).sendKeys("Feedback");
Thread.sleep(500);
driver.findElement(key2).sendKeys("2");
Thread.sleep(500);
driver.findElement(keyValue2).sendKeys("Complaint");
Thread.sleep(500);
driver.findElement(addNewBtn).click();
Thread.sleep(500);
driver.findElement(key3).sendKeys("3");
Thread.sleep(500);
driver.findElement(keyValue3).sendKeys("Enquiry");
Thread.sleep(500);
driver.findElement(textMessage).click();
Thread.sleep(500);
Select got = new Select(driver.findElement(gotoDrpDwn));
got.selectByVisibleText("2");
driver.findElement(footerSaveBtn).click();
JavascriptExecutor js = (JavascriptExecutor) driver;
js.executeScript("javascript:window.scrollBy(0,500)");
Thread.sleep(1000);
}

public void createNewIvr() throws InterruptedException { /// main method

// Below not done . Not understood.
WebElement targetLocator = driver
.findElement(By.xpath("//*[@p_id='3']//td[2][contains(@class,'nodecontent')]//*[@class='nodename']"));
targetLocator.click();
WebElement sourceLocator = driver.findElement(By.xpath("//*[@class=\"fa fa-ban\"]"));
sourceLocator.click();
Thread.sleep(1000);
driver.findElement(By.id("save-btn")).click();
Thread.sleep(500);
driver.switchTo().alert().accept();

}

public void menu1EndFlow() throws InterruptedException {
WebElement targetLocator = driver.findElement(
By.xpath("//*[@id='12'][@p_id='6']//td[2][contains(@class, 'nodecontent')]//*[@class='nodename']"));
targetLocator.click();
WebElement sourceLocator = driver.findElement(By.xpath("//*[@class=\"fa fa-ban\"]"));
sourceLocator.click();
Thread.sleep(1000);
}

public void menu2Endflow() throws InterruptedException {
WebElement targetLocator1 = driver.findElement(
By.xpath("//*[@id='13'][@p_id='8']//td[2][contains(@class, 'nodecontent')]//*[@class='nodename']"));
targetLocator1.click();
WebElement sourceLocator1 = driver.findElement(By.xpath("//*[@class=\"fa fa-ban\"]"));
sourceLocator1.click();
Thread.sleep(1000);
}

public void menu3EndFlow() throws InterruptedException {
WebElement targetLocator2 = driver.findElement(
By.xpath("//*[@id='14'][@p_id='10']//td[2][contains(@class, 'nodecontent')]//*[@class='nodename']"));
targetLocator2.click();
WebElement sourceLocator2 = driver.findElement(By.xpath("//*[@class=\"fa fa-ban\"]"));
sourceLocator2.click();
Thread.sleep(1000);
driver.findElement(By.id("save-btn")).click();
Thread.sleep(500);
driver.switchTo().alert().accept();
driver.findElement(By.xpath("//*[@id='tblActiveStudios']/tr[1]/td[7]/span[1]/a/i")).click();
Thread.sleep(500);
JavascriptExecutor js1 = (JavascriptExecutor) driver;
js1.executeScript("javascript:window.scrollBy(0,800)");
}

public void createMenuIvr() throws InterruptedException {
createNewIvrStudio();
setPlayMessge();
setMenu();
JavascriptExecutor js = (JavascriptExecutor) driver;
js.executeScript("javascript:window.scrollBy(0,500)");
Thread.sleep(1000);
setRingUser();
setmenu1(); // =================================================
menu1EndFlow();
setMenu2(); // =============================================================
menu2Endflow();
setMenu3(); // ===========================================================

}

public void switchToSecondWindow() throws InterruptedException {
Set<String> allWindowHandles = driver.getWindowHandles();
String w1 = (String) allWindowHandles.toArray()[0];
String w2 = (String) allWindowHandles.toArray()[1];
driver.switchTo().window(w2);
Thread.sleep(1000);
}

}


