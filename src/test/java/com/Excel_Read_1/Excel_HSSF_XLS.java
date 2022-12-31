package com.Excel_Read_1;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Excel_HSSF_XLS {
	
	static String path="./src/test/resources/testData/TestData1.xls";
	
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
		Assert.assertEquals(ActUrl,ExpUrl,"Test match Failed");

	} 
	
	@AfterTest
	public void tearDown() throws InterruptedException
	{
		Thread.sleep(2000);
		driver.quit();
	}
	
	
	@DataProvider(name="testData")
	public Object[][] readExcelData() throws IOException
	{
		Object[][] testData = new Object[][] {{}};
		
		FileInputStream fis = new FileInputStream(path);
		
		HSSFWorkbook wb = new HSSFWorkbook(fis);
		
		HSSFSheet sh = wb.getSheet("Sheet1");
		
		int rowsCount = sh.getLastRowNum()-sh.getFirstRowNum();
		
		System.out.println("Row Count = "+rowsCount);
		
	    int colsCount = sh.getRow(0).getPhysicalNumberOfCells();
	    
	    System.out.println("Cols Count = "+colsCount);
	    
	    String inputData[][] = new String [rowsCount][colsCount];
	    
	    DataFormatter formatter = new DataFormatter();
	    
	    int count=0;
	     
	    for(int i=1;i<=rowsCount;i++)
	    {
	    	for(int j=0;j<colsCount;j++)
	    	{
	    		Row row = sh.getRow(i);
	    		Cell cell = row.getCell(j);
	    		
	    		inputData[count][j]=formatter.formatCellValue(cell);
	    	}
	    	
	    	count++;
	    }
		
		return inputData;
		
	}

}
