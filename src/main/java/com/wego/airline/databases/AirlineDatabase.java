package com.wego.airline.databases;

public class AirlineDatabase {
	private static AirlineDatabase instance = null;
	
	public AirlineDatabase(){
		
	}
	
	public static AirlineDatabase getInstance(){
		if (instance == null){
			instance = new AirlineDatabase();
		}
		return instance;
	}
	
	public void loadDataFromCsvFile(String csv_file_path){
		
	}
}
