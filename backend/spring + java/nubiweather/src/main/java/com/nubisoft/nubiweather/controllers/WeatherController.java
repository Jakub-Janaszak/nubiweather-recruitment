package com.nubisoft.nubiweather.controllers;

import com.nubisoft.nubiweather.models.WeatherData;
import com.nubisoft.nubiweather.services.WeatherService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@RestController
public class WeatherController {

    @Value("#{'${weather.cities}'.split(',')}")
    private List<String> cities;

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/realtime-weather")
    public ResponseEntity<Map<String, Object>> getRealTimeWeather() {
        Map<String, Object> response = new HashMap<>();
        for (String city : cities) {
            WeatherData weather = weatherService.getCurrentWeather(city);
            if (weather != null) {
                response.put(city, weather);
            } else {
                response.put(city, "No data available for " + city);
            }
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/forecast-weather")
    public ResponseEntity<Map<String, Object>> getForecastWeather() {
        Map<String, Object> response = new HashMap<>();
        for (String city : cities) {
            List<WeatherData> forecast = weatherService.getForecastWeather(city);
            if (forecast != null && !forecast.isEmpty()) {
                response.put(city, forecast); // Dodaj całą listę prognoz do mapy
            } else {
                response.put(city, "No data available for " + city);
            }
        }
        return ResponseEntity.ok(response);
    }
}



