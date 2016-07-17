package com.marceloserpa.feignhystrix;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(url="http://localhost:9001/products", name="productsApi", fallback=ProductClientFallback.class)
public interface ProductClient {

	@RequestMapping(value="/", method=RequestMethod.GET)
	List<Product> all();
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	void add(Product product);
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	Product getById(@PathVariable("id") Long id);
}
