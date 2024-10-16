package com.example.bookingtravel;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.bookingtravel.callbacks.OnCarDataSubmitListener;
import com.example.bookingtravel.model.Car;

public class FragmentsActivity extends AppCompatActivity implements OnCarDataSubmitListener {
    private TripsListFragment tripsListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fragments);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Load the TripsFormFragment by default
        if (savedInstanceState == null) {
            loadTripsFormFragment();
        }
    }

    private void loadTripsFormFragment() {
        TripsFormFragment tripsFormFragment = new TripsFormFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, tripsFormFragment); // Replace with your container's ID
        fragmentTransaction.commit();
    }

    @Override
    public void onCarDataSubmit(Car car) {
        // Create a new instance of TripsListFragment
        TripsListFragment tripsListFragment = new TripsListFragment();

        // Pass the new car data to the list fragment using Bundle
        Bundle args = new Bundle();
        args.putSerializable("car", car);
        tripsListFragment.setArguments(args);

        // Switch to the TripsListFragment when data is submitted
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, tripsListFragment);
        transaction.addToBackStack(null); // Allow back navigation to form
        transaction.commit();

        // Show a confirmation Toast message
        Toast.makeText(this, "Car submitted: " + car.getCompanyName() + " " + car.getTypeOfCar() +
                " with " + car.getNumberOfSeats() + " seats", Toast.LENGTH_SHORT).show();
    }

}
