package com.rentacar.agentbackend.controller;

import com.rentacar.agentbackend.domain.CarClass;
import com.rentacar.agentbackend.service.CarClassService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/class")
public class CarClassController {

    private final CarClassService carClassService;

    public CarClassController(CarClassService carClassService) {
        this.carClassService = carClassService;
    }


    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    public ResponseEntity<?> createClass(@RequestBody CarClass carClass) {
        CarClass carClassCreated = this.carClassService.create(carClass);

        return new ResponseEntity<>(carClassCreated, HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_AGENT','ROLE_COMPANY','ROLE_ADMINISTRATOR')")
    public ResponseEntity<?> getAll() {
        List<CarClass> carClasses = this.carClassService.findAll();

        return new ResponseEntity<>(carClasses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_AGENT','ROLE_COMPANY','ROLE_ADMINISTRATOR')")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        CarClass carClass = this.carClassService.findById(id);

        return new ResponseEntity<>(carClass, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    public ResponseEntity<?> updateCarClass(@PathVariable Long id, @RequestBody CarClass carClass) {
        this.carClassService.update(id, carClass);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    public ResponseEntity<?> deleteCarClass(@PathVariable Long id) {
        this.carClassService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
