package compliance.PageObjMethods;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import Generic.TestBase.BrowserConfig;
import Generic.TestBase.CommonMethods;
import Generic.TestBase.testBase;
import freemarker.log.Logger;

public class Legal_Contractual extends testBase{

	private WebDriver driver = BrowserConfig.getDriver();
	WebDriverWait wait = new WebDriverWait(driver, 40);
	JavascriptExecutor jse = (JavascriptExecutor) driver;
	CommonMethods commonMethods = PageFactory.initElements(driver, CommonMethods.class);

	@FindBy(xpath = "//*[@id=\"root\"]/div/div/footer/div")
	public WebElement terms;


	public boolean termsOfUse() {

		try {
			wait.until(ExpectedConditions.visibilityOf(terms));
			if(terms.getText().contains("Terms of Use")) {

				logger.log(LogStatus.INFO, "Clicking on Terms of Use");
				System.out.println("Clicking on Terms of Use");
				//				terms.click();
				return true;
			}

			logger.log(LogStatus.INFO, "Dispaly information for Terms of Use");
			System.out.println("Dispaly information for Terms of Use");
			commonMethods.waitForPageToLoad();
			return true;


		}
		catch (Exception e){
			System.out.println(e);
			logger.log(LogStatus.FAIL, "Terms of Use is not there");
			System.out.println("Terms of Use is not there");
			return false;
		}
	}
}
