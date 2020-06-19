package com.rentacar.agentbackend.controller;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentacar.agentbackend.service.CarService;
import com.rentacar.agentbackend.service.RatingService;
import com.rentacar.agentbackend.service.ReservationService;
import com.rentacar.agentbackend.service.UserService;
import com.rentacar.agentbackend.domain.User;
import com.rentacar.agentbackend.domain.dto.RatingDTO;

@RestController
@RequestMapping("/ratings")
public class RatingController {
	
	private final RatingService ratingService;
    private final ReservationService reservationService;
    private final CarService carService;
    private final UserService userService;

    public RatingController(RatingService ratingService, ReservationService reservationService, CarService carService, UserService userService) {
        this.ratingService = ratingService;
        this.reservationService = reservationService;
        this.carService = carService;
        this.userService = userService;
    }
    
    @PostMapping
    public ResponseEntity<?> createRating(@RequestBody RatingDTO ratingDTO, Principal user) {
    	//TODO Implement min and max rating
    	
    	User creator = userService.findOneByEmail(user.getName());

        if(!this.carService.exists(ratingDTO.getCarId())) {
            return new ResponseEntity<>("Requested car doesn't exist", HttpStatus.BAD_REQUEST);
        }

        if(!this.reservationService.existsForRating(ratingDTO, creator)) {
            return new ResponseEntity<>("You cannot rate selected car", HttpStatus.BAD_REQUEST);
        }

        if(this.carService.carAlreadyRated(ratingDTO.getCarId(), creator)) {
            return new ResponseEntity<>("You already rated requested car", HttpStatus.BAD_REQUEST);
        }

        this.ratingService.createRating(ratingDTO, creator);

        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteRating(@PathVariable Long id) {

    }

    @GetMapping
    public void getAllRatings() {

    }

    @PutMapping("/{id}")
    public void approveRating(@PathVariable Long id) {

    }

    @PostMapping("/reply/{id}")
    public void replyToRanking(@PathVariable Long id, @RequestBody String reply) {

    }

}
