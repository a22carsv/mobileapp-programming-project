package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {

    private Context context;
    private List<City> cityList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public CityAdapter(Context context, List<City> cityList) {
        this.context = context;
        this.cityList = cityList;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView idTextView;
        public TextView loginTextView;
        public TextView nameTextView;
        public TextView locationTextView;
        public TextView sizeTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            idTextView = itemView.findViewById(R.id.cityIdTextView);
            loginTextView = itemView.findViewById(R.id.cityLoginTextView);
            nameTextView = itemView.findViewById(R.id.cityNameTextView);
            locationTextView = itemView.findViewById(R.id.cityLocationTextView);
            sizeTextView = itemView.findViewById(R.id.citySizeTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.city_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        City city = cityList.get(position);

        holder.idTextView.setText(city.getId());
        holder.loginTextView.setText(city.getLogin());
        holder.nameTextView.setText(city.getName());
        holder.locationTextView.setText(city.getLocation());
        holder.sizeTextView.setText(city.getSize());
    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }
}
