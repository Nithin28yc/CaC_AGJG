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

public class Authentication extends testBase{

	private WebDriver driver = BrowserConfig.getDriver();
	WebDriverWait wait = new WebDriverWait(driver, 45);
	JavascriptExecutor jse = (JavascriptExecutor) driver;
	CommonMethods commonMethods = PageFactory.initElements(driver, CommonMethods.class);

	@FindBy(xpath = "//input[@name='name']")
	public WebElement userName;

	@FindBy(xpath = "//input[@name='description']")
	public WebElement userPassword;

	@FindBy(xpath = "//input[@value='Save Todo']")
	public WebElement loginButton;

	

	public Boolean devOpsPlatformLogin(String username,String password) throws InterruptedException {
		commonMethods.waitForPageToLoad();

		try {

			wait.until(ExpectedConditions.visibilityOf(userName));
			logger.log(LogStatus.INFO, "Enter Username");			
			System.out.println("Enter Username");
			userName.click();
			userName.sendKeys(username);
			commonMethods.waitForPageToLoad();

			wait.until(ExpectedConditions.visibilityOf(userPassword));
			logger.log(LogStatus.INFO, "Enter Password");
			System.out.println("Enter password");
			userPassword.click();
			userPassword.sendKeys(password);
			commonMethods.waitForPageToLoad();
			
			logger.log(LogStatus.INFO, "Click on login button");
			System.out.println("Click on login button");
			loginButton.click();
			
			
			try {
				//accept.click();
				commonMethods.waitForPageToLoad();
				logger.log(LogStatus.PASS, "Login Successful");
				System.out.println("Login Successful");
				return true;
				
			}
			catch (Exception e){
				
			    String	errorMessage = "Invalid Credentials! Please try Again";

				errorMessage.contains("Invalid Credentials! Please try Again");
				logger.log(LogStatus.FAIL, "Invalid login");
				System.out.println("Invalid login");
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
