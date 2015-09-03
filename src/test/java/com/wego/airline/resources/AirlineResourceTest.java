package com.wego.airline.resources;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import org.junit.ClassRule;
import org.junit.Test;

import com.wego.airline.databases.AirlineDatabase;
import com.wego.airline.models.AirlineList;
import com.wego.airline.models.AirlineQuery;

import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.ResourceTestRule;
import static org.assertj.core.api.Assertions.assertThat;

public class AirlineResourceTest {
	
	@ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new AirlineResource())
            .build();
	
	@Test
	public void testSearchAirlineWithNotEmptyResult(){
		setupAirlineDatabase();
		assertResultSize("Tokyo", "Singapore", 4);
		assertResultSize("Singapore", "Tokyo", 4);
		assertResultSize("singapore", "tokyo", 4);
		assertResultSize("singapore", "tokyo", 4);
		assertResultSize("Singapore", "malaysia", 1);
	}
	
	@Test
	public void testSearchAirlineWithEmptyResult(){
		setupAirlineDatabase();
		String exptected = "<error>No airline operating between desired locations</error>";
		assertResultString("Singapore", "Indonesia", exptected);
	}
	
	@Test
	public void testSearchResultRepesentation(){
		setupAirlineDatabase();
		String exptected = "<root><airlines>Malaysian Airline</airlines></root>";
		assertResultString("Singapore", "Malaysia", exptected);
	}
	
	private void assertResultSize(String from, String to, int expected){
		AirlineQuery query = new AirlineQuery(from, to);
		AirlineList list = resources.client().target("/airlines/search")
				.request(MediaType.APPLICATION_XML_TYPE)
				.post(Entity.entity(query, MediaType.APPLICATION_XML_TYPE), AirlineList.class);
		assertThat(list.size()).isEqualTo(expected);
	}
	
	private void assertResultString(String from, String to, String expected){
		AirlineQuery query = new AirlineQuery(from, to);
		String result = resources.client().target("/airlines/search")
				.request(MediaType.APPLICATION_XML_TYPE)
				.post(Entity.entity(query, MediaType.APPLICATION_XML_TYPE), String.class);
		assertThat(result).isEqualTo(expected);
	}
	
	private void setupAirlineDatabase(){
		AirlineDatabase database = AirlineDatabase.getInstance();
	    String airlineDatTaCsvFilePath = ResourceHelpers.resourceFilePath("com/wego/airline/airline_data.csv");
	    database.loadDataFromCsvFile(airlineDatTaCsvFilePath);
	}
}
