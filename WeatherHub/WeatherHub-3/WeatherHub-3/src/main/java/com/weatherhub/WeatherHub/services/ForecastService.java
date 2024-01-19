package com.weatherhub.WeatherHub.services;

import com.weatherhub.WeatherHub.models.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class ForecastService {

    private static final String API_KEY = "d88bf8f8925bad27c5d010a0490de59c";
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/forecast?mode=json&units=metric&appid=" + API_KEY;

    public List<Output> getFiveDayForecast(String city) throws ParseException {
        String url = BASE_URL + "&q=" + city;
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);

        List<Output> weatherList = new ArrayList<>();
        JSONObject root = new JSONObject(result);
        JSONArray weatherObject = root.getJSONArray("list");
        SimpleDateFormat dfoutput2 = new SimpleDateFormat("HH");
        SimpleDateFormat dfoutput1 = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat dfinput = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (int i = 0; i < weatherObject.length(); i += 48) { // 12 saatte bir güncellenir
            JSONObject arrayElement = weatherObject.getJSONObject(i);
            JSONObject main = arrayElement.getJSONObject("main");

            double temp = (int) main.getFloat("temp");
            int pressure = main.getInt("pressure");
            int humidity = main.getInt("humidity");
            int temp_min = main.getInt("temp_min");
            int temp_max = main.getInt("temp_max");
            int temp_kf = main.getInt("temp_kf");
            int sea_level = main.getInt("sea_level");
            int grnd_level = main.getInt("grnd_level");

            String description = arrayElement.getJSONArray("weather").getJSONObject(0).getString("description");
            String icon = arrayElement.getJSONArray("weather").getJSONObject(0).getString("icon");
            String WeatherCondition = arrayElement.getJSONArray("weather").getJSONObject(0).getString("main");
            int id = arrayElement.getJSONArray("weather").getJSONObject(0).getInt("id");

            String date = arrayElement.getString("dt_txt");
            java.util.Date date1 = dfinput.parse(date);

            ForecastWeather fw = new ForecastWeather();
            Date dt = new Date();
            Main mn = new Main();
            Output e = new Output();
            Time t = new Time();
            Weather w = new Weather();

            mn.setTemp(temp);
            mn.setPressure(pressure / 10);
            mn.setHumidity(humidity);
            mn.setGrndLevel(grnd_level);
            mn.setSeaLevel(sea_level);
            mn.setTempKf(temp_kf);
            mn.setTempMax(temp_max);
            mn.setTempMin(temp_min);

            w.setDescription(description);
            w.setIcon(icon);
            w.setId(id);
            w.setMain(WeatherCondition);

            t.setTime(dfoutput2.format(date1));
            t.setMain(mn);
            t.setWeather(w);

            dt.setAdditionalProperty(dfoutput2.format(date1), t);
            fw.setAdditionalProperty(dfoutput1.format(date1), dt);

            e.setForecastWeather(fw);
            weatherList.add(e);
        }

        return weatherList;
    }

    private boolean isTomorrow(java.util.Date dateToCheck, java.util.Date currentDate) {
        java.util.Calendar cal1 = java.util.Calendar.getInstance();
        cal1.setTime(currentDate);

        java.util.Calendar cal2 = java.util.Calendar.getInstance();
        cal2.setTime(dateToCheck);

        return cal2.get(java.util.Calendar.DAY_OF_YEAR) == cal1.get(java.util.Calendar.DAY_OF_YEAR) + 1 &&
                cal2.get(java.util.Calendar.YEAR) == cal1.get(java.util.Calendar.YEAR);
    }
}
