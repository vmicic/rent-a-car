package com.rentacar.advertisementservice.repository;

import com.rentacar.advertisementservice.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rentacar.advertisementservice.domain.Advertisement;

import java.time.LocalDateTime;
import java.util.List;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long>{

    List<Advertisement> findAllByUserId(Long id);

    //check if valid advertisement for requested reservation time exists
    boolean existsByCarEqualsAndFromDateLessThanEqualAndToDateGreaterThanEqual(Car car, LocalDateTime fromDate, LocalDateTime toDate);
}
