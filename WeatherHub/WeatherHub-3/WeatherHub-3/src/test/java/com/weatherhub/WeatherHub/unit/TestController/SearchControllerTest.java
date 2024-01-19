package com.weatherhub.WeatherHub.unit.TestController;
import com.weatherhub.WeatherHub.controller.SearchController;
import com.weatherhub.WeatherHub.models.Output;
import com.weatherhub.WeatherHub.services.SearchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SearchControllerTest {

    @Mock
    private SearchService searchService;

    private SearchController searchController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        searchController = new SearchController(searchService);
    }

    @Test
    public void testGetWeather_ValidCity() throws ParseException {
        // Arrange
        String city = "New York";
        List<Output> expectedWeatherData = createExpectedWeatherData();
        when(searchService.searchWeather(city)).thenReturn(expectedWeatherData);

        // Act
        ResponseEntity<?> responseEntity = searchController.getWeather(city);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedWeatherData, responseEntity.getBody());
    }

    @Test
    public void testGetWeather_ParseException() throws ParseException {
        // Arrange
        String city = "InvalidCity";
        ParseException parseException = new ParseException("Invalid date format", 0);
        when(searchService.searchWeather(city)).thenThrow(parseException);

        // Act
        ResponseEntity<?> responseEntity = searchController.getWeather(city);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Error parsing date: Invalid date format", responseEntity.getBody());
    }

    @Test
    public void testGetWeather_GenericException() throws ParseException {
        // Arrange
        String city = "CityWithException";
        Exception exception = new Exception("Something went wrong");
        when(searchService.searchWeather(city)).thenThrow(exception);

        // Act
        ResponseEntity<?> responseEntity = searchController.getWeather(city);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("An error occurred: Something went wrong", responseEntity.getBody());
    }

    private List<Output> createExpectedWeatherData() {
        // Create and return a sample list of Output objects for testing
        // Modify this method based on your actual data structure
        return List.of(
                new Output(/* Initialize with sample data */),
                new Output(/* Initialize with sample data */)
        );
    }
}
