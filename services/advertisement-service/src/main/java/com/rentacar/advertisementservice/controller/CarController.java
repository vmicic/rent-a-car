package com.rentacar.advertisementservice.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentacar.advertisementservice.domain.dto.CarDTO;

@RestController
@RequestMapping("/cars")
public class CarController {

	@PostMapping
	public void createCar(@RequestBody CarDTO ratingDTO) {

    }
	
	@DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id) {

    }
	
	@GetMapping
    public void getAllCars() {

    }
	
}
