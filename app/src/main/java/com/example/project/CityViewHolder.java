package com.example.project;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CityViewHolder extends RecyclerView.ViewHolder {
    private TextView idTextView;
    private TextView loginTextView;
    private TextView sizeTextView;
    private TextView locationTextView;
    private TextView nameTextView;

    public CityViewHolder(@NonNull View itemView) {
        super(itemView);
        sizeTextView = itemView.findViewById(R.id.citySizeTextView);
        locationTextView = itemView.findViewById(R.id.cityLocationTextView);
        nameTextView = itemView.findViewById(R.id.cityNameTextView);
    }

    public void bind(City city) {
        sizeTextView.setText(city.getSize());
        locationTextView.setText(city.getLocation());
        nameTextView.setText(city.getName());
    }
}
