package com.Excel_Read_1;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Test2 {
	
	public static HashMap<String,String> sharePriceExcelValuesfromXLS;
	public static HashMap<String,String> sharePriceExcelValuesfromWEB;
	
	static String file="./src/test/resources/testData/TEST_TAB.xls";
	
	static Workbook wb;
	static Sheet sh;

	@Test
	public void excel() throws BiffException, IOException
	{
		
		sharePriceExcelValuesfromXLS = getExcelTestData(file, "Sheet1");
		
		sharePriceExcelValuesfromWEB =readDatafromWEB();
		
	    Set<String> keyList = sharePriceExcelValuesfromXLS.keySet();
	    
	    System.out.println("List of map: "+keyList);
	    
	    for(String key : keyList)
		{
			System.out.println("From excel: "+key);
			if(sharePriceExcelValuesfromXLS.get(key).equals(sharePriceExcelValuesfromWEB.get(key)))
				System.out.println("Pass for the " + key);
 
			else
				System.out.println("Fail for the " + key);
 
		}
	    
	}
	
	public static HashMap<String,String> readDatafromWEB()
	{
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://money.rediff.com/losers/bse/daily/groupa?src=gain_lose");
		List<WebElement> currentPriceList = driver.findElements(By.xpath("//table[@class='dataTable']/tbody/tr/td[4]"));
		List<WebElement> companyList = driver.findElements(By.xpath("//table[@class='dataTable']/tbody/tr/td[1]"));
		System.out.println("Size of the List ::" +currentPriceList.size() );
		HashMap<String,String> sharePriceExcelValuesfromWEB = new HashMap<String,String>();
		for(int i = 0 ;i <7;i++)
		{
			System.out.println("Key ::" +companyList.get(i).getText().trim()  +"Value ::"+currentPriceList.get(i).getText().trim() );
			sharePriceExcelValuesfromWEB.put(companyList.get(i).getText().trim(), currentPriceList.get(i).getText().trim());
		}
		return sharePriceExcelValuesfromWEB;
 
	}
	
	public static HashMap<String, String> getExcelTestData(String file_name, String dataSheet) throws IOException, BiffException{
		
        FileInputStream File= new FileInputStream(file_name);
		
		wb= Workbook.getWorkbook(File);
		
		int sheetSize= wb.getNumberOfSheets();
		
		System.out.println("No Of Sheets: "+sheetSize);
		
		sh=wb.getSheet(dataSheet);
		
		int rowCount=sh.getRows();
		
		int colsCount=sh.getColumns();
		System.out.println("Columns: "+colsCount);
		
		HashMap<String,String> sharePriceExcelValuesfromXLS = new HashMap<String,String>();
		
		for(int i=0;i<rowCount;i++)
		{
			for(int j=1;j<colsCount;j++)
			{
			Cell cell = sh.getCell(0,i);
			String key = cell.getContents();
 
			cell  = sh.getCell(j,i);
			String value= cell.getContents();
 
			/*
			 * System.out.print("Key  " + key); System.out.print("Value " + value);
			 */
			sharePriceExcelValuesfromXLS.put(key,value);
			System.out.println();
			}
		}
		
		
		return sharePriceExcelValuesfromXLS;
		
	}

}

