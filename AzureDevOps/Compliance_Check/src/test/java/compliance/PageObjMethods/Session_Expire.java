package compliance.PageObjMethods;

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

public class Session_Expire extends testBase{
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

	@FindBy(xpath="//*[@id=\"expire\"]/div[2]/div/div/div/h5")
	public WebElement sessionExpireMsg;

	@FindBy(xpath="//span[text()='Login']")
	public WebElement sessionExpireLoginBtn;

	public boolean sessionTimeout(String username, String password) throws InterruptedException{
		commonMethods.waitForPageToLoad();
		SoftAssert softassert = new SoftAssert();

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

			logger.log(LogStatus.INFO, "Confedential Information - Reminder poup is coming");
			System.out.println("Confedential Information - Reminder poup is coming");
			accept.click();
			commonMethods.waitForPageToLoad();
			
			logger.log(LogStatus.INFO, "Token is expirig after certain period of time");
			System.out.println("Token is expirig after certain period of time");
			softassert.assertEquals(sessionExpireMsg.getText(), "Your session has been expired. Please Login again. ");

			logger.log(LogStatus.PASS, "Your session has been expired. Please Login again.");
			System.out.println("Your session has been expired. Please Login again.");
			return true;

		}
		catch(Exception e) {
			System.out.println(e);
			logger.log(LogStatus.FAIL, "Session is not expiring");
			System.out.println("Session is not expiring");
			return false;
		}

		
	}
}
