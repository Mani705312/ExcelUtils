package com.Excel.Util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.testng.annotations.DataProvider;


public class Excel_Read_1 {

	private String dataSheet="./src/test/resources/testData/TestDataDiffSheet.xlsx";	
	
	@DataProvider(name="TestCase")
	public Object[][]TC_DataProvider() throws FileNotFoundException
	{
		
		return getTestDataFromExcel(dataSheet, "TESTCASE_1");
		
	}
	
	@DataProvider(name="TestCase_Sheet2")
	public Object[][]TC_DataProvider_Sheet2() throws FileNotFoundException
	{
		
		return getTestDataFromExcel(dataSheet, "TESTCASE_2");
		
	}
	
	public Object[][] getTestDataFromExcel(String fileName, String sheetName) throws FileNotFoundException
	{
		Object[][] testData = new Object[][] { {} };
		
	    FileInputStream input =  new FileInputStream(fileName);
		
		/*if(input.nullInputStream() != null)
		{
			System.out.println("The file" +input+ "is empty");
		}
		
		else
		{*/
			testData = Excel_1.getExcelDataOneRow(input, sheetName);
		//}
	
		return testData;
		
	}

}
