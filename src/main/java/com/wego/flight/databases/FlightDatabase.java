package com.wego.flight.databases;

public class FlightDatabase {
	private static FlightDatabase instance = null;
	
	public FlightDatabase(){
		
	}
	
	public static FlightDatabase getInstance(){
		if (instance == null){
			instance = new FlightDatabase();
		}
		return instance;
	}
	
	public void loadDataFromCsvFile(String csv_file_path){
		
	}
}
