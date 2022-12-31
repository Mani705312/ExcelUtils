package com.Excel_Read_1;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CompareXLSX_WebTable2 {
	
	static WebDriver driver;
	static Logger logger;
	
	public CompareXLSX_WebTable2()
	{
		logger = Logger.getLogger(CompareXLSX_WebTable2.class.getName());
		PropertyConfigurator.configure("./Log4j/log4j.properties");
	}
	
	@Parameters({"url","browser"})
	@Test
	public void compareXL_WT(@Optional("https://www.w3schools.com/html/html_tables.asp")String url,@Optional("firefox")String browser) throws IOException
	{
		
		
		  if(browser.equalsIgnoreCase("chrome")) 
		  { 
			 System.setProperty("webdriver.chrome.driver","./Drivers/chrome/chromedriver.exe");
		   
		     driver = new ChromeDriver(); 
		  }
		  
		  else if(browser.equalsIgnoreCase("firefox")) 
		  {
		 
		  System.setProperty("webdriver.gecko.driver", "./Drivers/firefox/geckodriver.exe");
		  
		  driver = new FirefoxDriver();
			
	      }
		
		  logger.info("Driver Initialized");
		  
		  driver.get(url);
		  
		  logger.info("URL Opened");
		
		  //Web table 
		  List <WebElement> allHeaders = driver.findElements(By.xpath("//*[@id='customers']/tbody/tr/th")); 
		  
		  List <String> allHeaderNames = new ArrayList<String>();
		  
		  for(WebElement header : allHeaders) 
		  { 
			  String headerName = header.getText();
			  
		      allHeaderNames.add(headerName); 
		  }
		  
		  //Each row will be a key value pair. So we will use LinkedHashMap so that
		  //order  can be retained. // All map will be added to a list.
		  
		  List<LinkedHashMap<String, String>> allTableData = new ArrayList<LinkedHashMap<String, String>>();
		  
		  List <WebElement> allRows = driver.findElements(By.xpath("//*[@id=\"customers\"]/tbody/tr"));
		  
		  //Starting from 2nd Row as 1st Row is header 
		  for(int i=2;i<=allRows.size();i++) 
		  { 
			  WebElement specificRowLocator = driver.findElement(By.xpath("//*[@id=\"customers\"]/tbody/tr["+i+"]"));
		  
		      List <WebElement> allColumns =  specificRowLocator.findElements(By.tagName("td"));
		  
		  // Creating a map to store key-value pair data. It will be created for each  iteration of row
		  
		  LinkedHashMap<String, String> eachRowData = new LinkedHashMap<>();
		  
		  //iterating each cell
		  
		  for(int j=0;j<allColumns.size();j++) 
		  { 
			  String columnValue = allColumns.get(j).getText();
		  
		  // We will put in to map with header name and value with iteration 
			  // Get jth  index value from allHeaderNames and jth cell value of row
		  
		      eachRowData.put(allHeaderNames.get(j), columnValue);
		  
		  }
		  
		 // System.out.println("-----Row Data-----"); 
		 // System.out.println(eachRowData);
		  
		  allTableData.add(eachRowData); 
		  
		  } 
		  logger.info("WebTable values are printed into linked Hashmap and list");
		  
		  System.out.println("-----Table Data-----");
		  logger.info("Table Data printed into the console");
		  
		  for(int m=0;m<allTableData.size();m++)
		  {
		  System.out.println("Company: "+allTableData.get(m).get("Company"));
		  System.out.println("Contact: "+allTableData.get(m).get("Contact"));
		  System.out.println("Country: "+allTableData.get(m).get("Country"));
		  }
		  //WebTable
		  
		  //Start of Excel load
		  logger.info("Loading Excel");
		  List<LinkedHashMap<String, String>> testData = getExcelAsMap();
			
		  System.out.println("-----Excel Data-----");
			for(int k=0;k<testData.size();k++)
			{
				/*
				 * for(String s: testData.get(k).keySet()) {
				 * System.out.println("Values Of "+s+" is : "+testData.get(k).get(s)); }
				 */
				
				System.out.println("Company: "+testData.get(k).get("Company"));
				System.out.println("Contact: "+testData.get(k).get("Contact"));
				System.out.println("Country: "+testData.get(k).get("Country"));
			}
			
			logger.info("Excel Data printed into the console");
			//Comparison
			logger.info("Excel Data vs Table Data comparison");
			for(int l=0; l<testData.size();l++)
			{
				
				if(allTableData.get(l).get("Contact").equals(testData.get(l).get("Contact")))
				{
					System.out.println("Data is similar-successful");
					logger.info("Data is similar-successful");
				}
				else
				{
					System.out.println("Data is not similar-unsuccessful");
				    logger.info("Data is not similar-unsuccessful");
				}
				
			}
					
		driver.quit();
		logger.info("DriverTearDown");
	}
	
	public static List<LinkedHashMap<String, String>> getExcelAsMap() throws IOException
	{
		FileInputStream fis = new FileInputStream("./src/test/resources/testData/WebTable.xls");
		
		HSSFWorkbook wb = new HSSFWorkbook(fis);
		
		HSSFSheet sh = wb.getSheetAt(0);
		
		List<LinkedHashMap<String, String>> dataList = new ArrayList<LinkedHashMap<String, String>>();
		
		int RowCount = sh.getLastRowNum()-sh.getFirstRowNum();
		
		//System.out.println(RowCount);
		
		for(int i=1;i<=RowCount;i++)
		{
			LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
			
			int cellCount = sh.getRow(i).getPhysicalNumberOfCells();
			
			for(int j=0;j<cellCount;j++)
			{
				Row row = sh.getRow(0);
				String ColumnHeaders = row.getCell(j).getStringCellValue();
				
				Row row1 = sh.getRow(i);
				String CellValues = row1.getCell(j).getStringCellValue();
			
				map.put(ColumnHeaders, CellValues);
				
			}
			dataList.add(map);
			//System.out.println("-----dataList-----");
			//System.out.println(dataList);
			logger.info("Excel values are printed into linked Hashmap and list");
		}
		return dataList;
	}

		

}
