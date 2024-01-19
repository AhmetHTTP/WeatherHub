package com.weatherhub.WeatherHub.integration.IntegrationTestController;

import com.weatherhub.WeatherHub.models.Output;
import com.weatherhub.WeatherHub.services.SearchService;
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
public class SearchControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SearchService searchService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetWeather() throws Exception {
        // Sahte veri oluştur
        List<Output> weatherData = Collections.singletonList(new Output());

        // SearchService'in sahte sonuçlarını ayarla
        when(searchService.searchWeather("Istanbul")).thenReturn(weatherData);

        mockMvc.perform(MockMvcRequestBuilders.get("/search/Istanbul")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].yourProperty").value("expectedValue"));
        // Beklenen sonuçlara uygun olarak JSON yanıtını kontrol edin
    }

    @Test
    public void testGetWeatherParseException() throws Exception {
        // ParseException fırlatan bir senaryoyu test etmek için sahte hizmet
        when(searchService.searchWeather("InvalidCity")).thenThrow(new ParseException("Invalid date", 0));

        mockMvc.perform(MockMvcRequestBuilders.get("/search/InvalidCity")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                .andExpect(MockMvcResultMatchers.content().string("Error parsing date: Invalid date"));
    }

    @Test
    public void testGetWeatherException() throws Exception {
        // Genel bir exception fırlatan senaryoyu test etmek için sahte hizmet
        when(searchService.searchWeather("ErrorCity")).thenThrow(new RuntimeException("Internal error"));

        mockMvc.perform(MockMvcRequestBuilders.get("/search/ErrorCity")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                .andExpect(MockMvcResultMatchers.content().string("An error occurred: Internal error"));
    }
}
