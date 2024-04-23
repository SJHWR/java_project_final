package com.example.finlandcityinformation;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.finlandcityinformation.databinding.ActivityMapsBinding;
//This class is used to display geographic information about a city on a map
// Map activity for displaying city map information
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private City city = getCityInformation.getCity();
    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Get the city's latitude and longitude information
        String latitude = city.getCityLatitude();
        String longitude = city.getCityLongitude();
        LatLng inputCity = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));
        // Add the location of the query city to the map.
        mMap.addMarker(new MarkerOptions().position(inputCity).title("Marker in " + getIntent().getStringExtra("cityName")));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(inputCity));
    }
}