package com.example.AccomodationReservationManagementMvc.repository;
import com.example.AccomodationReservationManagementMvc.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    @Query("SELECT a FROM Account a LEFT JOIN FETCH a.reservations")
    List<Account> findAllWithReservations();

}
