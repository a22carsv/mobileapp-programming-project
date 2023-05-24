package com.example.project;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CityAdapter.OnItemClickListener, JsonTask.JsonTaskListener {

    private RecyclerView recyclerView;
    private CityAdapter cityAdapter;
    private List<City> cityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Create an empty list for city data
        cityList = new ArrayList<>();

        // Initialize and set the adapter for RecyclerView
        cityAdapter = new CityAdapter(this, cityList);
        cityAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(cityAdapter);

        // Fetch JSON data
        getJsonFromURL();
    }

    private void getJsonFromURL() {
        new JsonTask(this).execute("https://mobprog.webug.se/json-api?login=a22carsv");
    }

    @Override
    public void onItemClick(int position) {
        // Handle item click event
        City city = cityList.get(position);

        // Open CityDetailsActivity and pass city information
        Intent intent = new Intent(this, CityDetailsActivity.class);
        intent.putExtra("cityId", city.getId());
        intent.putExtra("cityLogin", city.getLogin());
        intent.putExtra("citySize", city.getSize());
        intent.putExtra("cityLocation", city.getLocation());
        intent.putExtra("cityName", city.getName());
        startActivity(intent);
    }

    @Override
    public void onPostExecute(String json) {
        try {
            JSONArray jsonArray = new JSONArray(json);
            parseJsonData(jsonArray);
            cityAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void parseJsonData(JSONArray jsonArray) {
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject cityObject = jsonArray.getJSONObject(i);

                String id = cityObject.getString("ID");
                String login = cityObject.getString("type");
                String name = cityObject.getString("name");
                String location = cityObject.getString("location");
                String size = cityObject.getString("size");

                // Create a new City object and add it to the cityList
                City city = new City(id, login, size, location, name);
                cityList.add(city);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
