package com.wego.flight.health;

import com.codahale.metrics.health.HealthCheck;
import com.wego.common.FileWrapper;

public class FlightDatabaseInitializeHealthCheck extends HealthCheck{

	private FileWrapper file;

	public FlightDatabaseInitializeHealthCheck(FileWrapper file) {
		super();
		this.file = file;
	}


	@Override
	protected Result check() throws Exception {
		if (file == null){
			return Result.unhealthy("Flight Data CSV file is not available");
		}
		String filePath = file.getPath();
		String fileType = file.getType();
		
		if(filePath == null || filePath.trim().isEmpty()){
			return Result.unhealthy("Flight Data CSV file path is not available");
		}
		
		if (fileType != FileWrapper.LOCAL && fileType != FileWrapper.REMOTE){
			return Result.unhealthy("Flight Data CSV file type is not available");
		}
		return Result.healthy();
	}
	
	
}
