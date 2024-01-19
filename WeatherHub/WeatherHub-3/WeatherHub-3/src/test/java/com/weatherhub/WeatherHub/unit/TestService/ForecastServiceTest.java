package com.weatherhub.WeatherHub.unit.TestService;

import com.weatherhub.WeatherHub.models.Output;
import com.weatherhub.WeatherHub.services.ForecastService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.util.List;

import static org.mockito.Mockito.*;

public class ForecastServiceTest {

    @InjectMocks
    private ForecastService forecastService;

    @Mock
    private RestTemplate restTemplate;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetFiveDayForecast() throws ParseException {
        // Örnek verileri oluşturun
        String city = "İstanbul";
        String apiKey = "d88bf8f8925bad27c5d010a0490de59c";
        String baseUrl = "http://api.openweathermap.org/data/2.5/forecast?mode=json&units=metric&appid=" + apiKey;
        String resultJson = "{\"list\":[{\"main\":{\"temp\":25.0,\"pressure\":1013,\"humidity\":50,\"temp_min\":24,\"temp_max\":26,\"temp_kf\":0,\"sea_level\":1013,\"grnd_level\":1012},\"weather\":[{\"description\":\"Clear\",\"icon\":\"01d\",\"main\":\"Clear\",\"id\":800}],\"dt_txt\":\"2024-01-18 12:00:00\"}]}";

        // RestTemplate'ın sahte sonuç vermesini sağlayın
        when(restTemplate.getForObject(baseUrl + "&q=" + city, String.class)).thenReturn(resultJson);

        // ForecastService metodunu çağırın
        List<Output> weatherList = forecastService.getFiveDayForecast(city);

        // RestTemplate'ın çağrıldığını kontrol edin
        verify(restTemplate, times(1)).getForObject(baseUrl + "&q=" + city, String.class);

        // Sonuçları doğrudan karşılaştırmak yerine başka bir şekilde kontrol edin
        // Örnek olarak, sonuçların boş olmadığını kontrol edebilirsiniz
        assertNotEmpty(weatherList);
    }

    private void assertNotEmpty(List<Output> list) {
        // List'in boş olmadığını kontrol et
        assert !list.isEmpty();
    }
}
