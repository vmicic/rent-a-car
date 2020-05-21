package com.rentacar.userservice.service;

import com.rentacar.userservice.domain.Rating;
import com.rentacar.userservice.domain.dto.RatingDTO;

import java.util.List;

public interface RatingService {

    Rating createRating(RatingDTO ratingDTO);

    void deleteRating(Long id);

    List<Rating> getAllRatings();

    void approveRating(Long id);
}
