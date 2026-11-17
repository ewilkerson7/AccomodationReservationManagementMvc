package com.example.AccomodationReservationManagementMvc.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Account {
    @Id
    @Column(length = 10, nullable = false, unique = true)
    @Pattern(
            regexp = "^[A]\\d{9}$",
            message = "accountNumber must be 10 chars, starts with 'A'."
    )
    private String accountNumber; // A followed by 9 characters

    @NotBlank(message = "mailingAddress is required")
    private String mailingAddress; // Can be changed at any time[cite: 2]

    @NotBlank(message = "Email is required")
    @Email(message = "Please enter a valid email address")
    private String emailAddress; // Can be changed at any time[cite: 2]

    @NotBlank(message = "phoneNumber is required")
    @Pattern(regexp = "^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$", message = "Phone number must be 123-456-7890, (123) 456-7890, 123.456.7890, or 1234567890")
    private String phoneNumber; // Can be changed at any time[cite: 2]

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations = new ArrayList<>();

    // Constructors, Getters, Setters, and toString() omitted for brevity

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(String mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
