package com.weatherhub.WeatherHub.unit.TestController;

import com.weatherhub.WeatherHub.controller.CurrentWeatherController;
import com.weatherhub.WeatherHub.models.Output;
import com.weatherhub.WeatherHub.services.CurrentWeatherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CurrentWeatherControllerTest {

    @InjectMocks
    private CurrentWeatherController currentWeatherController;

    @Mock
    private CurrentWeatherService currentWeatherService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCurrentWeather() {
        // Arrange
        String city = "TestCity";
        List<Output> expectedWeather = Arrays.asList(new Output());

        // Mock the behavior of the currentWeatherService
        when(currentWeatherService.getCurrentWeather(city)).thenReturn(expectedWeather);

        // Act
        List<Output> actualWeather = currentWeatherController.getCurrentWeather(city);

        // Assert
        assertEquals(expectedWeather, actualWeather);
    }
}
