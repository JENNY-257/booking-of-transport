package com.example.bookingtravel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.bookingtravel.database.CarDatabaseHelper;
import com.example.bookingtravel.model.Car;
import java.util.List;

public class FragmentList2 extends Fragment {

    private RecyclerView recyclerView;
    private VehicleAdapter vehicleAdapter;
    private CarDatabaseHelper databaseHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list2, container, false);

        recyclerView = view.findViewById(R.id.recyclerView1);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize database helper and load car data
        databaseHelper = new CarDatabaseHelper(getContext());
        loadCarData();

        return view;
    }

    private void loadCarData() {
        List<Car> carList = databaseHelper.getAllCars();
        vehicleAdapter = new VehicleAdapter(carList, getContext(), new VehicleAdapter.OnUpdateClickListener() {
            @Override
            public void onUpdateClick(Car car) {
                // Replace with FragmentUpdate when the update button is clicked
                vehicleAdapter.notifyDataSetChanged();
                ((TabbedActivity) requireActivity()).replaceWithFragmentUpdate(car);
            }

            @Override
            public void onCarDeleted() {
                loadCarData();  // Reload data from the database
                vehicleAdapter.notifyDataSetChanged();  // Refresh adapter
                if (getActivity() instanceof TabbedActivity) {
                    ((TabbedActivity) getActivity()).showTabbedView();  // Ensure UI updates
                }
            }

        });

        recyclerView.setAdapter(vehicleAdapter);
    }
}
