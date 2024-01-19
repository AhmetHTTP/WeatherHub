package com.weatherhub.WeatherHub.integration.IntegrationTestController;

import com.weatherhub.WeatherHub.models.Output;
import com.weatherhub.WeatherHub.services.CurrentWeatherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class CurrentWeatherControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CurrentWeatherService currentWeatherService;

    @BeforeEach
    public void setup() {
        // CurrentWeatherService'in sahte sonuçlarını ayarlayın
        Output output = new Output();
        // Burada output nesnesini uygun şekilde doldurmalısınız
        when(currentWeatherService.getCurrentWeather("Bursa")).thenReturn(Collections.singletonList(output));
    }

    @Test
    public void testGetCurrentWeather() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/weather/{city}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].yourProperty").value("expectedValue"));
        // Beklenen sonuçlara uygun olarak JSON yanıtını kontrol edin
    }
}
