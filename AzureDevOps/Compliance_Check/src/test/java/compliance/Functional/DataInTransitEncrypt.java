package compliance.Functional;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Generic.TestBase.BrowserConfig;
import Generic.TestBase.CommonMethods;
import Generic.TestBase.restAssured_testBase;
import Generic.TestBase.testBase;
import compliance.PageObjMethods.DataInTransit_Encrypt;

public class DataInTransitEncrypt {
	private WebDriver driver = null;
	String parent;
	public String parentHandle = null;
	public static boolean classname;


	@Test( priority = 0, enabled = true)
	public void dataDisposal() throws InterruptedException, IOException {
		DataInTransit_Encrypt check = PageFactory.initElements(driver, DataInTransit_Encrypt.class);
//		check.transitEncrypt();
		check.transitEncrypt(testBase.getUsername(), testBase.getPassword());
	}
	
}
