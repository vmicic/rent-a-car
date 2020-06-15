package com.rentacar.advertisementservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.rentacar.advertisementservice.domain.Car;
import com.rentacar.advertisementservice.domain.dto.CarDTO;
import com.rentacar.advertisementservice.service.CarService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

	private static Logger logger = LoggerFactory.getLogger(CarController.class);
	
	private final CarService carService;
	
	public CarController(CarService carService) {
		this.carService = carService;
	}

	@PostMapping
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_AGENT','ROLE_COMPANY')")
	public ResponseEntity<?> createCar(@RequestBody CarDTO carDTO) {

		//TODO see if every entity with specified id exists

		Car car = carService.create(carDTO);
		
		if(car != null) {
			return new ResponseEntity<>(car, HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
    }

    @PostMapping("/uploadImage")
    public ResponseEntity<?> uploadImage(@RequestParam("imageFile") MultipartFile imageFile, @RequestParam("carId") Long id) throws IOException {
		logger.info("Trying to upload image");

		carService.saveImage(imageFile, id);

		return new ResponseEntity<>(HttpStatus.CREATED);
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

    @GetMapping("/user")
	public ResponseEntity<?> getCarsFromUser() {
		List<Car> cars = this.carService.findAllByUser();

		return new ResponseEntity<>(cars, HttpStatus.OK);
	}
	
	@GetMapping
    public void getAllCars() {

    }
	
}
