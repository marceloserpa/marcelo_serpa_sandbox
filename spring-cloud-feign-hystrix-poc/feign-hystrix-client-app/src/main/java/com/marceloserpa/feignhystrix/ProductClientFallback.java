package com.marceloserpa.feignhystrix;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
class ProductClientFallback implements ProductClient{

	@Override
	public List<Product> all() {
		System.out.println("Fallback method");
		return Arrays.asList();
	}

	@Override
	public void add(Product product) {
		System.out.println("Fallback method");
	}

	@Override
	public Product getById(Long id) {
		System.out.println("Fallback method");
		return null;
	}

}
