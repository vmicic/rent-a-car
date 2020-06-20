package com.rentacar.advertisementservice.service;

import com.rentacar.advertisementservice.domain.Reservation;
import com.rentacar.advertisementservice.domain.dto.RatingDTO;
import com.rentacar.advertisementservice.domain.dto.ReservationApprovedDTO;
import com.rentacar.advertisementservice.domain.dto.ReservationDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationService {

    Reservation createReservation(ReservationDTO reservationDTO);

    boolean reservationPossible(ReservationDTO reservationDTO);

    Reservation createApprovedReservation(ReservationApprovedDTO reservationApprovedDTO);

    boolean reservationApprovedPossible(ReservationApprovedDTO reservationApprovedDTO);

    void cancelReservation(Long id);

    Reservation findById(Long id);

    boolean exists(Long id);

    boolean loggedUserOwnerCar(Long id);

    void approveReservation(Long id);

    void cancelReservationOlderThen(LocalDateTime time);

    boolean canUsersExchangeMessages(String email);

    boolean existsForRating(RatingDTO ratingDTO);

    List<Reservation> getReservationsForApproval();

    List<Reservation> getAllRequestedReservations();
}
