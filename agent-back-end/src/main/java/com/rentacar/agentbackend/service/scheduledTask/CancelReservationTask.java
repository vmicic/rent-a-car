package com.rentacar.agentbackend.service.scheduledTask;


import com.rentacar.agentbackend.service.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import javax.annotation.PreDestroy;

@Component
public class CancelReservationTask {
	//Functionality moved to the ReservationServiceImpl class
	/*
    private static final Integer HOURS_AFTER_CANCELING_RESERVATION = 24;
    private boolean shuttingDown = false;

    private static final Logger logger = LoggerFactory.getLogger(CancelReservationTask.class);

    private final ReservationService reservationService;

    public CancelReservationTask(ReservationService reservationService) {
        this.reservationService = reservationService;
    }
    
    public void shutdown() {
    	shuttingDown = true;
    }

    //@Scheduled(fixedDelay = 5000)
    public void deleteReservations() {
        LocalDateTime timeAfterDeletes = LocalDateTime.now().minusHours(HOURS_AFTER_CANCELING_RESERVATION);
        if(!shuttingDown) {
	        reservationService.cancelReservationOlderThen(timeAfterDeletes);
        }
    }
    
    @PreDestroy
    public void preDestroy() {
    	shutdown();
    }
    */
}
