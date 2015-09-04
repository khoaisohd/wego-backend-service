package com.wego.flight.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.wego.flight.models.FlightList;
import com.wego.flight.models.FlightQuery;
import com.wego.flight.services.FlightService;

@Path("/flights")
public class FlightResource{

	private FlightService flightService = new FlightService();
	@GET
	@Path("/say-hello")
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHello(){
		return "Hello";
	}
	
	@POST
	@Path("/search")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_XML)
	public FlightList searchFlight(FlightQuery query){
		return flightService.queryFlight(query);
	}
}
