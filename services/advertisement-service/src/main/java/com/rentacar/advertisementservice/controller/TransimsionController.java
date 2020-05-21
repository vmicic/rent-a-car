package com.rentacar.advertisementservice.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentacar.advertisementservice.domain.dto.TransmissionDTO;

@RestController
@RequestMapping("/transmissions")
public class TransimsionController {

	@PostMapping
    public void createTransimissions(@RequestBody TransmissionDTO companyDTO) {

    }

    @GetMapping("/{id}")
    public void getSingleTransimissions(@PathVariable Long id) {

    }

    @GetMapping
    public void getAllTransimissions() {

    }

    @PutMapping("/{id}")
    public void updateTransimissions(@PathVariable Long id) {

    }

    @DeleteMapping("/{id}")
    public void deleteTransimissions(@PathVariable Long id) {

    }
}
