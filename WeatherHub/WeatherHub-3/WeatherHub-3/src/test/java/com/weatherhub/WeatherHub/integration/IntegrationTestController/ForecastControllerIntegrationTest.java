package com.weatherhub.WeatherHub.integration.IntegrationTestController;

import com.weatherhub.WeatherHub.models.Output;
import com.weatherhub.WeatherHub.services.ForecastService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.text.ParseException;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class ForecastControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ForecastService forecastService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetFiveDayForecast() throws Exception {
        // Sahte veri oluştur
        List<Output> weatherData = Collections.singletonList(new Output());

        // ForecastService'in sahte sonuçlarını ayarla
        when(forecastService.getFiveDayForecast("Bursa")).thenReturn(weatherData);

        mockMvc.perform(MockMvcRequestBuilders.get("/forecast/Bursa")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].yourProperty").value("expectedValue"));
        // Beklenen sonuçlara uygun olarak JSON yanıtını kontrol edin
    }

    @Test
    public void testGetFiveDayForecastParseException() throws Exception {
        // ParseException fırlatan bir senaryoyu test etmek için sahte hizmet
        when(forecastService.getFiveDayForecast("InvalidCity")).thenThrow(new ParseException("Invalid date", 0));

        mockMvc.perform(MockMvcRequestBuilders.get("/forecast/InvalidCity")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                .andExpect(MockMvcResultMatchers.content().string("Error parsing date: Invalid date"));
    }

    @Test
    public void testGetFiveDayForecastException() throws Exception {
        // Genel bir exception fırlatan senaryoyu test etmek için sahte hizmet
        when(forecastService.getFiveDayForecast("ErrorCity")).thenThrow(new RuntimeException("Internal error"));

        mockMvc.perform(MockMvcRequestBuilders.get("/forecast/ErrorCity")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                .andExpect(MockMvcResultMatchers.content().string("An error occurred: Internal error"));
    }
}
