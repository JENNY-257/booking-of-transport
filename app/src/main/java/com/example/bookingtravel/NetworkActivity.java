package com.example.bookingtravel;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class NetworkActivity extends AppCompatActivity {

    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager2 viewPager = findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(this);

        // Add Fragments to ViewPagerAdapter
        viewPagerAdapter.addFragment(new FormFragment(), "Add Car");
        viewPagerAdapter.addFragment(new RecyclerViewFragment(), "View Cars");

        viewPager.setAdapter(viewPagerAdapter);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) ->
                tab.setText(viewPagerAdapter.getTitle(position))
        ).attach();
        ImageButton backButton = findViewById(R.id.back_button_btId);
        backButton.setOnClickListener(v -> finish());
    }

}
