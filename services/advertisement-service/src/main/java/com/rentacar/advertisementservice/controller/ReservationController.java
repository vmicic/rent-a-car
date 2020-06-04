package com.rentacar.advertisementservice.controller;

import com.rentacar.advertisementservice.domain.Reservation;
import com.rentacar.advertisementservice.domain.dto.ReservationDTO;
import com.rentacar.advertisementservice.service.AdvertisementService;
import com.rentacar.advertisementservice.service.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    private static Logger logger = LoggerFactory.getLogger(ReservationController.class);

    private final ReservationService reservationService;
    private final AdvertisementService advertisementService;

    public ReservationController(ReservationService reservationService, AdvertisementService advertisementService) {
        this.reservationService = reservationService;
        this.advertisementService = advertisementService;
    }


    @PostMapping
    public ResponseEntity<?> createReservation(@RequestBody ReservationDTO reservationDTO) {
        // TODO Implement basic checks check if date is valid(for advertisement and if end date is after start date,
        //  if advertisement exists, if car exists, if every car is from same user

        if(!this.advertisementService.exists(reservationDTO.getAdvertisementId())) {
            return new ResponseEntity<>("Specified advertisement doesn't exist", HttpStatus.BAD_REQUEST);
        }

        Reservation reservation = this.reservationService.createReservation(reservationDTO);

        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }
}
