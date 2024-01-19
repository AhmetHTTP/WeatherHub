package com.weatherhub.WeatherHub.controller;

import com.weatherhub.WeatherHub.models.RadioStation;
import com.weatherhub.WeatherHub.services.RadioStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/radio-stations")
public class RadioStationController {

    private final RadioStationService radioStationService;

    @Autowired
    public RadioStationController(RadioStationService radioStationService) {
        this.radioStationService = radioStationService;
    }

    @GetMapping("/recommendations/{weatherCondition}")
    public List<RadioStation> getRecommendedStations(@PathVariable String weatherCondition) {
        return radioStationService.getRecommendedStations(weatherCondition);
    }
}
