package com.rentacar.agentbackend.controller;

import com.rentacar.agentbackend.domain.Car;
import com.rentacar.agentbackend.domain.Reservation;
import com.rentacar.agentbackend.domain.ReservationState;
import com.rentacar.agentbackend.domain.User;
import com.rentacar.agentbackend.domain.dto.ReservationApprovedDTO;
import com.rentacar.agentbackend.domain.dto.ReservationDTO;
import com.rentacar.agentbackend.service.AdvertisementService;
import com.rentacar.agentbackend.service.CarService;
import com.rentacar.agentbackend.service.ReservationService;
import com.rentacar.agentbackend.service.UserService;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    private static final Logger logger = LoggerFactory.getLogger(ReservationController.class);

    private final ReservationService reservationService;
    private final AdvertisementService advertisementService;
    private final CarService carService;
    private final UserService userService;

    public ReservationController(ReservationService reservationService, AdvertisementService advertisementService, CarService carService,
    		UserService userService) {
        this.reservationService = reservationService;
        this.advertisementService = advertisementService;
        this.carService = carService;
        this.userService = userService;
    }


    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> createReservation(@RequestBody ReservationDTO reservationDTO, Principal user) {
        // TODO Implement basic checks check if date is valid(for advertisement),
        //  if every car is from same user
    	
    	User creator = userService.findOneByEmail(user.getName());

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

        Reservation reservation = this.reservationService.createReservation(reservationDTO, creator);

        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

    @PostMapping("/approved")
    @PreAuthorize("hasRole('ROLE_AGENT')")
    public ResponseEntity<?> createApprovedReservation(@RequestBody ReservationApprovedDTO reservationApprovedDTO, Principal user) {
        //TODO check if request for multiple cars from same owner
    	
    	User creator = userService.findOneByEmail(user.getName());

        if(reservationApprovedDTO.getFromDate().isAfter(reservationApprovedDTO.getToDate())) {
            return new ResponseEntity<>("Requested date invalid, start date cannot be before end date", HttpStatus.BAD_REQUEST);
        }

        if(!carService.exists(reservationApprovedDTO.getCarId())) {
            return new ResponseEntity<>("Requested car doesn't exist", HttpStatus.BAD_REQUEST);
        }

        Car car = carService.findById(reservationApprovedDTO.getCarId());

        if(!carService.loggedInUserOwner(car, creator)) {
            return new ResponseEntity<>("Cannot create reservation for car of other person", HttpStatus.BAD_REQUEST);
        }

        if(!reservationService.reservationApprovedPossible(reservationApprovedDTO)) {
            return new ResponseEntity<>("Could not create reservation at requested time", HttpStatus.BAD_REQUEST);
        }

        Reservation reservation = reservationService.createApprovedReservation(reservationApprovedDTO);

        return new ResponseEntity<>(reservation, HttpStatus.CREATED);
    }

    @PutMapping("/approve/{id}")
    public ResponseEntity<?> approveReservation(@PathVariable Long id, Principal user) {
    	
    	User logedInUser = userService.findOneByEmail(user.getName());
    	
        if(!this.reservationService.exists(id)) {
            return new ResponseEntity<>("Requested reservation doesn't exist", HttpStatus.BAD_REQUEST);
        }

        if(!this.reservationService.loggedUserOwnerCar(id, logedInUser)) {
            return new ResponseEntity<>("Logged user cannot approve requested reservation", HttpStatus.BAD_REQUEST);
        }

        if(!this.reservationService.findById(id).getState().equals(ReservationState.PENDING)) {
            return new ResponseEntity<>("Request reservation doesn't need to be approved", HttpStatus.BAD_REQUEST);
        }

        this.reservationService.approveReservation(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/messages/{id}")
    public ResponseEntity<?> canUsersExchangeMessages(@PathVariable Long id, Principal user) {
    	User loggedInUser = userService.findOneByEmail(user.getName());
    	
    	boolean canExchange = this.reservationService.canUsersExchangeMessages(id, loggedInUser);

    	return new ResponseEntity<>(canExchange, HttpStatus.OK);
    }
}
