package com.wego.flight.health;

import com.codahale.metrics.health.HealthCheck;
import com.wego.common.FileWrapper;
import java.io.BufferedReader;
import java.io.IOException;

public class FlightDatabaseInitializeHealthCheck extends HealthCheck{

	private FileWrapper file;

	public FlightDatabaseInitializeHealthCheck(FileWrapper file) {
		super();
		this.file = file;
	}

	@Override
	protected Result check() throws Exception {
		BufferedReader reader = null;
		try {
			if(file != null) reader = file.read();
		} catch (IOException ex){	
		}
		
		if (reader == null){
			return Result.unhealthy("Flight Data CSV file is not available");
		} else {
			return Result.healthy();
		}
		
	}
	
	
}
