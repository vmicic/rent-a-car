package com.rentacar.advertisementservice.service;

import com.rentacar.advertisementservice.domain.Rating;
import com.rentacar.advertisementservice.domain.dto.RatingDTO;

public interface RatingService {

    Rating createRating(RatingDTO ratingDTO);
}
