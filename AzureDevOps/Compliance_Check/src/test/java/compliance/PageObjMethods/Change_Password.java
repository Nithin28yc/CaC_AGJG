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

public class Change_Password extends testBase{

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
	
	@FindBy(xpath = "//*[@id=\"root\"]/div/div/header/div[2]/div/a/img")
	public WebElement profileBtn;
	
	@FindBy(xpath="//*[@id=\"drpDown\"]/div/div/p/a[2]")
	public WebElement changePassword;
	
	@FindBy(xpath="//*[@id=\"passwordModalnew\"]/div[2]/div/form/div[1]/p")
	public WebElement passwordWord;  
	
	@FindBy(xpath="//*[@id=\"passwordModalnew\"]/div[2]/div/form/div[2]/div[2]/input")
	public WebElement oldpassword;
	
	@FindBy(xpath="//*[@id=\"passwordModalnew\"]/div[2]/div/form/div[2]/div[3]/input")
	public WebElement newpassword;
	
	@FindBy(xpath="//*[@id=\"passwordModalnew\"]/div[2]/div/form/div[2]/div[4]/input")
	public WebElement confirmpassword;
	
	@FindBy(xpath="//*[@id=\"passwordModalnew\"]/div[2]/div/form/div[3]/button[2]")
	public WebElement changepasswordBtn;
	
	@FindBy(xpath="//*[@id=\"ConfirmadminModifyPass\"]/div[2]/div/div/div/h5")
	public WebElement changepasswordMsg;
	
	@FindBy(xpath="//*[@id=\"ConfirmadminModifyPass\"]/div[2]/div/div/div/a/span")
	public WebElement okBtn;
	
	@FindBy(xpath="//*[@id=\"IncorrectPass\"]/div[2]/div/div/div/h5")
	public WebElement incorrectPasswordMsg;
	
	@FindBy(xpath="//*[@id=\"IncorrectPass\"]/div[2]/div/div/div/button")
	public WebElement incorrectPasswordOkBtn;
	
	@FindBy(xpath="//*[@id=\"SimilarPass\"]/div[2]/div/div/div/h5")
	public WebElement samePasswordErrMsg;
	
	@FindBy(xpath="//*[@id=\"SimilarPass\"]/div[2]/div/div/div/button")
	public WebElement samePasswordErrMsgOkBtn;
	
	public boolean changePassword(String username, String password, String newPassword) throws InterruptedException {
		commonMethods.waitForPageToLoad();
		SoftAssert softassert = new SoftAssert();
		
		wait.until(ExpectedConditions.visibilityOf(platformUsername));
		logger.log(LogStatus.INFO, "Enter Platform Username");
		System.out.println("Enter Platform Username");
		platformUsername.click();
		platformUsername.sendKeys(username);
		commonMethods.waitForPageToLoad();

		wait.until(ExpectedConditions.visibilityOf(platformPassword));
		logger.log(LogStatus.INFO, "Enter Platform Password");
		System.out.println("Enter Platform password");
		platformPassword.click();
		platformPassword.sendKeys(password);
		commonMethods.waitForPageToLoad();
		loginButton.click();
		commonMethods.waitForPageToLoad();
		click_No.click();
		commonMethods.waitForPageToLoad();
		accept.click();
		commonMethods.waitForPageToLoad();
		
		logger.log(LogStatus.INFO, "Clicking on profile icon to change password");
		System.out.println("Clicking on profile icon to change password");
		profileBtn.click();
		commonMethods.waitForPageToLoad();
		
		logger.log(LogStatus.INFO, "Clicking on change password");
		System.out.println("Clicking on change password");
		changePassword.click();
		commonMethods.waitForPageToLoad();

		try {
			Assert.assertEquals(passwordWord.getText(), "Change Password");
			logger.log(LogStatus.INFO, "Entering the old password");
			System.out.println("Entering the old password");
			oldpassword.sendKeys(password);
			commonMethods.waitForPageToLoad();
			logger.log(LogStatus.INFO, "Entering the new password");
			System.out.println("Entering the new password");
			newpassword.sendKeys(newPassword);
			commonMethods.waitForPageToLoad();
			logger.log(LogStatus.INFO, "Entering the password for confirmation");
			System.out.println("Entering the password for confirmation");
			confirmpassword.sendKeys(newPassword);
			commonMethods.waitForPageToLoad();
			changepasswordBtn.click();
			commonMethods.waitForPageToLoad();
			
			softassert.assertEquals(changepasswordMsg.getText(), "Password has been changed successfully, you will be logged out and need to login again!!!");
			if(changepasswordMsg.getText().contains("Password has been changed successfully")) {
				logger.log(LogStatus.PASS, "Password is changed successfully");
				System.out.println("Password is changed successfully");		
				okBtn.click();
				return true;
			}
			else if(incorrectPasswordMsg.getText().contains("Incorret Details")) {
				logger.log(LogStatus.FAIL, "Please enter correct password");
				System.out.println("Please enter correct password");
				incorrectPasswordOkBtn.click();
				return false;
			}
			else if(samePasswordErrMsg.getText().contains("New Password must not match Old Password!!!")) {
				logger.log(LogStatus.FAIL, "New Password must not match Old Password");
				System.out.println("New Password must not match Old Password");
				samePasswordErrMsgOkBtn.click();
				return false;
			}
			else {
				return false;
			}
		}
		catch(Exception e) {
			logger.log(LogStatus.FAIL, "Password did not changed");
			System.out.println("Password did not changed");
			return false;
		}
	}
}
