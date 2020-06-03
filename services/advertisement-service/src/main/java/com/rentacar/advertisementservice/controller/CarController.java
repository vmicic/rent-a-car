package com.rentacar.advertisementservice.controller;

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

import com.rentacar.advertisementservice.domain.Car;
import com.rentacar.advertisementservice.domain.dto.CarDTO;
import com.rentacar.advertisementservice.service.CarService;

@RestController
@RequestMapping("/cars")
public class CarController {
	
	private final CarService carService;
	
	public CarController(CarService carService) {
		this.carService = carService;
	}

	@PostMapping
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_AGENT','ROLE_COMPANY','ROLE_ADMINISTRATOR')")
	public ResponseEntity<?> createCar(@RequestBody CarDTO carDTO) {
		
		Car car = carService.create(carDTO);
		
		if(car != null) {
			return new ResponseEntity<>(car, HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
    }
	
	@GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_AGENT','ROLE_COMPANY','ROLE_ADMINISTRATOR')")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        Car car = this.carService.findById(id);

        return new ResponseEntity<>(car, HttpStatus.OK);
    }
	
	@DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id) {

    }
	
	@GetMapping
    public void getAllCars() {

    }
	
}
