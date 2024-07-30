## Overview
NubiWeather is a weather forecasting application built using Java 21 and Spring Boot. The application fetches real-time and forecast weather data for specified cities using an external weather API. It provides two main endpoints to access this data.

## Features
Real-time Weather Data: Fetches current weather information for a list of cities, including temperature, weather condition, wind speed, humidity, and more.

Forecast Weather Data: Retrieves hourly weather forecasts for the next three days for the specified cities.

Data Model: Utilizes a WeatherData model to structure weather information effectively.

Simple REST API: Exposes endpoints to retrieve weather data in a structured format.


## How to Run the Application
1. Ensure Java 21 is installed on your machine.
2. Build and run the application:
-Navigate to the project directory.
-Run the NubiweatherApplication class to start the application.
3. Access the endpoints:
After starting NubiweatherApplication, you can view the endpoints at:
http://localhost:8080/realtime-weather
http://localhost:8080/forecast-weather
Use Postman or your web browser to display weather data from these endpoints.
