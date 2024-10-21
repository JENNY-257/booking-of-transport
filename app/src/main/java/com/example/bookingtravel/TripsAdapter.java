package com.example.bookingtravel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.bookingtravel.model.Car;

import java.util.List;

public class TripsAdapter extends ArrayAdapter<Car> {

    public TripsAdapter(Context context, List<Car> cars) {
        super(context, 0, cars);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_trip, parent, false);
        }

        Car car = getItem(position);

        TextView carDetails = convertView.findViewById(R.id.carDetails);
        if (car != null) {
            carDetails.setText("Car: " + car.getCompanyName() + " " + car.getTypeOfCar() +
                    ", Year: " + car.getYear() + ", Seats: " + car.getNumberOfSeats());
        }

        return convertView;
    }

}
