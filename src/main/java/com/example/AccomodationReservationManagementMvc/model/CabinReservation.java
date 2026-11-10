package com.example.AccomodationReservationManagementMvc.model;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CABIN")
public class CabinReservation extends Reservation {
    private boolean fullKitchen; // Indication if there is full kitchen[cite: 2]
    private boolean loft; // Indication if cabin includes a loft[cite: 2]

    // Getters, Setters, and toString()


    public boolean getFullKitchen() {
        return fullKitchen;
    }

    public void setFullKitchen(boolean fullKitchen) {
        this.fullKitchen = fullKitchen;
    }

    public boolean getLoft() {
        return loft;
    }

    public void setLoft(boolean loft) {
        this.loft = loft;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
