package com.weatherhub.WeatherHub.unit.TestService;

import com.weatherhub.WeatherHub.models.RadioStation;
import com.weatherhub.WeatherHub.services.RadioStationService;
import com.weatherhub.WeatherHub.services.RadioStationServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RadioStationServiceTest {

    @InjectMocks
    private RadioStationServiceImpl radioStationService;

    // Doğru servisi enjekte edin

    @Test
    public void testGetRecommendedStations_ClearWeather() {
        // Arrange
        String weatherCondition = "Clear";

        // Act
        List<RadioStation> recommendedStations = radioStationService.getRecommendedStations(weatherCondition);

        // Assert
        assertEquals(1, recommendedStations.size());
        RadioStation popRadio = recommendedStations.get(0);
        assertEquals("Pop Radio", popRadio.getName());
        assertEquals("Pop", popRadio.getGenre());
        assertEquals("https://www.youtube.com/watch?v=HQtFR3mhzOY", popRadio.getUrl());
    }

    @Test
    public void testGetRecommendedStations_RainyWeather() {
        // Arrange
        String weatherCondition = "Rain";

        // Act
        List<RadioStation> recommendedStations = radioStationService.getRecommendedStations(weatherCondition);

        // Assert
        assertEquals(1, recommendedStations.size());
        RadioStation slowHitsRadio = recommendedStations.get(0);
        assertEquals("Slow Hits", slowHitsRadio.getName());
        assertEquals("Slow", slowHitsRadio.getGenre());
        assertEquals("https://www.youtube.com/watch?v=Dx5qFachd3A", slowHitsRadio.getUrl());
    }

    // Diğer hava koşulları için benzer testler ekleyebilirsiniz.
}
