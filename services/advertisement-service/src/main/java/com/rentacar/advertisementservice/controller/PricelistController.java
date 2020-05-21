package com.rentacar.advertisementservice.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.rentacar.advertisementservice.domain.dto.PricelistDTO;

public class PricelistController {
	@PostMapping
	public void createAdvertisement(@RequestBody PricelistDTO ratingDTO) {

    }
	
	@DeleteMapping("/{id}")
    public void deleteAdvertisement(@PathVariable Long id) {

    }
	
	@GetMapping
    public void getAllAdvertisements() {

    }
	
}
