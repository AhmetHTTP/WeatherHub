package com.weatherhub.WeatherHub.services;



import com.weatherhub.WeatherHub.models.RadioStation;

import java.util.List;

public interface RadioStationService {
    List<RadioStation> getRecommendedStations(String weatherCondition);
}
