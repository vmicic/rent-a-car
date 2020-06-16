package com.rentacar.searchservice.service;

import com.rentacar.searchservice.domain.Car;

import java.time.LocalDateTime;

public interface ReservationService {

    boolean carPossibleToReserveForDate(Car car, LocalDateTime dateFrom, LocalDateTime dateTo);
}
