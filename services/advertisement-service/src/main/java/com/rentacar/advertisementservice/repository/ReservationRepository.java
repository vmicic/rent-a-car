package com.rentacar.advertisementservice.repository;

import com.rentacar.advertisementservice.domain.Car;
import com.rentacar.advertisementservice.domain.Reservation;
import com.rentacar.advertisementservice.domain.ReservationState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findAllByCarsContains(Car car);

    //check for manuel created reservations who's time overlaps with wanted reservation
    // check if reservationManual.start <= fromDate <= reservationManual.end or reservationManual.start <= toDate <= reservationManual.end
    // cars contains means to check only for reservation for specific car
    List<Reservation> CarsContainsAndFromDateLessThanEqualAndToDateGreaterThanEqualAndStateEqualsOrAndCarsContainsAndFromDateLessThanEqualAndToDateGreaterThanEqualAndStateEquals(Car car, LocalDateTime fromDate, LocalDateTime fromDateCopy, ReservationState reservationState, Car carCopy, LocalDateTime toDate, LocalDateTime toDateCopy, ReservationState reservationStateCopy);

    List<Reservation> CarsContainsAndFromDateGreaterThanEqualAndToDateLessThanEqualAndStateEquals(Car car, LocalDateTime fromDate, LocalDateTime toDate, ReservationState reservationState);
}
