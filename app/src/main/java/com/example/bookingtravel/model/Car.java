package com.example.bookingtravel.model;

import java.io.Serializable;

public class Car implements Serializable {
    private String companyName;
    private String typeOfCar;  // Replaced model with typeOfCar
    private int year;
    private boolean isAvailable;
    private String destination;
    private int numberOfSeats;

    // Constructor, Getters, and Setters here

    public Car(String companyName, String typeOfCar, int year, boolean isAvailable, String destination, int numberOfSeats) {
        this.companyName = companyName;
        this.typeOfCar = typeOfCar;
        this.year = year;
        this.isAvailable = isAvailable;
        this.destination = destination;
        this.numberOfSeats = numberOfSeats;
    }

    // Getters and setters
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTypeOfCar() {
        return typeOfCar;
    }

    public void setTypeOfCar(String typeOfCar) {
        this.typeOfCar = typeOfCar;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }
}
