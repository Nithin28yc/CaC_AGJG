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

public class Logging_Monitoring extends testBase{

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
	
	@FindBy(xpath = "//*[@id=\"main\"]/div[1]/div[1]/div[1]/div")
	public WebElement nodesUsage;

	@FindBy(xpath = "//*[@id=\"main\"]/div[1]/div[3]")
	public WebElement podsUsage;
	
	@FindBy(xpath = "//div[text()='Manage PVC']")
	public WebElement managepvcText;


	public Boolean monitor(String username,String password) throws InterruptedException {
		commonMethods.waitForPageToLoad();
		SoftAssert softassert1 = new SoftAssert(); 
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
			
			softassert1.assertEquals(heading.getText(), "Confidential Information – Reminder");
			logger.log(LogStatus.INFO, "Confidential popup is coming");
			System.out.println("Confidential popup is coming");
//			commonMethods.ClickByTagWithText("button", "I Understand");
			accept.click();
			commonMethods.waitForPageToLoad();
			
			commonMethods.ClickByTagWithText("a", "Platform Statistics");
			logger.log(LogStatus.INFO, "Clicking on Platform Statistics");
			System.out.println("Clicking on Platform Statistics");
			commonMethods.waitForPageToLoad();			
			try {
				if(nodesUsage.getText().contains("percentage of CPU and Memory")) {
					if(podsUsage.getText().contains("CPU, Memory, and Storage/Volume usage details")) {
						logger.log(LogStatus.PASS, "Monitoring is there for the platform");
						System.out.println("Monitoring is there for the platform");
						return true;
					}
					else {
						return false;
					}
					
				}
				else {
					return false;
				}

			}catch (Exception e) {
				System.out.println(e);
				logger.log(LogStatus.FAIL, "Monitoring is not there");
				System.out.println("Monitoring is not there");
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
