package com.example.project;

import android.content.Intent;
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

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        cityList = new ArrayList<>();
        cityAdapter = new CityAdapter(this, cityList);
        cityAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(cityAdapter);

        JsonTask jsonTask = new JsonTask();
        jsonTask.setListener(this);
        jsonTask.execute("https://mobprog.webug.se/json-api?login=a22carsv");

    }

    @Override
    public void onItemClick(int position) {
        City city = cityList.get(position);
        Intent intent = new Intent(this, CityDetailsActivity.class);
        intent.putExtra("cityId", city.getID());
        intent.putExtra("cityLogin", city.getLogin());
        intent.putExtra("citySize", city.getSize());
        intent.putExtra("cityLocation", city.getLocation());
        intent.putExtra("cityName", city.getName());
        startActivity(intent);
    }

    @Override
    public void onJsonTaskComplete(JSONArray jsonArray) {
        if (jsonArray != null) {
            try {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String id = jsonObject.getString("id");
                    String login = jsonObject.getString("login");
                    String size = jsonObject.getString("size");
                    String location = jsonObject.getString("location");
                    String name = jsonObject.getString("name");

                    cityList.add(new City(id, login, size, location, name));
                }
                cityAdapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
