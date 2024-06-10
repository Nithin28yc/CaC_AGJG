package compliance.PageObjMethods;

import java.io.FileWriter;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;

import Generic.TestBase.BrowserConfig;
import Generic.TestBase.CommonMethods;
import Generic.TestBase.testBase;

public class Verify_Logs extends testBase {
	
	private WebDriver driver = BrowserConfig.getDriver();
	WebDriverWait wait = new WebDriverWait(driver, 45);
	JavascriptExecutor jse = (JavascriptExecutor) driver;
	CommonMethods commonMethods = PageFactory.initElements(driver, CommonMethods.class);
	
	@FindBy(xpath = "//a[text()='Multi-Cloud Management']")
    public WebElement multicloudtab;
	
	@FindBy(xpath = "//a[text()='Infrastructure Status']")
    public WebElement infrastructureStatustab;
	
	@FindBy(xpath = "//*[@id=\"cloud\"]/div/div[2]/div[2]/table/tbody/tr[1]/td[1]/div/a[1]/img")
    public WebElement logs;
	
	@FindBy(xpath = "//a[text()='show more..']")
    public WebElement showmore;
	
	@FindBy(xpath = "//*[@id=\"logModal\"]/div[2]")
    public WebElement clusterlogs;
	
	public void fetch_clusterLogs() throws InterruptedException {
		
		commonMethods.waitForPageToLoad();
		
		try {
			logger.log(LogStatus.INFO, "Click on MultiCloud Platform tab");
            System.out.println("Click on MultiCloud Platform tab");
            multicloudtab.click();
            commonMethods.waitForPageToLoad();
            
            logger.log(LogStatus.INFO, "Click on Infrastructure Status tab");
            System.out.println("Click on Infrastructure Status tab");
            infrastructureStatustab.click();
            commonMethods.waitForPageToLoad();
            
            logger.log(LogStatus.INFO, "Click on cluster logs");
            System.out.println("Click on cluster logs");
            logs.click();
            commonMethods.waitForPageToLoad();
            
            logger.log(LogStatus.INFO, "Click on show more");
            System.out.println("Click on show more");
            showmore.click();
            commonMethods.waitForPageToLoad();
            
            logger.log(LogStatus.INFO, "Fetching cluster logs");
            System.out.println("Fetching cluster logs");
            String logs=clusterlogs.getText();
            System.out.println(logs);
            commonMethods.waitForPageToLoad();
            
            FileWriter file = new FileWriter("./testfile/ClusterLogs.txt");
			file.write(logs);
			file.close();
            
		}
		catch (Exception e) {
            System.out.println(e);
            logger.log(LogStatus.FAIL, "User is not able to fetch logs");
            System.out.println("User is not able to fetch logs");
            Assert.fail("User is not able to fetch logs");
        }
	}
	
	
	

}
