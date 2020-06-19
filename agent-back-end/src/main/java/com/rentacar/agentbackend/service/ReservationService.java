package com.rentacar.agentbackend.service;

import com.rentacar.agentbackend.domain.Reservation;
import com.rentacar.agentbackend.domain.User;
import com.rentacar.agentbackend.domain.dto.RatingDTO;
import com.rentacar.agentbackend.domain.dto.ReservationApprovedDTO;
import com.rentacar.agentbackend.domain.dto.ReservationDTO;
import com.rentacar.agentbackend.domain.Car;

import java.time.LocalDateTime;

public interface ReservationService {

    Reservation createReservation(ReservationDTO reservationDTO, User creator);

    boolean reservationPossible(ReservationDTO reservationDTO);

    Reservation createApprovedReservation(ReservationApprovedDTO reservationApprovedDTO);

    boolean reservationApprovedPossible(ReservationApprovedDTO reservationApprovedDTO);

    void cancelReservation(Long id);

    Reservation findById(Long id);

    boolean exists(Long id);

    boolean loggedUserOwnerCar(Long id, User loggedInUser);

    void approveReservation(Long id);

    void cancelReservationOlderThen(LocalDateTime time);

    boolean canUsersExchangeMessages(Long id, User loggedInUser);

    boolean existsForRating(RatingDTO ratingDTO, User loggedInUser);
    
    boolean carPossibleToReserveForDate(Car car, LocalDateTime dateFrom, LocalDateTime dateTo);
}
