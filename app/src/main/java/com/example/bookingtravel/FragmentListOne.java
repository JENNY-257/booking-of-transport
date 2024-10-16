package com.example.bookingtravel;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bookingtravel.model.Car;

import java.util.ArrayList;
import java.util.List;

public class FragmentListOne extends Fragment {

    private RecyclerView recyclerView;
    private CarAdapter carAdapter;
    private List<Car> carList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_one, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Populate the car list with sample data
        carList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            carList.add(new Car(
                    "Virunga " ,
                    "Ritico, " + (2000 + i),
                    2000 + i,
                    true,
                    "Rubavu",
                    8
            ));
        }



        carAdapter = new CarAdapter(carList);
        recyclerView.setAdapter(carAdapter);

        return view;
    }
}
