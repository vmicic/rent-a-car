package com.rentacar.searchservice.service.impl;

import com.rentacar.searchservice.domain.Car;
import com.rentacar.searchservice.domain.ReservationState;
import com.rentacar.searchservice.repository.ReservationRepository;
import com.rentacar.searchservice.service.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReservationServiceImpl implements ReservationService {

    private static final Logger logger = LoggerFactory.getLogger(ReservationServiceImpl.class);

    private final ReservationRepository reservationRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public boolean carPossibleToReserveForDate(Car car, LocalDateTime dateFrom, LocalDateTime dateTo) {
        boolean overlaps = this.reservationRepository.existsByCarsContainsAndFromDateLessThanEqualAndToDateGreaterThanEqualAndStateEqualsOrCarsContainsAndFromDateLessThanEqualAndToDateGreaterThanEqualAndStateEquals(
                car, dateFrom, dateFrom, ReservationState.PAID, car, dateTo, dateTo, ReservationState.PAID);

        boolean inside = this.reservationRepository.existsByCarsContainsAndFromDateGreaterThanEqualAndToDateLessThanEqualAndStateEquals(car, dateFrom, dateTo, ReservationState.PAID);

        logger.info("Car id: " + car.getId() + ", overlaps: " + overlaps + ", inside: " + inside);

        return !(overlaps || inside);
    }
}
