package com.wego.flight.exceptions;

public class FlightNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -720530111800115839L;

	public FlightNotFoundException(String message){
		super(message);
	}
}
