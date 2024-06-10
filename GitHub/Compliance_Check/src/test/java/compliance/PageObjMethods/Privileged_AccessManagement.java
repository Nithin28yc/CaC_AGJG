package compliance.PageObjMethods;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Generic.TestBase.BrowserConfig;
import Generic.TestBase.CommonMethods;
import Generic.TestBase.testBase;
import io.appium.java_client.functions.ExpectedCondition;

public class Privileged_AccessManagement extends testBase{
	private WebDriver driver = BrowserConfig.getDriver();
	WebDriverWait wait = new WebDriverWait(driver, 45);
	JavascriptExecutor jse = (JavascriptExecutor) driver;
	CommonMethods commonMethods = PageFactory.initElements(driver, CommonMethods.class);
	 ExtentSparkReporter sparkReport;
	    ExtentReports reports;
	    ExtentTest testLog;
	@FindBy(xpath = "//*[@id=\"i0116\"]")
	public WebElement enterpriseId;
	
	@FindBy(xpath = "//*[@id=\"idSIButton9\"]")
	public WebElement next;
	
	@FindBy(xpath = "//*[@id=\"i0118\"]")
	public WebElement enterprisePass;
	
	@FindBy(xpath = "//*[@id='signInAnotherWay']")
	public WebElement signOtherWay;
	
	@FindBy(xpath = "//*[@id=\"idDiv_SAOTCS_Proofs\"]/div[4]/div/div/div[2]/div")
	public WebElement call;
	
	@FindBy(xpath = "//*[@id='announcement-banner']/div/h2/font/b")
	public WebElement innerSource;
	
	@FindBy(xpath = "//*[@id=\"dashboard-container\"]/div[2]/div/div[1]/div/div/input")
	public WebElement search;
	
	@FindBy(xpath = "//*[@id=\"dashboard-container\"]/div[2]/div/div[1]/h3")
	public WebElement repoSearch;
	
	@FindBy(xpath = "//*[@id=\"aui-sidebar-content\"]/div/div[2]/button")
	public WebElement expand;
	
	@FindBy(xpath = "//*[@id='quick-search-repository-172926']/div/strong/mark")
	public WebElement ct_demodocrepo;
	
	@FindBy(xpath = "//*[@id='aui-sidebar-content']/div/div[1]/nav/div/div[6]/ul/li/a/span[1]")
	public WebElement setting;
	
	@FindBy(xpath = "//*[@id='aui-page-panel-content-body']/div/div/nav/div/ul[2]/li[2]/a")
	public WebElement branchPermision;
	
	@FindBy(xpath = "//*[@id='branch-permissions-table']/tbody/tr[2]/td[2]/ul/li/div/a")
	public WebElement masterName;
	
	@FindBy(xpath = "//*[@id=\"restriction-types-checkbox-field\"]/div[2]/label")
	public WebElement prevent;
	
	@FindBy(xpath = "//*[@id=\"s2id_autogen3\"]")
	public WebElement text;

	
	public boolean privilegedAccessManage(String enterpriseID, String enterprisePassword, String reponame) throws InterruptedException {
		commonMethods.waitForPageToLoad();
		try {
			wait.until(ExpectedConditions.visibilityOf(enterpriseId));
			logger.log(LogStatus.INFO, "Entering the Enterprise Id");
			System.out.println("Entering the Enterprise Id");
			enterpriseId.sendKeys(enterpriseID);
			commonMethods.waitForPageToLoad();
			
			next.click();
			commonMethods.waitForPageToLoad();
			wait.until(ExpectedConditions.visibilityOf(enterprisePass));
			logger.log(LogStatus.INFO, "Entering the Enterprise password");
			System.out.println("Entering the Enterprise password");
			enterprisePass.sendKeys(enterprisePassword);
			commonMethods.waitForPageToLoad();
			next.click();
			
			wait.until(ExpectedConditions.visibilityOf(signOtherWay));
			logger.log(LogStatus.INFO, "Clicking on SignIn another way");
			System.out.println("Clicking on SignIn another way");
			signOtherWay.click();
			commonMethods.waitForPageToLoad();
			
			logger.log(LogStatus.INFO, "Calling to the moblie to approve signIn");
			System.out.println("Calling to the moblie to approve signIn");
			call.click();
			commonMethods.waitForPageToLoad();
			
			commonMethods.setInputById("quick-search", reponame);

     		commonMethods.waitForPageToLoad();
			logger.log(LogStatus.INFO, "Searching for the required repository");
			System.out.println("Searching for the required repository");
			commonMethods.ClickByTagWithText("mark",reponame);
			logger.log(LogStatus.INFO, "Clicking on the required repository");
			System.out.println("Clicking on the required repository");
			
			commonMethods.waitForPageToLoad();
			expand.click();
			
			commonMethods.ClickByTagWithText("span", "Repository settings");
			logger.log(LogStatus.INFO, "Clicking on Repository settings");
			System.out.println("Clicking on Repository settings");

			commonMethods.ClickOnLinkContainingText("Branch permissions");
			logger.log(LogStatus.INFO, "Clicking on Branch Permission");
			System.out.println("Clicking on Branch Permission");
			commonMethods.waitForPageToLoad();

//			commonMethods.ClickOnButtonByText("Add permissions");
//			logger.log(LogStatus.INFO, "Clicking on Add Permission to the user");
//			System.out.println("Clicking on Add Permission to the user");
//			
//			commonMethods.ClickByTagWithText("span", "Select branch");
//			logger.log(LogStatus.INFO, "Selecting the branch");
//			System.out.println("Selecting the branch");
//			
//			commonMethods.ClickByTagWithText("span", "master");
//			
//			commonMethods.waitForPageToLoad();
//			prevent.click();
//			logger.log(LogStatus.INFO, "Giving privilege access to the user "+prevent.getText());
//			System.out.println("Giving privilege access to the user "+prevent.getText());
//			text.click();
			
			
			String[] arr = {"Iyer, Rajalakshmy K."};
			for (int i=0;i<arr.length;i++) {
			if(masterName.getText().contentEquals(arr[i])) {
				logger.log(LogStatus.PASS, "Privileged Access Management is given to only lead");
				System.out.println("Privileged Access Management is given to only lead");
				return true;
			}
			else {
				logger.log(LogStatus.PASS, "Privileged Access Management is not given");
				System.out.println("Privileged Access Management is not given");
				return true;
			}
			}
			return true;
		
		}
		catch(Exception e) {
			System.out.println(e);
			logger.log(LogStatus.FAIL, "Authentication failed");
			System.out.println("Authentication failed");
			return false;
		}
		
	}
	
}
