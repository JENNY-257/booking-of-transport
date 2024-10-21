package com.example.bookingtravel;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.bookingtravel.model.Car;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class TabbedActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tabbed);

        // Apply edge-to-edge insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize ViewPager2 and TabLayout
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        // Set the adapter for ViewPager2
        viewPager.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                switch (position) {
                    case 0:
                        return new FragmentListOne(); // Fragment to display the RecyclerView
                    case 1:
                        return new FragmentList2();
                    default:
                        return new FragmentListOne();
                }
            }

            @Override
            public int getItemCount() {
                return 2; // We have 2 tabs (Fragment1 and Fragment2)
            }
        });

        // Set up TabLayout with ViewPager2
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Cars");
                    tab.setContentDescription("Cars Tab"); // Set content description for accessibility
                    break;
                case 1:
                    tab.setText("Other Cars");
                    tab.setContentDescription("Other Tab"); // Set content description for accessibility
                    break;
            }
        }).attach();
    }

    public void replaceWithFragmentUpdate(Car car) {
        FragmentUpdate fragmentUpdate = FragmentUpdate.newInstance(car);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, fragmentUpdate)  // Ensure correct container
                .addToBackStack(null)  // Allows back navigation
                .commit();

        // Make the fragment container visible and hide the ViewPager
        findViewById(R.id.fragmentContainer).setVisibility(View.VISIBLE);
        findViewById(R.id.viewPager).setVisibility(View.GONE);
    }
    public void showTabbedView() {
        // Hide the fragment container and show the ViewPager again
        findViewById(R.id.fragmentContainer).setVisibility(View.GONE);
        findViewById(R.id.viewPager).setVisibility(View.VISIBLE);

        // Ensure tabs are correctly updated if needed
        viewPager.getAdapter().notifyDataSetChanged();
    }

}
