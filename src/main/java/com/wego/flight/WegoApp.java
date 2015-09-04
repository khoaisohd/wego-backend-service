package com.wego.flight;

import com.wego.common.FileWrapper;
import com.wego.flight.databases.FlightDatabase;
import com.wego.flight.exceptions.FlightNotFoundExceptionMapper;
import com.wego.flight.health.FlightDatabaseInitializeHealthCheck;
import com.wego.flight.resources.FlightResource;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;


public class WegoApp extends Application<WegoConfig> {
    public static void main(String[] args) throws Exception {
        new WegoApp().run(args);
    }

    @Override
    public void run(WegoConfig configuration, Environment environment) {
    	healthCheckAndSetUpFlightDatabase(configuration, environment);
    	environment.jersey().register(new FlightResource());
    	environment.jersey().register(new FlightNotFoundExceptionMapper());
    }
    
    private void healthCheckAndSetUpFlightDatabase(WegoConfig configuration, Environment environment){
    	String filePath = configuration.getFlightDataCsvFilePath();
    	String fileType = configuration.getFlightDataCsvFileType();
    	FileWrapper file = new FileWrapper(filePath, fileType);
    	environment.healthChecks().register("FlightDatabaseInitialize", 
    			new FlightDatabaseInitializeHealthCheck(file));
    	
    	FlightDatabase flightDatabase = FlightDatabase.getInstance();
    	flightDatabase.loadDataFromCsvFile(file);
    }

}
