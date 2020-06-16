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


    //check for manuel created reservations who's time overlaps with wanted reservation
    // check if reservationManual.start <= fromDate <= reservationManual.end or reservationManual.start <= toDate <= reservationManual.end
    // cars contains means to check only for reservation for specific car
    boolean existsByCarsContainsAndFromDateLessThanEqualAndToDateGreaterThanEqualAndStateEqualsOrCarsContainsAndFromDateLessThanEqualAndToDateGreaterThanEqualAndStateEquals(Car car, LocalDateTime fromDate, LocalDateTime fromDateCopy, ReservationState reservationState, Car carCopy, LocalDateTime toDate, LocalDateTime toDateCopy, ReservationState reservationStateCopy);

    //check for reservations which is completely inside another reservation
    boolean existsByCarsContainsAndFromDateGreaterThanEqualAndToDateLessThanEqualAndStateEquals(Car car, LocalDateTime fromDate, LocalDateTime toDate, ReservationState reservationState);
}
