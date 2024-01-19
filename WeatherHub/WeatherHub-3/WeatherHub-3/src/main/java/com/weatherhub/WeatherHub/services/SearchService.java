package com.weatherhub.WeatherHub.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.weatherhub.WeatherHub.models.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {
    @Autowired
    private CurrentWeatherService currentWeatherService;

    @Autowired
    private ForecastService forecastService;

    public List<Output> searchWeather(String city) throws ParseException {
        List<Output> results = new ArrayList<>();

        List<Output> currentWeatherResults = currentWeatherService.getCurrentWeather(city);
        results.addAll(currentWeatherResults);

        List<Output> forecastResults = forecastService.getFiveDayForecast(city);
        results.addAll(forecastResults);

        return results;
    }
}


