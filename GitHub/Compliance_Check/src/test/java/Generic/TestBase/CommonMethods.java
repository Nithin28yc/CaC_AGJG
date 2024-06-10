package Generic.TestBase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.LogStatus;

public class CommonMethods extends testBase {

	WebDriver driver = BrowserConfig.getDriver();
	WebDriverWait wait = new WebDriverWait(driver, 120);

	public void ClickByTagClass(String tag, String element) throws InterruptedException {
		try {
			wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//" + tag + "[@class='" + element + "']")));
			driver.findElement(By.xpath("//" + tag + "[@class='" + element + "']")).click();
		} catch (Exception e) {
			try {
				WebElement element2 = driver.findElement(By.xpath("//" + tag + "[@class='" + element + "']"));
				JavascriptExecutor executor2 = (JavascriptExecutor) driver;
				executor2.executeScript("arguments[0].click();", element2);
			} catch (Exception e1) {
				System.out.println("Exception" + e);
			}
		}

	}

	public void ClickByTagWithText(String tag, String element) throws InterruptedException {
		try {
			wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//" + tag + "[text()='" + element + "']")));
			driver.findElement(By.xpath("//" + tag + "[text()='" + element + "']")).click();
		} catch (Exception e) {
			WebElement element1 = driver.findElement(By.xpath("//" + tag + "[text()='" + element + "']"));
			JavascriptExecutor executor1 = (JavascriptExecutor) driver;
			executor1.executeScript("arguments[0].click();", element1);
		}

	}

	public boolean VerifyElementDisplayed(String tag, String element) throws InterruptedException {
		try {
			driver.findElement(By.xpath("//" + tag + "[text()='" + element + "']")).isDisplayed();
			return true;

		} catch (Exception e) {
			try {
				wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//" + tag + "[text()='" + element + "']")));
				return true;
			} catch (Exception e1) {
				return false;
			}
		}

	}

	public void ClickOnButtonByText(String element) throws InterruptedException {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='" + element + "']")));
			driver.findElement(By.xpath("//button[text()='" + element + "']")).click();
		} catch (Exception e) {
			WebElement element1 = driver.findElement(By.xpath("//button[text()='" + element + "']"));
			JavascriptExecutor executor1 = (JavascriptExecutor) driver;
			executor1.executeScript("arguments[0].click();", element1);
		}

	}

	public void ClickOnLinkText(String element) {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='" + element + "']")));
			driver.findElement(By.xpath("//a[text()='" + element + "']")).click();
		} catch (Exception e) {
			WebElement element2 = driver.findElement(By.xpath("//a[text()='" + element + "']"));
			JavascriptExecutor executor2 = (JavascriptExecutor) driver;
			executor2.executeScript("arguments[0].click();", element2);
		}
	}

	public void ClickOnLinkContainingText(String element) {
		try {
			wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'" + element + "')]")));
			driver.findElement(By.xpath("//a[text()='" + element + "']")).click();
		} catch (Exception e) {
			WebElement element2 = driver.findElement(By.xpath("//a[contains(text(),'" + element + "')]"));
			JavascriptExecutor executor2 = (JavascriptExecutor) driver;
			executor2.executeScript("arguments[0].click();", element2);
		}
	}

	public void setInputById(String element, String data) throws InterruptedException {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='" + element + "']")));
			driver.findElement(By.xpath("//input[@id='" + element + "']")).sendKeys(data);
		} catch (Exception e) {
			WebElement element2 = driver.findElement(By.xpath("//input[@id='" + element + "']"));
			JavascriptExecutor executor2 = (JavascriptExecutor) driver;
			executor2.executeScript("arguments[0].value='" + data + "';", element2);
		}
	}

	public void setInputByType(String element, String data) throws InterruptedException {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='" + element + "']")));
			driver.findElement(By.xpath("//input[@type='" + element + "']")).sendKeys(data);
			;
		} catch (Exception e) {
			WebElement element2 = driver.findElement(By.xpath("//input[@type='" + element + "']"));
			JavascriptExecutor executor2 = (JavascriptExecutor) driver;
			executor2.executeScript("arguments[0].value='" + data + "';", element2);
		}
	}

	public void setInputByClass(String element, String data) throws InterruptedException {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='" + element + "']")));
			driver.findElement(By.xpath("//input[@class='" + element + "']")).sendKeys(data);
			;
		} catch (Exception e) {
			WebElement element2 = driver.findElement(By.xpath("//input[@class='" + element + "']"));
			JavascriptExecutor executor2 = (JavascriptExecutor) driver;
			executor2.executeScript("arguments[0].value='" + data + "';", element2);
		}
	}

	public void ClickInputByType(String element) throws InterruptedException {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='" + element + "']")));
			driver.findElement(By.xpath("//input[@type='" + element + "']")).click();
		} catch (Exception e) {
			WebElement element2 = driver.findElement(By.xpath("//input[@type='" + element + "']"));
			JavascriptExecutor executor2 = (JavascriptExecutor) driver;
			executor2.executeScript("arguments[0].click();", element2);
		}

	}

	public void selectInputByTagId(String tag, String element) throws InterruptedException {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//" + tag + "[@id='" + element + "']")));
			driver.findElement(By.xpath("//" + tag + "[@id='" + element + "']")).click();
		} catch (Exception e) {
			WebElement element2 = driver.findElement(By.xpath("//" + tag + "[@id='" + element + "']"));
			JavascriptExecutor executor2 = (JavascriptExecutor) driver;
			executor2.executeScript("arguments[0].click();", element2);
		}

	}

	public void selectInputByTag(String tag, String element, String data) throws InterruptedException {
		try {
			wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@" + tag + "='" + element + "']")));
			driver.findElement(By.xpath("//input[@" + tag + "='" + element + "']")).sendKeys(data);
			;
		} catch (Exception e) {
			WebElement element2 = driver.findElement(By.xpath("//input[@" + tag + "='" + element + "']"));
			JavascriptExecutor executor2 = (JavascriptExecutor) driver;
			executor2.executeScript("arguments[0].value='" + data + "';", element2);
		}

	}

	public void waitForPageToLoad() throws InterruptedException {
		String pageLoadStatus;
		do {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			pageLoadStatus = (String) js.executeScript("return document.readyState");
			Thread.sleep(5000);
		} while (!pageLoadStatus.equals("complete"));
	}

	public void VerifyTextFieldwithSpecialCharacter(WebElement element, String string) throws InterruptedException {

		logger.log(LogStatus.INFO, "Verify the minimum length allowed");
		System.out.println("Verify the minimum length allowed");
		element.sendKeys("ab");
		try {
			String errormessage = driver.findElement(By.xpath("//span [@class='errorMessage']")).getText();
			if (errormessage.equalsIgnoreCase(
					"Minimum 3 characters, name must start with alphabets,only '_' is allowed as special character")) {
				logger.log(LogStatus.INFO, "Error Message Displayed :" + errormessage);
				System.out.println("Error Message Displayed :" + errormessage);
				logger.log(LogStatus.PASS, "The Field doesnot allow Less than 3 Characters");
				System.out.println("The Field doesnot allow Less than 3 Characters");
			} else {
				logger.log(LogStatus.INFO, "Error Message Displayed :" + errormessage);
				System.out.println("Error Message Displayed :" + errormessage);
				logger.log(LogStatus.FAIL, "The Error Message displayed  not as expected");
				System.out.println("The Error Message displayed  not as expected");
			}
		} catch (Exception e) {
			try {
				String errormessage = driver.findElement(By.xpath("//span [@class='errorMessage'][2]")).getText();
				if (errormessage.equalsIgnoreCase(
						"Minimum 3 characters, name must start with alphabets,only '_' is allowed as special character")) {
					logger.log(LogStatus.INFO, "Error Message Displayed :" + errormessage);
					System.out.println("Error Message Displayed :" + errormessage);
					logger.log(LogStatus.PASS, "The Field doesnot allow Less than 3 Characters");
					System.out.println("The Field doesnot allow Less than 3 Characters");
				} else {
					logger.log(LogStatus.INFO, "Error Message Displayed :" + errormessage);
					System.out.println("Error Message Displayed :" + errormessage);
					logger.log(LogStatus.FAIL, "The Error Message displayed is not as expected");
					System.out.println("The Error Message displayed is not as expected");
				}
			} catch (Exception e1) {
				logger.log(LogStatus.FAIL, "The field accepts characters less than 3 characters");
				System.out.println("The field accepts characters less than 3 characters");
			}
		}
		logger.log(LogStatus.INFO,
				"Verify the special characters are not allowed other than the allowed special character :" + string);
		System.out.println(
				"Verify the special characters are not allowed other than the allowed special character :" + string);
		element.clear();
		Random r = new Random();

		String splString = "!@#$%^&*";
		char splchar = splString.charAt(r.nextInt(splString.length()));
//			   while((splchar==string)) 
//			   {
//				   splchar= splString.charAt(r.nextInt(splString.length()));  
//			   }
		element.sendKeys("ab" + splchar + "de");
		try {
			String errormessage = driver.findElement(By.xpath("//span [@class='errorMessage']")).getText();
			if (errormessage.equalsIgnoreCase(
					"Minimum 3 characters, name must start with alphabets,only '_' is allowed as special character")) {
				logger.log(LogStatus.INFO, "Error Message Displayed :" + errormessage);
				System.out.println("Error Message Displayed :" + errormessage);
				logger.log(LogStatus.PASS, "The Field doesnot allow special Characters");
				System.out.println("The Field doesnot allow special Characters");
			} else {
				logger.log(LogStatus.INFO, "Error Message Displayed :" + errormessage);
				System.out.println("Error Message Displayed :" + errormessage);
				logger.log(LogStatus.FAIL, "The Error Message displayed is not as expected");
				System.out.println("The Error Message displayed is not as expected");
			}
		} catch (Exception e2) {
			try {
				String errormessage = driver.findElement(By.xpath("//span [@class='errorMessage'][2]")).getText();
				if (errormessage.equalsIgnoreCase(
						"Minimum 3 characters, name must start with alphabets,only '_' is allowed as special character")) {
					logger.log(LogStatus.INFO, "Error Message Displayed :" + errormessage);
					System.out.println("Error Message Displayed :" + errormessage);
					logger.log(LogStatus.PASS, "The Field doesnot allow Less than 3 Characters");
					System.out.println("The Field doesnot allow Less than 3 Characters");
				} else {
					logger.log(LogStatus.INFO, "Error Message Displayed :" + errormessage);
					System.out.println("Error Message Displayed :" + errormessage);
					logger.log(LogStatus.FAIL, "The Error Message displayed is not as expected");
					System.out.println("The Error Message displayed is not as expected");
				}
			} catch (Exception e1) {
				logger.log(LogStatus.FAIL, "The field accepts characters less than 3 characters");
				System.out.println("The field accepts characters less than 3 characters");
			}
		}

	}

	public void VerifyTextField(WebElement element) throws InterruptedException {

	}

	public void VerifyNumberField(WebElement element) throws InterruptedException {

	}

	public void VerifyEmailField(WebElement element) throws InterruptedException {

	}

	public void VerifyVersionField(WebElement element) throws InterruptedException {

	}

	@SuppressWarnings("null")
	public List<String> ConvertStringToList(String Stringtospilt, String delimitor) {
		List<String> StringList = new ArrayList<String>();
		String[] StringToArray = Stringtospilt.split(delimitor);
		for (String string : StringToArray) {
			String tool = string.toUpperCase().replace("_", "");
			StringList.add(tool);
		}

		return StringList;
	}

	/*public static void Sort2DArray(Object arr[][], int col) {
		// Using built-in sort function Arrays.sort
		Arrays.sort(arr, new Comparator<Object[]>() {

			@Override
			// Compare values according to columns
			public int compare(final Object[] entry1, final Object[] entry2) {

				final String time1 = entry1[0].toString().toUpperCase().replace("_", "").replace(" ", "");
				final String time2 = entry2[0].toString().toUpperCase().replace("_", "").replace(" ", "");
				return time1.compareTo(time2);
			}
		}); // End of function call sort().
	}*/

	public String GetParentTitle() throws InterruptedException, IOException {
		logger.log(LogStatus.INFO, "Get parent win title");
		System.out.println("Get parent win title");
		String parent = driver.getTitle();
		return parent;

	}

	public void switchToParent(String parent) {
		try {
			Set<String> s1 = driver.getWindowHandles();
			Iterator<String> I1 = s1.iterator();
			while (I1.hasNext()) {
				String child = I1.next();
				if (!parent.equals(child)) {
					driver.switchTo().window(child);
					waitForPageToLoad();
					driver.close();
				}
			}
			driver.switchTo().window(parent);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("child window not opened");
		}
	}

	public String childWinNavigation(WebDriver driver, String parent) throws InterruptedException {
		String actualWinTitle = "";
		Set<String> s1 = driver.getWindowHandles();
		Iterator<String> I1 = s1.iterator();
		while (I1.hasNext()) {
			String child = I1.next();
			driver.switchTo().window(child);
			try {
				actualWinTitle = driver.getTitle();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (!parent.equals(actualWinTitle)) {
				waitForPageToLoad();
			}
		}
		return actualWinTitle;
	}

}
