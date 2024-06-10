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

public class Notice_Privacy extends testBase{

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
	
	String ExpectedMsg = "You are using an Accenture proprietary tool (myWizard). You understand that this tool may contain ACCENTURE, CLIENT, OR VENDOR / PARTNER CONFIDENTIAL INFORMATION. You are obligated to adhere to applicable confidentiality obligations and Accenture’s policies, including Policy 69 – Confidentiality, when using the tool and information in the tool. Information in the tool can only be shared with those authorized to receive it. If you are downloading/exporting Confidential Information to another file or report, you must label that file or report as Accenture Confidential. If you have any questions, please email your question to confidentiality@accenture.com or contact your Leadership. Thank you!";

	//	public void ngnixLogin(String Username, String Password) {
	//		try {
	//			//String url = driver.getCurrentUrl();
	//			//System.out.println("url : " +url);
	//			String urlWithCred = "https://" + username + ":" + password + "@" + testBase.getAppURL().replace("https://", "") + "jenkinscore/";	
	//			System.out.println(urlWithCred);
	//			driver.get(urlWithCred);
	//			driver.findElement(By.xpath("//button[@id='details-button']")).click();
	//			driver.findElement(By.xpath("//a[@id='proceed-link']")).click();
	//			commonMethods.waitForPageToLoad();
	//			logger.log(LogStatus.INFO, "Ngnix login Successful");
	//			System.out.println("Ngnix login Successful");
	//			driver.get(testBase.getAppURL());
	//			commonMethods.waitForPageToLoad();
	//		}catch (Exception e) {
	//				logger.log(LogStatus.INFO, "Ngnix login failed");
	//				System.out.println("Ngnix login failed");
	//			}
	//	     }

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
		}
	}


	public boolean devOpsPlatformLogin(String username,String password) throws InterruptedException {
		commonMethods.waitForPageToLoad();
		String errorMessage;
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
			commonMethods.waitForPageToLoad();
			click_No.click();
			commonMethods.waitForPageToLoad();
			
			Assert.assertEquals(heading.getText(), "Confidential Information – Reminder");

			if(heading.getText().contains("Confidential Information")) {
				String msg = message.getText();

				logger.log(LogStatus.INFO, "Confedential Information - Reminder poup is coming and fetching message from it to a file");
				System.out.println("Confedential Information - Reminder poup is coming and fetching message from it to a file");

				FileWriter file = new FileWriter("./testfile/Info.txt");
				file.write(msg);
				file.close();
				logger.log(LogStatus.PASS, "File has been created");
				System.out.println("File has been created");

				if (ExpectedMsg.equals(msg)) {
					logger.log(LogStatus.PASS, "Expected message and Extracted message are same");
					System.out.println("Expected message and Extracted message are same");
					
//					accept.click();
					commonMethods.waitForPageToLoad();
					return true;
				}
				else {
					logger.log(LogStatus.FAIL, "Expected message and Extracted message are not same");
					System.out.println("Expected message and Extracted message are not same");
					return false;
				}}
			else {
				logger.log(LogStatus.FAIL, "Please provide Notice-Privacy pop up");
				System.out.println("Please provide Notice-Privacy pop up");
			}

			try {
				errorMessage = loginErrorMessage.getText();

				errorMessage.contains("Invalid Credentials! Please try Again");
				logger.log(LogStatus.FAIL, "Negative Login Verification Successful");
				System.out.println("Negative Login Verification Successful");
				return false;
			}
			catch (Exception e){
				logger.log(LogStatus.PASS, "Login Successful");
				System.out.println("Login Successful");
				return true;
			}
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Platform is down");
			System.out.println(e);
			System.out.println("Platform is down");
			Assert.fail("Platform is down");
			return false;
		}
	}
	public void devOpsPlatformLogOff() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='user']")));
			driver.findElement(By.xpath("//img[@alt='user']")).click();
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[.='Sign Out']")));
			logger.log(LogStatus.INFO, "Click on SignOut");
			System.out.println("Sign Out");
			logoutButton.click();
			commonMethods.waitForPageToLoad();
			logger.log(LogStatus.PASS, "SignOut successful");
			System.out.println("SignOut successful");
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Platform is down");
			System.out.println("Platform is down");
			Assert.fail("Platform is down");
		}
	}

}
