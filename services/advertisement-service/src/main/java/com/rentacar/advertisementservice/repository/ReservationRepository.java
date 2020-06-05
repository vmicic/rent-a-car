package com.rentacar.advertisementservice.repository;

import com.rentacar.advertisementservice.domain.Car;
import com.rentacar.advertisementservice.domain.Reservation;
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
    // advertisement is null means reservation is manually created and cars contains means to check only for reservation for specific car
    List<Reservation> findAllByAdvertisementNullAndCarsContainsAndFromDateLessThanEqualAndToDateGreaterThanEqualOrAdvertisementNullAndCarsContainsAndFromDateLessThanEqualAndToDateGreaterThanEqual(Car car, LocalDateTime fromDate, LocalDateTime fromDateCopy, Car carCopy, LocalDateTime toDate, LocalDateTime toDateCopy);

    List<Reservation> findAllByAdvertisementNullAndCarsContainsAndFromDateGreaterThanEqualAndToDateLessThanEqual(Car car, LocalDateTime fromDate, LocalDateTime toDate);

    List<Reservation> findCarsContainsAndFromDateLessThanEqualAndToDateGreaterThanEqualAndCarsContainsAndFromDateLessThanEqualAndToDateGreaterThanEqual(Car car, LocalDateTime fromDate, LocalDateTime fromDateCopy, Car carCopy, LocalDateTime toDate, LocalDateTime toDateCopy);
}
