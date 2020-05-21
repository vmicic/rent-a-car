package com.rentacar.advertisementservice.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentacar.advertisementservice.domain.dto.CarClassDTO;

@RestController
@RequestMapping("/carClasses")
public class CarClassController {
	
	@PostMapping
    public void createCarClass(@RequestBody CarClassDTO companyDTO) {

    }

    @GetMapping("/{id}")
    public void getSingleCarClass(@PathVariable Long id) {

    }

    @GetMapping
    public void getAllCarClasses() {

    }

    @PutMapping("/{id}")
    public void updateCarClass(@PathVariable Long id) {

    }

    @DeleteMapping("/{id}")
    public void deleteCarClass(@PathVariable Long id) {

    }
}
