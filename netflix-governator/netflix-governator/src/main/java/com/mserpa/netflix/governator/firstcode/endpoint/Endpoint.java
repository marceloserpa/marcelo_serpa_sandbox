package com.mserpa.netflix.governator.firstcode.endpoint;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.inject.Singleton;
import com.mserpa.netflix.governator.firstcode.calc.Calculator;


@Singleton
@Path("/")
public class Endpoint {

	@Inject
	private Calculator calculator;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{a}/{b}")
	public String calc(@PathParam("a") Double a, @PathParam("b") Double b) {
		return calculator.sum(a, b).toString();
	}
	
}
