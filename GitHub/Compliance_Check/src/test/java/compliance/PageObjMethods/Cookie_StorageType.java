package compliance.PageObjMethods;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
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

public class Cookie_StorageType extends testBase{
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

	@FindBy(xpath = "//a[.='Sign Out']")
	public WebElement logoutButton;

	@FindBy(xpath = "//*[@id='invalid']")
	public WebElement loginErrorMessage;

	@FindBy(xpath = "//button[.='Try login']")
	public WebElement tryLoginButton;

	@FindBy(xpath="//button[@value='no']")
	public WebElement click_No;
	
	@FindBy(xpath = "//*[@id=\"DescPop\"]/div[2]/form/div[2]/h6")
	public WebElement heading;

	@FindBy(xpath = "//*[@id=\"DescPop\"]/div[2]/form/div[2]/div/div[1]/a")
	public WebElement cookiePolicyBtn;

	@FindBy(xpath="//*[@id=\"main\"]/h1")
	public WebElement cookiePolicy;
	
	@FindBy(xpath="//*[@id=\"main\"]/div/p[10]")
	public WebElement cookies;
	
	@FindBy(xpath="//*[@id=\"main\"]/div/p[11]")
	public WebElement cookieStorageType;
	
	public boolean devOpsPlatformLogin(String username,String password) throws InterruptedException {
		commonMethods.waitForPageToLoad();
		SoftAssert softassert = new SoftAssert();
		try {

			wait.until(ExpectedConditions.visibilityOf(platformUsername));
			logger.log(LogStatus.INFO, "Enter Username");
			System.out.println("Enter Username");
			platformUsername.click();
			platformUsername.sendKeys(username);
			commonMethods.waitForPageToLoad();

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

			if(heading.getText().contains("Cookies on this Site")) {
				System.out.println("About Privacy and Cookies on this Site is present");
				cookiePolicyBtn.click();
				commonMethods.waitForPageToLoad();
				logger.log(LogStatus.INFO, "Cookie policy is present");
				ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
				driver.switchTo().window(tabs.get(1));
				wait.until(ExpectedConditions.visibilityOf(cookiePolicy));
				logger.log(LogStatus.INFO, "Cookies information is provided");
				
				softassert.assertEquals(cookieStorageType.getText().contains("session cookies"), "myWizard® Devops Platform uses session cookies.");
				logger.log(LogStatus.PASS, "Session Cookie is used as a Storage type");
				System.out.println("Session Cookie is used as a Storage type");
				System.out.println(cookies.getText());
				return true;
			}
			else {
				logger.log(LogStatus.FAIL, "Please provide Privacy and Cookies policy");
				System.out.println("Please provide Privacy and Cookies policy");
				return false;
			}
		}
		catch(Exception e) {
			System.out.println(e);
			logger.log(LogStatus.FAIL, "Provide valid user account details for login");
			System.out.println("Provide valid user account details for login");
			return false;
		}
	}
}
