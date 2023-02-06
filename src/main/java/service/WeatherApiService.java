package service;

import model.daily.DailyRoot;
import model.parameter.ForecastRoot;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RequiredArgsConstructor
@Service
public class WeatherApiService {
    private static final String WeatherUrl = "http://api.weatherapi.com/v1/forecast.json?key=";
    private static final String historyWeatherUrl ="http://api.weatherapi.com/v1/history.json?key=";
    private final Logger logger = LoggerFactory.getLogger(WeatherApiService.class);
    private final RestTemplate restTemplate;
    private final String OpenWeatherUrl = "https://api.openweathermap.org/data/2.5/weather?q=";
    private final String apiKey = "ff9e87d38b6043fe8db145517230602";

    public DailyRoot getWeatherByCityName(String cityName) throws HttpMessageNotReadableException{
        logger.info("Requesting api.openweathermap.com weather for {}", cityName);
        DailyRoot dailyRoot = restTemplate.getForObject(OpenWeatherUrl+cityName+"&appid=" + apiKey,
                DailyRoot.class);
        return dailyRoot;
    }

    public ForecastRoot getWeatherForecast(String cityName, Integer day) {
        logger.info("Requesting api.weatherapi.com {} days weather for {}",
                day,
                cityName);
        ForecastRoot forecastRoot = restTemplate.getForObject(WeatherUrl+ apiKey +
                        "&q=" + cityName + "&days=" + day + "&aqi=no&alerts=no",
                ForecastRoot.class);
        return forecastRoot;
    }

    public ForecastRoot getWeatherHistory(String cityName, String dt){
        logger.info("Requesting api.weatherapi.com {} date weather for {}", dt, cityName);
        ForecastRoot forecastRoot = restTemplate.getForObject(historyWeatherUrl + apiKey +
                        "&q=" + cityName + "&dt=" + dt + "&aqi=no&alerts=no",
                ForecastRoot.class);

        return forecastRoot;
    }
}
