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
import compliance.PageObjMethods.Notice_Privacy;
import compliance.PageObjMethods.PasswordHistory_Policy;


public class PasswordHistoryPolicy extends testBase{
	private WebDriver driver = null;
	String parent;
	public String parentHandle = null;
	public static boolean classname;


	@BeforeClass
	public void initSetUp() throws IOException, InterruptedException {

		BrowserConfig.setDriver(browserType, appURL);
		driver=BrowserConfig.getDriver();
		//ExcelOp.loadExcel("Testdata");

		try {
			if (driver == null)
				System.out.println("---Driver not found---");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		classname = true;
	
	}

	@Test( priority = 1, enabled = true)
	public void passwordHistoryPolicy() throws InterruptedException, IOException {
		CommonMethods commonMethods = PageFactory.initElements(driver, CommonMethods.class);
		PasswordHistory_Policy pass = PageFactory.initElements(driver, PasswordHistory_Policy.class);
		pass.login(testBase.getUsername(), testBase.getPassword());
		boolean flag = pass.passwordChange(testBase.getLdapuname(),testBase.getLdapPass(),testBase.getNewPassword());
		Assert.assertTrue(flag);
	}


	@AfterClass
	public void endDriver() {
		//	driver.quit();
	}
}
