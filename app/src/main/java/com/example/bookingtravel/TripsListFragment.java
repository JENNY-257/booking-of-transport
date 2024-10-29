package com.example.bookingtravel;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

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

        // Static data for the car list
        loadStaticData();

        // Set up the adapter with the static car list
        TripsAdapter adapter = new TripsAdapter(getActivity(), carList);
        listView.setAdapter(adapter);
        ImageButton backButton = view.findViewById(R.id.back_button_icon);
        backButton.setOnClickListener(v -> {
            // Navigate back to the FragmentsActivity
            Intent intent = new Intent(getActivity(), FragmentsActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK); // Clear the stack
            startActivity(intent);
//            getActivity().finish(); // Optional: Finish the current activity
        });

        ImageButton nextButton = view.findViewById(R.id.next_button_icon);
        nextButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), NetworkActivity.class);
            startActivity(intent);
        });
        return view;
    }

    // Method to load static data
    private void loadStaticData() {
        carList.add(new Car(1, "Toyota", "Sedan", 2020, true, "Kigali", 4));
        carList.add(new Car(2, "Honda", "SUV", 2019, false, "Butare", 5));
        carList.add(new Car(3, "Nissan", "Hatchback", 2021, true, "Gisenyi", 4));
        carList.add(new Car(4, "Subaru", "Coupe", 2018, true, "Musanze", 2));
        carList.add(new Car(5, "Ford", "Pickup", 2022, true, "Nyamata", 2));
    }
}
