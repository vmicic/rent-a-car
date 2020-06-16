package com.rentacar.agentbackend.controller.admin;

import com.rentacar.agentbackend.domain.CarBrand;
import com.rentacar.agentbackend.service.admin.CarBrandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brand")
public class CarBrandController {

    private static Logger logger = LoggerFactory.getLogger(CarBrandController.class);

    private final CarBrandService carBrandService;

    public CarBrandController(CarBrandService carBrandService) {
        this.carBrandService = carBrandService;
    }


    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    public ResponseEntity<?> createBrand(@RequestBody CarBrand carBrand) {
        CarBrand carBrandCreated = this.carBrandService.create(carBrand);

        return new ResponseEntity<>(carBrandCreated, HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_AGENT','ROLE_COMPANY','ROLE_ADMINISTRATOR')")
    public ResponseEntity<?> getAll() {
        List<CarBrand> carBrands = this.carBrandService.findAll();

        return new ResponseEntity<>(carBrands, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_AGENT','ROLE_COMPANY','ROLE_ADMINISTRATOR')")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        CarBrand carBrand = this.carBrandService.findById(id);

        return new ResponseEntity<>(carBrand, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    public ResponseEntity<?> updateCarBrand(@PathVariable Long id, @RequestBody CarBrand carBrand) {
        this.carBrandService.update(id, carBrand);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    public ResponseEntity<?> deleteCarBrand(@PathVariable Long id) {
        this.carBrandService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
