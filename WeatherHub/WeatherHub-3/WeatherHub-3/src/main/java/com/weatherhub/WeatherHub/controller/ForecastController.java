package com.weatherhub.WeatherHub.controller;

import java.text.ParseException;
import java.util.List;
import com.weatherhub.WeatherHub.models.Output;
import com.weatherhub.WeatherHub.services.ForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/forecast")
public class ForecastController {

    private final ForecastService forecastService;

    @Autowired
    public ForecastController(ForecastService forecastService) {
        this.forecastService = forecastService;
    }

    @GetMapping("/{city}")
    public List<Output> getFiveDayForecast(@PathVariable String city) throws ParseException {
        return forecastService.getFiveDayForecast(city);
    }
}
