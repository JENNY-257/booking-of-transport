package com.example.bookingtravel;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class FormFragment extends Fragment {

    private EditText companyInput, typeInput, yearInput, destinationInput, seatsInput;
    private CheckBox availabilityCheckBox;
    private Button submitButton;
    private static final String POST_URL = "http://10.0.2.2:8080/save-car";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_form, container, false);

        // Initialize form fields
        companyInput = view.findViewById(R.id.companyInput);
        typeInput = view.findViewById(R.id.typeInput);
        yearInput = view.findViewById(R.id.yearInput);
        destinationInput = view.findViewById(R.id.destinationInput);
        seatsInput = view.findViewById(R.id.seatsInput);
        availabilityCheckBox = view.findViewById(R.id.availabilityCheckBox);
        submitButton = view.findViewById(R.id.submitButton);

        // Submit button click listener
        submitButton.setOnClickListener(v -> submitCar());

        return view;
    }

    private void sendPostRequest(JSONObject carData) {
        JsonObjectRequest postRequest = new JsonObjectRequest(
                Request.Method.POST, POST_URL, carData,
                response -> {
                    Toast.makeText(getContext(), "Car submitted!", Toast.LENGTH_SHORT).show();
                    clearForm();  // Call the method to clear the form
                },
                error -> {
                    // Log and display the error
                    Toast.makeText(getContext(), "Error: " + error.toString(), Toast.LENGTH_LONG).show();
                    Log.e("VolleyError", error.toString());
                }

        ) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };

        Volley.newRequestQueue(requireContext()).add(postRequest);
    }

    private void submitCar() {
        try {
            String company = companyInput.getText().toString().trim();
            String type = typeInput.getText().toString().trim();
            int year = Integer.parseInt(yearInput.getText().toString().trim());
            String destination = destinationInput.getText().toString().trim();
            int seats = Integer.parseInt(seatsInput.getText().toString().trim());
            boolean isAvailable = availabilityCheckBox.isChecked();

            // Validate required fields
            if (company.isEmpty() || type.isEmpty() || destination.isEmpty()) {
                Toast.makeText(getContext(), "Please fill all required fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // Create a JSONObject with the car data
            JSONObject carData = new JSONObject();
            carData.put("companyName", company);
            carData.put("typeOfCar", type);
            carData.put("year", year);
            carData.put("destination", destination);
            carData.put("numberOfSeats", seats);
            carData.put("isAvailable", isAvailable);

            // Call sendPostRequest() with the JSON object
            sendPostRequest(carData);

        } catch (JSONException | NumberFormatException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "Invalid input data.", Toast.LENGTH_LONG).show();
        }
    }

    // Method to clear the input fields
    private void clearForm() {
        companyInput.setText("");
        typeInput.setText("");
        yearInput.setText("");
        destinationInput.setText("");
        seatsInput.setText("");
        availabilityCheckBox.setChecked(false);
    }
}
