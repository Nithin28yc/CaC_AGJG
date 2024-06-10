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
import compliance.PageObjMethods.VerifyPassword_Encryption;
import compliance.PageObjMethods.Notice_Privacy;

public class VerifyPasswordEncryption extends testBase{
	private WebDriver driver = null;
	String parent;
	public String parentHandle = null;
	public static boolean classname;


	@BeforeClass
	public void initSetUp() throws IOException, InterruptedException {

		BrowserConfig.setDriver(browserType, appURL);
		driver=BrowserConfig.getDriver();
//		ExcelOp.loadExcel("Testdata");

		try {
			if (driver == null)
				System.out.println("---Driver not found---");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		classname = true;
	
	}

//	@Test( priority = 0, enabled = true)
//	public void platformValidLogin() throws InterruptedException, IOException {
//		CommonMethods commonMethods = PageFactory.initElements(driver, CommonMethods.class);
//		Notice_Privacy login = PageFactory.initElements(driver, Notice_Privacy.class);
//		//login.ngnixLogin(testBase.getUsername(), testBase.getPassword());
//		login.devOpsPlatformLogin(testBase.getUsername(),testBase.getPassword());
//
//	}

	@Test( priority = 1, enabled = true)
	public void PasswordEncryption() throws InterruptedException, IOException {
		CommonMethods commonMethods = PageFactory.initElements(driver, CommonMethods.class);
		VerifyPassword_Encryption ldapuser = PageFactory.initElements(driver, VerifyPassword_Encryption.class);
		ldapuser.devOpsPlatformLogin(testBase.getUsername(),testBase.getPassword());
		ldapuser.ldapuilogin(testBase.getUsername(),testBase.getPassword(),testBase.getldaploginusername(),testBase.getldappassword());
	    ldapuser.password_check(testBase.getUsername());
		
//		int rowCount = ExcelOp.getRowCount("addldapuser");
//		System.out.println(rowCount);
//		for (int row = 1; row <=rowCount; row++) {
//
//			testBase.uname = ExcelOp.ReadExcelData("addldapuser", row, "Username");
//			testBase.mail = ExcelOp.ReadExcelData("addldapuser", row, "Email");
//			testBase.passwd = ExcelOp.ReadExcelData("addldapuser", row, "Password");
//			testBase.confirmpass = ExcelOp.ReadExcelData("addldapuser", row, "ConfirmPass");
//			addldapuser.addingLdapUser(testBase.uname,testBase.mail,testBase.passwd,testBase.confirmpass);
//		}

//		int rowCount1 = ExcelOp.getRowCount("ldap_ui");
//		System.out.println(rowCount1);
//		for (int row = 1; row <= rowCount1; row++) {
//
//			testBase.ldapuser = ExcelOp.ReadExcelData("ldap_ui", row, "LDAP_username");
//			testBase.ldappasswd = ExcelOp.ReadExcelData("ldap_ui", row, "LDAP_pass");
//			addldapuser.ldapuilogin(testBase.getUsername(),testBase.getPassword(),testBase.ldapuser,testBase.ldappasswd);
//		}
//		addldapuser.password_check();
	}
	
/*	@Test( priority = 2, enabled = true)
	public void addLdapUser() throws InterruptedException, IOException {
		AddLDAP addldapuser = PageFactory.initElements(driver, AddLDAP.class);

		int rowCount = ExcelOp.getRowCount("addldapuser");
		System.out.println(rowCount);
		for (int row = 1; row <=rowCount; row++) {

			testBase.uname = ExcelOp.ReadExcelData("addldapuser", row, "Username");
			testBase.mail = ExcelOp.ReadExcelData("addldapuser", row, "Email");
			testBase.passwd = ExcelOp.ReadExcelData("addldapuser", row, "Password");
			testBase.confirmpass = ExcelOp.ReadExcelData("addldapuser", row, "ConfirmPass");
			addldapuser.addingLdapUser(testBase.uname,testBase.mail,testBase.passwd,testBase.confirmpass);
		}

	}

	@Test( priority = 3, enabled = true)
	public void passwordCheck() throws InterruptedException, IOException {
		LDAP_UI passwordcheck = PageFactory.initElements(driver, LDAP_UI.class);
		passwordcheck.ldapuilogin(testBase.getldapuser(),testBase.getldappasswd());
	}
*/

	@AfterClass
	public void endDriver() {
		//	driver.quit();
	}
}
