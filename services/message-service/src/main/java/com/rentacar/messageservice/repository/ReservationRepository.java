package com.rentacar.messageservice.repository;

import com.rentacar.messageservice.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
