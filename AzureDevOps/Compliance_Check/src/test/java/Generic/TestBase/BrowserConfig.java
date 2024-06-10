package Generic.TestBase;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Driver;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
//adding crossbrowser change, testng name 
public class BrowserConfig extends testBase{
	public static WebDriver driver;
	
	static String driverPath = "./Drivers_Jars";
	
	
	
	//Public function called by every page object to get driver post configuring browser executable and launching the URL passed through testng.xml
	public static WebDriver getDriver() 
	{
		return driver;
	}
	
	//Private function to set browser executable and to launch URL based on browsertype and URL passed through testng.xml
	public static void setDriver(String browserType, String appURL) throws MalformedURLException, InterruptedException 
	{
		switch (browserType) 
		{
		case "chrome":
			
			  if(osystem.equals("Windows 11"))
			{
				//local run
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--remote-allow-origins=*");
				options.addArguments("--disable-notifications");
				System.out.println("testing chrome locally");
				System.out.println("Launching google chrome..");
				
				System.setProperty("webdriver.chrome.driver", driverPath+"/chromedriver.exe");
				
				driver = new ChromeDriver(options);
				driver.manage().window().maximize();
				driver.navigate().to(appURL);
				break;
				
			}
			
		else
			{
              	//  ChromeOptions options = new ChromeOptions();
				//  options.setAcceptInsecureCerts(true);
				//  options.setCapability("build", "Testing Chrome Options [Selenium 4]");
                //  options.setCapability("name", "Testing Chrome Options [Selenium 4]");
                //  options.setCapability("browserName", "chrome");
				 ChromeOptions options = new ChromeOptions();
				 options. addArguments("--headless");
				 options.setCapability("browserName", "chrome");
				 options.setCapability("platformName", "LINUX");
				 options.setCapability("gsg:customcap", true);
				 driver = new ChromeDriver(options);
				 
					
				 System.out.println("RemoteWebdriver set test chrome");
				 System.out.println("Running the selenium scripts in headless mode");
					 try {
                            driver = new RemoteWebDriver(new URL("http://localhost:4444"), options);
    			//			driver = new RemoteWebDriver(new URL("http://172.21.0.4:4444"), options);
							driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
							driver.manage().window().maximize();
                         } catch (MalformedURLException e) {
                                 System.out.println("Invalid grid URL");
                                  }
								  driver.navigate().to(appURL);
								  break;

					
			}
		   
			
			
		case "firefox":	
			  if(osystem.equals("Windows 10"))
			 {
				//local run
					System.out.println("Launching mozilla firefox..");
					System.setProperty("webdriver.gecko.driver", driverPath+"/geckodriver.exe");
					driver=new FirefoxDriver();
					driver.manage().window().maximize();
					driver.navigate().to(appURL); 
					break;
			 }
			 
			 else
			 {
				//Jenkins run
					FirefoxProfile firefoxProfile = new FirefoxProfile();			
					firefoxProfile.setAcceptUntrustedCertificates(true);
					firefoxProfile.setPreference("layers.acceleration.disabled", true);
			//		DesiredCapabilities desiredCapabilities = DesiredCapabilities.firefox();
			//		desiredCapabilities.setCapability(FirefoxDriver.PROFILE, firefoxProfile);
			//		driver = new RemoteWebDriver(new URL("http://selenium-hub:4444"), desiredCapabilities);
					System.out.println("launching firefox driver");
					System.out.println("RemoteWebdriver set");
					//driver.manage().window().maximize();
					driver.navigate().to(appURL);
					break;
					 
			 }
			
			
		default:
			  if(osystem.equals("Windows 10"))
			{
				//local run
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-notifications");
				System.out.println("testing chrome locally");
				System.out.println("Launching google chrome..");
				System.setProperty("webdriver.chrome.driver", driverPath+"/chromedriver.exe");
				driver=new ChromeDriver(options);
				driver.manage().window().maximize();
				driver.navigate().to(appURL);
				break;
				
			}
			
			else
			{
              	 ChromeOptions options = new ChromeOptions();
				 options.setAcceptInsecureCerts(true);
				 options.setCapability("build", "Testing Chrome Options [Selenium 4]");
                 options.setCapability("name", "Testing Chrome Options [Selenium 4]");
                 options.setCapability("browserName", "chrome");
                
				 System.out.println("RemoteWebdriver set test");
					 try {
                            
							driver = new RemoteWebDriver(new URL("http://selenium-hub:4444"), options);
			//				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
							driver.manage().window().maximize();
                         } catch (MalformedURLException e) {
                                 System.out.println("Invalid grid URL");
                                  }
								  driver.navigate().to(appURL);
								  break;

					
			}
		   
			
			
		}
	}



}