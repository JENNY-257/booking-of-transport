package com.example.bookingtravel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookingtravel.model.Car;

import java.util.ArrayList;
import java.util.List;

public class TripsAdapter extends RecyclerView.Adapter<TripsAdapter.TripsViewHolder> {

    private List<Car> carList = new ArrayList<>();

    // Method to add a car to the list and notify adapter
    public void addCar(Car car) {
        carList.add(car);
        notifyDataSetChanged(); // Notify that data has changed
    }

    @NonNull
    @Override
    public TripsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_trip, parent, false); // Inflate your custom layout for list item
        return new TripsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TripsViewHolder holder, int position) {
        Car car = carList.get(position);
        holder.bind(car); // Bind data to the view
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    // ViewHolder class to hold individual trip data views
    static class TripsViewHolder extends RecyclerView.ViewHolder {
        private TextView carDetailsTextView;

        public TripsViewHolder(@NonNull View itemView) {
            super(itemView);
            carDetailsTextView = itemView.findViewById(R.id.carDetails);
        }

        public void bind(Car car) {
            // Display car data in the TextView
            String carDetails = "Make: " + car.getCompanyName() + "\n" +
                    "Model: " + car.getTypeOfCar() + "\n" +
                    "Year: " + car.getYear() + "\n" +
                    "Destination: " + car.getDestination() + "\n" +
                    "Available: " + (car.isAvailable() ? "Yes" : "No");
            carDetailsTextView.setText(carDetails);
        }
    }
}
