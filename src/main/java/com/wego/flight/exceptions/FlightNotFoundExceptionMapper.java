package com.wego.flight.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

@Provider
public class FlightNotFoundExceptionMapper implements ExceptionMapper<FlightNotFoundException>{
	
	public FlightNotFoundExceptionMapper(){
		
	}
	
	@Override
	public Response toResponse(FlightNotFoundException exception) {
		String message = exception.getMessage();
		JAXBElement jaxbElement = new JAXBElement(new QName("error"), message.getClass(), message);
		return Response.status(Status.NOT_FOUND)
				.entity(jaxbElement).build();
	}
	
}
