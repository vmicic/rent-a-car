package com.rentacar.agentbackend.repository;

import com.rentacar.agentbackend.domain.Car;
import com.rentacar.agentbackend.domain.Reservation;
import com.rentacar.agentbackend.domain.ReservationState;
import com.rentacar.agentbackend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    //TODO refactor to exists where is possible

    List<Reservation> findAllByCarsContains(Car car);

    //check for manuel created reservations who's time overlaps with wanted reservation
    // check if reservationManual.start <= fromDate <= reservationManual.end or reservationManual.start <= toDate <= reservationManual.end
    // cars contains means to check only for reservation for specific car
    List<Reservation> CarsContainsAndFromDateLessThanEqualAndToDateGreaterThanEqualAndStateEqualsOrCarsContainsAndFromDateLessThanEqualAndToDateGreaterThanEqualAndStateEquals(Car car, LocalDateTime fromDate, LocalDateTime fromDateCopy, ReservationState reservationState, Car carCopy, LocalDateTime toDate, LocalDateTime toDateCopy, ReservationState reservationStateCopy);


    //check for reservations which is completely inside another reservation
    List<Reservation> CarsContainsAndFromDateGreaterThanEqualAndToDateLessThanEqualAndStateEquals(Car car, LocalDateTime fromDate, LocalDateTime toDate, ReservationState reservationState);

    //find all reservations before some time and with state pending
    List<Reservation> findAllByCreationDateTimeBeforeAndStateEquals(LocalDateTime time, ReservationState reservationState);

    //check if there is reserved reservation between users so they can exchange messages
    List<Reservation> findAllByUserEqualsAndUserOwnerCar_IdAndStateEqualsOrUser_IdAndUserOwnerCarEqualsAndStateEquals(User user, Long id, ReservationState reservationState, Long idCopy, User userCopy, ReservationState reservationStateCopy);

    //check if there is reservation with logged user, and car which he wants to rate, which is finished and paid
    boolean existsReservationByUserEqualsAndCarsContainsAndToDateBeforeAndStateEquals(User user, Car car, LocalDateTime time, ReservationState reservationState);

    //check for manuel created reservations who's time overlaps with wanted reservation
    // check if reservationManual.start <= fromDate <= reservationManual.end or reservationManual.start <= toDate <= reservationManual.end
    // cars contains means to check only for reservation for specific car
    boolean existsByCarsContainsAndFromDateLessThanEqualAndToDateGreaterThanEqualAndStateEqualsOrCarsContainsAndFromDateLessThanEqualAndToDateGreaterThanEqualAndStateEquals(Car car, LocalDateTime fromDate, LocalDateTime fromDateCopy, ReservationState reservationState, Car carCopy, LocalDateTime toDate, LocalDateTime toDateCopy, ReservationState reservationStateCopy);

    //check for reservations which is completely inside another reservation
    boolean existsByCarsContainsAndFromDateGreaterThanEqualAndToDateLessThanEqualAndStateEquals(Car car, LocalDateTime fromDate, LocalDateTime toDate, ReservationState reservationState);
}
