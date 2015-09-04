package com.wego.flight;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;

public class WegoConfig extends Configuration {
	static String FLIGHT_DATA_CSV_LOCAL_TYPE = "local";
	static String FLIGHT_DATA_CSV_REMOTE_TYPE = "remote";
	
	@NotEmpty
    private String flightDataCsvFilePath;
	
	@NotEmpty
    private String flightDataCsvFileType;
	
	@JsonProperty
	public String getFlightDataCsvFilePath() {
		return flightDataCsvFilePath;
	}

	@JsonProperty
	public void setFlightDataCsvFilePath(String flightDataCsvFilePath) {
		this.flightDataCsvFilePath = flightDataCsvFilePath;
	}

	@JsonProperty
	public String getFlightDataCsvFileType() {
		return flightDataCsvFileType;
	}

	@JsonProperty
	public void setFlightDataCsvFileType(String flightDataCsvFileType) {
		this.flightDataCsvFileType = flightDataCsvFileType;
	}

	
}

