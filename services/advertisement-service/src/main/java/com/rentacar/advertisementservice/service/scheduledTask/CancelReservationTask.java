package com.rentacar.advertisementservice.service.scheduledTask;


import com.rentacar.advertisementservice.service.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CancelReservationTask {

    private static final Integer HOURS_AFTER_CANCELING_RESERVATION = 24;

    private static final Logger logger = LoggerFactory.getLogger(CancelReservationTask.class);

    private final ReservationService reservationService;

    public CancelReservationTask(ReservationService reservationService) {
        this.reservationService = reservationService;
    }


    @Scheduled(fixedRate = 5000)
    public void deleteReservations() {
        LocalDateTime timeAfterDeletes = LocalDateTime.now().minusHours(HOURS_AFTER_CANCELING_RESERVATION);
        this.reservationService.cancelReservationOlderThen(timeAfterDeletes);
    }
}
