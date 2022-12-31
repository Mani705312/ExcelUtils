package com.Excel_Read_1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Sample {
	
	WebDriver driver;
	
	@Test(priority=-1)
	public void test1()
	{
		  System.setProperty("webdriver.chrome.driver", "./Drivers/chrome/chromedriver.exe");
		  
		  driver = new ChromeDriver();
		  
		  driver.get("https://www.w3schools.com/html/html_tables.asp");
		  
		  System.out.println(driver.getTitle());
		  
		  driver.quit();
	}
	
	@Test(priority=0)
	public void test2()
	{
		  System.setProperty("webdriver.chrome.driver", "./Drivers/chrome/chromedriver.exe");
		  
		  driver = new ChromeDriver();
		  
		  driver.get("https://www.saucedemo.com");
		  
		  System.out.println(driver.getTitle());
		  
		  driver.quit();
	}
	
	@Test(priority=1)
	public void test3()
	{
		  System.setProperty("webdriver.chrome.driver", "./Drivers/chrome/chromedriver.exe");
		  
		  driver = new ChromeDriver();
		  
		  driver.get("https://www.flipkart.com");
		  
		  System.out.println(driver.getTitle());
		  
		  driver.quit();
	}
	

}
