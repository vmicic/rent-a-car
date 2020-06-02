package com.rentacar.administratorservice.controller;


import com.rentacar.administratorservice.domain.TransmissionType;
import com.rentacar.administratorservice.service.TransmissionTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transmission-type")
public class TransmissionTypeController {

    private final TransmissionTypeService transmissionTypeService;


    public TransmissionTypeController(TransmissionTypeService transmissionTypeService) {
        this.transmissionTypeService = transmissionTypeService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    public ResponseEntity<?> createTransmissionType(@RequestBody TransmissionType transmissionType) {
        TransmissionType carClassCreated = this.transmissionTypeService.create(transmissionType);

        return new ResponseEntity<>(carClassCreated, HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    public ResponseEntity<?> getAll() {
        List<TransmissionType> transmissionTypes = this.transmissionTypeService.findAll();

        return new ResponseEntity<>(transmissionTypes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        TransmissionType transmissionType = this.transmissionTypeService.findById(id);

        return new ResponseEntity<>(transmissionType, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    public ResponseEntity<?> updateTransmissionType(@PathVariable Long id, @RequestBody TransmissionType transmissionType) {
        this.transmissionTypeService.update(id, transmissionType);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    public ResponseEntity<?> deleteTransmissionType(@PathVariable Long id) {
        this.transmissionTypeService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
