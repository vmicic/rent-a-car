package com.rentacar.advertisementservice.controller;

import com.rentacar.advertisementservice.domain.Rating;
import com.rentacar.advertisementservice.domain.dto.RatingDTO;
import com.rentacar.advertisementservice.service.CarService;
import com.rentacar.advertisementservice.service.RatingService;
import com.rentacar.advertisementservice.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    public ResponseEntity<?> getRatingsForApproval() {
        List<Rating> ratings = this.ratingService.getAllForApproval();

        return new ResponseEntity<>(ratings, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createRating(@RequestBody RatingDTO ratingDTO) {
        //TODO Implement min and max rating

        if(!this.carService.exists(ratingDTO.getCarId())) {
            return new ResponseEntity<>("Requested car doesn't exist", HttpStatus.BAD_REQUEST);
        }

        //this method check if there is reservation which is paid and was before so user can rate it
        if(!this.reservationService.existsForRating(ratingDTO)) {
            return new ResponseEntity<>("You cannot rate selected car", HttpStatus.BAD_REQUEST);
        }

        if(this.carService.carAlreadyRated(ratingDTO.getCarId())) {
            return new ResponseEntity<>("You already rated requested car", HttpStatus.BAD_REQUEST);
        }

        this.ratingService.createRating(ratingDTO);

        return null;
    }

    @PutMapping("/approve/{id}")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    public ResponseEntity<?> approveRating(@PathVariable Long id) {
        if(!this.ratingService.existById(id)) {
            return new ResponseEntity<>("Requested rating doesn't exist", HttpStatus.BAD_REQUEST);
        }

        this.ratingService.approveRating(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/reject/{id}")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    public ResponseEntity<?> rejectRating(@PathVariable Long id) {
        if(!this.ratingService.existById(id)) {
            return new ResponseEntity<>("Requested rating doesn't exist", HttpStatus.BAD_REQUEST);
        }

        this.ratingService.rejectRating(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
