package com.example.bookingtravel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bookingtravel.model.Car;

public class TripsListFragment extends Fragment {

    private Car car;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trips_list, container, false);

        // Get the passed Car data
        if (getArguments() != null) {
            car = (Car) getArguments().getSerializable("car");
        }

        // Display the Car information (You can display this in a RecyclerView or TextView)
        if (car != null) {
            TextView carInfo = view.findViewById(R.id.textViewCarInfo); // Add a TextView in your layout
            carInfo.setText("Car: " + car.getCompanyName() + " " + car.getTypeOfCar() + ", Year: " + car.getYear() + ", Available: " + (car.isAvailable() ? "Yes" : "No"));
        }

        return view;
    }
}
