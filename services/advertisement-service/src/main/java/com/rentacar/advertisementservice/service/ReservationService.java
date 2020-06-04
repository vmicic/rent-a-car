package com.rentacar.advertisementservice.service;

import com.rentacar.advertisementservice.domain.Reservation;
import com.rentacar.advertisementservice.domain.dto.ReservationDTO;

public interface ReservationService {

    Reservation createReservation(ReservationDTO reservationDTO);
}
