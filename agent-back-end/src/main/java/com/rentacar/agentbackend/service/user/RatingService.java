package com.rentacar.agentbackend.service.user;

import com.rentacar.agentbackend.domain.Rating;
import com.rentacar.agentbackend.domain.dto.RatingDTO;

import java.util.List;

public interface RatingService {

    Rating createRating(RatingDTO ratingDTO);

    void deleteRating(Long id);

    List<Rating> getAllRatings();

    void approveRating(Long id);
}
