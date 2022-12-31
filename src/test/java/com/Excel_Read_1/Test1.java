package com.Excel_Read_1;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Test1 {
	
	String file_name="./src/test/resources/testData/TestData.xlsx";
	
	static XSSFWorkbook wb;
	static XSSFSheet sh;

	@Test(dataProvider="sheet1")
	public static void readValues(String User1, String User2, String User3, String User4,String User5) {
		
		System.out.println("User1: "+User1);	
		System.out.println("User2: "+User2);	
		System.out.println("User3: "+User3);	
		System.out.println("User4: "+User4);	
		System.out.println("User5: "+User5);

	}
	@Test(dataProvider="sheet2")
	public static void readValues1(String User6, String User7, String User8, String User9,String User10) {
		System.out.println("User1: "+User6);	
		System.out.println("User2: "+User7);	
		System.out.println("User3: "+User8);	
		System.out.println("User4: "+User9);	
		System.out.println("User5: "+User10);

	}
    
	@DataProvider(name="sheet1")
	public Object[][] getSheet1() throws IOException{

		return getExcelTestData(file_name,"Sheet1");
		
	}
	
	@DataProvider(name="sheet2")
	public Object[][] getSheet2() throws IOException{

		return getExcelTestData(file_name,"Sheet2");
		
	}
	
	public Object[][] getExcelTestData(String file_name, String dataSheet) throws IOException{
		
        FileInputStream File= new FileInputStream(file_name);
		
		wb= new XSSFWorkbook(File);
		
		int sheetSize= wb.getNumberOfSheets();
		
		System.out.println("No Of Sheets: "+sheetSize);
		
		sh=wb.getSheet(dataSheet);
		
		int rowCount=sh.getLastRowNum()-sh.getFirstRowNum();
		
		Row row= sh.getRow(1);
		
		int colsCount=row.getLastCellNum();
		
		System.out.println("Rows Count: "+rowCount+ " Cols Count: "+colsCount);
		
		Object[][] testData= new Object[rowCount][colsCount];
		
		for (int rNum=1;rNum<=rowCount;rNum++)
		   {
			   Row row1=sh.getRow(rNum);
			   
			   for(int cNum=0;cNum<colsCount;cNum++)
			   {
				   String cellText= row1.getCell(cNum).getStringCellValue();
				 
				   testData[rNum-1 ][cNum] = cellText;
			   }
		   }
		
		
		
		
		return testData;
		
	}

}

