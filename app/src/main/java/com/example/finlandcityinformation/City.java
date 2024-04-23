package com.example.finlandcityinformation;

//This class stores information about the city.
// Constructor methods are provided for initializing the city object.
// A series of get and set methods are provided to get and set the values of the properties of the city object.
// City class to store information about the city
public class City {
    // Declare city-related attributes
    private String cityName;
    private String cityID;
    private String cityTemperature;
    private String cityWeather;
    private String cityPopulation;
    private String cityPopulationChange;
    private String cityWSS; //Workplace self-sufficiency
    private String cityEmploymentRate;
    private String cityLatitude;
    private String cityLongitude;

    // Constructor to set property values when initializing a city object
    public City(String cityName,String cityID, String cityTemperature, String cityWeather, String cityPopulation, String cityPopulationChange, String cityWSS, String cityEmploymentRate, String cityLatitude, String cityLongitude) {
        this.cityName = cityName;
        this.cityID = cityID;
        this.cityTemperature = cityTemperature;
        this.cityWeather = cityWeather;
        this.cityPopulation = cityPopulation;
        this.cityPopulationChange = cityPopulationChange;
        this.cityWSS = cityWSS;
        this.cityEmploymentRate = cityEmploymentRate;
        this.cityLatitude = cityLatitude;
        this.cityLongitude = cityLongitude;
    }
    // The following is a series of getter and setter methods for getting and setting the value of an object's properties

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityID() {
        return cityID;
    }

    public void setCityID(String cityID) {
        this.cityID = cityID;
    }

    public String getCityTemperature() {
        return cityTemperature;
    }

    public void setCityTemperature(String cityTemperature) {
        this.cityTemperature = cityTemperature;
    }

    public String getCityWeather() {
        return cityWeather;
    }

    public void setCityWeather(String cityWeather) {
        this.cityWeather = cityWeather;
    }

    public String getCityPopulation() {
        return cityPopulation;
    }

    public void setCityPopulation(String cityPopulation) {
        this.cityPopulation = cityPopulation;
    }

    public String getCityPopulationChange() {
        return cityPopulationChange;
    }

    public void setCityPopulationChange(String cityPopulationChange) {
        this.cityPopulationChange = cityPopulationChange;
    }

    public String getCityWSS() {
        return cityWSS;
    }

    public void setCityWSS(String cityWSS) {
        this.cityWSS = cityWSS;
    }

    public String getCityEmploymentRate() {
        return cityEmploymentRate;
    }

    public void setCityEmploymentRate(String cityEmploymentRate) {
        this.cityEmploymentRate = cityEmploymentRate;
    }

    public String getCityLatitude() {
        return cityLatitude;
    }

    public void setCityLatitude(String cityLatitude) {
        this.cityLatitude = cityLatitude;
    }

    public String getCityLongitude() {
        return cityLongitude;
    }

    public void setCityLongitude(String cityLongitude) {
        this.cityLongitude = cityLongitude;
    }
}
