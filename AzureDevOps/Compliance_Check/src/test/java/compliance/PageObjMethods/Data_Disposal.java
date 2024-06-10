package compliance.PageObjMethods;


import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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

public class Data_Disposal extends testBase{

	private WebDriver driver = BrowserConfig.getDriver();
	WebDriverWait wait = new WebDriverWait(driver, 45);
	WebDriverWait wait1 = new WebDriverWait(driver, 100);
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
	
	@FindBy(xpath="//button[text()='I Understand']")
	public WebElement iun;

	@FindBy(xpath="//*[@id=\"DescPop\"]/div[2]/form/div[3]/button")
	public WebElement accept;
//
//	@FindBy(xpath = "//*[@id=\"DescPop\"]/div[2]/form/div[1]")
//	public WebElement heading;
//
	@FindBy(xpath = "//div[text()='DevOps Tools']")
	public WebElement devopsTools;

	@FindBy(xpath = "(//a//span[text()='Sign in'])[1]")
	public WebElement nexus_signIN;

	@FindBy(xpath = "//div[@role='dialog']//input[@name='username']")
	public WebElement nexus_username;
	
	@FindBy(xpath = "//div[@role='dialog']//input[@name='password']")
	public WebElement nexus_password;
	
	@FindBy(xpath = "(//a//span[text()='Sign in'])[2]")
	public WebElement nexus_login;

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
	public boolean devOpsPlatformLogin(String username,String password,String uname,String passwd,String reponame,String file) throws InterruptedException {
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
//			click_No.click();
//			commonMethods.waitForPageToLoad();
//			iun.click();
//			accept.click();
//			commonMethods.waitForPageToLoad();
	//		System.out.println(driver.getCurrentUrl());
	//		Thread.sleep(50000);
			
//			driver.navigate().refresh();
			wait1.until(ExpectedConditions.visibilityOf(devopsTools));
			
//			commonMethods.VerifyElementDisplayed("div", "DevOps Tools");
			
//			commonMethods.ClickByTagWithText("a", "Nexus");
			WebElement nexus = driver.findElement(By.xpath("//a[text()='Nexus']"));
			wait.until(ExpectedConditions.visibilityOf(nexus));
			jse.executeScript("arguments[0].click();", nexus);
			logger.log(LogStatus.INFO, "Clicking on nexus");
			System.out.println("Clicking on nexus");

			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(1));
//
//			String url = driver.getCurrentUrl();
//			System.out.println(url);
//			String updatedurl = url.replace("https://", "");
//			String newurl = "https://" + username + ":" + password + "@" + updatedurl;
//
//			//			System.out.println(newurl);
//			driver.get(newurl);
			
//			driver.navigate().refresh();
			commonMethods.waitForPageToLoad();
//			WebElement nexus_signIN = driver.findElement(By.xpath("(//a//span[text()='Sign in'])[1]"));
//			wait.until(ExpectedConditions.visibilityOf(nexus_signIN));
 //           jse.executeScript("arguments[0].click();", nexus_signIN);
			nexus_signIN.click();
			logger.log(LogStatus.INFO, "Clicking on Sign IN");
			System.out.println("Clicking on Sign In");
			 
			commonMethods.waitForPageToLoad();
			nexus_username.sendKeys(uname);
			logger.log(LogStatus.INFO, "Passing nexus username");
			System.out.println("Passing nexus username");
			
			commonMethods.waitForPageToLoad();
			nexus_password.sendKeys(passwd);
            nexus_password.sendKeys(Keys.ENTER);
			logger.log(LogStatus.INFO, "Passing nexus password");
			System.out.println("Passing nexus password");
			
			commonMethods.waitForPageToLoad();
			nexus_login.click();
			logger.log(LogStatus.INFO, "Clicking on login");
			System.out.println("Clicking on login");
			
			commonMethods.ClickByTagWithText("span", "Browse");
			logger.log(LogStatus.INFO, "Clicking on Browse");
			System.out.println("Clicking on Browse");
			
			commonMethods.ClickByTagWithText("div", "nithin" );
			logger.log(LogStatus.INFO, "Clicking on repository");
			System.out.println("Clicking on repository");

			commonMethods.ClickByTagWithText("span", "azure_foundation_template-v2.xlsx");
			logger.log(LogStatus.INFO, "Clicking on file to delete");
			System.out.println("Clicking on file to delete");

			commonMethods.ClickByTagWithText("span", "Delete asset");
			logger.log(LogStatus.INFO, "Clicking on delete button");
			System.out.println("Clicking on delete button");

			commonMethods.ClickByTagWithText("span", "Yes");	
			logger.log(LogStatus.PASS, "File is deleted");
			System.out.println("File is deleted");
			return true;

		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "File is not deleted");
			System.out.println(e);
			System.out.println("File is not deleted");
			Assert.fail("File is not deleted");
			return false;
		}
	}

}
