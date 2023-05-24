package com.example.project;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CityViewHolder extends RecyclerView.ViewHolder {
    private TextView sizeTextView;
    private TextView locationTextView;
    private TextView nameTextView;

    public CityViewHolder(View itemView) {
        super(itemView);
        sizeTextView = itemView.findViewById(R.id.sizeTextView);
        locationTextView = itemView.findViewById(R.id.locationTextView);
        nameTextView = itemView.findViewById(R.id.nameTextView);
    }

    public void bind(City city) {
        sizeTextView.setText(city.getSize());
        locationTextView.setText(city.getLocation());
        nameTextView.setText(city.getName());
    }
}
