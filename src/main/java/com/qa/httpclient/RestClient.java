package com.qa.httpclient;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {

	// 1. GET Method
	public void get(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient	= HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url); // http get request

		// Status Code
		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpget); // hit the GET url
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code ------> " + statusCode);

		// JSON String
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");

		// convert response string to JSON
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("Response JSON from API ------> " + responseJson);

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
