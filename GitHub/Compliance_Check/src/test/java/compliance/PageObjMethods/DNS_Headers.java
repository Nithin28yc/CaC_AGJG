package compliance.PageObjMethods;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;
import Generic.TestBase.BrowserConfig;
import Generic.TestBase.CommonMethods;
import Generic.TestBase.testBase;

public class DNS_Headers extends testBase {

	private WebDriver driver = BrowserConfig.getDriver();

	CommonMethods commonMethods = PageFactory.initElements(driver, CommonMethods.class);
	WebDriverWait wait = new WebDriverWait(driver, 45);
	JavascriptExecutor jse = (JavascriptExecutor) driver;
	
	public boolean VerifySecurityHeaders(String scanurl) throws Exception {

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		logger.log(LogStatus.INFO, "Starting the DNSHearders Validations");
		System.out.println("Starting the DNSHearders Validations");	
		WebElement siteaddress = driver.findElement(By.xpath("//*[@id='q']"));
		logger.log(LogStatus.INFO, "Scan the URL :"+scanurl);
		System.out.println("Scan the URL :"+scanurl);	
		siteaddress.sendKeys(scanurl);
		commonMethods.waitForPageToLoad();
		WebElement scanbutton = driver.findElement(By.id("scan"));
		scanbutton.click();
		commonMethods.waitForPageToLoad();
		String scorecol=driver.findElement(By.xpath("//div[@class='score']//div")).getAttribute("class");
		String score=driver.findElement(By.xpath("//div[@class='score']//span")).getText();
		if(scorecol.contains("score_green"))
		{
			Assert.assertTrue(true);
			logger.log(LogStatus.PASS, "The DNS Headers Validations Passed and Score is "+score);
			System.out.println("The DNS Headers Validations Passed and Score is "+score);
			return true;
		}else
			
		{
			logger.log(LogStatus.FAIL, "The DNS Headers Validations Failed and Score is "+score);
			System.out.println("The DNS Headers Validations Failed and Score is "+score);
			logger.log(LogStatus.INFO, "Missing Headers are as follows");
			System.out.println("Missing Headers are as follows");
			List<WebElement> headers=driver.findElements(By.xpath("//div[@class='reportTitle' and text()='Missing Headers']//parent::div//tr"));
			for(int i=1;i<=headers.size();i++)
			{
				String headerlabel=driver.findElement(By.xpath("(//div[@class='reportTitle' and text()='Missing Headers']//parent::div//tr//th)["+i+"]")).getText();
				String headerdetail=driver.findElement(By.xpath("(//div[@class='reportTitle' and text()='Missing Headers']//parent::div//tr//td)["+i+"]")).getText();
				logger.log(LogStatus.INFO,headerlabel+":"+headerdetail );
				System.out.println(headerlabel+":"+headerdetail );
			}
			Assert.assertTrue(false);
			return false;
		}
		
//		Actions actions = new Actions(driver);
//		actions.contextClick().perform();
//		
//		actions.sendKeys(Keys.CONTROL+"S");
		
		
		
//		// code to get the page source from current url
//		String appliurl = driver.getCurrentUrl();
//		System.out.println(appliurl);
//		String pageSource = driver.getPageSource();
//		// System.out.println(pageSource);
//
//		// code to save the captured pagesource text into an file in local
//		try {
//			File newtextFile = new File("./page1.html");
//			FileWriter fw = new FileWriter(newtextFile);
//			fw.write(pageSource);
//			fw.close();
//		} catch (Exception e) {
//			System.out.println(e);
//		}

	}

}
