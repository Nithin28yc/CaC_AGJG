package compliance.Functional;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Generic.TestBase.BrowserConfig;
import Generic.TestBase.CommonMethods;
import Generic.TestBase.ExcelOp;
import Generic.TestBase.testBase;
import compliance.PageObjMethods.SslLabs_AWS;
import compliance.PageObjMethods.SslLabs_AzureGCP;

public class SSLLABS_AzureGCP extends testBase {

	private WebDriver driver = null;
	String parent;
	public String parentHandle = null;
	public static boolean classname;
	public SoftAssert softAssertions;
	Boolean flag;

	@BeforeClass
	public void initSetUp() throws IOException, InterruptedException {

		BrowserConfig.setDriver(browserType, appURL);
		CommonMethods commonMethods1 = PageFactory.initElements(driver, CommonMethods.class);
		commonMethods1.waitForPageToLoad();
		driver = BrowserConfig.getDriver();
		softAssertions = new SoftAssert();
//		ExcelOp.loadExcel("SSLSheet");
		System.out.println("--------------------------SSL LABS URL Execution Started--------------------------");
		try {
			if (driver == null)
				System.out.println("---Driver not found---");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		classname = true;
	}

	@Test(priority = 0, enabled = true)
	public void SSLUrlSearch() throws InterruptedException, IOException {
			SslLabs_AzureGCP sslsearch = PageFactory.initElements(driver, SslLabs_AzureGCP.class);
			boolean flag = sslsearch.searchAllurl(testBase.getScanurl());
			Assert.assertTrue(flag);
	}

	@AfterClass
	public void endDriver() {
//		driver.quit();
	}
}
