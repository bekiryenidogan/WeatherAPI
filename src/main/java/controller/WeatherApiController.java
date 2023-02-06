package controller;

import lombok.RequiredArgsConstructor;
import model.daily.DailyRoot;
import model.parameter.ForecastRoot;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import service.WeatherApiService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/weather")
@Validated
public class WeatherApiController {
    private final WeatherApiService weatherApiService;

    @GetMapping("/current/{cityName}")
    public ResponseEntity<DailyRoot> getWeatherByCityName(@PathVariable String cityName){
        return ResponseEntity.ok(weatherApiService.getWeatherByCityName(cityName));
    }

    @GetMapping("/{cityName}/{day}")
    public ResponseEntity<ForecastRoot> getWeatherByCityNameAndParameter(@PathVariable String cityName, @PathVariable Integer day) {
        if (day == null) {
            throw new NullPointerException();
        }
        if (day < 0 || day > 10) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(weatherApiService.getWeatherForecast(cityName, day));
    }
    @GetMapping("/history/{cityName}/{date}")
    public ResponseEntity<ForecastRoot> getWeatherHistory(@PathVariable String cityName, @PathVariable String dt) {
        return ResponseEntity.ok(weatherApiService.getWeatherHistory(cityName, dt));

    }
}
