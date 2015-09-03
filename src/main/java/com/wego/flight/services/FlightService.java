package com.wego.flight.services;

import java.util.List;

import com.wego.flight.databases.FlightDatabase;
import com.wego.flight.models.Flight;
import com.wego.flight.models.FlightList;
import com.wego.flight.models.FlightQuery;

public class FlightService {
	
	public FlightList queryFlight(FlightQuery query){
		FlightDatabase database = FlightDatabase.getInstance();
		List<Flight> list = database.getListOfFlight(query.getFrom(), query.getTo());
		FlightList ans = new FlightList();
		if (list == null || list.isEmpty()){
			return ans;
		} else {
			for(Flight flight: list){
				ans.addFlight(flight);
			}
			return ans;
		}
		
	}
}
