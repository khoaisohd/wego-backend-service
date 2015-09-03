package com.wego.flight.models;

public class Flight {
	
	private String from;
	
	private String to;
	
	private String airline;

	public Flight(String from, String to, String airline) {
		super();
		this.from = from;
		this.to = to;
		this.airline = airline;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}
	
	
}
