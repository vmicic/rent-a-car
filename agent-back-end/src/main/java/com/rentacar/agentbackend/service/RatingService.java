package com.rentacar.agentbackend.service;

import com.rentacar.agentbackend.domain.Rating;
import com.rentacar.agentbackend.domain.User;
import com.rentacar.agentbackend.domain.dto.RatingDTO;

import java.util.List;

public interface RatingService {

    Rating createRating(RatingDTO ratingDTO, User creator);

    void deleteRating(Long id);

    List<Rating> getAllRatings();

    void approveRating(Long id);
}
