package com.marceloserpa.feignpoc;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.jaxrs.JAXRSContract;

@Path("/")
public interface FakeProductApiWithBuilder {

	@GET @Path("/")
	List<ProductRestModel> all();
	
	@POST @Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	void add(ProductRestModel product);

	@GET @Path("/{id}")
	ProductRestModel getById(@PathParam("id") Long id);
	
	static FakeProductApiWithBuilder connect(){
		return Feign.builder()
				.contract(new JAXRSContract())
				.decoder(new JacksonDecoder())
				.encoder(new JacksonEncoder())
				.target(FakeProductApiWithBuilder.class, "http://localhost:8080/products");
	}
	
}
