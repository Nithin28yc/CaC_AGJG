package compliance.PageObjMethods;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import Generic.TestBase.BrowserConfig;
import Generic.TestBase.CommonMethods;
import Generic.TestBase.testBase;

public class Cross_Site extends testBase{
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
  
    @FindBy(xpath = "//div[@id='main']/h5")
    public WebElement ErrMsg;
	
	
	public void ngnixLogin(String Username, String Password) {
		try {
			// String url = driver.getCurrentUrl();
			// System.out.println("url : " +url);
			String urlWithCred = "https://" + username + ":" + password + "@"
					+ testBase.getAppURL().replace("https://", "") +"/jenkinscore/";
			System.out.println(urlWithCred);
			driver.get(urlWithCred);
			driver.findElement(By.xpath("//button[@id='details-button']")).click();
			driver.findElement(By.xpath("//a[@id='proceed-link']")).click();
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
	
	public boolean VerifyCrossSite(String Username, String Password) throws InterruptedException{
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
		
		String url = driver.getCurrentUrl();
		String updatedurl = url + "#"
				+ "<SCRIPT>alert(1)</SCRIPT> “><script >alert(document.cookie)</script> '';!--\"<XSS>=&{()} <SCRIPT>alert('XSS')</SCRIPT> <SCRIPT>alert(String.fromCharCode(88,83,83))</SCRIPT> “><s”%2b”cript>alert(document.cookie)</script> “><ScRiPt>alert(document.cookie)</script> “><<script>alert(document.cookie);//<</script> foo%00<script>alert(document.cookie)</script> <scr<script>ipt>alert(document.cookie)</scr</script>ipt> </script><script >alert(document.cookie)</script> <img src=asdf onerror=alert(document.cookie)> <iframe src=http://ha.ckers.org/scriptlet.html < <INPUT TYPE=\"IMAGE\" SRC=\"javascript:alert('XSS');\"> </TITLE><SCRIPT>alert(\"XSS\");</SCRIPT> ></SCRIPT>\">'><SCRIPT>alert(String.fromCharCode(88,83,83))</SCRIPT> ";
		driver.get(updatedurl);
		Thread.sleep(1000);
		logger.log(LogStatus.INFO, "Launching the current Link with updated URL with crossite script");
		System.out.println("Launching the current Link with updated URL with crossite script");

		driver.navigate().refresh();
		logger.log(LogStatus.INFO, "Verify Alert Displayed");
		System.out.println("Verify Alert Displayed");

		try {
			driver.switchTo().alert();
			logger.log(LogStatus.FAIL, "Alert is Displayed");
			System.out.println("Alert is Displayed");
			return false;
		}catch(NoAlertPresentException e)
		{logger.log(LogStatus.PASS, "Alert Not Displayed");
			System.out.println("Alert Not Displayed");
			return true;
		}
	}
}
