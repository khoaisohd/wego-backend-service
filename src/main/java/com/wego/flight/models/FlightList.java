package com.wego.flight.models;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "root")
public class FlightList {

	
	@XmlElement(name = "airlines")
	private List<String> airlines;
	
	public FlightList(){
		airlines = new ArrayList<String>();
	}
	
	public void addFlight(Flight flight){
		if(flight != null){
			airlines.add(flight.getAirline());	
		}
	}
	
	
	public int size(){
		return airlines.size();
	}
}
