package com.rentacar.userservice.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentacar.userservice.domain.dto.RatingDTO;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @PostMapping
    public void createRating(@RequestBody RatingDTO ratingDTO) {

    }

    @DeleteMapping("/{id}")
    public void deleteRating(@PathVariable Long id) {

    }

    @GetMapping
    public void getAllRatings() {

    }

    @PutMapping("/{id}")
    public void approveRating(@PathVariable Long id) {

    }

    @PostMapping("/reply/{id}")
    public void replyToRanking(@PathVariable Long id, @RequestBody String reply) {

    }

}
