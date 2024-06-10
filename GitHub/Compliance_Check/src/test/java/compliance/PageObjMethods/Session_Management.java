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

public class Session_Management extends testBase{
	private WebDriver driver = BrowserConfig.getDriver();
	WebDriverWait wait = new WebDriverWait(driver, 45);
	JavascriptExecutor jse = (JavascriptExecutor) driver;
	CommonMethods commonMethods = PageFactory.initElements(driver, CommonMethods.class);

	@FindBy(xpath = "//input[@name='name']")
	public WebElement Username;

	@FindBy(xpath = "//input[@name='description']")
	public WebElement Password;

	@FindBy(xpath = "//input[@value='Save Todo']")
	public WebElement loginButton;

	
	public boolean sessionManage(String username, String password) throws InterruptedException {
		commonMethods.waitForPageToLoad();
		SoftAssert softassert = new SoftAssert();
		try {
			wait.until(ExpectedConditions.visibilityOf(Username));
			logger.log(LogStatus.INFO, "Enter Username");
			System.out.println("Enter Username");
			Username.click();
			Username.sendKeys(username);

			wait.until(ExpectedConditions.visibilityOf(Password));
			logger.log(LogStatus.INFO, "Enter Password");
			System.out.println("Enter password");
			Password.click();
			Password.sendKeys(password);

			logger.log(LogStatus.INFO, "Clicking on Sign in button");
			System.out.println("Clicking on Sign in button");
			loginButton.click();
			commonMethods.waitForPageToLoad();
			
			logger.log(LogStatus.INFO, "Clicking on Sign out button");
			System.out.println("Clicking on Sign Out button");
			commonMethods.waitForPageToLoad();

			logger.log(LogStatus.INFO, "Clicking on browser back button");
			System.out.println("Clicking on browser back button");
//			driver.navigate().back();
			commonMethods.waitForPageToLoad();
			
			String tryLoginMsg = "Please try again";
			
			if(tryLoginMsg.equals("Please try logging in again.")) {
		//		Assert.assertEquals(tryLoginMsg, "We're sorry, the page you requested could not be found in our server. Please try logging in again.");
				//			loginButton.click();
		//		logger.log(LogStatus.INFO, tryLoginMsg );
				logger.log(LogStatus.PASS, "Application Passed the Unsecure Session Management Vulnerability");
				System.out.println("Application Passed the Unsecure Session Management Vulnerability");
				return true;
			}
			else {
				logger.log(LogStatus.FAIL, "Application Failed the Unsecure Session Management Vulnerability");
				System.out.println("Application Failed the Unsecure Session Management Vulnerability");
				return false;
			}
		}
		catch(Exception e) {
			logger.log(LogStatus.FAIL, "Application Failed the Unsecure Session Management Vulnerability");
			System.out.println("Application Failed the Unsecure Session Management Vulnerability");
			return false;
		}
	}
}
