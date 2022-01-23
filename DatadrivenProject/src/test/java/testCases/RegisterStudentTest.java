package testCases;
//funny
import java.io.*;
import java.util.*;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.util.concurrent.TimeUnit;
import utilities.Constants;
import utilities.ExcelUtils;

public class RegisterStudentTest {

	public static void main(String args[]) throws IOException {
		ChromeOptions handlSSLErr = new ChromeOptions ();       
		handlSSLErr.setAcceptInsecureCerts(true);
		  System.setProperty("webdriver.chrome.driver",
		  "C:\\Users\\KalvaSwetha\\Downloads\\chromedriver_win32\\chromedriver.exe");
	
		WebDriver driver = new ChromeDriver(handlSSLErr); 
		  driver.manage().window().maximize();
		  //Open browser 
		  driver.get(Constants.URL);
		  
			/*
			 * driver.findElement(By.id("details-button")).click();
			 * driver.findElement(By.id("proceed-link")).click();
			 */
		  driver.manage().timeouts().implicitlyWait(2000,TimeUnit.SECONDS);
		  
		ExcelUtils xlReader = new ExcelUtils(Constants.Path_TestData);
		int rowCount=xlReader.getRowCount("STUDENT_DATA");
		xlReader.addColumn("STUDENT_DATA", "Status");
		for(int rowNum=2;rowNum<=rowCount;rowNum++)
		{
			String firstname = xlReader.getCellData("STUDENT_DATA", "firstname", rowNum);
			System.out.println(firstname);
			String lastname = xlReader.getCellData("STUDENT_DATA", "lastname", rowNum);
			System.out.println(lastname);
			String email = xlReader.getCellData("STUDENT_DATA", "email", rowNum);
			System.out.println(email);
			String mobile = xlReader.getCellData("STUDENT_DATA", "mobile", rowNum);
			System.out.println(mobile);
			String address = xlReader.getCellData("STUDENT_DATA", "address", rowNum);
			System.out.println(address);
			String gender = xlReader.getCellData("STUDENT_DATA", "gender", rowNum);
			System.out.println(gender);
			String hobbies = xlReader.getCellData("STUDENT_DATA", "hobbies", rowNum);
			System.out.println(hobbies);
			String state = xlReader.getCellData("STUDENT_DATA", "state", rowNum);
			System.out.println(state);
			
			driver.findElement(By.id("firstName")).sendKeys(firstname);
			driver.findElement(By.id("lastName")).sendKeys(lastname);
			driver.findElement(By.id("userEmail")).sendKeys(email);
			
			if(gender.equals("Male"))
			{
				WebElement gen=driver.findElement(By.id("gender-radio-1"));
				JavascriptExecutor js=(JavascriptExecutor)driver;
				js.executeScript("arguments[0].click();", gen);
				
			}
			else
			{
				WebElement gen=driver.findElement(By.id("gender-radio-2"));
				JavascriptExecutor js=(JavascriptExecutor)driver;
				js.executeScript("arguments[0].click();", gen);
			}
			driver.findElement(By.id("userNumber")).sendKeys(mobile);
			if(hobbies.equals("Reading"))
			{
				WebElement hob=driver.findElement(By.id("hobbies-checkbox-2"));
				JavascriptExecutor js=(JavascriptExecutor)driver;
				js.executeScript("arguments[0].click();", hob);
				
			}
			else if(hobbies.equals("Music"))
			{
				WebElement hob=driver.findElement(By.id("hobbies-checkbox-3"));
				JavascriptExecutor js=(JavascriptExecutor)driver;
				js.executeScript("arguments[0].click();", hob);
			}
			else
			{
				WebElement hob=driver.findElement(By.id("hobbies-checkbox-1"));
				JavascriptExecutor js=(JavascriptExecutor)driver;
				js.executeScript("arguments[0].click();", hob);	
			}
			driver.findElement(By.id("currentAddress")).sendKeys(address);
			
			driver.findElement(By.id("submit")).submit();
			
			
			  WebElement cfrmText=driver.findElement(By.
			  xpath("//div[text()='Thanks for submitting the form']"));
			  if(cfrmText.isDisplayed()) {
			   xlReader.setCellData("STUDENT_DATA", "Status", rowNum, "Pass");
			  }
			  else
			  {
				  xlReader.setCellData("STUDENT_DATA", "Status", rowNum, "Fail");
			  }
			  
			//close the confirmation popup
			
            WebElement closebtn=driver.findElement(By.id("closeLargeModal"));
            JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript("arguments[0].click();", closebtn);
         
            
		}
		driver.close();

	}

}
