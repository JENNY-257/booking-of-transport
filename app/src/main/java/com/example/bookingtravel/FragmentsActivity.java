package com.example.bookingtravel;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class FragmentsActivity extends AppCompatActivity  {
    private TripsListFragment tripsListFragment;
    private Button navigateButton;
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

        // Setup button to navigate to TripsListFragment
        navigateButton = findViewById(R.id.navigateButton);
        navigateButton.setOnClickListener(v -> {
            loadTripsListFragment();
        });
    }

    private void loadTripsFormFragment() {
        TripsFormFragment tripsFormFragment = new TripsFormFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, tripsFormFragment); // Replace with your container's ID
        fragmentTransaction.commit();
    }

    private void loadTripsListFragment() {
        TripsListFragment tripsListFragment = new TripsListFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, tripsListFragment); // Replace with your container's ID
        fragmentTransaction.addToBackStack(null); // Optional: Add to back stack to allow back navigation
        fragmentTransaction.commit();
        navigateButton.setVisibility(View.GONE);
    }



}
