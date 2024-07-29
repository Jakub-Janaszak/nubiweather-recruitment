package com.nubisoft.nubiweather.services;

import com.nubisoft.nubiweather.models.WeatherData;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherService {
    private final OkHttpClient client;
    private final String apiKey = "5fd72e4299394127918132940241607";
    private final List<WeatherData> realtimeWeatherDataList;

    public List<WeatherData> getRealtimeWeatherDataList() {
        return realtimeWeatherDataList;
    }

    public WeatherService() {
        this.client = new OkHttpClient();
        this.realtimeWeatherDataList = new ArrayList<>();
    }

    public WeatherData getCurrentWeather(String cityName) {
        JSONObject weatherJson = fetchWeatherData("https://api.weatherapi.com/v1/current.json?key=" + apiKey + "&q=" + cityName);
        if (weatherJson != null) {
            WeatherData weatherData = parseWeatherData(weatherJson);
            realtimeWeatherDataList.add(weatherData);
            return weatherData;
        }
        return null;
    }

    public List<WeatherData> getForecastWeather(String cityName) {
        JSONObject weatherJson =  fetchWeatherData("https://api.weatherapi.com/v1/forecast.json?key=" + apiKey + "&q=" + cityName + "&days=3");
        if (weatherJson != null) {
            List<WeatherData> weatherData = parseWeatherDataList(weatherJson);
            return weatherData;
        }
        return null;
    }

    private JSONObject fetchWeatherData(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return new JSONObject(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private WeatherData parseWeatherData(JSONObject json) {
        String localtimeStr = json.getJSONObject("current").getString("last_updated");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime localtime = LocalDateTime.parse(localtimeStr, formatter);

        WeatherData weatherData = new WeatherData(json.getJSONObject("location").getString("name"), json.getJSONObject("current").getDouble("temp_c"), json.getJSONObject("current").getJSONObject("condition").getString("text"), json.getJSONObject("current").getDouble("wind_kph"), json.getJSONObject("current").getInt("humidity"), json.getJSONObject("current").getDouble("feelslike_c"), localtime);
        return weatherData;
    }

    private List<WeatherData> parseWeatherDataList(JSONObject json) {
        List<WeatherData> weatherDataList = new ArrayList<>();
        String locationName = json.getJSONObject("location").getString("name");
        JSONArray forecastHour = json.getJSONObject("forecast").getJSONArray("forecastday").getJSONObject(0).getJSONArray("hour");
        for (int i = 0; i < forecastHour.length(); i++) {

            String timeStr = forecastHour.getJSONObject(i).getString("time");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime localtime = LocalDateTime.parse(timeStr, formatter);

            WeatherData weatherData = new WeatherData(locationName, forecastHour.getJSONObject(i).getDouble("temp_c"), forecastHour.getJSONObject(i).getJSONObject("condition").getString("text"), forecastHour.getJSONObject(i).getDouble("wind_kph"), forecastHour.getJSONObject(i).getInt("humidity"), forecastHour.getJSONObject(i).getDouble("feelslike_c"), localtime);

            weatherDataList.add(weatherData);
        }
        return weatherDataList;
    }


}
