package com.marceloserpa.feignhystrix;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="client/products")
public class ProductController {

	@Autowired 
	ProductClient client;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public List<Product> list(){
		return client.all();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public Product getById(@PathVariable("id") Long id){
		return client.getById(id);
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public void save(@RequestBody Product product){
		client.add(product);;
	}	
}
