package com.example.finlandcityinformation;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.qweather.sdk.bean.geo.GeoBean;
import com.qweather.sdk.bean.weather.WeatherNowBean;
//The role of this class is to display detailed information about a specific city for the user,
// including basic information, weather information and statistics,
// and to provide the ability to jump to a map page.
// City information activity class for displaying various information about the city
public class CityInformationActivity extends AppCompatActivity {
    // Declare city objects and interface elements
    private City city = getCityInformation.getCity();
    private TextView cityNameTextView;
    private TextView cityTemperatureTextView;
    private TextView cityWeatherTextView;
    private TextView cityPopulationTextView;
    private TextView cityChangeInPopulationTextView;
    private TextView cityWorkplaceSelfSufficiencyTextView;
    private TextView employmentRateTextView;
    private TextView cityLatitudeTextView;
    private TextView cityLongitudeTextView;
    private Button mapButton;

    // Interface initialization and data loading in the onCreate method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        // Initialize interface elements

        cityNameTextView = findViewById(R.id.cityNameTextView);
        cityWeatherTextView = findViewById(R.id.cityWeatherTextView);
        cityPopulationTextView = findViewById(R.id.cityPopulationTextView);
        cityChangeInPopulationTextView = findViewById(R.id.cityChangeInPopulationTextView);
        cityWorkplaceSelfSufficiencyTextView = findViewById(R.id.cityWorkplaceSelfSufficiencyTextView);
        employmentRateTextView = findViewById(R.id.employmentRateTextView);
        cityTemperatureTextView = findViewById(R.id.cityTemperatureTextView);
        cityLatitudeTextView = findViewById(R.id.cityLatitudeTextView);
        cityLongitudeTextView = findViewById(R.id.cityLongitudeTextView);
        mapButton = findViewById(R.id.mapButton);

        getBasicData basicDataInstance = new getBasicData();


        basicDataInstance.getBasic(CityInformationActivity.this, getCityInformation.getCity().getCityName(), new getBasicData.OnResultListener() {
            @Override
            public void onResultBasic(GeoBean geoBean) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String cityName = city.getCityName();
                        cityNameTextView.setText(cityName);
                        String cityLatitude = city.getCityLatitude();
                        String cityLongitude = city.getCityLongitude();
                        cityLatitudeTextView.setText("Latitude: " + cityLatitude);
                        cityLongitudeTextView.setText("Longitude: "+ cityLongitude);
                        new FetchStatisticsTask(CityInformationActivity.this).execute(cityName);
                        getWeather();
                    }
                });
            }
        });
        // Listen for map button clicks
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            // Open the map page
            public void onClick(View view) {
                openMap();
            }
        });
    }

    //For obtaining statistical data
    public class FetchStatisticsTask extends AsyncTask<String, Void, Void> {
        private Context context;
        //Used to initialize the context
        public FetchStatisticsTask(Context context) {
            this.context = context.getApplicationContext();
        }

        @Override
        protected Void doInBackground(String... params) {
            com.example.finlandcityinformation.getStatisticsInformation.getStatistics(context, params[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    cityPopulationTextView.setText("Population: " + city.getCityPopulation());
                    cityChangeInPopulationTextView.setText("Change In Population: " + city.getCityPopulationChange());
                    cityWorkplaceSelfSufficiencyTextView.setText("Workplace-Self-Sufficiency: " + city.getCityWSS());
                    employmentRateTextView.setText("Employment Rate: " + city.getCityEmploymentRate());
                }
            });
        }
    }

    // Get weather information
    private void getWeather() {
        getWeatherData weatherInstance = new getWeatherData();
        weatherInstance.getWeather(CityInformationActivity.this, getCityInformation.getCity().getCityID(), new getWeatherData.OnResultWeatherNowListener() {
            @Override
            public void onResultWeather(WeatherNowBean weatherBean) {
                runOnUiThread(new Runnable() {
                    @Override
                    // Update the interface to show weather information
                    public void run() {
                        cityWeatherTextView.setText("Weather Conditions: " + weatherBean.getNow().getText());
                        cityTemperatureTextView.setText("Temperature: " + weatherBean.getNow().getTemp() + "℃");
                    }
                });
            }
        });
    }

    // Open the map page
    private void openMap() {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }
}
