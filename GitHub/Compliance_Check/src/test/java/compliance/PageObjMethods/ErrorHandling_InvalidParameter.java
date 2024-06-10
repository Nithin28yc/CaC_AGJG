package compliance.PageObjMethods;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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

public class ErrorHandling_InvalidParameter extends testBase{

	private WebDriver driver = BrowserConfig.getDriver();
	WebDriverWait wait = new WebDriverWait(driver, 45);
	JavascriptExecutor jse = (JavascriptExecutor) driver;
	CommonMethods commonMethods = PageFactory.initElements(driver, CommonMethods.class);


	
	@FindBy(xpath = "//input[@name='name']")
	public WebElement platformUsername;
	
	@FindBy(xpath = "//input[@name='description']")
	public WebElement platformPassword;
	
	@FindBy(xpath = "//input[@value='Save Todo']")
	public WebElement loginButton;
	
	@FindBy(xpath = "//h1[text()='Not Found']")
	public WebElement errormessage;

	


	public boolean devOpsPlatformLogin(String username,String password) throws InterruptedException, IOException {
		commonMethods.waitForPageToLoad();
		SoftAssert softassert = new SoftAssert();
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
			
			
			String url = driver.getCurrentUrl();
			String newUrl = url.replace(url,url+"/"+"dscrl");
			driver.navigate().to(newUrl);
			commonMethods.waitForPageToLoad();
			
			
			if(errormessage.getText().contains("Not Found")) {
				logger.log(LogStatus.PASS, "Error handling is there for invalid parameter");
				System.out.println("Error handling is there for invalid parameter");
				return true;
			}
			else {
				logger.log(LogStatus.FAIL, "Error handling is not there for invalid parameter check");
				System.out.println("Error handling is not there for invalid parameter check");
				return false;
			}

		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Please provide proper creds");
			System.out.println(e);
			System.out.println("Please provide proper creds");
			Assert.fail("Platform is down");
			return false;
		}
	}


}
