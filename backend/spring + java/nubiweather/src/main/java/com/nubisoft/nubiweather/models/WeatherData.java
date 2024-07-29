package com.nubisoft.nubiweather.models;

import java.time.LocalDateTime;

public class WeatherData {
    private String locationName;
    private double tempC;
    private String conditionText;
    private double windKph;
    private int humidity;
    private double feelslikeC;
    private LocalDateTime time;

    public WeatherData(String locationName, double tempC, String conditionText, double windKph, int humidity, double feelslikeC, LocalDateTime time) {
        this.locationName = locationName;
        this.tempC = tempC;
        this.conditionText = conditionText;
        this.windKph = windKph;
        this.humidity = humidity;
        this.feelslikeC = feelslikeC;
        this.time = time;
    }

    // Getters and Setters
    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public double getTempC() {
        return tempC;
    }

    public void setTempC(double tempC) {
        this.tempC = tempC;
    }

    public String getConditionText() {
        return conditionText;
    }

    public void setConditionText(String conditionText) {
        this.conditionText = conditionText;
    }

    public double getWindKph() {
        return windKph;
    }

    public void setWindKph(double windKph) {
        this.windKph = windKph;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getFeelslikeC() {
        return feelslikeC;
    }

    public void setFeelslikeC(double feelslikeC) {
        this.feelslikeC = feelslikeC;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}