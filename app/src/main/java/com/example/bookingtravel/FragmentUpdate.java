package com.example.bookingtravel;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bookingtravel.database.CarDatabaseHelper;
import com.example.bookingtravel.model.Car;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentUpdate#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentUpdate extends Fragment {

    private static final String ARG_CAR = "car";
    private Car car;
    private CarDatabaseHelper databaseHelper;

    private EditText editCompanyName, editType, editYear, editDestination, editSeats;
    private CheckBox checkBoxIsAvailable;
    private Button btnUpdate;


    public static FragmentUpdate newInstance(Car car) {
        FragmentUpdate fragment = new FragmentUpdate();
        Bundle args = new Bundle();
        args.putSerializable(ARG_CAR, car);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update, container, false);

        databaseHelper = new CarDatabaseHelper(getContext());

        if (getArguments() != null) {
            car = (Car) getArguments().getSerializable(ARG_CAR);
        }

        editCompanyName = view.findViewById(R.id.editCompanyName);
        editType = view.findViewById(R.id.editType);
        editYear = view.findViewById(R.id.editYear);
        editDestination = view.findViewById(R.id.editDestination);
        editSeats = view.findViewById(R.id.editSeats);
        checkBoxIsAvailable = view.findViewById(R.id.checkBoxIsAvailable);
        btnUpdate = view.findViewById(R.id.btnUpdate);

        // Pre-fill the fields with the selected car's data
        editCompanyName.setText(car.getCompanyName());
        editType.setText(car.getTypeOfCar());
        editYear.setText(String.valueOf(car.getYear()));
        editDestination.setText(car.getDestination());
        editSeats.setText(String.valueOf(car.getNumberOfSeats()));
        checkBoxIsAvailable.setChecked(car.isAvailable());

        btnUpdate.setOnClickListener(v -> {
            // Update the car object with new values
            car.setCompanyName(editCompanyName.getText().toString());
            car.setTypeOfCar(editType.getText().toString());
            car.setYear(Integer.parseInt(editYear.getText().toString()));
            car.setDestination(editDestination.getText().toString());
            car.setNumberOfSeats(Integer.parseInt(editSeats.getText().toString()));
            car.setAvailable(checkBoxIsAvailable.isChecked());

            // Update the car in the database
            databaseHelper.updateCar(car);
            Toast.makeText(getContext(), "Car updated successfully!", Toast.LENGTH_SHORT).show();

            // Navigate back and refresh the list
            requireActivity().getSupportFragmentManager().popBackStack();
            ((TabbedActivity) requireActivity()).showTabbedView();
        });

        // Return to the previous fragment
//            getParentFragmentManager().popBackStack();

        return view;

    }

}