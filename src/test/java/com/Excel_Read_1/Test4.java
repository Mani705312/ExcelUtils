package com.Excel_Read_1;



import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Test4 
{
	
	public WebDriver driver;
	
	@Test(dataProvider="testData", priority=1)
	public void excel(String UserName, String Password) throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver", "./Drivers/chrome/chromedriver.exe");
		driver= new ChromeDriver();
		driver.get("https://www.saucedemo.com/");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@name='user-name']")).sendKeys(UserName);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(Password);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		Thread.sleep(2000);
		String ActUrl=driver.getCurrentUrl();
		String ExpUrl="https://www.saucedemo.com/inventory.html";
		//Assert.assertEquals(ActUrl,ExpUrl,"Test match Failed");
	}
	
	/*
	 * public void excel() { HashMap<String,String sharePriceExcelValuesfromXLS=
	 * readExcel();
	 * 
	 * for(String key : keyList) { System.out.println("From excel: "+key);
	 * if(sharePriceExcelValuesfromXLS.get(key).equals(sharePriceExcelValuesfromWEB.
	 * get(key))) System.out.println("Pass for the " + key);
	 * 
	 * else System.out.println("Fail for the " + key);
	 * 
	 * }
	 * 
	 * }
	 */
	
	@Test(priority=2)
	public void checkTitle()
	{
		String ActUrl=driver.getCurrentUrl();
		System.out.println(ActUrl);
		String ExpUrl="https://www.saucedemo.com/inventory.html";
		if(ActUrl.equalsIgnoreCase(ExpUrl))
		{
			System.out.println("TItle matches");
		}
		else
			System.out.println("TItle Not matched");
			
	}
	
	
	  @AfterMethod public void getTestResult(ITestResult testResult) {
	  //System.out.println("TestCase Name is: "+testResult.getName());
	  //System.out.println("TestCase Status is: "+testResult.getStatus());
	  
	  int status= testResult.getStatus(); if(status==1) { driver.close(); } else {
	  File outFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); try{
	  FileUtils.copyFile(outFile, new
	  File("./src/test/resources/Screenshots/Defect.jpeg")); } catch(IOException e)
	  { e.printStackTrace(); } } }
	 
	  @AfterClass
	  public void tearDown()
	  {
		  driver.quit();
	  }

@DataProvider(name="testData")
public Object[][] readExcel() throws BiffException, IOException
{
	//loading the file
	File f= new File("./src/test/resources/testData/TestData1.xls");
	//loading to workbook
	Workbook w= Workbook.getWorkbook(f);
	//loading to sheet
	Sheet s=w.getSheet(0);
	//Getting the rows and columns
	int noOfRows=s.getRows();
	System.out.println("No Of Rows: "+noOfRows);
	int noOfCols=s.getColumns();
	System.out.println("No Of Cols: "+noOfCols);
	//creating a string variable
	String inputData[][]= new String[noOfRows][noOfCols];
	//int count=0;
	
	HashMap<String,String> sharePriceExcelValuesfromXLS = new HashMap<String,String>();
	
	for(int i=1;i<noOfRows;i++)
	{
		for(int j=0;j<noOfCols;j++)
		{
			//getting contents from cells
			
			Cell c = null;
		
			 c= s.getCell(j,0);
			String key = c.getContents();
			System.out.println("The Keys are: "+key);
		     c = s.getCell(j,i);
		    String value = c.getContents();
		    System.out.println("The values are: "+value);
			
			sharePriceExcelValuesfromXLS.put(key,value);
			
			System.out.println("The map is: "+sharePriceExcelValuesfromXLS);
			
			inputData[i][j]=c.getContents();
		}
		//count++;
	}

return inputData;
}

}