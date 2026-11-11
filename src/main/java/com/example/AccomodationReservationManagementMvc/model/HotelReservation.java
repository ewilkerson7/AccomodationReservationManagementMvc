package com.example.AccomodationReservationManagementMvc.model;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("HOTEL")
public class HotelReservation extends Reservation {
    private boolean kitchenette; // Indication of kitchenette[cite: 2]

    public HotelReservation() {
        super();
        this.setNumberOfBedrooms(1); // Hotel can only be reserved for single bedroom[cite: 2]
        this.setNumberOfBathrooms(1); // Hotel can only be reserved for single bathroom[cite: 2]
        this.setNumberOfBeds(2); // Hotel can only be reserved for two beds[cite: 2]
    }

    // Getters, Setters, and toString()
    public String getReservationType()
    {
        return "HOTEL";
    }


    public boolean getKitchenette() {
        return kitchenette;
    }

    public void setKitchenette(boolean kitchenette) {
        this.kitchenette = kitchenette;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}