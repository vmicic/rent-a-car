package com.rentacar.searchservice.repository;

import com.rentacar.searchservice.domain.Advertisement;
import com.rentacar.searchservice.domain.PickupSpot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {

    List<Advertisement> findAllByFromDateIsLessThanEqualAndToDateGreaterThanEqualAndPickupSpotsContains(LocalDateTime fromTime, LocalDateTime toTime, PickupSpot pickupSpot);
}
