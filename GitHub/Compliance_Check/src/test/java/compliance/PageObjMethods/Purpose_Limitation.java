package compliance.PageObjMethods;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.bouncycastle.asn1.x509.sigi.PersonalData;
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
import freemarker.log.Logger;

public class Purpose_Limitation extends testBase{

	private WebDriver driver = BrowserConfig.getDriver();
	WebDriverWait wait = new WebDriverWait(driver, 40);
	JavascriptExecutor jse = (JavascriptExecutor) driver;
	CommonMethods commonMethods = PageFactory.initElements(driver, CommonMethods.class);

	@FindBy(xpath = "//a[text()='Privacy Statement']")
	public WebElement privacyStatement;

	@FindBy(xpath = "//div[text()='Sign in']")
	public WebElement word;

	@FindBy(xpath = "//*[@id=\"i0116\"]")
	public WebElement enterpriseId;

	@FindBy(xpath = "//*[@id=\"idSIButton9\"]")
	public WebElement next;

	@FindBy(xpath = "//*[@id=\"i0118\"]")
	public WebElement enterprisePass;

	@FindBy(xpath = "//a[@id='signInAnotherWay']")
	public WebElement signOtherWay;

	@FindBy(xpath = "//*[@id=\"idDiv_SAOTCS_Proofs\"]/div[4]/div/div/div[2]/div")
	public WebElement call;

	@FindBy(xpath = "//*[@id=\"post-5422\"]/div/div[1]/div/div/div[2]/div/div[1]/div/div[1]/div/div/p[1]/span/span[2]/strong")
	public WebElement heading;

	@FindBy(xpath = "//*[@id='post-5422']/div/div[1]/div/div/div[2]/div/div[1]/div")
	public WebElement content;

	@FindBy(xpath = "//*[@id=\"post-5422\"]/div/div[1]/div/div/div[2]/div/div[1]/div/div[2]/div/div/h1[4]/span")
	public WebElement personalData;

	@FindBy(xpath = "//*[@id=\"post-5422\"]/div/div[1]/div/div/div[2]/div/div[1]/div/div[2]/div/div/table[3]/tbody/tr[1]/td[1]")
	public WebElement purpose;

	//*[@id="post-5422"]/div/div[1]/div/div/div[2]/div/div[1]
	/*	private static boolean isEqual(File ExpectedInfo, File ExtractedInfo)
    {
        try {
            return FileUtils.contentEquals(ExpectedInfo, ExtractedInfo);
        } catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }*/
	public boolean privacy(String enterpriseID, String enterprisePassword) {
		SoftAssert softassert = new SoftAssert();
		try {

			String btn = privacyStatement.getText();
			if(btn.equals("Privacy Statement")) {
				wait.until(ExpectedConditions.visibilityOf(privacyStatement));
				logger.log(LogStatus.INFO, "Click on Privacy Statement");
				System.out.println("Click on Privacy Statement");
				privacyStatement.click();

				commonMethods.waitForPageToLoad();
				ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
				driver.switchTo().window(tabs.get(1));
				logger.log(LogStatus.INFO, "Dispaly information in new tab");
				System.out.println("Dispaly information in new tab");
				commonMethods.waitForPageToLoad();

				wait.until(ExpectedConditions.visibilityOf(enterpriseId));
				//				softassert.assertEquals(word.getText(), "Sign in");
				logger.log(LogStatus.INFO, "Entering the Enterprise Id");
				System.out.println("Entering the Enterprise Id");
				enterpriseId.sendKeys(enterpriseID);
				commonMethods.waitForPageToLoad();
				next.click();

				wait.until(ExpectedConditions.visibilityOf(enterprisePass));
				logger.log(LogStatus.INFO, "Entering the Enterprise password");
				System.out.println("Entering the Enterprise password");
				enterprisePass.sendKeys(enterprisePassword);
				commonMethods.waitForPageToLoad();
				next.click();
				
				logger.log(LogStatus.INFO, "Clicking on SignIn another way");
				System.out.println("Clicking on SignIn another way");
				commonMethods.waitForPageToLoad();
				wait.until(ExpectedConditions.visibilityOf(signOtherWay));
				signOtherWay.click();
				commonMethods.waitForPageToLoad();
				
				wait.until(ExpectedConditions.visibilityOf(call));
				logger.log(LogStatus.INFO, "Calling to the moblie to approve signIn");
				System.out.println("Calling to the moblie to approve signIn");
				call.click();
				commonMethods.waitForPageToLoad();
				wait.until(ExpectedConditions.visibilityOf(heading));

				Assert.assertTrue(heading.getText().contains("GLOBAL DATA PRIVACY STATEMENT"));
				Assert.assertTrue(personalData.getText().contains("PERSONAL DATA"));
				if(personalData.getText().contains("PERSONAL DATA"))
				{
					logger.log(LogStatus.PASS, "Personal data is colleted for a specific purpose listed");
					System.out.println("Personal data is colleted for a specific purpose listed");
					personalData.click();
					List<WebElement> rows = driver.findElements(By.xpath("//*[@id=\"post-5422\"]/div/div[1]/div/div/div[2]/div/div[1]/div/div[2]/div/div/table[3]/tbody/tr"));
					commonMethods.waitForPageToLoad();
//					System.out.println("Number of rows:" + rows.size());
					

					for(int i=2; i<rows.size(); i++) {
						WebElement name = driver.findElement(By.xpath("//*[@id=\"post-5422\"]/div/div[1]/div/div/div[2]/div/div[1]/div/div[2]/div/div/table[3]/tbody/tr["+i+"]/td[1]"));
						System.out.println(name.getText());
					}
					return true;

				}
				else
				{
					System.out.println("Purpose limitation is not mentioned for colleting personal data");
					logger.log(LogStatus.FAIL, "Purpose limitation is not mentioned for colleting personal data");
					return false;
				}

			}
			else {
				logger.log(LogStatus.FAIL, "There is no Purpose limitation listed");
				System.out.println("There is no Purpose limitation listed");
				return false;
			}
		}
		catch (Exception e){
			System.out.println(e);
			logger.log(LogStatus.FAIL, "Privacy Statement page did not open");
			System.out.println("Privacy Statement page did not open");
			return false;
		}
	}
}
