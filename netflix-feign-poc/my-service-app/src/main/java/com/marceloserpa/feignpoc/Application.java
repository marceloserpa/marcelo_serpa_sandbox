package com.marceloserpa.feignpoc;

import java.util.Arrays;
import java.util.List;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.jaxrs.JAXRSContract;

public class Application {

	public static void main(String[] args) {
		
		FakeProductApi fakeApi = Feign.builder()
				.contract(new JAXRSContract())
				.decoder(new JacksonDecoder())
				.encoder(new JacksonEncoder())
				.target(FakeProductApi.class, "http://localhost:8080/products");
		
		List<ProductRestModel> toAdd = Arrays.asList(
				new ProductRestModel(1L, "The Shining - Book", 10.5D), 
				new ProductRestModel(2L, "The Exorcist - Book", 7.3D));
		
		toAdd.stream().forEach(book -> fakeApi.add(book));
		
		fakeApi.all().stream().forEach(product -> System.out.println(product.getName()));
		
		System.out.println("============");
		System.out.println(fakeApi.getById(1L).getName());

	}
	
}
