package com.mytheresa.util;
import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.logging.Level; 
import java.util.logging.Logger; 


public class RestSearch {
	public static String PROPERTIES_PATH = System.getProperty("user.dir") + File.separator+"src/test/resources/";
	static PropertiesRead prop = new PropertiesRead(PROPERTIES_PATH + File.separator + "mytheresa.properties");

	private final static Logger logger =  
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME); 
	//public static void main(String [] srg) {
	@Test
	void Search() {
		String url = prop.getProperty("base_url");	
		String endpoint = prop.getProperty("end_point");

		logger.log(Level.INFO,"");

		RestAssured.baseURI = url;
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.GET,endpoint);
		System.out.println(response.asString());

		int statusCode = response.getStatusCode(); // Gettng status code
		Assert.assertEquals(statusCode, 200);
		logger.info("verified status "+statusCode);


		String responseBody = response.getBody().asString();//get response and verify with desired response body
		Assert.assertEquals(responseBody.contains("total_count"), true);
		Assert.assertEquals(responseBody.contains("incomplete_results"), true);
		Assert.assertEquals(responseBody.contains("items"), true);

		logger.info("verified response body"+ "incomplete_results"+" "+"total_count"+" "+"items");


	}


}
