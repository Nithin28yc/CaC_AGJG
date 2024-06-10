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

public class RBAC extends testBase{
	private WebDriver driver = BrowserConfig.getDriver();
	WebDriverWait wait = new WebDriverWait(driver, 45);
	JavascriptExecutor jse = (JavascriptExecutor) driver;
	CommonMethods commonMethods = PageFactory.initElements(driver, CommonMethods.class);

	@FindBy(xpath = "//input[@name='name']")
	public WebElement userName;

	@FindBy(xpath = "//input[@name='description']")
	public WebElement passWord;

	@FindBy(xpath = "//input[@value='Save Todo']")
	public WebElement loginButton;


	public boolean rolebase(String username,String password) throws InterruptedException {
		commonMethods.waitForPageToLoad();

		try {

			wait.until(ExpectedConditions.visibilityOf(userName));
			logger.log(LogStatus.INFO, "Enter Username");
			System.out.println("Enter Username");
			userName.click();
			userName.sendKeys(username);
			commonMethods.waitForPageToLoad();

			wait.until(ExpectedConditions.visibilityOf(passWord));
			logger.log(LogStatus.INFO, "Enter Password");
			System.out.println("Enter password");
			passWord.click();
			passWord.sendKeys(password);
			commonMethods.waitForPageToLoad();

			loginButton.click();
			commonMethods.waitForPageToLoad();


			try {	
				commonMethods.waitForPageToLoad();
				logger.log(LogStatus.PASS, "Logged in user is Admin-user");
				System.out.println("Logged in user is Admin-user");
				return true;

			}catch (Exception e) {
				System.out.println(e);
				logger.log(LogStatus.FAIL, "Logged in user is NonAdmin-user");
				System.out.println("Logged in user is NonAdmin-user");
				return false;

			}
		}
		catch(Exception e) {
			System.out.println(e);
			logger.log(LogStatus.FAIL, "Platform is down");
			System.out.println("Platform is down");
			return false;
		}
	}
}
