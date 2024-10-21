package com.example.bookingtravel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.bookingtravel.model.Car; // Make sure to import the Car model class

import java.util.ArrayList;
import java.util.List;

public class CarDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "carDatabase.db";
    private static final int DATABASE_VERSION = 2; // Increment version if you changed the schema

    public static final String TABLE_NAME = "Cars";
    public static final String COLUMN_ID = "id"; // Not used in Car constructor
    public static final String COLUMN_COMPANY_NAME = "companyName";
    public static final String COLUMN_TYPE = "typeOfCar";
    public static final String COLUMN_YEAR = "year";
    public static final String COLUMN_IS_AVAILABLE = "isAvailable";
    public static final String COLUMN_DESTINATION = "destination";
    public static final String COLUMN_SEATS = "numberOfSeats";

    private static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_COMPANY_NAME + " TEXT, " +
                    COLUMN_TYPE + " TEXT, " +
                    COLUMN_YEAR + " INTEGER, " +
                    COLUMN_IS_AVAILABLE + " INTEGER, " +
                    COLUMN_DESTINATION + " TEXT, " +
                    COLUMN_SEATS + " INTEGER)";

    public CarDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insertCar(String companyName, String typeOfCar, int year, boolean isAvailable, String destination, int numberOfSeats) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_COMPANY_NAME, companyName);
        values.put(COLUMN_TYPE, typeOfCar);
        values.put(COLUMN_YEAR, year);
        values.put(COLUMN_IS_AVAILABLE, isAvailable ? 1 : 0); // Store boolean as integer
        values.put(COLUMN_DESTINATION, destination);
        values.put(COLUMN_SEATS, numberOfSeats);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<Car> getAllCars() {
        List<Car> carList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);

        if (cursor != null) {
            // Get column indices
            int indexId = cursor.getColumnIndex(COLUMN_ID);
            int indexCompanyName = cursor.getColumnIndex(COLUMN_COMPANY_NAME);
            int indexType = cursor.getColumnIndex(COLUMN_TYPE);
            int indexYear = cursor.getColumnIndex(COLUMN_YEAR);
            int indexIsAvailable = cursor.getColumnIndex(COLUMN_IS_AVAILABLE);
            int indexDestination = cursor.getColumnIndex(COLUMN_DESTINATION);
            int indexSeats = cursor.getColumnIndex(COLUMN_SEATS);

            // Log column indices for debugging
            Log.d("CarDatabaseHelper", "Column Indices: " +
                    "Company Name: " + indexCompanyName +
                    ", Type: " + indexType +
                    ", Year: " + indexYear +
                    ", Is Available: " + indexIsAvailable +
                    ", Destination: " + indexDestination +
                    ", Seats: " + indexSeats);

            while (cursor.moveToNext()) {
                int id = cursor.getInt(indexId);
                String companyName = cursor.getString(indexCompanyName);
                String typeOfCar = cursor.getString(indexType);
                int year = cursor.getInt(indexYear);
                boolean isAvailable = cursor.getInt(indexIsAvailable) == 1;
                String destination = cursor.getString(indexDestination);
                int numberOfSeats = cursor.getInt(indexSeats);

                // Create a Car object
                Car car = new Car( id,companyName, typeOfCar, year, isAvailable, destination, numberOfSeats);
                carList.add(car);
            }
            cursor.close();
        }

        db.close();
        return carList;
    }
    public void updateCar(Car car) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_COMPANY_NAME, car.getCompanyName());
        values.put(COLUMN_TYPE, car.getTypeOfCar());
        values.put(COLUMN_YEAR, car.getYear());
        values.put(COLUMN_IS_AVAILABLE, car.isAvailable() ? 1 : 0);
        values.put(COLUMN_DESTINATION, car.getDestination());
        values.put(COLUMN_SEATS, car.getNumberOfSeats());

        db.update(TABLE_NAME, values, COLUMN_ID + " = ?", new String[]{String.valueOf(car.getId())});
        db.close();
    }

    public boolean deleteCar(int carId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsAffected = db.delete("Cars", "id = ?", new String[]{String.valueOf(carId)});
        db.close();
        return rowsAffected > 0;
    }



}
