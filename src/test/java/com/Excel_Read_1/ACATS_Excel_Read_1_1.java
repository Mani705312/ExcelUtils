package com.Excel_Read_1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.Excel.Util.Excel_Read_1;

public class ACATS_Excel_Read_1_1 {
	
	@Test(priority=1, dataProvider="TestCase", dataProviderClass = Excel_Read_1.class)
	public void getValues(List<Map<String, String>> map){
		
		String testId="TC_2";
		
		Map<String, String> testdata = getData(testId, map);
		
		System.out.println("The TestCaseID is: " +testdata.get("TESTCASE_ID"));
		
		String UserName = testdata.get("UserID");
		String Password = testdata.get("Password");
		
		System.out.println("The UserName is: "+UserName);
		System.out.println("The Password is: "+Password);
			
	}
	
	@Test(priority=2, dataProvider="TestCase_Sheet2", dataProviderClass = Excel_Read_1.class)
	public void getValues_Sheet2(List<Map<String, String>> map){
		
		String testId="TC_3";
		
		Map<String, String> testdata = getData(testId, map);
		
		System.out.println("The TestCaseID is: " +testdata.get("TESTCASE_ID"));
		
		String User1 = testdata.get("User 1");
		String User2 = testdata.get("User 2");
		
		System.out.println("The User1 is: "+User1);
		System.out.println("The User2 is: "+User2);
		
		/*
		 * System.out.println();
		 * 
		 * for(Map<String, String> testRowData : map) {
		 * System.out.println(testRowData.get("User 1")); }
		 */
			
	}
	
	private Map<String, String> getData(String testCaseId, List<Map<String, String>> map)
	{
		Map<String, String> testData = null;
		
		for(Map<String, String> entry : map)
		{
			if(entry.get("TESTCASE_ID").equalsIgnoreCase(testCaseId))
			{
				testData=entry;
				break;
			}
		}
		
		
		return testData;
		
	}
	

}
