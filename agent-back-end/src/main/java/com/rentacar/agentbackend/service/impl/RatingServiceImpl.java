package com.rentacar.agentbackend.service.impl;

import com.rentacar.agentbackend.domain.Car;
import com.rentacar.agentbackend.domain.Rating;
import com.rentacar.agentbackend.domain.User;
import com.rentacar.agentbackend.domain.dto.RatingDTO;
import com.rentacar.agentbackend.repository.RatingRepository;
import com.rentacar.agentbackend.service.CarService;
import com.rentacar.agentbackend.service.RatingService;
import com.rentacar.agentbackend.service.UserService;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    private final CarService carService;

    public RatingServiceImpl(RatingRepository ratingRepository, CarService carService) {
        this.ratingRepository = ratingRepository;
        this.carService = carService;
    }

    @Override
    public Rating createRating(RatingDTO ratingDTO, User creator) {
        Rating rating = new Rating();

        Car car = carService.findById(ratingDTO.getCarId());

        rating.setCar(car);
        rating.setRatingPosted(creator);
        rating.setRating(ratingDTO.getRating());
        rating.setComment(ratingDTO.getComment());
        //rating.setApproved(false);

        return this.ratingRepository.save(rating);
    }

	@Override
	public void deleteRating(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Rating> getAllRatings() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void approveRating(Long id) {
		// TODO Auto-generated method stub
		
	}
}
