package com.example.AccomodationReservationManagementMvc.model;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("HOUSE")
public class HouseReservation extends Reservation {
    private int numberOfFloors; // Number of floors[cite: 2]

    // Getters, Setters, and toString()

    public String getReservationType()
    {
        return "HOUSE";
    }

    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    public void setNumberOfFloors(int numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
