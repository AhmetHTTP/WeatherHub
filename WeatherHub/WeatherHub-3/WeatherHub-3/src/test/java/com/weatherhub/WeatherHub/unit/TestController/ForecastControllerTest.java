package com.weatherhub.WeatherHub.unit.TestController;

import com.weatherhub.WeatherHub.controller.ForecastController;
import com.weatherhub.WeatherHub.models.Output;
import com.weatherhub.WeatherHub.services.ForecastService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ForecastControllerTest {

    @InjectMocks
    private ForecastController forecastController;

    @Mock
    private ForecastService forecastService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetFiveDayForecast() throws ParseException {
        // Arrange
        String city = "TestCity";
        List<Output> expectedForecast = Arrays.asList(new Output(), new Output(), new Output(), new Output(), new Output());

        // Mock the behavior of the forecastService
        when(forecastService.getFiveDayForecast(city)).thenReturn(expectedForecast);

        // Act
        List<Output> actualForecast = forecastController.getFiveDayForecast(city);

        // Assert
        assertEquals(expectedForecast, actualForecast);
    }
}
