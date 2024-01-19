package com.weatherhub.WeatherHub.integration.IntegrationTestService;

import com.weatherhub.WeatherHub.models.RadioStation;
import com.weatherhub.WeatherHub.services.RadioStationServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class RadioStationServiceImplIntegrationTest {

    @Autowired
    private RadioStationServiceImpl radioStationService;

    @Test
    public void testGetRecommendedStations() {
        // Mock the weather condition (you can customize this for different test cases)
        String weatherCondition = "Clear";

        // Call the getRecommendedStations method and perform assertions
        List<RadioStation> recommendedStations = radioStationService.getRecommendedStations(weatherCondition);

        // You can add assertions to validate the recommendations
        assertThat(recommendedStations).isNotEmpty();
        assertThat(recommendedStations.get(0).getName()).isEqualTo("Pop Radio");
        assertThat(recommendedStations.get(0).getGenre()).isEqualTo("Pop");
        assertThat(recommendedStations.get(0).getUrl()).isEqualTo("https://www.youtube.com/watch?v=HQtFR3mhzOY");
        // Add more assertions as needed
    }
}
