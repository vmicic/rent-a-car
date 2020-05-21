package com.rentacar.advertisementservice.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentacar.advertisementservice.domain.dto.FuelTypeDTO;

@RestController
@RequestMapping("/fuelTypes")
public class FuelTypeController {

	@PostMapping
    public void createFuelType(@RequestBody FuelTypeDTO companyDTO) {

    }

    @GetMapping("/{id}")
    public void getSingleFuelType(@PathVariable Long id) {

    }

    @GetMapping
    public void getAllFuelType() {

    }

    @PutMapping("/{id}")
    public void updateFuelType(@PathVariable Long id) {

    }

    @DeleteMapping("/{id}")
    public void deleteFuelType(@PathVariable Long id) {

    }
}
