package com.RestassuredAPI_Testing.testCases;

import org.testng.Assert;
import org.testng.annotations.*;

import com.RestassuredAPI_Testing.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC002_Get_Single_Employee_Record extends TestBase {
	
	RequestSpecification httpRequest;
	Response response;
	
	@BeforeClass
	void getEmployeeData() throws InterruptedException
	{
		
	logger.info("********* Started TC002_Get_Single_Employee_Record *********");	
	
	RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
	httpRequest = RestAssured.given();
	response = httpRequest.request(Method.GET, "//employee/"+empID);
	
	Thread.sleep(5000);
	}
	
	@Test
	void checkResponseBody() 
	{
		String responseBody = response.getBody().asString();
		Assert.assertEquals(responseBody.contains(empID), true);
	}
	
	@Test
	void checkStatusCode() 
	{
		int statusCode = response.getStatusCode(); //Getting status code
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	void checkResponseTime() 
	{
		long responseTime = response.getTime(); //Getting response time
		Assert.assertTrue(responseTime<6000);
	}
	
	@Test
	void checkStatusLine() 
	{
		String statusLine = response.getStatusLine(); //Getting status line
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}
	
	@Test
	void checkcontentType() 
	{
		String contentType = response.header("content-Type"); 
		Assert.assertEquals(contentType, "application/json;charset=utf-8");
	}
	
	@Test
	void checkServerType() 
	{
		String serverType = response.header("Server");   
		Assert.assertEquals(serverType, "nginx/1.16.0");
	} 
	
	@Test
	void checkContentLength() 
	{
		String contentLength = response.header("Content-Length");  
		Assert.assertTrue(Integer.parseInt(contentLength)<600);
	} 
	
	@AfterClass
	void tearDown() 
	{
		logger.info("************* Finished TC002_Get_Single_Employee_Record *************");
	}


}
