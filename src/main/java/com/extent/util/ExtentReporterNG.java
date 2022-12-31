package com.extent.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReporterNG implements IReporter{
	
	private ExtentReports extent;
	
	public void generateReport(List<XmlSuite> xmlsuites, List<ISuite> suites, String outputDirectory) 
	{
		try
		{
		extent = new ExtentReports("./Extent_Reports/"+ getTimeNow() + "_ExtentReport.html", true);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	//Iterating through the suites
		for(ISuite suite : suites)
		{
			Map<String, ISuiteResult> result = suite.getResults();
			
			for(ISuiteResult r : result.values())
			{
				ITestContext context = r.getTestContext();
				
				bulidTestNodes(context.getPassedTests(), LogStatus.PASS);
				bulidTestNodes(context.getFailedTests(), LogStatus.FAIL);
				bulidTestNodes(context.getSkippedTests(), LogStatus.SKIP);
			}
		}
		
		extent.flush();
		extent.close();
	}
	
	public void bulidTestNodes(IResultMap tests, LogStatus status)
	{
		ExtentTest test;
		
		if(tests.size()>0)
		{
			for(ITestResult result : tests.getAllResults())
			{
				test = extent.startTest(result.getMethod().getMethodName());
				
				test.setStartedTime(getTime(result.getStartMillis()));
				test.setEndedTime(getTime(result.getEndMillis()));
				
				for(String group : result.getMethod().getGroups())
				  test.assignCategory(group);
				
				if(result.getThrowable()!= null)
				{
					test.log(status, result.getThrowable());
				}
				else
				{
					test.log(status, "Test" + status.toString().toLowerCase() + "ed");
				}
				
				extent.endTest(test);
			}
		}
	}
	
	private Date getTime(long millis)
	{
		Calendar calendar= Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}
	
	private String getTimeNow()
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy_MM_dd HH_mm_ss");  
		LocalDateTime time = LocalDateTime.now();
	    return formatter.format(time);
	}

}
