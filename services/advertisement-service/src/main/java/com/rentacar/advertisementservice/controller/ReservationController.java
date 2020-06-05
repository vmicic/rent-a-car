package com.rentacar.advertisementservice.controller;

import com.rentacar.advertisementservice.domain.Car;
import com.rentacar.advertisementservice.domain.Reservation;
import com.rentacar.advertisementservice.domain.dto.ReservationApprovedDTO;
import com.rentacar.advertisementservice.domain.dto.ReservationDTO;
import com.rentacar.advertisementservice.service.AdvertisementService;
import com.rentacar.advertisementservice.service.CarService;
import com.rentacar.advertisementservice.service.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private final CarService carService;

    public ReservationController(ReservationService reservationService, AdvertisementService advertisementService, CarService carService) {
        this.reservationService = reservationService;
        this.advertisementService = advertisementService;
        this.carService = carService;
    }


    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> createReservation(@RequestBody ReservationDTO reservationDTO) {
        // TODO Implement basic checks check if date is valid(for advertisement),
        //  if every car is from same user

        if(reservationDTO.getFromDate().isAfter(reservationDTO.getToDate())) {
            return new ResponseEntity<>("Requested date invalid, start date cannot be before end date", HttpStatus.BAD_REQUEST);
        }

        if(!this.advertisementService.exists(reservationDTO.getAdvertisementId())) {
            return new ResponseEntity<>("Specified advertisement doesn't exist", HttpStatus.BAD_REQUEST);
        }

        if(!this.reservationService.reservationPossible(reservationDTO)) {
            return new ResponseEntity<>("Reservation is not possible in requested time", HttpStatus.BAD_REQUEST);
        }

        for(Long id : reservationDTO.getCarIds()) {
            if(!carService.exists(id)) {
                return new ResponseEntity<>("Requested car doesn't exist", HttpStatus.BAD_REQUEST);
            }
        }

        Reservation reservation = this.reservationService.createReservation(reservationDTO);

        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

    @PostMapping("/approved")
    public ResponseEntity<?> createApprovedReservation(@RequestBody ReservationApprovedDTO reservationApprovedDTO) {

        if(reservationApprovedDTO.getFromDate().isAfter(reservationApprovedDTO.getToDate())) {
            return new ResponseEntity<>("Requested date invalid, start date cannot be before end date", HttpStatus.BAD_REQUEST);
        }

        if(!carService.exists(reservationApprovedDTO.getCarId())) {
            return new ResponseEntity<>("Requested car doesn't exist", HttpStatus.BAD_REQUEST);
        }

        Car car = carService.findById(reservationApprovedDTO.getCarId());

        if(!carService.loggedInUserOwner(car)) {
            return new ResponseEntity<>("Cannot create reservation for car of other person", HttpStatus.BAD_REQUEST);
        }

        if(!reservationService.reservationApprovedPossible(reservationApprovedDTO)) {
            return new ResponseEntity<>("Could not create reservation at requested time", HttpStatus.BAD_REQUEST);
        }

        Reservation reservation = reservationService.createApprovedReservation(reservationApprovedDTO);

        return new ResponseEntity<>(reservation, HttpStatus.CREATED);
    }
}
