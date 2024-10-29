package com.example.bookingtravel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.bookingtravel.model.Car;
import java.util.List;

public class CarAdapterApp extends RecyclerView.Adapter<CarAdapterApp.CarViewHolder> {

    private List<Car> carList;

    public CarAdapterApp(List<Car> carList) {
        this.carList = carList;
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.car_deatail, parent, false);
        return new CarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        Car car = carList.get(position);
        holder.companyName.setText(car.getCompanyName());
        holder.typeOfCar.setText(car.getTypeOfCar());
        holder.year.setText(String.valueOf(car.getYear()));
        holder.destination.setText(car.getDestination());
        holder.available.setText(car.isAvailable() ? "Available" : "Not Available");
        holder.numberOfSeats.setText(String.valueOf(car.getNumberOfSeats()));
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    public void updateData(List<Car> newCarList) {
        this.carList = newCarList;
        notifyDataSetChanged();
    }

    static class CarViewHolder extends RecyclerView.ViewHolder {
        TextView companyName, typeOfCar, year, destination, available, numberOfSeats;

        public CarViewHolder(@NonNull View itemView) {
            super(itemView);
            companyName = itemView.findViewById(R.id.companyName);
            typeOfCar = itemView.findViewById(R.id.typeOfCar);
            year = itemView.findViewById(R.id.year);
            destination = itemView.findViewById(R.id.destination);
            available = itemView.findViewById(R.id.available);
            numberOfSeats = itemView.findViewById(R.id.numberOfSeats);
        }
    }
}
