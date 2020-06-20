package com.rentacar.advertisementservice.service;

import com.rentacar.advertisementservice.domain.Rating;
import com.rentacar.advertisementservice.domain.dto.RatingDTO;

import java.util.List;

public interface RatingService {

    Rating createRating(RatingDTO ratingDTO);

    void approveRating(Long id);

    void rejectRating(Long id);

    boolean existById(Long id);

    List<Rating> getAllForApproval();
}
