package com.rentacar.advertisementservice.service.impl;

import com.rentacar.advertisementservice.client.UserServiceClient;
import com.rentacar.advertisementservice.domain.Car;
import com.rentacar.advertisementservice.domain.Rating;
import com.rentacar.advertisementservice.domain.User;
import com.rentacar.advertisementservice.domain.dto.RatingDTO;
import com.rentacar.advertisementservice.repository.RatingRepository;
import com.rentacar.advertisementservice.service.CarService;
import com.rentacar.advertisementservice.service.RatingService;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    private final UserServiceClient userServiceClient;
    private final CarService carService;

    public RatingServiceImpl(RatingRepository ratingRepository, UserServiceClient userServiceClient, CarService carService) {
        this.ratingRepository = ratingRepository;
        this.userServiceClient = userServiceClient;
        this.carService = carService;
    }

    @Override
    public Rating createRating(RatingDTO ratingDTO) {
        Rating rating = new Rating();

        User user = userServiceClient.getLoggedInUser();
        Car car = carService.findById(ratingDTO.getCarId());

        rating.setCar(car);
        rating.setRatingPosted(user);
        rating.setRating(ratingDTO.getRating());
        rating.setComment(ratingDTO.getComment());
        //rating.setApproved(false);

        return this.ratingRepository.save(rating);
    }
}
