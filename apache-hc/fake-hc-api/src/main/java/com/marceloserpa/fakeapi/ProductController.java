package com.marceloserpa.fakeapi;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("products")
public class ProductController {

	private List<Product> products = new ArrayList<Product>();
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public List<Product> getAll(){
		return products;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public Product getOne(@PathVariable("id") Long id){
		Optional<Product> product = this.products.stream().filter(p -> p.getId().equals(id)).findFirst();
		return product.orElseThrow(() -> new RuntimeException("not found"));
	}	
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public void save(@RequestBody Product product){
		System.out.println("Saving product...");
		products.add(product);		
	}			
	
}
