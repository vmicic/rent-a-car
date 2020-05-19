package com.rentacar.userservice.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @PostMapping
    public void createRating() {

    }

    @DeleteMapping("/{id}")
    public void deleteRating(@PathVariable Long id) {

    }

    @GetMapping
    public void getAllRatings() {

    }

}
