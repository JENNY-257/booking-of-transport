package com.example.bookingtravel;

import android.content.Intent;
import android.os.Bundle;
//import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.appcompat.widget.Toolbar;

//import com.example.bookingtravel.callbacks.OnCarDataSubmitListener;
//import com.example.bookingtravel.model.Car;

public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Apply window insets for full-screen UI adjustments
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Set up the Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Remove the title by setting it to an empty string
        toolbar.setTitle("");
        // Hide app name not displaying on toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        // Set OnClickListener for the Trips element
        findViewById(R.id.tripsIcon).setOnClickListener(v -> {
            // Create an Intent to start FragmentsActivity
            Intent intent = new Intent(MainActivity.this, FragmentsActivity.class);
            startActivity(intent);
        });



    }

}
