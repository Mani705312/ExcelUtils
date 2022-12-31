package com.Excel.Util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel_1 {
	
	public static Object[][] getExcelDataOneRow(FileInputStream input, String sheetName)
	{
		
		Object[][] testData = new Object[][] { {} };
		
		List<Map<String, String>> list = new ArrayList<>();
		
		DataFormatter formatter = new DataFormatter();
		
		try 
		{
			XSSFWorkbook workbook = new XSSFWorkbook(input);
			
			int sheetCount = workbook.getNumberOfSheets();
			
			//list = new ArrayList<>();
			
			XSSFSheet testDataSheet = workbook.getSheet(sheetName);
			
			int dataRowCount = testDataSheet.getLastRowNum()-testDataSheet.getFirstRowNum();
			
			Row row = testDataSheet.getRow(0);
			
			int headingColCount = row.getPhysicalNumberOfCells();
			
			String[] headings = new String[headingColCount];
			
			for(int cellCount=0; cellCount< row.getLastCellNum();cellCount++)
			{
				headings[cellCount] = row.getCell(cellCount).getStringCellValue();
			}
			
			for(int rowCount=1; rowCount <= dataRowCount; rowCount++)
			{
				Map<String, String> testCaseMap = new HashMap();
				
				Row dataRow = testDataSheet.getRow(rowCount);
				
				for(int cellCount = 0; cellCount< dataRow.getLastCellNum();cellCount++)
				{
					testCaseMap.put(headings[cellCount], formatter.formatCellValue(testDataSheet.getRow(rowCount).getCell(cellCount)));
				}
				list.add(testCaseMap);
			}
			testData[0] = new Object[] {list};
			
			workbook.close();

		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return testData;
		
	}

}
