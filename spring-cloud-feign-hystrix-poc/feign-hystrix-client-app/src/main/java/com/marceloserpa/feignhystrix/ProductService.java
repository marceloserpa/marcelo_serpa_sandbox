package com.marceloserpa.feignhystrix;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class ProductService {

	@Autowired
	public ProductClient client;

	public List<Product> getAll(){
		return client.all();
	}
	
	public List<Product> fallbackGetAll(){
		return new ArrayList<Product>();
	}
	
}
