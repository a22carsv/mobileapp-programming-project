package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener {

    private RecyclerView recyclerView;
    private CityAdapter cityAdapter;
    private List<City> cityList;

    private Button aboutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aboutButton = findViewById(R.id.aboutButton);
        aboutButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AboutActivity.class));
            }
        });
        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Create an empty list for city data
        cityList = new ArrayList<>();

        // Initialize and set the adapter for RecyclerView
        cityAdapter = new CityAdapter(this, cityList);
        recyclerView.setAdapter(cityAdapter);

        // Fetch JSON data
        getJsonFromURL();
    }

    private JsonTask.JsonTaskListener jsonTaskListener = new JsonTask.JsonTaskListener()
    {
        @Override
        public void onPostExecute(String json) {
            if (json != null) {
                Log.d("JSON Response", json); // Print the JSON response for debugging
                try {
                    JSONArray jsonArray = new JSONArray(json);
                    parseJsonData(jsonArray);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.d("JSON Response", "Empty or null JSON"); // Debug message for empty or null JSON
            }
        }



    };

    private void getJsonFromURL() {
        new JsonTask(jsonTaskListener).execute("https://mobprog.webug.se/json-api?login=a22carsv");

    }


    @Override
    public void onPostExecute(String json) {

    }

    private void parseJsonData(JSONArray jsonArray) {


        try {

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject cityObject = jsonArray.getJSONObject(i);

                String id = cityObject.getString("ID");
                String name = cityObject.getString("name");
                String location = cityObject.getString("location");
                String size = cityObject.getString("size");

                // Create a new City object and add it to the cityList
                City city = new City(id, size, location, name);
                cityList.add(city);

            }
            cityAdapter.set(cityList);
            for (int i = 0; i < cityList.size(); i++) {

            }


            // Notify the adapter that the data set has changed
            cityAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();

        }
    }
}
