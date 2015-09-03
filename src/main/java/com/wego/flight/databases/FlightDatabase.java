package com.wego.flight.databases;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.wego.flight.models.Flight;

public class FlightDatabase {
	private static FlightDatabase instance = null;
	static final String  KEY_SEPARATOR = "-9890awst-";
	public static int CSV_LOAD_LOCAL_TYPE = 0;
	public static int CSV_LOAD_REMOTE_TYPE = 1;
	
	private HashMap<String, List<Flight>> map;
	
	public FlightDatabase(){
		
	}
	
	public static FlightDatabase getInstance(){
		if (instance == null){
			instance = new FlightDatabase();
		}
		return instance;
	}
	
	public List<Flight> getListOfFlight(String from, String to){
		String key = constructFlightKey(from, to);
		return map.get(key);
	}
	
	public void loadDataFromCsvFile(String csvFilePath, int type){
		map = new HashMap<String, List<Flight>>();
		try {
			BufferedReader bufferedReader;
			if (type == FlightDatabase.CSV_LOAD_LOCAL_TYPE){
				bufferedReader = new BufferedReader(new FileReader(csvFilePath));
			} else {
				URL fileUrl = new URL(csvFilePath);
				bufferedReader = new BufferedReader(new InputStreamReader(fileUrl.openStream()));
			}
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				List<String> input = Arrays.asList(line.split(",")); 
				if(input.size() < 3) continue;
				String from = input.get(0).trim();
				String to = input.get(1).trim();
				String airline = input.get(2).trim();
				addFlight(new Flight(from, to, airline));
				if(!from.equals(to)) addFlight(new Flight(to, from, airline));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void addFlight(Flight flight){
		String key = constructFlightKey(flight.getFrom(), flight.getTo());
		if(map.get(key) == null){
			map.put(key, new ArrayList<Flight>());
		}
		map.get(key).add(flight);
	}
	
	private String constructFlightKey(String from, String to){
		return from.toLowerCase() + FlightDatabase.KEY_SEPARATOR + to.toLowerCase();
	}
}
