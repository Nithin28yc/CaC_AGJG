package compliance.Functional;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Generic.TestBase.BrowserConfig;
import Generic.TestBase.CommonMethods;
import Generic.TestBase.ExcelOp;
import Generic.TestBase.testBase;
import compliance.PageObjMethods.Authentication;
import compliance.PageObjMethods.Verify_Logs;


public class VerifyLogs extends testBase{
	private WebDriver driver = null;
	String parent;
	public String parentHandle = null;
	public static boolean classname;


	@BeforeClass
	public void initSetUp() throws IOException, InterruptedException {

		BrowserConfig.setDriver(browserType, appURL);
		driver=BrowserConfig.getDriver();
		ExcelOp.loadExcel("Testdata");

		try {
			if (driver == null)
				System.out.println("---Driver not found---");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		classname = true;
	
	}

	@Test( priority = 0, enabled = true)
	public void platformValidLogin() throws InterruptedException, IOException {
		CommonMethods commonMethods = PageFactory.initElements(driver, CommonMethods.class);
		Authentication login = PageFactory.initElements(driver, Authentication.class);
		 //login.ngnixLogin(testBase.getUsername(), testBase.getPassword());
		//login.ngnixLogin(testBase.getUsername(), testBase.getPassword());
		login.devOpsPlatformLogin(testBase.getUsername(),testBase.getPassword());

	}
	
	@Test( priority = 1, enabled = true)
	public void clusterLogs() throws InterruptedException, IOException {
		CommonMethods commonMethods = PageFactory.initElements(driver, CommonMethods.class);
		Verify_Logs logs = PageFactory.initElements(driver, Verify_Logs.class);
		logs.fetch_clusterLogs();

	}
	
	@AfterClass
	public void endDriver() {
		//	driver.quit();
	}
}
