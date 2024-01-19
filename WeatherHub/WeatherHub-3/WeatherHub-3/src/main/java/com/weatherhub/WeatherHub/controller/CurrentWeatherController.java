package com.weatherhub.WeatherHub.controller;

import java.util.List;
import com.weatherhub.WeatherHub.models.Output;
import com.weatherhub.WeatherHub.services.CurrentWeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/weather")
public class CurrentWeatherController {

    private final CurrentWeatherService currentWeatherService;

    @Autowired
    public CurrentWeatherController(CurrentWeatherService currentWeatherService) {
        this.currentWeatherService = currentWeatherService;
    }

    @GetMapping("/{city}")
    public List<Output> getCurrentWeather(@PathVariable String city) {
        return currentWeatherService.getCurrentWeather(city);
    }
}
