package compliance.PageObjMethods;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
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

public class DataTransfer_Policy extends testBase{

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

	@FindBy(xpath = "//*[@id=\"colophon\"]/div/div[2]/span[1]/text()")
	public WebElement copyrights;
                      
	@FindBy(xpath = "//*[@id='post-5422']/div/div[1]/div/div/div[2]/div/div[1]/div")
	public WebElement content;
	
	@FindBy(xpath = "//*[@id=\"post-5422\"]/div/div[1]/div/div/div[2]/div/div[1]/div/div[2]/div/div/p[4]/span")
	public WebElement european;
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

				wait.until(ExpectedConditions.visibilityOf(signOtherWay));
				logger.log(LogStatus.INFO, "Clicking on SignIn another way");
				System.out.println("Clicking on SignIn another way");
				signOtherWay.click();
				commonMethods.waitForPageToLoad();
				
				logger.log(LogStatus.INFO, "Calling to the moblie to approve signIn");
				System.out.println("Calling to the moblie to approve signIn");
				call.click();
				commonMethods.waitForPageToLoad();
				wait.until(ExpectedConditions.visibilityOf(heading));

				Assert.assertTrue(heading.getText().contains("GLOBAL DATA PRIVACY STATEMENT"));
//				FileWriter file = new FileWriter("./testfile/Content.txt");
//				file.write(content.getText());
//				file.close();
//				logger.log(LogStatus.PASS, "File has been created");
//				System.out.println("File has been created");
				//				
				//				File ExpectedInfo = new File("./testfile/PrivacyStatement_ExpectedMsg.txt");
				//				
				//		        File ExtractedInfo = new File("./testfile/Content.txt");
				//		        
				//		        boolean equal = isEqual(ExpectedInfo, ExtractedInfo);
				//		        if (equal) {
				//		        	logger.log(LogStatus.PASS, "Expected Information and Extracted Information are same");
				//		            System.out.println("Expected Information and Extracted Information are same");
				//		        }
				//		        else {
				//		        	logger.log(LogStatus.FAIL, "Expected Information and Extracted Information are not same");
				//		            System.out.println("Expected Information and Extracted Information are not same");
				//		        }

//				BufferedReader ExpectedInfo = new BufferedReader(new FileReader("./testfile/PrivacyStatement_ExpectedMsg.txt"));
//
//				BufferedReader ExtractedInfo = new BufferedReader(new FileReader("./testfile/Content.txt"));
//
//				String line1 = ExpectedInfo.readLine();
//
//				String line2 = ExtractedInfo.readLine();
//
//				boolean areEqual = true;
//
//				int lineNum = 1;
//
//				while (line1 != null || line2 != null)
//				{
//					if(line1 == null || line2 == null)
//					{
//						areEqual = false;
//
//						break;
//					}
//					else if(! line1.equalsIgnoreCase(line2))
//					{
//						areEqual = false;
//
//						break;
//					}
//
//					line1 = ExpectedInfo.readLine();
//
//					line2 = ExtractedInfo.readLine();
//
//					lineNum++;
//				}

				if(heading.getText().contains("GLOBAL DATA PRIVACY STATEMENT"))
				{
					logger.log(LogStatus.PASS, "Data Transfer policy and statements are present");
					System.out.println("Data Transfer policy and statements are present");
					european.click();
					if(european.getText().contains("European")) {
						logger.log(LogStatus.PASS, "Data Transfer policy for eu and non-eu region is there");
						System.out.println("Data Transfer policy for eu and non-eu region is there");
					}
					return true;
				}
				else
				{
//					System.out.println("Two files have different content. They differ at line "+lineNum);
//					logger.log(LogStatus.INFO, "Two files have different content. They differ at line "+lineNum);
//					System.out.println("ExpectedInfo file has "+line1+" and ExtractedInfo file has "+line2+" at line "+lineNum);
					logger.log(LogStatus.PASS, "Data Transfer policy and statements is not present");
					System.out.println("Data Transfer policy and statements is not present");
					return false;
				}

//				ExpectedInfo.close();
//
//				ExtractedInfo.close();
			}
			else {
				logger.log(LogStatus.FAIL, "There is no Privacy Statement");
				System.out.println("There is no Privacy Statement");
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
