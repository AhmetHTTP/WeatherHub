package com.weatherhub.WeatherHub.integration.IntegrationTestController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import com.weatherhub.WeatherHub.WeatherHubApplication; // Örnek bir ana uygulama sınıfı

@SpringBootTest(classes = WeatherHubApplication.class) // Ana uygulama sınıfını burada belirtin
public class RadioStationControllerIntegrationTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@BeforeEach
	public void setup() {
		mockMvc = webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testGetRecommendedStations() throws Exception {
		mockMvc.perform(get("/radio-stations/recommendations/Clear"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isNotEmpty());
	}
}
