package com.example.AccomodationReservationManagementMvc.controller;

import com.example.AccomodationReservationManagementMvc.model.Account;
import com.example.AccomodationReservationManagementMvc.model.HotelReservation;
import com.example.AccomodationReservationManagementMvc.model.Reservation;
import com.example.AccomodationReservationManagementMvc.repository.AccountRepository;
import com.example.AccomodationReservationManagementMvc.repository.ReservationRepository;
import com.example.AccomodationReservationManagementMvc.service.ReservationManagerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AccountController {

    private final AccountRepository accountRepository;
    private final ReservationRepository reservationRepository;
    private final ReservationManagerService reservationManagerService;

    public AccountController(AccountRepository accountRepository, ReservationRepository reservationRepository, ReservationManagerService reservationManagerService) {
        this.accountRepository = accountRepository;
        this.reservationRepository = reservationRepository;
        this.reservationManagerService = reservationManagerService;
    }

    // --- ACCOUNT ENDPOINTS ---

    @GetMapping("/accounts")
    public String viewAccounts(Model model) {
        model.addAttribute("accounts", accountRepository.findAll());
        return "accounts";
    }

    @GetMapping("/accounts/new")
    public String newAccountForm(Model model) {
        model.addAttribute("account", new Account());
        return "accountAdd"; // Fixed: Points to accountAdd.html
    }

    @PostMapping("/accounts/save")
    public String saveAccount(@ModelAttribute Account account) {
        if (accountRepository.existsById(account.getAccountNumber())) {
            reservationManagerService.updateAccount(account);
        } else {
            reservationManagerService.addAccount(account);
        }
        return "redirect:/accounts";
    }

    @GetMapping("/accounts/edit/{accountNumber}")
    public String editAccountForm(@PathVariable String accountNumber, Model model) {
        Account account = accountRepository.findById(accountNumber).orElseThrow();
        model.addAttribute("account", account);
        return "accountEdit"; // Fixed: Points to accountEdit.html
    }

    @GetMapping("/accounts/delete/{accountNumber}")
    public String deleteAccount(@PathVariable String accountNumber) {
        reservationManagerService.deleteAccount(accountNumber);
        return "redirect:/accounts";
    }

    // --- RESERVATION ENDPOINTS ---

    @GetMapping("/accounts/reservations/{accountNumber}")
    public String viewAccountDetails(@PathVariable String accountNumber, Model model) {
        Account account = accountRepository.findById(accountNumber).orElse(null);
        if (account != null) {
            model.addAttribute("reservations", account.getReservations());
            model.addAttribute("accountNumber", accountNumber);
        }
        return "reservations";
    }

    @GetMapping("/reservations/{reservationNumber}")
    public String viewReservationDetails(@PathVariable String reservationNumber, Model model) {
        Reservation reservation = reservationRepository.findById(reservationNumber).orElse(null);
        model.addAttribute("reservation", reservation);
        return "reservation"; // Resolves to reservation.html
    }

    // New Endpoint: Serve the Add Reservation form
    @GetMapping("/accounts/{accountNumber}/reservations/new")
    public String newReservationForm(@PathVariable String accountNumber, Model model) {
        // Using a concrete class (HotelReservation) because Reservation is abstract
        model.addAttribute("reservation", new HotelReservation());
        model.addAttribute("accountNumber", accountNumber);
        return "reservationAdd";
    }

    // New Endpoint: Process the Add Reservation form
    @PostMapping("/reservations/save")
    public String saveReservation(@ModelAttribute HotelReservation reservation, @RequestParam String accountNumber) {
        reservationManagerService.addDraftReservation(accountNumber, reservation);
        return "redirect:/accounts/reservations/" + accountNumber;
    }

    // New Endpoint: Serve the Edit Reservation form
    @GetMapping("/reservations/edit/{reservationNumber}")
    public String editReservationForm(@PathVariable String reservationNumber, Model model) {
        Reservation reservation = reservationRepository.findById(reservationNumber).orElseThrow();
        model.addAttribute("reservation", reservation);
        return "reservationEdit";
    }

    // New Endpoint: Process the Edit Reservation form
    @PostMapping("/reservations/update")
    public String updateReservation(@ModelAttribute HotelReservation reservation) {
        // Fetch existing to ensure account relationship isn't lost during update binding
        Reservation existing = reservationRepository.findById(reservation.getReservationNumber()).orElseThrow();
        reservation.setAccount(existing.getAccount());

        reservationManagerService.updateReservation(reservation);
        return "redirect:/accounts/reservations/" + reservation.getAccount().getAccountNumber();
    }

    @GetMapping("/reservations/delete/{reservationNumber}/{accountNumber}")
    public String deleteReservation(@PathVariable String reservationNumber, @PathVariable String accountNumber) {
        reservationManagerService.deleteReservation(reservationNumber);
        return "redirect:/accounts/reservations/" + accountNumber;
    }
}