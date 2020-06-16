package com.RestassuredAPI_Testing.testCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;


import com.RestassuredAPI_Testing.base.TestBase;
import com.RestassuredAPI_Testing.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC004_Put_Employee_Record extends TestBase {
	
	RequestSpecification httpRequest;
	Response response;
	
	String empName=RestUtils.empName();
	String empSalary=RestUtils.empSal();
	String empAge=RestUtils.empAge();
	
	@BeforeClass
	void updateEmployee() throws InterruptedException
	{
		
	logger.info("********* Started TC004_Put_Employee_Record *********");	
	
	RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
	httpRequest = RestAssured.given();
	
	//JSONObject is a class that represents a single JSON. We can add Key-Value pairs using the put method
	//{"name":"Frank123X","salary":"1234","age":"30"}
	JSONObject requestParams = new JSONObject();
	requestParams.put("name", empName);  //Cast
	requestParams.put("salary", empSalary);
	requestParams.put("age", empAge);
	
	// Add a header stating the request body is a JSON
	httpRequest.header("Content-Type","application/json");
	
	// Add the json to the body of the request
	httpRequest.body(requestParams.toJSONString());
	
	response = httpRequest.request(Method.PUT, "/update/"+empID);
	
	Thread.sleep(5000);
	
	}
	
	@Test
	void checkResponseBody()
	{
		String responseBody = response.getBody().asString();
		Assert.assertEquals(responseBody.contains(empName), true);
		Assert.assertEquals(responseBody.contains(empSalary), true);
		Assert.assertEquals(responseBody.contains(empAge), true);
	}
	
	@Test
	void checkStatusCode() 
	{
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	void checkStatusLine() 
	{
		String statusLine = response.getStatusLine();
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}
	
	@Test(enabled=true)
	void checkContentType() 
	{
		String contentType = response.header("contentType");
		Assert.assertEquals(contentType, "application/json;charset=utf-8");
	}
	
	@Test
	void checkserverType() 
	{
		String serverType = response.header("Server");
		Assert.assertEquals(serverType, "nginx/1.16.0");
	}
	
	@Test(enabled=true)
	void checkcontentEncoding() 
	{
		String contentEncoding = response.header("Content-Encoding");
		Assert.assertEquals(contentEncoding, "gzip");
	}
	
	@AfterClass
	void tearDown() 
	{
		logger.info("********* Finished TC004_Put_Employee_Record *********");
	}
	

	
	
}
