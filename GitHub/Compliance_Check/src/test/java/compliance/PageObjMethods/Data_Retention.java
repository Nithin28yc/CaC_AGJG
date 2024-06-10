package compliance.PageObjMethods;


import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;

import Generic.TestBase.BrowserConfig;
import Generic.TestBase.CommonMethods;
import Generic.TestBase.testBase;

public class Data_Retention extends testBase{

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

	@FindBy(xpath="//*[@id=\"DescPop\"]/div[2]/form/div[3]/button")
	public WebElement accept;

	@FindBy(xpath = "//*[@id=\"DescPop\"]/div[2]/form/div[1]")
	public WebElement heading;

	@FindBy(xpath = "//*[@id=\"DescPop\"]/div[2]/form/div[2]/p[1]")
	public WebElement message;

	@FindBy(xpath = "//*[text()='Platform Tools']")
	public WebElement platformtools;
	
	@FindBy(xpath = "//a[text()='Grafana']")
	public WebElement grafana;
	
	@FindBy(xpath = "//*[@id=\"panel-1\"]/div/div[1]/div/div[2]/div/h1")
	public WebElement grafanaMsg; 
	
	@FindBy(xpath = "/html/body/grafana-app/sidemenu/div[2]/div[3]/a/span/div")
	public WebElement sidemenu;
	
	public void ngnixLogin(String Username, String Password) {
		try {
			// String url = driver.getCurrentUrl();
			// System.out.println("url : " +url);
			String urlWithCred = "https://" + username + ":" + password + "@"
					+ testBase.getAppURL().replace("https://", "") + "/jenkinscore/";
			System.out.println(urlWithCred);
			driver.get(urlWithCred);
			commonMethods.waitForPageToLoad();
			logger.log(LogStatus.INFO, "Ngnix login Successful");
			System.out.println("Ngnix login Successful");
			driver.get(testBase.getAppURL());
			commonMethods.waitForPageToLoad();
		} catch (Exception e) {
			logger.log(LogStatus.INFO, "Ngnix login failed");
			System.out.println("Ngnix login failed");
			System.out.println(e);
		}
	}

	String errorMessage;
	public boolean devOpsPlatformLogin(String username,String password) throws InterruptedException {
		commonMethods.waitForPageToLoad();

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
			logger.log(LogStatus.INFO, "Click on login button");
			System.out.println("Click on login button");
			loginButton.click();
			commonMethods.waitForPageToLoad();
			click_No.click();
			commonMethods.waitForPageToLoad();

			accept.click();
			commonMethods.waitForPageToLoad();				
			wait.until(ExpectedConditions.visibilityOf(platformtools));
			logger.log(LogStatus.INFO, "Clicking on platformtools tab");
			System.out.println("Clicking on platformtools tab");
			platformtools.click();
			
			wait.until(ExpectedConditions.visibilityOf(grafana));
			logger.log(LogStatus.INFO, "Clicking on grafana");
			System.out.println("Clicking on grafana");
			grafana.click();

			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(1));

			wait.until(ExpectedConditions.visibilityOf(grafanaMsg));
			logger.log(LogStatus.INFO, "Opening grafana tool");
			System.out.println("Opening grafana tool");
			
			commonMethods.waitForPageToLoad();	
			logger.log(LogStatus.INFO, "Clicking on menu");
			System.out.println("Clicking on menu");
			sidemenu.click();

			commonMethods.waitForPageToLoad();	
			logger.log(LogStatus.INFO, "Clicking on file");
			System.out.println("Clicking on file");


			logger.log(LogStatus.PASS, "Data Retention is there");
			System.out.println("Data Retention is there");
			
			return true;

		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Data Retention is not there");
			System.out.println(e);
			System.out.println("Data Retention is not there");
			Assert.fail("Data Retention is not there");
			return false;
		}
	}

}
