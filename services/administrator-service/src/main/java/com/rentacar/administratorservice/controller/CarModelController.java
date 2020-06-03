package com.rentacar.administratorservice.controller;

import com.rentacar.administratorservice.domain.CarModel;
import com.rentacar.administratorservice.domain.dto.CarModelDTO;
import com.rentacar.administratorservice.service.CarModelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/model")
public class CarModelController {

    private final CarModelService carModelService;

    public CarModelController(CarModelService carModelService) {
        this.carModelService = carModelService;
    }

    @PostMapping
    public ResponseEntity<?> createCarModel(@RequestBody CarModelDTO carModelDTO) {

        CarModel carModel = this.carModelService.create(carModelDTO);

        return new ResponseEntity<>(carModel, HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_AGENT','ROLE_COMPANY','ROLE_ADMINISTRATOR')")
    public ResponseEntity<?> getAll() {
        List<CarModel> carModels = this.carModelService.findAll();

        return new ResponseEntity<>(carModels, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_AGENT','ROLE_COMPANY','ROLE_ADMINISTRATOR')")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        CarModel carModel = this.carModelService.findById(id);

        return new ResponseEntity<>(carModel, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    public ResponseEntity<?> updateCarModel(@PathVariable Long id, @RequestBody CarModel carModel) {
        this.carModelService.update(id, carModel);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    public ResponseEntity<?> deleteCarModel(@PathVariable Long id) {
        this.carModelService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
