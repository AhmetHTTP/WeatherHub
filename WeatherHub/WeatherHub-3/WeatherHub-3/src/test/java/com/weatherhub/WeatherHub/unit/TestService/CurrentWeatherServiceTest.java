import com.weatherhub.WeatherHub.models.Output;
import com.weatherhub.WeatherHub.services.CurrentWeatherService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CurrentWeatherServiceTest {

    @InjectMocks
    private CurrentWeatherService currentWeatherService;

    @Mock
    private RestTemplate restTemplate;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetCurrentWeather() {
        // Örnek verileri oluşturun
        String city = "Istanbul";
        String apiKey = "d88bf8f8925bad27c5d010a0490de59c";
        String baseUrl = "http://api.openweathermap.org/data/2.5/weather?mode=json&units=metric&appid=" + apiKey;
        String resultJson = "{\"weather\":[{\"description\":\"Cloud\",\"main\":\"Cloud\"}],\"main\":{\"temp\":25.0,\"pressure\":1013,\"humidity\":50,\"temp_min\":24,\"temp_max\":26}}";

        // RestTemplate'ın sahte sonuç vermesini sağlayın
        when(restTemplate.getForObject(baseUrl + "&q=" + city, String.class)).thenReturn(resultJson);

        // CurrentWeatherService metodunu çağırın
        List<Output> weatherList = currentWeatherService.getCurrentWeather(city);

        // Sonuçları kontrol edin (örnek bir kontrol)
        assertEquals(1, weatherList.size());
        assertEquals("Clear", weatherList.get(0).getTodaysWeather().getToday().getMain());
        assertEquals(25.0, weatherList.get(0).getTodaysWeather().getToday().getTemp(), 0.01);

        // RestTemplate'ın çağrıldığını kontrol edin
        verify(restTemplate, times(1)).getForObject(baseUrl + "&q=" + city, String.class);
    }
}
