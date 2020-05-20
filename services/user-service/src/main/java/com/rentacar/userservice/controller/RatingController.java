package com.rentacar.userservice.controller;

import com.rentacar.userservice.dto.RatingDTO;
import org.springframework.web.bind.annotation.*;

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
