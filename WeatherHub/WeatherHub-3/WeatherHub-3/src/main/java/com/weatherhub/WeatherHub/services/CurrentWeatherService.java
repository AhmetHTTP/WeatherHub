package com.weatherhub.WeatherHub.services;

import com.weatherhub.WeatherHub.models.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CurrentWeatherService {

    private static final String API_KEY = "d88bf8f8925bad27c5d010a0490de59c";
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?mode=json&units=metric&appid=" + API_KEY;

    public List<Output> getCurrentWeather(String city) {
        String url = BASE_URL + "&q=" + city;
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);

        List<Output> weatherList = new ArrayList<>();
        JSONObject root = new JSONObject(result);
        JSONArray weatherObject = root.getJSONArray("weather");

        String description = null;
        String WeatherCondition = null;
        double temp;
        double temp_min;
        double temp_max;
        double pressure;
        int humidity;

        for (int i = 0; i < weatherObject.length(); i++) {
            JSONObject elementInArray = weatherObject.getJSONObject(i);
            description = elementInArray.getString("description");
            WeatherCondition = elementInArray.getString("main");
        }

        JSONObject main = root.getJSONObject("main");

        temp = (int) main.getFloat("temp");
        pressure = main.getInt("pressure");
        humidity = main.getInt("humidity");
        temp_min = (int) main.getFloat("temp_min");
        temp_max = (int) main.getFloat("temp_max");

        TodaysWeather tw = new TodaysWeather();
        Output e = new Output();
        Today t = new Today();

        t.setDescription(description);
        t.setHumidity(humidity);
        t.setMain(WeatherCondition);
        t.setPressure(pressure);
        t.setTemp(temp);
        t.setTempMax(temp_max);
        t.setTempMin(temp_min);

        tw.setToday(t);
        e.setTodaysWeather(tw);

        weatherList.add(e);
        return weatherList;
    }
}
