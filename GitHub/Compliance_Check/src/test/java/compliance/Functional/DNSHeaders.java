package compliance.Functional;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Generic.TestBase.BrowserConfig;
import Generic.TestBase.CommonMethods;
import Generic.TestBase.testBase;
import compliance.PageObjMethods.DNS_Headers;

public class DNSHeaders extends testBase {
//	public static boolean classname = false;
//	public static WebDriver driver;
//	static String driverPath = "./Drivers_Jars";

	private WebDriver driver = null;
	String parent;
	public String parentHandle = null;
	public static boolean classname;
	public SoftAssert softAssertions;
	Boolean flag;
	
	
	@BeforeClass
	public void initSetUp() throws IOException, InterruptedException {
		BrowserConfig.setDriver(browserType, appURL);
		driver = BrowserConfig.getDriver();
		// driver=TestBase.testbase.BrowserConfig.getDriver();
		

		try {
			if (driver == null)
				System.out.println("---Driver not found---");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		classname = true;
	}

	@Test(priority = 0, enabled = true)
	public void SecurityHeaders() throws Exception {
		DNS_Headers dnsHeader = PageFactory.initElements(driver, DNS_Headers.class);
		boolean flag = dnsHeader.VerifySecurityHeaders(testBase.getScanurl());
		Assert.assertTrue(flag);
	}
	
	@AfterClass
	public void endDriver() {
//		driver.quit();
	}
}
