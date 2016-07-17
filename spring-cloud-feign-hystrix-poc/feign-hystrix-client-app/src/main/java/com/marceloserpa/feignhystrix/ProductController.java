package com.marceloserpa.feignhystrix;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="client/products")
public class ProductController {

	@Autowired 
	ProductClient service;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public List<Product> list(){
		return service.all();
	}
	
}
