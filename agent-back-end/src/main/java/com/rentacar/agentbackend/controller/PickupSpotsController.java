package com.rentacar.agentbackend.controller;

import com.rentacar.agentbackend.service.PickupSpotService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pickups")
public class PickupSpotsController {

    private final PickupSpotService pickupSpotService;

    public PickupSpotsController(PickupSpotService pickupSpotService) {
        this.pickupSpotService = pickupSpotService;
    }

    @GetMapping
    public ResponseEntity<?> getAllPickups() {
        return new ResponseEntity<>(pickupSpotService.getAll(), HttpStatus.OK);
    }
}
