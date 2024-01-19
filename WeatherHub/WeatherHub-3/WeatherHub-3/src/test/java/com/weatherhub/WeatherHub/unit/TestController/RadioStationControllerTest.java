package com.weatherhub.WeatherHub.unit.TestController;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.weatherhub.WeatherHub.WeatherHubApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.weatherhub.WeatherHub.controller.RadioStationController;
import com.weatherhub.WeatherHub.models.RadioStation;
import com.weatherhub.WeatherHub.services.RadioStationService;
import org.springframework.test.context.ContextConfiguration;

import java.util.Arrays;
import java.util.List;


@WebMvcTest(RadioStationController.class)
@ContextConfiguration(classes = WeatherHubApplication.class)
public class RadioStationControllerTest {

    @Autowired
    private RadioStationController controller;

    @MockBean
    private RadioStationService service;

    @Test
    public void testGetRecommendedStations() {
        // Arrange
        String weatherCondition = "sunny";
        List<RadioStation> expectedStations = Arrays.asList(new RadioStation()); // Mock data
        when(service.getRecommendedStations(weatherCondition)).thenReturn(expectedStations);

        // Act
        List<RadioStation> stations = controller.getRecommendedStations(weatherCondition);

        // Assert
        assertEquals(expectedStations, stations);
        verify(service).getRecommendedStations(weatherCondition);
    }
}
