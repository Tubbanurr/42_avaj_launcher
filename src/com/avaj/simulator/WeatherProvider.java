package com.avaj.simulator;

public class WeatherProvider 
{
    private static WeatherProvider weatherProvider;
    private String[] weather = {"SUN", "RAIN", "FOG", "SNOW"};

    private WeatherProvider() {}

    public static WeatherProvider getWeatherProvider() 
    {
        if (weatherProvider == null)
            weatherProvider = new WeatherProvider();
        return weatherProvider;
    }

    public String getCurrentWeather(Coordinates p_coordinates) 
    {
        int index = Math.abs(
            (p_coordinates.getLongitude() + p_coordinates.getLatitude() + p_coordinates.getHeight())
            % weather.length
        );
        return weather[index];
    }
}
