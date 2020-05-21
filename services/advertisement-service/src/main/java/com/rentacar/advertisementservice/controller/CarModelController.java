package com.rentacar.advertisementservice.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentacar.advertisementservice.domain.dto.CarModelDTO;

@RestController
@RequestMapping("/carModels")
public class CarModelController {

	@PostMapping
    public void createCarModel(@RequestBody CarModelDTO companyDTO) {

    }

    @GetMapping("/{id}")
    public void getSingleCarModel(@PathVariable Long id) {

    }

    @GetMapping
    public void getAllCarModels() {

    }

    @PutMapping("/{id}")
    public void updateCarModel(@PathVariable Long id) {

    }

    @DeleteMapping("/{id}")
    public void deleteCarModel(@PathVariable Long id) {

    }
}
