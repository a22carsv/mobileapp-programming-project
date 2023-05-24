package com.example.project;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class JsonTask extends AsyncTask<String, Void, JSONArray> {

    private List<City> cityList;
    private CityAdapter cityAdapter;

    public JsonTask(List<City> cityList, CityAdapter cityAdapter) {
        this.cityList = cityList;
        this.cityAdapter = cityAdapter;
    }

    @Override
    protected JSONArray doInBackground(String... urls) {
        if (urls.length == 0)
            return null;

        String urlString = urls[0];
        HttpURLConnection urlConnection = null;

        try {
            URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();

            InputStream inputStream = urlConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            StringBuilder stringBuilder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }

            return new JSONArray(stringBuilder.toString());

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null)
                urlConnection.disconnect();
        }

        return null;
    }

    @Override
    protected void onPostExecute(JSONArray jsonArray) {
        if (jsonArray != null) {
            Log.d("JsonTask", "Received JSON response: " + jsonArray.toString());
            try {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    if (jsonObject.has("ID")) {
                        String id = jsonObject.getString("ID");
                        String name = jsonObject.getString("name");
                        String location = jsonObject.getString("location");
                        int size = jsonObject.getInt("size");

                        cityList.add(new City(id, "", "", name, location));
                    } else {
                        Log.d("JsonTask", "Missing 'ID' field in JSON object: " + jsonObject.toString());
                    }
                }
                if (cityAdapter != null) {
                    cityAdapter.notifyDataSetChanged();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}

