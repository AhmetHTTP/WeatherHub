package com.weatherhub.WeatherHub.services;

import java.util.ArrayList;
import java.util.List;

import com.weatherhub.WeatherHub.models.RadioStation;
import org.springframework.stereotype.Service;

@Service
public class RadioStationServiceImpl implements RadioStationService {

    public List<RadioStation> getRecommendedStations(String weatherCondition) {
        // Hava durumuna göre önerilen radyo istasyonlarını seçerek liste oluşturun
        List<RadioStation> recommendedStations = new ArrayList<>();

        // Örnek: Eğer hava güneşli ise pop müzik, yağmurlu ise slow müzik önerilebilir
        if (weatherCondition.equalsIgnoreCase("Clear")) {
            RadioStation popRadio = new RadioStation();
            popRadio.setName("Pop Radio");
            popRadio.setGenre("Pop");
            popRadio.setUrl("https://www.youtube.com/watch?v=HQtFR3mhzOY");
            recommendedStations.add(popRadio);

        } else if (weatherCondition.equalsIgnoreCase("Rain")) {
            RadioStation slowHitsRadio = new RadioStation();
            slowHitsRadio.setName("Slow Hits");
            slowHitsRadio.setGenre("Slow");
            slowHitsRadio.setUrl("https://www.youtube.com/watch?v=Dx5qFachd3A");
            recommendedStations.add(slowHitsRadio);

        } else if (weatherCondition.equalsIgnoreCase("Snow")) {
            RadioStation christmassRadio = new RadioStation();
            christmassRadio.setName("Christmas Songs");
            christmassRadio.setGenre("Christmas Music");
            christmassRadio.setUrl("https://www.youtube.com/watch?v=fmgF0HO1fT0");
            recommendedStations.add(christmassRadio);

        } else if (weatherCondition.equalsIgnoreCase("Clouds")) {
            RadioStation classicRadio = new RadioStation();
            classicRadio.setName("Classic Songs");
            classicRadio.setGenre("Classic Music");
            classicRadio.setUrl("https://www.youtube.com/watch?v=zCj1i7Hkki4");
            recommendedStations.add(classicRadio);

        }

        return recommendedStations;
    }

}
