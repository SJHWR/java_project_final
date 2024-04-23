package com.example.finlandcityinformation;

// Classes for getting city information
public class getCityInformation {

    private static City city;

    // Initialize the city object
    public static void InitializeCity(){
        String cityName = "default";
        String cityID = "default";
        String cityTemperature = "default";
        String cityWeather = "default";
        String cityPopulation = "default";
        String cityPopulationChange = "default";
        String cityWSS = "default";
        String cityEmploymentRate = "default";
        String cityLatitude = "default";
        String cityLongitude = "default";
        city = new City(cityName, cityID, cityTemperature, cityWeather, cityPopulation, cityPopulationChange, cityWSS, cityEmploymentRate, cityLatitude, cityLongitude);
    }

    // Get the city object
    public static City getCity(){
        return city;
    }
}
