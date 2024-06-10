package compliance.PageObjMethods;

import java.io.FileWriter;

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

public class SSO extends testBase{
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

	@FindBy(xpath = "//*[@id=\"DescPop\"]/div[2]/form/div[1]")
	public WebElement heading;

	@FindBy(xpath = "//*[@id=\"DescPop\"]/div[2]/form/div[2]/p[1]")
	public WebElement message;

	@FindBy(xpath="//*[@id=\"DescPop\"]/div[2]/form/div[3]/button")
	public WebElement accept;

	@FindBy(xpath="//a[text()='Dashboard']")
	public WebElement dashboard;

	public boolean verify_SSO(String username,String password) throws InterruptedException {
		commonMethods.waitForPageToLoad();
		String errorMessage;
		SoftAssert softassert = new SoftAssert();
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

		Assert.assertEquals(heading.getText(), "Confidential Information – Reminder");
		accept.click();
		commonMethods.waitForPageToLoad();
		commonMethods.ClickOnLinkText("Jenkins BlueOcean");
		commonMethods.waitForPageToLoad();
		try {
			if(dashboard.getText().contains("board")) {
				logger.log(LogStatus.PASS, "Single sign on is implemented");
				System.out.println("Single sign on is implemented");
				return true;
			}
			else {
				logger.log(LogStatus.FAIL, "Single sign on is not implemented");
				System.out.println("Single sign on is not implemented");
				return false;
			}
		}
		catch(Exception e){
			logger.log(LogStatus.FAIL, "Single sign on is not implemented");
			System.out.println("Single sign on is not implemented");
			return false;
		}

	}

}
