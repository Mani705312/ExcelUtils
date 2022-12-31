package com.Excel_Read_1;



import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Test5 
{
	
	public WebDriver driver;
	

	@Test(dataProvider="testData")
	  public void excel(HashMap<String,String> map) throws BiffException, IOException { 
		  
		for(Entry<String, String> entry : map.entrySet())
		{
			System.out.println("Key = "+entry.getKey()+ "; Value = "+entry.getValue());
		}
	  
	  }
	 

@DataProvider(name="testData")

public Object[][] getTestDataAIP() throws IOException, BiffException{
	
	Object[][] testData=readExcel();
	
	return testData;
}


public static Object[][] readExcel() throws BiffException, IOException
{
	Object[][] testData = new Object[][] { {} };
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
	//String inputData[][]= new String[noOfRows][noOfCols];
	//int count=0;
	
	HashMap<String,String> sharePriceExcelValuesfromXLS = new HashMap<String,String>();
	
	for(int i=0;i<noOfRows;i++)
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
			
		}
		
		System.out.println("The map is: "+sharePriceExcelValuesfromXLS);
	}
	
	testData[0]= new Object[] {sharePriceExcelValuesfromXLS};

return testData;
}

}