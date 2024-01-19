package com.weatherhub.WeatherHub.integration.IntegrationTestService;

import com.weatherhub.WeatherHub.models.Output;
import com.weatherhub.WeatherHub.services.CurrentWeatherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
public class CurrentWeatherServiceIntegrationTest {

    @Autowired
    private CurrentWeatherService currentWeatherService;

    @Test
    public void testGetCurrentWeather() {
        // Define a city for testing
        String city = "Berlin"; // Change to the desired city

        // Call the getCurrentWeather method and perform assertions
        List<Output> weatherList = currentWeatherService.getCurrentWeather(city);

        // You can add assertions here to verify the results
        // For example:
        assertThat(weatherList).isNotEmpty();
        assertThat(weatherList.get(0).getTodaysWeather().getToday().getDescription()).isNotEmpty();
        assertThat(weatherList.get(0).getTodaysWeather().getToday().getTemp()).isGreaterThan(-100); // Assuming this range is reasonable
        // Add more assertions as needed
    }
}
