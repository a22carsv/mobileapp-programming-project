package com.example.project;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

public class CityDetails extends AppCompatActivity {

    private TextView cityAuxDataTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_details);

        cityAuxDataTextView = findViewById(R.id.cityAux);
        // Retrieve city information from intent extras
        String cityAuxData = getIntent().getStringExtra("AuxData");


        // Set city information to TextView
        cityAuxDataTextView.setText(cityAuxData);
    }
}