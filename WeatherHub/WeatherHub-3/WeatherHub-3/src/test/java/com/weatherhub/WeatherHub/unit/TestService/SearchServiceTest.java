package com.weatherhub.WeatherHub.unit.TestService;

import com.weatherhub.WeatherHub.models.Output;
import com.weatherhub.WeatherHub.services.CurrentWeatherService;
import com.weatherhub.WeatherHub.services.ForecastService;
import com.weatherhub.WeatherHub.services.SearchService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SearchServiceTest {

    @InjectMocks
    private SearchService searchService;

    @Mock
    private CurrentWeatherService currentWeatherService;

    @Mock
    private ForecastService forecastService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSearchWeather() throws ParseException {
        // Örnek verileri oluşturun
        String city = "Istanbul";
        List<Output> currentWeatherResults = new ArrayList<>();
        List<Output> forecastResults = new ArrayList<>();

        // CurrentWeatherService ve ForecastService için sahte sonuçlar hazırlayın
        when(currentWeatherService.getCurrentWeather(city)).thenReturn(currentWeatherResults);
        when(forecastService.getFiveDayForecast(city)).thenReturn(forecastResults);

        // searchWeather metodunu çağırın
        List<Output> results = searchService.searchWeather(city);

        // Sonuçları kontrol edin
        // Örnek olarak, results'ın beklenen boyutlara sahip olduğunu kontrol edebilirsiniz
        assertEquals(currentWeatherResults.size() + forecastResults.size(), results.size());

        // CurrentWeatherService ve ForecastService'nin çağrıldığını kontrol edin
        verify(currentWeatherService, times(1)).getCurrentWeather(city);
        verify(forecastService, times(1)).getFiveDayForecast(city);
    }
}

