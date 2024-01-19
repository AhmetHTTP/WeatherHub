package com.weatherhub.WeatherHub.integration.IntegrationTestService;

import com.weatherhub.WeatherHub.services.SearchService;
import com.weatherhub.WeatherHub.services.CurrentWeatherService;
import com.weatherhub.WeatherHub.services.ForecastService;
import com.weatherhub.WeatherHub.models.Output;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class SearchServiceIntegrationTest {

    @Autowired
    private SearchService searchService;

    @Autowired
    private CurrentWeatherService currentWeatherService;

    @Autowired
    private ForecastService forecastService;

    @Test
    public void testSearchWeather() throws ParseException {
        // Arrange
        String city = "TestCity";

        // Act
        List<Output> weatherData = searchService.searchWeather(city);

        // Assert
        // Gerçek verileri ve servisleri kullanarak doğrulamaları yapın
        assertTrue(weatherData.size() > 0);

        // Örnek doğrulamalar:
        List<Output> currentWeatherData = currentWeatherService.getCurrentWeather(city);
        List<Output> forecastData = forecastService.getFiveDayForecast(city);

        // currentWeatherService'den dönen verinin en az bir veri içermesi gerekiyor
        assertTrue(currentWeatherData.size() > 0);

        // forecastService'den dönen verinin en az bir veri içermesi gerekiyor
        assertTrue(forecastData.size() > 0);



    }
}
