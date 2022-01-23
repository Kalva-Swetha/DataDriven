package testCases;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utilities.Constants;

import utilities.TestUtils;

public class RegisterStudent_DataProviderTest {
	WebDriver driver;

	@BeforeMethod

	public void setUp() {
		ChromeOptions handlSSLErr = new ChromeOptions();
		handlSSLErr.setAcceptInsecureCerts(true);

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\KalvaSwetha\\Downloads\\chromedriver_win32\\chromedriver.exe");
		// new ChromeDriver();
		this.driver = new ChromeDriver(handlSSLErr);
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		// Open browser
		driver.get(Constants.URL);

	}

	@DataProvider
	public Iterator<Object[]> getTestData() {
		ArrayList<Object[]> testData = TestUtils.getDataFromExcel();
		return testData.iterator();

	}

	@Test(dataProvider = "getTestData")
	public void pageTest(String firstname, String lastname, String email, String mobile, String address, String gender,
			String hobbies) {

		driver.findElement(By.id("firstName")).sendKeys(firstname);
		driver.findElement(By.id("lastName")).sendKeys(lastname);
		driver.findElement(By.id("userEmail")).sendKeys(email);

		if (gender.equals("Male")) {
			WebElement gen = driver.findElement(By.id("gender-radio-1"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", gen);

		} else {
			WebElement gen = driver.findElement(By.id("gender-radio-2"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", gen);
		}
		driver.findElement(By.id("userNumber")).sendKeys(mobile);
		if (hobbies.equals("Reading")) {
			WebElement hob = driver.findElement(By.id("hobbies-checkbox-2"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", hob);

		} else if (hobbies.equals("Music")) {
			WebElement hob = driver.findElement(By.id("hobbies-checkbox-3"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", hob);
		} else {
			WebElement hob = driver.findElement(By.id("hobbies-checkbox-1"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", hob);
		}
		driver.findElement(By.id("currentAddress")).sendKeys(address);
		driver.findElement(By.id("submit")).submit();
		WebElement cfrmText = driver.findElement(By.xpath("//div[text()='Thanks for submitting the form']"));

		// close the confirmation popup
		if (cfrmText.isDisplayed()) {
			WebElement closebtn = driver.findElement(By.id("closeLargeModal"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", closebtn);
		}

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
