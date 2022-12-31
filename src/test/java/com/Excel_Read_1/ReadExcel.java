package com.Excel_Read_1;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.testng.annotations.Test;

public class ReadExcel {
	
	@Test
	
	public void loadData() throws IOException
	{
        List<LinkedHashMap<String, String>> testData = getExcelAsMap();
        
        System.out.println("TestData Size is: "+testData.size());
		
		for(int k=0;k<testData.size();k++)
		{
			for(String s: testData.get(k).keySet())
			{
				System.out.println("Values Of "+s+" is : "+testData.get(k).get(s));
			}
		}
	}
	
	
	public static List<LinkedHashMap<String, String>> getExcelAsMap() throws IOException
	{
		FileInputStream fis = new FileInputStream("./src/test/resources/testData/WebTable.xls");
		
		HSSFWorkbook wb = new HSSFWorkbook(fis);
		
		HSSFSheet sh = wb.getSheetAt(0);
		
		List<LinkedHashMap<String, String>> dataList = new ArrayList<LinkedHashMap<String, String>>();
		
		int RowCount = sh.getLastRowNum()-sh.getFirstRowNum();
		
		System.out.println(RowCount);
		
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
			System.out.println("-----dataList-----");
			System.out.println(dataList);
			
		}
		return dataList;
	}
	

}
