package compliance.PageObjMethods;


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

public class Cookie_Acceptance extends testBase{

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

	@FindBy(xpath = "//*[@id=\"DescPop\"]/div[2]/form/div[2]/h6")
	public WebElement cookiePolicy;

	@FindBy(xpath = "//*[@id=\"DescPop\"]/div[2]/form/div[2]/p[2]/strong")
	public WebElement message;

	String errorMessage;


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

			Assert.assertEquals(cookiePolicy.getText(), "About Privacy and Cookies on this Site");

			if(cookiePolicy.getText().contains("Cookies on this Site")) {
				if(message.getText().equals("Accept")) {

					accept.click();
					commonMethods.waitForPageToLoad();
					logger.log(LogStatus.PASS, "Cookie Acceptance is present");
					System.out.println("Cookie Acceptance is present");
					return true;
				}
				else {
					return false;
				}
			}
			else {
				logger.log(LogStatus.FAIL, "Cookie Acceptance is not present");
				System.out.println("Cookie Acceptance is not present");
				return false;
			}
			
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Platform is down");
			System.out.println(e);
			System.out.println("Platform is down");
			Assert.fail("Platform is down");
			return false;
		}
	}

}
