package compliance.Functional;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Generic.TestBase.BrowserConfig;
import Generic.TestBase.CommonMethods;
import Generic.TestBase.testBase;
import compliance.PageObjMethods.Testing_MFA;

public class TestingMFA extends testBase{
	private WebDriver driver = null;
	String parent;
	public String parentHandle = null;
	public static boolean classname;
	
	
	@BeforeClass
	public void initSetUp() throws IOException, InterruptedException {
		
	BrowserConfig.setDriver(browserType, appURL);
	driver=BrowserConfig.getDriver();
	//ExcelOp.loadExcel("TestData");
	
	try {
	if (driver == null)
	System.out.println("---Driver not found---");
	} catch (Exception e) {
	System.out.println(e.getMessage());
	}
	classname = true;
	}
	
	@Test( priority = 1, enabled = true)
	public void Multi_Factor_Authentication() throws InterruptedException, IOException {
		CommonMethods commonMethods = PageFactory.initElements(driver, CommonMethods.class);
		Testing_MFA mfa = PageFactory.initElements(driver, Testing_MFA.class);
		boolean flag = mfa.verify_MFA(testBase.getUsername(), testBase.getPassword());
		Assert.assertTrue(flag);
	}
	
	@AfterClass
	public void endDriver() {
		driver.quit();
	}
}
