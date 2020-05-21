package com.rentacar.advertisementservice.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentacar.advertisementservice.domain.dto.CarBrandDTO;

@RestController
@RequestMapping("/carBrands")
public class CarBrandController {
	
	@PostMapping
    public void createCarBrand(@RequestBody CarBrandDTO companyDTO) {

    }

    @GetMapping("/{id}")
    public void getSingleBrand(@PathVariable Long id) {

    }

    @GetMapping
    public void getAllBrands() {

    }

    @PutMapping("/{id}")
    public void updateBrand(@PathVariable Long id) {

    }

    @DeleteMapping("/{id}")
    public void deleteBrand(@PathVariable Long id) {

    }
	
}
