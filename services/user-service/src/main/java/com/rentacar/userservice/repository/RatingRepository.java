package com.rentacar.userservice.repository;

import com.rentacar.userservice.domain.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {
}
