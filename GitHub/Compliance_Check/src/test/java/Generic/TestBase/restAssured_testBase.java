
/* testing with 
 * has POM without page factory
 * has Test NG parameters
 * has Test listeners, Reporting, logs,  screenshots
 * has Excel config, parameterisation with apache POI
 * has different browser set up statements
 * 
 * no Maven,  GIT, log4j, PageObjFactory
 */

package Generic.TestBase;
//test
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
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

import compliance.Functional.DataInTransitEncrypt;
import compliance.PageObjMethods.DataInTransit_Encrypt;


//adding crossbrowser change with webhook
public class restAssured_testBase implements ISuiteListener  {
	WebDriver driver;
	public static ExtentReports extent;
	public static ExtentTest logger;
	public static String[] toolsToTest=null;
	public static String[] ipstoTest=null;
	public static String[] cartridgesToTest=null;
	public String selectedTools;
	public static String platformType;
	public static String username;
	public static String password;
	public static String ldappassword;
	public static String jobName;
	public static String osystem;
	public String rptfile;
	private static String innersourceusername;
	private static String innersourcepassword;
	private static String appUrl;
	private static String innersourceURL;
	private static String browserType;
	public static String cartridge;
	public static String buildid;
	public static String projectid;
	public static String entity;
	public static String clientid;
	private static String commitmsg;
	public static String apiurl;
	
//Initial Prerequisites for any test - Browser config and LoadExcel	by receiving the parameters from cmd line
	@Override
    public void onStart(ISuite suite)
	{
       /* Map<String, String> parameters = suite.getXmlSuite().getParameters();
        for (Map.Entry<String, String> parameter : parameters.entrySet()) 
        {
            String appURL = System.getenv(parameter.getKey());
            
            
            if (appURL != null && !appURL.trim().isEmpty()) 
            	parameter.setValue(appURL);
                          	
        }*/
    }

    @Override
    public void onFinish(ISuite suite) {

    }

    @BeforeClass
   	@Parameters({"browserType","username","password","appUrl","cartridge","commitmsg","clientid","projectid","entity","apiurl"})
   	public void setAuthentication(@Optional("NA") String browserType,@Optional("NA") String username,@Optional("NA") String password,@Optional("NA") String appUrl,@Optional("NA") String cartridge,@Optional("NA") String commitmsg,@Optional("NA") String clientid,@Optional("NA") String projectid,@Optional("NA") String apiurl) throws MalformedURLException, InterruptedException
   	{
    	restAssured_testBase.browserType=browserType;
   		 restAssured_testBase.username=username;
   		 restAssured_testBase.password=password;
   		 restAssured_testBase.cartridge=cartridge;
   		 restAssured_testBase.commitmsg=commitmsg;
   		 restAssured_testBase.clientid=clientid;
   		restAssured_testBase.appUrl=appUrl;
   	
   		restAssured_testBase.projectid=projectid;
   		restAssured_testBase.entity=entity;
   		restAssured_testBase.apiurl=apiurl;
   		
   		if(!appUrl.equals("NA"))
			{
				System.out.println("AppURL value received in initialize function is "+appUrl);	
				//ExcelOp.loadExcel();
				BrowserConfig.setDriver(browserType, appUrl);
				
				
			}		
   		
   		
   	}
   	
   	public static String getUsername()
   	{
   		return username;
   	}
   	public static String getPassword()
   	{
   		return password;
   	}
   	
   	public static String getprojectid()
   	{
   		return projectid;
   	}
   	
   	public static String getentity()
   	{
   		return entity;
   	}
   	
   	public static String getAPPURL()
   	{
   		return appUrl;
   	}
   	
   	public static String getcartridge()
   	{
   		return cartridge;
   	}
	public static String getbuildid()
   	{
		
   		return buildid;
   	}
	public static String getcommitmsg()
   	{
   		return commitmsg;
   	}
	public static String getclientid()
   	{
   		return clientid;
   	}
   	
	public static String getapiurl()
   	{
   		return apiurl;
   	}
   	
   	@DataProvider(name="getTestDataAnalytics")
   	public Object[][] getLdapTestData(Method m) throws Exception
   	{
   	if (m.getName().equalsIgnoreCase("codeCommitFlow"))
   	{
   	Object[][] testObjArray=ExcelOp.getTableArray("codecommit");
   	return testObjArray;
   	}	
   	else
   		return null;
   	}	
   	@Parameters({"browserType", "innersourceURL", "innersourceusername","innersourcepassword"})	
       @BeforeClass
   		
   			public void initialize(String browserType, @Optional("NA") String innersourceURL, @Optional("NA") String innersourceusername,@Optional("NA") String innersourcepassword) throws IOException, InterruptedException

   				{
   		      restAssured_testBase.innersourceusername=innersourceusername;
   		      restAssured_testBase.innersourcepassword=innersourcepassword;
   		      restAssured_testBase.innersourceURL=innersourceURL;
   		      restAssured_testBase.browserType=browserType;
   					
   			    	if(!innersourceURL.equals("NA"))
   					{
   						System.out.println("AppURL value received in initialize function is "+innersourceURL);	
   						//ExcelOp.loadExcel();
   						BrowserConfig.setDriver(browserType, innersourceURL);
   						
   						
   					}			
   					
   				}
   	public static String getInnersourceUserName()
   	{
   		return innersourceusername;
   	}
   	public static String getInnersourcePassword()
   	{
   		return innersourcepassword;
   	}
   	public static String getInnersourceURL()
   	{
   		return innersourceURL;
   	}
   	public static String getBrowser()
   	{
   		return browserType;
   	}
	
       				
	
	@DataProvider(name="getTestData")
	public Object[][] getTestData(Method m) throws Exception
	{
		if (m.getName().equalsIgnoreCase("enterUser"))
		{
		Object[][] testObjArray=ExcelOp.getTableArray("Ldap");
		return testObjArray;
		}
		
		
		//RC Gen Bot Intents Class	
				if (m.getName().equalsIgnoreCase("verifyGreetIntents"))
				{
				Object[][] testObjArray=ExcelOp.getTableArray("GreetIntents");
				return testObjArray;
				}
				if (m.getName().equalsIgnoreCase("verifyCountjenkinsjobIntents"))
				{
				Object[][] testObjArray=ExcelOp.getTableArray("Count jenkinsjobs");
				return testObjArray;
				}
				if (m.getName().equalsIgnoreCase("verifyTriggerJenkinsBuildIntents"))
				{
				Object[][] testObjArray=ExcelOp.getTableArray("TriggerJenkinsBuild");
				return testObjArray;
				}
				
				if (m.getName().equalsIgnoreCase("verifyFetchBuildInfoIntents"))
				{
				Object[][] testObjArray=ExcelOp.getTableArray("FetchBuildInfo");
				return testObjArray;
				}
				
				
				if (m.getName().equalsIgnoreCase("verifyRunningKubernetespodIntents"))
				{
				Object[][] testObjArray=ExcelOp.getTableArray("Running Kubernetes pods");
				return testObjArray;
				}
				if (m.getName().equalsIgnoreCase("verifyClusterInfoIntents"))
				{
				Object[][] testObjArray=ExcelOp.getTableArray("ClusterInfo");
				return testObjArray;
				}
				
				if (m.getName().equalsIgnoreCase("verifyScaleUpIntents"))
				{
				Object[][] testObjArray=ExcelOp.getTableArray("ScaleUp");
				return testObjArray;
				}
				if (m.getName().equalsIgnoreCase("verifyScaleDownIntents"))
				{
				Object[][] testObjArray=ExcelOp.getTableArray("ScaleDown");
				return testObjArray;
				}
				
				if(m.getName().equalsIgnoreCase("verifyCreateBranchIntents")) {
					Object[][] testObjArray=ExcelOp.getTableArray("CreateBranch");
					return testObjArray;
				}
				
				if(m.getName().equalsIgnoreCase("verifyListBranchIntents")) {
					Object[][] testObjArray=ExcelOp.getTableArray("ListBranch");
					return testObjArray;
				}
				
				if(m.getName().equalsIgnoreCase("verifyCountBranchIntents")) {
					Object[][] testObjArray=ExcelOp.getTableArray("CountBranch");
					return testObjArray;
				}
				
				if(m.getName().equalsIgnoreCase("verifyCollaboratorsIntents")) {
					Object[][] testObjArray=ExcelOp.getTableArray("Collaborators");
					return testObjArray;
				}
				
				if(m.getName().equalsIgnoreCase("verifyRCBotInvalidIntents")) {
					Object[][] testObjArray=ExcelOp.getTableArray("RCBotInvalidIntents");
					return testObjArray;
				}
				
				
				
				
				if(m.getName().equalsIgnoreCase("verifyGoodByeIntents")) {
					Object[][] testObjArray=ExcelOp.getTableArray("Gudbye Intents");
					return testObjArray;
				}
				
				
				
				
				
				if(m.getName().equalsIgnoreCase("verifyJiraIntents_CreateIssue")) {
					Object[][] testObjArray=ExcelOp.getTableArray("Jira Intents_Create issue");
					return testObjArray;
				}
				
				
				
				
				
				if(m.getName().equalsIgnoreCase("verifyJiraIntents_AssignIssue")) {
					Object[][] testObjArray=ExcelOp.getTableArray("Jira Intents_Assign issue");
					return testObjArray;
				}
				
				
				
				
				if(m.getName().equalsIgnoreCase("verifyJiraIntents_SearchIssues")) {
					Object[][] testObjArray=ExcelOp.getTableArray("Jira Intents_Search Issues");
					return testObjArray;
				}
				
				
				if(m.getName().equalsIgnoreCase("verifyJiraIntents_UpdateDescription")) {
					Object[][] testObjArray=ExcelOp.getTableArray("Jira Intents_Update description");
					return testObjArray;
				}
				
				
				
				
				
				if(m.getName().equalsIgnoreCase("verifyGitIntents_LatestCommit")) {
					Object[][] testObjArray=ExcelOp.getTableArray("GIT Intents_Latest Commit");
					return testObjArray;
				}
				
				if(m.getName().equalsIgnoreCase("verifyGitIntents_PullRequest")) {
					Object[][] testObjArray=ExcelOp.getTableArray("GIT Intents_pull request");
					return testObjArray;
				}
				
				
				if(m.getName().equalsIgnoreCase("verifyGitIntents_Merge")) {
					Object[][] testObjArray=ExcelOp.getTableArray("GIT Intents_Merge");
					return testObjArray;
				}
				
				
				
				
				
				if(m.getName().equalsIgnoreCase("verifySonarIntents")) {
					Object[][] testObjArray=ExcelOp.getTableArray("Sonar Intents");
					return testObjArray;
				}
				
				
				if(m.getName().equalsIgnoreCase("verifyMongoDBIntents")) {
					Object[][] testObjArray=ExcelOp.getTableArray("MongoDB Intents");
					return testObjArray;
				}
				
				
				
				
				
				
				
		if (m.getName().equalsIgnoreCase("VerifyRocketchatInvalidLogin"))
			{
			Object[][] testObjArray=ExcelOp.getTableArray("RCGenInvalidLogin");
			return testObjArray;
			}		
		
		if (m.getName().equalsIgnoreCase("VerifyRocketchatValidLogin"))
			{
			Object[][] testObjArray=ExcelOp.getTableArray("RCGenValidLogin");
			return testObjArray;
			}
		
		if (m.getName().equalsIgnoreCase("RCGenBotIntents")|| m.getName().equalsIgnoreCase("RCBotIntents"))
			{
			Object[][] testObjArray=ExcelOp.getTableArray("RCBotValidIntents");
			return testObjArray;
			}
		
		if (m.getName().equalsIgnoreCase("RCGenvalidJiraIntents"))
		{
		Object[][] testObjArray=ExcelOp.getTableArray("RCBotValidJiraIntents");
		return testObjArray;
		}
		if (m.getName().equalsIgnoreCase("RCGenvalidGitIntents"))
		{
		Object[][] testObjArray=ExcelOp.getTableArray("RCBotValidGitIntents");
		return testObjArray;
		}
		if (m.getName().equalsIgnoreCase("RCGenvalidJenkinsIntents"))
		{
		Object[][] testObjArray=ExcelOp.getTableArray("RCBotValidJenkinsIntents");
		return testObjArray;
		}
		if (m.getName().equalsIgnoreCase("RCGenvalidKubernetesIntents"))
		{
		Object[][] testObjArray=ExcelOp.getTableArray("RCBotValidKubernetsIntents");
		return testObjArray;
		}
		if (m.getName().equalsIgnoreCase("RCGenValidMongoDbSearchIntents"))
		{
		Object[][] testObjArray=ExcelOp.getTableArray("RCBotValidMongoDbSearchIntents");
		return testObjArray;
		}
		
		if (m.getName().equalsIgnoreCase("RCGenValidClusterInfoIntents"))
		{
		Object[][] testObjArray=ExcelOp.getTableArray("RCBotValidClusterInfoIntents");
		return testObjArray;
		}
		if (m.getName().equalsIgnoreCase("RCGenBotInvalidIntents"))
		{
		Object[][] testObjArray=ExcelOp.getTableArray("RCBotInvalidIntents");
		return testObjArray;
		}
		
		
		//malli edits
		if (m.getName().equalsIgnoreCase("allIntentsCheck"))
		{
		Object[][] testObjArray=ExcelOp.getTableArray("RegressionIntents");
		return testObjArray;
		}
		
		
		
		
		if(m.getName().equalsIgnoreCase("RCBotValidLogin")|| m.getName().equalsIgnoreCase("RCBotLogin"))
			{
			Object[][] testObjArray=ExcelOp.getTableArray("RCBotValidLogin");
			return testObjArray;
			}
		
		else if(m.getName().equalsIgnoreCase("testMethodRCBotValidIntents"))
			{
			Object[][] testObjArray=ExcelOp.getTableArray("RCBotValidIntents");
			return testObjArray;
			}
		
	   if(m.getName().equalsIgnoreCase("eclipseCheValidLogin")|| m.getName().equalsIgnoreCase("eclipseChebranchcommit"))
		{
		Object[][] testObjArray=ExcelOp.getTableArray("EclipseCheValidLogin");
		return testObjArray;
		}
		if (m.getName().equalsIgnoreCase("jiraLogsCheck")|| m.getName().equalsIgnoreCase("jiraLogsCheckThroughSearchOption")||m.getName().equalsIgnoreCase("eclipseCheUrlthroughLinkFromGenChan"))
		{
		Object[][] testObjArray=ExcelOp.getTableArray("JiraCreation");
		return testObjArray;
		}
		if (m.getName().equalsIgnoreCase("LDAPUserCreation"))
		{
		Object[][] testObjArray=ExcelOp.getTableArray("LDAPLogin");
		return testObjArray;
		}
		if (m.getName().equalsIgnoreCase("invoketransaction"))
		{
		Object[][] testObjArray=ExcelOp.getTableArray("BlockChainNodeLogin");
		return testObjArray;
		}
		else
			return null;
	}
		  	
    
			
  //Initial Prerequisites for any test - Start report, capture report and flush report			
    	@Parameters({"browserType"})
    	@BeforeTest
			public void startReport(String browserType){
				//ExtentReports(String filePath,Boolean replaceExisting) 
				//filepath - path of the file, in .htm or .html format - path where your report needs to generate. 
				//replaceExisting - Setting to overwrite (TRUE) the existing file or append to it
				//True (default): the file will be replaced with brand new markup, and all existing data will be lost. Use this option to create a brand new report
				//False: existing data will remain, new tests will be appended to the existing report. If the the supplied path does not exist, a new file will be created.
				try {
					//String rptfile=System.getProperty("./test-output/ExtentReport.html");	
					//String rptfile=System.getProperty("user.dir") +"\\test-output\\ExtentReport.html";
					
					String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
					System.out.println("Current timestamp: "+timeStamp);
					
					//String rptfile="";
					//System.out.println("Current directory is"+System.getProperty("user.dir"));
					//String jenvworkspace="${env.WORKSPACE}";
					//System.out.println("Current directory is"+jenvworkspace);
					//String current = System.getProperty("user.dir");
					
					osystem=System.getProperty("os.name");
					System.out.println("Op System: "+osystem);
			        
						String current = System.getProperty("user.dir");
						System.out.println("User directory is: "+current);
				        rptfile=current+"/test-output/"+"LocalRun"+timeStamp+browserType+"report"+".html";
			        			
			        						
					//checking final changes			
					System.out.println("Reportfile is "+rptfile);
					extent = new ExtentReports (rptfile,true );
					
					extent
		            .addSystemInfo("Host Name", "Clove Platform")
		            .addSystemInfo("Environment", "SITs")
		            .addSystemInfo("User Name", "ClovePlatformUser")
					.addSystemInfo("Browser under test", browserType);
		            //loading the external xml file (i.e., extent-config.xml) which was placed under the base directory
		            //You could find the xml file below. Create xml file in your project and copy past the code mentioned below
		            //extent.loadConfig(new File(System.getProperty("user.dir"+"\\config\\extent-config241.xml")));
					extent.loadConfig(new File(System.getProperty("./lib/extent-config.xml")));
					
				}
				catch(Exception ex)
				{
				//	System.out.println("Error "+ex.getMessage());
				}
				
				//extent.addSystemInfo("Environment","Environment Name")				
			}
			
			@BeforeMethod
			public void register(Method method)
			{
				String testName=method.getName();
				
				if(DataInTransitEncrypt.classname) {
				logger=extent.startTest(testName);
				}
				
			}			
			
			@AfterMethod
			public void getResult(ITestResult result)
			{
				if(result.getStatus() == ITestResult.FAILURE){
					logger.log(LogStatus.FAIL, "Test Case Failed: "+result.getName());
					logger.log(LogStatus.FAIL, "Test Case Failed: "+result.getThrowable());
				}else if(result.getStatus() == ITestResult.SKIP){
					logger.log(LogStatus.SKIP, "Test Case Skipped: "+result.getName());
				}else if(result.getStatus()==ITestResult.SUCCESS) {
					logger.log(LogStatus.PASS, "Test Case Passed: "+result.getName());
				}
				// ending test
				//endTest(logger) : It ends the current test and prepares to create HTML report
				extent.endTest(logger);
			}
			@AfterTest
			public void endReport(){
				// writing everything to document
				//flush() - to write or update test information to your report. 
		                extent.flush();
		                //Call close() at the very end of your session to clear all resources. 
		                //If any of your test ended abruptly causing any side-affects (not all logs sent to ExtentReports, information missing), this method will ensure that the test is still appended to the report with a warning message.
		                //You should call close() only once, at the very end (in @AfterSuite for example) as it closes the underlying stream. 
		                //Once this method is called, calling any Extent method will throw an error.
		                //close() - To close all the operation
		                extent.close();   
		               
		    }
			
			@AfterClass
			public void endDriver()
			{
				 WebDriver driver = Generic.TestBase.BrowserConfig.getDriver();
	             driver.quit();
			}
			
			/*@Test
			@Parameters({"platformType"})
			public void PostProvisionSanityTest(String platformType) throws InterruptedException
			//to be included and is applicable only for Post provision sanity test
			{
				System.out.println("Entered sanity root");
				System.out.println("Test to be run is for "+platformType+" platform");
				
				switch(platformType)
				{
				case "Microservice": 
					
					System.out.println("Testing "+platformType+" platform");
					PostProvisionSanityCartridgeMicroservice testMicroservice = new  PostProvisionSanityCartridgeMicroservice();
					testMicroservice.MicroserviceTest_Jenkins();
					testMicroservice.MicroserviceTest_Kibana();
					testMicroservice.MicroserviceTest_LDAP();
					testMicroservice.MicroserviceTest_Nexus();
					testMicroservice.MicroserviceTest_Rocketchat();
					testMicroservice.MicroserviceTest_Selenium();
					testMicroservice.MicroserviceTest_Sonar();
					testMicroservice.MicroserviceTest_Stackstorm();
					
					break;
					
				case "Selectedtools": 
					System.out.println("Testing "+platformType+" platform");
					PostProvisionSanitySelectedTools testSelectedtools = new  PostProvisionSanitySelectedTools();
					testSelectedtools.selectedToolsTest();
					break;
					
				case "Defaulttools": 
					System.out.println("Testing "+platformType+" platform");
					PostProvisionSanityDefault testDefaulttools = new  PostProvisionSanityDefault();
					testDefaulttools.DefaultToolsTest_Jenkins();
					testDefaulttools.DefaultToolsTest_LDAP();
					
					break;
					
				case "Blockchain": 
					System.out.println("Testing "+platformType+" platform");
					PostProvisionSanityBlockChain testBlockchain = new  PostProvisionSanityBlockChain();
					testBlockchain.invoketransaction();
					break;
					
				}
				
			}*/
			
			 public static class Verifier extends Assertion {
		//verify and report all assertions 
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
		           } //doAssert closes
		   
		   public void assertAll() {
               if (! m_errors.isEmpty()) 
               {
                     sb = new StringBuilder("The following asserts failed:");
                     boolean first = true;
                     for (Map.Entry<AssertionError, IAssert<?>> ae : m_errors.entrySet()) {
                            if (first) {
                                  first = false;
                            } else {
                                  //sb.append(System.getProperty("line.separator"));//separator
                                  sb.append("##");
                                  //sb.append(System.lineSeparator());
                            }
                            //sb.append(System.getProperty("line.separator"));
                            //sb.append("\n");
                            //sb.append(System.lineSeparator());
                            System.out.println();
                            sb.append(ae.getKey().getMessage());
                     }// for closes
                     
                               
                 System.out.println("key :"+sb.toString());
                 String errorMsg=sb.toString();
	             int count=0;
	             int errlength = errorMsg.length();
	             int exp1,butFnd1,exp2;
	             String output1,output2;
		         count = (errlength - errorMsg.replace("expected","").length())/8;
		         logger.log(LogStatus.INFO, "No: of errors = "+count);
		         //System.out.println("count :"+count);   
		         exp1=0;
		         butFnd1=0;    
		          
		         for(int i=0;i<count;i++)
		                  {
		                         
		                         exp1 =errorMsg.indexOf("expected",exp1)+1;
		                         butFnd1=errorMsg.indexOf("but found",butFnd1)+1;
		                         exp2 =errorMsg.indexOf("expected",exp1+1);
		                         
		                        /* System.out.println("exp1 :"+exp1); 
		                         System.out.println("butFnd1 :"+butFnd1); 
		                         System.out.println("exp2 :"+exp2); */
		
		                         output1 =errorMsg.substring(exp1+8, butFnd1-1);
		                        // System.out.println("output1 :"+output1); 
		                         
		                         if(exp2>0)
		                         	  {
		                               output2 =errorMsg.substring(butFnd1+9,exp2-1);
		                        //       System.out.println("output2 :"+output2); 
		                              }
		                         else {  
		                         output2 =errorMsg.substring(butFnd1+9);
		                         //System.out.println("output2 :"+output2); 
		                              }
		                         logger.log(LogStatus.FAIL, "Found"+ output2 + "instead of "+output1);
		                        // System.out.println("Error Reported:"+ "Found "+ output2 +"instead of "+ output1);
		                  }//for closes
		           // }//if closes 
		          throw new AssertionError(sb.toString());
           }//outer if () closes
   		}//assertAll() closes
	}//subclass closes
}//testBase class closes
                                  
