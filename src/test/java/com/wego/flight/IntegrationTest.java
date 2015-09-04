package com.wego.flight;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import com.wego.flight.WegoApp;
import com.wego.flight.WegoConfig;

import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import static org.assertj.core.api.Assertions.assertThat;

public class IntegrationTest {
	private static final String CONFIG_PATH = ResourceHelpers.resourceFilePath("test-wego.yml");
	
	@ClassRule
    public static final DropwizardAppRule<WegoConfig> RULE = new DropwizardAppRule<>(
    		WegoApp.class, CONFIG_PATH);
	
	private Client client;
	
	@Before
    public void setUp() throws Exception {
        client = ClientBuilder.newClient();
    }
	
	@After
    public void tearDown() throws Exception {
        client.close();
    }
	
	@Test
	public void testHelloWorld() throws Exception {
		final String saying = client.target("http://localhost:" + RULE.getLocalPort() + "/flights/say-hello")
              .request()
              .get(String.class);
		assertThat(saying).isEqualTo("Hello");
	}
}
