package com.ibm.pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ibm.test.Login;
import com.ibm.utilities.PropertiesFileHandler;

public class AdminPage{

	WebDriverWait wait;
	WebDriver driver;
	//PropertiesFileHandler propFileHandler;
	//HashMap<String, String> data;
		
		@Test
		public void testCase7() throws IOException, InterruptedException {
			FileInputStream file = new FileInputStream("./TestData/data.properties");
			Properties prop = new Properties();
			prop.load(file);
			String url = prop.getProperty("url");
			String username = prop.getProperty("user");
			String password = prop.getProperty("password");
			System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
			driver = new ChromeDriver();
			wait = new WebDriverWait(driver, 60);
			driver.get(url);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Login login = new Login(driver, wait);
			
			login.enterEmailAddress(username);
			login.enterPassword(password);
			login.clickOnLogin();
			
			WebElement systemEle=driver.findElement(By.linkText("System"));
			systemEle.click();
			
			WebElement usersEle=driver.findElement(By.linkText("Users"));
			usersEle.click();
			
			Thread.sleep(3000);
			WebElement actionEle=driver.findElement(By.cssSelector(".even:nth-child(2) .btn"));
			actionEle.click();
			
			WebElement deleteEle=driver.findElement(By.linkText("Delete"));
			deleteEle.click();
			
			WebElement confirmEle=driver.findElement(By.cssSelector("button.confirm"));
			confirmEle.click();
			
			WebElement logoutEle=driver.findElement(By.linkText("Log Out"));
			logoutEle.click();
			
			String username1 = prop.getProperty("user1");
			String password1 = prop.getProperty("password1");
			login.enterEmailAddress(username1);
			login.enterPassword(password1);
			login.clickOnLogin();

			Assert.assertTrue(driver.findElement(By.linkText(" No match for Email and Password or User is not activated.")).isDisplayed());
			
		}
			
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText("valid")));
	}
