package com.rentacar.agentbackend.controller;

import com.rentacar.agentbackend.domain.Advertisement;
import com.rentacar.agentbackend.domain.User;
import com.rentacar.agentbackend.service.AdvertisementService;
import com.rentacar.agentbackend.service.PickupSpotService;
import com.rentacar.agentbackend.service.UserService;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentacar.agentbackend.domain.dto.AdvertisementDTO;

@RestController
@RequestMapping("/advertisement")
public class AdvertisementController {

    private static Logger logger = LoggerFactory.getLogger(AdvertisementController.class);
    private static final Integer NUMBER_OF_ADS_ALLOWED_USER = 3;

    private final PickupSpotService pickupSpotService;
    private final AdvertisementService advertisementService;
    private final UserService userService;

    public AdvertisementController(PickupSpotService pickupSpotService, AdvertisementService advertisementService, UserService userService) {
        this.pickupSpotService = pickupSpotService;
        this.advertisementService = advertisementService;
        this.userService = userService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_AGENT','ROLE_COMPANY')")
	public ResponseEntity<?> createAdvertisement(@RequestBody AdvertisementDTO advertisementDTO, Principal user) {
        //TODO Implement check if car for advertisement is from user, make sure that user has max 3 advertisements
    	
	    User creator = userService.findOneByEmail(user.getName());

        if(advertisementService.getNumberOfCreatedAdvertisements(creator) >= NUMBER_OF_ADS_ALLOWED_USER) {
            return new ResponseEntity<>("Cannot create more than " + NUMBER_OF_ADS_ALLOWED_USER + " ads", HttpStatus.BAD_REQUEST);
        }

	    for(Long id : advertisementDTO.getPickupSpots()) {
	        if(!pickupSpotService.exists(id)) {
	            return new ResponseEntity<>("Requested pickup spot doesn't exist", HttpStatus.BAD_REQUEST);
            }
        }

	    Advertisement advertisement = advertisementService.createAdvertisement(advertisementDTO, creator);

	    return new ResponseEntity<>(advertisement, HttpStatus.CREATED);
    }
	
	@DeleteMapping("/{id}")
    public void deleteAdvertisement(@PathVariable Long id) {

    }
	
	@GetMapping
    public void getAllAdvertisements() {

    }
	

}
