package compliance.PageObjMethods;

import java.io.FileWriter;
import java.util.ArrayList;

import org.apache.commons.collections.bag.SynchronizedSortedBag;
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
import Generic.TestBase.ExcelOp;
import Generic.TestBase.testBase;

public class VerifyPassword_Encryption extends testBase{
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
    
	@FindBy(xpath = "//*[text()='DevOps Hub']")
	public WebElement devopshubtab;

	@FindBy(xpath = "//*[text()='Platform Tools']")
	public WebElement platformtools;

	@FindBy(xpath = "//a[text()='LDAP UI']")
	public WebElement ldapui;

	@FindBy(xpath = "//a[text()='login']")
	public WebElement loginbutton;

	@FindBy(xpath = "//input[@id='login']")
	public WebElement loginDN;

	@FindBy(xpath = "//input[@id='password']")
	public WebElement password2;

	@FindBy(xpath = "//input[@value='Authenticate']")
	public WebElement authenticate;

	@FindBy(xpath = "//*[.='Successfully logged into server.']")
	public WebElement validatinglogin;

	@FindBy(xpath = "//img[@class='imgs']")
	public WebElement expand1icon;

	@FindBy(xpath = "(//img[@class='imgs'])[13]")
	public WebElement expand2icon;

	//	@FindBy(xpath = "//a[text()='Testuser1']")
	//	public WebElement ldapuser;

	@FindBy(xpath = "//input[@type='password']")
	public WebElement passwordcheck;
	
	public void devOpsPlatformLogin(String username,String password) throws InterruptedException {
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
			commonMethods.waitForPageToLoad();
			click_No.click();
			accept.click();

			logger.log(LogStatus.PASS, "Login Successful");
			System.out.println("Login Successful");
	
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Platform is down");
			System.out.println(e);
			System.out.println("Platform is down");
			Assert.fail("Platform is down");
		}
	}
	
//    public void addingLdapUser(String uname, String mail, String passwd, String confirmpass) throws InterruptedException {
//
//        commonMethods.waitForPageToLoad();
//        try {
//            logger.log(LogStatus.INFO, "Click on Manage Platform tab");
//            System.out.println("Click on Manage Platform tab");
//            managetab.click();
//            commonMethods.waitForPageToLoad();
//
//            logger.log(LogStatus.INFO, "Click on manage LDAP User");
//            System.out.println("Click on manage LDAP User");
//            manageLdapUser.click();
//            commonMethods.waitForPageToLoad();
//
//            logger.log(LogStatus.INFO, "Click on add Ldap User");
//            System.out.println("Click on add Ldap User");
//            addLdapuser.click();
//            commonMethods.waitForPageToLoad();
//            
//            logger.log(LogStatus.INFO, "Entering the name of Ldap user");
//            System.out.println("Entering the name of Ldap user");
//            Name.sendKeys(uname);
//            commonMethods.waitForPageToLoad();
//
//            logger.log(LogStatus.INFO, "Entering the email of Ldap user");
//            System.out.println("Entering the email of Ldap user");
//            Email.sendKeys(mail);
//            commonMethods.waitForPageToLoad();
//            
//            password.sendKeys(passwd);
//            logger.log(LogStatus.INFO, "Please enter correct combination of password");
//            System.out.println("Entering the password");
//            commonMethods.waitForPageToLoad();
//
//            confirmpassword.sendKeys(confirmpass);
//            logger.log(LogStatus.INFO, "Entering the password for confirmation");
//            System.out.println("Entering the password for confirmation");
//            commonMethods.waitForPageToLoad();
//
//            logger.log(LogStatus.INFO, "Clicking on Add User");
//            System.out.println("Clicking on Add User");
//            addUser.click();
//            commonMethods.waitForPageToLoad();
//            String msg;
//            try {
//                msg = confirmMsg.getText();
//                System.out.println("User is able to add Ldap user");
//            }
//            catch (Exception e) {
//                msg="";
//            }
//            
//            String dupmsg;
//            try {
//                dupmsg = duplicateMsg.getText();
//                System.out.println("User already exist");
//            }
//            catch (Exception e) {
//                dupmsg="";
//            }
//
//            if (msg.contains("Added to Ldap!!!")) {
//                logger.log(LogStatus.PASS, "User is able to add Ldap user");
//                System.out.println("User added Successful");
//                OKbutton.click();
//            }
//            else if(dupmsg.contains("User Name is already added!!!"))
//            {
//                logger.log(LogStatus.PASS, "User already exist");
//                System.out.println("User Name is already added");
//                dupokbutton.click();
//            }
//
//        }
//
//        catch (Exception e) {
//            System.out.println(e);
//            logger.log(LogStatus.FAIL, "User is not able to add Ldap user");
//            System.out.println("User is not able to add Ldap user");
//            Assert.fail("User is not able to add Ldap user");
//        }
//    }
//    
    public void ldapuilogin(String username,String password, String ldaploginusername, String ldappassword) throws InterruptedException{

		WebDriverWait wait = new WebDriverWait(driver, 30);
		commonMethods.waitForPageToLoad();
		try {
			logger.log(LogStatus.INFO, "Click on Devops Hub tab");
			System.out.println("Click on Devops Hub tab");
			devopshubtab.click();
			commonMethods.waitForPageToLoad();
			
			logger.log(LogStatus.INFO, "Click on Platform tools tab");
			System.out.println("Click on Platform tools tab");
			platformtools.click();
			
			wait.until(ExpectedConditions.visibilityOf(ldapui));
			logger.log(LogStatus.INFO, "Click on LDAP_UI tool");
			System.out.println("Click on LDAP_UI tool");
			ldapui.click();
			System.out.println("Clicked on tool");
			commonMethods.waitForPageToLoad();
			Thread.sleep(1000);
			System.out.println(driver.getWindowHandles());
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(1));
			
			System.out.println("navigated");
//			String urlWithCred = "https://" + username + ":" + password + "@"
//					+ testBase.getAppURL().replace("https://","") ;
//			System.out.println(urlWithCred);
//			logger.log(LogStatus.INFO, "Opening in new tab");
//			System.out.println("Opening in new tab");
//			commonMethods.waitForPageToLoad();
//			driver.get(urlWithCred);
			commonMethods.waitForPageToLoad();
//			System.out.println("1");
			String url = driver.getCurrentUrl();
			System.out.println(url);
			String updatedurl = url.replace("https://", "");
			System.out.println(updatedurl);
			Thread.sleep(100);
			String newurl = "https://" + username + ":" + password + "@" + updatedurl;

			System.out.println(newurl);
			driver.get(newurl);
			wait.until(ExpectedConditions.visibilityOf(loginbutton));
			commonMethods.waitForPageToLoad();
			loginbutton.click();
			commonMethods.waitForPageToLoad();
			
			logger.log(LogStatus.INFO, "Entering the LoginDN");
			System.out.println("Entering the LoginDN");
			loginDN.sendKeys(ldaploginusername);
			logger.log(LogStatus.INFO, "Entering the password");
			System.out.println("Entering the password");
			password2.sendKeys(ldappassword);
			authenticate.click();
			commonMethods.waitForPageToLoad();
			
//			try {
//				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[.='Successfully logged into server.']")));
//				validatinglogin.isDisplayed();
//
//			} catch (Exception e) {
//				driver.navigate().refresh();
//				commonMethods.waitForPageToLoad();
//				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[.='Successfully logged into server.']")));
//				validatinglogin.isDisplayed();
//				password_check(uname,passwd);
//			}
		}

		catch (Exception e) {
			System.out.println("LDAPUI tool is not available");
			logger.log(LogStatus.FAIL, "LDAPUI tool is not available");
			Assert.fail("LDAPUI tool is not available");
		}

	}
	public void password_check(String username) throws InterruptedException{
		
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[.='Successfully logged into server.']")));
			validatinglogin.isDisplayed();
			commonMethods.waitForPageToLoad();
			logger.log(LogStatus.INFO, "Click on dc=ldap to expand");
			System.out.println("Click on dc=ldap to expand");
			expand1icon.click();
			commonMethods.waitForPageToLoad();
			
			logger.log(LogStatus.INFO, "Click on ou=people to expand");
			System.out.println("Click on ou=people to expand");
			expand2icon.click();
			commonMethods.waitForPageToLoad();
//			int rowCount = ExcelOp.getRowCount("addldapuser");
//			System.out.println(rowCount);
//			for (int row = 1; row <= rowCount; row++) {
		
//				String username=ExcelOp.ReadExcelData("addldapuser", row, "Username");
//				String ldap_pass=ExcelOp.ReadExcelData("addldapuser", row, "Password");
				logger.log(LogStatus.INFO, "Click on ldapuser "+username);
				System.out.println("Click on ldapuser "+username);
				
				driver.findElement(By.xpath("//a[text()='"+username+"']")).click();
			
				commonMethods.waitForPageToLoad();
				passwordcheck.click();
				String passwordExtract=passwordcheck.getAttribute("value");
				System.out.println(passwordExtract);
//				System.out.println("Password length "+password.length());

				if(passwordExtract.contains("SHA"))
				{
					logger.log(LogStatus.PASS, "Password is encrypted for "+username);
					System.out.println("Password is encrypted for "+username);
				}
				else
				{
					logger.log(LogStatus.FAIL, "Password is not encrypted for "+username);
					System.out.println("Password is not encrypted for"+username);
					Assert.fail("Password is not encrypted");
				}
				commonMethods.waitForPageToLoad();
			}
//		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
    
}
