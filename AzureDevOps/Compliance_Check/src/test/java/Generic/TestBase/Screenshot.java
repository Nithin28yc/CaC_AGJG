package Generic.TestBase;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshot extends testBase{
	static int sequence = 1;
	static WebDriver driver=BrowserConfig.getDriver();

//		public static String getScreenshot(String filename) {
//			
//			try {
//	//			System.out.println("getscreenshot filepath"+filename);
//				File screenShot = (File)((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//				BufferedImage bufferedImage = ImageIO.read(screenShot);
//				try {
//					filename = filename.replace(".png", "_" +sequence+ ".png");
//					File f = new File(filename);
//					// if(f.exists()){
//					// f.deleteOnExit();
//					// }
//					ImageIO.write(bufferedImage, "png", f);
//					sequence = sequence + 1;
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			} catch (Exception e) {
//			}
//			return filename;
//		}
	public static String getScreenshot(String screenshotName,String srpth) throws Exception {
		//below line is just to append the date format with the screenshot name to avoid duplicate names		
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
//		osystem = System.getProperty("os.name");
//		System.out.println("Op System: " + osystem);
		String destination=null;
//		if (osystem.equals("Windows 10")) {
//			// local run
//			String current = System.getProperty("user.dir");
//			System.out.println("User directory is: " + current);
			 destination =srpth+"/"+screenshotName+dateName+".png";
			//ssfile = current + "/test-output/" + "LocalRun" + timeStamp + browserType + "report.html";
		//}


//		else {
//			// Jenkins run test final
//			String current = System.getenv("WORKSPACE");
//			System.out.println("Current working directory in Java : " + current);
//			 destination =current + screenshotName+dateName+".png";
//
//		}

//		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
//		TakesScreenshot ts = (TakesScreenshot) driver;
//		File source = ts.getScreenshotAs(OutputType.FILE);
		//after execution, you could see a folder "FailedTestsScreenshots" under src folder
		//String destination =  "./Screenshot/screen"+screenshotName+dateName+".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		//Returns the captured file path
		return destination;
	}
}