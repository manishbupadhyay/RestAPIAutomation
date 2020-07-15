package org.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.httpclient.RestClient;
import com.qa.util.TestUtil;

public class GetAPITest extends TestBase {
	TestBase testBase;
	String serviceUrl;
	String apiUrl;
	String url; 
	RestClient restClient;
	CloseableHttpResponse closeableHttpResponse;

	@BeforeMethod
	public void setUp(){
		testBase = new TestBase();
		serviceUrl = prop.getProperty("URL");
		apiUrl = prop.getProperty("serviceURL");

		//https://reqres.in/api/users
		url = serviceUrl + apiUrl;


	}

	@Test(priority = 1)
	public void getAPITestWithoutHeaders() throws ClientProtocolException, IOException {
		restClient = new RestClient();
		closeableHttpResponse = restClient.get(url);	

		// Status Code
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code ------> " + statusCode);
		
		// use assertion to verify status code
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200 , " Status code is not correct");

		// JSON String
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");

		// convert response string to JSON
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("Response JSON from API ------> " + responseJson);
		
		// Single value assertion
		
		// per_page value
		String perPageValue = TestUtil.getValueByJPath(responseJson, "/per_page");
		System.out.println("Per page value is : " + perPageValue);
		
		// use assertion to verify per_page
		Assert.assertEquals(Integer.parseInt(perPageValue), 6);
		
		// total
		String totalValue = TestUtil.getValueByJPath(responseJson, "/total");
		System.out.println("Total value is : " + totalValue);
		
		// use assertion to verify total value
		Assert.assertEquals(Integer.parseInt(totalValue), 12);
		
		// get the value from JSON array
		String lastName = TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");
		String id = TestUtil.getValueByJPath(responseJson, "/data[0]/id");
		String avatar = TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");
		String firstName = TestUtil.getValueByJPath(responseJson, "/data[0]/first_name");
		String email = TestUtil.getValueByJPath(responseJson, "/data[0]/email");
		
		// print all one by one
		System.out.println("Last Name : " + lastName);
		System.out.println("Id : " + id);
		System.out.println("Avatar : " + avatar);
		System.out.println("First Name : " + firstName);
		System.out.println("Email : " + email);

		// print all headers
		Header[] headersArray = closeableHttpResponse.getAllHeaders();

		// use HashMap to store value in key value
		HashMap<String, String> allHeaders = new HashMap<String, String>();

		for(Header header : headersArray) {
			allHeaders.put(header.getName(), header.getValue());
		}

		System.out.println("Headers Array ------> " + allHeaders);
	}
	
	@Test(priority = 2)
	public void getAPITestWithHeaders() throws ClientProtocolException, IOException {
		restClient = new RestClient();
		
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		//headerMap.put("Connection", "keep-alive");
		//headerMap.put("Accept-Encoding", "gzip, deflate, br");
		//headerMap.put("Accept", "*/*");
		//headerMap.put("User-Agent", "PostmanRuntime/7.26.1");
		
		closeableHttpResponse = restClient.get(url,headerMap);	

		// Status Code
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code ------> " + statusCode);
		
		// use assertion to verify status code
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200 , " Status code is not correct");

		// JSON String
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");

		// convert response string to JSON
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("Response JSON from API ------> " + responseJson);
		
		// Single value assertion
		
		// per_page value
		String perPageValue = TestUtil.getValueByJPath(responseJson, "/per_page");
		System.out.println("Per page value is : " + perPageValue);
		
		// use assertion to verify per_page
		Assert.assertEquals(Integer.parseInt(perPageValue), 6);
		
		// total
		String totalValue = TestUtil.getValueByJPath(responseJson, "/total");
		System.out.println("Total value is : " + totalValue);
		
		// use assertion to verify total value
		Assert.assertEquals(Integer.parseInt(totalValue), 12);
		
		// get the value from JSON array
		String lastName = TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");
		String id = TestUtil.getValueByJPath(responseJson, "/data[0]/id");
		String avatar = TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");
		String firstName = TestUtil.getValueByJPath(responseJson, "/data[0]/first_name");
		String email = TestUtil.getValueByJPath(responseJson, "/data[0]/email");
		
		// print all one by one
		System.out.println("Last Name : " + lastName);
		System.out.println("Id : " + id);
		System.out.println("Avatar : " + avatar);
		System.out.println("First Name : " + firstName);
		System.out.println("Email : " + email);

		// print all headers
		Header[] headersArray = closeableHttpResponse.getAllHeaders();

		// use HashMap to store value in key value
		HashMap<String, String> allHeaders = new HashMap<String, String>();

		for(Header header : headersArray) {
			allHeaders.put(header.getName(), header.getValue());
		}

		System.out.println("Headers Array ------> " + allHeaders);
	}

}
