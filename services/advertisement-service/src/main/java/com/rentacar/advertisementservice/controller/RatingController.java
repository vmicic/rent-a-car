package com.rentacar.advertisementservice.controller;

import com.rentacar.advertisementservice.domain.dto.RatingDTO;
import com.rentacar.advertisementservice.service.CarService;
import com.rentacar.advertisementservice.service.RatingService;
import com.rentacar.advertisementservice.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    private final RatingService ratingService;
    private final ReservationService reservationService;
    private final CarService carService;

    public RatingController(RatingService ratingService, ReservationService reservationService, CarService carService) {
        this.ratingService = ratingService;
        this.reservationService = reservationService;
        this.carService = carService;
    }

    @PostMapping
    public ResponseEntity<?> createRating(@RequestBody RatingDTO ratingDTO) {
        //TODO Implement min and max rating

        if(!this.carService.exists(ratingDTO.getCarId())) {
            return new ResponseEntity<>("Requested car doesn't exist", HttpStatus.BAD_REQUEST);
        }

        if(!this.reservationService.existsForRating(ratingDTO)) {
            return new ResponseEntity<>("You cannot rate selected car", HttpStatus.BAD_REQUEST);
        }

        if(this.carService.carAlreadyRated(ratingDTO.getCarId())) {
            return new ResponseEntity<>("You already rated requested car", HttpStatus.BAD_REQUEST);
        }

        this.ratingService.createRating(ratingDTO);

        return null;
    }
}
