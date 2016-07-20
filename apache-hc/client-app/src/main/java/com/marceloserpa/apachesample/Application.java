package com.marceloserpa.apachesample;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Application {

	public static void main(String[] args) {
		
		//Executing POST request
		try(CloseableHttpClient httpClient = HttpClientBuilder.create().build()){
			HttpPost request = new HttpPost("http://localhost:9001/products/");
			
			Product product = new Product();
			product.setName("My Book test");
			product.setPrice(10.5D);
			String json = new GsonBuilder().create().toJson(product);			
	        StringEntity params = new StringEntity(json);
	        request.addHeader("content-type", "application/json");
	        request.setEntity(params);
	        
	        System.out.println("Send request to product api: " + json);
	        HttpResponse result = httpClient.execute(request);
	        int status = result.getStatusLine().getStatusCode();
	        System.out.println("The status of response is: " + status);
	        
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Executing GET request	
		try(CloseableHttpClient httpClient = HttpClientBuilder.create().build()){
			HttpGet httpGet = new HttpGet("http://localhost:9001/products/");
			httpGet.addHeader("User-Agent", "Mozilla/5.0");
			CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
			HttpEntity entity = httpResponse.getEntity();
			InputStream content = entity.getContent();
			Reader reader = new InputStreamReader(content);
			
			Gson gson = new GsonBuilder().create();
			List<Product> products = new ArrayList<Product>();
			products = Arrays.asList(gson.fromJson(reader, Product[].class));
			content.close();
			httpResponse.close();
			System.out.println("\nListing all products");
			products.stream().forEach(product -> System.out.println(product.getName() + " : R$ " + product.getPrice()));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}	
	
}
