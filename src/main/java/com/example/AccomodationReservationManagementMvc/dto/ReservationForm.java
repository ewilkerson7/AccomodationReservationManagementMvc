package com.example.AccomodationReservationManagementMvc.dto;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class ReservationForm {
    private String accountNumber;
    private String reservationType = "HOTEL"; // Default selection

    @Id
    @Column(length = 10, nullable = false, unique = true)
    @NotBlank(message = "reservationNumber is required")
    @Pattern(
            regexp = "^[CHO]\\d{9}$",
            message = "reservationNumber must be 10 chars, starts with 'C', 'H, or 'O'."
    )
    private String reservationNumber;

    private String physicalAddress;

    private String mailingAddress;

    @Future(message = "startDate time must be in the future")
    @DateTimeFormat(pattern = "yyyy-MM-dd") // Specify the date format
    private LocalDate startDate;

    @Min(value = 1, message = "numberOfNights cannot be zero.")
    private int numberOfNights;

    @Min(value = 1, message = "numberOfBeds cannot be zero.")
    private int numberOfBeds = 2;

    @Min(value = 1, message = "numberOfBedrooms cannot be zero.")
    private int numberOfBedrooms = 1;

    @Min(value = 1, message = "numberOfBathrooms cannot be zero.")
    private int numberOfBathrooms = 1;

    @Min(value = 1, message = "lodgingSizeSqFt cannot be zero.")
    private int lodgingSizeSqFt;

    private Double reservationPrice; // Not set until completed[cite: 2]

    private String status = "DRAFT"; // draft, completed, or cancelled[cite: 2]

    // Subclass-specific fields
    private boolean kitchenette;
    private boolean fullKitchen;
    private boolean loft;

   // @Min(value = 1, message = "numberOfFloors cannot be zero.")
    private int numberOfFloors = 1;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(String reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public String getReservationType() {
        return reservationType;
    }

    public void setReservationType(String reservationType) {
        this.reservationType = reservationType;
    }

    public String getPhysicalAddress() {
        return physicalAddress;
    }

    public void setPhysicalAddress(String physicalAddress) {
        this.physicalAddress = physicalAddress;
    }

    public String getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(String mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public int getNumberOfNights() {
        return numberOfNights;
    }

    public void setNumberOfNights(int numberOfNights) {
        this.numberOfNights = numberOfNights;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public int getNumberOfBedrooms() {
        return numberOfBedrooms;
    }

    public void setNumberOfBedrooms(int numberOfBedrooms) {
        this.numberOfBedrooms = numberOfBedrooms;
    }

    public int getNumberOfBathrooms() {
        return numberOfBathrooms;
    }

    public void setNumberOfBathrooms(int numberOfBathrooms) {
        this.numberOfBathrooms = numberOfBathrooms;
    }

    public int getLodgingSizeSqFt() {
        return lodgingSizeSqFt;
    }

    public void setLodgingSizeSqFt(int lodgingSizeSqFt) {
        this.lodgingSizeSqFt = lodgingSizeSqFt;
    }

    public Double getReservationPrice() {
        return reservationPrice;
    }

    public void setReservationPrice(Double reservationPrice) {
        this.reservationPrice = reservationPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean getKitchenette() {
        return kitchenette;
    }

    public void setKitchenette(boolean kitchenette) {
        this.kitchenette = kitchenette;
    }

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