package com.rentacar.administratorservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/brand")
public class CarBrandController {

    private static Logger logger = LoggerFactory.getLogger(CarBrandController.class);

    @PostMapping("/new")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    public void createBrand() {
        logger.info("CREATE BRAND");
    }
}
