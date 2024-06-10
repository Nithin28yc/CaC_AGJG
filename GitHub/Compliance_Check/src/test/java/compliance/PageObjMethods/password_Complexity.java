package compliance.PageObjMethods;

import java.util.regex.*;

import org.openqa.selenium.By;
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

public class password_Complexity extends testBase{

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

	@FindBy(xpath = "//a[@href='#/manage']")
	public WebElement managetab;

	@FindBy(xpath = "//a[@href='#/ldapusers']")
	public WebElement manageLdapUser;

	@FindBy(xpath = "//span[text()='Add LDAP User']")
	public WebElement addLdapuser;

	@FindBy(xpath = "//*[@id=\"addLdapUserModal\"]/div[2]/div/form/div[2]/div[1]/input")
	public WebElement Name;

	@FindBy(xpath = "//*[@id=\"addLdapUserModal\"]/div[2]/div/form/div[2]/div[2]/input")
	public WebElement Email;

	@FindBy(xpath = "//*[@id=\"addLdapUserModal\"]/div[2]/div/form/div[2]/div[4]/input")
	public WebElement passwordValue;

	@FindBy(id = "confirmpassword")
	public WebElement confirmpassword;

	@FindBy(xpath = "//button[text()='Add User']")
	public WebElement addUser;

	@FindBy(xpath = "//*[@id=\"ConfirmAdd\"]/div[2]/div/div/div/h5")
	public WebElement confirmMsg;

	@FindBy(xpath = "//span[@class='errorMessage']")
	public WebElement errMsg;

	@FindBy(xpath = "//*[@id=\"ConfirmAdd\"]/div[2]/div/div/div/button")
	public WebElement ok;

	public boolean addingLdapUser(String username,String password,String ldapuname, String ldapEmail, String ldapPass) throws InterruptedException {
		SoftAssert softassert = new SoftAssert();
		commonMethods.waitForPageToLoad();
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

			softassert.assertEquals(heading.getText(), "Confidential Information – Reminder");
			accept.click();
			commonMethods.waitForPageToLoad();

			logger.log(LogStatus.INFO, "Click on Manage Platform tab");
			System.out.println("Click on Manage Platform tab");
			managetab.click();
			commonMethods.waitForPageToLoad();

			logger.log(LogStatus.INFO, "Click on manage LDAP User");
			System.out.println("Click on manage LDAP User");
			manageLdapUser.click();
			commonMethods.waitForPageToLoad();

			logger.log(LogStatus.INFO, "Click on add Ldap User");
			System.out.println("Click on add Ldap User");
			addLdapuser.click();
			commonMethods.waitForPageToLoad();

			logger.log(LogStatus.INFO, "Entering the name of Ldap user");
			System.out.println("Entering the name of Ldap user");
			wait.until(ExpectedConditions.visibilityOf(Name));
			Name.sendKeys(ldapuname);
			commonMethods.waitForPageToLoad();

			logger.log(LogStatus.INFO, "Entering the email of Ldap user");
			System.out.println("Entering the email of Ldap user");
			Email.sendKeys(ldapEmail);
			commonMethods.waitForPageToLoad();

			logger.log(LogStatus.INFO, "Entering the password");
			System.out.println("Entering the password");

			String regex = "^(?=.*[0-9])"
					+ "(?=.*[a-z])(?=.*[A-Z])"
					+ "(?=.*[!@#$%^&=+])"
					+ "(?=\\S+$).{8,20}$";

			Pattern pattern = Pattern.compile(regex);
			Matcher match = pattern.matcher(ldapPass);

			boolean crt = match.find();
			System.out.println(crt);
			if(crt == false){
				passwordValue.click();
				passwordValue.sendKeys(ldapPass);
				commonMethods.waitForPageToLoad();
				logger.log(LogStatus.FAIL, "Please enter correct combination of password");
				System.out.println(errMsg.getText());
				System.out.println("Please enter correct combination of password");
				return false;

			}
			else {
				passwordValue.click();
				passwordValue.sendKeys(ldapPass);
				commonMethods.waitForPageToLoad();
			
				logger.log(LogStatus.INFO, "Entering the password for confirmation");
				System.out.println("Entering the password for confirmation");
				confirmpassword.sendKeys(ldapPass);
				commonMethods.waitForPageToLoad();

				logger.log(LogStatus.INFO, "Clicking on Add User");
				System.out.println("Clicking on Add User");
				addUser.click();
				commonMethods.waitForPageToLoad();
				
				String confMsg = confirmMsg.getText();
				Assert.assertEquals("Added to Ldap!!!", confMsg);
				ok.click();
				logger.log(LogStatus.PASS, "User added with password complexity");
				return true;
			}
		}
		
		catch (Exception e) {
			System.out.println(e);
			logger.log(LogStatus.FAIL, "User is not able to add Ldap user page");
			System.out.println("User is not able to add Ldap user page");
			Assert.fail("User is not able to add Ldap user page");
			return false;
		}
	}

}