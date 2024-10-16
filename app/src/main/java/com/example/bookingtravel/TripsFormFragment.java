package com.example.bookingtravel;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.example.bookingtravel.model.Car;

public class TripsFormFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trips_form, container, false);

        // Updated references to reflect the changes in the Car object
        EditText editTextCompanyName = view.findViewById(R.id.editTextCompanyName);
        EditText editTextTypeOfCar = view.findViewById(R.id.editTextTypeOfCar);
        EditText editTextYear = view.findViewById(R.id.editTextYear);
        EditText editTextDestination = view.findViewById(R.id.editTextDestination);
        EditText editTextNumberOfSeats = view.findViewById(R.id.editTextNumberOfSeats); // New field for number of seats
        CheckBox checkBoxAvailable = view.findViewById(R.id.checkBoxAvailable);
        Button buttonSubmit = view.findViewById(R.id.buttonSubmit);

        ImageButton imageButton = view.findViewById(R.id.imageButton2);

        imageButton.setOnClickListener(imageView -> {
            Intent intent = new Intent(getActivity(), TabbedActivity.class);
            Log.d("TripsFormFragment", "ImageButton clicked");
            startActivity(intent);
        });


        buttonSubmit.setOnClickListener(v -> {
            // Retrieve inputs and handle validations
            String companyName = editTextCompanyName.getText().toString().trim();
            String typeOfCar = editTextTypeOfCar.getText().toString().trim();
            String yearString = editTextYear.getText().toString().trim();
            String destination = editTextDestination.getText().toString().trim();
            String numberOfSeatsString = editTextNumberOfSeats.getText().toString().trim();


            // Validate inputs
            if (companyName.isEmpty()) {
                showToast("Please enter the company name.", false);
                return;
            }

            if (typeOfCar.isEmpty()) {
                showToast("Please enter the type of car.", false);
                return;
            }

            if (yearString.isEmpty()) {
                showToast("Please enter the year of manufacture.", false);
                return;
            }

            if (numberOfSeatsString.isEmpty()) {
                showToast("Please enter the number of seats.", false);
                return;
            }

            int year;
            try {
                year = Integer.parseInt(yearString);
                if (year < 1886 || year > 2024) { // Basic validation for year
                    showToast("Please enter a valid year.", false);
                    return;
                }
            } catch (NumberFormatException e) {
                showToast("Year must be a number.", false);
                return;
            }

            int numberOfSeats;
            try {
                numberOfSeats = Integer.parseInt(numberOfSeatsString);
                if (numberOfSeats <= 0) {
                    showToast("Please enter a valid number of seats.", false);
                    return;
                }
            } catch (NumberFormatException e) {
                showToast("Number of seats must be a number.", false);
                return;
            }

            if (destination.isEmpty()) {
                showToast("Please enter the destination.", false);
                return;
            }

            if (!checkBoxAvailable.isChecked()) {
                showToast("Please confirm that the car is available.", false);
                return;
            }

            // Create the Car object with the updated properties
            Car car = new Car(companyName, typeOfCar, year, checkBoxAvailable.isChecked(), destination, numberOfSeats);
            showToast("Car data submitted successfully!", true); // Success message

            // Create a new instance of TripsListFragment
            TripsListFragment tripsListFragment = new TripsListFragment();

            // Use a Bundle to pass the Car object to TripsListFragment
            Bundle args = new Bundle();
            args.putSerializable("car", car); // Assuming Car implements Serializable
            tripsListFragment.setArguments(args);

            // Replace the current fragment with TripsListFragment
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, tripsListFragment); // Use your container ID
            transaction.addToBackStack(null); // Add to back stack to allow navigation back
            transaction.commit();
        });

        return view;
    }

    // Helper method to show a toast message
    private void showToast(String message, boolean isSuccess) {
        Toast toast = new Toast(getActivity());
        View customView = LayoutInflater.from(getActivity()).inflate(R.layout.custom_toast, null);
        TextView toastText = customView.findViewById(R.id.toastText);
        toastText.setText(message);

        // Set background color based on success or error
        if (isSuccess) {
            customView.setBackgroundResource(R.drawable.toast_background); // Blue background for success
            ((GradientDrawable) customView.getBackground()).setColor(Color.BLUE); // Change to blue
        } else {
            ((GradientDrawable) customView.getBackground()).setColor(Color.RED); // Red background for error
        }

        toast.setView(customView);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }
}
