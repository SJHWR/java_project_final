package com.example.finlandcityinformation;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.qweather.sdk.bean.base.Code;
import com.qweather.sdk.bean.base.Lang;
import com.qweather.sdk.bean.base.Unit;
import com.qweather.sdk.bean.weather.WeatherNowBean;
import com.qweather.sdk.view.QWeather;
//This class is used to get the weather data of the specified city, including the current temperature and weather conditions
// Classes for getting weather data
public class getWeatherData {
    public void getWeather(Context context, String cityID, OnResultWeatherNowListener OnResultWeatherNowListener){
        QWeather.getWeatherNow(context, cityID, Lang.EN, Unit.METRIC, new QWeather.OnResultWeatherNowListener() {
            public static final String TAG = "HeFeng_weather";

            @Override
            public void onError(Throwable e) {//When an error occurs
                Log.i(TAG, "onError: ", e);
                System.out.println("Weather Now Error:" + new Gson());
            }

            @Override
            public void onSuccess(WeatherNowBean weatherBean) {
                if (Code.OK == weatherBean.getCode()) {

                    WeatherNowBean.NowBaseBean now = weatherBean.getNow();
                    String temperature = now.getTemp();
                    String weather = now.getText();
                    // Setting the city temperature and weather conditions
                    getCityInformation.getCity().setCityTemperature(temperature);
                    getCityInformation.getCity().setCityWeather(weather);

                    if (OnResultWeatherNowListener != null){
                        OnResultWeatherNowListener.onResultWeather(weatherBean);
                    }
                } else {
                    Code code = weatherBean.getCode();
                    System.out.println("Failed code: " + code);
                }
            }
        });
    }

    // The interface is used to pass in the current weather information.
    public interface OnResultWeatherNowListener {
        void onResultWeather(WeatherNowBean weatherBean);
    }
}
