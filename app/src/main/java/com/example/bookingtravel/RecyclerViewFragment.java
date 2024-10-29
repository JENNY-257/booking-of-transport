package com.example.bookingtravel;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.bookingtravel.callbacks.OnCarAddedListener;
import com.example.bookingtravel.model.Car;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class RecyclerViewFragment extends Fragment  {

    private RecyclerView recyclerView;
    private CarAdapterApp adapter;
    private List<Car> carList = new ArrayList<>();
    private static final String GET_URL = "http://10.0.2.2:8080/cars";  // Replace with your actual URL

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);

        // Setup RecyclerView
        adapter = new CarAdapterApp(carList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        // Fetch cars from the server
        fetchCars();

        return view;
    }


    private void fetchCars() {
        JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, GET_URL, null,
                response -> {
                    carList.clear();  // Clear the list to avoid duplicates
                    try {
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject carObject = response.getJSONObject(i);
                            Car car = new Car(
                                    carObject.getInt("id"),
                                    carObject.getString("companyName"),
                                    carObject.getString("typeOfCar"),
                                    carObject.getInt("year"),
                                    carObject.getBoolean("available"),  // Change 'isAvailable' to 'available'
                                    carObject.getString("destination"),
                                    carObject.getInt("numberOfSeats")
                            );
                            carList.add(car);
                        }
                        Log.d("FetchCars", "Car list size: " + carList.size());
                        adapter.updateData(carList);  // Update adapter with new data
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getContext(), "Error parsing data.", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> Toast.makeText(getContext(), "Failed to fetch cars.", Toast.LENGTH_SHORT).show());

        // Add the request to the request queue
        RequestQueue queue = Volley.newRequestQueue(requireContext());
        queue.add(getRequest);
    }

}
