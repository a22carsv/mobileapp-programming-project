package com.example.project;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

public class CityDetailsActivity extends AppCompatActivity {

    private TextView cityIdTextView;
    private TextView cityLoginTextView;
    private TextView citySizeTextView;
    private TextView cityLocationTextView;
    private TextView cityNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_details);

        // Retrieve city information from intent extras
        String cityId = getIntent().getStringExtra("cityId");
        String cityLogin = getIntent().getStringExtra("cityLogin");
        String citySize = getIntent().getStringExtra("citySize");
        String cityLocation = getIntent().getStringExtra("cityLocation");
        String cityName = getIntent().getStringExtra("cityName");

        // Initialize TextViews
        cityIdTextView = findViewById(R.id.cityIdTextView);
        cityLoginTextView = findViewById(R.id.cityLoginTextView);
        citySizeTextView = findViewById(R.id.citySizeTextView);
        cityLocationTextView = findViewById(R.id.cityLocationTextView);
        cityNameTextView = findViewById(R.id.cityNameTextView);

        // Set city information to TextViews
        cityIdTextView.setText(cityId);
        cityLoginTextView.setText(cityLogin);
        citySizeTextView.setText(citySize);
        cityLocationTextView.setText(cityLocation);
        cityNameTextView.setText(cityName);
    }
}
