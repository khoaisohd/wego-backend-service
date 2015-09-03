package com.wego.flight.resources;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import org.junit.ClassRule;
import org.junit.Test;

import com.wego.flight.databases.FlightDatabase;
import com.wego.flight.models.FlightList;
import com.wego.flight.models.FlightQuery;

import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.ResourceTestRule;
import static org.assertj.core.api.Assertions.assertThat;

public class FlightResourceTest {
	
	@ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new FlightResource())
            .build();
	
	@Test
	public void testSearchFlightWithNotEmptyResult(){
		setupFlightDatabase();
		assertResultSize("Tokyo", "Singapore", 4);
		assertResultSize("Singapore", "Tokyo", 4);
		assertResultSize("singapore", "tokyo", 4);
		assertResultSize("singapore", "tokyo", 4);
		assertResultSize("Singapore", "malaysia", 1);
	}	
	
	@Test
	public void testSearchFlightWithEmptyResult(){
		setupFlightDatabase();
		String exptected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><root><error>No airline operating between desired locations</error></root>";
		assertResultString("Singapore", "Indonesia", exptected);
	}
	
	@Test
	public void testSearchResultRepesentation(){
		setupFlightDatabase();
		String exptected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><root><airlines>Malaysian Airline</airlines></root>";
		assertResultString("Singapore", "Malaysia", exptected);
	}
	
	private void assertResultSize(String from, String to, int expected){
		FlightQuery query = new FlightQuery(from, to);
		FlightList list = resources.client().target("/flights/search")
				.request(MediaType.APPLICATION_XML_TYPE)
				.post(Entity.entity(query, MediaType.APPLICATION_XML_TYPE), FlightList.class);
		assertThat(list.size()).isEqualTo(expected);
	}
	
	private void assertResultString(String from, String to, String expected){
		FlightQuery query = new FlightQuery(from, to);
		String result = resources.client().target("/flights/search")
				.request(MediaType.APPLICATION_XML_TYPE)
				.post(Entity.entity(query, MediaType.APPLICATION_XML_TYPE), String.class);
		assertThat(result).isEqualTo(expected);
	}
	
	private void setupFlightDatabase(){
		FlightDatabase database = FlightDatabase.getInstance();
		String flightDatTaCsvFilePath = ResourceHelpers.resourceFilePath("com/wego/flight/flight_data.csv");
		database.loadDataFromCsvFile(flightDatTaCsvFilePath, FlightDatabase.CSV_LOAD_LOCAL_TYPE);
	}
}
