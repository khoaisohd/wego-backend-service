package com.wego.airline.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/airlines")
public class AirlineResource{

	@GET
	@Path("/say-hello")
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHello(){
		return "Hello";
	}
}
