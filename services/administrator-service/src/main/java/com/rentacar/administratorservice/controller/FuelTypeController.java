package com.rentacar.administratorservice.controller;

import com.rentacar.administratorservice.domain.FuelType;
import com.rentacar.administratorservice.service.FuelTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fuel-type")
public class FuelTypeController {

    private final FuelTypeService fuelTypeService;

    public FuelTypeController(FuelTypeService fuelTypeService) {
        this.fuelTypeService = fuelTypeService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    public ResponseEntity<?> createFuelType(@RequestBody FuelType fuelType) {
        FuelType fuelTypeCreated = this.fuelTypeService.create(fuelType);

        return new ResponseEntity<>(fuelTypeCreated, HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    public ResponseEntity<?> getAll() {
        List<FuelType> fuelType = this.fuelTypeService.findAll();

        return new ResponseEntity<>(fuelType, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        FuelType fuelType = this.fuelTypeService.findById(id);

        return new ResponseEntity<>(fuelType, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    public ResponseEntity<?> updateFuelType(@PathVariable Long id, @RequestBody FuelType fuelType) {
        this.fuelTypeService.update(id, fuelType);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    public ResponseEntity<?> deleteFuelType(@PathVariable Long id) {
        this.fuelTypeService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
