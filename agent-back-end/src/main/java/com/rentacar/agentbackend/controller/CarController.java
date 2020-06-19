package com.rentacar.agentbackend.controller;

import com.rentacar.agentbackend.domain.Image;
import com.rentacar.agentbackend.domain.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.rentacar.agentbackend.domain.Car;
import com.rentacar.agentbackend.domain.dto.CarDTO;
import com.rentacar.agentbackend.service.CarService;
import com.rentacar.agentbackend.service.UserService;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

	private static Logger logger = LoggerFactory.getLogger(CarController.class);
	
	private final CarService carService;
	private final UserService userService;
	
	public CarController(CarService carService, UserService userService) {
		this.carService = carService;
		this.userService = userService;
	}

	@PostMapping
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_AGENT','ROLE_COMPANY')")
	public ResponseEntity<?> createCar(@RequestBody CarDTO carDTO, Principal user) {

		//TODO see if every entity with specified id exists

		User creator = userService.findOneByEmail(user.getName());
		
		Car car = carService.create(carDTO, creator);
		
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

    @GetMapping("/images/{id}")
	public ResponseEntity<?> getAllImagesForCar(@PathVariable Long id) {
		List<Image> images = carService.getAllImages(id);

		return new ResponseEntity<>(images, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id) {

    }

    @GetMapping("/user")
	public ResponseEntity<?> getCarsFromUser(Principal loggedInUser) {
    	User user = userService.findOneByEmail(loggedInUser.getName());
		List<Car> cars = this.carService.findAllByUser(user);

		return new ResponseEntity<>(cars, HttpStatus.OK);
	}
	
	@GetMapping
    public void getAllCars() {

    }
	
}
