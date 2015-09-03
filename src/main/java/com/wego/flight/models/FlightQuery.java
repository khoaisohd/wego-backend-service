package com.wego.flight.models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="root")
public class FlightQuery {

	private String from;
	
	private String to;

	public FlightQuery(){
		
	}
	
	public FlightQuery(String from, String to) {
		super();
		this.from = from;
		this.to = to;
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
	
	
}
