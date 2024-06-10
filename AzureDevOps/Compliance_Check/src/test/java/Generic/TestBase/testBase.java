package Generic.TestBase;

import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.junit.AfterClass;
import org.openqa.selenium.WebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.Assertion;
import org.testng.asserts.IAssert;
import org.testng.collections.Maps;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import compliance.Functional.Authentication_test;
import compliance.Functional.ChangePassword;
import compliance.Functional.CookieAcceptance;
import compliance.Functional.CookieStorageType;
import compliance.Functional.CrossSite;
import compliance.Functional.DNSHeaders;
import compliance.Functional.DataClassification;
import compliance.Functional.DataDisposal;
import compliance.Functional.DataInTransitEncrypt;
import compliance.Functional.DataMasking;
import compliance.Functional.DataMinimization;
import compliance.Functional.DataRetention;
import compliance.Functional.PasswordComplexity;
import compliance.Functional.PasswordHistoryPolicy;
import compliance.Functional.PrivilegedAccessManagement;
import compliance.Functional.PurposeLimitation;
import compliance.Functional.DataTransferPolicy;
import compliance.Functional.LeastPrivilege;
import compliance.Functional.ErrorHandlingInvalidParameter;
import compliance.Functional.LegalContractual;
import compliance.Functional.LoggingMonitoring;
import compliance.Functional.SSLLABS_AWS;
import compliance.Functional.SSLLABS_AzureGCP;
import compliance.Functional.SessionExpire;
import compliance.Functional.SessionManagement;
import compliance.Functional.SingleSignOn;
import compliance.Functional.TestingMFA;
import compliance.Functional.VerifyPasswordEncryption;
import compliance.Functional.VerifyLogs;
import compliance.Functional.NoticePrivacy;
import compliance.Functional.roleBasedAccess;
import compliance.Functional.uniqueness;

//import ManagePVC.Functional.RegressionManagepvc;

public class testBase implements ISuiteListener {
 WebDriver driver;
	public static ExtentReports extent;
	public static ExtentTest logger;
	public static String browserType;
	public static String category;
	public static String selectedCartridges;
	public static String cartridgesToLoad;
	public static String appURL;
	public static String username;
	public static String password;
	public static String namevalidation;
	public static String emailvalidation;
	public static String passwordvalidation;
	public static String toolnamevalidation;
	public static String displaynamevalidation;
	public static String descriptionvalidation;
	public static String versionvalidation;
	public static String urlvalidation;
	public static String cartridgenamevalidation;
	public static String pipelineversionvalidation;
	public static String pipelinenamevalidation;
	public static String pipelinepathvalidation;
	public static String Cartridgenameforpipelinepath;
	public static String cartridge;
	public static String gituser;
	public static String namespace;
	public static String repourl;
	public static String srptpath;
	public static String rptpath;
	public static String ldapuname;
	public static String ldapEmail;
	public static String ldapPass;
	public static String enterpriseID;
	public static String enterprisePassword;
	
	public static String testAppURL;
	public static String testUsername;
	public static String testPassword;
	public static String[] selectedCartridgesArray;
	public static String[] cartridgesToLoadArray;
	public static String osystem;
	public String rptfile;
	public static boolean classname;
	
	public static String uname;
	public static String mail;
	public static String passwd;
	public static String confirmpass;
	public static String ldapusername;
	public static String ldaploginusername;
	public static String keycloakUserName;
	public static String keycloakPassword;
	public static String email;
	public static String firstName;
	public static String lastName;
	public static String groupName;
	
	public static String ldappassword;
	public static String SSLURL;
	public static String oldPassword;
	public static String newPassword;
	public static String Zfile;
	public static String testName;
	public static String scanurl;
	public static String appURL1;
	public static String username1;
	public static String password1;
	public static String reponame;
	public static String pat;
	public static String file;

//Initial Prerequisites for any test - Browser config and LoadExcel	by receiving the parameters from cmd line
	public void onStart(ISuite suite) {

	}

	public void onFinish(ISuite suite) {

	}

	@BeforeClass
	@Parameters({ "browserType", "appURL", "username", "password", 
		"cartridge","gituser","namespace","repourl","namevalidation","emailvalidation","passwordvalidation","cartridgenamevalidation","toolnamevalidation","displaynamevalidation","descriptionvalidation","urlvalidation","versionvalidation","pipelinenamevalidation","pipelinepathvalidation","Cartridgenameforpipelinepath","pipelineversionvalidation","uname","mail","passwd","confirmpass","ldaploginusername","ldappassword",
		"ldapuname", "ldapEmail", "scanurl", "ldapPass", "enterpriseID", "enterprisePassword","keycloakUserName","keycloakPassword","email","firstName","lastName","groupName", "SSLURL", "oldPassword", "newPassword", "username1", "password1", "appURL1","reponame","pat","file" })
	public void setPlatformType(@Optional("NA") String browserType, @Optional("NA") String appURL, @Optional("NA") String username, 
			@Optional("NA") String password,@Optional("NA") String cartridge,@Optional("NA") String gituser,
			@Optional("NA") String namespace,@Optional("NA") String repourl,@Optional("NA") String namevalidation,@Optional("NA") String emailvalidation,@Optional("NA") String passwordvalidation,@Optional("NA") String cartridgenamevalidation,@Optional("NA") String toolnamevalidation,@Optional("NA") String displaynamevalidation,@Optional("NA") String descriptionvalidation,@Optional("NA") String urlvalidation,@Optional("NA") String versionvalidation,@Optional("NA") String pipelinenamevalidation,@Optional("NA") String pipelinepathvalidation,@Optional("NA") String Cartridgenameforpipelinepath,@Optional("NA") String pipelineversionvalidation,@Optional("NA") String uname,@Optional("NA") String mail,
			@Optional("NA") String passwd,@Optional("NA") String confirmpass,@Optional("NA") String ldaploginusername,@Optional("NA") String ldappassword, @Optional("NA") String ldapuname, @Optional("NA") String ldapEmail, @Optional("NA") String scanurl,
			   @Optional("NA") String ldapPass, @Optional("NA") String enterpriseID, @Optional("NA") String enterprisePassword ,@Optional("NA") String keycloakUserName,@Optional("NA") String keycloakPassword,@Optional("NA") String email,@Optional("NA") String firstName,@Optional("NA") String lastName,@Optional("NA") String groupName ,@Optional("NA") String SSLURL, 
			   @Optional("NA") String oldPassword, @Optional("NA") String newPassword, @Optional("NA") String username1, @Optional("NA") String password1, @Optional("NA") String appURL1,@Optional("NA") String reponame, @Optional("NA") String pat, @Optional("NA") String file) throws InterruptedException, MalformedURLException {
	testBase.browserType = browserType;
	testBase.appURL = appURL;
	testBase.username = username;
	testBase.password = password;

	testBase.cartridge=cartridge;
	testBase.gituser=gituser;
	testBase.namespace=namespace;
	testBase.repourl=repourl;
	testBase.namevalidation=namevalidation;
	testBase.emailvalidation=emailvalidation;
	testBase.passwordvalidation=passwordvalidation;
	testBase.passwordvalidation=cartridgenamevalidation;
	testBase.pipelineversionvalidation=pipelineversionvalidation;
	testBase.toolnamevalidation=toolnamevalidation;
	testBase.displaynamevalidation=displaynamevalidation;
	testBase.descriptionvalidation=descriptionvalidation;
	testBase.versionvalidation=versionvalidation;
	testBase.descriptionvalidation=urlvalidation;
	testBase.pipelinenamevalidation=pipelinenamevalidation;
	testBase.pipelinepathvalidation=pipelinepathvalidation;
	testBase.Cartridgenameforpipelinepath=Cartridgenameforpipelinepath;
	
	testBase.uname=uname;
	testBase.mail=mail;
	testBase.passwd=passwd;
	testBase.confirmpass=confirmpass;
	testBase.ldaploginusername=ldaploginusername;
	testBase.ldappassword=ldappassword;
	
	testBase.ldapuname = ldapuname;
	testBase.ldapEmail = ldapEmail;
	testBase.ldapPass = ldapPass;
	testBase.enterpriseID = enterpriseID;
	testBase.enterprisePassword = enterprisePassword;
	testBase.keycloakUserName = keycloakUserName;
	testBase.keycloakPassword = keycloakPassword;
	testBase.email = email;
	testBase.firstName = firstName;
	testBase.lastName = lastName;
	testBase.groupName = groupName;
	
	testBase.SSLURL=SSLURL;
	testBase.oldPassword = oldPassword;
	testBase.newPassword = newPassword;
	testBase.scanurl=scanurl;
	testBase.appURL1 = appURL1;
	testBase.username1 = username1;
	testBase.password1 = password1;
	testBase.reponame = reponame;
	testBase.pat = pat;
	testBase.file = file;
	}

	public static String getBrowserType() {		
		return browserType;
	}
	
	public static String getCategory() {
		return testBase.category;
	}
	
	public static String getAppURL() {
		return testBase.appURL;
	}

	public static String getUsername() {
		return testBase.username;
	}

	public static String getPassword() {
		return testBase.password;
	}
	
	public static String getSelectedCartridges() {
		return testBase.selectedCartridges;
	}
	
	public static String getCartridgesToLoad() {
		return testBase.cartridgesToLoad;
	}
	

	public static String getcartridge() {
		return testBase.cartridge;
	}
	
	public static String getgituser() {
		return testBase.gituser;
	}
	public static String getnamespace() {
		return testBase.namespace;
	}
	public static String getrepourl() {
		return testBase.repourl;
	}
	
	public static String getnamevalidation() {
		return testBase.namevalidation;
	}
	
	public static String getemailvalidation() {
		return testBase.emailvalidation;
	}

	public static String gettoolnamevalidation() {
		return testBase.toolnamevalidation;
	}
	
	public static String getdisplaynamevalidation() {
		return testBase.displaynamevalidation;
	}
	
	public static String getdescriptionvalidation() {
		return testBase.descriptionvalidation;
	}
	
	public static String geturlvalidation() {
		return testBase.urlvalidation;
	}
	public static String getversionvalidation() {
		return testBase.versionvalidation;
	}
	
	
	public static String getcartridgenamevalidation() {
		return testBase.cartridgenamevalidation;
	}
	
	public static String getpipelineversionvalidation() {
		return testBase.pipelineversionvalidation;
	}
	
	public static String getpipelinenamevalidation() {
		return testBase.pipelinenamevalidation;
	}
	
	public static String getCartridgenameforpipelinepath() {
		return testBase.Cartridgenameforpipelinepath;
	}
	
	public static String getpipelinepathvalidation() {
		return testBase.pipelinepathvalidation;
	}
	
	
	public static String getuname() {
		return testBase.uname;
	}

	public static String getmail() {
		return testBase.mail;
	}
	public static String getpasswd() {
		return testBase.passwd;
	}
	public static String getconfirmpass() {
		return testBase.confirmpass;
	}
	public static String getldaploginusername() {
		return testBase.ldaploginusername;
	}
	public static String getldappassword() {
		return testBase.ldappassword;
	}
	
	public static String getLdapuname() {
		return testBase.ldapuname;
	}
	
	public static String getLdapEmail() {
		return testBase.ldapEmail;
	}
	
	public static String getLdapPass() {
		return testBase.ldapPass;
	}
	
	public static String getEnterpriseID() {
		return testBase.enterpriseID;
	}
	public static String getScanurl() {		
		return testBase.scanurl;
	}
	public static String getEnterprisePassword() {
		return testBase.enterprisePassword;
	}
	
	public static String getKeycloakUserName() {
		return testBase.keycloakUserName;
	}
	public static String getKeycloakPssword() {
		return testBase.keycloakPassword;
	}
	public static String getEmail() {
		return testBase.email;
	}
	public static String getFirstName() {
		return testBase.firstName;
	}
	public static String getLastName() {
		return testBase.lastName;
	}
	
	public static String getgroupName() {
		return testBase.groupName;
	}
	
	public static String[] getSelectedCartridgesArray() {
		return testBase.selectedCartridgesArray;
	}
	
	public static String[] getCartridgesToLoadArray() {
		return testBase.cartridgesToLoadArray;
	}
	
	public static String getTestAppURL() {
		return testBase.testAppURL;
	}

	public static String getTestUsername() {
		return testBase.testUsername;
	}

	public static String getTestPassword() {
		return testBase.testPassword;
	}
	
	public static String getSSLURL() {		
		return testBase.SSLURL;
	}
	
	public static String getOldPassword() {		
		return testBase.oldPassword;
	}
	
	public static String getNewPassword() {		
		return testBase.newPassword;
	}
	
	public static String getAppURL1() {
		return testBase.appURL1;
	}
	
	public static String getUsername1() {
		return testBase.username1;
	}

	public static String getPassword1() {
		return testBase.password1;
	}
	public static String getreponame() {
		return testBase.reponame;
	}
	public static String getpat() {
		return testBase.pat;
	}
	public static String getfile() {
		return testBase.file;
	}

	@DataProvider(name = "getTestData")
	public Object[][] getTestData(Method m) throws Exception {
		if (m.getName().equalsIgnoreCase("platformValidLogin")) {
			Object[][] testObjArray = ExcelOp.getTableArray("login");
			return testObjArray;
		} 
		 else
			return null;
	}

	// Initial Prerequisites for any test - Start report, capture report and flush
	// report

	@BeforeTest
	@Parameters({ "browserType", "category" })
	public void startReport(@Optional("NA") String browserType, @Optional("NA") String category) {
		// ExtentReports(String filePath,Boolean replaceExisting)
		// filepath - path of the file, in .htm or .html format - path where your report
		// needs to generate.
		// replaceExisting - Setting to overwrite (TRUE) the existing file or append to
		// it
		// True (default): the file will be replaced with brand new markup, and all
		// existing data will be lost. Use this option to create a brand new report
		// False: existing data will remain, new tests will be appended to the existing
		// report. If the the supplied path does not exist, a new file will be created.
		try {
			// String rptfile=System.getProperty("./test-output/ExtentReport.html");
			// String rptfile=System.getProperty("user.dir")
			// +"\\test-output\\ExtentReport.html";

			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
			System.out.println("Current timestamp: " + timeStamp);

			// String rptfile="";
			// System.out.println("Current directory is"+System.getProperty("user.dir"));
			// String jenvworkspace="${env.WORKSPACE}";
			// System.out.println("Current directory is"+jenvworkspace);
			// String current = System.getProperty("user.dir");
			Thread.sleep(5000);// added by poojitha
			osystem = System.getProperty("os.name");
			System.out.println("Op System: " + osystem);
			if (osystem.equals("Windows 11")) {
				// local run
				String current = System.getProperty("user.dir");
				System.out.println("User directory is: " + current);
				rptfile = current + "/test-output/TestReport/" + "LocalRun" + timeStamp;
				Zfile =current+"/test-output/TestReport/"+"Zip_"+"LatestTestReport"+timeStamp;
			}

			else {
				// Jenkins run test final
			//	String current = System.getenv("Pipeline.Workspace");
				String current = "#{PWD}#";
				System.out.println("Current working directory in Java : " + current);
				rptfile = current + "/test-output/" + "LatestTestReport";
				Zfile =current+"/test-output/"+"Zip_"+"LatestTestReport"+timeStamp;
			}
			File rptpath1 = new File(rptfile);
			if (rptpath1.exists()) {
				if (!rptpath1.isDirectory()) {
					try {
						rptpath1.mkdir();
					} catch (SecurityException se) {
						logger.log(LogStatus.FAIL,
								"Security exception while creating report path.  Please check if the user has valid rights!");
					}
				}
			} else {
				try {
					rptpath1.mkdir();

				} catch (SecurityException se) {
					logger.log(LogStatus.FAIL,
							"Security exception while creating report path.  Please check if the user has valid rights!");
				}
			}
			File rptpath2 = new File(Zfile);
			if (rptpath2.exists()) {
				if (!rptpath2.isDirectory()) {
					try {
						rptpath2.mkdir();
					} catch (SecurityException se) {
						logger.log(LogStatus.FAIL,
								"Security exception while creating report path.  Please check if the user has valid rights!");
					}
				}
			} else {
				try {
					rptpath2.mkdir();

				} catch (SecurityException se) {
					logger.log(LogStatus.FAIL,
							"Security exception while creating report path.  Please check if the user has valid rights!");
				}
			}

			srptpath = createNewScreenPath(rptfile);

			rptpath = rptfile + "/"+"LatestTestReport"+timeStamp+ ".html";
			// checking final changes
			System.out.println("Reportfile is " + rptpath);
			extent = new ExtentReports(rptpath, true);

			extent.addSystemInfo("Host Name", "Clove Platform").addSystemInfo("Environment", "SITs")
					.addSystemInfo("User Name", "ClovePlatformUser").addSystemInfo("Browser under test", browserType);
			// loading the external xml file (i.e., extent-config.xml) which was placed
			// under the base directory
			// You could find the xml file below. Create xml file in your project and copy
			// past the code mentioned below
			// extent.loadConfig(new
			// File(System.getProperty("user.dir"+"\\config\\extent-config241.xml")));
			extent.loadConfig(new File(System.getProperty("./lib/extent-config.xml")));

		} catch (Exception ex) {
			// System.out.println("Error "+ex.getMessage());
		}

		// extent.addSystemInfo("Environment","Environment Name")

	}

	@BeforeMethod
	public void register(Method method) {
		testName = method.getName();
		
		/*if (SmokeTestInfrastructureOnly.classname) {
			logger = extent.startTest(testName);
		}*/


		 if (PasswordComplexity.classname) {
			 logger = extent.startTest(testName);
		 }

		 else if (DataTransferPolicy.classname) {
			 logger = extent.startTest(testName);
		 }
		 else if (Authentication_test.classname) {
			 logger = extent.startTest(testName);
		 }
		 else if (NoticePrivacy.classname) {
			 logger = extent.startTest(testName);
		 }
		 else if (SSLLABS_AWS.classname) {
			 logger = extent.startTest(testName);
		 }
		 else if (SSLLABS_AzureGCP.classname) {
			 logger = extent.startTest(testName);
		 }
		 else if (CookieStorageType.classname) {
			 logger = extent.startTest(testName);
		 }
		 else if (CookieAcceptance.classname) {
			 logger = extent.startTest(testName);
		 }
		 else if (LoggingMonitoring.classname) {
			 logger = extent.startTest(testName);
		 }
		 else if (DataClassification.classname) {
			 logger = extent.startTest(testName);
		 }
		 else if (PurposeLimitation.classname) {
			 logger = extent.startTest(testName);
		 }
		 else if (LegalContractual.classname) {
			 logger = extent.startTest(testName);
		 }
		 else if (roleBasedAccess.classname) {
			 logger = extent.startTest(testName);
		 }
		 else if (SessionExpire.classname) {
			 logger = extent.startTest(testName);
		 }
		 else if (VerifyPasswordEncryption.classname) {
			 logger = extent.startTest(testName);
		 }
		 else if (PasswordHistoryPolicy.classname) {
			 logger = extent.startTest(testName);
		 }
		 else if (TestingMFA.classname) {
			 logger = extent.startTest(testName);
		 }
		 else if (SessionManagement.classname) {
			 logger = extent.startTest(testName);
		 }
		 else if (CrossSite.classname) {
			 logger = extent.startTest(testName);
		 }
		 else if (DataDisposal.classname) {
			 logger = extent.startTest(testName);
		 }
		 else if (DataRetention.classname) {
			 logger = extent.startTest(testName);
		 }
		 else if (DataMasking.classname) {
			 logger = extent.startTest(testName);
		 }
		 else if (SingleSignOn.classname) {
			 logger = extent.startTest(testName);
		 }
		 else if (uniqueness.classname) {
			 logger = extent.startTest(testName);
		 }
		 else if (DataMinimization.classname) {
			 logger = extent.startTest(testName);
		 }
		 else if (ChangePassword.classname) {
			 logger = extent.startTest(testName);
		 }
		 else if (DNSHeaders.classname) {
			 logger = extent.startTest(testName);
		 }
		 else if (PrivilegedAccessManagement.classname) {
			 logger = extent.startTest(testName);
		 }
		 else if (VerifyLogs.classname) {
			 logger = extent.startTest(testName);
		 }
		 else if (DataInTransitEncrypt.classname) {
			 logger = extent.startTest(testName);
		 }
		 else if (LeastPrivilege.classname) {
			 logger = extent.startTest(testName);
		 }
      	 else if (ErrorHandlingInvalidParameter.classname) {
			 logger = extent.startTest(testName);
		 }
	}

	@AfterMethod
	public void getResult(ITestResult result) throws Exception {
		if (result.getStatus() == ITestResult.FAILURE) {
			logger.log(LogStatus.FAIL, "Test Case Failed: " + result.getName());
//			logger.log(LogStatus.FAIL, "Test Case Failed: " + result.getThrowable());
			String screenshotPath = Screenshot.getScreenshot(result.getName(), srptpath);
//			//To add it in the extent report 
           logger.log(LogStatus.INFO,logger.addScreenCapture(screenshotPath));
         
		}
		// else if(result.getStatus() == ITestResult.SKIP){
		// logger.log(LogStatus.SKIP, "Test Case Skipped: "+result.getName());
		// }
		else if (result.getStatus() == ITestResult.SUCCESS) {
			logger.log(LogStatus.PASS, "Test Case Passed: " + result.getName());
			String screenshotPath = Screenshot.getScreenshot(result.getName(),srptpath);
			logger.log(LogStatus.INFO,logger.addScreenCapture(screenshotPath));
		}
		// ending test
		// endTest(logger) : It ends the current test and prepares to create HTML report
		extent.endTest(logger);
	}
//	protected static String captureScreenshot(String filepath) {
//		try {
//
//			Screenhshot.getScreenshot(filepath+".png");
//			logger.log(LogStatus.INFO, "Capturing screenshot:" + filepath +".png");
//			return filepath;
//		} catch (Exception e) {
////			logger.log(LogStatus.INFO, "Failed to capture screenshot. Exception:" + e.getMessage());
//			return filepath;
//		}
//	}
	@AfterTest
	public void endReport() {
		// writing everything to document
		// flush() - to write or update test information to your report.
		extent.flush();
		// Call close() at the very end of your session to clear all resources.
		// If any of your test ended abruptly causing any side-affects (not all logs
		// sent to ExtentReports, information missing), this method will ensure that the
		// test is still appended to the report with a warning message.
		// You should call close() only once, at the very end (in @AfterSuite for
		// example) as it closes the underlying stream.
		// Once this method is called, calling any Extent method will throw an error.
		// close() - To close all the operation
		extent.close();
		ZipFolder.CreateZipFolder(rptfile,Zfile);
	}


	  @AfterClass 
	  public void endDriver() {
		  //ZipFolder.CreateZipFolder(rptfile,Zfile);
	  
		  //WebDriver driver =BrowserConfig.getDriver(); 
		  //driver.quit(); 
		  }
	 

	public static class Verifier extends Assertion {
		// verify and report all assertions
		StringBuilder sb;
		private final Map<AssertionError, IAssert<?>> m_errors = Maps.newLinkedHashMap();

		@Override
		protected void doAssert(IAssert<?> a) {
			onBeforeAssert(a);
			try {
				a.doAssert();
				onAssertSuccess(a);
			} catch (AssertionError ex) {
				onAssertFailure(a, ex);
				m_errors.put(ex, a);
			} finally {
				onAfterAssert(a);
			}
		} // doAssert closes

		public void assertAll() {
			if (!m_errors.isEmpty()) {
				sb = new StringBuilder("The following asserts failed:");
				boolean first = true;
				for (Map.Entry<AssertionError, IAssert<?>> ae : m_errors.entrySet()) {
					if (first) {
						first = false;
					} else {
						// sb.append(System.getProperty("line.separator"));//separator
						sb.append("##");
						// sb.append(System.lineSeparator());
					}
					// sb.append(System.getProperty("line.separator"));
					// sb.append("\n");
					// sb.append(System.lineSeparator());
					System.out.println();
					sb.append(ae.getKey().getMessage());
				} // for closes

				System.out.println("key :" + sb.toString());
				String errorMsg = sb.toString();
				int count = 0;
				int errlength = errorMsg.length();
				int exp1, butFnd1, exp2;
				String output1, output2;
				count = (errlength - errorMsg.replace("expected", "").length()) / 8;
				logger.log(LogStatus.INFO, "No: of errors = " + count);
				// System.out.println("count :"+count);
				exp1 = 0;
				butFnd1 = 0;

				for (int i = 0; i < count; i++) {

					exp1 = errorMsg.indexOf("expected", exp1) + 1;
					butFnd1 = errorMsg.indexOf("but found", butFnd1) + 1;
					exp2 = errorMsg.indexOf("expected", exp1 + 1);

					/*
					 * System.out.println("exp1 :"+exp1); System.out.println("butFnd1 :"+butFnd1);
					 * System.out.println("exp2 :"+exp2);
					 */

					output1 = errorMsg.substring(exp1 + 8, butFnd1 - 1);
					// System.out.println("output1 :"+output1);

					if (exp2 > 0) {
						output2 = errorMsg.substring(butFnd1 + 9, exp2 - 1);
						// System.out.println("output2 :"+output2);
					} else {
						output2 = errorMsg.substring(butFnd1 + 9);
						// System.out.println("output2 :"+output2);
					}
					logger.log(LogStatus.FAIL, "Found" + output2 + "instead of " + output1);
					// System.out.println("Error Reported:"+ "Found "+ output2 +"instead of "+
					// output1);
				} // for closes
					// }//if closes
				throw new AssertionError(sb.toString());
			} // outer if () closes
		}// assertAll() closes
	}// subclass closes
	public static String createNewScreenPath(String rptfile) {

		File srptpath = new File(rptfile);
		if (srptpath.exists()) {
			if (!srptpath.isDirectory()) {
				try {
					srptpath.mkdir();
					return rptfile ;
				} catch (SecurityException se) {
					logger.log(LogStatus.FAIL,
							"Security exception while creating report path.  Please check if the user has valid rights!");
				}
			}
		} else {
			try {
				srptpath.mkdir();
				return rptfile;
			} catch (SecurityException se) {
				logger.log(LogStatus.FAIL,
						"Security exception while creating report path.  Please check if the user has valid rights!");
			}
		}
		return rptfile;

	}
}// testBase class closes
