package com.rentacar.advertisementservice.controller;

import com.rentacar.advertisementservice.domain.Car;
import com.rentacar.advertisementservice.domain.Reservation;
import com.rentacar.advertisementservice.domain.ReservationState;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    private static final Logger logger = LoggerFactory.getLogger(ReservationController.class);

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

        logger.info(reservationDTO.toString());

        if(reservationDTO.getFromDate().isAfter(reservationDTO.getToDate())) {
            return new ResponseEntity<>("Requested date invalid, start date cannot be before end date", HttpStatus.BAD_REQUEST);
        }

        if(!this.advertisementService.advertisementForCarAndDateExists(reservationDTO)) {
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
        //TODO check if request for multiple cars from same owner

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

    @PutMapping("/approve/{id}")
    public ResponseEntity<?> approveReservation(@PathVariable Long id) {
        if(!this.reservationService.exists(id)) {
            return new ResponseEntity<>("Requested reservation doesn't exist", HttpStatus.BAD_REQUEST);
        }

        if(!this.reservationService.loggedUserOwnerCar(id)) {
            return new ResponseEntity<>("Logged user cannot approve requested reservation", HttpStatus.BAD_REQUEST);
        }

        if(!this.reservationService.findById(id).getState().equals(ReservationState.PENDING)) {
            return new ResponseEntity<>("Request reservation doesn't need to be approved", HttpStatus.BAD_REQUEST);
        }

        this.reservationService.approveReservation(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/reject/{id}")
    public ResponseEntity<?> rejectReservation(@PathVariable Long id) {
        if(!this.reservationService.exists(id)) {
            return new ResponseEntity<>("Requested reservation doesn't exist", HttpStatus.BAD_REQUEST);
        }

        if(!this.reservationService.loggedUserOwnerCar(id)) {
            return new ResponseEntity<>("Logged user cannot approve requested reservation", HttpStatus.BAD_REQUEST);
        }

        if(!this.reservationService.findById(id).getState().equals(ReservationState.PENDING)) {
            return new ResponseEntity<>("Request reservation doesn't need to be approved", HttpStatus.BAD_REQUEST);
        }

        this.reservationService.rejectReservation(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/messages/{id}")
    public ResponseEntity<?> canUsersExchangeMessages(@PathVariable Long id) {
       boolean canExchange = this.reservationService.canUsersExchangeMessages(id);

       return new ResponseEntity<>(canExchange, HttpStatus.OK);
    }

    @GetMapping("/approval")
    public ResponseEntity<?> getReservationForApproval() {
        List<Reservation> reservations = this.reservationService.getReservationsForApproval();

        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }
}
