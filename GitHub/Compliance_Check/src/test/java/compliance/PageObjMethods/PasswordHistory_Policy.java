package compliance.PageObjMethods;

import java.util.List;

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

public class PasswordHistory_Policy extends testBase{

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

	@FindBy(xpath = "//a[@href='#/manage']")
	public WebElement managetab;

	@FindBy(xpath = "//a[@href='#/ldapusers']")
	public WebElement manageLdapUser;

	@FindBy(xpath = "//span[text()='Add LDAP User']")
	public WebElement addLdapuser;

	@FindBy(xpath = "//input[@name='userName']")
	public WebElement Name;

	@FindBy(xpath = "//input[@name='email']")
	public WebElement Email;

	@FindBy(xpath = "(//input[@name='password'])[2]")
	public WebElement password;

	@FindBy(id = "confirmpassword")
	public WebElement confirmpassword;

	@FindBy(xpath = "//button[text()='Add User']")
	public WebElement addUser;

	@FindBy(xpath = "(//div[@class='text-center']/h5)[4]")
	public WebElement confirmMsg;

	@FindBy(xpath = "(//button[text()='Ok'])[3]")
	public WebElement OKbutton;

	@FindBy(xpath = "(//div[@class='text-center']/h5)[8]")
	public WebElement duplicateMsg;

	@FindBy(xpath = "(//button[text()='Ok'])[7]")
	public WebElement dupokbutton;

	@FindBy(xpath = "//*[@id=\"ldapTable\"]/tbody/tr[1]/td[5]/button")
	public WebElement modifyBtn1;

	@FindBy(xpath = "//*[@id=\"SimilarPass\"]/div[2]/div/div/div/h5")
	public WebElement passwordPolicyMsg;

	@FindBy(xpath = "//*[@id=\"SimilarPass\"]/div[2]/div/div/div/button")
	public WebElement okBtn;

	@FindBy(xpath = "//*[@id=\"ConfirmModifyPass\"]/div[2]/div/div/div/h5")
	public WebElement passwordCrtMsg;

	@FindBy(xpath = "//*[@id=\"ConfirmModifyPass\"]/div[2]/div/div/div/button")
	public WebElement okBtnCrt;

	@FindBy(xpath = "//*[@id=\"IncorrectPass\"]/div[2]/div/div/div/h5")
	public WebElement incrtPass;

	@FindBy(xpath = "//*[@id=\"IncorrectPass\"]/div[2]/div/div/div/button")
	public WebElement incrtBtn;

	public void login(String username, String password) throws InterruptedException {
		commonMethods.waitForPageToLoad();

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

	}

	public boolean passwordChange(String ldapuname, String ldapPass, String newPassword) throws InterruptedException {
		commonMethods.waitForPageToLoad();
		SoftAssert softassert = new SoftAssert();

		logger.log(LogStatus.INFO, "Click on Manage Platform tab");
		System.out.println("Click on Manage Platform tab");
		managetab.click();
		commonMethods.waitForPageToLoad();

		logger.log(LogStatus.INFO, "Click on manage LDAP User");
		System.out.println("Click on manage LDAP User");
		manageLdapUser.click();
		commonMethods.waitForPageToLoad();

//		logger.log(LogStatus.INFO, "Click on add Ldap User");
//		System.out.println("Click on add Ldap User");
//		addLdapuser.click();
//		commonMethods.waitForPageToLoad();
//
//		logger.log(LogStatus.INFO, "Entering the name of Ldap user");
//		System.out.println("Entering the name of Ldap user");
//		wait.until(ExpectedConditions.visibilityOf(Name));
//		Name.sendKeys(ldapuname);
//		commonMethods.waitForPageToLoad();
//
//		logger.log(LogStatus.INFO, "Entering the email of Ldap user");
//		System.out.println("Entering the email of Ldap user");
//		Email.sendKeys(ldapEmail);
//		commonMethods.waitForPageToLoad();
//
//		logger.log(LogStatus.INFO, "Please enter correct combination of password");
//		System.out.println("Entering the password");
//		password.sendKeys(ldapPass);
//		commonMethods.waitForPageToLoad();
//
//		logger.log(LogStatus.INFO, "Entering the password for confirmation");
//		System.out.println("Entering the password for confirmation");
//		confirmpassword.sendKeys(ldapPass);
//		commonMethods.waitForPageToLoad();
//
//		logger.log(LogStatus.INFO, "Clicking on Add User");
//		System.out.println("Clicking on Add User");
//		addUser.click();
//		commonMethods.waitForPageToLoad();
//		String msg;
//		try {
//			msg = confirmMsg.getText();
//			System.out.println("User is able to add Ldap user");
//		}
//		catch (Exception e) {
//			msg="";
//		}
//
//		String dupmsg;
//		try {
//			dupmsg = duplicateMsg.getText();
//			System.out.println("User already exist");
//		}
//		catch (Exception e) {
//			dupmsg="";
//		}
//
//		if (msg.contains("Added to Ldap!!!")) {
//			logger.log(LogStatus.PASS, "User is able to add Ldap user");
//			System.out.println("User added Successful");
//			OKbutton.click();
//		}
//		else if(dupmsg.contains("User Name is already added!!!"))
//		{
//			logger.log(LogStatus.PASS, "User already exist");
//			System.out.println("User Name is already added");
//			dupokbutton.click();
//		}
//
		driver.navigate().refresh();

		try {
			wait.until(ExpectedConditions.visibilityOf(modifyBtn1));
			List<WebElement> rows = driver.findElements(By.xpath("//*[@id=\"ldapTable\"]/tbody/tr"));
			commonMethods.waitForPageToLoad();
			System.out.println("Number of rows:" + rows.size());
			commonMethods.waitForPageToLoad();

			for(int i=1; i<rows.size(); i++) {
				
				WebElement name = driver.findElement(By.xpath("//*[@id='ldapTable']/tbody/tr["+i+"]/td[2]"));
				String getName = name.getText();

				if(getName.equals(ldapuname)) {
					System.out.println(getName);
					logger.log(LogStatus.INFO, "Click on modify user details");
					System.out.println("Click on modify user details");
					WebElement modifyBtn = driver.findElement(By.xpath("//*[@id='ldapTable']/tbody/tr["+i+"]/td[5]/button"));
					modifyBtn.click();
					commonMethods.waitForPageToLoad();

					logger.log(LogStatus.INFO, "Click on Change Password");
					System.out.println("Click on Change Password");
					WebElement changePassword = driver.findElement(By.xpath("//*[@id='userModal"+i+"']/div[2]/div/div[2]/div/button[2]/span"));
					changePassword.click();
					commonMethods.waitForPageToLoad();
					
					logger.log(LogStatus.INFO, "Enter the Old Password");
					System.out.println("Enter the Old Password");
					WebElement oldPass = driver.findElement(By.xpath("//*[@id='passwordModal"+i+"']/div/div/form/div[2]/div[2]/input"));
					oldPass.sendKeys(ldapPass);
					commonMethods.waitForPageToLoad();

					logger.log(LogStatus.INFO, "Enter the New Password");
					System.out.println("Enter the New Password");
					WebElement newPass = driver.findElement(By.xpath("//*[@id='passwordModal"+i+"']/div/div/form/div[2]/div[3]/input"));
					newPass.sendKeys(newPassword);
					commonMethods.waitForPageToLoad();

					logger.log(LogStatus.INFO, "Enter the Password for confirmation");
					System.out.println("Enter the Password for confirmation");
					WebElement confirmPass = driver.findElement(By.xpath("//*[@id='passwordModal"+(i)+"']/div/div/form/div[2]/div[4]/input"));
					confirmPass.sendKeys(newPassword);
					commonMethods.waitForPageToLoad();

					WebElement changePassBtn = driver.findElement(By.xpath("//*[@id='passwordModal"+(i)+"']/div/div/form/div[3]/button[2]"));
					changePassBtn.click();
					commonMethods.waitForPageToLoad();

					softassert.assertEquals(passwordCrtMsg.getText(), "Password Changed!!!");
					softassert.assertEquals(passwordPolicyMsg.getText(), "New Password must not match Old Password!!!");
					softassert.assertEquals(incrtPass.getText(), "Incorrect Details!!!");

					if(passwordCrtMsg.getText().equals("Password Changed!!!")) {
						logger.log(LogStatus.PASS, "Password history policy is applied");
						System.out.println("Password history policy is applied");
						System.out.println("Password changed Successfully");
//						okBtnCrt.click();
						return true;
					}
					else if(passwordPolicyMsg.getText().equals("New Password must not match Old Password!!!")) {
						logger.log(LogStatus.FAIL, "New Password must not match Old Password!!!");
						System.out.println("New Password must not match Old Password!!!");
//						okBtn.click();
						return false;
					}
					else if(incrtPass.getText().equals("Incorrect Details!!!")) {
						System.out.println(incrtPass.getText());
						logger.log(LogStatus.FAIL, "Please enter correct old Password");
						System.out.println("Please enter correct old Password");
						return false;
//						incrtBtn.click();
					}
				
				break;
				}
		
			}
		}
		catch (Exception e) {
			System.out.println(e);

			logger.log(LogStatus.FAIL, "Not able to change password");
			System.out.println("Not able to change password");
			return false;
		}
		return false;
	}

}
