package com.example.bookingtravel;

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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;

import com.example.bookingtravel.callbacks.OnCarDataSubmitListener;
import com.example.bookingtravel.database.CarDatabaseHelper;
import com.example.bookingtravel.model.Car;

public class TripsFormFragment extends Fragment {

    private CarDatabaseHelper databaseHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trips_form, container, false);

        // Initialize database helper
        databaseHelper = new CarDatabaseHelper(getContext());

        EditText editTextCompanyName = view.findViewById(R.id.editTextCompanyName);
        EditText editTextTypeOfCar = view.findViewById(R.id.editTextTypeOfCar);
        EditText editTextYear = view.findViewById(R.id.editTextYear);
        EditText editTextDestination = view.findViewById(R.id.editTextDestination);
        EditText editTextNumberOfSeats = view.findViewById(R.id.editTextNumberOfSeats);
        CheckBox checkBoxAvailable = view.findViewById(R.id.checkBoxAvailable);
        Button buttonSubmit = view.findViewById(R.id.buttonSubmit);
        ImageView imageButton = view.findViewById(R.id.imageButton2);

        imageButton.setOnClickListener(imageView -> {
            Intent intent = new Intent(getActivity(), TabbedActivity.class);
            Log.d("TripsFormFragment", "ImageButton clicked");
            startActivity(intent);
        });

        buttonSubmit.setOnClickListener(v -> {
            String companyName = editTextCompanyName.getText().toString().trim();
            String typeOfCar = editTextTypeOfCar.getText().toString().trim();
            String yearString = editTextYear.getText().toString().trim();
            String destination = editTextDestination.getText().toString().trim();
            String numberOfSeatsString = editTextNumberOfSeats.getText().toString().trim();

            if (!validateInputs(companyName, typeOfCar, yearString, numberOfSeatsString)) return;

            int year = Integer.parseInt(yearString);
            int numberOfSeats = Integer.parseInt(numberOfSeatsString);
            boolean isAvailable = checkBoxAvailable.isChecked();

            // Create a Car object
            Car car = new Car(companyName, typeOfCar, year, isAvailable, destination, numberOfSeats);

            // Save the car to the database
            databaseHelper.insertCar(companyName, typeOfCar, year, isAvailable, destination, numberOfSeats);

            // Confirm the operation
            showToast("Car saved successfully!", true);
            clearForm(editTextCompanyName, editTextTypeOfCar, editTextYear, editTextDestination, editTextNumberOfSeats);
            checkBoxAvailable.setChecked(false);
        });

        return view;
    }

    private boolean validateInputs(String companyName, String typeOfCar, String yearString, String numberOfSeatsString) {
        if (companyName.isEmpty()) {
            showToast("Please enter the company name.", false);
            return false;
        }

        if (typeOfCar.isEmpty()) {
            showToast("Please enter the type of car.", false);
            return false;
        }

        if (yearString.isEmpty()) {
            showToast("Please enter the year of manufacture.", false);
            return false;
        }

        if (numberOfSeatsString.isEmpty()) {
            showToast("Please enter the number of seats.", false);
            return false;
        }

        try {
            int year = Integer.parseInt(yearString);
            if (year < 1886 || year > 2024) {
                showToast("Please enter a valid year.", false);
                return false;
            }
        } catch (NumberFormatException e) {
            showToast("Year must be a number.", false);
            return false;
        }

        try {
            int seats = Integer.parseInt(numberOfSeatsString);
            if (seats <= 0) {
                showToast("Please enter a valid number of seats.", false);
                return false;
            }
        } catch (NumberFormatException e) {
            showToast("Number of seats must be a number.", false);
            return false;
        }

        return true;
    }

    private void clearForm(EditText... fields) {
        // Reset all EditText fields to empty
        for (EditText field : fields) {
            field.setText("");
        }
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
