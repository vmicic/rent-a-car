package com.rentacar.advertisementservice.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentacar.advertisementservice.domain.dto.PriceListDTO;

@RestController
@RequestMapping("/priceLists")
public class PriceListController {
	@PostMapping
	public void createPriceList(@RequestBody PriceListDTO ratingDTO) {

    }
	
	@DeleteMapping("/{id}")
    public void deletePriceList(@PathVariable Long id) {

    }
	
	@GetMapping
    public void getAllPriceLists() {

    }
	
}
