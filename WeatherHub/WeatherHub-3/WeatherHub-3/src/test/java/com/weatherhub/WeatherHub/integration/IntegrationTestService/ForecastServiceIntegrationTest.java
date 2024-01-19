package com.weatherhub.WeatherHub.integration.IntegrationTestService;

import com.weatherhub.WeatherHub.services.ForecastService;
import com.weatherhub.WeatherHub.models.Output;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;
import java.text.ParseException;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ForecastServiceIntegrationTest {

    @MockBean
    private RestTemplate restTemplate;

    @Test
    public void testGetFiveDayForecast() throws ParseException {
        // Sample JSON data to mock the API response
        String jsonResponse = "{ your sample JSON data here }"; // Replace with actual JSON data

        // Mock the behavior of RestTemplate's getForObject method
        Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(String.class)))
                .thenReturn(jsonResponse);

        ForecastService forecastService = new ForecastService();

        // Call the getFiveDayForecast method and perform assertions
        List<Output> weatherList = forecastService.getFiveDayForecast("Bursa");

        // You can add assertions to validate the forecast data
        assertThat(weatherList).isNotEmpty();
        // Add more assertions as needed
    }
}
