package com.example.AccomodationReservationManagementMvc.controller;

import com.example.AccomodationReservationManagementMvc.dto.ReservationForm;
import com.example.AccomodationReservationManagementMvc.model.*;
import com.example.AccomodationReservationManagementMvc.repository.AccountRepository;
import com.example.AccomodationReservationManagementMvc.repository.ReservationRepository;
import com.example.AccomodationReservationManagementMvc.service.ReservationManagerService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String saveAccount(@Valid @ModelAttribute Account account, BindingResult bindingResult) {

        if (accountRepository.existsById(account.getAccountNumber())) {

            if (bindingResult.hasErrors()) {
                return "accountEdit";
            }

            reservationManagerService.updateAccount(account);
        } else {

            if (bindingResult.hasErrors()) {
                return "accountAdd";
            }

            String accountNumber;
            accountNumber = 'A' + account.getAccountNumber().substring(1);
            account.setAccountNumber(accountNumber);
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
        model.addAttribute("reservation", new ReservationForm());
        model.addAttribute("accountNumber", accountNumber);
        return "reservationAdd";
    }
    // New Endpoint: Process the Add Reservation form
    @PostMapping("/reservations/save")
    public String saveReservation(@Valid @ModelAttribute("reservation") ReservationForm form,
                                  BindingResult bindingResult,
                                  @RequestParam String accountNumber,
                                  Model model) {

        if (bindingResult.hasErrors()) {
            // Re-add accountNumber so the header, hidden input, and cancel button still work
            model.addAttribute("accountNumber", accountNumber);
            return "reservationAdd";
        }

        Reservation reservation;
        String reservationNumber;

        switch (form.getReservationType()) {
            case "CABIN" -> {
                CabinReservation cabin = new CabinReservation();
                cabin.setFullKitchen(form.getFullKitchen());
                cabin.setLoft(form.getLoft());
                reservation = cabin;
                reservationNumber = 'C' + form.getReservationNumber().substring(1);
            }
            case "HOUSE" -> {
                HouseReservation house = new HouseReservation();
                house.setNumberOfFloors(form.getNumberOfFloors());
                reservation = house;
                reservationNumber = 'O' + form.getReservationNumber().substring(1);
            }
            default -> {
                HotelReservation hotel = new HotelReservation();
                hotel.setKitchenette(form.getKitchenette());
                reservation = hotel;
                reservationNumber = 'H' + form.getReservationNumber().substring(1);
            }
        }

        // Copy common fields
        reservation.setReservationNumber(reservationNumber);
        reservation.setStartDate(form.getStartDate());
        reservation.setNumberOfNights(form.getNumberOfNights());
        reservation.setLodgingSizeSqFt(form.getLodgingSizeSqFt());
        reservation.setNumberOfBeds(form.getNumberOfBeds());
        reservation.setNumberOfBedrooms(form.getNumberOfBedrooms());
        reservation.setNumberOfBathrooms(form.getNumberOfBathrooms());
        reservation.setPhysicalAddress(form.getPhysicalAddress());
        reservation.setMailingAddress(form.getMailingAddress());

        reservationManagerService.addDraftReservation(accountNumber, reservation);
        return "redirect:/accounts/reservations/" + accountNumber;
    }
    @GetMapping("/reservations/edit/{reservationNumber}")
    public String editReservationForm(@PathVariable String reservationNumber, Model model) {
        Reservation existing = reservationRepository.findById(reservationNumber).orElseThrow();

        ReservationForm form = new ReservationForm();
        form.setAccountNumber(existing.getAccount().getAccountNumber());
        form.setReservationNumber(existing.getReservationNumber());
        form.setReservationType(existing.getReservationType());
        form.setStatus(existing.getStatus());
        form.setStartDate(existing.getStartDate());
        form.setNumberOfNights(existing.getNumberOfNights());
        form.setLodgingSizeSqFt(existing.getLodgingSizeSqFt());
        form.setNumberOfBeds(existing.getNumberOfBeds());
        form.setNumberOfBedrooms(existing.getNumberOfBedrooms());
        form.setNumberOfBathrooms(existing.getNumberOfBathrooms());
        form.setPhysicalAddress(existing.getPhysicalAddress());
        form.setMailingAddress(existing.getMailingAddress());

        if (existing instanceof CabinReservation cabin) {
            form.setFullKitchen(cabin.getFullKitchen());
            form.setLoft(cabin.getLoft());
        } else if (existing instanceof HouseReservation house) {
            form.setNumberOfFloors(house.getNumberOfFloors());
        } else if (existing instanceof HotelReservation hotel) {
            form.setKitchenette(hotel.getKitchenette());
        }

        model.addAttribute("reservation", form);
        return "reservationEdit";
    }

    @PostMapping("/reservations/update")
    public String updateReservation(@ModelAttribute ReservationForm form) {
        Reservation existing = reservationRepository.findById(form.getReservationNumber()).orElseThrow();

        // Update fields directly on the existing managed entity (or recreate if type changed)
        if (existing instanceof CabinReservation cabin) {
            cabin.setFullKitchen(form.getFullKitchen());
            cabin.setLoft(form.getLoft());
        } else if (existing instanceof HouseReservation house) {
            house.setNumberOfFloors(form.getNumberOfFloors());
        } else if (existing instanceof HotelReservation hotel) {
            hotel.setKitchenette(form.getKitchenette());
        }

        existing.setStatus(form.getStatus());
        existing.setStartDate(form.getStartDate());
        existing.setNumberOfNights(form.getNumberOfNights());
        existing.setLodgingSizeSqFt(form.getLodgingSizeSqFt());
        existing.setNumberOfBeds(form.getNumberOfBeds());
        existing.setNumberOfBedrooms(form.getNumberOfBedrooms());
        existing.setNumberOfBathrooms(form.getNumberOfBathrooms());
        existing.setPhysicalAddress(form.getPhysicalAddress());
        existing.setMailingAddress(form.getMailingAddress());

        reservationManagerService.updateReservation(existing);
        return "redirect:/accounts/reservations/" + existing.getAccount().getAccountNumber();
    }

    @GetMapping("/reservations/delete/{reservationNumber}/{accountNumber}")
    public String deleteReservation(@PathVariable String reservationNumber, @PathVariable String accountNumber) {
        reservationManagerService.deleteReservation(reservationNumber);
        return "redirect:/accounts/reservations/" + accountNumber;
    }
}