package com.example.AccomodationReservationManagementMvc.repository;
import com.example.AccomodationReservationManagementMvc.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, String> {
    @Query("SELECT r.status FROM Reservation r WHERE r.reservationNumber = :reservationNumber")
    String findStatusByReservationNumber(@Param("reservationNumber") String reservationNumber);
}
