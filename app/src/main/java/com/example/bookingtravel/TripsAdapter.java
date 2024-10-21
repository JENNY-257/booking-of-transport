package com.example.bookingtravel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bookingtravel.model.Car;

import java.util.List;

public class TripsAdapter extends ArrayAdapter<Car> {

    public TripsAdapter(Context context, List<Car> cars) {
        super(context, 0, cars);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Car car = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
        }

        TextView text1 = convertView.findViewById(android.R.id.text1);
        TextView text2 = convertView.findViewById(android.R.id.text2);

        text1.setText(car.getCompanyName() + " " + car.getTypeOfCar());
        text2.setText("Year: " + car.getYear() + " - Available: " + (car.isAvailable() ? "Yes" : "No"));

        return convertView;
    }
}
