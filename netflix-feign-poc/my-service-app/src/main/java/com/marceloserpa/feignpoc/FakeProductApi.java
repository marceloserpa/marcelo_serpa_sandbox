package com.marceloserpa.feignpoc;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Path("/")
public interface FakeProductApi {
	
	@GET
	@Path("/")
	List<ProductRestModel> all();
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	void add(ProductRestModel product);

	@GET
	@Path("/{id}")
	ProductRestModel getById(@PathParam("id") Long id);
}
