package com.rentacar.agentbackend.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentacar.agentbackend.domain.dto.DiscountDTO;

@RestController
@RequestMapping("/discounts")
public class DiscountController {
	
	@PostMapping
	public void createDiscount(@RequestBody DiscountDTO ratingDTO) {

    }
	
	@DeleteMapping("/{id}")
    public void deleteDiscount(@PathVariable Long id) {

    }
	
	@GetMapping
    public void getAllDiscounts() {

    }
	
}
