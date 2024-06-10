package compliance.PageObjMethods;

import java.util.regex.*;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import Generic.TestBase.BrowserConfig;
import Generic.TestBase.CommonMethods;
import Generic.TestBase.testBase;

public class Data_Minimization extends testBase{
	
	private WebDriver driver = BrowserConfig.getDriver();
	WebDriverWait wait = new WebDriverWait(driver, 45);
	JavascriptExecutor jse = (JavascriptExecutor) driver;
	CommonMethods commonMethods = PageFactory.initElements(driver, CommonMethods.class);
	
	@FindBy(xpath = "//input[@name='username']")
	public WebElement platformUsername;

	@FindBy(xpath = "//input[@name='password']")
	public WebElement platformPassword;

	@FindBy(xpath = "//*[text()='Login']")
	public WebElement loginButton;
	
	@FindBy(xpath="//button[@value='no']")
	public WebElement click_No;
	
	@FindBy(xpath="//*[@id=\"DescPop\"]/div[2]/form/div[3]/button")
	public WebElement accept;
	
	@FindBy(xpath = "//*[@id=\"DescPop\"]/div[2]/form/div[1]")
	public WebElement heading;
	
	@FindBy(xpath = "//a[@href='#/manage']")
	public WebElement managetab;
	
	@FindBy(xpath = "//a[@href='#/ldapusers']")
	public WebElement manageLdapUser;
	
	@FindBy(xpath = "//span[text()='Add LDAP User']")
	public WebElement addLdapuser;
	
	@FindBy(xpath = "//*[@id=\"addLdapUserModal\"]/div[2]/div/form/div[2]/div[1]/input")
	public WebElement Name;
	
	@FindBy(xpath = "//*[@id=\"addLdapUserModal\"]/div[2]/div/form/div[2]/div[2]/input")
	public WebElement Email;
	
	@FindBy(xpath = "//*[@id=\"addLdapUserModal\"]/div[2]/div/form/div[2]/div[4]/input")
	public WebElement passwordValue;
	
	@FindBy(id = "confirmpassword")
	public WebElement confirmpassword;
	
	@FindBy(xpath = "//button[text()='Add User']")
	public WebElement addUser;
	
	@FindBy(xpath = "//*[@id=\"ConfirmAdd\"]/div[2]/div/div/div/h5")
	public WebElement confirmMsg;
	
	@FindBy(xpath = "//span[@class='errorMessage']")
	public WebElement errMsg;
	
	@FindBy(xpath = "//*[@id=\"ConfirmAdd\"]/div[2]/div/div/div/button")
	public WebElement ok;
	
	public boolean devOpsPlatformLogin(String username,String password) throws InterruptedException {
		SoftAssert softassert = new SoftAssert();
		commonMethods.waitForPageToLoad();
		try {
			wait.until(ExpectedConditions.visibilityOf(platformUsername));
			logger.log(LogStatus.INFO, "Enter Username");
			System.out.println("Enter Username");
			platformUsername.click();
			platformUsername.sendKeys(username);
			
			wait.until(ExpectedConditions.visibilityOf(platformPassword));
			logger.log(LogStatus.INFO, "Enter Password");
			System.out.println("Enter password");
			platformPassword.click();
			platformPassword.sendKeys(password);
			commonMethods.waitForPageToLoad();
			loginButton.click();
			commonMethods.waitForPageToLoad();
			click_No.click();
			commonMethods.waitForPageToLoad();
			
			softassert.assertEquals(heading.getText(), "Confidential Information – Reminder");
			accept.click();
			commonMethods.waitForPageToLoad();
			
			logger.log(LogStatus.INFO, "Click on Manage Platform tab");
			System.out.println("Click on Manage Platform tab");
			managetab.click();
			commonMethods.waitForPageToLoad();
			
			logger.log(LogStatus.INFO, "Click on manage LDAP User");
			System.out.println("Click on manage LDAP User");
			manageLdapUser.click();
			commonMethods.waitForPageToLoad();
		
			logger.log(LogStatus.INFO, "Click on add Ldap User");
			System.out.println("Click on add Ldap User");
			addLdapuser.click();
			commonMethods.waitForPageToLoad();
			
			wait.until(ExpectedConditions.visibilityOf(Name));
			logger.log(LogStatus.INFO, "There is no phone number, gender field");
			System.out.println("There is no phone number, gender field");
			
				
			logger.log(LogStatus.PASS, "Data is being collected only what is required for the application");
			System.out.println("Data is being collected only what is required for the application");
			return true;
		}
		
		catch (Exception e) {
			System.out.println(e);
			logger.log(LogStatus.INFO, "There is phone number, gender field");
			logger.log(LogStatus.FAIL, "Extra data is being colleted");
			System.out.println("There is phone number, gender field");
			Assert.fail("Extra data is being colleted");
			return false;
		}
	}

}
