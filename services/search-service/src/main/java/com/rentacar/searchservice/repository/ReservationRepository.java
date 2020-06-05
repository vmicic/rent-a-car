package com.rentacar.searchservice.repository;

import com.rentacar.searchservice.domain.Car;
import com.rentacar.searchservice.domain.Reservation;
import com.rentacar.searchservice.domain.ReservationState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
