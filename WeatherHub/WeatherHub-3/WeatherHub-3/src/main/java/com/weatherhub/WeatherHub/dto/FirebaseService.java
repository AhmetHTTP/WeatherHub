package com.weatherhub.WeatherHub.dto;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class FirebaseService {

    public void sendWeatherDataToFirebase(String city, double temperature, int humidity) {
        try {
            // Firebase veritabanı referansını al
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("weatherData");

            // Veriyi hazırla
            Map<String, Object> weatherData = new HashMap<>();
            weatherData.put("city", city);
            weatherData.put("temperature", temperature);
            weatherData.put("humidity", humidity);

            // Veriyi Firebase veritabanına ekle
            databaseReference.push().setValueAsync(weatherData); // Asenkron bir şekilde veriyi gönderir

            System.out.println("Weather data sent to Firebase successfully.");
        } catch (Exception e) {
            System.err.println("Error sending weather data to Firebase: " + e.getMessage());
        }
    }
}
