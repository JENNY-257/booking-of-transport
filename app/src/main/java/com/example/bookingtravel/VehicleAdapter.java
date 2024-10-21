package com.example.bookingtravel;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AlertDialog;

import com.example.bookingtravel.R;
import com.example.bookingtravel.database.CarDatabaseHelper;
import com.example.bookingtravel.model.Car;
import java.util.List;

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.VehicleViewHolder> {

    private final List<Car> carList;
    private OnUpdateClickListener onUpdateClickListener;
    private final CarDatabaseHelper databaseHelper;
    private final Context context;
    public interface OnUpdateClickListener {
        void onUpdateClick(Car car);
        void onCarDeleted();
    }

    public VehicleAdapter(List<Car> carList,Context context, OnUpdateClickListener listener) {
        this.carList = carList;
        this.context = context;
        this.onUpdateClickListener = listener;
        this.databaseHelper = new CarDatabaseHelper(context);
    }

    @NonNull
    @Override
    public VehicleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.car_item, parent, false);
        return new VehicleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VehicleViewHolder holder, int position) {
        Car car = carList.get(position);
        holder.companyName.setText(car.getCompanyName());
        holder.typeOfCar.setText(car.getTypeOfCar());
        holder.year.setText(String.valueOf(car.getYear()));
        holder.isAvailable.setText(car.isAvailable() ? "Available" : "Not Available");
        holder.destination.setText(car.getDestination());
        holder.numberOfSeats.setText(String.valueOf(car.getNumberOfSeats()));
        holder.btnUpdate.setOnClickListener(v -> onUpdateClickListener.onUpdateClick(car));
        holder.btnDelete.setOnClickListener(v -> showDeleteConfirmation(car, position));
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    private void showDeleteConfirmation(Car car, int position) {
        new AlertDialog.Builder(context)
                .setTitle("Confirm Delete")
                .setMessage("Are you sure you want to delete this car?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    Log.d("VehicleAdapter", "Deleting car with ID: " + car.getId());
                    databaseHelper.deleteCar(car.getId()); // Delete car from DB
                    carList.remove(position); // Remove from the list
                    notifyItemRemoved(position); // Notify RecyclerView
                    onUpdateClickListener.onCarDeleted(); // Notify deletion
                })
                .setNegativeButton("No", (dialog, which) -> dialog.dismiss())
                .show();
    }


    public static class VehicleViewHolder extends RecyclerView.ViewHolder {
        TextView companyName, typeOfCar, year, isAvailable, destination, numberOfSeats;
        Button btnUpdate, btnDelete;
        public VehicleViewHolder(@NonNull View itemView) {
            super(itemView);
            companyName = itemView.findViewById(R.id.textViewCompanyName);
            typeOfCar = itemView.findViewById(R.id.textViewTypeOfCar);
            year = itemView.findViewById(R.id.textViewYear);
            isAvailable = itemView.findViewById(R.id.textViewIsAvailable);
            destination = itemView.findViewById(R.id.textViewDestination);
            numberOfSeats = itemView.findViewById(R.id.textViewNumberOfSeats);
            btnUpdate = itemView.findViewById(R.id.btnUpdate);
            btnDelete = itemView.findViewById(R.id.btnDelete);

        }
    }
}