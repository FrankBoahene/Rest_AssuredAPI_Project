package com.RestassuredAPI_Testing.utilities;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Listeners extends TestListenerAdapter 
{
	public ExtentHtmlReporter htmlReport;
	public ExtentReports extent;
	public ExtentTest test;
	
	public void onStart(ITestContext testContext) 
	{
		htmlReport=new ExtentHtmlReporter(System.getProperty("user.dir")+ "/reports/myReport.html"); //specify location
		
		htmlReport.config().setDocumentTitle("Automation Report"); //Title of the report
		htmlReport.config().setReportName("Rest API Testing Report"); //name of the report
		//htmlReport.config().setTestViewChartLocation(ChartLocation.TOP); //location of the chart
		htmlReport.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(htmlReport);
		extent.setSystemInfo("Project Name", "Employee Database API");
		extent.setSystemInfo("Host name", "localhost");
		extent.setSystemInfo("Environment","QA");
		extent.setSystemInfo("user","Fboahene");
	}
	
	public void onTestSuccess(ITestResult result) 
	{
		//test=extent.createTest(result.getClass().getName());
		//test.createNode(result.getName());
		test=extent.createTest(result.getName()); // create new entry in the report
		
		test.log(Status.PASS, "Test Case PASSED IS " + result.getName());
	}
	
	public void onTestFailure(ITestResult result) 
	{
		test=extent.createTest(result.getName()); // create new entry in the report
		
		test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getName()); // to add name in the extent report
		test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getThrowable()); //to add error/exception in extent report
	}
	
	public void onTestSkipped(ITestResult result) 
	{
        test=extent.createTest(result.getName()); // create new entry in the report
		
		test.log(Status.SKIP, "TEST CASE SKIPPED IS " + result.getName()); // to add name in the extent report
	}
	
	public void onFinish(ITestContext testContext) 
	{
        extent.flush();
	}	

}
