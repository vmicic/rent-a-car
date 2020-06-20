package com.rentacar.advertisementservice.repository;

import com.rentacar.advertisementservice.domain.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    List<Rating> findAllBySeenEquals(boolean seen);
}
