package com.wego.flight.resources;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import com.wego.common.FileWrapper;
import com.wego.flight.databases.FlightDatabase;
import com.wego.flight.exceptions.FlightNotFoundExceptionMapper;
import com.wego.flight.models.FlightList;
import com.wego.flight.models.FlightQuery;

import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.ResourceTestRule;
import static org.assertj.core.api.Assertions.assertThat;

public class FlightResourceTest {
	
	@ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new FlightResource())
            .addProvider(new FlightNotFoundExceptionMapper())
            .build();
	
	@Before
    public void setUp() throws Exception {
		setupFlightDatabase();
    }
	
	@Test
	public void testSearchFlightWithNotEmptyResult(){
		assertResultSize("Tokyo", "Singapore", 4);
		assertResultSize("Singapore", "Tokyo", 4);
		assertResultSize("singapore", "tokyo", 4);
		assertResultSize("singapore", "tokyo", 4);
		assertResultSize("Singapore", "malaysia", 1);
		assertResultSize("Singapore", "singapore", 1);
	}	
	
	@Test
	public void testSearchFlightWithEmptyResult(){
		String exptected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><error>No airline operating between desired locations</error>";
		assertResultString("Singapore", "Indonesia", exptected);
	}
	
	@Test
	public void testSearchResultRepesentation(){
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
		Response response = resources.client().target("/flights/search")
				.request(MediaType.APPLICATION_XML_TYPE)
				.post(Entity.entity(query, MediaType.APPLICATION_XML_TYPE));
		String body = response.readEntity(String.class);
		assertThat(body).isEqualTo(expected);
	}
	
	private void setupFlightDatabase(){
		FlightDatabase database = FlightDatabase.getInstance();
		String flightDatTaCsvFilePath = ResourceHelpers.resourceFilePath("com/wego/flight/flight_data.csv");
		FileWrapper file = new FileWrapper(flightDatTaCsvFilePath, FileWrapper.LOCAL);
		database.loadDataFromCsvFile(file);
	}
}
