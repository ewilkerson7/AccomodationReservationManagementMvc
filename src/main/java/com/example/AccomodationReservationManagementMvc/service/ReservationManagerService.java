package com.example.AccomodationReservationManagementMvc.service;
import com.example.AccomodationReservationManagementMvc.exception.*;
import com.example.AccomodationReservationManagementMvc.model.*;
import com.example.AccomodationReservationManagementMvc.repository.*;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class ReservationManagerService {

    private final AccountRepository accountRepo;
    private final ReservationRepository reservationRepo;

    public ReservationManagerService(AccountRepository accountRepo, ReservationRepository reservationRepo) {
        this.accountRepo = accountRepo;
        this.reservationRepo = reservationRepo;
    }

    public Account addAccount(Account account) {
        if (accountRepo.existsById(account.getAccountNumber())) {
            throw new DuplicateObjectException("Account already exists on add action with the same account number: " + account.getAccountNumber()); //[cite: 2]
        }
        return accountRepo.save(account);
    }

    public Reservation addDraftReservation(String accountNumber, Reservation reservation) {
        if (reservationRepo.existsById(reservation.getReservationNumber())) {
            throw new DuplicateObjectException("Reservation object already exists with the same reservation number: " + reservation.getReservationNumber()); //[cite: 2]
        }
        Account account = accountRepo.findById(accountNumber)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        reservation.setAccount(account); // Account number is updated when reservation is added to a specific account[cite: 2]
        reservation.setStatus("DRAFT"); // New reservation always starts in draft status[cite: 2]
        return reservationRepo.save(reservation);
    }

    public double calculatePricePerNight(Reservation res) {
        double price = 120.0; // Basic price is $120[cite: 2]

        if (res.getLodgingSizeSqFt() > 900) {
            price += 15.0; // Additional fee of $15 if the lodging size is greater than 900 square feet[cite: 2]
        }

        if (res instanceof CabinReservation) {
            CabinReservation cabin = (CabinReservation) res;
            if (cabin.getFullKitchen()) price += 20.0; // Cabin has an additional fee of $20 for full kitchen[cite: 2]
            if (cabin.getNumberOfBathrooms() > 1) {
                price += 5.0 * (cabin.getNumberOfBathrooms() - 1); // $5 for each additional bathroom[cite: 2]
            }
        } else if (res instanceof HotelReservation) {
            HotelReservation hotel = (HotelReservation) res;
            price += 50.0; // Hotel has an additional flat fee of $50[cite: 2]
            if (hotel.getKitchenette()) price += 10.0; // Plus $10 for kitchenette[cite: 2]
        }

        return price;
    }

    public Reservation completeReservation(String reservationNumber) {
        Reservation res = reservationRepo.findById(reservationNumber)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));

        if ("COMPLETED".equals(res.getStatus()) || "CANCELLED".equals(res.getStatus())) {
            throw new IllegalOperationException("Attempted to complete, but status is " + res.getStatus() + " for reservation: " + reservationNumber); //[cite: 2]
        }

        double perNight = calculatePricePerNight(res);
        res.setReservationPrice(perNight * res.getNumberOfNights()); // Price of the reservation is not set until the reservation is completed[cite: 2]
        res.setStatus("COMPLETED");
        return reservationRepo.save(res);
    }

    public Reservation cancelReservation(String reservationNumber) {
        Reservation res = reservationRepo.findById(reservationNumber)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));

        if ("COMPLETED".equals(res.getStatus())) {
            throw new IllegalOperationException("Completed reservation cannot be ever cancelled: " + reservationNumber); //[cite: 2]
        }
        if (res.getStartDate().isBefore(LocalDate.now()) || res.getStartDate().isEqual(LocalDate.now())) {
            throw new IllegalOperationException("Can only be cancelled if the start date is in the future: " + reservationNumber); //[cite: 2]
        }

        res.setStatus("CANCELLED");
        return reservationRepo.save(res);
    }
}