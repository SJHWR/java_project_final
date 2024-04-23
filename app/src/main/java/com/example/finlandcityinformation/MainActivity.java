package com.example.finlandcityinformation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.qweather.sdk.view.HeConfig;

// Main activity for searching cities
public class MainActivity extends AppCompatActivity {

    private EditText editCityInput;
    private Button buttonSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initialize interface elements
        editCityInput = findViewById(R.id.editCityInput);
        buttonSearch = findViewById(R.id.buttonSearch);

        HeConfig.init("HE2404010521321037", "434d1a67dcca48b58f7efb9fdab29616");
        HeConfig.switchToDevService();
        // Set up a listener for the search button click event
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            // Call the search city method
            public void onClick(View view) {
                searchCity();
            }
        });
    }

    // City search methodology
    private void searchCity() {
        // Get the city name entered by the user
        String cityName = editCityInput.getText().toString();
        // Initialize city information
        getCityInformation.InitializeCity();
        getCityInformation.getCity().setCityName(cityName);

        Intent intent = new Intent(this, CityInformationActivity.class);
        startActivity(intent);
    }
}