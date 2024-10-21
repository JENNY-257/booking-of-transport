package com.example.bookingtravel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bookingtravel.model.Car;

import java.util.ArrayList;
import java.util.List;

public class TripsListFragment extends Fragment {

    private List<Car> carList;
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trips_list, container, false);

        listView = view.findViewById(R.id.listViewTrips);
        carList = new ArrayList<>();

        // Get the passed Car object from arguments
        if (getArguments() != null) {
            Car car = (Car) getArguments().getSerializable("car");
            if (car != null) {
                carList.add(car); // Add the car to the list
            }
        }

        // Set up the adapter with the car list
        TripsAdapter adapter = new TripsAdapter(getActivity(), carList);
        listView.setAdapter(adapter);

        return view;
    }
}
