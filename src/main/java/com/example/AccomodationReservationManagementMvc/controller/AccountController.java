package com.example.AccomodationReservationManagementMvc.controller;
import com.example.AccomodationReservationManagementMvc.model.Account;
import com.example.AccomodationReservationManagementMvc.model.Reservation;
import com.example.AccomodationReservationManagementMvc.repository.AccountRepository;
import com.example.AccomodationReservationManagementMvc.repository.ReservationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AccountController {

    private final AccountRepository accountRepository;
    private final ReservationRepository reservationRepository; // Inject ReservationRepository[cite: 10]

    public AccountController(AccountRepository accountRepository, ReservationRepository reservationRepository) {
        this.accountRepository = accountRepository;
        this.reservationRepository = reservationRepository;
    }

    @GetMapping("/accounts")
    public String viewAccounts(Model model) {
        model.addAttribute("accounts", accountRepository.findAll());
        return "accounts";
    }

    @GetMapping("/accounts/reservations/{accountNumber}")
    public String viewAccountDetails(@PathVariable String accountNumber, Model model) {
        Account account = accountRepository.findById(accountNumber).orElse(null);
        if (account != null) {
            model.addAttribute("reservations", account.getReservations());
        }
        return "reservations";
    }

    // New endpoint to handle "View Details" for a specific reservation
    @GetMapping("/reservations/{reservationNumber}")
    public String viewReservationDetails(@PathVariable String reservationNumber, Model model) {
        Reservation reservation = reservationRepository.findById(reservationNumber).orElse(null);
        model.addAttribute("reservation", reservation);
        return "reservation"; // Resolves to reservation.html
    }
}